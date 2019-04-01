/**
## GAUGE ##

Bar or ellipse gauge, with fill only, with ticks only or with both.
Backgrounds for AnalogDimmer
**/

class Gauge extends UIObject {
  float oldw, oldh; float trickx, tricky;
  float sangle1, sangle2, tangle1, tangle2, s_sw, s_sh, t_sw, t_sh; // for ellipse gauge

  int mode_shape; // bar horizontal positive ; bar horizontal negative ; 
  // bar vertical positive ; bar vertical negative ; 
  // ellipse cw ; ellipse ccw
  SPoint bardir;

  int mode_ticks; // no ticks, no fill ; no ticks, fill ; ticks, no fill ; ticks, fill
  int shape_resolution; // number of points in circular shape
  int ticks_resolution; // number of ticks in scale

  float pos, offset, screenbufoffx, screenbufoffy; // pos can be an angle or a length (depends whether the gauge is circular or bar)
  float min_value, max_value; // values mapped to gauge scale+

  boolean redrawTicksRequested; // flag to redraw all ticks only once to offscreen TicksBuffer (when a tick is added, modified or removed)
  PGraphics offscreenTicksBuffer; // draw ticks here only when needed, display image (avoid unnecessary redraw)
  boolean redrawShapeRequested; // flag to redraw shape only once to offscreen ShapeBuffer (when a tick is added, modified or removed)
  PGraphics offscreenShapeBuffer; // draw shape here only when needed, display image (avoid unnecessary redraw)
  private ArrayList ticks; // stores the ticks. Ticks must be inserted in order (ID has the same order as pos)

  ArrayList ticktemplates_pts, ticktemplates_len, multipliers;

  SPoint [] curtickpoints; // current tick values (built from template + pos + scaleratio )
  float [] curticklengths;

  float dtheta, sint, cost; // rotation (coupled with on-screen detection of ticks)

  ArrayList ticks_colourProfiles, shape_colourProfiles;
  ArrayList bckp_shape_colourProfiles, bckp_ticks_colourProfiles;
  color [] ticks_colours;

  PGraphics ticksMapScreenBuffer;

  //I/O
  boolean watchDrag;
  float cur_IOfloatid, prv_IOfloatid;

  public Gauge(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, float _ticksizepercent, 
  float [] _tickautocoords, float [] _ticklengths, 
  int _mode_shape, int _mode_ticks, int _shape_resolution, int _ticks_resolution, 
  float _min_value, float _max_value) {
    super(_parent, _layer);
    
    SPoint [] _points = new SPoint[_tickautocoords.length];
    float [][] _scaled;
    int i;

    _scaled = scaleCoords(_tickautocoords, _ticklengths, _ticksizepercent*_h);
    switch(abs(_mode_shape)) {
    case 2:
      for (i = 0; i < _points.length; i++) _points[i] = new SPoint(0, _scaled[0][i]);
      break;
    case 3:
      _scaled = scaleCoords(_tickautocoords, _ticklengths, _ticksizepercent*_w);
      for (i = 0; i < _points.length; i++) _points[i] = new SPoint(_scaled[0][i], 0);
      break;
    }

    gaugeinit(_x, _y, _w, _h, _points, _scaled[1], _mode_shape, _mode_ticks, 
    _shape_resolution, _ticks_resolution, _min_value, _max_value);
  }

  public Gauge(PApplet _parent, PGraphics _layer, float _x, float _y, float _dim, 
  float [] _tickautocoords, float [] _ticklengths, 
  float _angle1, float _angle2, float _s_sdim, float _t_sdim, 
  int _mode_shape, int _mode_ticks, int _shape_resolution, int _ticks_resolution, 
  float _min_value, float _max_value) {
    // circular gauge (elliptical is not really used and was being too much of a hassle
    super(_parent, _layer);
    
    SPoint [] _points = new SPoint[_tickautocoords.length];
    int i;

    sangle1 = tangle1 = _angle1; 
    sangle2 = tangle2 = _angle2;

    if (abs(_angle2 - _angle1) < 360) { // open arc, rewrite so that last tick/quad is in the right place
      tangle2 += (_angle2 - _angle1) / (_ticks_resolution - 1);
    }

    // assumes tickpercent is 100% for the default tick template (0)
    s_sw = s_sh = _s_sdim; 
    t_sw = t_sh = _t_sdim;

    // points and lengths here are still dummy. Will be scaled properly in addAutoTicks
    for (i = 0; i < _points.length; i++) _points[i] = new SPoint(_tickautocoords[i], 0);
    gaugeinit(_x, _y, _dim, _dim, _points, _ticklengths, _mode_shape, _mode_ticks, 
    _shape_resolution, _ticks_resolution, _min_value, _max_value);
  }

  private void gaugeinit(float _x, float _y, float _w, float _h, SPoint [] _tickpoints, float [] _ticklengths, 
  int _mode_shape, int _mode_ticks, int _shape_resolution, int _ticks_resolution, 
  float _min_value, float _max_value) {
    int i, infosize;
    
    setBoundingBox(_x, _y, _w, _h);
    oldw = _w; oldh = _h; trickx = tricky = 0;

    prv_IOfloatid = cur_IOfloatid = 0;
    watchDrag = false;

    infosize = min(_tickpoints.length, _ticklengths.length);
    SPoint [] atickpoints = new SPoint[infosize]; //copy for more safety
    float [] aticklengths = new float[infosize];
    curtickpoints = new SPoint[infosize];
    curticklengths = new float[infosize];

    for (i = 0; i < infosize; i++) {
      atickpoints[i] = new SPoint(_tickpoints[i]);
      curtickpoints[i] = new SPoint(_tickpoints[i]); // start values
      aticklengths[i] = _ticklengths[i];
      curticklengths[i] = _ticklengths[i];
    }

    ticktemplates_pts = new ArrayList(0);
    ticktemplates_len = new ArrayList(0);
    ticktemplates_pts.add(0, atickpoints);
    ticktemplates_len.add(0, aticklengths);
    multipliers = new ArrayList(0);
    multipliers.add(0, 1.0);

    ticks = new ArrayList(0);
    mode_shape = _mode_shape; 
    mode_ticks = _mode_ticks;
    shape_resolution = _shape_resolution; 
    ticks_resolution = _ticks_resolution;

    min_value = _min_value; 
    max_value = _max_value;
    if (mode_shape < 0) {
      float tmp;
      tmp = min_value;
      min_value = max_value;
      max_value = tmp;
    }

    dtheta = 0; cost = 1; sint = 0;

    ticks_colourProfiles = new ArrayList(0);
    shape_colourProfiles = new ArrayList(0);
    bckp_shape_colourProfiles = new ArrayList(0);
    bckp_ticks_colourProfiles = new ArrayList(0);
    ticks_colours = new color[ticks_resolution];

    reinitTicksScreenBuffer();
    reinitTicksBuffer();
    reinitShapeBuffer();
  }

  void display() { 
    // must call fillTicksBuffer before to build shapes. This only displays final state of TicksBuffer
    //offscreenTicksBuffer.noFill();
    //offscreenTicksBuffer.stroke(#FF00FF);
    //offscreenTicksBuffer.rect(1, 1, offscreenTicksBuffer.width-1, offscreenTicksBuffer.height-1);

    pushMatrix();
    translate(x, y);
    rotate(dtheta);
    //translate(dx, dy);
    imageMode(CENTER);
    if (mode_ticks % 2 != 0) image(offscreenShapeBuffer, 0, 0);
    if (mode_ticks > 1) image(offscreenTicksBuffer, 0, 0);
    popMatrix();
  }

  /* handling of shape */

  void updateShapeBuffer() {
    if (mode_ticks % 2 == 0) return;

    int i, j, signal;
    float d, endpos, startpos;
    float curx, cury, tmp;
    float curx2, cury2, tmp2, tmp3, tmp4;
    color curcolour;

    resetShapeBuffer();
    signal = (mode_shape >> 31) | 1;
    offscreenShapeBuffer.beginDraw();
    offscreenShapeBuffer.strokeWeight(2);
    switch(abs(mode_shape)) {
    case 2:
      tmp = (1-signal)/2*oldw;
      for (j = 0; j < shape_colourProfiles.size(); j++) {
        colourStorage cur = getShapeColourProfile(j);

        endpos = oldw*cur.endPercent();
        startpos = oldw*cur.startPercent();

        offscreenShapeBuffer.beginShape(QUAD_STRIP);
        for (i = 0; i < shape_resolution; i++) {
          d = map(i, 0, shape_resolution-1, startpos, endpos);

          curcolour = cur.interpColour( float(i)/(float(shape_resolution)) );

          if (i > 0 && i < shape_resolution-1) offscreenShapeBuffer.stroke(curcolour);
          else offscreenShapeBuffer.noStroke();
          offscreenShapeBuffer.fill(curcolour);

          curx = 2 + tmp + signal*d;

          offscreenShapeBuffer.vertex(curx, 4); // +4 because of the tick stroke
          offscreenShapeBuffer.vertex(curx, oldh+4); // +4 because of the tick stroke
        }
        offscreenShapeBuffer.endShape();
      }
      break;
    case 3:
      tmp = (1+signal)/2*oldh;
      for (j = 0; j < shape_colourProfiles.size(); j++) {
        colourStorage cur = getShapeColourProfile(j);

        endpos = oldh*cur.endPercent();
        startpos = oldh*cur.startPercent();

        offscreenShapeBuffer.beginShape(QUAD_STRIP);
        for (i = 0; i < shape_resolution; i++) {
          d = map(i, 0, shape_resolution-1, startpos, endpos); 

          curcolour = cur.interpColour( float(i)/(float(shape_resolution)) );
          if (i > 0 && i < shape_resolution-1) offscreenShapeBuffer.stroke(curcolour);
          else offscreenShapeBuffer.noStroke();
          offscreenShapeBuffer.fill(curcolour);

          cury = 2 + tmp - signal*d;

          offscreenShapeBuffer.vertex(4, cury); // +4 because of the tick stroke
          offscreenShapeBuffer.vertex(oldw+4, cury); // +4 because of the tick stroke
        }
        offscreenShapeBuffer.endShape();
      }
      break;
    case 1:
      offscreenShapeBuffer.pushMatrix();
      offscreenShapeBuffer.translate(offscreenShapeBuffer.width/2, offscreenShapeBuffer.height/2);
      tmp = s_sw/2;
      tmp2 = s_sh/2;
      tmp3 = oldw/2;
      tmp4 = oldh/2;

      for (j = 0; j < shape_colourProfiles.size(); j++) {
        colourStorage cur = getShapeColourProfile(j);

        endpos = sangle1 + (sangle2 - sangle1)*cur.endPercent();
        startpos = sangle1 + (sangle2 - sangle1)*cur.startPercent();

        offscreenShapeBuffer.beginShape(QUAD_STRIP);
        for (i = 0; i < shape_resolution; i++) {
          d = -signal*radians(map(i, 0, shape_resolution-1, startpos, endpos));

          float cosd = cos(d);
          float sind = sin(d);

          curcolour = cur.interpColour( float(i)/(float(shape_resolution)) );
          if (i > 0 && i < shape_resolution-1) offscreenShapeBuffer.stroke(curcolour);
          else offscreenShapeBuffer.noStroke();
          offscreenShapeBuffer.fill(curcolour);

          curx =  tmp*cosd;
          cury = tmp2*sind;
          curx2 = tmp3*cosd;
          cury2 = tmp4*sind;
          offscreenShapeBuffer.vertex(curx, cury);
          offscreenShapeBuffer.vertex(curx2, cury2);
        }
        offscreenShapeBuffer.endShape();
      }

      offscreenShapeBuffer.popMatrix();
      break;
    }
    offscreenShapeBuffer.endDraw();
  }

  void reinitShapeBuffer() {
    offscreenShapeBuffer = createGraphics(int(oldw) + 4, int(oldh) + 4, P2D);
  }

  void resetShapeBuffer() {
    offscreenShapeBuffer.beginDraw();
    offscreenShapeBuffer.background(0, 0);
    offscreenShapeBuffer.endDraw();
  }

  /* handling of ticks */

  float [] scaleCoords(float _min_coord, float _max_coord, float _cur_coord, 
  float _length, SPoint _p1, SPoint _p2) {
    /* 1 step, using directingvector */
    float ratio;
    int i;
    float [] returner = new float[3];
    SPoint directvec;

    directvec = new SPoint(_p2.x - _p1.x, _p2.y - _p1.y);
    ratio = (_cur_coord - _min_coord) / (_max_coord - _min_coord);

    returner[2] = _p1.distTo(_p2)/(_max_coord - _min_coord)*_length;
    returner[0] = _p1.x + ratio*directvec.x; 
    returner[1] = _p1.y + ratio*directvec.y;

    return returner;
  }

  float [][] scaleCoords(float [] _origCoords, float [] _origLengths, float _axisAmplitude) {
    /* all steps, constant axisAmplitude. Repeting code to save CPU */
    float origAmplitude, ratio;
    int i;
    int minsize = max(0, min(_origCoords.length, _origLengths.length));
    float [][] returner = new float[2][minsize];

    origAmplitude = abs(_origCoords[minsize-1] - _origCoords[0]);
    ratio = _axisAmplitude / origAmplitude;

    for (i = 0; i < minsize; i++) {
      returner[0][i] = (_origCoords[i] - _origCoords[0])*ratio + _origCoords[0];
      returner[1][i] = _origLengths[i]*ratio;
    }

    return returner;
  }

  int value2template(float _value) {
    // returns the index of point template to use.
    // Pattern of this implementation (can be overriden by subclass):
    // value % 10 == 0 -> template 2
    // value % 5 == 0 -> template 1
    // others: template 0

    // assumes point templates have been already filled by setters
    // but has simple error handling against empty templates:
    // defaults to biggest template index available
    int returner = 0;

    switch(int(_value % 10)) {
    case 0:
      returner = 2; 
      break;
    case 5:
      returner = 1; 
      break;
    default:
      returner = 0; 
      break;
    }

    returner = min(returner, ticktemplates_pts.size()-1);

    return returner;
  }

  SPoint [] id2pos_ell(float _id, SPoint _origin, int _signal, int _templateIndex) { // ellipse (polar)
    // this implementation assumes equally spaced radial positioning. Can be overriden by subclass
    float [] ticklengths = (float []) ticktemplates_len.get(_templateIndex);
    SPoint [] tickpoints = (SPoint []) ticktemplates_pts.get(_templateIndex);

    int arraysize = ticklengths.length;
    SPoint [] returner = new SPoint[arraysize];
    float d, cosd, sind, tmp1, tmp2, tmp3, tmp4, multiplier;
    int j;

    multiplier = (Float) multipliers.get(_templateIndex);
    tmp1 = oldw/2;
    tmp2 = oldh/2;
    tmp3 = (2 - multiplier) * t_sw/2;
    tmp4 = (2 - multiplier) * t_sh/2;

    // --- the math behind this implementation (linear interpolation) is below ---
    d = -_signal*radians(map(_id, 0, ticks_resolution, tangle1, tangle2));
    cosd = cos(d); 
    sind = sin(d);
    // build tick main axis below:
    returner[0] = new SPoint(offset + (1 + cosd)*tmp1, offset + (1 + sind)*tmp2);
    returner[returner.length-1] = new SPoint(tmp1 + offset + cosd*tmp3, tmp2 + offset + sind*tmp4);
    // --- end of math ---

    for (j = 1; j < curtickpoints.length -1; j++) {
      float [] coords = scaleCoords(tickpoints[0].x, tickpoints[arraysize-1].x, 
      tickpoints[j].x, ticklengths[j], returner[0], returner[arraysize-1]);

      returner[j] = new SPoint(coords[0], coords[1]);
    }

    return returner;
  }

  SPoint id2pos_bar(float _id, SPoint _dirvec, SPoint _origin, float _step, SPoint _templatept) { // bar (cartesian)
    // this implementation assumes linear positioning. Can be overriden by subclass
    SPoint returner;

    returner = new SPoint(_origin);
    returner.x += (_dirvec.x*_id*_step + _templatept.x); // templatept must be centered
    returner.y += (_dirvec.y*_id*_step + _templatept.y);

    return returner;
  }
  SPoint value2pos_bar(float value, SPoint _dirvec, SPoint _origin, 
  float screenDimention, SPoint _templatept) {
    // this implementation assumes linear positioning. Can be overriden by subclass
    int signal = (mode_shape >> 31) | 1;
    float pos = (value - min_value) / (max_value - min_value) * screenDimention;
    SPoint returner;

    returner = new SPoint(_origin);
    returner.x += (_dirvec.x*pos + _templatept.x); // templatept must be centered
    returner.y += (_dirvec.y*pos + _templatept.y);
    return returner;
  }

  float id2value(float _id, float _lowerBound, float _upperBound, int _resolution) {
    // this implementation assumes linear scale. Can be overriden by subclass
    return ( _lowerBound + _id*(_upperBound - _lowerBound) / (_resolution -1) );
  }

  SPoint [] centreTickPoints(SPoint [] _tickpoints) {
    float offx, offy;
    SPoint [] returner = new SPoint[_tickpoints.length];
    int i;

    offx = offy = 0; // will store baricentre coords
    for (i = 0; i < _tickpoints.length; i++) {
      offx -= _tickpoints[i].x;
      offy -= _tickpoints[i].y;
    }
    offx /= _tickpoints.length;
    offy /= _tickpoints.length;
    for (i = 0; i < _tickpoints.length; i++) {
      returner[i] = new SPoint(_tickpoints[i].x + offx, _tickpoints[i].y + offy);
    }

    return returner;
  }

  void addAutoTicks() {
    if (mode_ticks < 2) return;

    int i, j;
    SPoint origin;
    int signal = (mode_shape >> 31) | 1;
    int templateIndex;
    float pos_increment;

    origin = new SPoint(offset + oldw/2, offset + oldh/2); // centre point
    SPoint [][] tickpoints = new SPoint[ticktemplates_pts.size()][];

    for (i = 0; i < ticktemplates_pts.size(); i++) {
      tickpoints[i] = (SPoint []) ticktemplates_pts.get(i);
      tickpoints[i] = centreTickPoints(tickpoints[i]);
    }

    fillTicksColours();

    switch(abs(mode_shape)) {
    case 1: // ellipse
      for (i = 0; i < ticks_resolution; i++) {
        float value = id2value(i, min_value, max_value, ticks_resolution);
        templateIndex = value2template(value);
        curtickpoints = id2pos_ell(i, origin, signal, templateIndex);

        addTick_lowlvl(false, value, 0);
      }
      break;
    case 2: // bar horizontal
      pos_increment = oldw/(ticks_resolution -1);
      origin.x -= signal*oldw/2; // start point
      bardir = new SPoint(signal, 0);
      for (i = 0; i < ticks_resolution; i++) {
        float value = id2value(i, min_value, max_value, ticks_resolution);
        templateIndex = value2template(value);

        for (j = 0; j < curtickpoints.length; j++) {
          curtickpoints[j] = id2pos_bar(i, ( bardir ), origin, 
          pos_increment, tickpoints[templateIndex][j]);
        }

        addTick_lowlvl(false, value, 0);
      }
      break;
    case 3: // bar vertical
      pos_increment = oldh/(ticks_resolution -1);
      origin.y += signal*oldh/2; // start point
      bardir = new SPoint(0, -signal);
      for (i = 0; i < ticks_resolution; i++) {
        float value = id2value(i, min_value, max_value, ticks_resolution);
        templateIndex = value2template(value);

        for (j = 0; j < curtickpoints.length; j++) {
          curtickpoints[j] = id2pos_bar(i, ( bardir ), origin, 
          pos_increment, tickpoints[templateIndex][j]);
        }

        addTick_lowlvl(false, value, 0);
      }
      break;
    }
  }

  private void addTick_lowlvl(boolean _isCustom, float _value, color _colour) { 
    /* add tick at low level, in order. With previously set curtickpoints
     Assumes all ticks were added in order 
     arraylist ticks is private to avoid adding out of order; to force add methods
     to be used. They all eventually get to this method, that is why this is low-level */
    int i, compare, signal;
    tick cur;
    float [] placeholder; 
    // trickery to avoid branching at if (a < b) inside loop to find where to add tick
    color [] curcolour; 
    int flag;
    // trickery to avoid branching at loop to find determine whether to use custom colour or colour array
    if (_isCustom) {
      curcolour = new color[1];
      curcolour[0] = _colour;
      flag = 0;
    } 
    else {
      curcolour = ticks_colours;
      flag = 1;
    }

    if (ticks.size() == 0) {
      ticks.add(new tick(offscreenTicksBuffer, curtickpoints, curticklengths, 
      curcolour[0], _value, _isCustom)); 
      //println(0 + " // " + _value);
    }

    placeholder = new float[2];

    signal = (mode_shape >> 31) | 1;
    compare = (signal + 1) / 2;
    // no-branching equivalent to: compare = (mode_shape > 0) ? (1) : (0) ;
    placeholder[compare] = _value;

    for (i = ticks.size() - 1; i >= 0; i--) {
      cur = (tick) ticks.get(i);

      placeholder[1-compare] = cur.getValue();

      if (placeholder[0] < placeholder[1]) {
        ticks.add(i+1, new tick(offscreenTicksBuffer, curtickpoints, curticklengths, 
        curcolour[flag*(i+1)], _value, _isCustom));
        break;
      }
    }
  }

  void addCustomTick(float value, int templateIndex, color _colour) { 
    // tick with an existing template but custom value and colour
    int i;
    int signal = (mode_shape >> 31) | 1;
    SPoint origin;
    SPoint [][] tickpoints = new SPoint[ticktemplates_pts.size()][];

    for (i = 0; i < ticktemplates_pts.size(); i++) {
      tickpoints[i] = (SPoint []) ticktemplates_pts.get(i);
      tickpoints[i] = centreTickPoints(tickpoints[i]);
    }

    origin = new SPoint(offset + oldw/2, offset + oldh/2); // centre point

    switch(abs(mode_shape)) {
    case 1: // ellipse
      curtickpoints = id2pos_ell(map(value, min_value, max_value, 0, ticks_resolution-1), origin, signal, templateIndex);
      addTick_lowlvl(true, value, _colour);
      break;
    case 2: // bar horizontal
      origin.x -= signal*oldw/2; // start point
      for (i = 0; i < curtickpoints.length; i++) {
        curtickpoints[i] = value2pos_bar(value, (new SPoint(signal, 0)), 
        origin, oldw, tickpoints[templateIndex][i]);
      }
      addTick_lowlvl(true, value, _colour);
      break;
    case 3: // bar vertical
      origin.y += signal*oldh/2; // start point
      for (i = 0; i < curtickpoints.length; i++) {
        curtickpoints[i] = value2pos_bar(value, (new SPoint(0, -signal)), 
        origin, oldh, tickpoints[templateIndex][i]);
      }
      addTick_lowlvl(true, value, _colour);
      break;
    }
  }

  void clearAllTicks() {
    ticks.clear();
  }

  void clearAllTemplates() {
    ticktemplates_pts.clear();
    ticktemplates_len.clear();
  }

  void resetTicksScreenBuffer() {
    ticksMapScreenBuffer.beginDraw();
    ticksMapScreenBuffer.background(0);
    ticksMapScreenBuffer.endDraw();
  }

  void reinitTicksScreenBuffer() {
    int q = (abs(mode_shape) > 1) ? (round( sqrt(oldw*oldw + oldh*oldh) )) : ( round(3*max(oldw, oldh) - 2*min(t_sw, t_sh)) ) ;
    ticksMapScreenBuffer = createGraphics(q, q, P2D);
  }

  void resetTicksBuffer() {
    offscreenTicksBuffer.beginDraw();
    offscreenTicksBuffer.background(0, 0);
    offscreenTicksBuffer.endDraw();
  }

  void reinitTicksBuffer() {
    float maxlength; // of all templates

    maxlength = 0;

    for (int j = 0; j < ticktemplates_len.size(); j++) {
      float [] ticklengths = (float []) ticktemplates_len.get(j);

      for (int i = 0; i < ticklengths.length; i++) {
        maxlength = max(maxlength, ticklengths[i]);
      }
    }

    offset = maxlength + 1;
    offscreenTicksBuffer = createGraphics(int(oldw + 2*offset), int(oldh + 2*offset), P2D);

    for (int i = 0; i < ticks.size(); i++) {
      tick cur = (tick) ticks.get(i);
      cur.setLayer(offscreenTicksBuffer);
    }
  }

  void fillTicksBuffer() {
    if (mode_ticks % 2 == 0) return;

    int i;
    tick cur;

    for (i = 0; i < ticks.size(); i++) {
      cur = (tick) ticks.get(i);
      cur.requestUpdate(2);
      cur.update();
    }
  }
  
  void fillTicksScreenBuffer() {
    if (abs(mode_shape) > 1) {
      fillTicksScreenBuffer_bar();
    } else {
      fillTicksScreenBuffer_ell();
    }
  }

  void fillTicksScreenBuffer_ell() {
    if (mode_ticks % 2 == 0) return;

    int i, _zx, _zy, signal;
    tick curt, prvt;
    SPoint curd, curz;
    SPoint [] plist;
    float tmp1, tmp2, m, mul;
    float ptmp1, ptmp2;
    SPoint prvz, prvd;

    signal = (mode_shape >> 31) | 1;
    mul = 0;
    for (i = 0; i < multipliers.size(); i++) {
      mul = max(mul, (Float) multipliers.get(i));
    }
    mul = 2 - mul;
    m = max(oldw, oldh) - min(t_sw, t_sh);
    screenbufoffx = screenbufoffy = m;
    _zx = ticksMapScreenBuffer.width/2;
    _zy = ticksMapScreenBuffer.height/2;

    ticksMapScreenBuffer.beginDraw();
    ticksMapScreenBuffer.background(0);
    ticksMapScreenBuffer.strokeWeight(1);

    curt = (tick) ticks.get(0);
    curd = curt.dirAxis();
    curz = curt.getCentre();
    curz.x += m;
    curz.y += m;
    tmp1 = curd.x*m*3; 
    tmp2 = curd.y*m*3;
    for (i = 1; i < ticks.size(); i++) {
      curt = (tick) ticks.get(i);
      prvd = curd;
      curd = curt.dirAxis();
      prvz = curz;
      curz = curt.getCentre();
      curz.x += m;
      curz.y += m;
      ptmp1 = tmp1; 
      ptmp2 = tmp2;
      tmp1 = curd.x*m*3; 
      tmp2 = curd.y*m*3;

      ticksMapScreenBuffer.stroke(0xFFFFFFFF - i);
      ticksMapScreenBuffer.fill(0xFF000000 + i);
      ticksMapScreenBuffer.beginShape(TRIANGLES);
      ticksMapScreenBuffer.vertex(_zx, _zy);
      ticksMapScreenBuffer.vertex(prvz.x - 3*ptmp1, prvz.y - 3*ptmp2);
      ticksMapScreenBuffer.vertex(curz.x - 3*tmp1, curz.y - 3*tmp2);
      ticksMapScreenBuffer.endShape();
    }

    ticksMapScreenBuffer.endShape();

    tmp2 = sqrt(_zx*_zx + _zy*_zy);
    ticksMapScreenBuffer.fill(0);
    ticksMapScreenBuffer.noStroke();
    ticksMapScreenBuffer.ellipse(_zx, _zy, t_sw*mul, t_sh*mul);
    ticksMapScreenBuffer.noFill();
    ticksMapScreenBuffer.stroke(0);
    ticksMapScreenBuffer.strokeWeight(tmp2);
    ticksMapScreenBuffer.ellipse(_zx, _zy, oldw+tmp2, oldh+tmp2);
    ticksMapScreenBuffer.endDraw();
  }

  void fillTicksScreenBuffer_bar() {
    int i;
    tick curt, prvt;
    SPoint curd, curz, prvd, prvz, p1, p2, p3, p4;
    float m, _zx, _zy, k1, k2;

    ticksMapScreenBuffer.beginDraw();
    ticksMapScreenBuffer.background(0);
    ticksMapScreenBuffer.strokeWeight(1);

    curt = (tick) ticks.get(0);
    curd = curt.dirAxis();
    curz = curt.getCentre();
    m = curd.x*oldw + curd.y*oldh;
    _zx = ticksMapScreenBuffer.width/2;
    _zy = ticksMapScreenBuffer.height/2;

    k1 = sqrt(_zx*_zx + _zy*_zy);
    k2 = (m + k1)/2;
    p1 = new SPoint(curz);
    p1.x -= k2*curd.x + 10*bardir.x; 
    p1.y -= k2*curd.y + 10*bardir.y;
    p2 = new SPoint (( (tick) ticks.get(ticks.size()-1) ).getCentre() );
    p2.x -= k2*curd.x - 10*bardir.x; 
    p2.y -= k2*curd.y - 10*bardir.y;
    p3 = new SPoint(curz);
    p3.x += k2*curd.x - 10*bardir.x; 
    p3.y+= k2*curd.y - 10*bardir.y;
    p4 = new SPoint (( (tick) ticks.get(ticks.size()-1) ).getCentre() );
    p4.x += k2*curd.x + 10*bardir.x; 
    p4.y += k2*curd.y + 10*bardir.y;

    ticksMapScreenBuffer.translate(_zx*(1 - bardir.x), _zy*(1 - bardir.y));
    screenbufoffx = curd.x*_zx; 
    screenbufoffy = curd.y*_zy;

    for (i = 1; i < ticks.size(); i++) {
      ticksMapScreenBuffer.stroke(0xFFFFFFFF - i);
      ticksMapScreenBuffer.fill(0xFF000000 + i);
      prvt = curt;
      curt = (tick) ticks.get(i);
      prvd = curd;
      curd = curt.dirAxis();
      prvz = curz;
      curz = curt.getCentre();

      ticksMapScreenBuffer.rectMode(CORNERS);
      ticksMapScreenBuffer.rect(
      prvz.x - prvd.x*m, prvz.y - prvd.y*m, 
      curz.x + curd.x*m, curz.y + curd.y*m);
    }

    ticksMapScreenBuffer.noFill();
    ticksMapScreenBuffer.stroke(0);
    ticksMapScreenBuffer.strokeWeight(k1);
    ticksMapScreenBuffer.line(p1.x, p1.y, p2.x, p2.y);
    ticksMapScreenBuffer.line(p3.x, p3.y, p4.x, p4.y);
    ticksMapScreenBuffer.endDraw();
  }

  int getNumberOfTicks() {
    return ticks.size();
  }

  tick getTickReference(int _index) {
    tick returner = null;
    try {
      returner = (tick) ticks.get(_index);
    } 
    catch (Exception e) {
    }
    return returner;
  }

  void addTickTemplate(SPoint [] _tickpoints, float [] _ticklengths) {
    addTickTemplate(_tickpoints, _ticklengths, ticktemplates_pts.size());
  }

  void addTickTemplate(SPoint [] _tickpoints, float [] _ticklengths, int _index) {
    int i, infosize;

    infosize = min(_ticklengths.length, _tickpoints.length);
    SPoint [] atickpoints = new SPoint[infosize];
    float [] aticklengths = new float[infosize];

    for (i = 0; i < infosize; i++) { // copy for safety
      atickpoints[i] = new SPoint(_tickpoints[i]);
      float tmp = _ticklengths[i];
      aticklengths[i] = tmp;
    }

    int index = min(_index, ticktemplates_pts.size());
    ticktemplates_pts.add(index, atickpoints);
    ticktemplates_len.add(index, aticklengths);
    multipliers.add(index, 1.0);
  }

  void setTemplateMultiplier(float _multiplier, int _index) {
    try {
      multipliers.set(_index, _multiplier);

      if (abs(mode_shape) > 1) {
        int i;
        SPoint [] tickpts = (SPoint []) ticktemplates_pts.get(_index); // assumes coords are on x
        float [] tickcoords = new float[tickpts.length];
        for (i = 0; i < tickcoords.length; i++) tickcoords[i] = tickpts[i].x;
        float [] ticklens = (float []) ticktemplates_len.get(_index);
        SPoint [] _points = new SPoint[tickcoords.length];
        float [][] _scaled = new float[1][1];


        switch(abs(mode_shape)) {
        case 2:
          _scaled = scaleCoords(tickcoords, ticklens, _multiplier*oldh);
          for (i = 0; i < _points.length; i++) _points[i] = new SPoint(0, _scaled[0][i]);
          break;
        case 3:
          _scaled = scaleCoords(tickcoords, ticklens, _multiplier*oldw);
          for (i = 0; i < _points.length; i++) _points[i] = new SPoint(_scaled[0][i], 0);
          break;
        }

        ticktemplates_pts.set(_index, _points);
        ticktemplates_len.set(_index, _scaled[1]);
      }
    } 
    catch (Exception e) {
    }
  }

  void rotateDeg(float _dtheta) { // rotate in degrees
    rotateRad(radians(_dtheta));
  }
  void rotateRad(float _dtheta) {
    dtheta = _dtheta;
    sint = sin(dtheta);
    cost = cos(dtheta);
    float [] newDims = rotateBoundingBox(oldw, oldh, dtheta);
    setBoundingBox(x, y, newDims[0], newDims[1]);
  }
  float getRotationDeg() {
    return degrees(dtheta);
  }
  float getRotationRad() {
    return dtheta;
  }

  class tick {
    /*
     scale tick, can have a very wide range of shapes; specify 2 extreme points that define a main axis and
     as many mid-points as needed. The boundaries of the shape are defined by extending those points in the
     direction perpendicular to the main axis', those points being centres of segments of defined magnitudes
     (w1, w2, w3, etc are the lengths of these segments)
     */

    SPoint zentrum;
    SPoint [] primary, secondary, secondaryDistinct;
    float [] lengths;
    SPoint centrePoint;
    PGraphics layer;
    boolean isCustom, enableSmooth;
    float value; // associated value
    int updateRequested; // update requests (additive (vestibular flag)): 0 = none ; 1 = recalculate ; 2 = redraw
    color colour;

    tick(PGraphics _layer, SPoint _p1, SPoint _p2, SPoint _p3, float _w1, float _w2, float _w3, 
    color _colour, float _value, boolean _isCustom) {
      allocVectors(3);

      primary[0] = _p1; 
      primary[1] = _p2; 
      primary[2] = _p3;
      lengths[0] = _w1; 
      lengths[1] = _w2; 
      lengths[2] = _w3;

      layer = _layer;
      isCustom = _isCustom;
      value = _value;
      enableSmooth = true;
      colour = _colour;

      zentrum = new SPoint(0, 0);
      updateRequested = 3;
      update();
    }

    tick(PGraphics _layer, SPoint [] _points, float [] _lengths, color _colour, float _value, boolean _isCustom) {
      int infosize, i;

      infosize = min(_points.length, _lengths.length);
      allocVectors(infosize);

      for (i = 0; i < infosize; i++) {
        primary[i] = new SPoint(_points[i]);
        lengths[i] = _lengths[i];
      }

      layer = _layer;
      isCustom = _isCustom;
      value = _value;
      enableSmooth = true;
      colour = _colour;

      zentrum = new SPoint(0, 0);
      updateRequested = 3;
      update();
    }

    void findSecondaryPointsBounds() { // find secondary points taking first and last points as definers of primary directing axis
      int i, j, k;
      SPoint lastPoint = primary[primary.length -1];
      SPoint firstPoint = primary[0];
      int tmp = min(lengths.length, primary.length);
      secondary = new SPoint[2*tmp];
      SPoint perpendicularAxis = findPerpendicularAxis(lastPoint, firstPoint);
      for (i = 0; i < tmp; i++) {
        float tmp1 = lengths[i]*0.5;
        secondary[i] = new SPoint(primary[i].x + perpendicularAxis.x*tmp1, primary[i].y + perpendicularAxis.y*tmp1);
        secondary[i+tmp] = new SPoint(primary[i].x - perpendicularAxis.x*tmp1, primary[i].y - perpendicularAxis.y*tmp1);
      }

      SPoint [] tmpp = new SPoint[2*tmp];

      boolean foundDupe = false;
      k = 0;
      for (i = 0; i < 2*tmp; i++) {
        for (j = i+1; j < 2*tmp && !foundDupe; j++) {
          if (secondary[j].isEqualTo(secondary[i], 0.01)) {
            foundDupe = true;
          }
        }
        if (!foundDupe) {
          tmpp[k] = secondary[i];
          k++;
        }
      }
      SPoint [] tmpp2 = new SPoint[k];
      float accX = 0, accY = 0;
      for (i = 0; i < tmpp2.length; i++) {
        tmpp2[i] = tmpp[i];
        accX += tmpp[i].x; 
        accY += tmpp[i].y;
      }
      centrePoint = new SPoint( accX / (float(tmpp2.length)), accY / (float(tmpp2.length)) );
      zentrum = new SPoint(centrePoint); // store centre

      // order points clockwise
      secondaryDistinct = new SPoint[tmpp2.length];

      secondaryDistinct[0] = new SPoint(tmpp2[0]);
      for (i = 0; i < tmpp2.length; i++) {
        if (less(tmpp2[i], secondaryDistinct[0], zentrum, h) > 0) secondaryDistinct[0] = new SPoint(tmpp2[0]);
      }
      int first = i-1;
      for (i = 1; i < tmpp2.length; i++) {
        secondaryDistinct[i] = new SPoint(secondaryDistinct[i-1]);
        for (j = 0; j < tmpp2.length; j++) { 
          // find first point that is for certain greater than the last already ordered
          if (less(secondaryDistinct[i-1], tmpp2[j], zentrum, h) > 0) { 
            secondaryDistinct[i] = new SPoint(tmpp2[j]); 
            break;
          }
        }
        for (j = 0; j < tmpp2.length; j++) {
          if (less(secondaryDistinct[i-1], tmpp2[j], zentrum, h) > 0 && 
            less(tmpp2[j], secondaryDistinct[i], zentrum, h) > 0) 
            secondaryDistinct[i] = new SPoint(tmpp2[j]);
        }
      }
    }

    void allocVectors(int primaryLength) {
      primaryLength = abs(primaryLength);
      primary = new SPoint[primaryLength];
      lengths = new float[primaryLength];
      secondary = new SPoint[2*primaryLength];
    }

    void update() {
      if (updateRequested > 0) {
        // trickery: bitwise operations to interpret additive (vestibular) flags
        int maxfunctioncalls = 2;
        int flag = updateRequested;
        int curstate = 0;

        for (int i = 0; i < maxfunctioncalls; i++) {
          curstate = flag & 1;
          if (curstate == 1) {
            switch(i) {
              /*
              note that for everytime this is called after updateRequest is set to 3,
               order of primitive flags is important! If redraw was 1 and recalc was 2,
               it would try to redraw prior to recalculating, which would need 2 update calls
               to indeed update, and in the first time it would yield a nullpointerexception for
               SPoint [] secondaryDistinct (redraw accesses it but recalc is what generates it)
               */
            case 0: // primitive flag is 1 (recalc)
              findSecondaryPointsBounds();
              updateRequested -= 1;
              break;
            case 1: // primitive flag is 2 (redraw)
              redrawTick();
              updateRequested -= 2;
              break;
            }
          }
          flag = flag >> 1;
        }
      }
    }

    void redrawTick() { 
      /*use this to draw to a layer which is an offscreen buffer and then convert that layer to image.
       calculate only once, after that is just a matter of showing on the screen.
       If one tick must be erased, need to redraw all except the one that was erased */

      layer.beginDraw();
      if (enableSmooth) layer.smooth();
      else layer.noSmooth();

      layer.fill(colour);
      layer.strokeWeight(2);
      layer.stroke(colour);
      layer.beginShape(TRIANGLE_FAN);
      layer.vertex(centrePoint.x, centrePoint.y);
      for (int i = 0; i < secondaryDistinct.length; i++) {
        layer.vertex(secondaryDistinct[i].x, secondaryDistinct[i].y);
      }
      layer.vertex(secondaryDistinct[0].x, secondaryDistinct[0].y);
      layer.endShape(CLOSE);
      layer.endDraw();
    }

    void setPoint(SPoint _p, int _index) {
      try {
        primary[_index] = _p;
      } 
      catch (Exception e) {
      }
    }

    SPoint getPoint(int _index) {
      SPoint returner = new SPoint(0, 0);
      try {
        returner = new SPoint(primary[_index]);
      } 
      catch (Exception e) {
      }

      return returner;
    }

    SPoint dirAxis() { //unit direction vector
      SPoint p1, p2, returner;
      float dx, dy, mod;

      p1 = primary[0]; 
      p2 = primary[primary.length - 1];
      dx = p2.x - p1.x; 
      dy = p2.y - p1.y;
      mod = sqrt(dx*dx + dy*dy);
      returner = new SPoint(dx/mod, dy/mod);

      return returner;
    }

    void setValue(float _value) {
      value = _value;
    }

    float getValue() {
      return value;
    }

    void setLayer(PGraphics _layer) {
      layer = (_layer == null) ? (g) : (_layer) ;
    }

    void enableSmooth(boolean _enableSmooth, boolean _callUpdateNow) {
      if (enableSmooth != _enableSmooth) {
        enableSmooth = _enableSmooth;
        updateRequested = 2;
        if (_callUpdateNow) update();
      }
    }

    void setColour(color _colour, boolean _callUpdateNow) {
      if (colour != _colour) {
        colour = _colour;
        updateRequested = 2;
        if (_callUpdateNow) update();
      }
    }

    void requestUpdate(int _updateRequested) {
      updateRequested = _updateRequested;
    }

    SPoint getCentre() {
      return zentrum;
    }

    SPoint getPrimaryPoint(int _index) {
      if (_index < 0 || _index > primary.length) return null;
      else return primary[_index];
    }
  }

  /* handling of colours */

  void fillTicksColours() {
    int i, j, start;
    float end;

    for (j = 0; j < ticks_colourProfiles.size(); j++) {
      colourStorage cur = getTicksColourProfile(j);
      end = cur.endPercent()*ticks_resolution;
      start = int(cur.startPercent()*ticks_resolution);
      for (i = start; i < end; i++) {
        ticks_colours[i] = cur.interpColour( float(i - start) / (end - start) );
      }
    }
  }

  void addTicksColourProfile(color _einc, color _ausc, float _einp, float _ausp) {
    // here, percent positions apply to number of ticks
    ticks_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    bckp_ticks_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    Collections.sort(ticks_colourProfiles); // will sort profiles; changes indexing order
    Collections.sort(bckp_ticks_colourProfiles);
  }
  ArrayList getTicksColourProfiles() {
    return ticks_colourProfiles;
  }
  colourStorage getTicksColourProfile(int i) {
    return ( (colourStorage) ticks_colourProfiles.get(i) );
  }

  void addShapeColourProfile(color _einc, color _ausc, float _einp, float _ausp) {
    // here, percent positions apply to number of ticks
    shape_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    bckp_shape_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    Collections.sort(shape_colourProfiles); // will sort profiles; changes indexing order
    Collections.sort(bckp_shape_colourProfiles);
  }
  ArrayList getShapeColourProfiles() {
    return shape_colourProfiles;
  }
  colourStorage getShapeColourProfile(int i) {
    return ( (colourStorage) shape_colourProfiles.get(i) );
  }

  void setTicksColourProfileLimit(int i, float _newEinPercent) {
    if (mode_ticks < 2) return;

    float oldeinp, oldausp, delta, neweinp, newausp, maxp, minp;
    colourStorage tst, tmp;
    int j;

    minp = ((colourStorage) bckp_ticks_colourProfiles.get(0)).einp;
    maxp = ((colourStorage) bckp_ticks_colourProfiles.get(bckp_ticks_colourProfiles.size()-1)).ausp;

    if ((i != 0 && _newEinPercent < minp) || (i != getTicksColourProfiles().size()-1 && _newEinPercent > maxp)) return;
    if (i <= 0 || i >= getTicksColourProfiles().size()-1) return;

    tst = getTicksColourProfile(i);
    oldeinp = tst.einp;
    oldausp = tst.ausp;
    delta = oldausp - oldeinp;
    newausp = max(minp, min(maxp, _newEinPercent + delta));
    if (_newEinPercent == oldeinp) return;

    tmp = new colourStorage(tst.einc, tst.ausc, _newEinPercent, newausp);
    ticks_colourProfiles.set(i, tmp);

    tst = getTicksColourProfile(i+1);
    neweinp = tmp.ausp;
    newausp = max(neweinp, tst.ausp);

    tmp = new colourStorage(tst.einc, tst.ausc, neweinp, newausp);
    ticks_colourProfiles.set(i+1, tmp);
  }

  void moveTicksColoursBoundary(int i, float _newEinPercent) {
    // will glue colour profiles together. Moving profile i by its einp is the same as moving i-1 by its ausp
    if (mode_ticks < 2) return;

    int j;
    colourStorage tmp_cur1, tmp_cur2, tmp_prv1, tmp_prv1_b, tmp_bckp, old;
    float deltaMove, deltaP, prvdeltaP, curdeltaP, curdeltaMove;
    float tmp1, tmp2, maxp, minp;

    if (i <= 0 || i >= getTicksColourProfiles().size()) return;
    setTicksColourProfileLimit(i, _newEinPercent);
    minp = ((colourStorage) bckp_ticks_colourProfiles.get(0)).einp;
    maxp = ((colourStorage) bckp_ticks_colourProfiles.get(bckp_ticks_colourProfiles.size()-1)).ausp;

    old = (colourStorage) bckp_ticks_colourProfiles.get(i);
    deltaP = old.ausp - old.einp;
    deltaMove = _newEinPercent - old.einp;

    tmp_bckp = (colourStorage) bckp_ticks_colourProfiles.get(0);
    tmp_cur1 = (colourStorage) ticks_colourProfiles.get(0);
    curdeltaP = tmp_bckp.ausp - tmp_bckp.einp;
    for (j = 0; j < getTicksColourProfiles().size(); j++) {
      tmp_prv1_b = tmp_bckp;
      tmp_bckp = (colourStorage) bckp_ticks_colourProfiles.get(j);
      tmp_prv1 = tmp_cur1;
      tmp_cur1 = (colourStorage) ticks_colourProfiles.get(j);

      prvdeltaP = curdeltaP;
      curdeltaP = tmp_bckp.ausp - tmp_bckp.einp;
      curdeltaMove = tmp_bckp.einp + deltaMove;

      tmp1 = max(minp, min(maxp, curdeltaMove)); // move ein to new position
      tmp2 = max(minp, min(maxp, tmp1 + curdeltaP)); // move aus to new position
      if (j == 0) tmp1 = tmp_bckp.einp;
      if (j == getTicksColourProfiles().size()-1) tmp2 = tmp_bckp.ausp;
      tmp_cur2 = new colourStorage(tmp_cur1.einc, tmp_cur1.ausc, tmp1, tmp2);
      ticks_colourProfiles.set(j, tmp_cur2);

      if (tmp1 < tmp_prv1_b.ausp && j > 0) {
        tmp2 = max(minp, min(maxp, tmp1 - prvdeltaP));
        tmp_cur2 = new colourStorage(tmp_prv1.einc, tmp_prv1.ausc, tmp2, tmp1);
        ticks_colourProfiles.set(j-1, tmp_cur2);
      }
    }

    resetTicksBuffer();
    fillTicksColours();
    int off = 0;
    for (i = 0; i < getNumberOfTicks(); i++) {
      tick cur = getTickReference(i);
      if (cur.isCustom) off++;
      else cur.setColour(ticks_colours[i-off], false);
    }
    fillTicksBuffer();
  }

  void setShapeColourProfileLimit(int i, float _newEinPercent) {
    if (mode_ticks % 2 == 0) return;

    float oldeinp, oldausp, delta, neweinp, newausp, maxp, minp;
    colourStorage tst, tmp;
    int j;

    minp = ((colourStorage) bckp_shape_colourProfiles.get(0)).einp;
    maxp = ((colourStorage) bckp_shape_colourProfiles.get(bckp_shape_colourProfiles.size()-1)).ausp;

    if ((i != 0 && _newEinPercent < minp) || (i != getShapeColourProfiles().size()-1 && _newEinPercent > maxp)) return;
    if (i <= 0 || i >= getShapeColourProfiles().size()-1) return;

    tst = getShapeColourProfile(i);
    oldeinp = tst.einp;
    oldausp = tst.ausp;
    delta = oldausp - oldeinp;
    newausp = max(minp, min(maxp, _newEinPercent + delta));
    if (_newEinPercent == oldeinp) return;

    tmp = new colourStorage(tst.einc, tst.ausc, _newEinPercent, newausp);
    shape_colourProfiles.set(i, tmp);

    tst = getShapeColourProfile(i+1);
    neweinp = tmp.ausp;
    newausp = max(neweinp, tst.ausp);

    tmp = new colourStorage(tst.einc, tst.ausc, neweinp, newausp);
    shape_colourProfiles.set(i+1, tmp);
  }

  void moveShapeColoursBoundary(int i, float _newEinPercent) {
    if (mode_ticks % 2 == 0) return;

    // will glue colour profiles together. Moving profile i by its einp is the same as moving i-1 by its ausp
    int j;
    colourStorage tmp_cur1, tmp_cur2, tmp_prv1, tmp_prv1_b, tmp_bckp, old;
    float deltaMove, deltaP, prvdeltaP, curdeltaP, curdeltaMove;
    float tmp1, tmp2, maxp, minp;

    if (i <= 0 || i >= getShapeColourProfiles().size()) return;
    setShapeColourProfileLimit(i, _newEinPercent);
    minp = ((colourStorage) bckp_shape_colourProfiles.get(0)).einp;
    maxp = ((colourStorage) bckp_shape_colourProfiles.get(bckp_shape_colourProfiles.size()-1)).ausp;

    old = (colourStorage) bckp_shape_colourProfiles.get(i);
    deltaP = old.ausp - old.einp;
    deltaMove = _newEinPercent - old.einp;

    tmp_bckp = (colourStorage) bckp_shape_colourProfiles.get(0);
    tmp_cur1 = (colourStorage) shape_colourProfiles.get(0);
    curdeltaP = tmp_bckp.ausp - tmp_bckp.einp;
    for (j = 0; j < getShapeColourProfiles().size(); j++) {
      tmp_prv1_b = tmp_bckp;
      tmp_bckp = (colourStorage) bckp_shape_colourProfiles.get(j);
      tmp_prv1 = tmp_cur1;
      tmp_cur1 = (colourStorage) shape_colourProfiles.get(j);

      prvdeltaP = curdeltaP;
      curdeltaP = tmp_bckp.ausp - tmp_bckp.einp;
      curdeltaMove = tmp_bckp.einp + deltaMove;

      tmp1 = max(minp, min(maxp, curdeltaMove)); // move ein to new position
      tmp2 = max(minp, min(maxp, tmp1 + curdeltaP)); // move aus to new position
      if (j == 0) tmp1 = tmp_bckp.einp;
      if (j == getShapeColourProfiles().size()-1) tmp2 = tmp_bckp.ausp;
      tmp_cur2 = new colourStorage(tmp_cur1.einc, tmp_cur1.ausc, tmp1, tmp2);
      shape_colourProfiles.set(j, tmp_cur2);

      if (tmp1 < tmp_prv1_b.ausp && j > 0) {
        tmp2 = max(minp, min(maxp, tmp1 - prvdeltaP));
        tmp_cur2 = new colourStorage(tmp_prv1.einc, tmp_prv1.ausc, tmp2, tmp1);
        shape_colourProfiles.set(j-1, tmp_cur2);
      }
    }

    updateShapeBuffer();
  }

  class colourStorage implements Comparable {
    color einc, ausc; //start (ein) and end (aus) colours
    float einp, ausp; //start (ein) and end (aus) percentage positions

    colourStorage(color _einc, color _ausc, float _einp, float _ausp) {
      einc = _einc; 
      ausc = _ausc;
      einp = _einp; 
      ausp = _ausp;
    }

    color interpColour(float percent) {
      return lerpColor(einc, ausc, percent);
    }

    color startColour() {
      return einc;
    }

    color endColour() {
      return ausc;
    }

    float startPercent() {
      return einp;
    }

    float endPercent() {
      return ausp;
    }

    int compareTo(Object _other) {
      colourStorage other = (colourStorage) _other;
      float diff = other.einp - this.einp;
      if (diff > 0) return -1;
      else if (diff < 0) return 1;
      else return 0;
    }
  }

  // in case you rather do the drawing yourself
  PGraphics getTicksBuffer() {
    return offscreenTicksBuffer;
  }
  PGraphics getShapeBuffer() {
    return offscreenShapeBuffer;
  }

  // I/O
  void mPressed() {
    float _ox, _oy, _rx, _ry;
    _ox = mouseX - x;
    _oy = mouseY - y;
    _rx = screenbufoffx + oldw/2 + _ox*cost + _oy*sint;
    _ry = screenbufoffy + oldh/2 - _ox*sint + _oy*cost;

    prv_IOfloatid = cur_IOfloatid;
    cur_IOfloatid = click2id(round(_rx), round(_ry)); 
    //println(_rx + " // " + _ry);
    watchDrag = (cur_IOfloatid >= 0) ;
    //println(watchDrag + " // " + cur_IOfloatid);
  }

  void mDragged() {
    float _ox, _oy, _rx, _ry, tmp1, tmp2;
    _ox = mouseX - x + trickx;
    _oy = mouseY - y + tricky;
    if (watchDrag) {
      if (abs(mode_shape) > 1) { // project on bardir
        _rx = screenbufoffx + oldw/2 + _ox*cost + _oy*sint;
        _ry = screenbufoffy + oldh/2 - _ox*sint + _oy*cost;
        float barlambda = bardir.getProjectionOf(_rx, _ry);
        prv_IOfloatid = cur_IOfloatid;
        SPoint offset = ((tick) ticks.get(0)).dirAxis();
        float tickdimention = (abs(mode_shape) == 2) ? ( oldh ) : ( oldw ) ;
        tickdimention /= 2;

        tmp1 = barlambda*bardir.x + screenbufoffx + offset.x*tickdimention;
        tmp2 = barlambda*bardir.y + screenbufoffy + offset.y*tickdimention;
      } 
      else {
        float modulus, gaugeRadius;
        _rx = + _ox*cost + _oy*sint;
        _ry = - _ox*sint + _oy*cost;
        gaugeRadius = 0.99*oldw/2;
        
        modulus = sqrt(_rx*_rx + _ry*_ry);
        
        tmp1 = gaugeRadius/modulus*_rx + screenbufoffx + oldw/2;
        tmp2 = gaugeRadius/modulus*_ry + screenbufoffy + oldh/2;
      }
      prv_IOfloatid = cur_IOfloatid;
      cur_IOfloatid = click2id(round(tmp1), round(tmp2));
    }
  }

  void mReleased() {
    watchDrag = false;
  }

  float click2id(int _px, int _py) {
    // returns id in linear scale. Can be overriden by subclass,
    // although it would be more clever to override the upperlevel
    // method that receives this id all at once (id2value, id2pos)
    int regionId;
    color regionColour;
    float returner = -1;
    tick tmp, tmp1;
    SPoint prvtick, nxttick, segment, pos;

    regionColour = ticksMapScreenBuffer.get(_px, _py);
    regionId = ( (regionColour << 8 ) >> 8 ); // remove alpha
    if (regionId == 0) return -1;
    if (regionId < 0) returner = -(regionId +2); // click was exactly over a tick
    else { // click was between 2 ticks
      tmp1 = (tick) ticks.get(regionId); // next tick
      tmp = (tick) ticks.get(regionId-1); // prev tick
      if (abs(mode_shape) > 1) {
        nxttick = tmp1.getCentre();
        prvtick = tmp.getCentre();
      } 
      else {
        nxttick = tmp1.getPrimaryPoint(0);
        prvtick = tmp.getPrimaryPoint(0);
      }
      segment = new SPoint(nxttick.x - prvtick.x, nxttick.y - prvtick.y);
      pos = new SPoint(_px - prvtick.x - screenbufoffx, _py - prvtick.y - screenbufoffy);
      returner = regionId-1 + segment.getProjectionOf(pos);
    }
    return returner;
  }
  
  float getClickId() {
    return cur_IOfloatid;
  }
  float getPClickId() {
    return prv_IOfloatid;
  }
  
  boolean isOnFocus() {
    return (click2id(round(mouseX - x + xoff + screenbufoffx + oldw/2), 
                     round(mouseY - y + yoff + screenbufoffy + oldh/2)) != -1);
  }
  
  void setModeTicks(int _mode_ticks) {
    mode_ticks = _mode_ticks;
  }
  void setModeShape(int _mode_shape) {
    mode_shape = _mode_shape;
  }
  void setMDraggedOffsets(float _trickx, float _tricky) {
    trickx = _trickx; tricky = _tricky;
  }
}

/**
## ADIMMERBAR ##

Analog dimmer (bar shaped).
Ticks can be set on/off (from gauge)
Background can be set on/off (from gauge)
Ticks colouring following setPosition can be set on/off (from gauge)
Background colouring following setPostion can be set on/off (from gauge)
Button can be displayed or not
**/

public class ADimmerBar extends UIObject {
  Gauge baseGauge;
  Button baseButton; SPoint btnpos, btnorigin;
  SPoint bardir; float dtheta, sint, cost; int bsizew, bsizeh;
  float oldw, oldh;
  float minV, maxV;
  int numTicks, displayMode, colouringMode, s_cprofile, t_cprofile;
  // displayMode: no ticks, no fill ; no ticks, fill ; ticks, no fill ; ticks, fill
  // colouringMode: following: ticks no, fill no ; ticks no, fill yes ; ticks yes, fill no ; ticks yes, fill yes
  boolean watchDrag;
  float posid, cfactor;
  
  PImage [] statesIBckp;
  public ADimmerBar(PApplet _parent, PGraphics _layer, 
                    float _barx, float _bary, float _barw, float _barh,
                    float _butw, float _buth, color state0, color state2,
                    float _minV, float _maxV, int _numTicks) {
    // assumes dimmer is horizontal. Allows direction to be changed later. Take that into account when setting w, h
    super(_parent, _layer);
    setBoundingBox(_barx, _bary, _barw, _barh);
    oldw = _barw; oldh = _barh;
    bardir = new SPoint(1, 0); // default
    maxV = _maxV; minV = _minV; numTicks = _numTicks;
    dtheta = 0; sint = 0; cost = 1; posid = 0.1;
    watchDrag = false;
    cfactor = 1 + 1.0/numTicks;
    colouringMode = s_cprofile = t_cprofile = 0;
    
    // -- bar gauge --
    float [] _coords = new float[4];
    _coords[0] = 0; _coords[1] = h*0.1; _coords[2] = h*0.9; _coords[3] = h; // defaults
    float [] _wds = new float[4];
    int t = numTicks*10;
    _wds[0] = _wds[3] = w*0.5/t; _wds[1] = _wds[2] = w/t; // defaults
    displayMode = 2;
    baseGauge = new Gauge(parent, layer, x, y, oldw, oldh, 0.5, _coords, _wds, 2, displayMode, 2, numTicks, minV, maxV);
    
    SPoint [] newtemplate = new SPoint[4];
    for (int i=0; i < 4; i++) newtemplate[i] = new SPoint(_coords[i], 0); // routines assume coords are on x, y is dummy
    baseGauge.addTickTemplate(newtemplate, _wds);
    baseGauge.setTemplateMultiplier(1.0, 1);
    baseGauge.resetTicksBuffer();
    baseGauge.clearAllTicks();
    baseGauge.addAutoTicks();
    baseGauge.updateShapeBuffer();
    baseGauge.fillTicksScreenBuffer();
    // -- end of bar gauge --
    
    // -- button --
    // default: start at minV
    btnpos = new SPoint(x - oldw/2, y);
    btnorigin = new SPoint(x - oldw/2, y);
    state0 = color(red(state0), green(state0), blue(state0), 255);
    state2 = color(red(state2), green(state2), blue(state2), 255);
    baseButton = new Button(parent, layer, btnpos.x, btnpos.y, oldw/(numTicks-1), oldh*1.5, 2,
                            state0, lerpColor(state0, state2, 0.5), state2, 0, 0,
                            null, "", 0, 0, 0, 0, 0);
    baseButton.setDefaultState(true, 0);
    setBaseButtonPos(posid, 0, 0);
    bsizew = baseButton.statesI[0].BG.width; bsizeh = baseButton.statesI[0].BG.height;
    statesIBckp = new PImage[6];
    for (int i = 0; i < 6; i++) {
      int iw, ih;
      iw = baseButton.statesI[i].BG.width; ih = baseButton.statesI[i].BG.height;
      statesIBckp[i] = createImage(iw, ih, RGB);
      statesIBckp[i].copy(baseButton.statesI[i].BG, 0, 0, iw, ih, 0, 0, iw, ih);
    }
    // -- end of button --
  }
  
  void rotateDeg(float _dtheta) { // rotate in degrees
    rotateRad(radians(_dtheta));
  }
  void rotateRad(float _dtheta) {
    if (_dtheta != dtheta) {
      dtheta = _dtheta;
      cost = cos(dtheta);
      sint = sin(dtheta);
      
      bardir.x = cost;
      bardir.y = sint;
      float [] newDims = rotateBoundingBox(oldw, oldh, dtheta);
      setBoundingBox(x, y, newDims[0], newDims[1]);
      
      baseGauge.rotateRad(dtheta);
      
      float tmp = oldw/2;
      btnorigin.x = x - tmp*bardir.x;
      btnorigin.y = y - tmp*bardir.y;
      setBaseButtonPos(posid, 0, 0);
      setBaseButtonRotation(dtheta);
    }
  }
  float getRotationDeg() {
    return degrees(dtheta);
  }
  float getRotationRad() {
    return dtheta;
  }
  
  void setShapeResolution(int _shape_res) {
    if (baseGauge.shape_resolution != _shape_res) {
      baseGauge.shape_resolution = _shape_res;
      baseGauge.updateShapeBuffer();
    }
  }
  void setTicksResolution(int _ticks_res) {
  }
  
  Gauge gauge() {
    return baseGauge;
  }
  Button button() {
    return baseButton;
  }
  
  private SPoint calculateButtonPos(float _id, float _offx, float _offy) {
    SPoint returner = new SPoint(0,0);
    returner.x = btnorigin.x + _id*oldw/numTicks*bardir.x + _offx;
    returner.y = btnorigin.y + _id*oldw/numTicks*bardir.y + _offy;
    return returner;
  }
  
  private void setBaseButtonPos(float _id, float _offx, float _offy) {
    //btnpos.x = btnorigin.x + _id*oldw/numTicks*bardir.x + _offx;
    //btnpos.y = btnorigin.y + _id*oldw/numTicks*bardir.y + _offy;
    btnpos = calculateButtonPos(_id, _offx, _offy);
    ImageBox [] reference = baseButton.statesI;
    baseButton.setPos(btnpos.x, btnpos.y);
    for (int i = 0; i < reference.length; i++) {
      reference[i].setPos(btnpos.x, btnpos.y);
    }
  }
  
  private void setBaseButtonRotation(float _angle) {
    ImageBox [] reference = baseButton.statesI;
    PGraphics tmp; float [] dim = new float[2]; int [] intdim = new int[2];
    for (int i = 0; i < reference.length; i++) {
      dim = rotateBoundingBox(bsizew, bsizeh, _angle);
      intdim[0] = round(dim[0]); intdim[1] = round(dim[1]);
      tmp = createGraphics(intdim[0], intdim[1], P2D);
      tmp.beginDraw();
      tmp.pushMatrix();
      tmp.translate(tmp.width/2, tmp.height/2);
      tmp.rotate(_angle);
      tmp.imageMode(CENTER);
      tmp.image(statesIBckp[i], 0, 0);
      tmp.popMatrix();
      tmp.endDraw();
      reference[i].BG = tmp.get(0, 0, intdim[0], intdim[1]);
    }
    dim = rotateBoundingBox(bsizew, bsizeh, _angle);
    baseButton.w = dim[0]; baseButton.h = dim[1];
    for (int i = 0; i < 6; i++) {
      baseButton.active[i].settings(intdim[0], intdim[1]);
      baseButton.active[i].addImage(reference[i].BG, 0x00000001, false);
    }
  }
  
  private int gaugeSide(float _px, float _py) {
    // receives a point and tests it to see to which side of the gauge middle it is
    SPoint perpendicularAxis = new SPoint(-bardir.y*10, bardir.x*10); //rotate 90 deg
    SPoint testMiddle = new SPoint(x, y);
    //SPoint testVector = new SPoint(perpendicularAxis.x - testMiddle.x, perpendicularAxis.y - testMiddle.y);
    SPoint testPos = new SPoint(_px - testMiddle.x, _py - testMiddle.y);
    if (perpendicularAxis.crossProductWith(testPos) < 0.1) return 1;
    else if (perpendicularAxis.crossProductWith(testPos) > 0.1) return -1;
    else return 0;
  }

  public void display() {
    baseGauge.display();
    baseButton.display();
  }
  
  public void mPressed() {
    if (baseButton.probeActiveMouse()) watchDrag = true;
    else if (baseGauge.isOnFocus()) {
      baseGauge.watchDrag = true;
      baseGauge.mDragged();
      if (baseGauge.getClickId() != -1) {
        posid = baseGauge.getClickId()*cfactor;
      }
      setBaseButtonPos(posid, 0, 0);
      if (colouringMode % 2 != 0) baseGauge.moveShapeColoursBoundary(s_cprofile, posid/numTicks);
      if (colouringMode > 1) baseGauge.moveTicksColoursBoundary(t_cprofile, posid/numTicks);
      baseGauge.watchDrag = false;
    }
  }
  public void mReleased() {
    watchDrag = false;
  }
  public void mDragged() {
    if (watchDrag) {
      if (baseGauge.getClickId() == -1) {
        int side = gaugeSide(mx, my);
        posid = numTicks/2.0 + side*numTicks/2.0;
      } else {
        posid = baseGauge.getClickId()*cfactor;
      }
      setBaseButtonPos(posid, 0, 0);
      if (colouringMode % 2 != 0) baseGauge.moveShapeColoursBoundary(s_cprofile, posid/numTicks);
      if (colouringMode > 1) baseGauge.moveTicksColoursBoundary(t_cprofile, posid/numTicks);
    }
  }
  
  void setColourFollow(int _colouringMode, int _s_cprofile, int _t_cprofile) {
    colouringMode = _colouringMode;
    s_cprofile = _s_cprofile;
    t_cprofile = _t_cprofile;
    if (colouringMode % 2 != 0) baseGauge.moveShapeColoursBoundary(s_cprofile, posid/numTicks);
    if (colouringMode > 1) baseGauge.moveTicksColoursBoundary(t_cprofile, posid/numTicks);
  }
}
