import peasy.*;
import java.lang.reflect.*;

class PopUp {
  /*
  Always rectangular
  has fade-in / fade-out effect if system supports it (whole thing at once, no per-pixel stuff)
  screen update is called upon request (not by default)
  
  defaulf behavior: start fading-in, specifying time. No auto-deactivation: if specified, must specify time.
  */
  
  PUWindow W;
  plugApplet A;
  private String myName;
  private int myID;
  
  int x, y, w, h, b;
  color bg, border;
  String renderer;
  
  int absMouseX, absMouseY, w_X, w_Y; // mouse and window positions
  int PabsMouseX, PabsMouseY, Pw_X, Pw_Y; // previous mouse and window positions
  int movableButton; // mousebutton that moves the popup around
  screenRegion movableTrigger; // region inside popup where a mousedrag with button movable will move the popup
  boolean isMovable; // helper flag to avoid the need of probing an empty screenRegion
  MouseEvent meP, meR;
  //MouseWheelEvent mwe;
  boolean isMoving, rearmMovable, enableMouse, enableMouseWheel, m_isPressed, mw_hasChanged, m_wasPressed;
  int WID, WMouseDeltaX, WMouseDeltaY;
  Point WSnapshotPos;
  
  float maxalpha; // popup will have alpha = maxalpha when fully active.
  boolean translucencySupport, isActive, isIdle, isAlwaysOnTop;
  Timer fadeTimer, autoFadeTimer;
  int fadeTime, autoFadeTime;
  boolean isFading, restartFade, isAutoFading, restartAutoFade, fadeMode; // fading in or out
  boolean revertFadeFlag, forceAutoFadeTimerRestart; float K; // time correction
  float initialAlpha; // fade starts from this alpha
  boolean autoStop;
  Object parent;
  
  Method myDraw, myMousePressed, myMouseReleased, myMouseClicked;
  
  public PopUp(Object _parent, int _x, int _y, int _w, int _h, int _b, String _renderer, int _fadeintime, float _maxalpha, color _bg, color _border) {
    x = _x; y = _y; w = _w; h = _h; b = _b;
    bg = _bg; border = _border;
    renderer = _renderer;
    parent = _parent;
    
    this.A = new plugApplet(parent, this, w -2*b, h -2*b, renderer, bg);
    this.W = new PUWindow(A, frame);
    
    translucencySupport = (AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.TRANSLUCENT)) ? (true) : (false) ;
    
    fadeTime = _fadeintime; maxalpha = _maxalpha;
    isFading = isActive = false;
    fadeMode = true;
    restartFade = true; // request fading restart (actvate fading daemon)
    fadeTimer = new Timer(fadeTime);
    fade();
    // autoFader is disabled by default
    autoFadeTimer = new Timer(0); // dummy
    isAutoFading = false; restartFade = false;
    K = 1; revertFadeFlag = false; forceAutoFadeTimerRestart = false;
    
    if (translucencySupport) {
      //this.W.setVisible(false);
      AWTUtilities.setWindowOpacity(this.W, 0);
      //this.W.setVisible(true);
    }
    initialAlpha = 0;
    
    movableTrigger = new screenRegion(this);
    movableButton = MouseEvent.BUTTON1; isMovable = true;
    m_isPressed = mw_hasChanged = m_wasPressed = false;
    enableMouse = enableMouseWheel = true;
    meR = meP = new MouseEvent(this.W, 0, 0, 0, -screen.height, -screen.width, 0, false, MouseEvent.NOBUTTON);
    //mwe = new MouseWheelEvent(this.A, 0, 0, 0, -screen.height, -screen.width, 0, false, MouseWheelEvent.WHEEL_UNIT_SCROLL, 0, 0);
    setMovableTrigger(3, 0, 0, 0, 0);
    
    setEncapsulation(0, "");
    autoStop = false; isIdle = false; isAlwaysOnTop = false; rearmMovable = true;
  }
  
  public PopUp(PopUp _p) {
    this(_p.parent, _p.x, _p.y, _p.w, _p.h, _p.b, _p.renderer, _p.fadeTime, _p.maxalpha, _p.bg, _p.border);
  }
  
  public PopUp(Object _parent, int _x, int _y, int _w, int _h, int _b, String _renderer, int _fadeintime, 
               float _maxalpha, color _bg, color _border, int _ID, String _name) {
    this(_parent, _x, _y, _w, _h, _b, _renderer, _fadeintime, _maxalpha, _bg, _border);
    setEncapsulation(_ID, _name);
  }
  
  public void setEncapsulation(int _myID, String _myName) {
    myName = new String(_myName);
    myID = _myID;
    
    Class tmp = parent.getClass();
    try {
      myDraw = tmp.getMethod(myName + "_draw");
    } catch (Exception e) {};
    try {
      myMousePressed = tmp.getMethod(myName + "_mousePressed");
    } catch (Exception e) {};
    try {
      myMouseReleased = tmp.getMethod(myName + "_mouseReleased");
    } catch (Exception e) {};
    try {
      myMouseClicked = tmp.getMethod(myName + "_mouseClicked");
    } catch (Exception e) {};
    this.A.setMethods(myDraw, myMousePressed, myMouseReleased, myMouseClicked);
  }
  
  public String getMyName() {
    return myName;
  }
  
  public int getMyID() {
    return myID;
  }
  
  public void daemons() { // MUST BE CALLED IN MAIN APPLET'S DRAW() ALWAYS, NO CONDITIONAL! OR IN ANOTHER LISTENER-THREAD!
    fade();
    autoFade();
    if (isActive) movable();
    else if (autoStop) isIdle = true; 
  }
  
  public void setIdle(boolean _isIdle) {
    isIdle = _isIdle;
  }
  
  public void setAutoStop(boolean _autoStop) {
    autoStop = _autoStop;
  }
  
  public void setAppletFrameRate(int _frameRate) {
    this.A.setFrameRate(_frameRate);
  }
  
  public void update() {
    if (isActive && !isIdle) this.A.redraw();
  }
  
  public void fade() {
    /* fading daemon. Uses vars fadeMode and fadeTime, defined by setters
       if autofading && !fading && restartFade -> (was autofading and startFade() was called)
     cancel autofading, deactivate autofade daemon and start fading normally
     (FADE HAS PRECEDENCE)
    */
    if (!translucencySupport || fadeTime == 0) { // translucency not available or instantaneous fadeTime
      isActive = fadeMode; isFading = false; restartFade = false;
      if (isActive) this.W.setVisible(true);
      else this.W.setVisible(false);
      if (translucencySupport) { // if has translucency but fadeTime == 0
        if (fadeMode) AWTUtilities.setWindowOpacity(this.W, maxalpha);
        else AWTUtilities.setWindowOpacity(this.W, 0);
      }
    } else { // translucency is available, let's throw eye-candy
      if (!isFading) { // wasn't fading; set things up to start daemon on next call
        if (restartFade) {
          if (isAutoFading) {
            isAutoFading = false; restartAutoFade = false; // deactivate autoFade daemon
            if (GLOBAL.VERBOSE) println("CANCELING AUTO FADE. Going fading now!");
          }
          initialAlpha = AWTUtilities.getWindowOpacity(this.W);
          fadeTimer.restart();
          isFading = true; restartFade = false;
          isActive = true; // must be active while fading or will not be redrawn while fading
          K = 1;
          if (fadeMode) { // trickery to avoid blinking colours
            this.W.setVisible(false);
            AWTUtilities.setWindowOpacity(this.W, 0);
            this.W.setVisible(true);
          }
        }
      } else { // was already fading, work as daemon
        float timePercent;
        float alphaPercent;
        if (restartFade) {
          initialAlpha = AWTUtilities.getWindowOpacity(this.W);
          restartFade = false;
          // correct fadeTime to stretch timePercent
          K = (fadeMode) ? ( 1.0/(1-initialAlpha/maxalpha) ) : ( maxalpha/initialAlpha ) ;
          fadeTimer.setTime(round(K*fadeTime));
          fadeTimer.restart();
        }
        timePercent = float(fadeTimer.elapsed())/ ( K*fadeTime );
        if (fadeMode) { // fading in
          alphaPercent = initialAlpha/maxalpha + timePercent;
          if (alphaPercent >= 1 - 0.005) { // 0.005 is tolerance
            alphaPercent = 1;  
            isActive = true; 
            restartFade = false; isFading = false; // deactivate daemon 
            // leave initial settings for next daemon call (request fading restart)
            initialAlpha = maxalpha;
          }
        } else { // fading out
          alphaPercent = initialAlpha/maxalpha - timePercent;
          if (alphaPercent <= 0 + 0.005) { // 0.005 is tolerance
            alphaPercent = 0; 
            isActive = false; 
            restartFade = false; isFading = false; // deactivate daemon 
            // leave initial settings for next daemon call (request fading restart)
            initialAlpha = 0;
            this.W.setVisible(false);
          }
        }
        AWTUtilities.setWindowOpacity(this.W, alphaPercent*maxalpha);
      }
    }
  }
  
  public void setFade(int _fadeTime, boolean _fadeMode) {
    fadeTimer.setTime(_fadeTime);
    fadeTime = _fadeTime;
    fadeMode = _fadeMode;
  }
  
  public void setFade(int _fadeTime) {
    fadeTimer.setTime(_fadeTime);
    fadeTime = _fadeTime;
  }
  
  public void setFade(boolean _fadeMode) {
    fadeMode = _fadeMode;
  }
  
  public boolean getLastFadeMode() {
    return fadeMode;
  }
  
  public int getLastFadeTime() {
    return fadeTime;
  }
  
  public void startFade() { 
    // can to be called only once for each time a restart is needed or daemon locks
    // with previously set fadeTime and fadeMode
    restartFade = true;
  }
  
  public void revertFade() {
    /* if fading -> reverts fading
       if !fading -> do nothing
    */
    if (isFading) {
      setFade(getLastFadeTime(), !getLastFadeMode()); // revert fading direction
      if (translucencySupport) initialAlpha = AWTUtilities.getWindowOpacity(W);
      fadeTimer.restart(); // restart fading timer to revert at the same speed it was going before
    }
  }
  
  public void autoFade() {
    /* 1) if !fading -> start autoFader daemon, count time and after autoFadeTime is 
      reached, start fading in the opposite fadeMode as it was just before starting to fade
      and deactivate autoFader itself
       2) else -> revert fading to get back to state before fading and from there start
      excuting (1)
      ----------------------------------------
       3) if autofading:
       3)1) if restartAutoFade requested -> restart autoFadeTimer
       3)2) else -> keep on counting time normally (FADE HAS PRECEDENCE. See fade())
       4) else -> 
       4)1) if restartAutoFade requested -> check fading and do either (1) or (2)
       4)2) else -> DO NOTHING
    */
    if (isAutoFading) {
      if (restartAutoFade) {
        autoFadeTimer.setTime(autoFadeTime);
        autoFadeTimer.restart();
        restartAutoFade = false;
      } else {
        //autoFadeClock(isFading);
        
        if (isFading) { // revert!
          if (revertFadeFlag) {
            revertFade();
            revertFadeFlag = false; forceAutoFadeTimerRestart = true;
          }
        } else { 
          /* after reverting is complete and isFading is false, or regular operation in
          case it was not even fading when restartAutoFade was requested, we can begin
          normal operation: wait until time reaches autoFadeTime and then fire fade() daemon
          with fadeMode opposite to last state, and then deactivate autoFade daemon
          */
          if (forceAutoFadeTimerRestart) {
            autoFadeTimer.setTime(autoFadeTime);
            autoFadeTimer.restart();
            forceAutoFadeTimerRestart = false;
          }
          float timePercent = float(autoFadeTimer.elapsed()) / (float(autoFadeTime));
            if (timePercent >= 1 - 0.005) { // 0.005 is tolerance
            fadeMode = !isActive;
            startFade();
            isAutoFading = false; restartAutoFade = false;
          }
        }
        
      }
    } else {
      if (restartAutoFade) {
        isAutoFading = true;
        autoFadeTimer.setTime(autoFadeTime);
        autoFadeTimer.restart();
        restartAutoFade = false;
      }
    }
  }
  
  public void setAutoFadeTime(int _autoFadeTime) { 
    // can to be called only once each time a restart is needed or daemon locks
    if (_autoFadeTime == 0) { // flag to cancel daemon operation
      isAutoFading = restartAutoFade = false;
    } else {
      autoFadeTime = _autoFadeTime;
      autoFadeTimer.setTime(autoFadeTime);
    }
  }
  
  public void startAutoFade(boolean cancelCurrentFading) {
    // true: revert fade() and startautofade over
    // false: parallel; autofade and fade will behave as 2 independent daemons
    restartAutoFade = true;
    revertFadeFlag = cancelCurrentFading;
  }
  
  public void bringToFront() {
    if (isActive) { // this method only reorganises windows in the screen (sets current on top)
    // to bring popups from the dead, use startFade()
      this.W.toFront();
    }
  }
  
  public void hide() { // alias to hide popup at once
    fadeTime = 0;
    fadeMode = false;
    startFade();
  }
  
  public void setMovableButton(int _movableButton) {
    switch(_movableButton) {
      case MouseEvent.BUTTON1:
      case MouseEvent.BUTTON2:
      case MouseEvent.BUTTON3: isMovable = true; movableButton = _movableButton; break;
      case MouseEvent.NOBUTTON:
      default: isMovable = false; movableButton = 0; break;
    }
  }
    
  public void setMovableTrigger(int mode, int p1, int p2, int p3, int p4) {
    // build screenregion as rectangle
    // mode = 1: center + 2 dimensions
    // mode = 2: 2 corner points
    // mode = 3: around border
    switch(mode) {
      case 1:
        movableTrigger.addRectangle(1, p1, p2, p3, p4);
        break;
      case 2:
        movableTrigger.addRectangle(2, p1, p2, p3, p4);
        break;
      case 3:
        movableTrigger.addRectangle(2, 0, 0, b, h);
        movableTrigger.addRectangle(2, 0, 0, w, b);
        movableTrigger.addRectangle(2, w - b, 0, b, h);
        movableTrigger.addRectangle(2, 0, h - b, w, b);
        break;
    }
  }
    
  public void setMovableTrigger(PImage _custommap, color _mc) { // set custom screenregion (WILL BE RESIZED TO FIT WITHIN POPUP!)
    movableTrigger.addImage(_custommap, _mc, true);
  }
  
  public void movable() {
    if (isActive && isMovable) {
      if (!isMoving) {
        if (getMouseState() && 
            movableTrigger.isPointInRegion(meP.getPoint()) && 
            meP.getButton() == movableButton &&
            rearmMovable) {
              bringToFront();
              isMoving = true;
              GLOBAL.getScreenInfo.updateContainerPosition(myID); // get W ID in getScreenInfo's lists and update
              WSnapshotPos = GLOBAL.getScreenInfo.getCContainerPosition(myID); // and save snapshot W position when daemon starts
              // WSnapshotPos is global to be able to restore W position if user presses ESC while dragging
              Point mouseSnapshotPos = GLOBAL.getScreenInfo.curabsMousePos(); // snapshot mousePos
              WMouseDeltaX = WSnapshotPos.x - mouseSnapshotPos.x -b;
              WMouseDeltaY = WSnapshotPos.y - mouseSnapshotPos.y -b;
              //WMouseDelta is a vector that goes from mouseSnapshotPos to WSnapshotPos
            }
      } else {
        isMoving = GLOBAL.disableUIFocus = m_isPressed; // releasing the mouse will deactivate daemon
        Point mpos = GLOBAL.getScreenInfo.curabsMousePos();
        this.W.setLocation(WMouseDeltaX + mpos.x, WMouseDeltaY + mpos.y);
        if(GLOBAL.getKeyInfo.KC_probeKey(ESC)) {
          this.W.setLocation(WSnapshotPos);
          isMoving = rearmMovable = GLOBAL.disableUIFocus = false;
        }
      }
    }
  }
  
  public boolean isSetupDone() {
    return A.plugAppletSetupDone;
  }
  
  public plugApplet getApplet() {
    return A;
  }
  
  public PUWindow getWindow() {
    return W;
  }
  
  public boolean isActive() {
    return isActive;
  }
  
  public void setPositionRel(Container _C, int _x, int _y) {
    // set position relative to container (by explicit reference); if updated during runtime, follows container
    if (_C != null) {
      int _CID;
      _CID = GLOBAL.getScreenInfo.updateContainerPosition(_C);
      Point p, q;
      p = GLOBAL.getScreenInfo.getCContainerPosition(_CID);
      q = new Point(p.x + _x, p.y + _y);
      updatePosition(q);
    }
  }
  
  public void setPositionRel(int _CID, int _x, int _y) {
    // set position relative to container (by ID, faster than above); if updated during runtime, follows container
    GLOBAL.getScreenInfo.updateContainerPosition(_CID);
    
    Point p, q;
    p = GLOBAL.getScreenInfo.getCContainerPosition(_CID);
    q = new Point(p.x + _x, p.y + _y);
    updatePosition(q);
  }
  
  private void updatePosition(Point p) {
    x = p.x; y = p.y;
    W.setLocation(p.x, p.y);
  }
  
  public void setMyName(String _myName) {
    myName = new String(_myName); // copy value, not reference. For safety
  }
  
  public boolean isAlwaysOnTop() {
    return isAlwaysOnTop;
  }
  
  public void setAlwaysOnTop(boolean _isAlwaysOnTop) {
    isAlwaysOnTop = _isAlwaysOnTop;
  }
  
  public boolean getMouseState() {
    if (this.A.m_isPressed) return true;
    if (m_isPressed) return true;
    return false;
  }
  
  class PUWindow extends JWindow {
    JPanel borders; //http://www.rgagnon.com/javadetails/java-0204.html
    
    public PUWindow(PApplet _app, Frame _ownerFrame) {
      /*
      Window to hold another an extended PApplet where popup stuff gets drawn.
      The border of the window is merely eye-candy; won't detect mouse clicks or anything.
      */
      super(_ownerFrame);
      _app.init();
      borders = new JPanel();
      try { // solucao coxa
        Thread.sleep(100);
      } catch (Exception e) {}
      borders.setBackground( new Color(int(red(border)), int(green(border)), int(blue(border))) );
      GridBagConstraints constr = new GridBagConstraints();
      borders.setLayout(new GridBagLayout());
      borders.add(_app, constr);
      getContentPane().add(borders);
      
      setSize(w, h);
      setLocation(x, y);
      setVisible(false); // starts hidden by default, popup fires it up with fade(), fadeMode true (fade in)

      absMouseX = x;
      absMouseY = y;
      
      this.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent _e) {
          if (enableMouse) {
            rearmMovable = true;
            meP = _e;
            m_isPressed = true;
          }
        }
        
        public void mouseReleased(MouseEvent _e) {
          if (enableMouse) {
            meR = _e;
            m_isPressed = false;
          }
        }
      });
      
    }
  }
  
}

class plugApplet extends PApplet {
  int w, h, CID;
  color bg;
  String MODE;
  PeasyCam cam;
  boolean space3D;
  
  boolean m_isPressed, m_wasPressed, mw_hasChanged, enableMouse, enableMouseWheel;
  //MouseWheelEvent mwe;
  
  boolean globalSetupDoneFlag, plugAppletSetupDone;
  
  Object parent; PopUp parent2;
  Method myDraw, myMousePressed, myMouseReleased, myMouseClicked;
  
  public plugApplet(Object _parent, PopUp _parent2, int _w, int _h, String _MODE, color _bg) {
    super();
    w = _w; h = _h; MODE = _MODE; bg = _bg;
    m_isPressed = mw_hasChanged = m_wasPressed = false;
    enableMouse = enableMouseWheel = true;
    //mwe = new MouseWheelEvent(this, 0, 0, 0, -screen.height, -screen.width, 0, false, MouseWheelEvent.WHEEL_UNIT_SCROLL, 0, 0);
    space3D = (MODE.equals(OPENGL) || MODE.equals(P3D)) ? (true) : (false) ;
    
    CID = 0;
    
    globalSetupDoneFlag = false;
    plugAppletSetupDone = false;
    
    parent = _parent; parent2 = _parent2;
  }
  
  public void setFrameRate(int _frameRate) {
    frameRate(_frameRate); 
  }
  
  public void setMethods(Method _myDraw, Method _myMousePressed, Method _myMouseReleased, Method _myMouseClicked) {
    myDraw = _myDraw; myMousePressed = _myMousePressed; myMouseReleased = _myMouseReleased; myMouseClicked = _myMouseClicked;
  }
  
  public void setup() {
    size(w, h, MODE);
    
    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent _e) {
        if (enableMouse) {
          _e.translatePoint(parent2.b, parent2.b);
          parent2.meP = _e;
          m_wasPressed = m_isPressed;
          m_isPressed = true;
          
          GLOBAL.setLastFocus(parent2.myID); // rearm lastFocus
          
          if (myMousePressed != null) {
            try {
              myMousePressed.invoke(parent);
            } catch (Exception e) {}
          }
        }
      }
        
      public void mouseReleased(MouseEvent _e) {
        if (enableMouse) {
          _e.translatePoint(parent2.b, parent2.b);
          parent2.meR = _e;
          m_wasPressed = m_isPressed;
          m_isPressed = false;
          if (myMouseReleased != null) {
            try {
              myMouseReleased.invoke(parent);
            } catch (Exception e) {}
          }
        }
      }
      
      public void mouseClicked(MouseEvent _e) {
        if (enableMouse) {
          parent2.meP = parent2.meR = _e;
          m_wasPressed = true; m_isPressed = false;
          if (myMouseClicked != null) {
            try {
              myMouseClicked.invoke(parent);
            } catch (Exception e) {}
          }
        }
      }
    });
    
    this.addMouseWheelListener(new MouseAdapter() {
      public void mouseWheelMoved(MouseWheelEvent _e) {
        if (enableMouseWheel) {
          //mwe = _e;
          //mw_hasChanged = true;
          GLOBAL.getMouseWheelInfo.mwaction(_e);
        }
      }
    });
    
    if (space3D) {
      cam = new PeasyCam(this, 100);
      cam.setMinimumDistance(50);
      cam.setMaximumDistance(500);
    }
    
    boolean falseStart = true;
    while(falseStart) {
      try {
        if (GLOBAL.isSetupDone) falseStart = false;
      } catch(NullPointerException e) {
      }
    }
    CID = GLOBAL.getScreenInfo.updateContainerPosition(this);
    
    plugAppletSetupDone = true;
    
    noLoop();
  }
  
  public void draw() {
    background(bg); // clear stuff behind to avoid unwanted overlaps of shapes and colours

    if (myDraw != null) {
      try {
        myDraw.invoke(parent);
      } catch (Exception e) {}
    }
  }
  
  public void keyTyped() {
    if (key == 'k') println("HÁ!");
  }
  
  public void setMouse(boolean _enableMouse) {
    enableMouse = _enableMouse;
  }
  
  public void setMouseWheel(boolean _enableMouseWheel) {
    enableMouseWheel = _enableMouseWheel;
  }
  
  public boolean mouseWheelLastState() {
    return mw_hasChanged;
  }
  
  public PeasyCam getCamera() {
    // returns a reference to the camera object, that can be used to call PeasyCam methods
    return cam;
  }
  
  public Point getPosition() {
    return GLOBAL.getScreenInfo.getCContainerPosition(CID);
  }
}

class loadingPopUp extends JWindow { // not a subclass of PopUp because this MUST be VERY simple to load VERY fast
  private LPApplet canvas;
  private int x, y, dim, num, period;
  private color bg, fg;
  private boolean hasCloseBeenCalled;
  
  public loadingPopUp(int _x, int _y, int _dim, color _bg, color _fg, int _num, int _period) {
    x = _x; y = _y; dim = _dim; bg = _bg; fg = _fg; num = _num; period = _period;
    canvas = new LPApplet();
    add(canvas);
    setSize(dim, dim);
    setLocation(x - round(dim*0.5), y - round(dim*0.5));
    setVisible(true);
    canvas.init();
    hasCloseBeenCalled = false;
    //period is how many cycles per frame
  }
  
  public void bringToFront() {
    setVisible(true);
  }
  
  public void close() {
    hasCloseBeenCalled = true;
    canvas.setVisible(false);
    this.dispose();
  }
  
  public boolean closeCalled() {
    return hasCloseBeenCalled;
  }
  
  private class LPApplet extends PApplet {
    float ed, r;
    color eshade;
    float [] posx, posy;
    int i;
    color cur_fill;
    
    
    public LPApplet() {
      super();
      ed = dim*PI/(1.2*num + PI);
      r = (dim*0.95 - ed)*0.5;
      posx = new float[num]; posy = new float[num];
      for (i = 0; i < posx.length; i++) {
        posx[i] = 0.5*dim + r*sin(-i*2*PI/num);
        posy[i] = 0.5*dim + r*cos(-i*2*PI/num);
      }
    }
    
    public void setup() {
      size(dim, dim, P2D);
      eshade = lerpColor(bg, fg, 0.5);
      noStroke();
      ellipseMode(CENTER);
    }
    
    public void draw() {
      cur_fill = eshade;
      
      background(bg);
      
      for (i = 0; i < num; i++) {
        cur_fill = (int(frameCount/period) % num == i) ? (fg) : (eshade) ;
        fill(cur_fill);
        ellipse(posx[i], posy[i], ed, ed);
      }
    }
  }
}

public class PopUpManager { // manages all popups. CREATE ONLY ONE POPUPMANAGER AND ADD ALL YOUR POPUPS TO IT!
  private int counter;
  ArrayList PopUpList;
  ArrayList namesList;
  private daemonThread daemons;
  
  public PopUpManager() {
    counter = 0;
    PopUpList = new ArrayList(0);
    namesList = new ArrayList(0);
    daemons = new daemonThread();
  }
  
  public boolean arePopUpsSetUp() { // returns true if all popups until the last one added have their setup() completed
    for (int i = PopUpList.size()-1; i >= 0; i--) { // backwards because last popups are more likely to be still loading
      if (!(((PopUp)PopUpList.get(i)).isSetupDone())) return false;
    }
    return true;
  }
  
  public int addPopUp(Object _parent, String _name, int _x, int _y, int _w, int _h, int _b, String _renderer, int _fadeintime, float _maxalpha, color _bg, color _border) {
    String TMP;
    TMP = (_name.equals("")) ? ("popup" + (counter)) : (_name) ;
    namesList.add(TMP);
    
    PopUp added = new PopUp(_parent, _x, _y, _w, _h, _b, _renderer, _fadeintime, _maxalpha, _bg, _border, counter, TMP);
    PopUpList.add( added );
    
    GLOBAL.getScreenInfo.addContainerToList(added.getApplet());
    
    counter++;
    return (counter - 1);
  }
  
  public int addPopUp(String _name, PopUp _clonable) {
    String TMP;
    TMP = (_name.equals("")) ? (_clonable.getMyName()) : (_name) ;
    _clonable.setEncapsulation(counter, TMP);
    namesList.add(TMP);
    
    PopUp added = new PopUp(_clonable);
    PopUpList.add( added );
    
    GLOBAL.getScreenInfo.addContainerToList(added.getApplet());
    
    counter++;
    return (counter - 1);
  }
  
  public void removePopUp(String _name) {
    int [] storedID = new int [1];
    if (getPopUp(_name, storedID) != null) {
      PopUpList.remove(storedID[0]);
    } else {
      println(this + "PopUp name not found");
    }
  }
  
  public void removePopUp(int _ID) {
    try {
      PopUpList.remove(_ID);
    } catch (Exception e) {
      println(this + "PopUp ID not found");
    }
  }
  
  public PopUp getPopUp(String _name) {
    return getPopUp(_name, null);
  }
  
  public PopUp getPopUp(String _name, int [] _storeID) {
    for (int i = 0; i < namesList.size(); i++) {
      if (namesList.get(i).equals(_name)) {
        if (_storeID != null) _storeID[0] = i;
        return (PopUp) PopUpList.get(i);
      }
    }
    println(this + "PopUp name not found");
    if (_storeID != null) _storeID[0] = -1;
    return null;
  }
  
  public PopUp getPopUp(int _ID) {
    try {
      return (PopUp) PopUpList.get(_ID);
    } catch(Exception e) {
      println(this + "PopUp ID not found!");
      return null;
    }
  }
  
  public void blink() { // blink all visible
    int i;
    for (i = 0; i < PopUpList.size(); i++) {
      PopUp TMP = (PopUp) PopUpList.get(i);
      if (TMP.isActive()) {
        TMP.getWindow().setVisible(false);
        TMP.getWindow().setVisible(true);
      }
    }
  }
  
  public void blink(String _name) {
    PopUp TMP = getPopUp(_name);
    if (TMP != null && TMP.isActive()) {
      TMP.getWindow().setVisible(false);
      TMP.getWindow().setVisible(true);
    }
  }
  
  public void blink(int _ID) {
    PopUp TMP = getPopUp(_ID);
    if (TMP != null && TMP.isActive()) {
      TMP.getWindow().setVisible(false);
      TMP.getWindow().setVisible(true);
    }
  }
  
  private class daemonThread extends Thread {
    
    public daemonThread() {
      this.start();
    }
    
    public void run() {
      int i;
      while(true) {
        try {
          Thread.sleep(100); // save CPU
        } catch (Exception e) {}
        for (i = 0; i < PopUpList.size(); i++) {
          try {
            PopUp TMP = (PopUp) PopUpList.get(i);
            TMP.daemons();
            if (i == GLOBAL.getLastFocus()) {
              TMP.bringToFront();
              GLOBAL.setLastFocus(-1); // disarm lastFocus
            }
            if (TMP.isAlwaysOnTop()) TMP.bringToFront(); // if a popup requires alwaysOnTop, do it in the order they were added
          } catch (Exception e) {}
        }
      }
    }
    
  }
  
}
