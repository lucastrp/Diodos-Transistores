// for screenRegion
import java.awt.geom.RectangularShape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Arc2D;

globalTrickery GLOBAL; // trickery

public class globalTrickery {
  PApplet reference;
  
  public screenInfo getScreenInfo; 
  public screenRegion wholeScreen;
  public keyboardStateStorage getKeyInfo;
  public mouseWheelInfo getMouseWheelInfo;
  
  public boolean isSetupDone;
  public int lastFocus;
  
  public boolean VERBOSE;
  public boolean disableUIFocus;

  public globalTrickery(PApplet _reference) {
    reference = _reference;
    VERBOSE = false;
    
    getScreenInfo = new screenInfo(screen.width/2, screen.height/2);
    wholeScreen = new screenRegion();
    getKeyInfo = new keyboardStateStorage();
    getMouseWheelInfo = new mouseWheelInfo(reference);
    isSetupDone = false;
    lastFocus = -1; // start disarmed
    registerKeyEvent(this);
    disableUIFocus = false;
  }

  public void keyEvent(KeyEvent e) {
    switch (e.getID()) {
    case KeyEvent.KEY_PRESSED:
      if (e.getKeyCode() == e.VK_ESCAPE) {
        key = 0; 
        keyCode = -1;
      }
      getKeyInfo.KC_setKey(e.getKeyCode(), true);
      break;
    case KeyEvent.KEY_RELEASED:
      getKeyInfo.KC_setKey(e.getKeyCode(), false);
      break;
    }
  }
  
  public void setupDone() {
    isSetupDone = true;
  }
  
  public int getLastFocus() {
    return lastFocus;
  }
  
  public void setLastFocus(int _lastFocus) {
    lastFocus = _lastFocus;
  }
}

public class Timer { // convenience class; timePassed returns true after time has passed
  private int time, timerMark;
  private boolean running;

  Timer(int _time) {
    time = _time;
    running = true;
    restart();
  }

  public void restart() {
    timerMark = millis();
    running = true;
  }

  public boolean check() {
    // daemon: returns true after timer has reached its time until restart
    if (running) {
      if (elapsed() >= time) { 
        running = false;
        return true;
      }
      else return false;
    } 
    else return true;
  }

  public int elapsed() {
    // daemon: returns elapsed time regardless of whether timer has reached time
    return (millis() - timerMark);
  }

  public boolean beep() {
    // daemon: returns true once it has reached, false all the rest of the time
    if (elapsed() >= time) {
      if (running) {
        running = false;
        return true;
      } 
      else return false;
    } 
    else return false;
  }

  public void stop() {
    timerMark = millis();
    running = false;
  }

  public void setTime(int _time) {
    time = _time;
  }

  public int getTime() {
    return time;
  }
}


public class screenRegion { // class to map the screen
  /* rectangle (center point + 2 dimensions; 2 corner points)
   ellipse (center point + 2 radii; 2 frame corner points)
   polygon (each point -> offscreen buffer)
   object (copy contour of object -> offscreen buffer)
   offscreen buffer: like 3D-picking: redirects draw() of object to
   offscreen buffer
   
   rectangle and ellipse have var mode when defined, to flag which
   definition was used
   add methods return contour ID, if user wants to remove them later
   (add methods are additive... do NOT call them continuously in draw()!)
   */

  private binaryMap Map;
  
  public screenRegion(int _w, int _h) { // custom map
    settings(_w, _h);
  }

  public screenRegion() { // map whole screen (absolute coords)
    settings(screen.width, screen.height);
  }

  public screenRegion(PopUp P) { // map only a popup (relative coords)
    settings(P.w, P.h);
  }

  public screenRegion(PApplet A) { // map only a PApplet (inside a popup or main PApplet; relative coords)
    settings(A.width, A.height);
  }
  
  public screenRegion(screenRegion _clonable) { // clone an existing screenRegion
    clone(_clonable);
  }

  private void settings(int _w, int _h) {
    Map = new binaryMap(_w, _h);
  }

  public void addRectangle(int mode, float p1, float p2, float p3, float p4) { // define a rectangular contour
    Rectangle2D.Float recta = new Rectangle2D.Float();
    
    switch(mode) {
      case 1:
        recta = new Rectangle2D.Float(p1 - p3*0.5, p2 - p4*0.5, p3, p4);
        break;
      case 2:
        recta = new Rectangle2D.Float(p1, p2, p3, p4);
        break;
    }
    
    checkPoints(recta);
  }

  public void addEllipse(int mode, float p1, float p2, float p3, float p4) { // define an eliptical contour
    Ellipse2D ellip = new Ellipse2D.Float();
    
    switch(mode) {
      case 1:
        ellip = new Ellipse2D.Float(p1 - p3*0.5, p2 - p4*0.5, p3, p4);
        break;
      case 2:
        ellip = new Ellipse2D.Float(p1, p2, p3, p4);
        break;
    }
    
    checkPoints(ellip);
  }
  
  public void addPolygon(Point [] points) { // define a polygonal contour
    int [] TMPx, TMPy;
    int i;

    TMPx = new int[points.length]; 
    TMPy = new int[points.length];
    for (i = 0; i < points.length; i++) {
      TMPx[i] = round(points[i].x);
      TMPy[i] = round(points[i].y);
    }

    Polygon poly = new Polygon(TMPx, TMPy, points.length);
    
    checkPoints(poly);
  }
  
  public void addRoundRectangle(int mode, float p1, float p2, float p3, float p4, float arcw, float arch) { // define a rounded rectangle contour
    RoundRectangle2D rrect = new RoundRectangle2D.Float();
    
    switch(mode) {
      case 1:
        rrect = new RoundRectangle2D.Float(p1 - p3*0.5, p2 - p4*0.5, p3, p4, arcw, arch);
        break;
      case 2:
        rrect = new RoundRectangle2D.Float(p1, p2, p3, p4, arcw, arch);
        break;
    }
    
    checkPoints(rrect);
  }
  
  public void addArc(int mode, float p1, float p2, float p3, float p4, float start, float extent, int type) {
    // define an arc contour (type must be closed!!! open is useless)
    Arc2D arcc = new Arc2D.Float();
    
    switch(mode) {
      case 1:
        arcc = new Arc2D.Float(p1 - p3*0.5, p2 - p4*0.5, p3, p4, start, extent, type);
        break;
      case 2:
        arcc = new Arc2D.Float(p1, p2, p3, p4, start, extent, type);
        break;
    }
    
    checkPoints(arcc);
  }
  
  private void checkPoints(Shape _shape) {
    for (int i = 0; i < Map.getW(); i++) {
      for (int j = 0; j < Map.getH(); j++) {
        if (_shape.contains(i, j)) Map.setPixel(i, j, true);
      }
    }
  }
  
  public void addImage(PImage img, color mc, boolean _isInclusive) { // define a custom contour
    // Copy must be of same size as Map and mapped stuff in colour mc
    int i, w, h;
    
    w = Map.getW(); h = Map.getH();
    if (img.width != w || img.height != h) {
      if (GLOBAL.VERBOSE) println(this + ".addImage: Images not of the same size; resizing");
      img.resize(w, h);
    }
    /* avoid problems with screenTopoRegion:
      that extension will use more than 1 colour to map and there could be pixels
      already in those other mapping-colours, and there will be no way to know which
      pixels were mapped to those other mapping-colours and which already came with them
      if this clean-up is not made now
    */
    img.loadPixels();
    if (_isInclusive) {
      if (alpha(mc) == 0) { // testing for transparency, colour does not matter, only alpha!
        for (i = 0; i < w*h; i++) {
          if (alpha(img.pixels[i]) == 0) Map.setPixel(i % w, int (i / w), true);
        }
      } else {
        for (i = 0; i < w*h; i++) {
          if (img.pixels[i] == mc) Map.setPixel(i % w, int (i / w), true);
        }
      }
    } else {
      if (alpha(mc) == 0) {
        for (i = 0; i < w*h; i++) {
          if (alpha(img.pixels[i]) != 0) Map.setPixel(i % w, int (i / w), true);
        }
      } else {
        for (i = 0; i < w*h; i++) {
          if (img.pixels[i] != mc) Map.setPixel(i % w, int (i / w), true);
        }
      }
    }
  }

  public boolean isPointInRegion(Point p) {
    return (Map.probe(p));
  }
  
  public int getW() {
    return Map.getW();
  }
  
  public int getH() {
    return Map.getH();
  }
  
  public void clone(screenRegion _clonable) { // clone current variables of screenRegion _clonable to itself
    settings(_clonable.getMap().w, _clonable.getMap().h);
    Map.clone(_clonable.getMap());
  }
  
  public void fit(int _newW, int _newH, boolean _scale, int _xoffset1, int _yoffset1, int _xoffset2, int _yoffset2) {
    //fit map to new size with offsets
    //binaryMap BCKP = new binaryMap(Map);
    PImage OLD = Map.binary2image();
    PImage NEW;
    _newW = max(0, _newW); _newH = max(0, _newH);

    if (_scale) { // scale
      NEW = createImage(_newW, _newH, RGB); 
      NEW.copy(OLD, 0, 0, Map.getW(), Map.getH(), 0, 0, _newW, _newH);
    } else { // trim or expand canvas with map centered, no scaling
      int sx = 0, sy = 0, sw, sh, dx, dy;
      
      sx = (OLD.width > _newW) ? (round(OLD.width*0.5 - _newW + _xoffset1)) : (_xoffset1);
      sy = (OLD.height > _newH) ? (round(OLD.height*0.5 - _newH + _yoffset1)) : (_yoffset1);
      if (sx < 0 || sx > OLD.width) sx = 0;
      if (sy < 0 || sy > OLD.height) sy = 0;
      sw = min(OLD.width, _newW);
      sh = min(OLD.height, _newH);
      
      dx = (OLD.width > _newW) ? (_xoffset2) : (round((_newW - OLD.width)*0.5 + _xoffset2)) ;
      dy = (OLD.height > _newH) ? (_yoffset2) : (round((_newH - OLD.height)*0.5 + _yoffset2)) ;
      if (dx < 0 || dx > _newW) dx = 0;
      if (dy < 0 || dy > _newH) dy = 0;
      
      NEW = createImage(_newW, _newH, RGB);
      for (int i = 0; i < NEW.pixels.length; i++) NEW.pixels[i] = #000000;
      NEW.copy(OLD, sx, sy, sw, sh, dx, dy, sw, sh);
    }
    
    Map = new binaryMap(_newW, _newH);
    addImage(NEW, #FFFFFF, true);
  }
  
  public binaryMap getMap() { // get map reference, not map copy!
    return Map;
  }
  
  public PImage getImage() {
    return Map.binary2image();
  }
  
  class binaryMap {
    private boolean [][] MAP;
    private int w, h;
    
    public binaryMap(int _w, int _h) {
      settings(_w, _h);
    }
    
    public binaryMap(binaryMap _clonable) {
      clone(_clonable);
    }
    
    private void settings(int _w, int _h) {
      w = abs(_w); h = abs(_h);
      MAP = new boolean[w][h];
      reset();
    }
    
    public void setPixel(int _x, int _y, boolean _state) {
      MAP[_x][_y] = _state;
    }
    
    public boolean getPixel(int _x, int _y) {
      return MAP[_x][_y];
    }
    
    public void clone(binaryMap _clonable) {
      settings(_clonable.getW(), _clonable.getH());
      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          MAP[i][j] = _clonable.getPixel(i, j);
        }
      }
    }
    
    public void reset() {
      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          MAP[i][j] = false;
        }
      }
    }
    
    public boolean probe(Point p) {
      boolean returner;
      try {
        returner = MAP[p.x][p.y];
      } catch(ArrayIndexOutOfBoundsException e) {
        if (GLOBAL.VERBOSE) println("probe: point not in map");
        returner = false;
      }
      return returner;
    }
    
    public int getW() {
      return w;
    }
    
    public int getH() {
      return h;
    }
    
    public PImage binary2image() { // converts the 2bit map to RGB (24bit)
      /* PROCESSING: colors go from 0 to 254 and not 255 sometimes.
      */
      PImage returner = createImage(w, h, RGB);
      returner.loadPixels();
      for (int i = 0; i < returner.pixels.length; i++) {
        if (this.probe(new Point( i % w, int(i / w) ))) returner.pixels[i] = #FFFFFF;
        else returner.pixels[i] = #000000;
      }
      returner.updatePixels();
      return returner;
    }
  }
}

/*
TOPOGRAPHIC MAP!!!! EXTENSION TO screenRegion: topoMap
 returns float in given scale instead of true or false, -1 meaning it is out (if scale begins at 0)
 */

public class topoMap {
  private PImage TOPOMAP; // one colour for each level, in RGB: total = (256^3 - 1) levels (black is background)!
  /* don't build anything, just store. Use screenRegion methods to build, and this to compile a collection of
     screenRegion objects into one screenTopoRegion. The class will change the colours to map each screenRegion 
     to a unique topographic level. Limited to 256^3 - 1 levels... ~16 million levels. Enough? */
  private PImage DISPLAYMAP;
  private color [] colours;
  private int w, h, x, y, levelCount;
  private boolean remapColoursRequested;
  
  public topoMap(screenRegion [] _levels, int _w, int _h, int _x, int _y) { // custom size
    settings(_w, _h, _x, _y);
    addLevels(_levels);
  }
  
  public topoMap(screenRegion [] _levels) { // whole screen
    settings(screen.width, screen.height, 0, 0);
    addLevels(_levels);
  }
  
  public topoMap(screenRegion [] _levels, PopUp P) {
    settings(P.w, P.h, P.x, P.y);
    addLevels(_levels);
  }
  
  public topoMap(screenRegion [] _levels, plugApplet A) {
    Point p = A.getPosition();
    settings(A.width, A.height, p.x, p.y);
    addLevels(_levels);
  }
  
  public topoMap(screenRegion [] _levels, PApplet A, int _x, int _y) {
    settings(A.width, A.height, _x, _y);
    addLevels(_levels);
  }
  
  public topoMap(PImage _clonableMap, int _x, int _y, color [] _colours, int _levelCount) {
    int i, j;
    boolean found;
    
    settings(_clonableMap.width, _clonableMap.height, _x, _y);
    TOPOMAP.set(0, 0, _clonableMap);
    levelCount = _levelCount;
    DISPLAYMAP.set(0, 0, TOPOMAP);
    if (_colours != null) { // need to rebuild levels
      levelCount = _colours.length;
      TOPOMAP.loadPixels();
      for (i = 0; i < TOPOMAP.pixels.length; i++) {
        found = false;
        for (j = 0; j < _colours.length && !found; j++) {
          if (TOPOMAP.pixels[i] == _colours[j]) {
            TOPOMAP.pixels[i] = keepAlpha(j+1);
            found = true;
          }
        }
        if (!found) TOPOMAP.pixels[i] = #000000;
      }
    }
  }
  
  public topoMap(topoMap _clonable) {
    clone(_clonable);
  }
  
  public void settings(int _w, int _h, int _x, int _y) {
    remapColoursRequested = false;
    levelCount = 0;
    w = abs(_w); h = abs(_h); x = _x; y = _y;
    TOPOMAP = createImage(w, h, RGB);
    reset();
    colours = new color[1]; colours[0] = #000001;
    DISPLAYMAP = createImage(w, h, RGB);
  }
  
  public void reset() {
    TOPOMAP.loadPixels();
    for (int i = 0; i < TOPOMAP.pixels.length; i++) TOPOMAP.pixels[i] = keepAlpha(0);
    TOPOMAP.updatePixels();
  }
  
  public void setLevelDisplayColours(color [] _colours) {
    int i;
    
    color [] newcolours = new color [levelCount]; 
    // newcolours and not colours directly to avoid circular reference if calling this method from within addLevel
    for (i = 0; i < min(levelCount, _colours.length); i++) {
      newcolours[i] = _colours[i];
    }
    if (levelCount > _colours.length) {
      for (i = _colours.length; i < levelCount; i++) newcolours[i] = #FFFFFF;
      // dummy display colour because colour info is missing
    }
    colours = new color [levelCount];
    for (i = 0; i < levelCount; i++) colours[i] = newcolours[i];
  }
  
  public void setMapPositionAbs(int _x, int _y) {
    x = _x; y = _y;
  }
  
  public void setMapPositionRel(Container _C, int _x, int _y) {
    // set position relative to container (by explicit reference); if updated during runtime, follows container
    int _CID;
    _CID = GLOBAL.getScreenInfo.updateContainerPosition(_C);
    Point p;
    p = GLOBAL.getScreenInfo.getCContainerPosition(_CID);
    x = p.x + _x; y = p.y + _y;
  }
  
  public void setMapPositionRel(int _CID, int _x, int _y) {
    // set position relative to container (by ID, faster than above); if updated during runtime, follows container
    GLOBAL.getScreenInfo.updateContainerPosition(_CID);
    
    Point p;
    p = GLOBAL.getScreenInfo.getCContainerPosition(_CID);
    x = p.x + _x; y = p.y + _y;
  }
  
  public PImage getTopoMap() { // get TOPOMAP reference
    return TOPOMAP;
  }
  
  public PImage getDisplayMap() { // get DISPLAYMAP referente
    return DISPLAYMAP;
  }
  
  public void buildDisplayMap(color [] _colours) {
    recolour4display(_colours);
  }
  
  public void buildDisplayMap() {
    recolour4display(colours);
  }
  
  private void recolour4display(color [] _colours) {
    boolean found;
    
    if (_colours != null) {
      TOPOMAP.loadPixels();
      DISPLAYMAP.loadPixels();
      for (int i = 0; i < TOPOMAP.pixels.length; i++) {
        found = false;
        for (int j = 0; j < _colours.length && !found; j++) {
          if (TOPOMAP.pixels[i] == keepAlpha(j+1)) {
            DISPLAYMAP.pixels[i] = _colours[j];
          }
        }
        if (!found) DISPLAYMAP.pixels[i] = #000000;
      }
      DISPLAYMAP.updatePixels();
    }
  }
  
  public void addLevel(screenRegion _addLevel, int _levelID, int _overwrite, 
                       boolean _resize, int xoffset1, int yoffset1, int xoffset2, int yoffset2) { // add at index _levelID
    screenRegion addLevel = new screenRegion(_addLevel);
    int levelIDColour = keepAlpha(abs(_levelID) + 1);
    
    if (addLevel.getW() != w || _addLevel.getH() != h) {
      addLevel.fit(w, h, _resize, xoffset1, yoffset1, xoffset2, yoffset2);
      /* crop: addLevel bigger than TOPOMAP:  false -> crop addLevel to fit TOPOMAP and center addLevel to TOPOMAP
                                              true -> rezise addLevel to fit TOPOMAP
               addLevel smaller than TOPOMAP: false -> center addLevel to TOPOMAP
                                              true -> resize addLevel to fit TOPOMAP */
    }
    
    TOPOMAP.loadPixels();
    for (int i = 0; i < TOPOMAP.pixels.length; i++) {
      if (TOPOMAP.pixels[i] >= levelIDColour) { 
        // if pixel belongs to an upper level (equal is upper because it will be shifted)
        TOPOMAP.pixels[i] = keepAlpha(TOPOMAP.pixels[i] + 1); // shift it 1 level up
      }
      // will mess up things if defining more than the maximum number of levels! (256^3 - 1)
      if (addLevel.isPointInRegion(new Point(i % w, int(i / w)) )) {
        if (TOPOMAP.pixels[i] == #000000) TOPOMAP.pixels[i] = levelIDColour;
        else { // found point but it is not blank (#000000). Therefore, found intersection:
          switch(_overwrite) {
            case 0: // do not overwrite
              break;
            case 1: // overwrite only higher (there are no equals because equals have just been shifted)
              if (TOPOMAP.pixels[i] > levelIDColour) TOPOMAP.pixels[i] = levelIDColour;
              break;
            default: // overwrite only lower (there are no equals because equals have just been shifted)
              if (TOPOMAP.pixels[i] < levelIDColour) TOPOMAP.pixels[i] = levelIDColour;
              break;
          }
        }
      }
    }
    TOPOMAP.updatePixels();
    levelCount++;
    setLevelDisplayColours(colours); // assign dummy display colour to new level
    DISPLAYMAP.set(0, 0, TOPOMAP);
  }
  
  public void removeLevel(int _levelID) {
    int levelID = abs(_levelID); // avoid errors because of negative indexes
    int levelIDColour = keepAlpha(abs(_levelID) + 1);
    TOPOMAP.loadPixels();
    for (int i = 0; i < TOPOMAP.pixels.length; i++) {
      if (TOPOMAP.pixels[i] == levelIDColour) TOPOMAP.pixels[i] = #000000; 
      // remove level; this pixel won't enter next if-statement
      if (TOPOMAP.pixels[i] > levelIDColour) TOPOMAP.pixels[i] = keepAlpha(TOPOMAP.pixels[i] - 1); 
      // shift upper levels 1 down
    }
    TOPOMAP.updatePixels();
    levelCount--;
    setLevelDisplayColours(colours);
    DISPLAYMAP.set(0, 0, TOPOMAP); // reset DISPLAYMAP
  }
  
  public void removeLevels(int _levelID) {
    // _levelID < 0: from _levelID forwards
    // _levelID >= 0: from _levelID backwards
    int levelID = abs(_levelID);
    int i;
    if (_levelID < 0) {
      for (i = min(levelID, (levelCount-1)); i < levelCount; i++) {
        removeLevel(i);
      }
    } else {
      for (i = 0; i < min(levelID, (levelCount-1)); i++) {
        removeLevel(i);
      }
    }
  }
  
  public void addLevels(screenRegion [] _levels) {
    for (int i = 0; i < _levels.length; i++) {
      addLevel(_levels[i], i+1, -1, false, 0, 0, 0, 0);
    }
  }
  
  color keepAlpha(int _color) {
    int result, tmp;
  
    tmp = _color << 8; // trunk to avoid stack overflow or resetting of alpha bits
    // locks either on 0x00000000 (black) or 0xFFFFFFFF (white)
    result = (tmp >> 8) | 0xFF000000;
  
    return result;
  }
  
  int removeAlpha(color _color) {
    int result, tmp;
    
    result = _color ^ 0xFF000000;
    return result;
  }
  
  public Point abs2rel(Point _abs) {
    Point _rel;
    
    _rel = new Point(_abs.x - x, _abs.y - y);
    return _rel;
  }
  
  public Point rel2abs(Point _rel) {
    Point _abs;
    
    _abs = new Point(_rel.x + x, _rel.y + y);
    return _abs;
  }
  
  public int findLevelRel(Point p) {
    int returner;
    try {
      TOPOMAP.loadPixels();
      color testcolor = TOPOMAP.pixels[p.y*w + p.x];
      returner = (testcolor == #000000) ? ( 0 ) : ( removeAlpha(testcolor) );
    } catch(ArrayIndexOutOfBoundsException e) {
      if (GLOBAL.VERBOSE) println("topoMap: point (" + nfp(p.x, 4, 2) + "; " + nfp(p.y, 4, 2) + ") not in topoMap's boundaries!");
      returner = -1;
    }
    return returner;
  }
  
  public color findDisplayColourRel(Point p) {
    int levelID = findLevelRel(p);
    return findDisplayColourRel(levelID);
  }
  
  public color findDisplayColourRel(int _levelID) {
    color returner;
    try {
      returner = colours[_levelID];
    } catch(Exception e) {
      if (GLOBAL.VERBOSE) println("topoMap: point not in topoMap's boundaries or display colour not defined for level " + _levelID);
      returner = #FFFFFF;
    }
    return returner;
  }
  
  public void clone(topoMap _clonable) {
    settings(_clonable.getTopoMap().width, _clonable.getTopoMap().height, _clonable.x, _clonable.y);
    TOPOMAP.set(0, 0, _clonable.getTopoMap());
  }

}

public class screenInfo {
  Point PmousePos, mousePos;

  ArrayList PCInfo; // Previous absolute points for each container
  ArrayList CCInfo; // Current absolute points for each container

  public screenInfo(int _initX, int _initY) {
    PmousePos = new Point(_initX, _initY);
    mousePos = new Point(PmousePos);   
    PCInfo = new ArrayList(0);
    CCInfo = new ArrayList(0);
  }

  private class CInfo { // C stands for Container. Helper litle class
    private Container CID; // Container ID; reference to a container
    private Point absCPos; // current container position
    private Point absMousePos; // current abs mouse position when container position was probed

    private CInfo(Container C) {
      try {
        CID = C;
        absCPos = CID.getLocationOnScreen();
      } catch (IllegalComponentStateException e) {
        if (GLOBAL.VERBOSE) println("CINFO: container not drawn!");
      }
      PointerInfo pi = MouseInfo.getPointerInfo();
      absMousePos = pi.getLocation();
    }

    private CInfo(Container C, Point _absCPos, Point _absMousePos) { // update points
      CID = C; 
      absCPos = _absCPos; 
      absMousePos = _absMousePos;
    }

    public Point getAbsCPos() {
      return absCPos;
    }

    public Point getAssocAbsMousePos() {
      return absMousePos;
    }

    public Container getContainer() {
      return CID;
    }
  }

  public Point curabsMousePos() {
    PmousePos.x = mousePos.x; 
    PmousePos.y = mousePos.y;

    PointerInfo pi = MouseInfo.getPointerInfo();
    mousePos = pi.getLocation();

    return mousePos;
  }

  public Point prvabsMousePos() {
    return PmousePos;
  }

  public Point updateContainerPosition(int _ID) {
    Point returner = new Point(-screen.width, -screen.height);
    if (CCInfo.size() > _ID) {
      CInfo TMPprev = (CInfo) CCInfo.get(_ID);
      PCInfo.set(_ID, new CInfo( TMPprev.getContainer(), TMPprev.getAbsCPos(), TMPprev.getAssocAbsMousePos() ) );
      // save previous position
      CInfo TMPcur = new CInfo(TMPprev.getContainer());
      CCInfo.set(_ID, TMPcur ); // update current position
      returner = TMPcur.getAbsCPos();
    }
    return returner;
  }
  
  public int addContainerToList(Container C) {
    CCInfo.add( new CInfo(C) );
    PCInfo.add( new CInfo(C) );
    return (CCInfo.size()-1);
  }

  public int updateContainerPosition(Container C) {
    Point p;
    int i;
    boolean found;

    found = false;
    for (i = 0; i < CCInfo.size() && !found; i++) { // search in current-positions list
      CInfo TMPC = (CInfo) CCInfo.get(i);
      if (TMPC.getContainer().equals(C)) { // container already probed: save previous points and probe current
        found = true;
        PCInfo.set(i, new CInfo( C, TMPC.getAbsCPos(), TMPC.getAssocAbsMousePos() ) ); // save previous position
        CCInfo.set(i, new CInfo(C) ); // update current position
        break; // avoid increment of i that would require correction based on found
      }
    }
    return ((found) ? (i) : (-1)); // returns ID of container updated. IF NOT FOUND, RETURNS -1. WON'T ADD AUTOMATICALLY, CONCURRENCY PROBLEMS
  }

  // get container's previous position:
  public Point getPContainerPosition(int _ID) { // from container's ID
    Point returner = new Point(-screen.width, -screen.height);
    if (PCInfo.size() > _ID) {
      CInfo TMP = (CInfo) PCInfo.get(_ID);
      returner = TMP.getAbsCPos();
    }
    return returner;
  }
  public Point getPContainerPosition(Container C) { // from container's reference
    // searches the arraylist; slower than to get by ID
    for (int i = 0; i < PCInfo.size(); i++) {
      CInfo TMP = (CInfo) PCInfo.get(i);
      if (TMP.getContainer().equals(C)) {
        return TMP.getAbsCPos();
      }
    }
    return ( new Point(-screen.width, -screen.height) );
  }
  // get container's current position:
  public Point getCContainerPosition(int _ID) { // from container's ID
    Point returner = new Point();
    
    try {
      if (PCInfo.size() > _ID) {
        CInfo TMP = (CInfo) CCInfo.get(_ID);
        returner = TMP.getAbsCPos();
      } 
      else {
        returner.x = -screen.width; 
        returner.y = -screen.height;
      }
    } catch (Exception e) {
    }
    
    return returner;
  }
  public Point getCContainerPosition(Container C) { // from container's reference
    // searches the arraylist; slower than to get by ID
    for (int i = 0; i < PCInfo.size(); i++) {
      CInfo TMP = (CInfo) PCInfo.get(i);
      if (TMP.getContainer().equals(C)) {
        return TMP.getAbsCPos();
      }
    }
    return ( new Point(-screen.width, -screen.height) );
  }

  public Point coords_abs2rel(Container C, int mode, Point p) {
    // transforms absolute coords in coords relative to Container
    // mode = 1 -> top-left corner of Container
    // mode = 2 -> center of Container
    Point COrigin, returner = null;
    Dimension CSize;

    try {
      COrigin = C.getLocationOnScreen();
      CSize = C.getSize();
    } 
    catch (IllegalComponentStateException e) {
      if (GLOBAL.VERBOSE) println(millis() + ": coords_abs2rel: Container not drawn in screen");
      return null;
    }

    switch(mode) {
    case 1:
      returner = new Point(p.x - COrigin.x, p.y - COrigin.y); 
      break;
    case 2:
      returner = new Point(p.x - (COrigin.x + CSize.width/2), p.y - (COrigin.y + CSize.height/2));
      break;
    }
    return returner;
  }

  public Point coords_rel2abs(Container C, int mode, Point p) {
    // transforms coords relative to Container in absolute coords
    // mode = 1 -> top-left corner of Container
    // mode = 2 -> center of Container
    Point COrigin, returner = null;
    Dimension CSize;

    try {
      COrigin = C.getLocationOnScreen();
      CSize = C.getSize();
    } 
    catch (IllegalComponentStateException e) {
      if (GLOBAL.VERBOSE) println(this + ": coords_rel2abs: Container not drawn in screen");
      return null;
    }

    switch(mode) {
    case 1:
      returner = new Point(p.x + COrigin.x, p.y + COrigin.y);
      break;
    case 2:
      returner = new Point(p.x + COrigin.x + CSize.width/2, p.y + COrigin.y + CSize.height/2);
      break;
    }
    return returner;
  }
}

public class keyboardStateStorage {
  private boolean [] KEYS;

  public keyboardStateStorage() {
    KEYS = new boolean[185];
  }

  public int getKeyID(int _keyCode) {
    // searches KEYS[] for given keyCode and returns the index assigned to that keyCode
    switch (_keyCode) {
    case KeyEvent.VK_0: 
      return 0 ;
    case KeyEvent.VK_1: 
      return 1 ;
    case KeyEvent.VK_2: 
      return 2 ;
    case KeyEvent.VK_3: 
      return 3 ;
    case KeyEvent.VK_4: 
      return 4 ;
    case KeyEvent.VK_5: 
      return 5 ;
    case KeyEvent.VK_6: 
      return 6 ;
    case KeyEvent.VK_7: 
      return 7 ;
    case KeyEvent.VK_8: 
      return 8 ;
    case KeyEvent.VK_9: 
      return 9 ;
    case KeyEvent.VK_A: 
      return 10 ;
    case KeyEvent.VK_ACCEPT: 
      return 11 ;
    case KeyEvent.VK_ADD: 
      return 12 ;
    case KeyEvent.VK_AGAIN: 
      return 13 ;
    case KeyEvent.VK_ALL_CANDIDATES: 
      return 14 ;
    case KeyEvent.VK_ALPHANUMERIC: 
      return 15 ;
    case KeyEvent.VK_ALT: 
      return 16 ;
    case KeyEvent.VK_ALT_GRAPH: 
      return 17 ;
    case KeyEvent.VK_AMPERSAND: 
      return 18 ;
    case KeyEvent.VK_ASTERISK: 
      return 19 ;
    case KeyEvent.VK_AT: 
      return 20 ;
    case KeyEvent.VK_B: 
      return 21 ;
    case KeyEvent.VK_BACK_QUOTE: 
      return 22 ;
    case KeyEvent.VK_BACK_SLASH: 
      return 23 ;
    case KeyEvent.VK_BACK_SPACE: 
      return 24 ;
    case KeyEvent.VK_BRACELEFT: 
      return 25 ;
    case KeyEvent.VK_BRACERIGHT: 
      return 26 ;
    case KeyEvent.VK_C: 
      return 27 ;
    case KeyEvent.VK_CANCEL: 
      return 28 ;
    case KeyEvent.VK_CAPS_LOCK: 
      return 29 ;
    case KeyEvent.VK_CIRCUMFLEX: 
      return 30 ;
    case KeyEvent.VK_CLEAR: 
      return 31 ;
    case KeyEvent.VK_CLOSE_BRACKET: 
      return 32 ;
    case KeyEvent.VK_CODE_INPUT: 
      return 33 ;
    case KeyEvent.VK_COLON: 
      return 34 ;
    case KeyEvent.VK_COMMA: 
      return 35 ;
    case KeyEvent.VK_COMPOSE: 
      return 36 ;
    case KeyEvent.VK_CONTROL: 
      return 37 ;
    case KeyEvent.VK_CONVERT: 
      return 38 ;
    case KeyEvent.VK_COPY: 
      return 39 ;
    case KeyEvent.VK_CUT: 
      return 40 ;
    case KeyEvent.VK_D: 
      return 41 ;
    case KeyEvent.VK_DEAD_ABOVEDOT: 
      return 42 ;
    case KeyEvent.VK_DEAD_ABOVERING: 
      return 43 ;
    case KeyEvent.VK_DEAD_ACUTE: 
      return 44 ;
    case KeyEvent.VK_DEAD_BREVE: 
      return 45 ;
    case KeyEvent.VK_DEAD_CARON: 
      return 46 ;
    case KeyEvent.VK_DEAD_CEDILLA: 
      return 47 ;
    case KeyEvent.VK_DEAD_CIRCUMFLEX: 
      return 48 ;
    case KeyEvent.VK_DEAD_DIAERESIS: 
      return 49 ;
    case KeyEvent.VK_DEAD_DOUBLEACUTE: 
      return 50 ;
    case KeyEvent.VK_DEAD_GRAVE: 
      return 51 ;
    case KeyEvent.VK_DEAD_IOTA: 
      return 52 ;
    case KeyEvent.VK_DEAD_MACRON: 
      return 53 ;
    case KeyEvent.VK_DEAD_OGONEK: 
      return 54 ;
    case KeyEvent.VK_DEAD_SEMIVOICED_SOUND: 
      return 55 ;
    case KeyEvent.VK_DEAD_TILDE: 
      return 56 ;
    case KeyEvent.VK_DEAD_VOICED_SOUND: 
      return 57 ;
    case KeyEvent.VK_DECIMAL: 
      return 58 ;
    case KeyEvent.VK_DELETE: 
      return 59 ;
    case KeyEvent.VK_DIVIDE: 
      return 60 ;
    case KeyEvent.VK_DOLLAR: 
      return 61 ;
    case KeyEvent.VK_DOWN: 
      return 62 ;
    case KeyEvent.VK_E: 
      return 63 ;
    case KeyEvent.VK_END: 
      return 64 ;
    case KeyEvent.VK_ENTER: 
      return 65 ;
    case KeyEvent.VK_EQUALS: 
      return 66 ;
    case KeyEvent.VK_ESCAPE: 
      return 67 ;
    case KeyEvent.VK_EURO_SIGN: 
      return 68 ;
    case KeyEvent.VK_EXCLAMATION_MARK: 
      return 69 ;
    case KeyEvent.VK_F: 
      return 70 ;
    case KeyEvent.VK_F1: 
      return 71 ;
    case KeyEvent.VK_F10: 
      return 72 ;
    case KeyEvent.VK_F11: 
      return 73 ;
    case KeyEvent.VK_F12: 
      return 74 ;
    case KeyEvent.VK_F13: 
      return 75 ;
    case KeyEvent.VK_F14: 
      return 76 ;
    case KeyEvent.VK_F15: 
      return 77 ;
    case KeyEvent.VK_F16: 
      return 78 ;
    case KeyEvent.VK_F17: 
      return 79 ;
    case KeyEvent.VK_F18: 
      return 80 ;
    case KeyEvent.VK_F19: 
      return 81 ;
    case KeyEvent.VK_F2: 
      return 82 ;
    case KeyEvent.VK_F20: 
      return 83 ;
    case KeyEvent.VK_F21: 
      return 84 ;
    case KeyEvent.VK_F22: 
      return 85 ;
    case KeyEvent.VK_F23: 
      return 86 ;
    case KeyEvent.VK_F24: 
      return 87 ;
    case KeyEvent.VK_F3: 
      return 88 ;
    case KeyEvent.VK_F4: 
      return 89 ;
    case KeyEvent.VK_F5: 
      return 90 ;
    case KeyEvent.VK_F6: 
      return 91 ;
    case KeyEvent.VK_F7: 
      return 92 ;
    case KeyEvent.VK_F8: 
      return 93 ;
    case KeyEvent.VK_F9: 
      return 94 ;
    case KeyEvent.VK_FINAL: 
      return 95 ;
    case KeyEvent.VK_FIND: 
      return 96 ;
    case KeyEvent.VK_FULL_WIDTH: 
      return 97 ;
    case KeyEvent.VK_G: 
      return 98 ;
    case KeyEvent.VK_GREATER: 
      return 99 ;
    case KeyEvent.VK_H: 
      return 100 ;
    case KeyEvent.VK_HALF_WIDTH: 
      return 101 ;
    case KeyEvent.VK_HELP: 
      return 102 ;
    case KeyEvent.VK_HIRAGANA: 
      return 103 ;
    case KeyEvent.VK_HOME: 
      return 104 ;
    case KeyEvent.VK_I: 
      return 105 ;
    case KeyEvent.VK_INPUT_METHOD_ON_OFF: 
      return 106 ;
    case KeyEvent.VK_INSERT: 
      return 107 ;
    case KeyEvent.VK_INVERTED_EXCLAMATION_MARK: 
      return 108 ;
    case KeyEvent.VK_J: 
      return 109 ;
    case KeyEvent.VK_JAPANESE_HIRAGANA: 
      return 110 ;
    case KeyEvent.VK_JAPANESE_KATAKANA: 
      return 111 ;
    case KeyEvent.VK_JAPANESE_ROMAN: 
      return 112 ;
    case KeyEvent.VK_K: 
      return 113 ;
    case KeyEvent.VK_KANA: 
      return 114 ;
    case KeyEvent.VK_KANA_LOCK: 
      return 115 ;
    case KeyEvent.VK_KANJI: 
      return 116 ;
    case KeyEvent.VK_KATAKANA: 
      return 117 ;
    case KeyEvent.VK_KP_DOWN: 
      return 118 ;
    case KeyEvent.VK_KP_LEFT: 
      return 119 ;
    case KeyEvent.VK_KP_RIGHT: 
      return 120 ;
    case KeyEvent.VK_KP_UP: 
      return 121 ;
    case KeyEvent.VK_L: 
      return 122 ;
    case KeyEvent.VK_LEFT: 
      return 123 ;
    case KeyEvent.VK_LEFT_PARENTHESIS: 
      return 124 ;
    case KeyEvent.VK_LESS: 
      return 125 ;
    case KeyEvent.VK_M: 
      return 126 ;
    case KeyEvent.VK_META: 
      return 127 ;
    case KeyEvent.VK_MINUS: 
      return 128 ;
    case KeyEvent.VK_MODECHANGE: 
      return 129 ;
    case KeyEvent.VK_MULTIPLY: 
      return 130 ;
    case KeyEvent.VK_N: 
      return 131 ;
    case KeyEvent.VK_NONCONVERT: 
      return 132 ;
    case KeyEvent.VK_NUM_LOCK: 
      return 133 ;
    case KeyEvent.VK_NUMBER_SIGN: 
      return 134 ;
    case KeyEvent.VK_NUMPAD0: 
      return 135 ;
    case KeyEvent.VK_NUMPAD1: 
      return 136 ;
    case KeyEvent.VK_NUMPAD2: 
      return 137 ;
    case KeyEvent.VK_NUMPAD3: 
      return 138 ;
    case KeyEvent.VK_NUMPAD4: 
      return 139 ;
    case KeyEvent.VK_NUMPAD5: 
      return 140 ;
    case KeyEvent.VK_NUMPAD6: 
      return 141 ;
    case KeyEvent.VK_NUMPAD7: 
      return 142 ;
    case KeyEvent.VK_NUMPAD8: 
      return 143 ;
    case KeyEvent.VK_NUMPAD9: 
      return 144 ;
    case KeyEvent.VK_O: 
      return 145 ;
    case KeyEvent.VK_OPEN_BRACKET: 
      return 146 ;
    case KeyEvent.VK_P: 
      return 147 ;
    case KeyEvent.VK_PAGE_DOWN: 
      return 148 ;
    case KeyEvent.VK_PAGE_UP: 
      return 149 ;
    case KeyEvent.VK_PASTE: 
      return 150 ;
    case KeyEvent.VK_PAUSE: 
      return 151 ;
    case KeyEvent.VK_PERIOD: 
      return 152 ;
    case KeyEvent.VK_PLUS: 
      return 153 ;
    case KeyEvent.VK_PREVIOUS_CANDIDATE: 
      return 154 ;
    case KeyEvent.VK_PRINTSCREEN: 
      return 155 ;
    case KeyEvent.VK_PROPS: 
      return 156 ;
    case KeyEvent.VK_Q: 
      return 157 ;
    case KeyEvent.VK_QUOTE: 
      return 158 ;
    case KeyEvent.VK_QUOTEDBL: 
      return 159 ;
    case KeyEvent.VK_R: 
      return 160 ;
    case KeyEvent.VK_RIGHT: 
      return 161 ;
    case KeyEvent.VK_RIGHT_PARENTHESIS: 
      return 162 ;
    case KeyEvent.VK_ROMAN_CHARACTERS: 
      return 163 ;
    case KeyEvent.VK_S: 
      return 164 ;
    case KeyEvent.VK_SCROLL_LOCK: 
      return 165 ;
    case KeyEvent.VK_SEMICOLON: 
      return 166 ;
    case KeyEvent.VK_SEPARATOR: 
      return 167 ;
    case KeyEvent.VK_SHIFT: 
      return 168 ;
    case KeyEvent.VK_SLASH: 
      return 169 ;
    case KeyEvent.VK_SPACE: 
      return 170 ;
    case KeyEvent.VK_STOP: 
      return 171 ;
    case KeyEvent.VK_SUBTRACT: 
      return 172 ;
    case KeyEvent.VK_T: 
      return 173 ;
    case KeyEvent.VK_TAB: 
      return 174 ;
    case KeyEvent.VK_U: 
      return 175 ;
    case KeyEvent.VK_UNDEFINED: 
      return 176 ;
    case KeyEvent.VK_UNDERSCORE: 
      return 177 ;
    case KeyEvent.VK_UNDO: 
      return 178 ;
    case KeyEvent.VK_UP: 
      return 179 ;
    case KeyEvent.VK_V: 
      return 180 ;
    case KeyEvent.VK_W: 
      return 181 ;
    case KeyEvent.VK_X: 
      return 182 ;
    case KeyEvent.VK_Y: 
      return 183 ;
    case KeyEvent.VK_Z: 
      return 184 ;
    }
    return -1;
  }

  /* below: ID means that the method works with an index in KEYS[] array.
   KC means that the method works with the key code that the desired
   entry in the KEYS[] array is assigned to.
   */
  public boolean ID_probeKey(int _keyID) {
    boolean returner;
    try {
      returner = KEYS[_keyID];
    } 
    catch (ArrayIndexOutOfBoundsException e) {
      if (GLOBAL.VERBOSE) println("ID_probeKey(" + _keyID + "): No such keyID!");
      returner = false;
    }
    return returner;
  }

  public void ID_setKey(int _keyID, boolean _state) {
    try {
      KEYS[_keyID] = _state;
    } 
    catch (ArrayIndexOutOfBoundsException e) {
      if (GLOBAL.VERBOSE) println("ID_setKey(" + _keyID + "): No such keyID!");
    }
  }

  public boolean KC_probeKey(int _keyCode) {
    int keyID = this.getKeyID(_keyCode);
    if (keyID != -1) return KEYS[keyID];
    else {
      if (GLOBAL.VERBOSE) println("KC_probeKey(" + _keyCode + "): No such keyCode!");
      return false;
    }
  }

  public void KC_setKey(int _keyCode, boolean _state) {
    int keyID = this.getKeyID(_keyCode);
    if (keyID != -1) KEYS[keyID] = _state;
    else if (GLOBAL.VERBOSE) println("KC_setKey(" + _keyCode + "): No such keyCode!");
  }
}

public class mouseWheelInfo {
  private boolean enableMouseWheel;
  private int mwRotation;
  private int mwDisplacement;
  PApplet parent;
  
  public mouseWheelInfo(PApplet _parent) {
    parent = _parent;
    enableMouseWheel = true;
    mwDisplacement = 0;
    
    parent.addMouseWheelListener(new MouseAdapter() {
      public void mouseWheelMoved(MouseWheelEvent _e) {
        mwaction(_e);
      }
    });
    
  }
  
  public void mwaction(MouseWheelEvent _e) {
    if (enableMouseWheel) {
      mwRotation = _e.getWheelRotation();
      mwDisplacement += mwRotation;
    }
  }
  
  public void setActive(boolean _enableMouseWheel) {
    enableMouseWheel = _enableMouseWheel;
  }
  
  public int getRotation() {
    return mwRotation;
  }
  
  public int getDisplacement() { 
    /* each object compares its own saved displacement state against the current, probed here;
       when they differ, it means mousewheel has moved and object needs to update itself based on
       the delta displacement.
       When object loses focus, its probing&checking listener deactivates. When it regains focus,
       its own saved displacement is reset to current BEFORE the reactivation of the listener, or
       object might update itself unnecessarily and not properly (if mousewheel has moved while
       it was out of focus, when it regains focus it cannot be updated suddenly by that delta)
    */
    return mwDisplacement;
  }
  
  public boolean getActive() {
    return enableMouseWheel;
  }
}

// helper thread to auto-reloop main sketch!

public class selfExec extends Thread {
  ArrayList [] timers;
  Timer quartz;
  PApplet watched;
  
  public selfExec(PApplet _watched) {
    watched = _watched;
    timers = new ArrayList[3];
    timers[0] = new ArrayList(0); // noloop
    timers[1] = new ArrayList(0); // loop
    timers[2] = new ArrayList(0); // redraw
    quartz = new Timer(0);
    this.start();
  } // as millis() returns an int that keeps getting incremented, 
  // it will eventually overflow if the sketch keeps running long enough;
  // but that "long enough" is 25 days... so, it's ok
  // http://processing.org/discourse/yabb2/YaBB.pl?num=1236004453
  
  public void addMark(int _mark, int _type) {
    if (_type > 2 || _type < 0) _type = 0;
    timers[_type].add(_mark);
  }
  
  public void removeMark(int _mark, int _type) {
    if (_type > 2 || _type < 0) _type = 0;
    for (int i = 0; i < timers[_type].size(); i++) {
      if ( ((Integer)timers[_type].get(i)).intValue() == _mark ) {
        timers[_type].remove(i);
      } 
    }
  }
  
  public void run() {
    int i, j;
    boolean found;
    
    while(true) {
      try {
        Thread.sleep(200);
      } catch (Exception e) {}
      for (i = 0; i < 3; i++) {
        found = false;
        for (j = 0; j < timers[i].size() && !found; j++) {
          if (quartz.elapsed() >= ((Integer)timers[i].get(j)).intValue()) {
            switch(i) {
              case 0: watched.noLoop(); break;
              case 1: watched.loop(); break;
              case 2: watched.redraw(); break;
            }
            found = true;
          }
        }
      }
    }
  }
}
