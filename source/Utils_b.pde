class SPoint {
  float x, y;

  SPoint(float _x, float _y) {
    x = _x; 
    y = _y;
  }

  SPoint(SPoint _p) {
    this(_p.x, _p.y);
  }

  boolean isEqualTo(SPoint _p, float _eps) {
    return ( (abs(_p.x - x) <= _eps && abs(_p.y - y) <= _eps) );
  }

  float distTo(SPoint _p) {
    return ( distTo(_p.x, _p.y) );
  }

  float distTo(float _x, float _y) {
    float distx = x - _x;
    float disty = y - _y;
    return abs(sqrt( distx*distx + disty*disty ));
  }

  float getProjectionOf(SPoint _p) {
    return ( getProjectionOf(_p.x, _p.y) );
  }

  float getProjectionOf(float _x, float _y) { 
    // returns multiplier that represents orthog projection of (_x, _y) on this vector
    float _dotProduct;
    _dotProduct = dotProductWith(_x, _y);
    if (_dotProduct == 0) return 0;
    return ( _dotProduct / (x*x + y*y) );
  }

  float dotProductWith(float _x, float _y) {
    return (_x*x + _y*y);
  }

  float crossProductWith(SPoint _p) {
    return ( crossProductWith(_p.x, _p.y) );
  }

  float crossProductWith(float _x, float _y) {
    return (x*_y - y*_x);
  }

  float modulus() {
    return (sqrt(x*x + y*y));
  }
  
  void normaliseMe() {
    float mod = modulus();
    x /= mod; y /= mod;
  }
}

class clockPoint extends SPoint implements Comparable {
  //http://wiki.processing.org/w/Sorting_with_Comparable
  SPoint clockCentre;
  float h;

  clockPoint(float _x, float _y, float _cx, float _cy, float _h) {
    super(_x, _y);
    clockCentre = new SPoint(_cx, _cy);
    h = _h;
  }

  clockPoint(SPoint _p, SPoint _c, float _h) {
    this(_p.x, _p.y, _c.x, _c.y, _h);
  }

  int compareTo(Object _other) {
    clockPoint other = (clockPoint) _other;
    return less(new SPoint(x, y), new SPoint(other.x, other.y), clockCentre, h );
  }
}

int less(SPoint _a, SPoint _b, SPoint _zentrum, float h) {
  //http://stackoverflow.com/questions/6989100/sort-points-in-clockwise-order
  int returner = 1;
  SPoint a, b, zentrum;

  a = new SPoint(_a.x, h - _a.y);
  b = new SPoint(_b.x, h - _b.y);
  zentrum = new SPoint(_zentrum.x, h - _zentrum.y);

  if (a.x >= 0 && b.x < 0) 
    returner = 1;
  else if (a.x == 0 && b.x == 0) 
    returner = (a.y > b.y) ? (1) : (-1);

  else {
    // compute the cross product of vectors (centre -> a) x (centre -> b)
    float det = (a.x - zentrum.x) * (b.y - zentrum.y) - (b.x - zentrum.x) * (a.y - zentrum.y); 
    if (det < -0.1) 
      returner = 1;
    else if (det > 0.1) 
      returner = -1;

    else {
      // points a and b are on the same line from the centre 
      // check which point is closer to the centre 
      float d1 = (a.x - zentrum.x) * (a.x - zentrum.x) + (a.y - zentrum.y) * (a.y - zentrum.y); 
      float d2 = (b.x - zentrum.x) * (b.x - zentrum.x) + (b.y - zentrum.y) * (b.y - zentrum.y); 
      returner = (d1 > d2) ? (1) : (-1);
    }
  }

  return returner;
}

SPoint findPerpendicularAxis(SPoint _start, SPoint _end) { //storing in a SPoint class because we need a group of 2 floats, but technically it is a 2D vector
  SPoint returner;
  SPoint dirVector = new SPoint(_end.x - _start.x, _end.y - _start.y); //storing in a SPoint class, but this is a 2D vector

  if (abs(dirVector.y) < 0.1) {
    returner = new SPoint(0, 1);
  } 
  else {
    float xyratio = dirVector.x / dirVector.y;
    float newVectorX = 1/sqrt(1 + xyratio*xyratio);
    float newVectorY = -xyratio*newVectorX;
    float normaliser = sqrt(newVectorX*newVectorX + newVectorY*newVectorY);
    returner = new SPoint(newVectorX / normaliser, -xyratio*newVectorX / normaliser); // returns a unit vector
  }

  return returner;
}

float [] rotateBoundingBox(float _w, float _h, float _angle) {
  /* rotates object of initial bounding box {_w, _h} and returns new bounding 
  box that contains rectangle of dimentions {_w, _h} rotated by angle _angle (in radians) */
  float sina = abs(sin(_angle));
  float cosa = abs(cos(_angle));
  
  float [] returner = new float[2];
  returner[0] = (_h*sina + _w*cosa);
  returner[1] = (_h*cosa + _w*sina);
  
  return (returner);
}
