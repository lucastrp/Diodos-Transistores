class Particulas {
 
 float angle, d, raio, x_c, y_c, freq, x, y, speedx, speedy, x_ant, y_ant;
 float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
 color c1, c2;
 PGraphics pg;
 
 Particulas(float temp_angle, float temp_d, float temp_raio, float temp_x_c, float temp_y_c, float temp_freq, float temp_speedx, float temp_speedy, color temp_c1, color temp_c2, PGraphics temp_pg) {
  angle = temp_angle;
  d = temp_d;
  raio = temp_raio;
  x_c = temp_x_c;
  y_c = temp_y_c;
  freq = temp_freq;
  speedx = temp_speedx;
  speedy = temp_speedy;
  c1 = temp_c1;
  c2 = temp_c2;
  pg = temp_pg;
 }
 
 void display() {
    pg.fill(c1);
    pg.stroke(c2);
    pg.strokeWeight(2);
    x = x_c + cos(radians(angle))*raio;
    y = y_c + sin(radians(angle))*raio;
    pg.ellipse(x, y, d, d);
    pg.stroke(255);
    if(d <= 10)
      pg.strokeWeight(1);
    pg.line(x - d/4, y, x + d/4, y);
 }
 
 void orbitar() {
   angle-= freq;
   x = x_c + cos(radians(angle))*raio;
   y = y_c + sin(radians(angle))*raio;
 }
 
 void mover() {
   x_c = x_c + speedx;
   y_c = y_c + speedy;
 }
 
 float get_ang() {
   return angle;
 }
 
 float get_x() {
   float r;
   r = x_c + cos(radians(angle))*raio;
   return r;
 }
 
 float get_y() {
   float r;
   r = y_c + sin(radians(angle))*raio;
   return r;
 }
 
 float get_xc() {
   return x_c;
 }
 
 float get_spx() {
   return speedx;
 }
 
 float get_spy() {
   return speedy;
 }
 
 float get_yc() {
   return y_c;
 }
 
 void set_xc(float temp) {
   x_c = temp;
 }
 
 void set_yc(float temp) {
   y_c = temp;
 }
 
 void set_ang(float temp) {
   angle = temp;
 }
 
 void invert_spx() {
    speedx = -speedx; 
 }
 
 void invert_spy() {
    speedy = -speedy; 
 }
 
 void set_spx(float sp) {
    speedx = sp; 
 }
 
 void set_spy(float sp) {
    speedy = sp; 
 }
 
 void change_pg(PGraphics pgr) {
    pg = pgr;  
 }
 
 void display_lacuna() {
    x = x_c + cos(radians(angle))*raio;
    y = y_c + sin(radians(angle))*raio;
    pg.stroke(147, 7, 89);
    pg.strokeWeight(1.5);
    pg.line(x - 4, y - 4, x + 4, y + 4);
    pg.line(x + 4, y - 4, x - 4, y + 4);
 }
 void encontrar_lacuna() {
    if(k == 0) {
      x2 = p[19].get_x();
      x1 = x_c + cos(radians(angle))*raio;
      y1 = y_c + sin(radians(angle))*raio;
      y2 = p[19].get_y();
      speedx = (x2 - x1)/60;
      speedy = (y2 - y1)/60;
      k = 1;
      p[19].set_xc(get_xc());
      p[19].set_yc(get_yc());
      p[19].set_ang(get_ang());
    }
    if((x >= x2 - 0.5 && x <= x2 + 0.5) || (y >= y2 - 0.5 && y <= y2 + 0.5)) {
      speedx = 0;
      speedy = 0;
      p[19].display_lacuna();
      display();
      q = 1;
    }
    else if((x <= x2 - 0.5 || x >= x2 + 0.5) && (y <= y2 - 0.5 || y >= y2 + 0.5) && q == 0) {
      mover();
      display();
      p[19].display_lacuna();
      pg4.strokeWeight(1.5);
      pg4.stroke(147, 7, 89);
      pg4.line(x2 - 4, y2 - 4, x2 + 4, y2 + 4);
      pg4.line(x2 + 4, y2 - 4, x2 - 4, y2 + 4);
    }
  }
}

