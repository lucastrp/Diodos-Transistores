import java.awt.*;
import java.awt.event.*;
import javax.swing.JWindow;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

ScrollFrame sf;
ScrollFrame sf_b;
ScrollFrame sf_c;
ScrollFrame sf2;
ScrollFrame sf3;
ScrollFrame sf4;
ScrollFrame sf5;
int alvo, alvo2, i, j, cont = 0, q = 0, k = 0, ind = 0, passe2 = 0, cont2 = 0, cont3 = 0, passe3 = 0, passe4 = 0, passe5 = 0, passe6 = 0, passe7 = 0, passe8 = 0, passe9 = 0;
int passe10 = 0, passe11 = 0, passe12 = 0, passe14 = 0, passe15 = 0, passe_n5 = 0, passe16 = 0, passe_n6 = 0, passe17 = 0, vai = -1;
int window = 0, sub_aba = 1, pol = 1, op = 1, etapa = 1, neut = -1, neut2 = -1, neut5 = -1, neut6 = -1;
float speed = 0.5, xc1 = -50, xc2 = 400, ang, sen = 5, xc12 = -80.5, xc22 = 429.5, xc13 = 24, xc23 = 173, mouse_x, mouse_y, vb = 2.0;
float x_c1 = 57, x_c2 = -27, y_sl = 355, y_sl2 = 165, v_b, v_c, ib, ie, ic;
float yc13 = 15.5, yc23 = 164.5, ics, igrec;
char dopante = 'p', op2 = 'A';
int[] verif = new int[20];
int[] verif2 = new int[10];
int[] verif3 = new int[6];
int[] verif4 = new int[12];
int[] passe13 = new int[12];
int[] pass = new int[4];
int[] neut3 = new int[6];
int[] neut4 = new int[6];
float[] y1 = new float[9];
float[] y0 = new float[9];
float[] vy = new float[9];
float t1 = 0, t2 = 0, t3 = 0, t, yc1 = 300, yc2 = -110, t4 = 0, t5 = 0, t6 = 0, t7 = 0;
PImage esfera, esfera2, esfera3, marker, led_on, led_off, lupa, forw, ret, back, colchete, beta, omega, w1, w2, w3, w4, w5;
Particulas[] el = new Particulas[54];
Particulas[] p = new Particulas[50];
Particulas[] n = new Particulas[50];
Particulas[] port =  new Particulas[20];
Particulas[] n1 =  new Particulas[20];
Particulas[] n2 =  new Particulas[20];
Particulas[] pp =  new Particulas[6];
Particulas[] b =  new Particulas[6];
Particulas[] v2 =  new Particulas[12];
Particulas legenda1, legenda2;
color a = color(25, 117, 165);
color z = color(255);
PGraphics pg2, pg3, pg4, pg5, pg6, pg7, pg8, pg9, pg10, pg11, pg1;
PFont fonte1, fonte2, fonte3, fonte4, fonte5, fonte6, fonte7;
boolean r1 = false, r2 = false, on = false, play4 = false, play1 = false, play2 = false, play3 = false, play5 = false, be = false, ce = false, chave = false, help = false;
float x_sin, y_sin;
float prevX=0.0, prevY=0.0;
int numOfWaves = 4;
float angle_sin = 0, amplitude = 50, amplitude2 = 30;

void setup() {
  
  size(800, 600);
  background(200, 200, 200);
  fonte1 = loadFont("MS-PGothic-20.vlw");
  fonte2 = loadFont("MS-PGothic-16.vlw");
  fonte3 = loadFont("MS-PGothic-28.vlw");
  fonte4 = loadFont("MS-PGothic-10.vlw");
  fonte5 = loadFont("MS-PGothic-36.vlw");
  fonte6 = loadFont("MS-PGothic-14.vlw");
  fonte7 = loadFont("MS-PGothic-12.vlw");
  for(i = 0; i < 10; i++)
    verif[i] = 0;
  pg1 = createGraphics(400, 475, P2D);
  pg2 = createGraphics(400, 340, P2D);
  pg3 = createGraphics(310, 190, P2D);
  pg4 = createGraphics(310, 250, P2D);
  pg5 = createGraphics(400, 460, P2D);
  pg6 = createGraphics(160, 110, P2D);
  pg7 = createGraphics(160, 110, P2D);
  pg8 = createGraphics(420, 480, P2D);
  pg9 = createGraphics(120, 120, P2D);
  pg10 = createGraphics(120, 60, P2D);
  pg11 = createGraphics(120, 120, P2D);
  esfera = loadImage("sphere.png");
  esfera2 = loadImage("esfera2.png");
  esfera3 = loadImage("esfera3.png");
  marker = loadImage("marker.png");
  ret = loadImage("return.png");
  led_on = loadImage("led_lamp.png");
  led_off = loadImage("led_lamp_off.PNG");
  lupa = loadImage("lupa.png");
  forw = loadImage("next.png");
  back = loadImage("back.png");
  beta = loadImage("beta.png");
  omega = loadImage("omega.png");
  colchete = loadImage("colchete.png");
  w1 = loadImage("w1.png");
  w2 = loadImage("w2.png");
  w3 = loadImage("w3.png");
  w4 = loadImage("w4.png");
  w5 = loadImage("w5.png");
  for(j = 0; j <= 3; j++) {
    pass[j] = 0;
  }
  for(i = 0; i < 10; i++)
    verif2[i] = 0;
  for(i = 0; i < 6; i++)
    verif3[i] = 0;
  for(i = 0; i < 6; i++) {
    neut4[i] = neut3[i] = -1;
  }
  for(i = 0; i < 12; i++)
    verif4[i] = 0;
  for(i = 0; i < 12; i++)
    passe13[i] = 0;
  for (i = 0; i <= 3; i++) {
    y1[i] = y0[i] = 110 + random(-60, 10);
    vy[i] = random(0.7, 1.2);
  }
  
  for (i = 4; i < 8; i++) {
    el[i] = new Particulas(0 + 90*(i - 4), 8, 35, xc1 + 25, 120, 1, 0.5, 0, 0, 0, pg2); 
  }
  for (i = 8; i < 12; i++) {
    el[i] = new Particulas(0 + 90*(i - 8), 8, 35, xc2 + 25, 120, -1, -0.5, 0, 0, 0, pg2); 
  }
  for (i = 12; i < 16; i++) {
    el[i] = new Particulas(0 + 90*(i - 12), 8, 35, 150.5, yc1 + 25, -1, 0, -0.5, 0, 0, pg2); 
  }
  for (i = 16; i < 20; i++) {
    el[i] = new Particulas(0 + 90*(i - 16), 8, 35, 250.5, yc1 + 25, -1, 0, -0.5, 0, 0, pg2); 
  }
  for (i = 20; i < 24; i++) {
    el[i] = new Particulas(0 + 90*(i - 20), 8, 35, 150.5, yc2 + 25, -1, 0, 0.5, 0, 0, pg2); 
  }
  for (i = 24; i < 28; i++) {
    el[i] = new Particulas(0 + 90*(i - 24), 8, 35, 250.5, yc2 + 25, -1, 0, 0.5, 0, 0, pg2); 
  }
  for (i = 28; i < 32; i++) {
    el[i] = new Particulas(0 + 90*(i - 28), 8, 35, xc12 + 25, 120, -1, 0.5, 0, 0, 0, pg2); 
  }
  for (i = 32; i < 36; i++) {
    el[i] = new Particulas(0 + 90*(i - 32), 8, 35, xc22 + 25, 120, -1, -0.5, 0, 0, 0, pg2); 
  }
  for (i = 0; i < 3; i++) {
    p[i] = new Particulas(0 + 90*i, 6, 30, 120, 110, 1, 0, 0, 0, 0, pg4);
  }
  for (i = 3; i < 7; i++) {
    p[i] = new Particulas(0 + 90*(i - 3), 6, 30, xc13 + 20, 110, 1, 0.5, 0, 0, 0, pg4);
  }
  for (i = 7; i < 11; i++) {
    p[i] = new Particulas(0 + 90*(i - 7), 6, 30, xc23 + 20, 110, -1, -0.5, 0, 0, 0, pg4);
  }
  for (i = 11; i < 15; i++) {
    p[i] = new Particulas(0 + 90*(i - 11), 6, 30, 120, yc13 + 20, 1, 0, 0.5, 0, 0, pg4);
  }
  for (i = 15; i < 19; i++) {
    p[i] = new Particulas(0 + 90*(i - 15), 6, 30, 120, yc23 + 20, -1, 0, -0.5, 0, 0, pg4);
  }
  p[19] = new Particulas(270, 6, 30, 120, 110, 0, 0, 0, 0, 0, pg4);
  //mais 7 grupos de 4 elétrons
  for (i = 20; i < 24; i++) {
    p[i] = new Particulas(0 + 90*(i - 20), 6, 30, xc13 + 20, yc13 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 24; i < 28; i++) {
    p[i] = new Particulas(0 + 90*(i - 24), 6, 30, xc23 + 20, yc13 + 20, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 28; i < 32; i++) {
    p[i] = new Particulas(0 + 90*(i - 28), 6, 30, xc13 + 20, yc23 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 32; i < 36; i++) {
    p[i] = new Particulas(0 + 90*(i - 32), 6, 30, xc23 + 20, yc23 + 20, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 36; i < 40; i++) {
    p[i] = new Particulas(0 + 90*(i - 36), 6, 30, xc23 + 20 + 75, yc13 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 40; i < 44; i++) {
    p[i] = new Particulas(0 + 90*(i - 40), 6, 30, xc23 + 20 + 75, 110, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 44; i < 48; i++) {
    p[i] = new Particulas(0 + 90*(i - 44), 6, 30, xc23 + 20 + 75, yc23 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  //pg "n"
  for (i = 0; i < 4; i++) {
    n[i] = new Particulas(0 + 90*i, 6, 30, 120, 110, 1, 0, 0, 0, 0, pg4);
  }
  for (i = 4; i < 8; i++) {
    n[i] = new Particulas(0 + 90*(i - 4), 6, 30, xc13 + 20, 110, 1, 0.5, 0, 0, 0, pg4);
  }
  for (i = 8; i < 12; i++) {
    n[i] = new Particulas(0 + 90*(i - 8), 6, 30, xc23 + 20, 110, -1, -0.5, 0, 0, 0, pg4);
  }
  for (i = 12; i < 16; i++) {
    n[i] = new Particulas(0 + 90*(i - 12), 6, 30, 120, yc13 + 20, 1, 0, 0.5, 0, 0, pg4);
  }
  for (i = 16; i < 20; i++) {
    n[i] = new Particulas(0 + 90*(i - 16), 6, 30, 120, yc23 + 20, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 20; i < 24; i++) {
    n[i] = new Particulas(0 + 90*(i - 20), 6, 30, xc13 + 20, yc13 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 24; i < 28; i++) {
    n[i] = new Particulas(0 + 90*(i - 24), 6, 30, xc23 + 20, yc13 + 20, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 28; i < 32; i++) {
    n[i] = new Particulas(0 + 90*(i - 28), 6, 30, xc13 + 20, yc23 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 32; i < 36; i++) {
    n[i] = new Particulas(0 + 90*(i - 32), 6, 30, xc23 + 20, yc23 + 20, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 36; i < 40; i++) {
    n[i] = new Particulas(0 + 90*(i - 36), 6, 30, xc23 + 20 + 75, yc13 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 40; i < 44; i++) {
    n[i] = new Particulas(0 + 90*(i - 40), 6, 30, xc23 + 20 + 75, 110, -1, 0, -0.5, 0, 0, pg4);
  }
  for (i = 44; i < 48; i++) {
    n[i] = new Particulas(0 + 90*(i - 44), 6, 30, xc23 + 20 + 75, yc23 + 20, 1, 0, -0.5, 0, 0, pg4);
  }
  n[48] = new Particulas(45, 6, 30, 120, 110, 1, 1.5, 0.5, 0, 0, pg4);
  
  for(i = 0; i < 3; i++) {
    port[i] = new Particulas(0, 8, 0, 144.2 - 13.8*i, random(35, 75), 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg6);
  }
  for(i = 10; i < 13; i++) {
    port[i] = new Particulas(0, 8, 0, 20 + 13.8*(i - 10), random(35, 75), 0, random(0.4, 0.6), random(-0.4, 0.4), 0, 0, pg7);
  }
  for(i = 3; i < 10; i++) {
    if(i > 6)
      port[i] = new Particulas(0, 8, 0, 144.2 - 13.8*i, random(30, 80), 0, random(0.4, 0.6), random(-0.4, 0.4), 0, 0, pg6);
    else
      port[i] = new Particulas(0, 8, 0, 144.2 - 13.8*i, random(30, 80), 0, random(-0.6, -0.4), random(-0.4, 0.4), 0, 0, pg6);
  }
  for(i = 13; i < 20; i++) {
    if(i > 16)
      port[i] = new Particulas(0, 8, 0, 20 + 13.8*(i - 10), random(30, 70), 0, random(-0.6, -0.4), random(-0.4, 0.4), 0, 0, pg7);
    else
      port[i] = new Particulas(0, 8, 0, 20 + 13.8*(i - 10), random(30, 70), 0, random(0.4, 0.6), random(-0.4, 0.4), 0, 0, pg7);
  }
  for(i = 0; i < 2; i++) {
    pp[i] = new Particulas(0, 8, 0, 165, 323, 0, -0.4, 0, 0, 0, pg5);
  }
  for(i = 2; i < 4; i++) {
    pp[i] = new Particulas(0, 8, 0, 233, 323, 0, 0.4, 0, 0, 0, pg5);
  }
  pp[4] = new Particulas(0, 8, 0, 233, 305, 0, 0.4, 0, 0, 0, pg5);
  pp[5] = new Particulas(0, 8, 0, 165, 305, 0, -0.4, 0, 0, 0, pg5);
  for(i = 0; i < 20; i++) {
    n1[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg11);
  }
  for(i = 0; i < 20; i++) {
    n2[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg9);
  }
  for(i = 0; i < 3; i++) {
    b[i] = new Particulas(0, 6, 0, 32, 270, 0, 0, -0.4, 0, 0, pg8); //lacunas
  }
  for(i = 3; i < 6; i++) {
    b[i] = new Particulas(0, 6, 0, 32, 376, 0, 0, 0.6, 0, 0, pg8); //elétrons
  }
  for(i = 0; i < 6; i++) {
    v2[i] = new Particulas(0, 6, 0, 242, 45 + 200*i, 0, 0, -0.4, 0, 0, pg8); //lacunas
  }
   for(i = 6; i < 12; i++) {
    v2[i] = new Particulas(0, 6, 0, 242, 185 - 200*(i - 6), 0, 0, 0.4, 0, 0, pg8); //elétrons
  }
  legenda1 = new Particulas(0, 8, 0, 438, 180, 1, 0.5, 0, 0, 0, g);
  legenda2 = new Particulas(0, 8, 0, 438, 240, 1, 0.5, 0, 0, 0, g);
   //daqui
  GLOBAL = new globalTrickery(this);
  
  sf = new ScrollFrame(this, this.g, 610, 330, 340, 340, 340, 783, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf.setVAMargins(2, 2, 2, 2);
  sf.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf.setBG(new ImageBox(this, this.g, sf.x, sf.y, sf.w, sf.h, color(200, 200, 200, 150), 0, 0));
  sf.setUpdateVATransparent(true); sf.setVABGFillsWholeVA(false); sf.setVABG();
  
  sf_b = new ScrollFrame(this, this.g, 570, 312.5, 420, 405, 340, 810, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf_b.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf_b.setVAMargins(2, 2, 2, 2);
  sf_b.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf_b.setBG(new ImageBox(this, this.g, sf_b.x, sf_b.y, sf_b.w, sf_b.h, color(200, 200, 200, 150), 0, 0));
  sf_b.setUpdateVATransparent(true); sf_b.setVABGFillsWholeVA(false); sf_b.setVABG();
  
  sf_c = new ScrollFrame(this, this.g, 570, 312.5, 420, 405, 340, 630, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf_c.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf_c.setVAMargins(2, 2, 2, 2);
  sf_c.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf_c.setBG(new ImageBox(this, this.g, sf_c.x, sf_c.y, sf_c.w, sf_c.h, color(200, 200, 200, 150), 0, 0));
  sf_c.setUpdateVATransparent(true); sf_c.setVABGFillsWholeVA(false); sf_c.setVABG();
  
  sf2 = new ScrollFrame(this, this.g, 610, 340, 320, 460, 320, 2550, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf2.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf2.setVAMargins(2, 2, 2, 2);
  sf2.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf2.setBG(new ImageBox(this, this.g, sf2.x, sf2.y, sf2.w, sf2.h, color(200, 200, 200, 150), 0, 0));
  sf2.setUpdateVATransparent(true); sf2.setVABGFillsWholeVA(false); sf2.setVABG();
 
  sf3 = new ScrollFrame(this, this.g, 160, 346, 280, 456, 280, 1900, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf3.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf3.setVAMargins(2, 2, 2, 2);
  sf3.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf3.setBG(new ImageBox(this, this.g, sf3.x, sf3.y, sf3.w, sf3.h, color(200, 200, 200, 150), 0, 0));
  sf3.setUpdateVATransparent(true); sf3.setVABGFillsWholeVA(false); sf3.setVABG();
  
  sf4 = new ScrollFrame(this, this.g, 625, 345, 300, 480, 300, 1950, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf4.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf4.setVAMargins(2, 2, 2, 2);
  sf4.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf4.setBG(new ImageBox(this, this.g, sf4.x, sf4.y, sf4.w, sf4.h, color(200, 200, 200, 150), 0, 0));
  sf4.setUpdateVATransparent(true); sf4.setVABGFillsWholeVA(false); sf4.setVABG();
  
  sf5 = new ScrollFrame(this, this.g, 615, 105+475/2, 320, 475, 300, 2150, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf5.setBorderSF(4, color(200, 200, 200, 150), 0.5, 0.5, 0, 255, 3);
  sf5.setVAMargins(2, 2, 2, 2);
  sf5.setBorderVA(4, color(220, 220, 220, 150), 0.5, 0.5, 0, 255, 3);
  sf5.setBG(new ImageBox(this, this.g, sf5.x, sf5.y, sf5.w, sf5.h, color(200, 200, 200, 150), 0, 0));
  sf5.setUpdateVATransparent(true); sf5.setVABGFillsWholeVA(false); sf5.setVABG();
  
  GLOBAL.setupDone();
  //até aqui  
}

void draw () {
  if(window == 0) {
    configs(window);
    fill(14, 3, 98);
    textAlign(CENTER);
    textFont(fonte2);
    text("DIODOS E TRANSISTORES DE JUNÇÃO BIPOLAR", 200, 70, 400, 100);
    textFont(fonte6);
    fill(0);
    text("Este tutorial animado tem como objetivo fornecer uma introdução ao estudo dos diodos e transistores de junção bipolar" +
    ", abordando desde a influência do comportamento subatômico (elétrons livres e lacunas eletrônicas) no seu funcionamento até" +
    " alguns aspectos básicos de sua operação em circuitos eletrônicos simples. O tutorial é dividido em cinco seções, que podem ser acessadas clicando nas " +
    "abas que aparecem no topo da tela. Os conteúdos dessas seções estão resumidos abaixo. Passando o mouse sobre a lupa (      ), você pode visualizar uma prévia em miniatura do conteúdo de cada aba.", 30, 106, 740, 200);
    image(lupa, 272, 175, 20, 20);
    fill(14, 3, 98);
    textAlign(LEFT);
    text("Silício e dopantes tipo p e n", 30, 224);
    text("Junção p-n", 30, 373);
    text("Diodo de junção bipolar", 30, 487);
    text("Junção n-p-n", 410, 224);
    text("Transistor de junção bipolar", 410, 338);
    fill(0);
    
    text("Explora a estrutura atômica do silício puro e dopado, abordando os conceitos de ligação covalente no silício, sua dopagem com impurezas dos tipos p e n, lacunas eletrônicas e elétrons livres," +
    " o que introduz conceitos fundamentais para o entendimento da operação dos diodos e transistores de junção bipolar.", 30, 240, 360, 300);
    text("Ilustra o comportamento das lacunas eletrônicas e elétrons livres majoritários na junção p-n e o seu papel na condução de corrente elétrica " +
    "nas condições de polarização direta, reversa e na ausência de polarização.", 30, 390, 360, 200);
    text("Nesta aba, o diodo de junção bipolar é considerado como um componente circuital com a função de permitir a passagem de corrente elétrica em apenas um sentido.", 30, 504, 360, 100);
    text("Mostra como o movimento de elétrons livres e lacunas eletrônicas possibilita a condução de corrente elétrica através do transistor de junção bipolar npn, bem como sua amplificação, no modo de operação ativo desse transistor.", 410, 240, 360, 300);
    text("O transistor de junção bipolar npn é considerado como componente circuital operando nos modos ativo, de saturação e de corte. A seção também exemplifica o funcionamento do transistor como chave eletrônica.", 410, 353, 360, 150);
    mouse_x = mouseX;
    mouse_y = mouseY;
    
    image(lupa, 203, 210, 20, 20);
    image(lupa, 110, 360, 20, 20);
    image(lupa, 175, 472, 20, 20);
    image(lupa, 502, 210, 20, 20);
    image(lupa, 580, 323, 20, 20);
    
    if(mouse_x >= 203 && mouse_x <= 223 && mouse_y >= 210 && mouse_y <= 230)
      image(w1, mouse_x, mouse_y, 324.2, 200);
    else if(mouse_x >= 110 && mouse_x <= 130 && mouse_y >= 360 && mouse_y <= 380)
      image(w2, mouse_x, mouse_y, 324.2, 200);
    else if(mouse_x >= 175 && mouse_x <= 195 && mouse_y >= 472 && mouse_y <= 492)
      image(w3, mouse_x, mouse_y - 200, 324.2, 200);
    else if(mouse_x >= 502 && mouse_x <= 522 && mouse_y >= 210 && mouse_y <= 230)
      image(w4, mouse_x - 324.2, mouse_y, 324.2, 200);
    else if(mouse_x >= 580 && mouse_x <= 600 && mouse_y >= 323 && mouse_y <= 343)
      image(w5, mouse_x - 324.2, mouse_y, 324.2, 200);
  }
  else if(window == 1) {
    
    configs(window);
    if(sub_aba == 1) {
      //scrollframe
      sf.display();
      sf.beginVADraw();
  
    
      sf.getVA().fill(0);
      sf.getVA().background(223, 236, 242, 150);
  
   
      sf.getVA().textFont(fonte2);
      sf.getVA().text("O silício é um elemento da família IVA da tabela periódica (ou seja, tem valência 4). Na natureza, o silício é majoritariamente encontrado na forma de silicatos e de dióxido de silício e, em menor quantidade, como silício puro. O silício puro tem o arranjo atômico esquematizado na janela denominada 'Silício puro': observe que cada um dos quatro elétrons de valência de um átomo de silício é compartilhado com cada um dos quatro átomos vizinhos por meio de ligações covalentes." +
      "\nO silício puro pode se apresentar nas formas amorfa, monocristalina e policristalina. As variedades monocristalina e policristalina são as empregadas na indústria de semicondutores.\nO silício é classificado como semicondutor por apresentar uma condutividade de magnitude intermediária entre a dos condutores e a dos isolantes." +
      " Esta condutividade moderada é consequência da existência de dois tipos de portadores de carga no silício: os elétrons livres e as lacunas eletrônicas.\nEm temperaturas superiores ao zero absoluto, a agitação térmica confere a alguns elétrons energia suficiente para romper a ligação covalente, permitindo que eles se desvencilhem dos seus átomos de origem; "
      + "esses elétrons se tornam elétrons livres, portadores de carga negativa. Os vazios deixados por eles nas ligações covalentes são as denominadas lacunas eletrônicas, que são, por sua vez, portadoras de carga positiva." +
      "\nNo silício puro, elétrons livres e lacunas eletrônicas têm a mesma concentração, que é de aproximadamente 1,5x10    /cm   à temperatura ambiente (por volta de 25°C) e que cresce com o aumento da temperatura. Para efeito de comparação, o número de átomos por cm   no silício é próximo de 5x10    .", 8, 20, 297, 1000);
      sf.getVA().textFont(fonte7);
      sf.getVA().text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n10         3", 202, 22);
      sf.getVA().text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n3                                                            22", 105, 23);
      sf.endVAdraw();
      rectMode(CORNER);
      imageMode(CORNER);
      
      pg2.beginDraw();
      pg2.pushMatrix();
      pg2.translate(0, 50);
      pg2.background(255);
      if(play1 == true) {
        pg2.image(esfera, xc1, 95, 50, 50);
        pg2.image(esfera, xc2, 95, 50, 50);
        for (j = 4; j < 12; j++) {
          el[j].display();
        }
        if((xc2 - xc1) >= 100) {
          xc1 = xc1 + speed;
          xc2 = xc2 - speed;
          for(j = 4; j < 12; j++) {
            el[j].mover(); 
            el[j].orbitar();
          }
        }
        else {
          ang = el[6].get_ang();
          sen = sin(radians(ang));
          if(sen > 0.01 || sen < -0.01) {
            for (i = 4; i < 12; i++)
              el[i].orbitar();
            ang = el[6].get_ang();
            sen = sin(radians(ang));
          }
          else if (t1 == 0)
            t1 = millis();
        }
        sen = 5;
        t = millis();
        if(t - t1 >= 2000 && t1 != 0) {
          if(t2 == 0)
            t2 = millis();
          pg2.noFill();
          pg2.stroke(0);
          pg2.strokeWeight(1);
          pg2.ellipse((xc1 + xc2 + 50)/2, 120, 47, 18);
          if(t - t2 >= 2000 && t2 != 0) {
            pg2.image(esfera, 125.5, yc1, 50, 50);
            pg2.image(esfera, 225.5, yc1, 50, 50);
            pg2.image(esfera, 125.5, yc2, 50, 50);
            pg2.image(esfera, 225.5, yc2, 50, 50);    
            pg2.image(esfera, xc12, 95, 50, 50);
            pg2.image(esfera, xc22, 95, 50, 50);
            for (j = 12; j < 36; j++) {
              el[j].display();
            }
            if((yc1 - 95) >= 100) {
              yc1 = yc1 - speed;
              yc2 = yc2 + speed;
              xc12 = xc12 + speed;
              xc22 = xc22 - speed;
              for(j = 12; j < 36; j++) {
                el[j].mover(); 
                el[j].orbitar();
              }
            }
            else {
              pg2.noFill();
              pg2.stroke(0);
              ang = el[15].get_ang();
              sen = sin(radians(ang));
              if(sen > 0.01 || sen < -0.01) {
                for (i = 12; i < 36; i++)
                  el[i].orbitar();
                ang = el[15].get_ang();
                sen = sin(radians(ang));
              }
              else if(xc22 == 324) {
                pg2.ellipse((xc2 + xc22 + 50)/2, 120, 47, 18);
                pg2.ellipse((xc1 + xc12 + 50)/2, 120, 47, 18);
                pg2.ellipse(xc1 + 25, (95 + yc2 + 50)/2, 18, 47);
                pg2.ellipse(xc1 + 25, (95 + yc1 + 50)/2, 18, 47);
                pg2.ellipse(xc2 + 25, (95 + yc2 + 50)/2, 18, 47);
                pg2.ellipse(xc2 + 25, (95 + yc1 + 50)/2, 18, 47);
                pg2.ellipse((xc2 + xc1 + 50)/2, yc1 + 25, 47, 18);
                pg2.ellipse((xc2 + xc1 + 50)/2, yc2 + 25, 47, 18);
                
              }
            }
          }
        }
      }
      pg2.popMatrix();
      pg2.endDraw();
      
      image(pg2, 20, 160);
      noFill();
      strokeJoin(BEVEL);
      stroke(220, 220, 220);
      strokeWeight(4.8);
      rect(20, 160, 400, 340);
      textFont(fonte3);
      fill(0);
      text("Silício puro", 230, 128);
      image(forw, 720, 530);
      textFont(fonte2);
      text("Dopagem do tipo p (com boro) e a lacuna eletrônica", 340, 535, 400, 100);
      if(sen <= 0.01 && sen >= -0.01 && xc22 == 324) {
        text("Reiniciar", 340, 478);
        image(ret, 380, 460, 24, 24);
      }
      if(play1 == false) {
        noStroke();
        textAlign(CENTER);
        fill(0);
        text("Arranjo cristalino bidimensional de átomos de silício com suas ligações covalentes.\n\nClique aqui para visualizar.", 60, 278, 320, 340);
        
      }
    }
    else if(sub_aba == 2) {
      //scrollframe b
      sf_b.display();
      sf_b.beginVADraw();
  
    
      sf_b.getVA().fill(0);
      sf_b.getVA().background(223, 236, 242, 150);
  
   
      sf_b.getVA().textFont(fonte2);
      sf_b.getVA().text("Como você pode observar na janela denominada 'Dopagem do tipo p', a rede cristalina do silício apresenta defeitos pontuais, as lacunas atômicas, que correspondem à ausência de átomos em sítios regulares da rede cristalina. Nesta representação, a fração de átomos ausentes foi exagerada em favor da visualização. Nas lacunas atômicas podem se alojar átomos substitucionais, de valências tais que levam à alteração da condutividade do silício. Ao processo de adição desses átomos substitucionais na rede cristalina do silício denomina-se dopagem. Há dois tipos de dopagem: p e n. Nesta sub aba, trataremos da dopagem do tipo p. A janela 'Dopagem do tipo p' apresenta de forma esquemática a dopagem do silício (em vermelho) com boro (em azul). Note que, mesmo após a dopagem, algumas lacunas atômicas não são preenchidas.\n\n" +
      "\nNo caso do silício, a dopagem do tipo p consiste na inserção substitucional de átomos de elementos da família IIIA da tabela periódica (valência 3) na rede do silício, o boro, por exemplo. Assista à animação 'A lacuna eletrônica: silício + boro' para observar o surgimento de uma lacuna eletrônica pela dopagem do silício com boro e a sua subsequente movimentação por difusão através do reticulado cristalino. Como o boro tem três elétrons na camada de valência, apenas três dos átomos de silício vizinhos se ligam covalentemente a ele; a quarta ligação covalente não se completa devido à ausência de um elétron na camada de valência do boro, gerando assim a lacuna eletrônica. Portanto, para cada átomo de boro inserido no reticulado cristalino do silício surge uma lacuna. Considere que a temperatura é baixa o bastante para que os elétrons livres e as lacunas eletrônicas originários da agitação térmica não precisem ser representados. Para o silício, isso pode ser assumido em temperaturas inferiores à máxima temperatura de operação da junção p-n, que varia entre 150ºC e 200ºC. De fato, a concentração de lacunas eletrônicas em semicondutores do tipo p é quase sempre aproximada pela concentração do dopante p, ou seja, despreza-se a concentração de lacunas eletrônicas originárias da agitação térmica.", 8, 25, 375, 2000);
      sf_b.endVAdraw();
      rectMode(CORNER);
      imageMode(CORNER);
      
      pg3.beginDraw();
      pg3.background(255);
      pg3.pushMatrix();
      pg3.translate(20, -135);
      for(i = 0; i <= 11; i++) {
        pg3.image(esfera, 17 + 20*i, 195, 15, 15);
        if(i != 2 && i != 6)
          pg3.image(esfera, 17 + 20*i, 215, 15, 15);
        if(i != 4 && i != 10)
          pg3.image(esfera, 17 + 20*i, 235, 15, 15);
        if(i != 1 && i != 8)
          pg3.image(esfera, 17 + 20*i, 255, 15, 15);
        pg3.image(esfera, 17 + 20*i, 275, 15, 15);
      }
      if(play2 == true) {
        for (j = 0; j <= 3; j++) {
          if(y1[j] <= 180) {
            y1[j] = y1[j] + vy[j];
            a = color(25, 117, 165);
            pg3.fill(lerpColor(z, a, (y1[j] - y0[j])/(180 - y0[j])));
            pg3.stroke(lerpColor(z, a, (y1[j] - y0[j])/(180 - y0[j])));
            if(j == 0 || j == 1)
              pg3.ellipse(44.5 + 20*j, y1[j], 15, 15);
            else
              pg3.ellipse(64.5 + 40*(j), y1[j], 15, 15);
          }
          else {
            pass[j] = 1;
            pg3.fill(a);
            if(j == 0)
              pg3.ellipse(44.5, 262.5, 15, 15);
            else if(j == 1)
              pg3.ellipse(64.5, 222.5, 15, 15);
            else if(j == 2)
              pg3.ellipse(144.5, 222.5, 15, 15);
            else if(j == 3)
              pg3.ellipse(184.5, 262.5, 15, 15);
            
          }
        }
      }
      pg3.popMatrix();
      pg3.endDraw();
      
      image(pg3, 20, 110);
      if(pass[0] == 1 &&  pass[1] == 1 && pass[2] == 1 && pass[3] == 1) {
        fill(0);
        text("Reiniciar", 242, 288);
        image(ret, 275, 275, 18, 18); 
      }

      strokeWeight(4.8);
      noFill();
      stroke(220, 220, 220);
      strokeJoin(BEVEL);
      rect(20, 110, 310, 190);
      if(play2 == false) {
        fill(255);
        rect(20, 110, 310, 190);
        
        textFont(fonte2);
        textAlign(CENTER);
        fill(0);
        text("Ilustração da dopagem do silício com boro.\n\nClique aqui para visualizar.", 40, 165, 270, 220);
      }
      //consertar no mouse pressed, a altura passou para 190
      strokeWeight(4.8);
      noFill();
      stroke(220, 220, 220);
      strokeJoin(BEVEL);
      // movimento da lacuna no silício dopado com boro
    
      pg4.beginDraw();
      pg4.background(255);
      pg4.image(esfera2, 100, 90, 40, 40);
      pg4.image(esfera, xc13, 90, 40, 40);
      pg4.image(esfera, xc23, 90, 40, 40);
      pg4.image(esfera, 100, yc13, 40, 40);
      pg4.image(esfera, 100, yc23, 40, 40);
      
      pg4.image(esfera, xc13, yc13, 40, 40);
      pg4.image(esfera, xc13, yc23, 40, 40);
      pg4.image(esfera, xc23, yc13, 40, 40);
      pg4.image(esfera, xc23, yc23, 40, 40);
      pg4.image(esfera, xc23 + 75, yc13, 40, 40);
      pg4.image(esfera, xc23 + 75, yc23, 40, 40);
      pg4.image(esfera, xc23 + 75, 90, 40, 40);
      
      //novo
      for (i = 0; i < 19; i++) {
          p[i].display();
      }
      for(i = 20; i < 48; i++) {
          p[i].display();
      }
      p[19].display_lacuna();
      println(vai);
      if(play3 == true) {//reinicializar novos elétrons a cada volta à aba
        if(vai == 0) {
           p[21].encontrar_lacuna();
           if(q == 1) {
              p[21].set_xc(120);
              p[21].set_yc(110);
              p[21].set_ang(270);
           }
        }
        else if(vai == 1) {
           p[20].encontrar_lacuna();
        }
        else if(vai == 2) {
           p[12].encontrar_lacuna(); 
        }
        else if(vai == 3) {
           p[26].encontrar_lacuna(); 
        }
        else if(vai == 4) {
           p[10].encontrar_lacuna();
        }
        else if(vai == 5) {
            p[9].encontrar_lacuna();
        }
        else if(vai == 6) {
           p[35].encontrar_lacuna();
        }
        else if(vai == 7) {
            p[46].encontrar_lacuna();
        }
      }
      pg4.endDraw();
      
      image(pg4, 20, 333);
      rect(20, 333, 310, 250);
      if(vai < 7) {
        fill(0);
        image(forw, 303, 553, 24, 24);
        text("Continua", 273, 570);
      }
      else {
        fill(0);
        image(ret, 303, 556, 20, 20);
        text("Reiniciar", 268, 571);
      }
      if(play3 == false) {
        fill(255);
        rect(20, 333, 310, 250);
        textFont(fonte2);
        textAlign(CENTER);
        fill(0);
        text("Ilustração do surgimento de uma lacuna eletrônica (ou buraco eletrônico) por dopagem do silício com boro e de sua subsequente movimentação na estrutura cristalina.\n\nClique aqui para visualizar. ", 40, 387, 270, 230);
      }
      
      fill(0);

      textFont(fonte2);
      text("A lacuna eletrônica: silício + boro", 175, 324);
      text("Dopagem do tipo p", 175, 100);
      fill(0);
      text("Dopagem do tipo n (com fósforo) e o elétron livre", 590, 545);
      text("Silício puro", 418, 575);
      image(back, 352, 552);
      image(forw, 755, 525);
    }
    else if(sub_aba == 3) {
      //scrollframe c
      sf_c.display();
      sf_c.beginVADraw();
  
    
      sf_c.getVA().fill(0);
      sf_c.getVA().background(223, 236, 242, 150);
  
   
      sf_c.getVA().textFont(fonte2);
      sf_c.getVA().text("Como mencionado na sub aba 'Dopagem do tipo p (com boro) e a lacuna eletrônica', a rede cristalina do silício contém lacunas atômicas, que são defeitos pontuais correspondentes à ausência de átomos em sítios regulares da rede cristalina. Nessa mesma sub aba, também se mencionou a dopagem, processo que permite alterar a condutividade do silício por meio da inserção substitucional de átomos de outros elementos químicos nas lacunas atômicas. Já abordamos a dopagem do tipo p; nesta aba, daremos foco à dopagem do tipo n." +
      " A dopagem do tipo n está representada de forma fantasiosa na janela 'Dopagem do tipo n'. Os átomos vermelhos são de silício e os verdes, de fósforo. Observe que mesmo após a dopagem algumas lacunas permanecem sem ser preenchidas.\nA animação 'O elétron livre: silício + fósforo' ilustra o surgimento de um elétron livre pela alocação de um átomo de fósforo em uma lacuna atômica. Observe que quatro dos elétrons de valência do fósforo participam das ligações covalentes com os quatro átomos de silício vizinhos. O quinto elétron do fósforo não participa de ligação covalente e, portanto, é um elétron livre. Os elétrons livres e as lacunas eletrônicas correspondentes à condutividade intrínseca do silício não estão representados. O elétron livre se move aleatoriamente por difusão, sob influência da agitação térmica e sem direção preferencial de deslocamento devido à ausência de campo elétrico. Note que a mobilidade dos elétrons livres é superior à das lacunas eletrônicas, já que elas têm deslocamento restrito aos sítios contendo elétrons participantes de ligações covalentes, enquanto os elétrons livres podem se movimentar sem restrições pela rede cristalina.", 8, 20, 375, 2000);
      sf_c.endVAdraw();
      rectMode(CORNER);
      imageMode(CORNER);
      
      pg3.beginDraw();
      pg3.background(255);
      pg3.pushMatrix();
      pg3.translate(20, -135);
      for(i = 0; i <= 11; i++) {
        pg3.image(esfera, 17 + 20*i, 195, 15, 15);
        if(i != 2 && i != 6)
          pg3.image(esfera, 17 + 20*i, 215, 15, 15);
        if(i != 4 && i != 10)
          pg3.image(esfera, 17 + 20*i, 235, 15, 15);
        if(i != 1 && i != 8)
          pg3.image(esfera, 17 + 20*i, 255, 15, 15);
        pg3.image(esfera, 17 + 20*i, 275, 15, 15);
      }
      if(play2 == true) {
        for (j = 0; j <= 3; j++) {
          if(y1[j] <= 180) {
            y1[j] = y1[j] + vy[j];
            a = color(64, 147, 46);
            pg3.fill(lerpColor(z, a, (y1[j] - y0[j])/(180 - y0[j])));
            pg3.stroke(lerpColor(z, a, (y1[j] - y0[j])/(180 - y0[j])));
            if(j == 0 || j == 1)
              pg3.ellipse(44.5 + 20*j, y1[j], 15, 15);
            else
              pg3.ellipse(64.5 + 40*(j), y1[j], 15, 15);
          }
          else {
            pass[j] = 1;
            pg3.fill(a);
            if(j == 0)
              pg3.ellipse(44.5, 262.5, 15, 15);
            else if(j == 1)
              pg3.ellipse(64.5, 222.5, 15, 15);
            else if(j == 2)
              pg3.ellipse(144.5, 222.5, 15, 15);
            else if(j == 3)
              pg3.ellipse(184.5, 262.5, 15, 15);
          }
        }
      }
      pg3.popMatrix();
      pg3.endDraw();
      
      image(pg3, 20, 110);
      if(pass[0] == 1 &&  pass[1] == 1 && pass[2] == 1 && pass[3] == 1) {
        fill(0);
        text("Reiniciar", 242, 288);
        image(ret, 275, 275, 18, 18); 
      }
      strokeWeight(4.8);
      noFill();
      stroke(220, 220, 220);
      strokeJoin(BEVEL);
      rect(20, 110, 310, 190);
      if(play2 == false) {
        fill(255);
        rect(20, 110, 310, 190);
        
        textFont(fonte2);
        textAlign(CENTER);
        fill(0);
        text("Ilustração da dopagem do silício com fósforo.\n\nClique aqui para visualizar.", 40, 160, 270, 220);
      }
      strokeWeight(4.8);
      noFill();
      stroke(220, 220, 220);
      strokeJoin(BEVEL);
     
      // dopante n
      pg4.beginDraw();
      pg4.background(255);
      pg4.image(esfera3, 100, 90, 40, 40);
      pg4.image(esfera, xc13, 90, 40, 40);
      pg4.image(esfera, xc23, 90, 40, 40);
      pg4.image(esfera, 100, yc13, 40, 40);
      pg4.image(esfera, 100, yc23, 40, 40);
      
      pg4.image(esfera, xc13, yc13, 40, 40);
      pg4.image(esfera, xc13, yc23, 40, 40);
      pg4.image(esfera, xc23, yc13, 40, 40);
      pg4.image(esfera, xc23, yc23, 40, 40);
      pg4.image(esfera, xc23 + 75, yc13, 40, 40);
      pg4.image(esfera, xc23 + 75, yc23, 40, 40);
      pg4.image(esfera, xc23 + 75, 90, 40, 40);
      for(i = 0; i <= 48; i++) {
         n[i].display(); 
      }
      println(vai);
      if(play3 == true) {
        //310, 220
        if(n[48].get_x() <= 10) {
           n[48].invert_spx();
        }
        else if(n[48].get_x() >= 300) {
           n[48].invert_spx();
        }
        
        if(n[48].get_y() <= 10) {
           n[48].invert_spy();
        }
        else if(n[48].get_y() >= 200) {
           n[48].invert_spy();
        }
        t = millis();
        if(t6 == 0) {
          t6 = millis();
        }
        if(t - t6 <= 30000) {
          n[48].mover();
        }
        else {
          r2 = true;
        }
      }
      pg4.endDraw();
      
      image(pg4, 20, 333);
      if(r2 == true) {
        fill(0);
        image(ret, 303, 556, 20, 20);
        text("Reiniciar", 268, 571);
      }
      noFill();
      rect(20, 333, 310, 250);
      if(play3 == false) {

        fill(255);
        rect(20, 333, 310, 250);
        textFont(fonte2);
        textAlign(CENTER);
        fill(0);
        text("Ilustração do surgimento de um elétron livre por dopagem do silício com fósforo e de sua subsequente movimentação na estrutura cristalina.\n\nClique aqui para visualizar. ", 40, 405, 270, 230);
        
      }
      
      fill(0);

      textFont(fonte2);
      text("O elétron livre: silício + fósforo", 175, 324);
      text("Dopagem do tipo n", 175, 100);      
      fill(0);
      text("Dopagem do tipo p (com boro) e a lacuna eletrônica", 580, 558);
      image(back, 373, 535);
    }
    
    textFont(fonte2);
    
    strokeWeight(4.8);
    noFill();
    stroke(220, 220, 220);
  }
  else if(window == 2) {
    configs(window);
    //scrollframe 2
    sf2.display();
    sf2.beginVADraw();

  
    sf2.getVA().fill(0);
    sf2.getVA().background(223, 236, 242, 150);

 
    sf2.getVA().textFont(fonte2);
    sf2.getVA().text("Esta animação ilustra uma junção p-n. A junção p-n é obtida a partir de uma única barra de silício dopada das extremidades em direção ao centro, de um lado com dopantes do tipo p (no nosso caso, o boro, em azul) e, do outro, com dopantes do tipo n (representados pelo fósforo, em verde). Nesta animação, as lacunas minoritárias e os elétrons minoritários não estão representados em favor da simplificação." +
    "\nQuando a junção p-n está em aberto, as lacunas do lado p tendem a se deslocar para o lado n e os elétrons do lado n tendem a se deslocar para o lado p mediados por difusão. Uma vez no lado n, as lacunas se recombinam rapidamente com os elétrons livres dessa região, já que, no lado n, os elétrons livres estão presentes em grande quantidade por serem portadores majoritários. A cada recombinação, um par elétron livre-lacuna se aniquila." +
    " Quando uma lacuna se recombina com um elétron, o átomo de boro que deu origem à lacuna adquire uma carga negativa. Esta carga é fixa, pois o átomo de boro está preso à rede cristalina. Analogamente, o átomo de fósforo que deu origem ao elétron livre aniquilado na recombinação adquire uma carga positiva fixa. No lado p, este processo se repete: os elétrons livres que atingem esta região se recombinam com as lacunas majoritárias, e um par de cargas fixas de sinais opostos é gerado a cada recombinação." +
    "\nA repetição deste processo de recombinações dá origem a um campo elétrico orientado do lado n para o lado p, devido ao surgimento de cargas fixas positivas no lado n e negativas no lado p. Este campo elétrico delimita uma região de depleção na interface entre as porções p e n da junção. A camada de depleção compreende as cargas fixas correspondentes às lacunas e elétrons livres que estavam mais próximos da interface e que, portanto, se recombinaram. Assim, esta região é uma zona destituída de portadores livres.\n" +
    "Observe que, na animação, cada recombinação provoca o aumento da camada de depleção (representada em amarelo) e do módulo do vetor campo elétrico E (representado pela seta). Note que este campo elétrico atua no sentido de confinar as lacunas na porção p e os elétrons na porção n da junção, criando uma barreira de potencial cujo valor para a junção p-n de silício dopado é de aproximadamente 0,7 V no equilíbrio.\n" +
    "Aguarde a recombinação de todas as lacunas e elétrons deslocados por difusão para ativar a polarização da junção p-n. Ao término destas recombinações, você pode acionar a polarização direta ou reversa clicando sobre as setas correspondentes.\n\nPolarização direta\n\nNa polarização direta, a região p está ligada ao terminal positivo da bateria, e a região n, ao terminal negativo. O potencial fornecido pela bateria é contrário ao potencial de barreira e menor do que ele em módulo; assim, na polarização direta há uma diminuição da camada de depleção.\n" +
    "Com a diminuição da região de depleção, algumas lacunas conseguem atravessar a barreira de potencial e atingem o lado n. Atraídas pelo terminal negativo da bateria, estas lacunas tenderão a se deslocar ao longo da região n até deixar a junção e, assim, há condução de corrente elétrica. O mesmo acontece com alguns elétrons, que têm energia suficiente para transpor a barreira de potencial e "
    + "tendem a deixar a junção pelo lado p. Quando os elétron estão na região p e as lacunas na região n, há chance de que eles se recombinem com as lacunas da região p e os elétrons da região n, respectivamente. Este fato não está ilustrado nesta animação devido à pequena quantidade de portadores representada, mas acontece na junção p-n real, de forma que, no lado n, a concentração de lacunas é maior próximo à interface entre os lados p e n do que nas proximidades do contato A, decrescendo exponencialmente em função da distância à interface. " +
    "No lado p, a concentração de elétrons livres também é regida por uma função exponencial descrecente da distância à interface entre os lados p e n. Assim, nas proximidades do contato B, a concentração de elétrons livres é menor do que na interface.\n\nPolarização reversa\n\nNa polarização reversa, os terminais da bateria estão trocados. Com o terminal negativo da bateria conectado ao lado p da junção e o terminal positivo conectado ao lado n, um fluxo de elétrons é forçado para dentro da região p e um fluxo de lacunas para dentro da região n. Uma vez no lado p, a maior parte dos elétrons provenientes da bateria se recombina com as lacunas presentes em grande quantidade nesta porção da junção, " +
    "levando ao surgimento de cargas negativas fixas correspondentes aos átomos de origem das lacunas aniquiladas. As recombinações acontecem também no lado n da junção e provocam o aparecimento de cargas positivas fixas. Assim, a camada de depleção aumenta e os elétrons e lacunas não têm energia suficiente para transpassar a barreira de potencial, ou seja, não há condução de corrente elétrica.", 15, 30, 262, 2700);
    sf2.endVAdraw();
    rectMode(CORNER);
    imageMode(CORNER);
    
    pg5.beginDraw();
    pg5.background(255);
    pg5.stroke(0);
    pg5.strokeWeight(1);
    
    pg6.beginDraw();
    pg6.background(255);
    for(i = 0; i < 6; i++) {
      for(j = 0; j < 4; j++) {
         if((j == 0 && (i == 1 || i == 5 || i == 4)) || (j == 1 && i == 4) || (j == 2 && (i == 1 || i == 3 || i == 5)) || (j == 3 && (i == 5 || i == 4 || i == 2)))
          pg6.fill(192, 237, 237, 200);
         else
          pg6.fill(252, 74, 74, 100);
        pg6.noStroke();
        pg6.ellipse(20 + 25*i, 20 + 25*j, 20, 20);
      }
    }
    pg6.stroke(0, 0, 0, 150);
    pg6.strokeWeight(2);
    if(cont2 + cont3 == 1) {
      pg6.line(140, 20, 150, 20);
    }
    else if(cont2 +cont3 == 2) {
      pg6.line(115, 95, 125, 95);
      pg6.line(140, 20, 150, 20);
    }
    else if(cont2 + cont3 == 3) {
      pg6.line(115, 45, 125, 45);
      pg6.line(115, 95, 125, 95);
      pg6.line(140, 20, 150, 20);
    }
    else if(cont2 + cont3 == 4) {
      pg6.line(115, 45, 125, 45);
      pg6.line(115, 95, 125, 95);
      pg6.line(140, 20, 150, 20);
      pg6.line(140, 95, 150, 95);
    }
    else if(cont2 +cont3 == 5) {
      pg6.line(115, 45, 125, 45);
      pg6.line(115, 95, 125, 95);
      pg6.line(140, 20, 150, 20);
      pg6.line(140, 70, 150, 70);
      pg6.line(140, 95, 150, 95);
    }
    else if(cont2 + cont3 == 6) {
      pg6.line(115, 45, 125, 45);
      pg6.line(115, 95, 125, 95);
      pg6.line(140, 20, 150, 20);
      pg6.line(115, 20, 125, 20);
      pg6.line(140, 70, 150, 70);
      pg6.line(140, 95, 150, 95);
      
    }
    else if(cont2 == 4) { //polarização reversa
      pg6.line(115, 45, 125, 45);
      pg6.line(115, 95, 125, 95);
      pg6.line(140, 20, 150, 20);
      pg6.line(90, 70, 100, 70);  
      pg6.line(115, 20, 125, 20);
      pg6.line(140, 95, 150, 95);
      pg6.line(140, 70, 150, 70);   
    }
    for(cont = 0; cont < 10; cont++) {
      port[cont].display_lacuna();
    }
    for(cont = 10; cont < 13; cont++)
      if(verif[cont] == 1)
        port[cont].display();
    if(passe5 == 1 && pol == 0)
      pp[0].display();
    else if(passe5 == 1 && pol == 1)
      pp[1].display_lacuna();
    if(passe8 == 1)
      pp[3].display();
    if(passe16 == 2)
      pp[5].display_lacuna();
    if(passe_n6 == 1)
      port[neut6].display();
    pg6.endDraw();
    
    pg5.image(pg6, x_c1 - 20, 85);
    
    pg7.beginDraw();
    pg7.background(255);
    for(i = 0; i < 6; i++) {
      for(j = 0; j < 4; j++) {
        if((j == 0 && (i == 1 || i == 3 || i == 0)) || (j == 1 && (i == 1)) || (j == 2 && (i == 0 || i == 1 || i == 4)) || (j == 3 && (i == 0 || i == 2 || i == 5)))
          pg7.fill(183, 232, 180, 200);
         else
          pg7.fill(252, 74, 74, 100);
        pg7.noStroke();
        pg7.ellipse(20 + 25*i, 20 + 25*j, 20, 20);
      }
    }
    pg7.stroke(0, 0, 0, 150);
    pg7.strokeWeight(2);
    if(cont2 + cont3 == 1) {
      pg7.line(15, 70, 25, 70);//ok
      pg7.line(20, 65, 20, 75); //ok
    }
    else if(cont2 + cont3 == 2) {
      
      pg7.line(15, 70, 25, 70);//ok
      pg7.line(20, 65, 20, 75);//ok
      pg7.line(40, 20, 50, 20);
      pg7.line(45, 15, 45, 25);
    }
    else if(cont2 + cont3 == 3) {
      
      pg7.line(15, 70, 25, 70);//ok
      pg7.line(20, 65, 20, 75);//ok
      pg7.line(40, 20, 50, 20);
      pg7.line(45, 15, 45, 25);
      pg7.line(15, 95, 25, 95);//ok
      pg7.line(20, 90, 20, 100);//ok
    }
    else if(cont2 + cont3 == 4) {
      pg7.line(15, 70, 25, 70);//ok
      pg7.line(20, 65, 20, 75);//ok
      pg7.line(40, 20, 50, 20);
      pg7.line(45, 15, 45, 25);
      pg7.line(15, 95, 25, 95);//ok
      pg7.line(20, 90, 20, 100);//ok
      pg7.line(15, 20, 25, 20);
      pg7.line(20, 15, 20, 25);
    }
    else if(cont2 + cont3 == 5) {
      pg7.line(15, 70, 25, 70);
      pg7.line(20, 65, 20, 75);
      pg7.line(40, 20, 50, 20);
      pg7.line(45, 15, 45, 25);
      pg7.line(15, 95, 25, 95);//ok
      pg7.line(20, 90, 20, 100);//ok
      pg7.line(15, 20, 25, 20);
      pg7.line(20, 15, 20, 25);
      pg7.line(40, 45, 50, 45);
      pg7.line(45, 40, 45, 50);
    }
    else if(cont2 + cont3 == 6) {
      pg7.line(15, 70, 25, 70);//ok
      pg7.line(20, 65, 20, 75);//ok
      pg7.line(40, 20, 50, 20);
      pg7.line(45, 15, 45, 25);
      pg7.line(15, 95, 25, 95);//ok
      pg7.line(20, 90, 20, 100);//ok
      pg7.line(15, 20, 25, 20);
      pg7.line(20, 15, 20, 25);
      pg7.line(40, 45, 50, 45);
      pg7.line(45, 40, 45, 50);
      pg7.line(40, 70, 50, 70);
      pg7.line(45, 65, 45, 75);
    }
    else if(cont3 == 4) { //polarização reversa

      pg7.line(15, 70, 25, 70);
      pg7.line(20, 65, 20, 75);
      pg7.line(40, 20, 50, 20);
      pg7.line(45, 15, 45, 25);
      pg7.line(15, 95, 25, 95);
      pg7.line(20, 90, 20, 100);
      pg7.line(65, 95, 75, 95);
      pg7.line(70, 90, 70, 100);
      pg7.line(15, 20, 25, 20);
      pg7.line(20, 15, 20, 25);
      pg7.line(40, 45, 50, 45);
      pg7.line(45, 40, 45, 50);
      pg7.line(40, 70, 50, 70);
      pg7.line(45, 65, 45, 75);

    }
    for (cont = 10; cont < 20; cont++) {
      port[cont].display();
    }
    for(cont = 0; cont < 3; cont++)
      if(verif[cont] == 1)
        port[cont].display_lacuna();
    if(passe6 == 1 && pol == 0)
      pp[2].display_lacuna();
    else if(passe6 == 1 && pol == 1)
      pp[3].display();
    if(passe7 == 1)
      pp[1].display_lacuna();
    if(passe_n5 == 1)
      port[neut5].display_lacuna();
    if(passe17 == 2)
      pp[4].display();
    pg7.endDraw();
    
    pg5.pushMatrix();
    pg5.translate(245, 0);
    pg5.image(pg7, x_c2 - 20, 85);
    pg5.popMatrix();
    for(cont = 0; cont < 16; cont++)
       pg5.line(200, 80 + 8*cont, 200, 84 + 8*cont);
    
    if(play4 == true) {
       if(cont2 + cont3 >= 6) {
         passe4 = 1;
         for(j = 0; j < 3; j++) {
           if(verif[j] == 1) {
             passe4 = 0;
           }
         }
         for(j = 10; j < 13; j++) {
           if(verif[j] == 1) {
             passe4 = 0;
           }
         }
       }
       pg5.stroke(0);
       pg5.strokeWeight(1);

       pg5.strokeWeight(2);
       pg5.fill(0);
       if(cont2 + cont3 != 0) {
         pg5.line(200 - (cont2 + cont3)*10, 220, 200 + (cont2 + cont3)*10, 220);
         pg5.noStroke();
         pg5.triangle(200 - (cont2 + cont3)*10, 220, 200 - (cont2 + cont3)*10 + 7, 215, 200 - (cont2 + cont3)*10 + 7, 225);
         pg5.stroke(250, 245, 154);
         pg5.fill(250, 245, 154, 80);
         if(on == false || (on == true && pol == 1)) {
           pg5.rect(200 - (cont2 + cont3)*9, 95, 18*(cont2 + cont3), 95);
         }
         else if(pol == 0)
           pg5.rect(146 - (cont2 + cont3 - 6)*14, 95, 108 + (cont2 + cont3 - 6)*28, 95);
       }       
       if(passe2 == 0) {
         for(cont = 0; cont < 3; cont++) {
           port[cont].set_spx(0.4);
         }
         for(cont = 10; cont < 13; cont++) {
           port[cont].set_spx(-0.4);
         }
         passe2 = 1;
       }
       for(cont = 3; cont < 10; cont++) {
         if(port[cont].get_x() >= 150-9*(cont2+cont3) && cont != neut5) { // mudei aqui
            if(port[cont].get_spx() > 0)
              port[cont].invert_spx();
            else
              port[cont].set_spx(-0.4);
         }
         else if(port[cont].get_x() <= 15 && cont != neut5)
           port[cont].invert_spx();
       }
       for(cont = 13; cont < 20; cont++) {
         if(port[cont].get_x() >= 150 && cont != neut6) {
             port[cont].invert_spx();
         }
         else if(port[cont].get_x() <= 15+9*(cont2+cont3) && cont != neut6) { // mudei aqui
            if(port[cont].get_spx() < 0)
              port[cont].invert_spx();
            else
              port[cont].set_spx(+0.4);
         }
       }
       for(cont = 0; cont < 20; cont++) {
      
         if(port[cont].get_x() < 1000 && port[cont].get_x() > - 1000 && cont != neut5 && cont != neut6) {
           if(port[cont].get_y() >= 80 && port[cont].get_y() <= 100) {
             port[cont].set_spy(random(-0.8, -0.1));
           }
           else if(port[cont].get_y() <= 35 && port[cont].get_y() >= 15) {
             port[cont].set_spy(random(0.1, 0.8));
           }
           else
             port[cont].set_spy(random(-0.8, 0.8)); //mudei aqui - falta mudar nos elétrons da polarização
         }
         
         //AQUI
         if(passe4 != 1 || (passe4 == 1 && on == true))
           port[cont].mover();
         if(port[cont].get_y() <= 15 || port[cont].get_y() >= 100 && cont != neut5 && cont != neut6) {
           port[cont].invert_spy();
         }
    
         if(verif[cont] == 1) {
           if(cont >= 0 && cont < 3) {
             if(port[cont].get_x() <= 150 && port[cont].get_x() >= 130) {
               port[cont].set_spx(random(-0.6, -0.4));
             }
             else if(port[cont].get_x() >= 15+9*(cont2+cont3) && port[cont].get_x() <= 15+9*(cont2+cont3) + 20) { // mudei aqui
               port[cont].set_spx(random(0.4, 0.6));
             }
             else if(port[cont].get_x() > 150)
               port[cont].set_spx(-0.4);
             else if(port[cont].get_x() < 15+9*(cont2+cont3))
               port[cont].set_spx(0.4);
             else
               port[cont].set_spx(random(-0.4, 0.6));
             for(j = 13; j < 20; j++)
               if(port[j].get_x() <= port[cont].get_x() + 5 && port[j].get_x() >= port[cont].get_x() - 5 && port[j].get_y() <= port[cont].get_y() + 5 && port[j].get_y() >= port[cont].get_y() - 5) {
                 //explosao
                 port[j].set_xc(1200);
                 port[j].set_spx(0);
                 port[j].set_spy(0);
                 port[cont].set_xc(-1200);
                 port[cont].set_spx(0);
                 port[cont].set_spy(0);
                 verif[cont] = 0;
                 verif[j] = 0;
                 cont3++;
               }
           }
           else if(cont >= 10 && cont < 13) { //else ?
               
               if(port[cont].get_x() >= 15 && port[cont].get_x() <= 35) {
                 port[cont].set_spx(random(0.4, 0.6));
               }
               else if(port[cont].get_x() <= 150-9*(cont2+cont3) && port[cont].get_x() >= 130-9*(cont2+cont3)) { // mudei aqui
                 port[cont].set_spx(random(-0.4, -0.6));
               }
               else if(port[cont].get_x() < 15)
                  port[cont].set_spx(0.4);
               else if(port[cont].get_x() > 150-9*(cont2+cont3))
                 port[cont].set_spx(-0.4);
               else
                 port[cont].set_spx(random(-0.4, 0.6));
               for(j = 3; j < 10; j++)
                 if(port[j].get_x() <= port[cont].get_x() + 5 && port[j].get_x() >= port[cont].get_x() - 5 && port[j].get_y() <= port[cont].get_y() + 5 && port[j].get_y() >= port[cont].get_y() - 5) {
                   port[j].set_yc(1400);
                   port[j].set_spx(0);
                   port[j].set_spy(0);
                   port[cont].set_yc(-1400);
                   port[cont].set_spx(0);
                   port[cont].set_spy(0);
                   verif[cont] = 0;
                   verif[j] = 0;
                   cont2++;
                   //adicionar explosao
                 }
           }
         }
       }
       for(cont = 0; cont < 3; cont++) {
         if(port[cont].get_x() >= 160) {
           port[cont].change_pg(pg7);
           port[cont].set_xc(25);
           verif[cont] = 1;
           
         } 
       }
       for(cont = 10; cont < 13; cont++) {
         if(port[cont].get_x() <= 0) {
           port[cont].change_pg(pg6);
           port[cont].set_xc(140);
           verif[cont] = 1;
         
         } 
       }
    }
    if(passe4 == 1) {
       if(on == true) {
         pg5.stroke(0);
         pg5.strokeWeight(2.5);
         pg5.line(165, 315, 20, 315);
         pg5.line(20, 315, 20, 140);
         pg5.line(20, 140, 25, 140);
         pg5.line(233, 315, 378, 315);
         pg5.line(378, 315, 378, 140);
         pg5.line(378, 140, 373, 140);
         pg5.strokeWeight(1);
         
         if(pol == 1) { //polarização direta
           
           pg5.fill(211, 73, 73);
           pg5.rect(170, 300, 30, 30);
           pg5.fill(165, 155, 155);
           pg5.rect(165, 312, 5, 6);
           pg5.rect(230, 312, 5, 6);
           pg5.rect(200, 300, 30, 30);
           pg5.rect(25, 135, 5, 10);
           pg5.rect(368, 135, 5, 10);          
           pg5.line(185, 310, 185, 320);
           pg5.line(180, 315, 190, 315);
           pg5.line(210, 315, 220, 315);
           if(passe_n5 == 4 && passe_n6 == 4) {
             port[neut5].set_xc(pp[1].get_xc());
             port[neut5].set_yc(pp[1].get_yc());
             port[neut6].set_xc(pp[3].get_xc());
             port[neut6].set_yc(pp[3].get_yc());
             port[neut5].change_pg(pg6);
             port[neut6].change_pg(pg7);
             neut6 = -1;
             neut5 = -1;
             passe_n5 = 0;
             passe_n6 = 0;
             passe5 = 0;
             passe6 = 0;
             pp[1].set_xc(165);
             pp[1].set_yc(323);;
             pp[1].set_spx(-0.4);
             pp[1].set_spy(0);
             pp[1].change_pg(pg5);
             
             pp[3].set_xc(233);
             pp[3].set_yc(323);
             pp[3].set_spx(0.4);
             pp[3].set_spy(0);
             pp[3].change_pg(pg5);
           }
           //lacuna de cobertura
           if(passe16 == 1 || passe16 == 2) {
             pp[5].mover();
             pp[5].display_lacuna(); 
           }
           if(passe16 == 1 && pp[5].get_x() < 28) {
             pp[5].set_xc(28);
             pp[5].set_spx(0);
             pp[5].set_spy(-0.4);
           }
           if(pp[5].get_y() < 153 && passe16 == 1) {
             pp[5].change_pg(pg6);
             pp[5].set_spx(0.4);
             pp[5].set_spy(random(-0.4, 0.4));//aqui
             pp[5].set_yc(48);
             pp[5].set_xc(16);
             passe16 = 2;
           }
           else if(passe16 == 2) {
             if(pp[5].get_y() >= 80 && pp[5].get_y() <= 100) {
               pp[5].set_spy(random(-0.8, -0.1));
             }
             else if(pp[5].get_y() <= 35 && pp[5].get_y() >= 15) {
               pp[5].set_spy(random(0.1, 0.8));
             }
             else
               pp[5].set_spy(random(-0.8, 0.8));

             if(pp[5].get_x() <= 15 || pp[5].get_x() >= 150 - 9*(cont2+cont3)) { 
               pp[5].invert_spx();
             }
           }
           //elétron de cobertura
           if(passe17 == 1 || passe17 == 2) {
             pp[4].mover();
             pp[4].display(); 
           }
           if(passe17 == 1 && pp[4].get_x() > 370) {
             pp[4].set_xc(370);
             pp[4].set_spx(0);
             pp[4].set_spy(-0.4);
           }
           if(pp[4].get_y() < 153 && passe17 == 1) {
             pp[4].change_pg(pg7);
             pp[4].set_spx(0.4);
             pp[4].set_spy(random(-0.4, 0.4));
             pp[4].set_yc(48);
             pp[4].set_xc(145);
             passe17 = 2;
           }
           else if(passe17 == 2) { //ok
             if(pp[4].get_y() >= 80 && pp[4].get_y() <= 100) {
               pp[4].set_spy(random(-0.8, -0.1));
             }
             else if(pp[4].get_y() <= 35 && pp[4].get_y() >= 15) {
               pp[4].set_spy(random(0.1, 0.8));
             }
             else
               pp[4].set_spy(random(-0.8, 0.8));

             if(pp[4].get_x() <= 15 + 9*(cont2+cont3) || pp[4].get_x() >= 150) {//ok 
               pp[4].invert_spx();
             }
           }
           pp[1].mover();
           if(passe5 == 0)
             pp[1].display_lacuna();
           pp[3].mover();
           if(passe6 == 0)
             pp[3].display();
           if(pp[1].get_x() < 13 && passe5 == 0) {
             pp[1].set_xc(13);
             pp[1].set_spx(0);
             pp[1].set_spy(-0.4);
           }
           if(pp[3].get_x() > 385 && passe5 == 0) {
             pp[3].set_xc(385);
             pp[3].set_spx(0);
             pp[3].set_spy(-0.4);
             
           }
           if(pp[1].get_y() < 133 && passe5 == 0) {
             pp[1].change_pg(pg6);
             pp[1].set_spx(0.4);
             pp[1].set_spy(random(-0.4, 0.4));//aqui
             pp[1].set_yc(48);
             pp[1].set_xc(16);
             passe5 = 1;
             if(passe16 == 0 && passe17 == 0)
               cont2--;
           }
           else if(passe5 == 1) {
             if(pp[1].get_y() >= 80 && pp[1].get_y() <= 100) {
               pp[1].set_spy(random(-0.8, -0.1));
             }
             else if(pp[1].get_y() <= 35 && pp[1].get_y() >= 15) {
               pp[1].set_spy(random(0.1, 0.8));
             }
             else
               pp[1].set_spy(random(-0.8, 0.8));

             if(pp[1].get_x() <= 15 || pp[1].get_x() >= 150 - 9*(cont2+cont3)) { 
               pp[1].invert_spx();
             }
             for(j = 3; j < 10 && neut5 == -1; j++) {
                if(port[j].get_x() < 700 && port[j].get_x() > -700 && port[j].get_y() > -700 && port[j].get_y() < 700) {
                 neut5 = j;
                 port[neut5].set_spx(0.4); 
                }
             }
             if(port[neut5].get_x() >= 145 && passe_n5 == 0) {
               passe_n5 = 1;
               passe16 = 1;
               port[neut5].change_pg(pg7);
               port[neut5].set_xc(16);
             }
             else if(port[neut5].get_x() >= 145 && passe_n5 == 1) {
               passe_n5 = 2;
               port[neut5].change_pg(pg5);
               port[neut5].set_xc(385);
               port[neut5].set_yc(133);
               port[neut5].set_spy(+0.4);
               port[neut5].set_spx(0);
               
             }
             else if(port[neut5].get_y() > 323 && passe_n5 == 2) {
               passe_n5 = 3;
               port[neut5].set_spy(0);
               port[neut5].set_spx(-0.4);
             }
             else if(port[neut5].get_x() < 238 && passe_n5 == 3) {
               passe_n5 = 4;
               port[neut5].set_yc(800);
               port[neut5].set_spy(0);
               port[neut5].set_spx(0);
             }
             if(passe_n5 == 3 || passe_n5 == 2) {
               port[neut5].display_lacuna();
             }
             else if(passe_n5 == 0 || passe_n5 == 1) {
               if(port[neut5].get_y() > 55)
                 port[neut5].set_spy(-0.6);
               else if(port[neut5].get_y() < 45)
                 port[neut5].set_spy(0.6);
               else
                 port[neut5].set_spy(0);
             }
             
           }
           if(pp[3].get_y() < 133 && passe6 == 0) {
             pp[3].change_pg(pg7);
             pp[3].set_spx(-0.4);
             pp[3].set_spy(0);
             pp[3].set_yc(48);
             pp[3].set_xc(149);
             passe6 = 1;
           }
           else if(passe6 == 1) {
             if(pp[3].get_y() >= 80 && pp[3].get_y() <= 100) {
               pp[3].set_spy(random(-0.8, -0.1));
             }
             else if(pp[3].get_y() <= 35 && pp[3].get_y() >= 15) {
               pp[3].set_spy(random(0.1, 0.8));
             }
             else
               pp[3].set_spy(random(-0.8, 0.8));
             if(pp[3].get_x() <= 15 + 9*(cont2+cont3) || pp[3].get_x() >= 150) { //arrumar aqui
               pp[3].invert_spx();
             }
             for(j = 13; j < 20 && neut6 == -1; j++) {
                if(port[j].get_x() < 700 && port[j].get_x() > -700 && port[j].get_y() > -700 && port[j].get_y() < 700) {
                 neut6 = j;
                 port[neut6].set_spx(-0.4); 
                }
             }
             if(port[neut6].get_x() <= 15 && passe_n6 == 0) {
               passe_n6 = 1;
               passe17 = 1;
               port[neut6].change_pg(pg6);
               port[neut6].set_xc(154);
             }
             else if(port[neut6].get_x() <= 15 && passe_n6 == 1) {
               passe_n6 = 2;
               port[neut6].change_pg(pg5);
               port[neut6].set_xc(13);// colocar displays em pg6
               port[neut6].set_yc(133);
               port[neut6].set_spy(+0.4);
               port[neut6].set_spx(0);
               
             }
             else if(port[neut6].get_y() > 323 && passe_n6 == 2) {
               passe_n6 = 3;
               port[neut6].set_spy(0);
               port[neut6].set_spx(0.4);
             }
             else if(port[neut6].get_x() > 165 && passe_n6 == 3) { 
               passe_n6 = 4;
               port[neut6].set_yc(-800);
               port[neut6].set_spy(0);
               port[neut6].set_spx(0);
             }
             if(passe_n6 == 3 || passe_n6 == 2) {
               port[neut6].display();
             }
             else if(passe_n6 == 0 || passe_n6 == 1) {
               if(port[neut6].get_y() > 55)
                 port[neut6].set_spy(-0.6);
               else if(port[neut6].get_y() < 45)
                 port[neut6].set_spy(0.6);
               else
                 port[neut6].set_spy(0);
             }
           }
         }
         else {
           pg5.fill(165, 155, 155);
           pg5.rect(165, 312, 5, 6);
           pg5.rect(230, 312, 5, 6);
           pg5.rect(170, 300, 30, 30);
           pg5.rect(25, 135, 5, 10);
           pg5.rect(368, 135, 5, 10); 
           pg5.fill(211, 73, 73);
           pg5.rect(200, 300, 30, 30);
           pg5.line(215, 310, 215, 320);
           pg5.line(180, 315, 190, 315);
           pg5.line(210, 315, 220, 315);
           pp[0].mover();
           pp[0].display();
           pp[2].mover();
           pp[2].display_lacuna();
           // movimentação dos elétrons/lacunas provenientes do gerador de tensão
           if(pp[0].get_x() < 13 && passe5 == 0) {
             pp[0].set_xc(13);
             pp[0].set_spx(0);
             pp[0].set_spy(-0.4);
             
           }
           if(pp[2].get_x() > 385 && passe5 == 0) {
             pp[2].set_xc(385);
             pp[2].set_spx(0);
             pp[2].set_spy(-0.4);
             
           }
           if(pp[0].get_y() < 133 && passe5 == 0) {
             pp[0].change_pg(pg6);
             for(alvo = 3; alvo < 10; alvo++)
               if(port[alvo].get_x() >= 15 && port[alvo].get_x() <= 150 && port[alvo].get_y() >= 15 && port[alvo].get_y() <= 100)
                 break;
             pp[0].set_yc(48);
             pp[0].set_xc(16);
             passe5 = 1;
           }
           else if(passe5 == 1) {
             pp[0].set_spx((port[alvo].get_x() - pp[0].get_x())/80);
             pp[0].set_spy((port[alvo].get_y() - pp[0].get_y())/80);
             if(pp[0].get_x() <= 15 || pp[0].get_x() >= 150) {
              pp[0].invert_spx();
             }
             if(pp[0].get_y() <= 15 || pp[0].get_y() >= 100) {
              pp[0].invert_spy();
             }
             for(j = 3; j < 10; j++)
                 if(port[j].get_x() <= pp[0].get_x() + 5 && port[j].get_x() >= pp[0].get_x() - 5 && port[j].get_y() <= pp[0].get_y() + 5 && port[j].get_y() >= pp[0].get_y() - 5) {
                   //explosao
                   port[j].set_yc(1600);
                   port[j].set_spx(0);
                   port[j].set_spy(0);
                   pp[0].set_yc(-1600);
                   pp[0].set_spx(0);
                   pp[0].set_spy(0);
                   cont2++;
                   verif[j] = 0;
                 }
           }
           if(pp[2].get_y() < 133 && passe6 == 0) {
             for(alvo2 = 13; alvo2 < 20; alvo2++)
               if(port[alvo2].get_x() >= 15 && port[alvo2].get_x() <= 150 && port[alvo2].get_y() >= 15 && port[alvo2].get_y() <= 100)
                 break;
             pp[2].change_pg(pg7);
             pp[2].set_yc(48);
             pp[2].set_xc(149);
             passe6 = 1;
           }
           else if(passe6 == 1) {
             pp[2].set_spx((port[alvo2].get_x() - pp[2].get_x())/80);
             pp[2].set_spy((port[alvo2].get_y() - pp[2].get_y())/80);
             if(pp[2].get_x() <= 15 || pp[2].get_x() >= 150) {
              pp[2].invert_spx();
             }
             if(pp[2].get_y() <= 15 || pp[2].get_y() >= 100) {
              pp[2].invert_spy();
             }
             for(j = 13; j < 20; j++)
                 if(port[j].get_x() <= pp[2].get_x() + 5 && port[j].get_x() >= pp[2].get_x() - 5 && port[j].get_y() <= pp[2].get_y() + 5 && port[j].get_y() >= pp[2].get_y() - 5) {
                   //explosao
                   port[j].set_yc(1800);
                   port[j].set_spx(0);
                   port[j].set_spy(0);
                   pp[2].set_yc(-1800);
                   pp[2].set_spx(0);
                   pp[2].set_spy(0);
                   cont3++;
                   verif[j] = 0;
                 }
           }
         }
       }
     }
     pg5.endDraw();
    
    image(pg5, 30, 110);
    if(on == true) {
      fill(0);
      text("Contato A", 75, 190);
      text("Contato B", 386, 190);
      text("Campo elétrico", 233, 350);
      textFont(fonte2);
      text("Reiniciar", 370, 500);
      image(ret, 405, 484, 18, 18);
      text("Sem polarização", 346, 555);
      image(forw, 401, 539, 24, 24);
      
      if(pol == 1) {
        textFont(fonte2);
        text("Polarização reversa", 334, 527);
        image(forw, 401, 512, 24, 24);
        textFont(fonte1);
        text("Junção p-n diretamente polarizada", 233, 145);
        textFont(fonte7);
        textAlign(LEFT);
        text("Na condição de polarização direta, a bateria impulsiona lacunas para a região p e elétrons para a região n, ocasionando a diminuição do campo elétrico e permitindo, assim, o deslocamento de portadores através da junção - isto é, a condução de corrente elétrica. ", 43, 446, 216, 200);
      }
      else {
        textFont(fonte2);
        text("Polarização direta", 339, 527);
        image(forw, 401, 512, 24, 24);
        textFont(fonte1);
        text("Junção p-n reversamente polarizada", 233, 145);
        textFont(fonte7);
        textAlign(LEFT);
        text("A injeção de elétrons na região p e a de lacunas na região n ocasiona recombinações e, portanto, o aumento do número de cargas fixas de sinais opostos. Assim, o campo elétrico fica mais intenso e a zona de depleção se alarga, impedindo a condução de corrente elétrica.", 43, 446, 216, 200);
      }
    }
    else {
      fill(0);
      text("Região p", 95, 190);
      text("Região n", 366, 190);
      if(play4 == true) {
        text("Zona de depleção", 233, 185);
        text("Campo elétrico", 233, 350);
        if(passe4 == 1) {
          textFont(fonte2);
          text("Reiniciar", 370, 500);
          image(ret, 405, 484, 18, 18);
          text("Polarização direta", 339, 527);
          text("Polarização reversa", 334, 555);
          image(forw, 401, 512, 24, 24);
          image(forw, 401, 539, 24, 24);
        }
     
        textFont(fonte7);
        text("Na ausência de polarização, há um deslocamento natural de lacunas para a região n e de elétrons para a região p devido à interação elétrica. A recombinação de portadores gera cargas fixas de sinais opostos nos dois lados da junção e, consequentemente, um campo elétrico proporcional ao número destas cargas fixas e uma zona de depleção, que funciona como uma barreira à movimentação de elétrons e lacunas através da junção.", 43, 368, 380, 200);
        textFont(fonte1);
        text("Junção p-n não polarizada", 233, 145);
  
        
      }
    }
    strokeWeight(4.8);
    noFill();
    stroke(220, 220, 220);
    strokeJoin(BEVEL);
    rect(30, 110, 400, 460);
    if(play4 == false) {
     
      fill(0);
      text("Região p", 95, 190);
      text("Região n", 366, 190);
      textAlign(CENTER);
      textFont(fonte2);
      text("Aqui consideramos a junção p-n em três condições: não-polarizada (ou em aberto), polarizada diretamente" +
      " e polarizada reversamente. Ilustramos o movimento das lacunas eletrônicas e dos elétrons livres majoritários na junção e o seu papel na " +
      "condução de corrente elétrica nas três condições.\n\nIniciar", 60, 365, 360, 300);
      image(forw, 260, 510);
      
    }

  }
  else if(window == 3) {
    configs(window);
    
    //scrollframe 3
    sf3.display();
    sf3.beginVADraw();

  
    sf3.getVA().fill(0);
    sf3.getVA().background(223, 236, 242, 150);

 
    sf3.getVA().textFont(fonte2);
    sf3.getVA().text("O circuito representado na animação 'O diodo ideal em um circuito excitado por um sinal senoidal' é composto de um gerador de tensão senoidal, um diodo ideal e um resistor. A amplitude da onda fornecida pelo gerador de tensão pode ser ajustada pelo controlador do tipo slider denominado 'Amplitude do sinal de entrada', no canto inferior esquerdo da tela. Conectado ao circuito há um osciloscópio de dois canais e dois displays. O canal 1 (CH1) exibe o sinal de entrada, proveniente do gerador de tensão. O canal 2 (CH2) fornece a forma de onda da tensão no resistor.\nO diodo ideal é um modelo para o dispositivo ativo denominado diodo, que consiste em uma junção p-n e opera conforme descrito na aba 'Junção p-n'. No modelo do diodo ideal, assume-se que o diodo só permite a passagem de corrente elétrica em um sentido, do ânodo (indicado por A) para o cátodo (indicado por K) e que o potencial de barreira da junção p-n, que nos diodos reais é de aproximadamente 0,7 V, é nulo. O ânodo corresponde ao lado p e o cátodo ao lado n da junção.\nPara que o diodo ideal conduza, ele deve estar polarizado diretamente, e, como o potencial de barreira é assumido como nulo, basta que a diferença de tensão entre ânodo e cátodo seja maior do que zero, em um primeiro momento. Quando o diodo ideal entra em condução, a queda de tensão nele assume o valor do potencial de barreira, e, portanto, é nula (porque se trata do modelo do diodo ideal; já no diodo real, a queda de tensão é de aproximadamente 0,7 V durante a condução); nesta situação, o diodo ideal funciona como uma chave fechada.\nQuando o diodo ideal é polarizado reversamente, isto é, quando a tensão no cátodo é superior à tensão no ânodo, não flui corrente através dele e dizemos que o diodo está em “corte”. Toda a tensão negativa imposta pela fonte recai sobre o diodo, já que não há corrente elétrica fluindo no circuito e que, pela Lei de Ohm, a tensão no resistor é nula. Portanto, o diodo funciona como uma chave aberta.\nAs posições do cátodo e do ânodo podem ser invertidas clicando-se sobre o diodo. Quando o ânodo está à esquerda e o cátodo à direita, o diodo destá posicionado de forma que só há condução de corrente elétrica no semiciclo positivo da tensão senoidal proveniente do gerador, ou seja, quando a tensão que se tenta impor no ânodo é maior do que a tensão no cátodo. Nessas condições, a tensão no resistor “imita” a forma de onda da tensão proveniente do gerador, uma vez que a queda de tensão no diodo é nula. Quando a fonte entra no semiciclo negativo, a tensão no ânodo é inferior à tensão no cátodo: o diodo está em corte. Como não há condução de corrente elétrica, a tensão no resistor é nula.\nQuando o cátodo está à esquerda e o ânodo à direita, a tensão no ânodo será inferior à tensão no cátodo no semiciclo positivo da fonte. Nesta situação, o diodo está em corte e a tensão no resistor é nula porque a corrente fluindo no circuito é nula. Quando a fonte entra no semiciclo negativo,  a tensão que se tenta impor no ânodo é maior do que a do cátodo; o diodo conduz e tensão no resistor tem a mesma forma de onda da tensão no gerador.", 15, 30, 220, 2200);
    sf3.endVAdraw();
    rectMode(CORNER);
    imageMode(CORNER);
     
    pushMatrix();
    ics = mouseX;
    igrec = mouseY;
    if(passe15 == 0) {
      mouse_x = 122;
      mouse_y = 540;
    }
    else if(passe15 == 1) {
      if(ics >= 90 + 271 && ics <= 215 + 271 && igrec >= 530 - 390 && igrec <= 570 - 390) {
        mouse_x = mouseX - 30 - 271;
        amplitude2 = amplitude*mouse_x/122;
      }
    }
    translate(30, 5);
  
    fill(255);
    stroke(220, 220, 220);
    strokeWeight(4.8);
    rect(300, 110, 450, 460);
    stroke(0);
    strokeWeight(2);
    
    //aqui
    pushMatrix();
    translate(32, -27);
    
    line(490, 170, 700, 170);
    line(490, 170, 490, 300);
    line(700, 170, 700, 300);
    line(490, 300, 577.5, 300);
    line(619.5, 300, 700, 300);
    ellipse(490, 235, 36, 36);
    arc(484, 235, 12, 12, 0, PI);
    arc(496, 235, 12, 12, PI, TWO_PI);
    for(i = 0 ; i <3; i++) {
      line(577.5 + 16.8*i, 300, 581.7 + 16.8*i, 290);
      line(581.7 + 16.8*i, 290, 585.9 + 16.8*i, 300);
      if(i != 2) {
        line(585.9 + 16.8*i, 300, 590.1 + 16.8*i, 310);
        line(590.1 + 16.8*i, 310, 594.3 + 16.8*i, 300);
      }
    }
    //mais um translate aqui
    pushMatrix();
    translate(-30, 0);
    fill(220, 220, 220);
    stroke(130, 130, 130);
    rect(350, 335, 350, 150);
    stroke(210, 210, 210);
    strokeWeight(1);
    fill(0);
    textFont(fonte2);
    textAlign(CENTER);
    text("Amplitude do sinal de entrada", 405, 160);
    textFont(fonte7);
    text("Esta animação é composta de um circuito eletrônico simples e de um osciloscópio. O canal CH1 do osciloscópio exibe a forma de onda da tensão de entrada, e o canal CH2, a forma de onda da tensão de saída, que é tomada no resistor. O diodo é ideal. Troque as posições do cátodo (K) e do ânodo (A) do diodo clicando sobre ele para verificar sua influência na tensão de saída. Para modificar a amplitude do sinal de entrada, clique sobre o slider 'Amplitude do sinal de entrada' e deslize-o.", 307, 494, 435, 120); 
    textFont(fonte1);
    text("O diodo ideal em um circuito excitado por um sinal senoidal", 508, 122);
    textFont(fonte1);
    text("CH1", 490, 365);
    text("CH2", 660, 365);
    rect(360, 375, 160, 98);
    rect(530, 375, 160, 98);
    stroke(178, 39, 4);
    fill(234, 68, 26);
    ellipse(365, 355, 10, 10);
    ellipse(535, 355, 10, 10);
    stroke(255);
    strokeWeight(0.5);
    line(360, 400 + amplitude/2, 520, 400 + amplitude/2);
    line(530, 400 + amplitude/2, 690, 400 + amplitude/2);
    strokeWeight(2);
    stroke(225, 255, 8);
    
    pushMatrix();
    translate(360, 400 + amplitude/2);
    scale(1, -1);
   
    for(int count=0; count < 160; ++count)
    {
      x_sin = count;
   
      angle_sin = radians(count);
      y_sin = sin(angle_sin*(numOfWaves/0.8));
   
      y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
   
      line(prevX, prevY, x_sin, y_sin);
   
      prevX = x_sin;
      prevY = y_sin;
    }
   
    prevX = prevY = 0.0;
    
    popMatrix();
    
    stroke(0);
    fill(50, 50, 50);
    ellipse(380, 355, 10, 10);
    ellipse(550, 355, 10, 10);
    noFill();
 
    beginShape();
    vertex(365, 355);
    bezierVertex(393, 300, 345, 310, 428, 238);
    bezierVertex(458, 216, 490, 223, 520, 190);
    endShape();
    
    beginShape();
    vertex(535, 355);
    bezierVertex(530, 348, 540, 330, 575, 300);
    endShape();
    
    beginShape();
    vertex(550, 355);
    bezierVertex(655, 363, 650, 330, 680, 300);
    endShape();
    
    beginShape();
    vertex(380, 355);
    bezierVertex(438, 337, 350, 300, 520, 275);
    endShape();
    
    fill(0);
    
    if(op == 1) {
      line(630, 158, 630, 182);
      triangle(630, 170, 611, 158, 611, 182);
      text("A", 597, 160);
      text("K", 644, 160);
      strokeWeight(2);
      stroke(225, 255, 8);
      
      pushMatrix();
      translate(530, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 37; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = sin(angle_sin*(numOfWaves/0.8));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
      
     
      prevX = prevY = 0.0;
      
      popMatrix(); 
  
      pushMatrix();
      translate(601, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 37; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = sin(angle_sin*(numOfWaves/0.8));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
      
     
      prevX = prevY = 0.0;
      
      popMatrix();
      
      pushMatrix();
      translate(672, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 19; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = sin(angle_sin*(numOfWaves/0.8));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
      
     
      prevX = prevY = 0.0;
      
      popMatrix();
      
      stroke(225, 255, 8);
      line(530 + 36, 400 + amplitude/2, 530 + 71.11, 400 + amplitude/2);
      line(530 + 37 + 71.11, 400 + amplitude/2, 530 + 143, 400 + amplitude/2); 
    }
    else if(op == 2) {
      line(611, 158, 611, 182);
      triangle(611, 170, 630, 158, 630, 182);
      text("K", 597, 160);
      text("A", 644, 160);
      strokeWeight(2);
      stroke(225, 255, 8);
      
      pushMatrix();
      translate(566, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 36; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = -1*sin(angle_sin*(numOfWaves/0.8));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
     
      prevX = prevY = 0.0;
      
      popMatrix();
      
      pushMatrix();
      translate(636, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 37; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = -1*sin(angle_sin*(numOfWaves/0.8));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
     
      prevX = prevY = 0.0;
      
      popMatrix();
      
      stroke(225, 255, 8);
      line(530, 400 + amplitude/2, 530 + 36, 400 + amplitude/2);
      line(530 + 71.11, 400 + amplitude/2, 530 + 106, 400 + amplitude/2);
      line(530 + 71.11 + 71.11, 400 + amplitude/2, 530 + 124 + 36, 400 + amplitude/2);
    }
    popMatrix();
    //fim aqui
    
    //aqui
    
    
    popMatrix();
    
    //controlador de amplitude
    stroke(34, 186, 211);
    strokeWeight(6);
    
    pushMatrix();
    translate(271, -390);
    line(70, 550, 190, 550);
    image(marker, mouse_x, mouse_y);
    popMatrix();
    
    popMatrix();
     
  }
  else if (window == 4) {
    configs(4);
    
    //scrollframe 4
    sf4.display();
    sf4.beginVADraw();

  
    sf4.getVA().fill(0);
    sf4.getVA().background(223, 236, 242, 150);

 
    sf4.getVA().textFont(fonte2);
    sf4.getVA().text("A animação 'Operação intrínseca do TJB' apresenta um transistor de junção bipolar (TJB). O transistor de junção bipolar é dividido em três partes: o emissor, a base e o coletor. Existem dois tipos de TJB: o npn, que é o representado nesta animação, e o pnp. " +
    "No transistor npn, o emissor e o coletor são feitos de material semicondutor do tipo n e a base de material semicondutor do tipo p. Nele, os elétrons são os portadores majoritários. No transistor pnp, o emissor e o coletor são de material p e a base de material n, o que faz com que, nesta variedade do TJB, as lacunas sejam os portadores majoritários. " +
    "Nesta animação, ilustraremos apenas o funcionamento do transistor npn, uma vez que ele é o mais utilizado comercialmente e o entendimento da sua operação é facilitado pelo fato de nele os elétrons serem os portadores majoritários - estudantes, geralmente, estão mais familiarizados com o conceito de elétrons do que com o de lacunas. Além do mais, a operação dos transistores npn e pnp é bastante semelhante.\n" +
    "Nos transistores de junção bipolar, o emissor e o coletor são fortemente dopados e apresentam baixa resistividade. A base, por sua vez, é levemente dopada, e, por isso, tem elevada resistividade. Essas características dos semicondutores empregados na fabricação dos TJBs desempenham um papel importante na sua operação, como veremos adiante.\nInicialmente, a tensão entre o emissor e a base e entre a base e o coletor são nulas. Por isso, nessas condições, os elétrons estão confinados no emissor e no coletor. As lacunas da base não foram representadas porque, no TJB npn, a corrente elétrica que flui do emissor ao coletor se deve majoritariamente ao fluxo de elétrons entre essas regiões. " +
    "Clique sobre o slide da fonte de tensão 1 para elevar a tensão entre emissor e base para aproximadamente 0,6 V. Observe que a junção p-n/emissor-base está sob polarização direta e que sua camada de depleção diminui à medida que o potencial aplicado aumenta, ou seja, à medida que o slide da fonte de tensão se desloca para cima. O valor de 0,6 V para a tensão entre emissor e base corresponde à tensão de barreira da junção p-n de silício dopado e é o mínimo necessário para que esta junção conduza, exatamente como nos diodos reais.\n"+
    "Com a junção base-emissor polarizada diretamente, alguns elétrons do emissor conseguem atravessar a zona de depleção e instalam-se na base. Note que a corrente que flui entre a base e o emissor é pequena devido à elevada resistividade do material que compõe a base.\n" +
    "Clique sobre o slide da fonte de tensão 2 para elevar a tensão entre emissor e coletor a 5 V. Esta fonte deve fornecer um potencial superior a 0,6 V para que o transistor opere no modo ativo, que é o representado nesta animação. Os outros dois modos de operação do transistor - saturação e corte - serão explorados na aba 'Modos de operação'. No modo ativo, o coletor precisa estar em um potencial mais elevado do que a base, ou seja, a junção base-coletor deve estar polarizada reversamente. Note que a camada de depleção entre a base e o coletor aumenta conforme a tensão aplicada aumenta.\n" +
    "A polarização reversa aumenta o campo elétrico gerado pelas cargas fixas da junção base-coletor. Este campo elétrico é orientado do coletor em direção à base e, portanto, favorável ao deslocamento dos elétrons localizados na base para o coletor. A corrente que flui entre o coletor e o emissor é intensa devido à baixa resistividade do material que os compõe. Esta corrente é proporcional à corrente entre emissor e base segundo um fator que depende apenas das características construtivas do transistor e que, tipicamente, varia entre 50 e 200, podendo chegar a 1000 em dispositivos especiais.", 10, 20, 255, 2200);
    sf4.endVAdraw();
    rectMode(CORNER);
    imageMode(CORNER);
    
    //programação dos pgs
    
    pg9.beginDraw();
    pg9.background(183, 232, 180, 100);
    for(cont = 0; cont < 6; cont++) {
      if(passe13[cont] == 2) {
        v2[cont].display_lacuna(); 
        if(v2[cont].get_xc() >= 110 || v2[cont].get_xc() <= 10)
            v2[cont].invert_spx();
         if(v2[cont].get_y() >= 70 && v2[cont].get_y() <= 110)
           v2[cont].set_spy(random(-0.8, -0.1));
         else if(v2[cont].get_y() <= 50 && v2[cont].get_y() >= 10)
           v2[cont].set_spy(random(0.1, 0.8));
         else
           v2[cont].set_spy(random(-0.8, 0.8)); 
         if(v2[cont].get_y() <= 10 || v2[cont].get_y() >= 110)
           v2[cont].invert_spy();
         for(i = 0; i < 20; i++) {  
           if(n2[i].get_xc() >= v2[cont].get_xc() - 3 && n2[i].get_xc() <= v2[cont].get_xc() + 3 && n2[i].get_yc() >= v2[cont].get_yc() - 3 && n2[i].get_yc() <= v2[cont].get_yc() + 3) {
                passe13[cont] = 3;
                if(neut3[cont] != -1 && n2[neut3[cont]].get_yc() == -1100) {
                  neut4[cont] = neut3[cont];
                  neut3[cont] = i;
                }
                else {
                  neut4[cont] = neut3[cont] = i;
                }
                v2[cont].set_yc(1100);
                n2[i].set_yc(-1100);
                n2[i].set_spy(0);
                n2[i].set_spx(0);
           }
         } 
      }
    }
    for(i = 0; i < 20; i++) {
      if(play5 == true)
        n2[i].mover();
      n2[i].display();
    }
    
    for(cont = 0; cont < 20; cont++) {
      if((cont < 6 && cont != neut3[cont] && cont != neut4[cont]) || cont >= 6) { 
        if(n2[cont].get_xc() >= 110 || n2[cont].get_xc() <= 10)
          n2[cont].invert_spx();
        if(n2[cont].get_y() >= 90 && n2[cont].get_y() <= 110) {
         n2[cont].set_spy(random(-0.8, -0.1));
       }
       else if(n2[cont].get_y() <= 30 && n2[cont].get_y() >= 10) {
         n2[cont].set_spy(random(0.1, 0.8));
       }
       else
         n2[cont].set_spy(random(-0.8, 0.8)); 
       if(n2[cont].get_y() <= 10 || n2[cont].get_y() >= 110) {
         n2[cont].invert_spy();
       }
     }
    }
    for(cont = 0; cont < 6; cont++) { 
      if(verif3[cont] == 1) {
        n1[cont].display();
        if(n1[cont].get_y() >= 100 && n1[cont].get_y() <= 110) {
           n1[cont].set_spy(random(-0.8, -0.1));
         }
         else if(n1[cont].get_y() <= 20 && n1[cont].get_y() >= 10) {
           n1[cont].set_spy(random(0.1, 0.8));
         }
         else
           n1[cont].set_spy(random(-0.8, 0.8)); 
         if((n1[cont].get_y() <= 10 && n1[cont].get_y() >= -1000) || (n1[cont].get_y() >= 110 && n1[cont].get_y() <= 1000)) {
           n1[cont].invert_spy();
         }
      }
    }
    for(cont = 6; cont < 12; cont++) {
      if(passe13[cont] == 6) {
        v2[cont].display(); 
        if(v2[cont].get_xc() >= 110 || v2[cont].get_xc() <= 10)
          v2[cont].invert_spx();
        if(v2[cont].get_y() >= 90 && v2[cont].get_y() <= 110) {
           v2[cont].set_spy(random(-0.8, -0.1));
        }
        else if(v2[cont].get_y() <= 30 && v2[cont].get_y() >= 10) {
           v2[cont].set_spy(random(0.1, 0.8));
        }
        else
           v2[cont].set_spy(random(-0.8, 0.8)); 
        if((v2[cont].get_y() <= 10 && v2[cont].get_y() >= -1000) || (v2[cont].get_y() >= 110 && v2[cont].get_y() <= 1000)) {
           v2[cont].invert_spy();
        }
      }
    }
    pg9.endDraw();
    
    pg10.beginDraw();
    pg10.background(192, 237, 237, 100);
    //elétrons passando (emissor - coletor)
    for(cont = 6; cont < 12; cont++) {
      if(passe13[cont] == 5) {
        v2[cont].display(); 
        if(v2[cont].get_xc() >= 110 || v2[cont].get_xc() <= 10)
          v2[cont].invert_spx();
        v2[cont].set_spy(random(-0.6, -0.8));
        v2[cont].set_spx(random(-0.4, 0.4));
        if(v2[cont].get_y() < 10) {
          v2[cont].change_pg(pg9);
          passe13[cont] = 6;
          v2[cont].set_yc(105);
         }
      }
      
    }
    //lacuna proveniente da fonte entre a base e o emissor
    if(passe10 == 2) {
       b[0].display_lacuna();
       if(b[0].get_xc() >= 110 || b[0].get_xc() <= 10)
        b[0].invert_spx();
       if(b[0].get_y() >= 45 && b[0].get_y() <= 55) {
         b[0].set_spy(random(-0.8, -0.1));
       }
       else if(b[0].get_y() <= 15 && b[0].get_y() >= 5) {
         b[0].set_spy(random(0.1, 0.8));
       }
       else
         b[0].set_spy(random(-0.8, 0.8)); 
       if(b[0].get_y() <= 5 || b[0].get_y() >= 55) {
         b[0].invert_spy();
       }
    }
    else if(passe10 == 4) {
       b[3].display();
       if(b[3].get_xc() >= 110 || b[3].get_xc() <= 10)
        b[3].invert_spx();
       if(b[3].get_y() >= 45 && b[3].get_y() <= 55) {
         b[3].set_spy(random(-0.8, -0.1));
       }
       else if(b[3].get_y() <= 15 && b[3].get_y() >= 5) {
         b[3].set_spy(random(0.1, 0.8));
       }
       else
         b[3].set_spy(random(-0.8, 0.8)); 
       if(b[3].get_y() <= 5 || b[3].get_y() >= 55) {
         b[3].invert_spy();
       }
    }
    for(cont = 0; cont < 10; cont++) {
      if(verif2[cont] == 1) { 
         n1[cont].display();
         if(ce == true && be == true && y_sl2 <= 65) {
           if(n1[cont].get_y() >= 45 && n1[cont].get_y() <= 55 && cont >= 6) {
             n1[cont].set_spy(random(-0.8, -0.1));
           }
           else if(n1[cont].get_y() <= 15 && n1[cont].get_y() >= 5 && cont >= 6) {
             n1[cont].set_spy(random(0.1, 0.8));
           }
           else if(cont >= 6)
             n1[cont].set_spy(random(-0.8, 0.8)); 
           if(((n1[cont].get_y() <= 5 && n1[cont].get_y() >= -1000) || (n1[cont].get_y() >= 55 && n1[cont].get_y() <= 1000)) && cont >= 6) {
             n1[cont].invert_spy();
           }
           if(cont < 6 && verif3[cont] == 0) {
             n1[cont].set_spy(-0.6);
             if(n1[cont].get_y() < 5) {
               n1[cont].change_pg(pg9);
               verif3[cont] = 1;
               n1[cont].set_yc(random(95, 105));
             }
           }
         }
         else if(ce == false || (ce == true && y_sl2 > 65)) {
            if(n1[cont].get_y() >= 45 && n1[cont].get_y() <= 55) {
               n1[cont].set_spy(random(-0.8, -0.1));
             }
             else if(n1[cont].get_y() <= 15 && n1[cont].get_y() >= 5) {
               n1[cont].set_spy(random(0.1, 0.8));
             }
             else
               n1[cont].set_spy(random(-0.8, 0.8)); 
             if((n1[cont].get_y() <= 5 && n1[cont].get_y() >= -1000) || (n1[cont].get_y() >= 55 && n1[cont].get_y() <= 1000)) {
               n1[cont].invert_spy();
             }
         } 
         
         
         if(passe10 == 2) {
           if(n1[cont].get_xc() >= b[0].get_xc() - 3 && n1[cont].get_xc() <= b[0].get_xc() + 3 && n1[cont].get_yc() >= b[0].get_yc() - 3 && n1[cont].get_yc() <= b[0].get_yc() + 3 && cont >= 6) {
              passe10 = 3;
              if(neut != -1 && n1[neut].get_yc() == -1100) {
                neut2 = neut;
                neut = cont;
              }
              else {
                neut2 = neut = cont;
              }
              b[0].set_yc(1100);
              n1[cont].set_yc(-1100);
              n1[cont].set_spy(0);
              n1[cont].set_spx(0);
           }
         }
      }
    }
     
    pg10.endDraw();
    
    pg11.beginDraw();
    pg11.background(183, 232, 180, 100);
    
    for(cont = 6; cont < 12; cont++) {
      
      if(passe13[cont] == 3 || passe13[cont] == 4) {
        v2[cont].display(); 
        if(v2[cont].get_xc() >= 110 || v2[cont].get_xc() <= 10)
            v2[cont].invert_spx();
      }
      if(passe13[cont] == 3) {
         if(v2[cont].get_y() >= 100 && v2[cont].get_y() <= 110)
           v2[cont].set_spy(random(-0.8, -0.1));
         else if(v2[cont].get_y() <= 20 && v2[cont].get_y() >= 10)
           v2[cont].set_spy(random(0.1, 0.8));
         else
           v2[cont].set_spy(random(-0.8, 0.8)); 
         if(v2[cont].get_y() <= 10 || v2[cont].get_y() >= 110)
           v2[cont].invert_spy();
      }
      else if(passe13[cont] == 4) {
         v2[cont].set_spy(random(-0.6, -0.8));
         v2[cont].set_spx(random(-0.4, 0.4));
         if(v2[cont].get_y() < 10) {
           v2[cont].change_pg(pg10);
           passe13[cont] = 5;
           v2[cont].set_yc(50);
         }
      }
    }
    //elétron proveniente da fonte entre a base e o emissor
    if(passe11 == 3 && passe10 != 4) {
       b[3].display();
       if(b[3].get_xc() >= 110 || b[3].get_xc() <= 10)
        b[3].invert_spx();
       if(b[3].get_y() >= 100 && b[3].get_y() <= 110 && passe10 != 3) {
         b[3].set_spy(random(-0.8, -0.1));
       }
       else if(b[3].get_y() <= 20 && b[3].get_y() >= 10 && passe10 != 3) {
         b[3].set_spy(random(0.1, 0.8));
       }
       else if(passe10 == 3) {
         if(b[3].get_yc() >= 10) {
           b[3].set_spy(random(-0.6, -0.8));
           b[3].set_spx(random(-0.4, 0.4));
         }
         else {
           passe10 = 4;
           b[3].change_pg(pg10);
           b[3].set_xc(60);
           b[3].set_yc(54);
         }
       }
       else if(passe10 != 4)
         b[3].set_spy(random(-0.8, 0.8)); 
       if((b[3].get_y() <= 10 || b[3].get_y() >= 110) && passe10 != 3 && passe10 != 4) {
         b[3].invert_spy();
       }
    }
    
    for(i = 0; i < 20; i++) {
      if(play5 == true)
        n1[i].mover();
      n1[i].display();
    }
    
    for(cont = 0; cont < 20; cont++)
      if(n1[cont].get_xc() >= 110 || n1[cont].get_xc() <= 10)
        n1[cont].invert_spx();
    for(cont = 10; cont < 20; cont++) {
     if(n1[cont].get_y() >= 100 && n1[cont].get_y() <= 110) {
       n1[cont].set_spy(random(-0.8, -0.1));
     }
     else if(n1[cont].get_y() <= 20 && n1[cont].get_y() >= 10) {
       n1[cont].set_spy(random(0.1, 0.8));
     }
     else
       n1[cont].set_spy(random(-0.8, 0.8)); 
     if(n1[cont].get_y() <= 10 || n1[cont].get_y() >= 110) {
       n1[cont].invert_spy();
     }
    }
    if(be == false || (be == true && y_sl >= 290)) {
      for(cont = 0; cont < 10; cont++) {
       if(n1[cont].get_y() >= 100 && n1[cont].get_y() <= 110) {
         n1[cont].set_spy(random(-0.8, -0.1));
       }
       else if(n1[cont].get_y() <= 20 && n1[cont].get_y() >= 10) {
         n1[cont].set_spy(random(0.1, 0.8));
       }
       else
         n1[cont].set_spy(random(-0.8, 0.8)); 
       if(n1[cont].get_y() <= 10 || n1[cont].get_y() >= 110) {
         n1[cont].invert_spy();
       }
      } 
    }
    else {
      for(cont = 0; cont < 10 && verif2[cont] == 0; cont++) {
        n1[cont].set_spy(random(-0.4, -0.8));
      }
      for(cont = 0; cont < 10; cont++) {
        if(n1[cont].get_y() < 5 && verif2[cont] == 0) {
          verif2[cont] = 1;
          n1[cont].change_pg(pg10);
          n1[cont].set_yc(54);
        }
      }
    }
    
    pg11.endDraw();
    
    pg8.beginDraw();
    pg8.pushMatrix();
    pg8.translate(0, -20);
    pg8.smooth();
    pg8.background(255);
    pg8.image(pg9, 80, 60);
    pg8.image(pg10, 80, 195);
    pg8.image(pg11, 80, 270 - 65/8);
    pg8.noStroke();
    pg8.fill(255, 254, 211);
    pg8.rect(80, 255, 120, 15 - (65 - (y_sl - 290))/8); //diminuirá
    pg8.rect(80, 180 - (100 - (y_sl2 - 65))/40, 120, 15 + (100 - (y_sl2 - 65))/20);
    pg8.fill(206, 206, 206);
    pg8.stroke(206, 206, 206);
    pg8.strokeJoin(BEVEL);
    pg8.strokeWeight(3);
    pg8.rect(80, 390 - 65/8, 120, 6);
    pg8.rect(80, 54, 120, 6);
    pg8.rect(74, 195, 6, 60);
    pg8.stroke(180, 180, 180);
    //cabos
    pg8.line(75, 225, 40, 225);
    pg8.line(40, 225, 40, 418);
    pg8.line(40, 418, 140, 418);
    pg8.line(140, 418, 140, 395 - 65/8);
    pg8.line(140, 418, 250, 418);
    pg8.line(250, 418, 250, 30);
    pg8.line(250, 30, 140, 30);
    pg8.line(140, 30, 140, 55);
    pg8.strokeWeight(2); //fontes de tensão
    pg8.rect(20, 273, 40, 100);
    pg8.rect(230, 50, 40, 130);  
    pg8.stroke(64, 72, 211);
    pg8.strokeWeight(6);
    pg8.line(40, 290, 40, 355);
    pg8.stroke(224, 66, 66);
    pg8.line(250, 65, 250, 165); 
    pg8.stroke(160, 160, 160);
    pg8.strokeWeight(8);
    if(be == true && y_sl >= 290)
      y_sl = y_sl - 0.4;
    pg8.line(32, y_sl, 48, y_sl);
    if(ce == true && y_sl2 > 65)
      y_sl2 = y_sl2 - 0.4;
    pg8.line(242, y_sl2, 258, y_sl2);
    
    if(ce == true) {
      passe12 = 1;
      for(i = 0; i < 6; i++) {
        if(passe13[i] == 3 && passe13[i+6] == 3)
          passe13[i+6] = 4;
        if(verif3[i] == 0)
          passe12 = 0;
      }
      if(passe12 == 1) {
       if(t5 == 0)
         t5 = millis();
      }
      if(t - t5 > 5000 && t5 != 0) {
       
       for(i = 0; i < 6; i++) {
         
         if(v2[i].get_yc() <= 45 && verif4[i] == 0) {
           verif4[i] = 1;
         }
         if(verif4[i] == 1) //verificador para garantir que a lacuna já tenha "saído" do gerador
           v2[i].display_lacuna();
         if(v2[i].get_yc() <= 38 && passe13[i] == 0) {
           passe13[i] = 1;
           v2[i].set_spx(-0.4);
           v2[i].set_spy(0);
         }
         else if(passe13[i] == 1 && v2[i].get_xc() <= 148) {
           v2[i].change_pg(pg9);
           v2[i].set_xc(60);
           v2[i].set_yc(15);
           v2[i].set_spx(random(-0.4, 0.6));
           passe13[i] = 2;
         }
         if(play5 = true)
           v2[i].mover();
       }
       for(i = 6; i < 12; i++) {
         if(play5 = true)
           v2[i].mover();
         if(v2[i].get_yc() >= 185 && verif4[i] == 0) {
           verif4[i] = 1;
         }
         if(verif4[i] == 1) //verificador para garantir que o elétron já tenha "saído" do gerador
           v2[i].display();
         if(v2[i].get_yc() >= 410 && passe13[i] == 0) {
           passe13[i] = 1;
           v2[i].set_spx(-0.6);
           v2[i].set_spy(0);
         }
         else if(passe13[i] == 1 && v2[i].get_xc() <= 148) {
           v2[i].set_spx(0);
           v2[i].set_spy(-0.6);
           passe13[i] = 2;
         }
         else if(passe13[i] == 2 && v2[i].get_yc() <= 392) {
           passe13[i] = 3;
           v2[i].change_pg(pg11);
           v2[i].set_xc(60);
           v2[i].set_yc(105);
           v2[i].set_spx(random(0.4, 0.6));
         }
       }
      }
      passe14 = 1;
      for(i = 6; i < 12; i++)
        if(passe13[i] != 6)
          passe14 = 0;
      if (t - t5 > 15000 && t5 != 0 && passe14 == 1) { //AQUI REINICIAR TUDO
        t5 = 0;
        for(i = 0; i < 6; i++) {
          n2[neut4[i]].set_yc(v2[i+6].get_yc()); //rever
          n2[neut4[i]].set_xc(v2[i+6].get_xc());
          v2[i].set_yc(45 + 200*i);
          v2[i].set_spy(-0.4);
          neut3[i] = neut4[i] = -1;
        }
        for(i = 6; i < 12; i++) {
          v2[i].set_yc(185 - 200*(i - 6));
          v2[i].set_spy(0.4);
        }
          for(i = 0; i < 12; i++) {
          passe13[i] = 0;
          v2[i].change_pg(pg8);
          verif4[i] = 0;
          v2[i].set_xc(242);
          v2[i].set_spx(0);
        }
      }
    }
    
    if(be == true) {
      t = millis();
      passe9 = 1;
      for(i = 0; i < 10; i++) {
        if(verif2[i] == 0)
          passe9 = 0;
      }
      if(passe9 == 1) {
       if(t4 == 0)
         t4 = millis();
      }
      if(t - t4 > 5000 && t4 != 0) {
       if(play5 == true)
       b[0].mover();
       b[3].mover();
       b[3].display();
       b[0].display_lacuna();
       //as bordas mudam ?????
       if(b[0].get_yc() <= 218 && passe10 == 0) {
         passe10 = 1;
         b[0].set_spx(0.4);
         b[0].set_spy(0);
       }
       else if(passe10 == 1 && b[0].get_xc() >= 75) {
         b[0].change_pg(pg10);
         b[0].set_xc(15);
         b[0].set_yc(30);
         passe10 = 2;
       }
       if(b[3].get_yc() >= 425 && passe11 == 0) {
         passe11 = 1;
         b[3].set_spx(0.6);
         b[3].set_spy(0);
       }
       else if(passe11 == 1 && b[3].get_xc() >= 148) {
         passe11 = 2;
         b[3].set_spx(0);
         b[3].set_spy(-0.6);
       }
       else if(passe11 == 2 && b[3].get_yc() <= 395 - (65 - (y_sl - 290))/8) {
         passe11 = 3;
         b[3].change_pg(pg11);
         b[3].set_xc(60);
         b[3].set_yc(105);
       } 
      }
      if (t - t4 > 30000 && t4 != 0 && passe10 == 4) { //AQUI REINICIAR TUDO
        t4 = 0;
        passe10 = 0;
        passe11 = 0;
        n1[neut2].set_yc(b[3].get_yc());
        n1[neut2].set_xc(b[3].get_xc());
        b[0].change_pg(pg8);
        b[3].change_pg(pg8);
        b[0].set_xc(32);
        b[3].set_xc(32);
        b[0].set_yc(270);
        b[3].set_yc(376);
        b[0].set_spx(0);
        b[0].set_spy(-0.4);
        b[3].set_spx(0);
        b[3].set_spy(0.6);
      }
    }
    pg8.popMatrix();
    pg8.endDraw();
    image(pg8, 25, 105);
    fill(0);
    textFont(fonte1);
    text("Operação intrínseca do TJB", 232, 96);
    textFont(fonte2);
    text("Emissor (n)", 355, 415);
    text("Base (p)", 355, 315);
    text("Coletor (n)", 355, 215);
    text("Fonte 1", 65, 295);
    text("Fonte 2", 330, 130);
    textFont(fonte1);
    println(y_sl2);
    if(t7 == 0 && y_sl <= 290 && y_sl2 <= 65)
      t7 = millis();
    t = millis();
    if(play5 == false) {
      text("Iniciar", 226, 547); 
      image(forw, 250, 526, 32, 32);
    }

    else if((t - t7 < 60000 && t7 != 0) || t7 == 0) {
      if(y_sl > 290) {
        textFont(fonte7);
        text("Com as fontes de tensão 1 e 2 desligadas, as junções p-n base-emissor e base-coletor estão em aberto e o transistor não conduz." +
       " Clique sobre o slider da fonte de tensão 1 para polarizar a junção base-emissor diretamente, fazendo-a conduzir.", 32, 515, 410, 200);
      }
      if(y_sl <= 290 && y_sl2 > 65) {
        textFont(fonte7);
        text("Os elétrons se acumulam na base, que está em um potencial mais elevado. A corrente de base é pequena devido à alta resistividade/baixa dopagem dessa região. Não há corrente de coletor pois a junção base-coletor está em aberto. Clique sobre o slider da fonte de tensão 2 para polarizá-la reversamente.", 32, 515, 410, 200);
      }
      if(y_sl <= 290 && y_sl2 <= 65) {
        textFont(fonte7);
        text("O coletor está em um potencial mais elevado do que o da base. Assim, os elétrons acumulados na base se deslocam para o coletor. A corrente de coletor é mais intensa que a de base porque o coletor é fortemente dopado, e, portanto, tem baixa resistividade. O transistor está operando no modo ativo.", 32, 515, 410, 200);
      }
    }
    else if(t - t7 >= 60000 && t7 != 0) {
      image(ret, 273, 532, 24, 24);
      textFont(fonte1);
      text("Reiniciar", 230, 550);
    }
    textFont(fonte4);
    text("0,7 V", 67, 369);
    text("0 V", 65, 455);
    text("5 V", 275, 145);
    text("0 V", 275, 263);
    
    strokeWeight(4.8);
    noFill();
    stroke(220, 220, 220);
    strokeJoin(BEVEL);
    rect(25, 105, 420, 480);
    //resolvido aqui
    fill(206, 206, 206);
    stroke(206, 206, 206);
    strokeWeight(3);
    rect(105, 475 - 65/8, 120, 6);
    rect(105, 139, 120, 6);
    rect(99, 280, 6, 60);
    stroke(180, 180, 180);
    strokeWeight(2);
    //Sinais de + e - da fonte 1 aqui
    line(47, 345, 55, 345);
    line(51, 349, 51, 341);
    line(47, 468, 55, 468);
    pushMatrix();
    translate(200, -220);
    //Sinais de + e - da fonte 2
    line(47, 345, 55, 345);
    line(51, 349, 51, 341);
    line(47, 495, 55, 495);
    popMatrix();
    strokeWeight(2);
    noFill();
    rect(45, 358, 40, 100);
    rect(255, 135, 40, 130);
  }
  else if(window == 5) {
   configs(5);
   pg1.beginDraw();
   pg1.pushMatrix();
   pg1.translate(-100, 0);
   pg1.background(255);
   pg1.smooth();
   pg1.stroke(0);
   pg1.strokeWeight(2);
   pg1.fill(0);
   pg1.strokeWeight(1);
   pg1.triangle(297, 309, 304, 298, 310, 310);
   pg1.triangle(302, 65, 318, 65, 310, 50);
   pg1.ellipse(352, 200, 8, 8);
   pg1.ellipse(352, 345, 8, 8);
   pg1.ellipse(260, 250, 8, 8);
   pg1.textFont(fonte2);

   pg1.rect(300, 120, 20, 40);
   pg1.rect(200, 260, 40, 20);
   pg1.strokeWeight(4);
   pg1.line(280, 240, 280, 300);
   pg1.strokeWeight(3);
   pg1.line(260, 270, 260, 250);
   pg1.line(160, 270, 160, 335);
   pg1.line(160, 335, 310, 335);
   pg1.line(280, 270, 160, 270);
   pg1.line(310, 200, 350, 200);
   pg1.line(310, 345, 350, 345);
   pg1.line(310, 230, 280, 250);
   pg1.line(310, 310, 280, 290);
   pg1.line(310, 310, 310, 380);
   pg1.line(295, 380, 325, 380);
   pg1.line(300, 387, 320, 387);
   pg1.line(305, 394, 315, 394);
   pg1.line(310, 230, 310, 65);
   if(op2 == 'A') {
     if(vb > 0.7 && vb <= 5) {
       v_b = 0.7;
       ib = (vb - 0.7)/200;
       ic = 100*ib;
       ie = ic + ib;
       v_c = 5 - 2*ic;

     }
     else if(vb < 0.7) {
       ib = 0;
       ic = 0;
       ie = 0;
       v_c = 5;
       v_b = vb;

     }
     else if(vb > 5) {
      v_c = 0;
      ic = 2.5;
      v_b = 0.7;
      ib = (vb - v_b)/200;
      ie = ic + ib;
   
     } 
     pg1.strokeWeight(2);
     pg1.fill(177, 195, 203);
     pg1.rect(190, 315, 80, 40);
     pg1.fill(255);
     pg1.rect(195, 320, 50, 30);
     pg1.fill(66, 70, 72);
     pg1.triangle(257, 320, 249, 330, 265, 330);
     pg1.triangle(257, 350, 249, 340, 265, 340);
     pg1.stroke(180, 180, 180);
     pg1.line(175, 320, 185, 320);
     pg1.line(180, 315, 180, 325);
     pg1.line(275, 320, 285, 320);
     
   }
   else {
    pg1.fill(255); 
    pg1.noStroke();
    pg1.rect(170, 330, 40, 20);
    pg1.stroke(0);
    pg1.line(310, 355, 260, 355);
    pg1.line(260, 362, 260, 348);
    pg1.line(252, 368, 252, 342);
    pg1.line(200, 355, 252, 355);
    pg1.fill(0);

    pg1.strokeWeight(1);
    pg1.ellipse(200, 355, 8, 8);
    pg1.ellipse(170, 335, 8, 8);
    pg1.ellipse(210, 335, 8, 8);
    pg1.strokeWeight(2);
    pg1.line(375, 286, 383, 286);
    pg1.line(379, 282, 379, 290);
    pg1.line(400, 286, 408, 286);

    if(chave == false) {
      pg1.line(170, 335, 210, 335);
      pg1.image(led_off, 382, 236, 19, 50);
      pg1.line(352, 200, 352, 300);
      pg1.line(352, 300, 389, 300);
      pg1.line(389, 300, 389, 280);
      pg1.line(352, 345, 397, 345);
      pg1.line(397, 345, 397, 280);
      
    }
    else {
      pg1.line(352, 200, 352, 300);
      pg1.line(352, 300, 389, 300);
      pg1.line(389, 300, 389, 280);
      pg1.line(352, 345, 397, 345);
      pg1.line(397, 345, 397, 280);
      pg1.line(170, 335, 200, 355);
      pg1.image(led_on, 370, 230, 40, 84.8);
    }
    pg1.fill(255);
    pg1.noStroke();
    pg1.rect(340, 220, 20, 8); 
    pg1.stroke(0);
    pg1.strokeWeight(2);
    pg1.line(347, 220, 357, 220);
    pg1.line(339, 228, 365, 228);
   }
   pg1.popMatrix();
   pg1.endDraw();
   image(pg1, 25, 105);
   strokeJoin(BEVEL);
   noFill();
   strokeWeight(4.8);
   stroke(220, 220, 220);
   rect(25, 105, 400, 475);
   textFont(fonte1);
   fill(0);
   textAlign(LEFT);
     
   if(op2 == 'B') {
    fill(0);
    text("O TJB como chave eletrônica", 93, 95);
    textFont(fonte2);
    text("6 V", 170, 493);
    text("Chave C", 90, 430);
    text("Modos de operação do TJB", 140, 570);
    image(back, 110, 548);
    textFont(fonte4);
    text("2", 125, 477);
    text("1", 125, 451);
    textFont(fonte7);
    textAlign(CENTER);
    text("Clique sobre a chave C para observar a operação do TJB como chave eletrônica.", 26, 517, 400, 150);
    if(chave == false) {
      text("Com a chave na posição 1, a tensão na base é nula e o TJB está em corte. Dessa forma, o circuito do LED fica em aberto e o LED não acende.", 272, 120, 140, 200);
    }
    else {
      text("Com a chave na posição 2, a tensão na base é grande o bastante para que o TJB sature (analise a aba 'Modos de operação do TJB' desta animação). Entre os terminais de coletor e emissor se forma um 'curto-circuito virtual' e, assim, o LED acende.", 35, 118, 180, 200);
    }
    textAlign(LEFT);
   }
   else {
     fill(0);
     text("Modos de operação do TJB", 105, 95);
     textFont(fonte2);
     text("O TJB como chave eletrônica", 110, 567);
     image(forw, 305, 548);
     text(nf(vb, 1, 2) + " V", 125, 445);
     text("Vb = " + nf(v_b, 1, 2) + " V\nVc = " + nf(v_c, 1, 2) + " V\nIb = " + nf(ib, 1, 4) + " mA\nIc = " + nf(ic, 1, 4) + " mA\nIe = " + nf(ie, 1, 4) + " mA", 40, 145);
     textFont(fonte7);
     textAlign(CENTER);
     text("Utilize as setas da fonte de tensão variável para aumentar ou diminuir a tensão de base e alterar o modo de operação do TJB.", 26, 508, 400, 150);
     textAlign(LEFT);
     if(vb > 0.7 && vb <= 5) {
       textFont(fonte1);
       fill(0, 0, 255);
       text("Modo ativo", 288, 140);
       fill(0);
       textFont(fonte7);
       textAlign(CENTER);
       text("Neste modo, o TJB conduz e as correntes de coletor e de base guardam uma relação de proporcionalidade.", 258, 160, 160, 200);
       textAlign(LEFT);
     }
     else if(vb < 0.7) {
       textFont(fonte1);
       fill(0, 0, 255);
       text("Corte", 310, 140);
       fill(0);
       textFont(fonte7);
       textAlign(CENTER);
       text("Neste modo, o TJB não conduz e produz uma tensão em aberto entre o coletor e o emissor. ", 258, 160, 160, 200);
       textAlign(LEFT);
     }
     else if(vb > 5) {
      textFont(fonte1);
      fill(0, 0, 255);
      text("Saturação", 298, 130);
      fill(0);
      textFont(fonte7);
      textAlign(CENTER);
      text("Neste modo, o TJB conduz, mas as correntes de coletor e de base não são proporcionais.", 262, 140, 160, 200);
      text("A tensão entre coletor e emissor é nula, ou seja, esses terminais funcionam como um curto-circuito.", 280, 188, 125, 200);
      textAlign(LEFT);
     } 
   }
   fill(0);
   textFont(fonte2);
   text("Vb", 177, 339);
   text("200 K", 110, 351);
   image(omega, 149, 339, 13, 13);
   text("Vc", 292, 292);
   text("Ve = 0 V", 275, 475);
   if(op2 == 'A') {
     text("2 K", 180, 252);
     image(omega, 204, 240, 13, 13);
   }
   else {
     text("2 K", 250, 252);
     image(omega, 274, 240, 13, 13);
   }
   text("   = 100", 215, 380);
   image(beta, 210, 365, 20, 20);
   text("+5 Vcc", 212, 145);
    //scrollframe 5
    sf5.display();
    sf5.beginVADraw();
  
  
    sf5.getVA().fill(0);
    sf5.getVA().background(223, 236, 242, 150);
  
   
    sf5.getVA().textFont(fonte2);
    sf5.getVA().text("Esta animação possui duas abas: 'Modos de operação do TJB' e 'O TJB como chave eletrônica'." +
    "Em ambas as abas, o TJB representado na forma circuital é do tipo npn e o fator de proporcionalidade entre a corrente de coletor e a de base, denominado beta, vale 100. Nas representações circuitais de TJBs npn ou pnp, o emissor é sempre a extremidade do transistor onde está desenhada a seta indicativa do sentido da corrente elétrica. Assim, na aba 'Modos de operação do TJB', Ve indica o potencial" +
    " do emissor. Analogamente, Vb e Vc são os potenciais de base e coletor. Ib, Ic e Ie se referem às correntes de base, coletor e emissor, respectivamente.\nNesta aba, utilize a fonte de tensão variável para elevar ou reduzir a tensão aplicada ao resistor da base do TJB e observar a sua influência no modo de operação do transistor e nas demais tensões e correntes dos circuito.\n" +
    "No modo de operação ativo, a junção base-emissor deve estar diretamente polarizada e a junção base-coletor reversamente polarizada. Portanto, nessa situação Vc > Vb > Ve. Pelo circuito, Ve = 0. Como a junção base-emissor está diretamente polarizada, a diferença de tensão Vb - Ve vale aproximadamente 0,7 V. Este potencial corresponde ao potencial de barreira da junção p-n/base-emissor para dispositivos de silício em temperaturas inferiores à temperatura máxima de operação, que varia entre 150°C e 200°C. " +
    "Logo, Vb = 0,7 V no modo ativo.\nConforme ilustrado na aba 'O TJB', no transistor npn a corrente de base tem sentido para dentro da base. Assim, se o potencial aplicado ao resistor da base for inferior ao potencial típico da base no modo ativo - 0,7 V -, não fluirá corrente pela base. Consequentemente, Vb assumirá o valor do potencial ajustado na fonte de tensão variável, pois, na ausência de corrente, não há queda de tensão no resistor da base. Nessas condições, ambas as junções p-n (base-emissor e base-coletor) estão reversamente polarizadas e, portanto, o transistor está em corte. Abaixe a tensão aplicada ao resistor da base para 0,5 V ou menos para verificar esse fato. Note que, por não haver corrente de coletor, Vc = 5 V e o transistor se comporta como uma chave aberta entre os terminais do coletor e do emissor.\n" +
    "A análise anterior permite concluir que o potencial aplicado ao resistor da base tem influência sobre a corrente de base. Como, no modo ativo, Ic e Ib são proporcionais, isto é, Ic =      x Ib, a influência do potencial do resistor da base se estende à corrente de coletor e, consequentemente, à tensão no coletor. Se o potencial fornecido pela fonte de tensão for elevado a valores que determinem corrente de coletor grande o bastante para que Vc < Vb (sendo que Vb = 0,7 V, porque a junção base-emissor se mantém diretamente polarizada), o transistor saturará. Na saturação, ambas as junções p-n (base-emissor e base-coletor) estão diretamente polarizadas e, como Vb = 0,7 V, Vc passa a valer 0 V. Eleve a tensão na fonte variável para valores superiores a 5,5 V para verificar esse fato. Note que, como Vc = Ve = 0 V, na saturação o trasnsistor se comporta como um curto-circuito ou uma chave fechada entre os terminais do coletor e do emissor. \n" +
    "A aba 'O TJB como chave eletrônica' trata do funcionamento do transistor como chave eletrônica. Entre o coletor e o emissor, há uma bateria DC e um LED (diodo emissor de luz). A tensão fornecida pela bateria é a tensão nominal do LED. O LED só acende quando o circuito é fechado, isto é, quando o transistor atua como um curto circuito. Isto acontece na saturação. Quando o transistor funciona como uma chava aberta - no corte -, o LED permanece apagado. Utilize a chave C para controlar o funcionamento do transistor como chave. Quando a chave C estiver na posição 1, o transistor estará saturado, pois a bateria de 6 V impõe Ib grande o bastante para que Vc < Vb, como verificamos na análise realizada na aba 'Modos de operação do TJB'. Note que o LED acende. Quando a chave C é passada para a posição 2, a queda de tensão no resistor não condiz com o sentido de Ib, que deve ser para dentro da base. Assim, não há corrente na base e o transistor está em corte, funcionando como chave aberta. Por isso, o LED não acende.", 10, 20, 270, 2200);
    sf5.getVA().image(beta, 178, 1169, 20, 20);
    sf5.endVAdraw();
    rectMode(CORNER);
    imageMode(CORNER);
  }
  else if(window == 6) {
    configs(window);
    fill(0);
    textFont(fonte1);
    text("Este tutorial se divide em cinco abas de teoria e quatro de informações extras, identificadas pelo nome na porção superior da tela. Para acessar uma aba, clique no botão em forma de seta correspondente. Cada uma das abas aborda conceitos relacionados a diodos e transistores de junção bipolar, compreendendo desde aspectos de sua fabricação, a partir de materiais semicondutores, até o seu funcionamento propriamente dito.\nTodas as abas contêm uma ou mais animações e um texto explicativo de apoio. As instruções referentes a cada uma delas estão presentes nas próprias animações, que também contam com uma breve descrição dos elementos e processos nelas ilustrados. Se você tiver dúvidas ou quiser saber mais, consulte o texto de apoio.\n\nA aba 'O silício e dopantes do tipo p e n' trata da distribuição atômica do silício e explora a possibilidade de alteração de sua condutividade pela dopagem com boro (p) ou fósforo (n). Esta aba se divide em três sub abas sequenciais, que podem ser acessadas clicando-se nos botões de prosseguir (      ) e voltar (      ), identificados com o nome da sub aba de destino, na parte inferior da tela.\n\n", 60, 110, 680, 500);
    image(forw, 570, 508);
    image(back, 692, 507);
  }
  else if(window == 7) {
    configs(window);
    strokeWeight(1);
    image(esfera, 100, 170, 30, 30);
    fill(252, 74, 74, 100);
    stroke(252, 74, 74, 100);
    ellipse(114, 220, 15, 15);
    
    image(esfera2, 100, 270, 30, 30);
    fill(25, 117, 165);
    stroke(25, 117, 165);
    ellipse(114, 320, 15, 15);
    fill(192, 237, 237, 200);
    stroke(192, 237, 237, 200);
    ellipse(114, 350, 15, 15);
    
    image(esfera3, 100, 400, 30, 30);
    fill(64, 147, 46);
    stroke(64, 147, 46);
    ellipse(114, 450, 15, 15);
    fill(183, 232, 180, 200);
    stroke(183, 232, 180, 200);
    ellipse(114, 480, 15, 15);
    
    legenda1.display();
    legenda2.display_lacuna();
    
    fill(192, 237, 237, 200);
    stroke(0);
    rect(430, 290, 20, 20);
    fill(183, 232, 180, 200);
    stroke(0);
    rect(430, 350, 20, 20);
    fill(250, 245, 154, 80);
    stroke(0);
    rect(430, 410, 20, 20);
    
    line(430, 475, 450, 475);
    fill(0);
    triangle(450, 475, 444, 471, 444, 479);
    
    image(colchete, 170, 170, 20, 60);
    image(colchete, 170, 275, 20, 80);
    image(colchete, 170, 405, 20, 80);
    
    textFont(fonte2);
    text("Átomos de silício", 270, 204);
    text("Átomos de boro", 267, 320);
    text("Átomos de fósforo", 269, 452);
    text("Elétron", 606, 185);
    text("Lacuna eletrônica", 608, 244);
    text("Sílicio tipo p", 608, 305);
    text("Sílicio tipo n", 608, 366);
    text("Camada de depleção", 608, 426);
    text("Campo elétrico", 608, 480);
    
  }
  else if(window == 8) {
    configs(window);
    textFont(fonte7);
    fill(0);
    text("SEDRA, Adel S.; SMITH, Kenneth C., Microeletrônica. 5a. ed. São Paulo: Pearson Prentice Hall, 2007.\n\nHonsberg, Christiana; Bowden, Stuart. 'Band Gap'. Photovoltaic Education.\nhttp://www.pveducation.org/pvcdrom/pn-junction/band-gap. Acesso em 10 set. 2012\n\n'Introdução aos Semicondutores'.\nhttp://aquarius.ime.eb.br/~aecc/FundEngEle/Semicondutores.pdf. Acesso em 10 set. 2012\n\nCoates, Eric. 'Bipolar Junction Transistors (BJTs)'. Learn about-Electronics\nhttp://www.learnabout-electronics.org/bipolar_junction_transistors_01.php. Acesso em 10 set. 2012", 20, 100, 365, 400);
    text("Chandler, Nathan. 'How Transistors Work'. How Stuff Works.\nhttp://electronics.howstuffworks.com/transistor4.htm. Acesso em 15 set. 2012\n\nDutra, Daniel A.; Mayrinck, Igor B.; Costa Jr., Marcos E.; Holder, Robinson P.; Conhalata, Rodrigo S.; Silveira, Sandro E. 'Caracterização da Variação de Parâmetros de Diodos e Transistores em Função da Temperatura'.\nhttp://www.cpdee.ufmg.br/~jramirez/disciplinas/materiais/trab6.pdf. Acesso em 2 nov. 2012", 415, 100, 365, 400);
    textFont(fonte2);
    text("Imagens e ícones:", 30, 386, 740, 500);
    textFont(fonte7);
    text("http://wallpaperdojo.com/3Dwallpapers/Wallpaperdojo_3D_Spheres_Wallpaper_Black_White_20/3Dwallpapers-wallpapers.html. Acesso em 10 set. 2012\n\nhttp://acriticaestaqui.blogspot.com.br/2012/02/livro-dom-casmurro.html. Acesso em 25 jan. 2013\n\nhttp://en.wikipedia.org/wiki/Wikipedia:Wikipedia_logos. Acesso em 25 jan. 2013", 50, 432, 325, 300);
    text("http://icons.iconarchive.com/icons/fatcow/farm-fresh/32/resultset-next-icon.png. Acesso em 20 dez. 2012\n\nhttp://www.iconesbr.net/down_ico/1408/lupa. Acesso em 9 dez. 2012\n\nhttp://www.iconesbr.net/down_ico/6980/setas. Acesso em 28 jan. 2013", 425, 432, 325, 300);
    image(esfera, 20, 435, 15, 15);
    image(beta, 20, 498, 16, 16);
    image(omega, 20, 550, 10, 10);
    image(forw, 395, 429, 22, 22);
    image(lupa, 395, 482, 18, 18);
    image(ret, 399, 532, 16, 16);
  }
  else if(window == 9) {
    configs(window);
    textFont(fonte2);
    text("Desenvolvimento: Luana Ianara Rubini Ruiz (Aluna do 3º ano do Curso de Engenharia Elétrica - Sistemas Eletrônicos, Escola Politécnica da Universidade de São Paulo, São Paulo - SP, Brasil)\nOrientação: Ivette Frida Cymbaum Oppenheim (Departamento de Engenharia Metalúrgica e de Materiais, Escola Politécnica da Universidade de São Paulo, São Paulo - SP, Brasil)\nTexto: Luana Ianara Rubini Ruiz e Ivette Frida Cymbaum Oppenheim\nCriado em: Processing                    Última atualização: 24/janeiro/2013", 60, 128, 680, 420);
    text("\nApoio: O tutorial 'Diodos e Transistores de Junção Bipolar' foi desenvolvido no contexto do projeto 'Animações para disciplinas Introdutórias de Ciência e Engenharia dos Materiais', integrante do 'Programa Ensinar com Pesquisa' da Pró-Reitoria de Graduação da Universidade de São Paulo, à qual expressamos nossos agradecimentos pela concessão da bolsa de Iniciação Científica (Projeto 5767 - ano 2012).", 60, 258, 680, 420);  
    text("Sobre o Processing: 'Processing' é uma linguagem de programação e ambiente de desenvolvimento com foco principal nas artes visuais, aproveitando-se da plataforma Java e de uma comunidade virtual intensamente atuante por meio do compartilhamento de códigos e bibliotecas através da Internet. Surgiu como iniciativa de Casey Reas e Ben Fry, pesquisadores do Instituto de Tecnologia de Massachussets (MIT) dos E.U.A, sendo, desde sua criação em 2001, disponibilizado gratuitamente como software livre, acessível a qualquer interessado a partir do download no site ‘processing.org', onde podem ser encontradas informações adicionais sobre 'Processing'.", 60, 400, 680, 410);
  }
  
  
}
