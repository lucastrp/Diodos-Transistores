/**
## UIOBJECT ##
**/

public abstract class UIObject {
  PApplet parent;
  PGraphics layer;
  float x, y, w, h, margin;
  float xoff, yoff; // offsets to correct clicking position due 
                    // to layer origin not being equal to container origin
  int mx, my; // mouse position in parent
  boolean focusable, focus;
  
  public UIObject(PApplet _parent, PGraphics _layer) {
    parent = _parent; layer = _layer; margin = 0;
    xoff = yoff = 0;
    
    parent.registerMouseEvent(this);
  }
  
  public void forceFocus(boolean _focus) {
    focus = _focus;
  }
  
  public void forceFocusable(boolean _focusable) {
    focusable = _focusable;
  }
  
  public void setBoundingBox(float _x, float _y, float _w, float _h) { // center point + dimensions
    setPos(_x, _y); w = _w; h = _h;
  }
  
  public void setPos(float _x, float _y) {
    x = _x; y = _y;
  }
  
  public void setOff(float _xoff, float _yoff) {
    xoff = _xoff; yoff = _yoff;
  }
  
  public void setParent(PApplet _parent) {
    parent = _parent;
  }
  
  public void setLayer(PGraphics _layer) {
    layer = _layer;
  }
  
  public float [] getOffs() {
    float [] returner = new float[2];
    returner[0] = xoff; returner[1] = yoff;
    return returner;
  }
  
  public float [] getBoundingBox(boolean _withMargin) { // top-left point + bottom-right point
    float [] returner = new float[4];
    int flag = (_withMargin) ? (1) : (0) ;
    returner[0] = x - w*0.5 - flag*margin; returner[1] = y - h*0.5 - flag*margin;
    returner[2] = x + w*0.5 + flag*margin; returner[3] = y + h*0.5 + flag*margin;
    return returner;
  }
  
  public void mouseEvent(MouseEvent evt) {
    mx = evt.getX();
    my = evt.getY();
    
    switch(evt.getID()) {
      case MouseEvent.MOUSE_PRESSED:
        mPressed();
        break;
      case MouseEvent.MOUSE_RELEASED:
        mReleased();
        break;
      case MouseEvent.MOUSE_DRAGGED:
        mDragged();
        break;
    }
  }
  
  public abstract void display();
  public abstract void mPressed();
  public abstract void mReleased();
  public abstract void mDragged();
}

/**
## PROTOTYPE ##
**/

/*
public class PROTOTYPE extends UIObject {
  
  public PROTOTYPE(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h) {
    super(_parent, _layer);
    setBoundingBox(_x, _y, _w, _h);
  }
  
  public void display() {}
  
  public void mPressed() {}
  public void mReleased() {}
  public void mDragged() {}
}
*/

/**
## IMAGEBOX ##

Provides backgrounds for other objects (such as labels) and bases for other objects
(can load up a PNG with transparency, for instance, and make a button in any format)
**/

public class ImageBox extends UIObject {
  PImage BG, BGbckp;
  color bg, border;
  int imgMode;
  boolean noBG; // true if transparent bg
  float xdef, ydef, wdef, hdef;
  PGraphics clonable; boolean copylayer; // for copylayer
  
  int bsize, angle, balpha, bordermode; float shadePercent, lightPercent; // aux for bevel border
  
  public ImageBox(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h) {
    // empty; dummy
    super(_parent, _layer);
    margin = imgMode = border = 0;
    xdef = x; ydef = y; wdef = w; hdef = h;
    setBoundingBox(_x, _y, _w - 2*margin, _h - 2*margin);
    noBG = true; copylayer = false;
  }
  
  public ImageBox(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, PGraphics _clonable) {
    // clone piece of _clonable (former buildtrickery)
    super(_parent, _layer);
    imgMode = 1;
    margin = border = 0;
    setBoundingBox(_x, _y, _w - 2*margin, _h - 2*margin);
    noBG = true;
    PGraphics TMP = (_clonable == null) ? (layer) : (_clonable) ;
    copylayer = true;
    copyLayer();
  }
  
  public ImageBox(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, color _bg, color _border, int _bstroke) {
    // flat colour
    super(_parent, _layer);
    bg = _bg; border = _border; margin = _bstroke;
    setBoundingBox(_x, _y, _w - 2*margin, _h - 2*margin);
    imgMode = 0; copylayer = false;
    noBG = (alpha(bg) != 0) ? (false) : (true) ;
  }
  
  public ImageBox(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, 
                  color _bg, PImage _BG, int _imgMode, color _border, int _bstroke) {
    // image
    super(_parent, _layer);
    border = _border; margin = _bstroke;
    setBoundingBox(_x, _y, _w - 2*margin, _h - 2*margin);
    imgMode = _imgMode; bg = _bg; copylayer = false;
    setBG(_BG);
    noBG = (alpha(bg) != 0) ? (false) : (true) ;
  }
  
  public void setBG(PImage _BG) { // configures image as background (with previously set imgMode)
    if (imgMode == 0) return; // zero is no image
    int offX, offY, minW, minH;
    BGbckp = _BG;
    offX = int((_BG.width - w)*0.5); offY = int((_BG.height - h)*0.5);
    if (offX < 0) offX = 0; // deactivate offset if not needed
    if (offY < 0) offY = 0;
    minW = min(int(w), _BG.width);
    minH = min(int(h), _BG.height);
    switch(imgMode) {
      case 1: //mantain aspect ratio, centered in bounding box, no scaling. Will cut if image is bigger.
        BG = createImage(minW, minH, _BG.format);
        BG.copy(_BG, offX, offY, minW, minH, 0, 0, minW, minH);
        break;
      case 2: //mantain aspect ratio, centered in bounding box, scaled to fit
        float multiplier = min(w/float(_BG.width), h/float(_BG.height));
        minW = int(multiplier*_BG.width); minH = int(multiplier*_BG.height);
        BG = createImage(minW, minH, _BG.format);
        BG.copy(_BG, 0, 0, _BG.width, _BG.height, 0, 0, minW, minH);
        break;
      case 3: //do not mantain aspect ratio, scale to fit
        BG = createImage(int(w), int(h), _BG.format);
        BG.copy(_BG, 0, 0, _BG.width, _BG.height, 0, 0, int(w), int(h));
        break;
      case 4: //special mode for bevel border (solid colour bg)
        BG = buildRectFrame(bg, border, bsize, shadePercent, lightPercent, angle, balpha, bordermode);
        break;
      case 5: //special mode for bevel border (image bg)
        if (copylayer) copyLayer();
        BG = buildRectFrame(BG, bsize, shadePercent, lightPercent, angle, balpha, bordermode);
        break;
      case 6: //special mode for copylayer
        if (copylayer) copyLayer();
        break;
    }
  }
  
  public void updateBG() { // resets BG without changing imgMode settings
    setBG(BGbckp);
  }
  
  public void redefineDefaults(float _xdef, float _ydef, float _wdef, float _hdef) {
    xdef = _xdef; ydef = _ydef; wdef = _wdef; hdef = _hdef;
  }
  
  public void restoreDefaults() {
    setBoundingBox(xdef, ydef, wdef, hdef);
  }
  
  public void setBG(boolean _noBG, color _bg) { // configures solid background
    noBG = _noBG; bg = _bg; imgMode = 0;
  }
  
  public void setImgMode(int _imgMode) {
    imgMode = _imgMode;
  }
  
  public void setClonableLayer(PGraphics _clonable, boolean _copylayer, boolean _hasBorder) {
    clonable = _clonable;
    copylayer = _copylayer;
    if (copylayer) {
      imgMode = (_hasBorder) ? (5) : (6);
      copyLayer();
    }
  }
  
  public void copyLayer() { // copies specified layer in imagebox's area. 
    //NEED TO BE CALLED EVERYTIME BACKGROUND UNDERNEATH CHANGES TO PROPERLY UPDATE
    BGbckp = BG;
    int _w, _h; _w = int(w + 2*bsize); _h = int(h + 2*bsize);
    BG = createImage(_w, _h, RGB);
    int offXo = int(x - _w/2), offYo = int(y - _h/2); int offXt = 0, offYt = 0;
    if (offXo < 0) { offXt = -offXo; offXo = 0; } if (offYo < 0) { offYt = -offYo; offYo = 0; }
    BG.copy(clonable, offXo, offYo, _w, _h, offXt, offYt, min(_w, clonable.width), min(_h, clonable.height));
    //println("cplayer " + _w + " // " + _h + " // " + offXt + " // " + offYt + " // " + offXo + " // " + offYo);
  }
  
  public void enclose(UIObject [] objects, float _margin) { // encloses bounding boxes of objects in array with a 
  //single imageBox working as a big border frame for the objects. With previously set bstroke
    int i, j;
    float [] bbb = new float[4]; // big bounding box
    float [] TMP = new float[4];
    TMP = objects[0].getBoundingBox(true);
    bbb = objects[0].getBoundingBox(true);
    
    for(i = 1; i < objects.length; i++) {
      TMP = objects[i].getBoundingBox(true);
      for(j = 0; j < 2; j++) {
        bbb[j] = min(bbb[j], TMP[j]);
      }
      for(j = 2; j < 4; j++) {
        bbb[j] = max(bbb[j], TMP[j]);
      }
    }
    
    float _x, _y, _w, _h;
    _w = bbb[2] - bbb[0] + 2*(_margin + margin);
    _h = bbb[3] - bbb[1] + 2*(_margin + margin);
    _x = (bbb[0] + bbb[2])*0.5;
    _y = (bbb[1] + bbb[3])*0.5;
    
    setBoundingBox(_x, _y, _w, _h);
  }
  
  private PImage buildRectFrame(color _bg, color _border, int _bsize, float _shadePercent, float _lightPercent, int _angle, int _balpha, int _bordermode) {
    // build PImage to throw at the other function, so everything is more general
    int _w, _h; _w = int(w) + 2*bsize; _h = int(h) + 2*bsize;
    PImage img = createImage(_w, _h, ARGB);
    img.loadPixels();
    for (int i = 0; i < img.pixels.length; i++) {
      if (i > _w*_bsize && i < img.pixels.length - _w*_bsize &&
          i % _w >= _bsize && i % _w < _w - _bsize) {
        img.pixels[i] = _bg;
      } else img.pixels[i] = _border;
    } 
    img.updatePixels();
    img = buildRectFrame(img, _bsize, _shadePercent, _lightPercent, _angle, _balpha, _bordermode);
    return img;
  }
  
  private PImage buildRectFrame(PImage _bg, int _bsize, float _shadePercent, float _lightPercent, int _angle, int _balpha, int _bordermode) {
    /* angle:
    -2: top-right -> bottom-left
    -1: top-left -> bottom-right
     0: centre
    +1: bottom-right -> top-left
    +2: bottom-left -> top-right
    */
    int _w, _h; _w = int(w) + 2*bsize; _h = int(h) + 2*bsize;
    PImage img = createImage(_w, _h, ARGB);
    if (_bg.width != _w || _bg.height != _h) {
      if (GLOBAL.VERBOSE) println(this + ".buildRectFrame: images not of the same size; resizing");
      _bg.resize(_w, _h);
    }
    img.loadPixels(); _bg.loadPixels();
    
    color [] endColour = new color[4]; color cur_endColour; 
    float [] endColourpct = new float[4]; float cur_endColourpct;
    int bordermode = 4; //default
    bordermode = _bordermode;
    switch(_angle) {
      case -2: //top-right -> bottom-left
        endColour[3] = endColour[2] = (_balpha == 0) ? (color(1, 0, 0, _balpha)) : (color(0, 0, 0, _balpha)) ; // processing bug
        endColourpct[3] = endColourpct[2] = _shadePercent;
        endColour[1] = endColour[0] = color(255, 255, 255, _balpha);
        endColourpct[1] = endColourpct[0] = _lightPercent;
        break;
      case -1: //top-left -> bottom-right
        endColour[2] = endColour[1] = (_balpha == 0) ? (color(1, 0, 0, _balpha)) : (color(0, 0, 0, _balpha)) ; // processing bug
        endColourpct[2] = endColourpct[1] = _shadePercent;
        endColour[3] = endColour[0] = color(255, 255, 255, _balpha);
        endColourpct[3] = endColourpct[0] = _lightPercent;
        break;
      case 0: //centred
        if (_bordermode > 0) { // bordermode sign flags if sinked or protuberant. Protuberant
          endColour[0] = (_balpha == 0) ? (color(1, 0, 0, _balpha)) : (color(0, 0, 0, _balpha)) ; // processing bug
          endColourpct[0] = _shadePercent;
          bordermode = _bordermode;
        } else { // sinked
          endColour[0] = color(255, 255, 255, _balpha);
          endColourpct[0] = _lightPercent;
          bordermode = -_bordermode;
        }
        for (int i = 3; i > 0; i--) {
          endColour[i] = endColour[0];
          endColourpct[i] = endColourpct[0];
        }
        break;
      case 1: //bottom-right -> top-left
        endColour[3] = endColour[0] = (_balpha == 0) ? (color(1, 0, 0, _balpha)) : (color(0, 0, 0, _balpha)) ; // processing bug
        endColourpct[3] = endColourpct[0] = _shadePercent;
        endColour[2] = endColour[1] = color(255, 255, 255, _balpha);
        endColourpct[2] = endColourpct[1] = _lightPercent;
        break;
      case 2: //bottom-left -> top-right
        endColour[1] = endColour[0] = (_balpha == 0) ? (color(1, 0, 0, _balpha)) : (color(0, 0, 0, _balpha)) ; // processing bug
        endColourpct[1] = endColourpct[0] = _shadePercent;
        endColour[3] = endColour[2] = color(255, 255, 255, _balpha);
        endColourpct[3] = endColourpct[2] = _lightPercent;
        break;
    }
    int px, py, revpx, revpy; // pixel position
    float m, k; // helpers
    for (int i = 0; i < img.pixels.length; i++) {
      px = i % _w; py = i/_w; revpx = _w-1 - px; revpy = _h-1 - py;
      if ((py < _bsize) && (px >= py) && (revpx > py)) {
        //region = 1;
        m = k = 1 - float(py)/float(_bsize); // default: m = k
        switch(bordermode) {
          case 2: m = (k < 0.5) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break; // use to avoid corner problem when drawing very thick lines
        }
        cur_endColour = endColour[0]; cur_endColourpct = endColourpct[0];
      } else if ((revpx < _bsize) && (revpx <= py) && (revpy > revpx)) {
        //region = 2;
        m = k = 1 - float(revpx)/float(_bsize);
        switch(bordermode) {
          case 2: m = (k < 0.5) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break;
        }
        cur_endColour = endColour[1]; cur_endColourpct = endColourpct[1];
      } else if ((revpy < _bsize) && (revpy <= revpx) && (px > revpy)) {
        //region = 3;
        m = k = 1 - float(revpy)/float(_bsize);
        switch(bordermode) {
          case 2: m = (k < 0.5) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break;
        }
        cur_endColour = endColour[2]; cur_endColourpct = endColourpct[2];
      } else if ((px < _bsize) && (px < py) && (px <= revpy)) {
        //region = 4;
        m = k = 1 - float(px)/float(_bsize);
        switch(bordermode) {
          case 2: m = (k < 0.5) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break;
        }
        cur_endColour = endColour[3]; cur_endColourpct = endColourpct[3];
      } else {
        //region = 0;
        m = 1; cur_endColour = _bg.pixels[i]; cur_endColourpct = 1;
      }
      img.pixels[i] = lerpColor(_bg.pixels[i], cur_endColour, m*cur_endColourpct);
    }
    img.updatePixels();
    return img;
  }
  
  public void setBorder(int _bsize, color _border, float _shadePercent, float _lightPercent, int _angle, int _balpha, int _bordermode) {
    // possible resource hog; do not call on draw() if not needed. Won't put update need check because it's easy 
    // to implement this externally on the call to this method, rather than checking all these parameters
    margin = _bsize; border = _border; bsize = _bsize; shadePercent = _shadePercent; 
    lightPercent = _lightPercent; angle = _angle; balpha = _balpha; bordermode = _bordermode;
    if (imgMode == 0 || imgMode == 4) {
      BG = buildRectFrame(bg, border, bsize, shadePercent, lightPercent, angle, balpha, bordermode);
      imgMode = 4; setBG(BG);
    } else {
      BGbckp = BG;
      BG = buildRectFrame(BGbckp, bsize, shadePercent, lightPercent, angle, balpha, bordermode);
    }
  }
  
  public void setBorder(int _bstroke, color _border) {
    margin = _bstroke; border = _border;
  }
  
  public void display() {
    boolean drawrect = false;
    
    if (noBG) layer.fill(0, 0, 1, 0); 
    else { layer.fill(bg); drawrect = true; }
    if (margin > 0) { layer.strokeWeight(margin); layer.stroke(border); drawrect = true;}
    else { layer.noStroke(); }
    if (drawrect && imgMode < 4) {
      layer.rectMode(CENTER);
      layer.rect(x, y, w + margin, h + margin);
    }
    
    if (imgMode != 0) {
      layer.imageMode(CENTER);
      layer.image(BG, x, y);
    }
  }  
  
  public void mPressed() {}
  public void mReleased() {}
  public void mDragged() {}
  
  public PImage getBG(boolean _original) {
    PImage returner;
    returner = (_original) ? (BGbckp) : (BG) ;
    return returner;
  }
}


/**
## LABEL ##
**/

public class Label extends HLabel {
  float hbckp;
  boolean NoWordBreak;
  String [] WText; // wrapped text
  int valign;
  ImageBox BG; //default background is nothing (transparent; label is an overlayed piece of text)
  
  public Label(PApplet _parent, PGraphics _layer, PFont _FONT, String _LText, float _x, float _y, float _w, float _h, color _Foreground) {
    super(_parent, _layer, _FONT, _LText, _x, _y, _w, _h, _Foreground);
    NoWordBreak = true;
    valign = CENTER; // default
    hbckp = _h;
    WrapText();
  }
  
  public void display() {    
    if (BG != null) BG.display();  
    if (alpha(Foreground) > 0) { // only show if not transparent
      layer.textFont(FONT, fontSize);
      layer.fill(Foreground);
      for (int i = 0; i < WText.length; i++) {
        layer.text(WText[i], x + xalignoffset, y + yalignoffset + i*fontSize);
      }
    }
  }
  
  public void alignText() {
    if (alpha(Foreground) > 0) {
      calculateXoff();
      calculateYoff();
    }
  }
  
  public void calculateYoff() {
    layer.textFont(FONT, fontSize);
    switch(valign) {
      case CENTER:
        yalignoffset = -(float(WText.length)*0.5 - 1)*fontSize - 0.5*(layer.textAscent() - layer.textDescent());
        break;
      case TOP:
        yalignoffset = -h*0.5 +fontSize;
        break;
      case BOTTOM:
        yalignoffset = +h*0.5 - (WText.length - 1)*fontSize - 0.5*(layer.textAscent() - layer.textDescent());
        break;
    }
  }
  
  public void mPressed() {}
  public void mReleased() {}
  public void mDragged() {}
  
  
  private void WrapText() {
    float tolerance;
    String [] TextBlocks, CurrentTextBlockWords;
    int i, j, k;
    String CurrentLine, space;
    
    ArrayList FinalText = new ArrayList(1);
    String TMPString; 
    String [] Returner;
  
    boolean KeepRunning = true;
    
    if (layer == null) layer = createGraphics(10, 10, P2D);
    if (!HLocked) KeepRunning = false;
    
    // has to be done manually instead of using native text() wrap capabilities because info gets lost
    TextBlocks = split(LText, '\n');
    // Empty TextBlock means empty line, which means mandatory newline (user-defined in Text, will exist independent of wrap)
    
    tolerance = 0.05; // 5% tolerance
    fontSize = (HLocked) ? ( (1 - tolerance)*hbckp / (float(TextBlocks.length)) ) : ( FontSize ) ; // maximum fontsize
    layer.textFont(FONT, fontSize);
    
    do {  // loop gets done 1 time nonetheless KeepRunning is true, because of the unlocked case (needs only 1 iteration)
      layer.textSize(fontSize);
      CurrentLine = "";
      for (i = 0; i < TextBlocks.length; i++) { // wrap each textblock
        if (TextBlocks[i].length() == 0) { // empty line. Add and count it has been added
          TMPString = "";
          FinalText.add(TMPString);
        } else {
          CurrentLine = ""; // nothing has been parsed yet for this textblock. Reset
          CurrentTextBlockWords = split(TextBlocks[i], ' '); // split into words
          space = " "; // this is the space char. Trickery: if CurrentLine is still empty, do not add this space (see below)
          for (j = 0; j < CurrentTextBlockWords.length; j++) { // parse each word
            if (CurrentLine.length() == 0) space = ""; // "CurrentWord" and not " CurrentWord" => " CurrentWORD" would cause
                                                       // " faulty sentence that might screw up wrap because of the additional"
                                                       // " width of that initial space char that should not be there"
            else space = " ";
            if (layer.textWidth(CurrentLine + space + CurrentTextBlockWords[j]) <= w) { // "CurrentLine CurrentWORD" -> space in between
              CurrentLine += space + CurrentTextBlockWords[j];
            } else { // that last word doesn't fit line width. WRAP!
              if (!NoWordBreak && layer.textWidth(CurrentTextBlockWords[j]) > w) { // only if words can be broken
                // word bigger than line width. THIS SUCKS. Break the word.
                if (layer.textWidth(CurrentLine + space + CurrentTextBlockWords[j].charAt(0)) <= w) { // if space and first char still fit this line
                  CurrentLine += " "; // add space to this line, word will start being put after that space
                } else { // not even space fits. Start a new line to continue parsing
                  if (CurrentLine.length() != 0) FinalText.add(CurrentLine);
                  CurrentLine = "";
                }
                for (k = 0; k < CurrentTextBlockWords[j].length() - 1; k++) {
                  if (layer.textWidth(CurrentLine + CurrentTextBlockWords[j].charAt(k + 1)) > w - layer.textWidth("m")) { // m is a wide letter
                    FinalText.add((CurrentLine + CurrentTextBlockWords[j].charAt(k)));
                    CurrentLine = "";
                  } else {
                    CurrentLine += CurrentTextBlockWords[j].charAt(k);
                  }
                } // below: take care of last char
                if (layer.textWidth(CurrentLine + CurrentTextBlockWords[j].charAt(k)) > w - layer.textWidth("m")) {
                  if (CurrentLine.length() != 0) FinalText.add(CurrentLine);
                  CurrentLine = CurrentTextBlockWords[j].charAt(k) + "";
                } else {
                  CurrentLine += CurrentTextBlockWords[j].charAt(k);
                }
              } else {
                if (CurrentLine.length() > 0) FinalText.add(CurrentLine); // empty lines have been taken care of
                if (layer.textWidth(CurrentTextBlockWords[j]) > w && NoWordBreak) { // if word is bigger than width and words cannot be broken
                  while (layer.textWidth(CurrentTextBlockWords[j]) > w) { // make font smaller until word fits
                    fontSize -= 0.2;
                    layer.textFont(FONT, fontSize);
                  }
                }
                CurrentLine = CurrentTextBlockWords[j]; // newline; begins with that word which didn't fit that line just before
              }
            }
          }
        }
        if (TextBlocks[i].length() != 0) {
          FinalText.add(CurrentLine); // last word, then move on to next textblock 
        }
      }
      
      if (FinalText.size()*fontSize <= (1 - tolerance)*hbckp) KeepRunning = false;
      else {
        if (HLocked) {
          fontSize -= 0.2;
          for (i = FinalText.size() - 1; i >= 0; i--) {
            FinalText.remove(i);
          }
        }
      }
      
    } while(KeepRunning);
  
    Returner = new String[FinalText.size()]; // working with strings is easier
    for (i = FinalText.size() - 1; i >= 0; i--) {
      Returner[i] = (String) FinalText.get(i);
      FinalText.remove(i);
    }
    if (!HLocked) {
      h = Returner.length*fontSize;
    }
    
    WText = Returner;
    
    alignText();
  }
  
  public void setBG(ImageBox _BG) {
    if (_BG == null) return;
    BG = _BG;
    if (dimsLinked) {
      BG.setBoundingBox(x, y, w, h);
      // BG's and Labels' dimensions are linked (BG adapts to Label)
    } else {
      BG.restoreDefaults(); // reset constuctor's settings (x, y, w, h)def
      BG.setPos(x, y); // BG's and Labels' w and h are independent to allow trickeries, but x and y not
    }
  }
  
  public void setDimsLinked(boolean _dimsLinked) {
    dimsLinked = _dimsLinked;
    if (BG != null) {
      setBG(BG);
      BG.updateBG();
    }
  }
  
  public void setHLocked(boolean _HLocked) {
    if (_HLocked != HLocked) {
      HLocked = _HLocked;
      WrapText();
      if (dimsLinked && BG != null) {
        setBG(BG);
        BG.updateBG();
      }
      if (HLocked) h = hbckp;
    }
  }
  
  public void setFont(PFont _FONT) {
    if (!_FONT.equals(FONT)) {
      FONT = _FONT;
      WrapText();
      if (!HLocked && dimsLinked && BG != null) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
  
  public void setFontSize(float _FontSize) {
    if (_FontSize != FontSize) {
      FontSize = _FontSize;
      if (!HLocked) {
        WrapText();
        if (dimsLinked && BG != null) {
          setBG(BG);
          BG.updateBG();
        }
      }
    }
  }
  
  public void setNoWordBreak(boolean _NoWordBreak) {
    if (_NoWordBreak != NoWordBreak) {
      NoWordBreak = _NoWordBreak;
      WrapText();
      if (!HLocked && dimsLinked && BG != null) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
  
  public void setText(String _LText) {
    if (!_LText.equals(LText)) {
      LText = new String(_LText);
      WrapText();
      if (!HLocked && dimsLinked && BG != null) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
  
  public void setAlignment(int _halign, int _valign) {
    if (_halign != 0) halign = _halign;
    if (_valign != 0) valign = _valign;
    alignText();
  }
  
  public void forceUpdate() {
    WrapText();
    if (BG != null) {
      setBG(BG);
      BG.updateBG();
    }
  }
}

/**
## HLABEL ##
1-line vertically locked label, horizontally unlocked (for fields)
**/

public class HLabel extends UIObject {
  float fontSize, FontSize, xalignoffset, yalignoffset;
  PFont FONT;
  boolean dimsLinked, HLocked;
  String LText; // hlabel text
  int halign;
  color Foreground;
  ImageBox BG; //default background is nothing (transparent; label is an overlayed piece of text)
  
  public HLabel(PApplet _parent, PGraphics _layer, PFont _FONT, String _LText, float _x, float _y, float _w, float _h, color _Foreground, float _fontSize) {
    super(_parent, _layer);
    FONT = _FONT;
    LText = new String(_LText);
    fontSize = FontSize = _fontSize;
    setBoundingBox(_x, _y, _w, _h);
    halign = CENTER; // default (valign is always CENTRE, so no need to define it)
    Foreground = _Foreground; dimsLinked = HLocked = true;
    WrapText();
    alignText();
  }
  
  public HLabel(PApplet _parent, PGraphics _layer, PFont _FONT, String _LText, float _x, float _y, float _w, float _h, color _Foreground) {
    // basic constructor to use in Label subclass (handles wrap its own way)
    super(_parent, _layer);
    FONT = _FONT;
    LText = new String(_LText);
    setBoundingBox(_x, _y, _w, _h);
    halign = CENTER; // default (valign is always CENTRE, so no need to define it)
    Foreground = _Foreground; dimsLinked = HLocked = true;
  }
  
  public void alignText() {
    if (alpha(Foreground) > 0) { // only bother to calculate if not transparent
      calculateXoff();
      calculateYoff();
    }
  }
  
  public void calculateXoff() {
    switch(halign) {
      case CENTER:
        xalignoffset = 0;
        layer.textAlign(CENTER, BASELINE);  
        break;
      case LEFT:
        xalignoffset = -w*0.5;
        layer.textAlign(LEFT, BASELINE);
        break;
      case RIGHT:
        xalignoffset = +w*0.5;
        layer.textAlign(RIGHT, BASELINE);
        break;
    }
  }
  
  public void calculateYoff() {
    yalignoffset = 0.5*(layer.textAscent() - layer.textDescent());
  }
  
  public void display() {
    if (BG != null) BG.display();
    if (alpha(Foreground) > 0) {// only draw if not transparent
      layer.textFont(FONT, fontSize);
      layer.fill(Foreground);
      layer.text(LText, x + xalignoffset, y + yalignoffset);
    }
  }
  
  private void WrapText() {
    float tolerance, fontsize;
    
    if (layer == null) layer = createGraphics(10, 10, P2D);
    tolerance = 0.05;
    
    LText = LText.replaceAll("\n", "");
    // error handling; 1-liner can't have multiple lines. Handle enter/return outside
    
    layer.textFont(FONT, FontSize);
    fontSize = FontSize;
    
    if (HLocked) {
      float increment, lineHeight;
      boolean KeepRunning = true;
      int cur_signal, prev_signal;
      
      fontsize = FontSize;  // initial guess
      layer.textSize(fontsize);
      lineHeight = layer.textAscent() + layer.textDescent();
      cur_signal = (lineHeight > h) ? (-1) : (+1) ;
      increment = (cur_signal > 0) ? (fontsize*2) : (fontsize/2) ; // initial guess
      
      while (KeepRunning) {
        fontsize += cur_signal*increment;
        layer.textSize(fontsize);
        lineHeight = layer.textAscent() + layer.textDescent();
        if (lineHeight > (1 - tolerance)*h && lineHeight < h) KeepRunning = false;
        prev_signal = cur_signal;
        cur_signal = (lineHeight > (1 - 0.5*tolerance)*h) ? (-1) : (+1) ;
        if (cur_signal + prev_signal == 0) {
          increment  *= 0.9;
        }
      }
      fontSize = fontsize;
    }
    
    w = layer.textWidth(LText)/(1 - tolerance);
  }
  
  public void mPressed() {}
  public void mReleased() {}
  public void mDragged() {}
  
  public void setBG(ImageBox _BG) {
    if (_BG == null) return;
    BG = _BG;
    if (dimsLinked) {
      BG.setBoundingBox(x, y, w, h);
      // BG's and Labels' dimensions are linked (BG adapts to Label)
    } else {
      BG.restoreDefaults(); // reset constuctor's settings (x, y, w, h)def
      BG.setPos(x, y); // BG's and Labels' w and h are independent to allow trickeries, but x and y not
    }
  }
  
  public void setDimsLinked(boolean _dimsLinked) {
    dimsLinked = _dimsLinked;
    if (BG != null) {
      setBG(BG);
      BG.updateBG();
    }
  }
  
  public void setFont(PFont _FONT) {
    if (!_FONT.equals(FONT)) {
      FONT = _FONT;
      WrapText();
      if (BG != null && dimsLinked) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
  
  public void setFontSize(float _FontSize) {
    if (_FontSize != FontSize) {
      FontSize = _FontSize;
      WrapText();
      if (BG != null && dimsLinked) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
  
  public void setHLocked(boolean _HLocked) {
    if (_HLocked != HLocked) {
      HLocked = _HLocked;
      WrapText();
      if (BG != null && dimsLinked) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
  
  public void setText(String _LText) {
    if (!LText.equals(_LText)) {
      LText = _LText;
      WrapText();
      if (BG != null && dimsLinked) {
        setBG(BG);
        BG.updateBG();
      }
    }
  }
}

/**
## BUTTON ##

5 states:
free = 0
hovered1 = 1 (0.1)
pressed = 2
hovered2 = 3 (3.9)
selected = 4

macBlink = 2.5 (3)
**/

public class Button extends UIObject {
  ImageBox [] statesI; // images for each state
  Label [] statesT; // text for each states
  int cur_state, prev_state, display_state, default_state; // flags for each state
  screenRegion [] active; // map screen region where button will be clickable/hoverable
  int bdr; // border size
  
  boolean macBlink;     // blink effect from macOS (will fire up state 5!); 
  boolean macBlinked;   // will blink the colour of the current state when clicked
  Timer macBlinker; boolean macBlinking; int macBlinkTime, numBlinks;
  
  boolean hover; // hovering (enable = cute / disable = save cpu)
  boolean forceHide, defaultState, autoDisplayUpdate;
  
  public Button(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h) {
    // most generalist constructor; states images and texts are handled externally by ImageBoxes and Labels
    // ImageBoxes will get resized to fit bounding box, Labels must be dimentioned manually
    // (trial and error, depends on how you want them to look like)
    super(_parent, _layer);
    setBoundingBox(_x, _y, _w, _h);
    focusable = true; focus = forceHide = false;
    prev_state = cur_state = display_state = default_state = 0;
    statesI = new ImageBox[6]; statesT = new Label[6]; active = new screenRegion[6];
    hover = autoDisplayUpdate = true;
    setupMacBlink(true, 100, 4);
    defaultState = false;
  }
  
  public Button(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, int _bdr,
                color state0, color state1, color state2, color state3, color state4, 
                PFont _FONT, String _BText, color t0, color t1, color t2, color t3, color t4) {
    // rectangular button, constant text; if needed, change text by setter
    this(_parent, _layer, _x, _y, max(_w - _bdr, 3), max(_h - _bdr, 3)); bdr = _bdr;
    
    color [] cstate = new color[6], tstate = new color[6]; int [] bstate = new int[6];
    cstate[0] = state0; cstate[1] = state1; cstate[2] = state2; cstate[3] = state3; cstate[4] = state4;
    cstate[5] = color(255 - red(state2), 255 - green(state2), 255 - blue(state2), alpha(state2));
    tstate[0] = t0; tstate[1] = t1; tstate[2] = t2; tstate[3] = t3; tstate[4] = t4;
    tstate[5] = color(255 - red(t2), 255 - green(t2), 255 - blue(t2), alpha(t2));
    bstate[0] = bstate[1] = -1; bstate[2] = bstate[3] = bstate[4] = bstate[5] = 1;
    // we might want null labels or imageboxes (bar of Scroll object or cute little hovering labels)
    for (int i = 0; i < 6; i++) {
      try {
      statesI[i] = new ImageBox(parent, layer, x, y, w, h, cstate[i], 0, 0);
      statesI[i].setBorder(max(bdr, 1), cstate[i], 0.6, 0.6, bstate[i], int(alpha(cstate[i])), 4);
      statesT[i] = new Label(parent, layer, _FONT, _BText, x, y, w, h, tstate[i]);
      } catch (Exception e) {}
    }
    active[0] = new screenRegion(int(w), int(h));
    active[0].addRectangle(2, 0, 0, w, h);
    active[5] = active[4] = active[3] = active[2] = active[1] = active[0]; // pointers trickery
  }
  
  public boolean probeActiveMouse() {
    //returns instant focus regardless of being focusable. That is taken in consideration in var focus;
    //could test only var focus, but this method needs to be called anyway to update it, so we'll
    //call it also inside the if statements
    //Point p = GLOBAL.getScreenInfo.coords_abs2rel(parent, 1, GLOBAL.getScreenInfo.curabsMousePos());
    Point p = new Point(mx, my);
    p.x += w/2 - x + xoff; p.y += h/2 - y + yoff;
    boolean _focus = active[display_state].isPointInRegion(p);
    if (focusable && !GLOBAL.disableUIFocus) focus = _focus;
    return _focus;
  }
  
  public void mapActive(color _mc, boolean _isInclusive, int _state) {
    //alias for mapActive() but using statesI. _state is the state to use;
    //negative _state flags constant active ; _state == +-6 flags all
    if (abs(_state) > 6) {
      if (GLOBAL.VERBOSE) println("Button: mapActive: _state out of range");
      return;
    } else if (_state == -6) _state = 6;
    if (_state < 0) {
      mapActive(statesI[-_state].getBG(false), _mc, _isInclusive, 0);
      active[5] = active[4] = active[3] = active[2] = active[1] = active[0];
    } else {
      if (_state == 6) {
        for (_state = 0; _state < 6; _state++) {
          mapActive(statesI[_state].getBG(false), _mc, _isInclusive, _state);
        }
      } else {
        mapActive(statesI[_state].getBG(false), _mc, _isInclusive, _state);
      }
    }
  }
  
  public void mapActive(PImage _amap, color _mc, boolean _isInclusive, int _index) {
    // _isInclusive == true => mc maps active area
    // _isInclusive == false => mc maps everything that is not active area
    int _w, _h; _w = int(w); _h = int(h);
    PImage amap = createImage(_w, _h, ARGB);
    amap.copy(_amap, 0, 0, _amap.width, _amap.height, 0, 0, _w, _h);
    active[_index].addImage(amap, _mc, _isInclusive);
  }
  
  public void update(boolean scaleActiveRegion, int _state) { // when changing boundingbox
    for (int i = 0; i < 6; i++) {
      try {
        statesI[i].setBoundingBox(x, y, w, h);
        statesI[i].updateBG();
        statesT[i].setBoundingBox(x, y, w, h);
        statesT[i].forceUpdate();
      } catch (Exception e) {}
    }
    if (scaleActiveRegion) {
      if (_state < 0) {
        active[0].fit(int(w), int(h), true, 0, 0, 0, 0);
        active[5] = active[4] = active[3] = active[2] = active[1] = active[0];
      } else {
        if (abs(_state) > 6) {
          if (GLOBAL.VERBOSE) println("Button: mapActive: _state out of range");
          return;
        } else if (_state == -6) _state = 6;
        if (_state == 6) {
          for (int i = 0; i < 6; i++) {
            try {
              active[i].fit(int(w), int(h), true, 0, 0, 0, 0);
            } catch (Exception e) {}
          }
        } else {
          try {
            active[_state].fit(int(w), int(h), true, 0, 0, 0, 0);
          } catch (Exception e) {}
        }
      }
    }
  }
  
  public void display() {
    /* 1) to deactivate display and mantain functionality: enable forceHide through setDState
       2) to deactivate functionality and mantain display: disable focusable through setFocusable
         2)a) if hovering must be deactivated too, disable hover through setHover
       3) to deactivate both, do 1) and 2)
    */
    if (autoDisplayUpdate) { 
      // if setDState() is used with setAutoDisplayUpdate(false), 
      // one can control buttons appearance manually (and externally)
      display_state = cur_state; // this might be changed
    
      if (macBlink) {
        if (!macBlinked) {
          macBlinker.restart();
          display_state = 5;
          macBlinked = true;
          macBlinking = true;
        } else if (macBlinking) {
          if (macBlinker.elapsed() < numBlinks*macBlinkTime) {
            display_state = ((int(macBlinker.elapsed()/macBlinkTime) % numBlinks) % 2 == 0) ? (5) : (2) ;
          } else {
            //display_state = cur_state;
            macBlinking = false;
          }
        }
      }
    
      if (hover && !macBlinking) { // correct display_state if hovering is on
        if (probeActiveMouse() && cur_state != 2) {//not testing for var focus because this is just hovering
          display_state = (cur_state < 2) ? (cur_state + 1) : (cur_state - 1) ;
        } //else display_state = cur_state;
      }
    
    }
    try { // we might want null labels or imageboxes
      if (!forceHide) {
        statesI[display_state].display();
        statesT[display_state].display();
      }
    } catch (Exception e) {}
  }
  
  public void mPressed() {
    if (probeActiveMouse() && focus) {
      prev_state = cur_state;
      cur_state = 2;
    }
  }
  public void mReleased() {
    if (cur_state == 2) {
      if (defaultState) {
        cur_state = default_state;
      } else {
        cur_state = (prev_state < 2) ? (4) : (0) ;
      }
      if (macBlink) macBlinked = false;
    }
  }
  public void mDragged() {}
  
  public void fitBG(int _state) { //fit BG to bounding box 
    try {
      statesI[_state].setBoundingBox(x, y, w, h);
      statesI[_state].updateBG();
    } catch (Exception e) {}
  }
  
  public void setupMacBlink(boolean _macBlink, int _blinkTime, int _numBlinks) {
    if (_macBlink != macBlink || macBlinkTime != _blinkTime || numBlinks != _numBlinks) {
      macBlinkTime = _blinkTime; numBlinks = _numBlinks;
      macBlink = _macBlink;
      if (macBlink) {
        macBlinker = new Timer(_numBlinks*_blinkTime);
        macBlinked = true; // start deactivated, will activate on mousereleased after click
        macBlinking = false;
      }
    }
  }
  
  public void setHover(boolean _hover) {
    hover = _hover;
  }
  
  public void setBG(int _state, ImageBox _BG) { // setup imagebox for BG of each state
    try {
      statesI[_state] = _BG;
    } catch (Exception e) {}
  }
  
  public void setLabel(int _state, Label _LBL) { // setup label for text of each state
    try {
      statesT[_state] = _LBL;
    } catch (Exception e) {}
  }
  
  public void setCState(int _cur_state) {
    if (_cur_state >= 0 && _cur_state <= 4) cur_state = _cur_state;
  }
  
  public void setPState(int _prev_state) {
    if (_prev_state >= 0 && _prev_state <= 4 && _prev_state != 2) prev_state = _prev_state;
  }
  
  public void setDState(int _display_state) { // invalid flags forceHide
    if (_display_state >= 0 && _display_state < 6) display_state = _display_state; //ordinary
    else forceHide = true; // hide button but not deactivate it
  }
  
  public void setDefaultState(boolean _defaultState, int _default_state) {
    defaultState = _defaultState; default_state = _default_state;
  }
  
  public void setActive(screenRegion _active, int index) {
    // if buttons changes its bounding box, a new empty screenRegion with the new dimentions
    // must be injected through this method and then the active region can be remapped properly
    active[index] = _active;
  }
  
  public void setAutoDisplayUpdate(boolean _autoDisplayUpdate) {
    autoDisplayUpdate = _autoDisplayUpdate;
  }
  
  public int getCState() { // current state
    return cur_state;
  }
  
  public int getPState() { // previous state
    return prev_state;
  }
  
  public int getDState() { // display state
    return display_state;
  }
  
  public ImageBox getState(int _state) {
    try {
      return statesI[_state];
    } catch (Exception e) {
      return statesI[0];
    }
  }
  
  public screenRegion getActiveRegion(int _state) {
    try {
      return active[_state];
    } catch (Exception e) {
      return active[0];
    }
  }
}

/**
## SCROLL ##
**/

public class Scroll extends UIObject {
  boolean vertical, resetMW, scrollRequested, updateDisplayFlag;
  int saveMWDisplacement, deltaMWDisplacement;
  screenRegion expanded; //map expanded hover area for mousewheel detection
  boolean expandedEnabled, MWEnabled;
  int eoffx, eoffy; // expanded area offsets to map mx and my into expanded
  
  Button [] controls; int [] controlsflags;
  float railpercent, VA_d, RVratio;
  int bar_var, but_var, barpos, barabspos, dragOffset; // variable dimensions/positions
  //int [] rail_var; // variable dimensions and positions for rail
  float fixeddim, vardim, varpos, fixedpos; // who is w and who is h
  int [] cx, cy, cw, ch;
  float MWsensitivity, Bsensitivitystep;
  
  boolean barDrag; int buttonsAction, queuedbutton, queuedamount;
  boolean isScrolling; // flag transient state
  
  color c_0, c_1, c_2, c_3, c_4, c_fg, c_rail; // c_fg is foreground (arrows), constant.
  
  public Scroll(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h,
                float _railpercent, boolean _vertical, float _VA_d) { //general constructor
    super(_parent, _layer);
    setBoundingBox(_x, _y, _w, _h);
    
    VA_d = _VA_d;
    controls = new Button[5]; // user can change buttons using their setters; 
    // default is arrows + bar. Both with 1 dimension locked and other variable
    cx = new int[5]; cy = new int[5]; cw = new int[5]; ch = new int[5];
    controlsflags = new int[5]; //   | display |  hover  |  click  | (flag vestibular: 0+1+2=3; 1+2+4=7, etc)
                                // 0 |    n    |    n    |    n    | 0 = bar
                                // 1 |    y    |    n    |    n    | 1 = up button
                                // 2 |    n    |    y    |    n    | 2 = down button
                                // 4 |    n    |    n    |    y    | 3 = rail (hover = blink)
    /*
    all: { 0 1 2 3 4 5 6 7 }
    dont have no. 1: { 0 2 4 6 } (i even)
    dont have no. 2: { 0 1 4 5 } (int(i/2) even)
    dont have no. 4: { 0 1 2 3 } (i < 4)
    */
    
    vertical = _vertical; railpercent = min(_railpercent, 1);
    
    setRBarPos(0); updateDisplayFlag = true;
    setinitdims();
    
    MWsensitivity = (vardim*railpercent - bar_var -4)/(3*5); //default
    Bsensitivitystep = (vardim*railpercent - bar_var -4)/(3*5); //default
    resetMW = focusable = true; focus = scrollRequested = barDrag = false;
    buttonsAction = queuedbutton = dragOffset = queuedamount = 0;
    expanded = null; // no expanded area by default
    isScrolling = expandedEnabled = false; MWEnabled = true;
    eoffx = eoffy = 0;
  }
  
  public Scroll(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, 
                float _railpercent, boolean _vertical, color _c_fg, color _c_0, color _c_2, color _c_4,
                float _VA_d) { // border constant = 2, rectangular 3D-effect controls
    this(_parent, _layer, _x, _y, _w, _h, _railpercent, _vertical, _VA_d);
    
    // barpercent is the percentage of the space (h for vertical, w for horizontal) which will be
    // occupied by the scrolling rail. The remainder will be equally divided between the buttons, 
    // whose other dimention is the locked dimension (w for vertical, h for horizontal)
    
    for (int i = 0; i < 5; i++) controlsflags[i] = 7;
    if (railpercent == 0) { controlsflags[0] = 0; controlsflags[3] = controlsflags[4] = 0;}
    else if (railpercent == 1) { controlsflags[1] = 0; controlsflags[2] = 0; }
    
    c_0 = _c_0; c_2 = _c_2; c_4 = _c_4; 
    c_fg = (alpha(_c_fg) == 0) ? (color(0, 0, 1, 0)) : (_c_fg) ;
    c_1 = lerpColor(c_0, c_2, 0.5); c_3 = lerpColor(c_2, c_4, 0.5);
    c_rail = lerpColor(c_0, color(255, 255, 255, alpha(c_0)), 0.7);
    
    buildcontrols();
    buildarrowbuttons();
  }
  
  public void setinitdims() {
    int i;
    if (vertical) {
      fixeddim = w;
      vardim = h;
      fixedpos = x;
      varpos = y;
      cx[0] = int(x); cw[0] = int(w) -4;
      for(i = 1; i < 5; i++) {
        cx[i] = cx[i-1]; //pointers trickery
        cw[i] = cw[i-1];
      }
      cw[3] = cw[4] = int(w);
    } else {
      fixeddim = h;
      vardim = w;
      fixedpos = y;
      varpos = x;
      cy[0] = int(y); ch[0] = int(h) -4;
      for(i = 1; i < 5; i++) {
        cy[i] = cy[i-1]; //pointers trickery
        ch[i] = ch[i-1];
      }
      ch[3] = ch[4] = int(h);
    }
    
    RVratio = vardim/VA_d;
    bar_var = int(min(1, RVratio)*vardim*railpercent) - 4;
    but_var = int(vardim*(1 - railpercent)/2);
    
    float tmp; int tmp1, tmp2;
    tmp = 0.5*(vardim - but_var);
    tmp1 = int(varpos - tmp); tmp2 = int(varpos + tmp);
    
    if (vertical) {
      cy[1] = tmp1; cy[2] = tmp2; 
      ch[0] = bar_var; ch[1] = ch[2] = but_var;
    } else {
      cx[1] = tmp1; cx[2] = tmp2; 
      cw[0] = bar_var; cw[1] = cw[2] = but_var;
    }
    
    update(); // with previously set barpos
  }
  
  public void buildcontrols() {
    int i; color[][] co = new color[5][5];
    co[0][0] = c_0; co[0][1] = c_1; co[0][2] = c_2; co[0][3] = c_3; co[0][4] = c_4;
    co[2] = co[1] = co[0];
    co[3][0] = c_rail; co[3][1] = co[3][3] = co[3][4] = 0; 
    co[3][2] = color(255 - red(c_rail), 255 - green(c_rail), 255 - blue(c_rail));
    co[4] = co[3];
    
    for (i = 0; i < 5; i++) {
      controls[i] = new Button(parent, layer, cx[i], cy[i], cw[i], ch[i], 2, 
                               co[i][0], co[i][1], co[i][2], co[i][3], co[i][4], null, null, 0, 0, 0, 0, 0);
      controls[i].setDefaultState(true, 0);
      //if (i < 3) controls[i].setupMacBlink(false, 0, 0);
      controls[i].setupMacBlink(false, 0, 0);
      if (i > 2) {
        controls[i].setHover(false);
        controls[i].getState(0).setBorder(0, 0, 0, 0, 0, 0, 4);
        controls[i].getState(2).setBorder(0, 0, 0, 0, 0, 0, 4);
        controls[i].getState(5).setBorder(0, 0, 0, 0, 0, 0, 4);
      }
    }
  }
  
  public void buildarrowbuttons() {
    int i; PImage tmp; ImageBox imtmp; float angle;
    
    angle = (vertical) ? (0) : (-PI/2) ;
    for (i = 0; i < 6; i++) {
      tmp = buildarrowstate(controls[1].getState(i), angle);
      controls[1].getState(i).setImgMode(5);
      controls[1].getState(i).BG = tmp; // cheating
      controls[1].getState(i).setBG(tmp);
      tmp = buildarrowstate(controls[2].getState(i), angle+PI);
      controls[2].getState(i).setImgMode(5);
      controls[2].getState(i).BG = tmp; // cheating
      controls[2].getState(i).setBG(tmp);
    }
  }
  
  public PImage buildarrowstate(ImageBox _stateI, float _angle) {
    int i, W, H, bsize; PGraphics _trick; float diag, sina, cosa;
    PImage returner, _map, _bg, _loaded, _snapshot;
    
    _bg = _stateI.getBG(false);
    _loaded = loadImage("arrow_aus.png"); //greyscale map, like an alpha channel.
    //_loaded cant have alpha. Colour info will be ignored (using brightness())
    _map = createImage(_bg.width, _bg.height, ARGB);
    
    diag = sqrt(_loaded.width*_loaded.width + _loaded.height*_loaded.height);
    _trick = createGraphics(int(diag) +1, int(diag) +1, P2D); // little offscreen buffer to rotate image
    _trick.beginDraw();
    _trick.background(#FFFFFF);
    _trick.imageMode(CENTER);
    _trick.pushMatrix();
    _trick.translate(_trick.width/2, _trick.height/2);
    _trick.rotate(_angle);
    _trick.image(_loaded, 0, 0);
    _trick.popMatrix();
    
    sina = abs(sin(_angle)); cosa = abs(cos(_angle)); bsize = 2;
    W = int(_loaded.height*sina + _loaded.width*cosa);
    H = int(_loaded.height*cosa + _loaded.width*sina);
    
    _map.copy(_trick, (_trick.width - W)/2, (_trick.height - H)/2, 
              W, H, bsize, bsize, _bg.width - 2*bsize, _bg.height -2*bsize); 
    
    _trick.endDraw();
    
    returner = createImage(_bg.width, _bg.height, ARGB);
    _map.loadPixels(); returner.loadPixels(); _bg.loadPixels();
      
    for (i = bsize*_bg.width; i < returner.pixels.length - bsize*_bg.width; i++) { // trim out upper and lower borders
      if (i % _bg.width >= bsize && i % _bg.width < _bg.width - bsize) // trim out left and right borders
        returner.pixels[i] = lerpColor(c_fg, _bg.pixels[i], brightness(_map.pixels[i])/255.0) ;
    }
    returner.updatePixels();
    
    return returner;
  }
  
  public void update() {
    int i;
    float tmp;
    int [] rail_var = new int[4]; // aux helper vars
    
    if (railpercent == 0) { controlsflags[0] = 0; controlsflags[3] = controlsflags[4] = 0; }
    tmp = varpos - vardim*0.5 + but_var + barpos +2;
    barabspos = int(tmp + bar_var*0.5);
    rail_var[0] = barpos; rail_var[1] = int(vardim*railpercent - bar_var - barpos) -4;
    rail_var[2] = int(tmp - rail_var[0]*0.5); rail_var[3] = int(varpos + vardim*0.5 - but_var - rail_var[1]*0.5) -2;
    if (vertical) {
      cy[0] = barabspos; cy[3] = rail_var[2]; cy[4] = rail_var[3];
      ch[3] = rail_var[0]; ch[4] = rail_var[1];
    } else {
      cx[0] = barabspos; cx[3] = rail_var[2]; cx[4] = rail_var[3];
      cw[3] = rail_var[0]; cw[4] = rail_var[1];
    }
  }
  
  private void mousedaemon() { // bar drag and buttons
    int i, stillpressed = 0;
    if (barDrag) {
      scrollRequested = false; //force deactivation of mousewheel (bar drag has precedence)
      updateScrollD();
    } else if (buttonsAction > 0) { //buttons
      scrollRequested = false;
      for (i = 1, stillpressed = 0; i <= 4 && stillpressed == 0; i++) 
        if (controls[i].getCState() > 0) stillpressed = i;
      if (stillpressed > 0) buttonsAction = int((stillpressed + 1)/2)*2;
      switch(buttonsAction) {
        case 1:
          updateScrollB(queuedamount*(2*queuedbutton-3));
          buttonsAction = queuedamount = 0;
          break;
        case 2:
          updateScrollB(queuedamount*(2*stillpressed-3));
          break;
        case 3:
          updateScrollR(queuedamount*(2*queuedbutton-7));
          buttonsAction = queuedamount = 0;
          break;
        case 4:
          updateScrollR(2*queuedbutton-7);
          break;
      }
    }
  }
  
  private void mwdaemon() {
    if (focus) { // if on focus, update
      if (resetMW) { // has just regained focus, needs to reset saved mousewheel state
        saveMWDisplacement = GLOBAL.getMouseWheelInfo.getDisplacement();
        resetMW = false;
      } else { // normal operation
        int curMWDisplacement = GLOBAL.getMouseWheelInfo.getDisplacement();
        deltaMWDisplacement = curMWDisplacement - saveMWDisplacement;
        saveMWDisplacement = curMWDisplacement;
        if (deltaMWDisplacement  != 0) {
          scrollRequested = true;
        }
      }
    } else { // has lost focus, whenever it regains, needs to reset saved mousewheel state
      resetMW = true;
    }
  }
  
  private boolean probeFocus() { // probe focus; continuous since mousewheel must be probed on hover
    boolean _focus = false;
    int px = int(mx - x + xoff); int py = int(my - y + yoff);
    Point p = new Point(int(mx - eoffx), int(my - eoffy));
    if (expanded != null && expandedEnabled) {
      if (expanded.isPointInRegion(p)) _focus = true;
    }
    if (abs(px) <= w/2 && abs(py) <= h/2) _focus = true;
    if (focusable && !GLOBAL.disableUIFocus) focus = _focus;
    return _focus;

  }
  
  private void statedaemon() {
    isScrolling = false;
    if (barDrag || scrollRequested || buttonsAction != 0) isScrolling = true;
  }
  
  public void daemons() {
    probeFocus();
    if (MWEnabled) mwdaemon();
    mousedaemon();
    statedaemon();
    
    if (scrollRequested) {
      updateScrollMW();
      scrollRequested = false;
    }
  }
  
  public void updateScrollMW() { // mousewheel
    setRBarPos(int(barpos + MWsensitivity*deltaMWDisplacement));
  }
  
  public void updateScrollB(float _multiplier) { // buttons
    setRBarPos(int(barpos + _multiplier*Bsensitivitystep));
  }
  
  public void updateScrollD() { // drag
    setRBarPos( ((vertical) ? (my) : (mx)) - dragOffset );
  }
  
  public void updateScrollR(float _multiplier) { // rail
    int mpos, sign;
    float oldcmpos, oldcbpos, newcmpos, newcbpos;
    
    sign = (_multiplier > 0) ? (1) : (-1);
    
    mpos = (vertical) ? (my) : (mx) ;
    oldcmpos = mpos - (varpos - vardim/2 + but_var);
    oldcbpos = barpos + bar_var/2*(1 + sign);
    
    setRBarPos(int(barpos + _multiplier*vardim*railpercent*RVratio));
    
    mpos = (vertical) ? (my) : (mx) ;
    newcmpos = mpos - (varpos - vardim/2 + but_var);
    newcbpos = barpos + bar_var/2*(1 + sign);
    
    if ((oldcmpos - oldcbpos)*(newcmpos - newcbpos) <= 0) {
      buttonsAction = queuedamount = 0;
      controls[queuedbutton].setCState(0);
    }
  }
  
  public void display() { 
    int i;
    
    daemons();
    
    for (i = 4; i >= 0; i--) {
      if (controlsflags[i] % 2 != 0) {
        if (updateDisplayFlag) {
          controls[i].setBoundingBox(cx[i], cy[i], cw[i], ch[i]);
          controls[i].update(true, -1);
          controls[i].getActiveRegion(0).addRectangle(2, 0, 0, cw[i], ch[i]);
          // rebuild active area in case screenregion size goes to zero and has to come back
        }
        controls[i].display();
      }
    }
    if (updateDisplayFlag) updateDisplayFlag = false;
    
    /*float ha = my - (y - h/2 + but_var);
    float hb = barpos + bar_var;
    stroke(#00FF00);
    line(0, ha, 700, ha);
    stroke(#0000FF);
    line(0, hb, 700, hb);*/
  }
  
  public void mPressed() {
    int i;
    for (i = 0; i < 5; i++) {
      controls[i].mPressed();
      if (controls[i].getCState() == 2) {
        switch(i) {
          case 0:
            barDrag = true; 
            dragOffset = (vertical) ? (my) : (mx) ;
            dragOffset -= barpos;
            break;
          case 1:
          case 2:
            buttonsAction = 1;
            queuedbutton = i;
            queuedamount++;
            break;
          case 3:
          case 4:
            buttonsAction = 3;
            queuedbutton = i;
            queuedamount++;
            break;
        }
      }
    }
  }
  public void mReleased() {
    barDrag = false;
    if (buttonsAction % 2 == 0) { 
      buttonsAction = queuedamount = 0;
    }
  }
  public void mDragged() {}
  
  public void setMWsensitivity(float _MWsensitivity) {
    MWsensitivity = _MWsensitivity;
  }
  
  public void setActive(boolean _active, boolean _includeHover) {
    int i;
    
    focusable = _active;
    for (i = 0; i < 5; i++) {
      controls[i].forceFocusable(_active);
      if (_includeHover) controls[i].setHover(_active);
    }
    if (!_active) {
      focus = false;
      for (i = 0; i < 5; i++) controls[i].forceFocus(false);
    }
  }
  
  public void setUpdateDisplayFlag(boolean _updateDisplayFlag) {
    updateDisplayFlag = _updateDisplayFlag;
  }
  
  public void stopScroll() {
    if (isScrolling) {
      barDrag = scrollRequested = false;
      buttonsAction = queuedamount = 0;
      
      for (int i = 0; i < 5; i++) controls[i].mReleased();
      isScrolling = false;
    }
  }
  
  public void setExpanded(screenRegion _expanded, int _eoffx, int _eoffy) {
    expanded = _expanded;
    eoffx = _eoffx; eoffy = _eoffy; // top left corner of expanded area 
                                    //(coords relative to the same system as mx and my)
  }
  
  public void enableMouseWheel(boolean _MWEnabled) {
    MWEnabled = _MWEnabled;
  }
  
  public void enableExpanded(boolean _expandedEnabled) {
    expandedEnabled = _expandedEnabled;
  }
  
  public void setRBarPos(int _barpos) { // real barpos
    if (_barpos < 2) _barpos = 2;
    else {
      float tmp = vardim*railpercent - bar_var -2 -2;
      if (_barpos > tmp) _barpos = int(tmp);
    }
    if (barpos != _barpos) {
      barpos = _barpos;
      update();
      updateDisplayFlag = true;
    }
  }
  
  public void setVBarPos(int _vbarpos) { // virtual barpos   
    setRBarPos(int(float(_vbarpos)*(RVratio*railpercent)));
  }
  
  public int getRBarPos() { // real barpos
    return barpos;
  }
  
  public int getVBarPos() { // virtual barpos
    return int(float(barpos)/(RVratio*railpercent));
  }
  
  public Button getButton(int index) {
    Button returner = null;
    try {
      returner = controls[index];
    } catch (Exception e) {}
    return returner;
  }
  
  public boolean probeState() {
    return isScrolling;
  }
}

/**
## SCROLLFRAME ##
**/

public class ScrollFrame extends UIObject {
  ImageBox BG, VABG, borderSF, borderVA; /* VABG will be set to same layer to be able 
         to copy it properly, and its image will be directly imaged into VA (gambiarra)*/
  PGraphics VA; PImage VAtrick;
  Scroll [] scrollbars; int [] scrollbarsflags;
  int vmargin, hmargin;
  int BBVA_w, BBVA_h, BBVA_x, BBVA_y, VA_w, VA_h;
  int scrollDisplay; // flag
  color c_fg, c_0, c_2, c_4;
  int mup, mdown, mleft, mright; // VA display margins
  int VAbsize;
  boolean updateVATransparent; /* if VA has transparent background parts
    and applet below it changes, need to update that background trickery
    (remember problem when displaying transparency/text in different PGraphics
    overlayed without cleaning up the Pgraphics on top before)
    */
  boolean VABGFillsWholeVA; // if this is true, above never needs to be true
  boolean updateScrollsRequested; int Uindex, pUindex, Usign, restore0, restore1;
  float zoom; boolean MWzoom, enableMWzoom, resetMW, scaleRequested;
  screenRegion scrollsExpanded; int eoffx, eoffy, saveMWDisplacement, deltaMWDisplacement;
  
  public ScrollFrame(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h,
                     int _VA_w, int _VA_h, int _hmargin, int _vmargin, 
                     color _c_fg, color _c_0, color _c_2, color _c_4) {
    super(_parent, _layer);
    setBoundingBox(_x, _y, _w, _h);
    VA_w = _VA_w; VA_h = _VA_h;
    scrollbars = new Scroll[2]; 
    scrollbarsflags = new int[2]; /* 0 = !display, !function
                                     1 = display && function
                                     -1 = display && !function 
                                     2 = !display && function */ 
    hmargin = _hmargin; vmargin = _vmargin;
    c_fg = _c_fg; c_0 = _c_0; c_2 = _c_2; c_4 = _c_4;
    
    setupScrollbars();
    updateVATransparent = true; VABGFillsWholeVA = false;
    updateScrollsRequested = false; pUindex = Uindex = Usign = -1; restore0 = restore1 = 0;
    
    mup = mdown = mleft = mright = 0;
    VAbsize = 0;
    updateVABBox();
    VA = createGraphics(BBVA_w, BBVA_h, P2D);
    zoom = 1; MWzoom = false; enableMWzoom = true;
    saveMWDisplacement = deltaMWDisplacement = 0;
  }
  
  public void setupScrollbars() {
    float dw, dh, railpercent;
    int f0, f1; //aux
    
    dw = max(VA_w - w, 0); dh = max(VA_h - h, 0);
    scrollbarsflags[0] = 1; scrollbarsflags[1] = 1;
    if (dw <= 0) scrollbarsflags[0] = 0;
    if (dh <= 0) scrollbarsflags[1] = 0;
    f0 = scrollbarsflags[0]; f1 = scrollbarsflags[1];
    
    railpercent = 1 - 2*vmargin/w;
    scrollbars[0] = new Scroll(parent, layer, x - f1*0.5*hmargin, y + 0.5*(h - vmargin), 
                              w - f1*hmargin, vmargin, railpercent, false, 
                              c_fg, c_0, c_2, c_4, w + dw);
    railpercent = 1 - 2*hmargin/h;
    scrollbars[1] = new Scroll(parent, layer, x + 0.5*(w - hmargin), y - f0*0.5*vmargin +1.5, // +1.5 e -1 = gambiarra
                              hmargin, h - f0*vmargin -1, railpercent, true, 
                              c_fg, c_0, c_2, c_4, h + dh);
  }
  
  public void updateVABBox() { // automatic, based on margins
    int dw, dh, xoff, yoff, _hmargin, _vmargin;
    dw = abs(scrollbarsflags[1]); dh = abs(scrollbarsflags[0]); // reuse vars
    if (dw != 1) dw = 0; if (dh != 1) dh = 0;
    _hmargin = dw*hmargin; _vmargin = dh*vmargin;
    
    dw = mleft + mright;
    dh = mup + mdown;
    xoff = mleft - mright;
    yoff = mup - mdown;
    
    updateVABBox(int(x + xoff*0.5 - _hmargin*0.5), int(y + yoff*0.5 - _vmargin*0.5), 
                 int(w - dw - 2*VAbsize - _hmargin), int(h - dh - 2*VAbsize - _vmargin));
  }
  
  public void updateVABBox(int _BBVA_x, int _BBVA_y, int _BBVA_w, int _BBVA_h) { // custom
    BBVA_x = _BBVA_x; BBVA_y = _BBVA_y; BBVA_w = _BBVA_w; BBVA_h = _BBVA_h;
    
    scrollsExpanded = new screenRegion(BBVA_w, BBVA_h);
    scrollsExpanded.addRectangle(2, 0, 0, BBVA_w, BBVA_h);
    
    eoffx = BBVA_x - BBVA_w/2; eoffy = BBVA_y - BBVA_h/2;
    scrollbars[0].setExpanded(scrollsExpanded, eoffx, eoffy);
    scrollbars[1].setExpanded(scrollsExpanded, eoffx, eoffy);
    keyboardAction();
    resetMW = true; scaleRequested = false;
  }
  
  public void keyboardAction() {
    boolean tmp = GLOBAL.getKeyInfo.ID_probeKey(37); // true if CTRL is pressed
    scrollbars[0].enableExpanded(tmp);
    scrollbars[1].enableExpanded(!tmp);
    
    MWzoom = GLOBAL.getKeyInfo.ID_probeKey(168); // true if SHIFT is pressed
    scrollbars[0].setActive(!MWzoom, false); // disable both scrolls
    scrollbars[1].setActive(!MWzoom, false); // because this will make MW zoom VA
  }
  
  public boolean probezoomfocus() {
    Point p = new Point(mx - eoffx, my - eoffy);
    return (scrollsExpanded.isPointInRegion(p) && MWzoom);
  }
  
  private void zoommwdaemon() {
    if (probezoomfocus()) { // if on focus, update
      if (resetMW) { // has just regained focus, needs to reset saved mousewheel state
        saveMWDisplacement = GLOBAL.getMouseWheelInfo.getDisplacement();
        resetMW = false;
      } else { // normal operation
        int curMWDisplacement = GLOBAL.getMouseWheelInfo.getDisplacement();
        deltaMWDisplacement = curMWDisplacement - saveMWDisplacement;
        saveMWDisplacement = curMWDisplacement;
        if (deltaMWDisplacement  != 0) {
          scaleRequested = true;
        }
      }
    } else { // has lost focus, whenever it regains, needs to reset saved mousewheel state
      resetMW = true;
    }
  }
  
  public void display() {
    keyboardAction();
    if (enableMWzoom) zoommwdaemon();
    if (scaleRequested) {
      zoom += deltaMWDisplacement*0.05;
      if (zoom < 0) zoom = 0;
      scaleRequested = false;
      updateScrollsRequested = true;
    }
    
    if (!VABGFillsWholeVA && updateVATransparent && VABG != null) {
      updateVABBox();
      VA = createGraphics(BBVA_w, BBVA_h, P2D);
      VABG.setClonableLayer(layer, true, false);
      VABG.updateBG();
      VAtrick = VABG.getBG(true);
      VAtrick.resize(VA.width, VA.height);
      updateVATransparent = false;
    }
    
    if (updateScrollsRequested) { // this could be bettered
      int f0, f1;
      float vardim0, vardim1, barpos0, barpos1, rail0, rail1; 
      f0 = f1 = 0;
      if (abs(scrollbarsflags[0]) == 1) f0 = 1;
      if (abs(scrollbarsflags[1]) == 1) f1 = 1;
      
      float v0, v1;
      v0 = scrollbars[0].getVBarPos();
      v1 = scrollbars[1].getVBarPos();
      
      barpos0 = scrollbars[0].getRBarPos();
      barpos1 = scrollbars[1].getRBarPos();
      
      vardim0 = w - f1*hmargin -2;
      vardim1 = h - f0*vmargin -2;
      rail0 = 1 - 2*(vmargin-4)/vardim0;
      rail1 = 1 - 2*(hmargin-4)/vardim1;
      scrollbars[0].x = x - f1*0.5*hmargin;
      scrollbars[0].w = vardim0;
      scrollbars[1].y = y - f0*0.5*vmargin;
      scrollbars[1].h = vardim1;
      scrollbars[0].railpercent = rail0;
      scrollbars[1].railpercent = rail1;
      scrollbars[0].VA_d = VA_w*zoom;
      scrollbars[1].VA_d = VA_h*zoom;
      scrollbars[0].setinitdims(); scrollbars[0].setUpdateDisplayFlag(true);
      scrollbars[1].setinitdims(); scrollbars[1].setUpdateDisplayFlag(true);
            
      //println("update: " + scrollbars[0].RVratio);
      
      float newpos = 0;
      // use uindex = 2 to force both bars to update
      if (Uindex != 0) { // update horizontal bar
        if (Usign < 0) {
          if (pUindex != Uindex) scrollbars[1].setRBarPos(restore1);
          
          restore0 = int(barpos0);
          newpos = v0;
          scrollbars[0].setVBarPos(int(newpos));
        } else {
          scrollbars[0].setRBarPos(restore0);
        }
      }
      if (Uindex != 1) {
        if (Usign < 0) {
          if (pUindex != Uindex) scrollbars[0].setRBarPos(restore0);
          
          restore1 = int(barpos1);
          newpos = v1;
          scrollbars[1].setVBarPos(int(newpos));
        } else {
          newpos = restore1;
          scrollbars[1].setRBarPos(restore1);
        }
      }
      
      updateScrollsRequested = false;
    }
    
    if (BG != null) {
      BG.display();
    }
    if (borderSF != null) {
      borderSF.display();
    }
    if (borderVA != null) {
      borderVA.display();
    }
    VA.beginDraw();
    if (VABG != null) {
      VA.fill(#FFFFFF);
      VA.rectMode(CORNER);
      VA.rect(0,0,VA.width, VA.height);
      VA.imageMode(CENTER);
      VA.image(VAtrick, VA.width/2, VA.height/2);
    }
    
    VA.endDraw();
    layer.imageMode(CENTER);
    layer.image(VA, BBVA_x, BBVA_y, BBVA_w, BBVA_h);
    
    if (abs(scrollbarsflags[0]) == 1) scrollbars[0].display();
    if (abs(scrollbarsflags[1]) == 1) scrollbars[1].display();
  }
  
  public void beginVADraw() {
    VA.beginDraw();
    
    //println(scrollbars[0].getVBarPos() + " //// " + scrollbars[1].getVBarPos());
    //println(scrollbars[1].RVratio);
    
    if (scrollbarsflags[0] > 0) VA.translate(-scrollbars[0].getVBarPos(), 0);
    if (scrollbarsflags[1] > 0) VA.translate(0, -scrollbars[1].getVBarPos());
    VA.scale(zoom, zoom);
  }
  
  public void endVAdraw() {
    VA.endDraw();
    layer.imageMode(CENTER);
    layer.image(VA, BBVA_x, BBVA_y, BBVA_w, BBVA_h);
  }
  
  public void mPressed() {}
  public void mReleased() {}
  public void mDragged() {}
  
  // set ImageBoxes to null to flag deactivation of their display calls
  public void setBG(ImageBox _BG) { 
    BG = _BG;
  }
  
  public void setBorderSF(ImageBox _borderSF) {
    borderSF = _borderSF;
  }
  
  public void setBorderVA(ImageBox _borderVA) {
    borderVA = _borderVA;
  }
  
  public void setVABG() { // automatic, based on margins
    updateVABBox();
    VABG = new ImageBox(this.parent, layer, BBVA_x, BBVA_y, BBVA_w, BBVA_h, color(0, 0, 1, 0), 0, 0);
    VABG.setClonableLayer(layer, true, false);
  }
  
  public void setVABG(ImageBox _VABG) { //custom
    VABG = _VABG;
    if (VABG != null) VABG.setLayer(VA);
  }
  
  // build one of the ImageBox's border types
  public void setBorderSF(int _SFbsize, color bcolor, float spercent, float lpercent, int angle, int balpha, int btype) { 
    margin = _SFbsize;
    borderSF = new ImageBox(this.parent, layer, x, y, w, h, color(0, 0, 1, 0), 0, 0);
    borderSF.setBorder(int(margin), bcolor, spercent, lpercent, 0, 255, 2);
  }
  
  public void setBorderVA(int _VAbsize, color bcolor, float spercent, float lpercent, int angle, int balpha, int btype) {
    VAbsize = _VAbsize;
    updateVABBox();
    borderVA = new ImageBox(this.parent, layer, BBVA_x, BBVA_y, BBVA_w, BBVA_h, color(0, 0, 1, 0), 0, 0);
    borderVA.setBorder(VAbsize, bcolor, spercent, lpercent, 0, 255, 2);
  }
  
  public void setVABGFillsWholeVA(boolean _VABGFillsWholeVA) {
    VABGFillsWholeVA = _VABGFillsWholeVA; /* must be set manually since one can have
    a ImageBox whose bounding box appears to fill whole VA but actually has image only
    at a part (transparency or image fit to boundingbox maintaining aspect ratio)
    */
  }
  
  public void setUpdateVATransparent(boolean _updateVATransparent) {
    updateVATransparent = _updateVATransparent;
  }
  
  public void setVAMargins(int _mleft, int _mright, int _mup, int _mdown) {
    mleft = _mleft; mright = _mright; 
    mup = _mup; mdown = _mdown;
  }
  
  public void setScrollState(int _index, boolean _display, boolean _function, boolean _force) {
    // _force cancels scrolling and forces to hide even while scrolling
    int newflag, testflag, oldflag, tmp;
    tmp = abs(_index);
    
    if (tmp < 2) {
      newflag = 2; // false, true -> flag = 2
      if (_display) newflag = 1; // true, true -> flag = 1
      if (!_function) newflag -= 2; // true, false -> flag = -1 ; false, false -> flag = 0
    
      oldflag = abs( abs(scrollbarsflags[tmp]) - 1 ); // 0 now is display
      testflag = abs( abs(newflag) - 1 ); // 1 now is !display
      
      if (oldflag > testflag) { // if invoking
        scrollbarsflags[tmp] = newflag;
        Usign = -1;
      } else if (oldflag < testflag) { // excluding == on purpose
        Usign = +1;
        if (_force) {
          scrollbars[tmp].stopScroll();
          scrollbarsflags[tmp] = newflag;
        }
        else {
          if (!scrollbars[tmp].probeState()) scrollbarsflags[tmp] = newflag;
        }
      }
      
      if (scrollbarsflags[tmp] <= 0) scrollbars[tmp].setActive(false, false);
      else scrollbars[tmp].setActive(true, false);
      
      newflag = abs( abs(scrollbarsflags[tmp]) - 1 ); // if indeed it changed
      if (oldflag != newflag) { // transformed flags, different now means that display state has changed
        updateVABBox();
        if (borderVA != null) {
          borderVA.setBoundingBox(BBVA_x, BBVA_y, BBVA_w, BBVA_h);
          borderVA.updateBG();
        }
        if (VABG != null) {
          VABG.setBoundingBox(BBVA_x, BBVA_y, BBVA_w, BBVA_h);
          updateVATransparent = true;
        }
        updateScrollsRequested = true; 
        pUindex = Uindex; Uindex = tmp;
      }
    }
  }
  
  public Scroll getScrollObj(int index) {
    Scroll returner = null;
    try {
      returner = scrollbars[index];
    } catch (Exception e) {}
    return returner;
  }
  
  public PGraphics getVA() { // reference to VA in order to draw on it
    return VA;
  }
}


/* ideas:

  TEXTBOX ---|> FIELD ---|> NUMBERFIELD
  FIELD: HLocked but !WLocked, so when user tipes, number of lines is locked to 1 but that line can be infinite. The part of the line to be shown
depends on settgins: default: only show beggining when FIELD loses focus // other option: last position cursor has stopped (not necessarily the end)
  NUMBERFIELD: a field but with WLocked too; number of significant digits is locked; while typing it may behave as FIELD if set so, but after all 
it always rounds and truncates/expands with zeroes to the limit of significant digits. NUMBERFIELD set to arbitrary precision in floating point mode
+ KNOB = DDIMMER (DIGITALDIMMER)
*/
