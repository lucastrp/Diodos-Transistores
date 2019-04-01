import processing.core.*; 
import processing.xml.*; 

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.JWindow; 
import javax.swing.JPanel; 
import com.sun.awt.AWTUtilities; 
import peasy.*; 
import java.lang.reflect.*; 
import java.awt.geom.RectangularShape; 
import java.awt.geom.Rectangle2D; 
import java.awt.geom.Ellipse2D; 
import java.awt.geom.RoundRectangle2D; 
import java.awt.geom.Arc2D; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class BJ_transistor_v2 extends PApplet {







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
float speed = 0.5f, xc1 = -50, xc2 = 400, ang, sen = 5, xc12 = -80.5f, xc22 = 429.5f, xc13 = 24, xc23 = 173, mouse_x, mouse_y, vb = 2.0f;
float x_c1 = 57, x_c2 = -27, y_sl = 355, y_sl2 = 165, v_b, v_c, ib, ie, ic;
float yc13 = 15.5f, yc23 = 164.5f, ics, igrec;
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
int a = color(25, 117, 165);
int z = color(255);
PGraphics pg2, pg3, pg4, pg5, pg6, pg7, pg8, pg9, pg10, pg11, pg1;
PFont fonte1, fonte2, fonte3, fonte4, fonte5, fonte6, fonte7;
boolean r1 = false, r2 = false, on = false, play4 = false, play1 = false, play2 = false, play3 = false, play5 = false, be = false, ce = false, chave = false, help = false;
float x_sin, y_sin;
float prevX=0.0f, prevY=0.0f;
int numOfWaves = 4;
float angle_sin = 0, amplitude = 50, amplitude2 = 30;

public void setup() {
  
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
    vy[i] = random(0.7f, 1.2f);
  }
  
  for (i = 4; i < 8; i++) {
    el[i] = new Particulas(0 + 90*(i - 4), 8, 35, xc1 + 25, 120, 1, 0.5f, 0, 0, 0, pg2); 
  }
  for (i = 8; i < 12; i++) {
    el[i] = new Particulas(0 + 90*(i - 8), 8, 35, xc2 + 25, 120, -1, -0.5f, 0, 0, 0, pg2); 
  }
  for (i = 12; i < 16; i++) {
    el[i] = new Particulas(0 + 90*(i - 12), 8, 35, 150.5f, yc1 + 25, -1, 0, -0.5f, 0, 0, pg2); 
  }
  for (i = 16; i < 20; i++) {
    el[i] = new Particulas(0 + 90*(i - 16), 8, 35, 250.5f, yc1 + 25, -1, 0, -0.5f, 0, 0, pg2); 
  }
  for (i = 20; i < 24; i++) {
    el[i] = new Particulas(0 + 90*(i - 20), 8, 35, 150.5f, yc2 + 25, -1, 0, 0.5f, 0, 0, pg2); 
  }
  for (i = 24; i < 28; i++) {
    el[i] = new Particulas(0 + 90*(i - 24), 8, 35, 250.5f, yc2 + 25, -1, 0, 0.5f, 0, 0, pg2); 
  }
  for (i = 28; i < 32; i++) {
    el[i] = new Particulas(0 + 90*(i - 28), 8, 35, xc12 + 25, 120, -1, 0.5f, 0, 0, 0, pg2); 
  }
  for (i = 32; i < 36; i++) {
    el[i] = new Particulas(0 + 90*(i - 32), 8, 35, xc22 + 25, 120, -1, -0.5f, 0, 0, 0, pg2); 
  }
  for (i = 0; i < 3; i++) {
    p[i] = new Particulas(0 + 90*i, 6, 30, 120, 110, 1, 0, 0, 0, 0, pg4);
  }
  for (i = 3; i < 7; i++) {
    p[i] = new Particulas(0 + 90*(i - 3), 6, 30, xc13 + 20, 110, 1, 0.5f, 0, 0, 0, pg4);
  }
  for (i = 7; i < 11; i++) {
    p[i] = new Particulas(0 + 90*(i - 7), 6, 30, xc23 + 20, 110, -1, -0.5f, 0, 0, 0, pg4);
  }
  for (i = 11; i < 15; i++) {
    p[i] = new Particulas(0 + 90*(i - 11), 6, 30, 120, yc13 + 20, 1, 0, 0.5f, 0, 0, pg4);
  }
  for (i = 15; i < 19; i++) {
    p[i] = new Particulas(0 + 90*(i - 15), 6, 30, 120, yc23 + 20, -1, 0, -0.5f, 0, 0, pg4);
  }
  p[19] = new Particulas(270, 6, 30, 120, 110, 0, 0, 0, 0, 0, pg4);
  //mais 7 grupos de 4 el\u00e9trons
  for (i = 20; i < 24; i++) {
    p[i] = new Particulas(0 + 90*(i - 20), 6, 30, xc13 + 20, yc13 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 24; i < 28; i++) {
    p[i] = new Particulas(0 + 90*(i - 24), 6, 30, xc23 + 20, yc13 + 20, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 28; i < 32; i++) {
    p[i] = new Particulas(0 + 90*(i - 28), 6, 30, xc13 + 20, yc23 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 32; i < 36; i++) {
    p[i] = new Particulas(0 + 90*(i - 32), 6, 30, xc23 + 20, yc23 + 20, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 36; i < 40; i++) {
    p[i] = new Particulas(0 + 90*(i - 36), 6, 30, xc23 + 20 + 75, yc13 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 40; i < 44; i++) {
    p[i] = new Particulas(0 + 90*(i - 40), 6, 30, xc23 + 20 + 75, 110, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 44; i < 48; i++) {
    p[i] = new Particulas(0 + 90*(i - 44), 6, 30, xc23 + 20 + 75, yc23 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  //pg "n"
  for (i = 0; i < 4; i++) {
    n[i] = new Particulas(0 + 90*i, 6, 30, 120, 110, 1, 0, 0, 0, 0, pg4);
  }
  for (i = 4; i < 8; i++) {
    n[i] = new Particulas(0 + 90*(i - 4), 6, 30, xc13 + 20, 110, 1, 0.5f, 0, 0, 0, pg4);
  }
  for (i = 8; i < 12; i++) {
    n[i] = new Particulas(0 + 90*(i - 8), 6, 30, xc23 + 20, 110, -1, -0.5f, 0, 0, 0, pg4);
  }
  for (i = 12; i < 16; i++) {
    n[i] = new Particulas(0 + 90*(i - 12), 6, 30, 120, yc13 + 20, 1, 0, 0.5f, 0, 0, pg4);
  }
  for (i = 16; i < 20; i++) {
    n[i] = new Particulas(0 + 90*(i - 16), 6, 30, 120, yc23 + 20, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 20; i < 24; i++) {
    n[i] = new Particulas(0 + 90*(i - 20), 6, 30, xc13 + 20, yc13 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 24; i < 28; i++) {
    n[i] = new Particulas(0 + 90*(i - 24), 6, 30, xc23 + 20, yc13 + 20, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 28; i < 32; i++) {
    n[i] = new Particulas(0 + 90*(i - 28), 6, 30, xc13 + 20, yc23 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 32; i < 36; i++) {
    n[i] = new Particulas(0 + 90*(i - 32), 6, 30, xc23 + 20, yc23 + 20, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 36; i < 40; i++) {
    n[i] = new Particulas(0 + 90*(i - 36), 6, 30, xc23 + 20 + 75, yc13 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 40; i < 44; i++) {
    n[i] = new Particulas(0 + 90*(i - 40), 6, 30, xc23 + 20 + 75, 110, -1, 0, -0.5f, 0, 0, pg4);
  }
  for (i = 44; i < 48; i++) {
    n[i] = new Particulas(0 + 90*(i - 44), 6, 30, xc23 + 20 + 75, yc23 + 20, 1, 0, -0.5f, 0, 0, pg4);
  }
  n[48] = new Particulas(45, 6, 30, 120, 110, 1, 1.5f, 0.5f, 0, 0, pg4);
  
  for(i = 0; i < 3; i++) {
    port[i] = new Particulas(0, 8, 0, 144.2f - 13.8f*i, random(35, 75), 0, random(-0.4f, 0.4f), random(-0.4f, 0.4f), 0, 0, pg6);
  }
  for(i = 10; i < 13; i++) {
    port[i] = new Particulas(0, 8, 0, 20 + 13.8f*(i - 10), random(35, 75), 0, random(0.4f, 0.6f), random(-0.4f, 0.4f), 0, 0, pg7);
  }
  for(i = 3; i < 10; i++) {
    if(i > 6)
      port[i] = new Particulas(0, 8, 0, 144.2f - 13.8f*i, random(30, 80), 0, random(0.4f, 0.6f), random(-0.4f, 0.4f), 0, 0, pg6);
    else
      port[i] = new Particulas(0, 8, 0, 144.2f - 13.8f*i, random(30, 80), 0, random(-0.6f, -0.4f), random(-0.4f, 0.4f), 0, 0, pg6);
  }
  for(i = 13; i < 20; i++) {
    if(i > 16)
      port[i] = new Particulas(0, 8, 0, 20 + 13.8f*(i - 10), random(30, 70), 0, random(-0.6f, -0.4f), random(-0.4f, 0.4f), 0, 0, pg7);
    else
      port[i] = new Particulas(0, 8, 0, 20 + 13.8f*(i - 10), random(30, 70), 0, random(0.4f, 0.6f), random(-0.4f, 0.4f), 0, 0, pg7);
  }
  for(i = 0; i < 2; i++) {
    pp[i] = new Particulas(0, 8, 0, 165, 323, 0, -0.4f, 0, 0, 0, pg5);
  }
  for(i = 2; i < 4; i++) {
    pp[i] = new Particulas(0, 8, 0, 233, 323, 0, 0.4f, 0, 0, 0, pg5);
  }
  pp[4] = new Particulas(0, 8, 0, 233, 305, 0, 0.4f, 0, 0, 0, pg5);
  pp[5] = new Particulas(0, 8, 0, 165, 305, 0, -0.4f, 0, 0, 0, pg5);
  for(i = 0; i < 20; i++) {
    n1[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8f*i, 0, random(-0.4f, 0.4f), random(-0.4f, 0.4f), 0, 0, pg11);
  }
  for(i = 0; i < 20; i++) {
    n2[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8f*i, 0, random(-0.4f, 0.4f), random(-0.4f, 0.4f), 0, 0, pg9);
  }
  for(i = 0; i < 3; i++) {
    b[i] = new Particulas(0, 6, 0, 32, 270, 0, 0, -0.4f, 0, 0, pg8); //lacunas
  }
  for(i = 3; i < 6; i++) {
    b[i] = new Particulas(0, 6, 0, 32, 376, 0, 0, 0.6f, 0, 0, pg8); //el\u00e9trons
  }
  for(i = 0; i < 6; i++) {
    v2[i] = new Particulas(0, 6, 0, 242, 45 + 200*i, 0, 0, -0.4f, 0, 0, pg8); //lacunas
  }
   for(i = 6; i < 12; i++) {
    v2[i] = new Particulas(0, 6, 0, 242, 185 - 200*(i - 6), 0, 0, 0.4f, 0, 0, pg8); //el\u00e9trons
  }
  legenda1 = new Particulas(0, 8, 0, 438, 180, 1, 0.5f, 0, 0, 0, g);
  legenda2 = new Particulas(0, 8, 0, 438, 240, 1, 0.5f, 0, 0, 0, g);
   //daqui
  GLOBAL = new globalTrickery(this);
  
  sf = new ScrollFrame(this, this.g, 610, 330, 340, 340, 340, 783, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf.setVAMargins(2, 2, 2, 2);
  sf.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf.setBG(new ImageBox(this, this.g, sf.x, sf.y, sf.w, sf.h, color(200, 200, 200, 150), 0, 0));
  sf.setUpdateVATransparent(true); sf.setVABGFillsWholeVA(false); sf.setVABG();
  
  sf_b = new ScrollFrame(this, this.g, 570, 312.5f, 420, 405, 340, 810, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf_b.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf_b.setVAMargins(2, 2, 2, 2);
  sf_b.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf_b.setBG(new ImageBox(this, this.g, sf_b.x, sf_b.y, sf_b.w, sf_b.h, color(200, 200, 200, 150), 0, 0));
  sf_b.setUpdateVATransparent(true); sf_b.setVABGFillsWholeVA(false); sf_b.setVABG();
  
  sf_c = new ScrollFrame(this, this.g, 570, 312.5f, 420, 405, 340, 630, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf_c.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf_c.setVAMargins(2, 2, 2, 2);
  sf_c.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf_c.setBG(new ImageBox(this, this.g, sf_c.x, sf_c.y, sf_c.w, sf_c.h, color(200, 200, 200, 150), 0, 0));
  sf_c.setUpdateVATransparent(true); sf_c.setVABGFillsWholeVA(false); sf_c.setVABG();
  
  sf2 = new ScrollFrame(this, this.g, 610, 340, 320, 460, 320, 2550, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf2.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf2.setVAMargins(2, 2, 2, 2);
  sf2.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf2.setBG(new ImageBox(this, this.g, sf2.x, sf2.y, sf2.w, sf2.h, color(200, 200, 200, 150), 0, 0));
  sf2.setUpdateVATransparent(true); sf2.setVABGFillsWholeVA(false); sf2.setVABG();
 
  sf3 = new ScrollFrame(this, this.g, 160, 346, 280, 456, 280, 1900, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf3.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf3.setVAMargins(2, 2, 2, 2);
  sf3.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf3.setBG(new ImageBox(this, this.g, sf3.x, sf3.y, sf3.w, sf3.h, color(200, 200, 200, 150), 0, 0));
  sf3.setUpdateVATransparent(true); sf3.setVABGFillsWholeVA(false); sf3.setVABG();
  
  sf4 = new ScrollFrame(this, this.g, 625, 345, 300, 480, 300, 1950, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf4.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf4.setVAMargins(2, 2, 2, 2);
  sf4.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf4.setBG(new ImageBox(this, this.g, sf4.x, sf4.y, sf4.w, sf4.h, color(200, 200, 200, 150), 0, 0));
  sf4.setUpdateVATransparent(true); sf4.setVABGFillsWholeVA(false); sf4.setVABG();
  
  sf5 = new ScrollFrame(this, this.g, 615, 105+475/2, 320, 475, 300, 2150, 20, 20,
                     color(255, 255, 255, 100), color(200, 200, 200, 150),
                     color(131, 197, 223, 150), color(255, 255, 255));
  sf5.setBorderSF(4, color(200, 200, 200, 150), 0.5f, 0.5f, 0, 255, 3);
  sf5.setVAMargins(2, 2, 2, 2);
  sf5.setBorderVA(4, color(220, 220, 220, 150), 0.5f, 0.5f, 0, 255, 3);
  sf5.setBG(new ImageBox(this, this.g, sf5.x, sf5.y, sf5.w, sf5.h, color(200, 200, 200, 150), 0, 0));
  sf5.setUpdateVATransparent(true); sf5.setVABGFillsWholeVA(false); sf5.setVABG();
  
  GLOBAL.setupDone();
  //at\u00e9 aqui  
}

public void draw () {
  if(window == 0) {
    configs(window);
    fill(14, 3, 98);
    textAlign(CENTER);
    textFont(fonte2);
    text("DIODOS E TRANSISTORES DE JUN\u00c7\u00c3O BIPOLAR", 200, 70, 400, 100);
    textFont(fonte6);
    fill(0);
    text("Este tutorial animado tem como objetivo fornecer uma introdu\u00e7\u00e3o ao estudo dos diodos e transistores de jun\u00e7\u00e3o bipolar" +
    ", abordando desde a influ\u00eancia do comportamento subat\u00f4mico (el\u00e9trons livres e lacunas eletr\u00f4nicas) no seu funcionamento at\u00e9" +
    " alguns aspectos b\u00e1sicos de sua opera\u00e7\u00e3o em circuitos eletr\u00f4nicos simples. O tutorial \u00e9 dividido em cinco se\u00e7\u00f5es, que podem ser acessadas clicando nas " +
    "abas que aparecem no topo da tela. Os conte\u00fados dessas se\u00e7\u00f5es est\u00e3o resumidos abaixo. Passando o mouse sobre a lupa (      ), voc\u00ea pode visualizar uma pr\u00e9via em miniatura do conte\u00fado de cada aba.", 30, 106, 740, 200);
    image(lupa, 272, 175, 20, 20);
    fill(14, 3, 98);
    textAlign(LEFT);
    text("Sil\u00edcio e dopantes tipo p e n", 30, 224);
    text("Jun\u00e7\u00e3o p-n", 30, 373);
    text("Diodo de jun\u00e7\u00e3o bipolar", 30, 487);
    text("Jun\u00e7\u00e3o n-p-n", 410, 224);
    text("Transistor de jun\u00e7\u00e3o bipolar", 410, 338);
    fill(0);
    
    text("Explora a estrutura at\u00f4mica do sil\u00edcio puro e dopado, abordando os conceitos de liga\u00e7\u00e3o covalente no sil\u00edcio, sua dopagem com impurezas dos tipos p e n, lacunas eletr\u00f4nicas e el\u00e9trons livres," +
    " o que introduz conceitos fundamentais para o entendimento da opera\u00e7\u00e3o dos diodos e transistores de jun\u00e7\u00e3o bipolar.", 30, 240, 360, 300);
    text("Ilustra o comportamento das lacunas eletr\u00f4nicas e el\u00e9trons livres majorit\u00e1rios na jun\u00e7\u00e3o p-n e o seu papel na condu\u00e7\u00e3o de corrente el\u00e9trica " +
    "nas condi\u00e7\u00f5es de polariza\u00e7\u00e3o direta, reversa e na aus\u00eancia de polariza\u00e7\u00e3o.", 30, 390, 360, 200);
    text("Nesta aba, o diodo de jun\u00e7\u00e3o bipolar \u00e9 considerado como um componente circuital com a fun\u00e7\u00e3o de permitir a passagem de corrente el\u00e9trica em apenas um sentido.", 30, 504, 360, 100);
    text("Mostra como o movimento de el\u00e9trons livres e lacunas eletr\u00f4nicas possibilita a condu\u00e7\u00e3o de corrente el\u00e9trica atrav\u00e9s do transistor de jun\u00e7\u00e3o bipolar npn, bem como sua amplifica\u00e7\u00e3o, no modo de opera\u00e7\u00e3o ativo desse transistor.", 410, 240, 360, 300);
    text("O transistor de jun\u00e7\u00e3o bipolar npn \u00e9 considerado como componente circuital operando nos modos ativo, de satura\u00e7\u00e3o e de corte. A se\u00e7\u00e3o tamb\u00e9m exemplifica o funcionamento do transistor como chave eletr\u00f4nica.", 410, 353, 360, 150);
    mouse_x = mouseX;
    mouse_y = mouseY;
    
    image(lupa, 203, 210, 20, 20);
    image(lupa, 110, 360, 20, 20);
    image(lupa, 175, 472, 20, 20);
    image(lupa, 502, 210, 20, 20);
    image(lupa, 580, 323, 20, 20);
    
    if(mouse_x >= 203 && mouse_x <= 223 && mouse_y >= 210 && mouse_y <= 230)
      image(w1, mouse_x, mouse_y, 324.2f, 200);
    else if(mouse_x >= 110 && mouse_x <= 130 && mouse_y >= 360 && mouse_y <= 380)
      image(w2, mouse_x, mouse_y, 324.2f, 200);
    else if(mouse_x >= 175 && mouse_x <= 195 && mouse_y >= 472 && mouse_y <= 492)
      image(w3, mouse_x, mouse_y - 200, 324.2f, 200);
    else if(mouse_x >= 502 && mouse_x <= 522 && mouse_y >= 210 && mouse_y <= 230)
      image(w4, mouse_x - 324.2f, mouse_y, 324.2f, 200);
    else if(mouse_x >= 580 && mouse_x <= 600 && mouse_y >= 323 && mouse_y <= 343)
      image(w5, mouse_x - 324.2f, mouse_y, 324.2f, 200);
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
      sf.getVA().text("O sil\u00edcio \u00e9 um elemento da fam\u00edlia IVA da tabela peri\u00f3dica (ou seja, tem val\u00eancia 4). Na natureza, o sil\u00edcio \u00e9 majoritariamente encontrado na forma de silicatos e de di\u00f3xido de sil\u00edcio e, em menor quantidade, como sil\u00edcio puro. O sil\u00edcio puro tem o arranjo at\u00f4mico esquematizado na janela denominada 'Sil\u00edcio puro': observe que cada um dos quatro el\u00e9trons de val\u00eancia de um \u00e1tomo de sil\u00edcio \u00e9 compartilhado com cada um dos quatro \u00e1tomos vizinhos por meio de liga\u00e7\u00f5es covalentes." +
      "\nO sil\u00edcio puro pode se apresentar nas formas amorfa, monocristalina e policristalina. As variedades monocristalina e policristalina s\u00e3o as empregadas na ind\u00fastria de semicondutores.\nO sil\u00edcio \u00e9 classificado como semicondutor por apresentar uma condutividade de magnitude intermedi\u00e1ria entre a dos condutores e a dos isolantes." +
      " Esta condutividade moderada \u00e9 consequ\u00eancia da exist\u00eancia de dois tipos de portadores de carga no sil\u00edcio: os el\u00e9trons livres e as lacunas eletr\u00f4nicas.\nEm temperaturas superiores ao zero absoluto, a agita\u00e7\u00e3o t\u00e9rmica confere a alguns el\u00e9trons energia suficiente para romper a liga\u00e7\u00e3o covalente, permitindo que eles se desvencilhem dos seus \u00e1tomos de origem; "
      + "esses el\u00e9trons se tornam el\u00e9trons livres, portadores de carga negativa. Os vazios deixados por eles nas liga\u00e7\u00f5es covalentes s\u00e3o as denominadas lacunas eletr\u00f4nicas, que s\u00e3o, por sua vez, portadoras de carga positiva." +
      "\nNo sil\u00edcio puro, el\u00e9trons livres e lacunas eletr\u00f4nicas t\u00eam a mesma concentra\u00e7\u00e3o, que \u00e9 de aproximadamente 1,5x10    /cm   \u00e0 temperatura ambiente (por volta de 25\u00b0C) e que cresce com o aumento da temperatura. Para efeito de compara\u00e7\u00e3o, o n\u00famero de \u00e1tomos por cm   no sil\u00edcio \u00e9 pr\u00f3ximo de 5x10    .", 8, 20, 297, 1000);
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
          if(sen > 0.01f || sen < -0.01f) {
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
            pg2.image(esfera, 125.5f, yc1, 50, 50);
            pg2.image(esfera, 225.5f, yc1, 50, 50);
            pg2.image(esfera, 125.5f, yc2, 50, 50);
            pg2.image(esfera, 225.5f, yc2, 50, 50);    
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
              if(sen > 0.01f || sen < -0.01f) {
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
      strokeWeight(4.8f);
      rect(20, 160, 400, 340);
      textFont(fonte3);
      fill(0);
      text("Sil\u00edcio puro", 230, 128);
      image(forw, 720, 530);
      textFont(fonte2);
      text("Dopagem do tipo p (com boro) e a lacuna eletr\u00f4nica", 340, 535, 400, 100);
      if(sen <= 0.01f && sen >= -0.01f && xc22 == 324) {
        text("Reiniciar", 340, 478);
        image(ret, 380, 460, 24, 24);
      }
      if(play1 == false) {
        noStroke();
        textAlign(CENTER);
        fill(0);
        text("Arranjo cristalino bidimensional de \u00e1tomos de sil\u00edcio com suas liga\u00e7\u00f5es covalentes.\n\nClique aqui para visualizar.", 60, 278, 320, 340);
        
      }
    }
    else if(sub_aba == 2) {
      //scrollframe b
      sf_b.display();
      sf_b.beginVADraw();
  
    
      sf_b.getVA().fill(0);
      sf_b.getVA().background(223, 236, 242, 150);
  
   
      sf_b.getVA().textFont(fonte2);
      sf_b.getVA().text("Como voc\u00ea pode observar na janela denominada 'Dopagem do tipo p', a rede cristalina do sil\u00edcio apresenta defeitos pontuais, as lacunas at\u00f4micas, que correspondem \u00e0 aus\u00eancia de \u00e1tomos em s\u00edtios regulares da rede cristalina. Nesta representa\u00e7\u00e3o, a fra\u00e7\u00e3o de \u00e1tomos ausentes foi exagerada em favor da visualiza\u00e7\u00e3o. Nas lacunas at\u00f4micas podem se alojar \u00e1tomos substitucionais, de val\u00eancias tais que levam \u00e0 altera\u00e7\u00e3o da condutividade do sil\u00edcio. Ao processo de adi\u00e7\u00e3o desses \u00e1tomos substitucionais na rede cristalina do sil\u00edcio denomina-se dopagem. H\u00e1 dois tipos de dopagem: p e n. Nesta sub aba, trataremos da dopagem do tipo p. A janela 'Dopagem do tipo p' apresenta de forma esquem\u00e1tica a dopagem do sil\u00edcio (em vermelho) com boro (em azul). Note que, mesmo ap\u00f3s a dopagem, algumas lacunas at\u00f4micas n\u00e3o s\u00e3o preenchidas.\n\n" +
      "\nNo caso do sil\u00edcio, a dopagem do tipo p consiste na inser\u00e7\u00e3o substitucional de \u00e1tomos de elementos da fam\u00edlia IIIA da tabela peri\u00f3dica (val\u00eancia 3) na rede do sil\u00edcio, o boro, por exemplo. Assista \u00e0 anima\u00e7\u00e3o 'A lacuna eletr\u00f4nica: sil\u00edcio + boro' para observar o surgimento de uma lacuna eletr\u00f4nica pela dopagem do sil\u00edcio com boro e a sua subsequente movimenta\u00e7\u00e3o por difus\u00e3o atrav\u00e9s do reticulado cristalino. Como o boro tem tr\u00eas el\u00e9trons na camada de val\u00eancia, apenas tr\u00eas dos \u00e1tomos de sil\u00edcio vizinhos se ligam covalentemente a ele; a quarta liga\u00e7\u00e3o covalente n\u00e3o se completa devido \u00e0 aus\u00eancia de um el\u00e9tron na camada de val\u00eancia do boro, gerando assim a lacuna eletr\u00f4nica. Portanto, para cada \u00e1tomo de boro inserido no reticulado cristalino do sil\u00edcio surge uma lacuna. Considere que a temperatura \u00e9 baixa o bastante para que os el\u00e9trons livres e as lacunas eletr\u00f4nicas origin\u00e1rios da agita\u00e7\u00e3o t\u00e9rmica n\u00e3o precisem ser representados. Para o sil\u00edcio, isso pode ser assumido em temperaturas inferiores \u00e0 m\u00e1xima temperatura de opera\u00e7\u00e3o da jun\u00e7\u00e3o p-n, que varia entre 150\u00baC e 200\u00baC. De fato, a concentra\u00e7\u00e3o de lacunas eletr\u00f4nicas em semicondutores do tipo p \u00e9 quase sempre aproximada pela concentra\u00e7\u00e3o do dopante p, ou seja, despreza-se a concentra\u00e7\u00e3o de lacunas eletr\u00f4nicas origin\u00e1rias da agita\u00e7\u00e3o t\u00e9rmica.", 8, 25, 375, 2000);
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
              pg3.ellipse(44.5f + 20*j, y1[j], 15, 15);
            else
              pg3.ellipse(64.5f + 40*(j), y1[j], 15, 15);
          }
          else {
            pass[j] = 1;
            pg3.fill(a);
            if(j == 0)
              pg3.ellipse(44.5f, 262.5f, 15, 15);
            else if(j == 1)
              pg3.ellipse(64.5f, 222.5f, 15, 15);
            else if(j == 2)
              pg3.ellipse(144.5f, 222.5f, 15, 15);
            else if(j == 3)
              pg3.ellipse(184.5f, 262.5f, 15, 15);
            
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

      strokeWeight(4.8f);
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
        text("Ilustra\u00e7\u00e3o da dopagem do sil\u00edcio com boro.\n\nClique aqui para visualizar.", 40, 165, 270, 220);
      }
      //consertar no mouse pressed, a altura passou para 190
      strokeWeight(4.8f);
      noFill();
      stroke(220, 220, 220);
      strokeJoin(BEVEL);
      // movimento da lacuna no sil\u00edcio dopado com boro
    
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
      if(play3 == true) {//reinicializar novos el\u00e9trons a cada volta \u00e0 aba
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
        text("Ilustra\u00e7\u00e3o do surgimento de uma lacuna eletr\u00f4nica (ou buraco eletr\u00f4nico) por dopagem do sil\u00edcio com boro e de sua subsequente movimenta\u00e7\u00e3o na estrutura cristalina.\n\nClique aqui para visualizar. ", 40, 387, 270, 230);
      }
      
      fill(0);

      textFont(fonte2);
      text("A lacuna eletr\u00f4nica: sil\u00edcio + boro", 175, 324);
      text("Dopagem do tipo p", 175, 100);
      fill(0);
      text("Dopagem do tipo n (com f\u00f3sforo) e o el\u00e9tron livre", 590, 545);
      text("Sil\u00edcio puro", 418, 575);
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
      sf_c.getVA().text("Como mencionado na sub aba 'Dopagem do tipo p (com boro) e a lacuna eletr\u00f4nica', a rede cristalina do sil\u00edcio cont\u00e9m lacunas at\u00f4micas, que s\u00e3o defeitos pontuais correspondentes \u00e0 aus\u00eancia de \u00e1tomos em s\u00edtios regulares da rede cristalina. Nessa mesma sub aba, tamb\u00e9m se mencionou a dopagem, processo que permite alterar a condutividade do sil\u00edcio por meio da inser\u00e7\u00e3o substitucional de \u00e1tomos de outros elementos qu\u00edmicos nas lacunas at\u00f4micas. J\u00e1 abordamos a dopagem do tipo p; nesta aba, daremos foco \u00e0 dopagem do tipo n." +
      " A dopagem do tipo n est\u00e1 representada de forma fantasiosa na janela 'Dopagem do tipo n'. Os \u00e1tomos vermelhos s\u00e3o de sil\u00edcio e os verdes, de f\u00f3sforo. Observe que mesmo ap\u00f3s a dopagem algumas lacunas permanecem sem ser preenchidas.\nA anima\u00e7\u00e3o 'O el\u00e9tron livre: sil\u00edcio + f\u00f3sforo' ilustra o surgimento de um el\u00e9tron livre pela aloca\u00e7\u00e3o de um \u00e1tomo de f\u00f3sforo em uma lacuna at\u00f4mica. Observe que quatro dos el\u00e9trons de val\u00eancia do f\u00f3sforo participam das liga\u00e7\u00f5es covalentes com os quatro \u00e1tomos de sil\u00edcio vizinhos. O quinto el\u00e9tron do f\u00f3sforo n\u00e3o participa de liga\u00e7\u00e3o covalente e, portanto, \u00e9 um el\u00e9tron livre. Os el\u00e9trons livres e as lacunas eletr\u00f4nicas correspondentes \u00e0 condutividade intr\u00ednseca do sil\u00edcio n\u00e3o est\u00e3o representados. O el\u00e9tron livre se move aleatoriamente por difus\u00e3o, sob influ\u00eancia da agita\u00e7\u00e3o t\u00e9rmica e sem dire\u00e7\u00e3o preferencial de deslocamento devido \u00e0 aus\u00eancia de campo el\u00e9trico. Note que a mobilidade dos el\u00e9trons livres \u00e9 superior \u00e0 das lacunas eletr\u00f4nicas, j\u00e1 que elas t\u00eam deslocamento restrito aos s\u00edtios contendo el\u00e9trons participantes de liga\u00e7\u00f5es covalentes, enquanto os el\u00e9trons livres podem se movimentar sem restri\u00e7\u00f5es pela rede cristalina.", 8, 20, 375, 2000);
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
              pg3.ellipse(44.5f + 20*j, y1[j], 15, 15);
            else
              pg3.ellipse(64.5f + 40*(j), y1[j], 15, 15);
          }
          else {
            pass[j] = 1;
            pg3.fill(a);
            if(j == 0)
              pg3.ellipse(44.5f, 262.5f, 15, 15);
            else if(j == 1)
              pg3.ellipse(64.5f, 222.5f, 15, 15);
            else if(j == 2)
              pg3.ellipse(144.5f, 222.5f, 15, 15);
            else if(j == 3)
              pg3.ellipse(184.5f, 262.5f, 15, 15);
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
      strokeWeight(4.8f);
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
        text("Ilustra\u00e7\u00e3o da dopagem do sil\u00edcio com f\u00f3sforo.\n\nClique aqui para visualizar.", 40, 160, 270, 220);
      }
      strokeWeight(4.8f);
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
        text("Ilustra\u00e7\u00e3o do surgimento de um el\u00e9tron livre por dopagem do sil\u00edcio com f\u00f3sforo e de sua subsequente movimenta\u00e7\u00e3o na estrutura cristalina.\n\nClique aqui para visualizar. ", 40, 405, 270, 230);
        
      }
      
      fill(0);

      textFont(fonte2);
      text("O el\u00e9tron livre: sil\u00edcio + f\u00f3sforo", 175, 324);
      text("Dopagem do tipo n", 175, 100);      
      fill(0);
      text("Dopagem do tipo p (com boro) e a lacuna eletr\u00f4nica", 580, 558);
      image(back, 373, 535);
    }
    
    textFont(fonte2);
    
    strokeWeight(4.8f);
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
    sf2.getVA().text("Esta anima\u00e7\u00e3o ilustra uma jun\u00e7\u00e3o p-n. A jun\u00e7\u00e3o p-n \u00e9 obtida a partir de uma \u00fanica barra de sil\u00edcio dopada das extremidades em dire\u00e7\u00e3o ao centro, de um lado com dopantes do tipo p (no nosso caso, o boro, em azul) e, do outro, com dopantes do tipo n (representados pelo f\u00f3sforo, em verde). Nesta anima\u00e7\u00e3o, as lacunas minorit\u00e1rias e os el\u00e9trons minorit\u00e1rios n\u00e3o est\u00e3o representados em favor da simplifica\u00e7\u00e3o." +
    "\nQuando a jun\u00e7\u00e3o p-n est\u00e1 em aberto, as lacunas do lado p tendem a se deslocar para o lado n e os el\u00e9trons do lado n tendem a se deslocar para o lado p mediados por difus\u00e3o. Uma vez no lado n, as lacunas se recombinam rapidamente com os el\u00e9trons livres dessa regi\u00e3o, j\u00e1 que, no lado n, os el\u00e9trons livres est\u00e3o presentes em grande quantidade por serem portadores majorit\u00e1rios. A cada recombina\u00e7\u00e3o, um par el\u00e9tron livre-lacuna se aniquila." +
    " Quando uma lacuna se recombina com um el\u00e9tron, o \u00e1tomo de boro que deu origem \u00e0 lacuna adquire uma carga negativa. Esta carga \u00e9 fixa, pois o \u00e1tomo de boro est\u00e1 preso \u00e0 rede cristalina. Analogamente, o \u00e1tomo de f\u00f3sforo que deu origem ao el\u00e9tron livre aniquilado na recombina\u00e7\u00e3o adquire uma carga positiva fixa. No lado p, este processo se repete: os el\u00e9trons livres que atingem esta regi\u00e3o se recombinam com as lacunas majorit\u00e1rias, e um par de cargas fixas de sinais opostos \u00e9 gerado a cada recombina\u00e7\u00e3o." +
    "\nA repeti\u00e7\u00e3o deste processo de recombina\u00e7\u00f5es d\u00e1 origem a um campo el\u00e9trico orientado do lado n para o lado p, devido ao surgimento de cargas fixas positivas no lado n e negativas no lado p. Este campo el\u00e9trico delimita uma regi\u00e3o de deple\u00e7\u00e3o na interface entre as por\u00e7\u00f5es p e n da jun\u00e7\u00e3o. A camada de deple\u00e7\u00e3o compreende as cargas fixas correspondentes \u00e0s lacunas e el\u00e9trons livres que estavam mais pr\u00f3ximos da interface e que, portanto, se recombinaram. Assim, esta regi\u00e3o \u00e9 uma zona destitu\u00edda de portadores livres.\n" +
    "Observe que, na anima\u00e7\u00e3o, cada recombina\u00e7\u00e3o provoca o aumento da camada de deple\u00e7\u00e3o (representada em amarelo) e do m\u00f3dulo do vetor campo el\u00e9trico E (representado pela seta). Note que este campo el\u00e9trico atua no sentido de confinar as lacunas na por\u00e7\u00e3o p e os el\u00e9trons na por\u00e7\u00e3o n da jun\u00e7\u00e3o, criando uma barreira de potencial cujo valor para a jun\u00e7\u00e3o p-n de sil\u00edcio dopado \u00e9 de aproximadamente 0,7 V no equil\u00edbrio.\n" +
    "Aguarde a recombina\u00e7\u00e3o de todas as lacunas e el\u00e9trons deslocados por difus\u00e3o para ativar a polariza\u00e7\u00e3o da jun\u00e7\u00e3o p-n. Ao t\u00e9rmino destas recombina\u00e7\u00f5es, voc\u00ea pode acionar a polariza\u00e7\u00e3o direta ou reversa clicando sobre as setas correspondentes.\n\nPolariza\u00e7\u00e3o direta\n\nNa polariza\u00e7\u00e3o direta, a regi\u00e3o p est\u00e1 ligada ao terminal positivo da bateria, e a regi\u00e3o n, ao terminal negativo. O potencial fornecido pela bateria \u00e9 contr\u00e1rio ao potencial de barreira e menor do que ele em m\u00f3dulo; assim, na polariza\u00e7\u00e3o direta h\u00e1 uma diminui\u00e7\u00e3o da camada de deple\u00e7\u00e3o.\n" +
    "Com a diminui\u00e7\u00e3o da regi\u00e3o de deple\u00e7\u00e3o, algumas lacunas conseguem atravessar a barreira de potencial e atingem o lado n. Atra\u00eddas pelo terminal negativo da bateria, estas lacunas tender\u00e3o a se deslocar ao longo da regi\u00e3o n at\u00e9 deixar a jun\u00e7\u00e3o e, assim, h\u00e1 condu\u00e7\u00e3o de corrente el\u00e9trica. O mesmo acontece com alguns el\u00e9trons, que t\u00eam energia suficiente para transpor a barreira de potencial e "
    + "tendem a deixar a jun\u00e7\u00e3o pelo lado p. Quando os el\u00e9tron est\u00e3o na regi\u00e3o p e as lacunas na regi\u00e3o n, h\u00e1 chance de que eles se recombinem com as lacunas da regi\u00e3o p e os el\u00e9trons da regi\u00e3o n, respectivamente. Este fato n\u00e3o est\u00e1 ilustrado nesta anima\u00e7\u00e3o devido \u00e0 pequena quantidade de portadores representada, mas acontece na jun\u00e7\u00e3o p-n real, de forma que, no lado n, a concentra\u00e7\u00e3o de lacunas \u00e9 maior pr\u00f3ximo \u00e0 interface entre os lados p e n do que nas proximidades do contato A, decrescendo exponencialmente em fun\u00e7\u00e3o da dist\u00e2ncia \u00e0 interface. " +
    "No lado p, a concentra\u00e7\u00e3o de el\u00e9trons livres tamb\u00e9m \u00e9 regida por uma fun\u00e7\u00e3o exponencial descrecente da dist\u00e2ncia \u00e0 interface entre os lados p e n. Assim, nas proximidades do contato B, a concentra\u00e7\u00e3o de el\u00e9trons livres \u00e9 menor do que na interface.\n\nPolariza\u00e7\u00e3o reversa\n\nNa polariza\u00e7\u00e3o reversa, os terminais da bateria est\u00e3o trocados. Com o terminal negativo da bateria conectado ao lado p da jun\u00e7\u00e3o e o terminal positivo conectado ao lado n, um fluxo de el\u00e9trons \u00e9 for\u00e7ado para dentro da regi\u00e3o p e um fluxo de lacunas para dentro da regi\u00e3o n. Uma vez no lado p, a maior parte dos el\u00e9trons provenientes da bateria se recombina com as lacunas presentes em grande quantidade nesta por\u00e7\u00e3o da jun\u00e7\u00e3o, " +
    "levando ao surgimento de cargas negativas fixas correspondentes aos \u00e1tomos de origem das lacunas aniquiladas. As recombina\u00e7\u00f5es acontecem tamb\u00e9m no lado n da jun\u00e7\u00e3o e provocam o aparecimento de cargas positivas fixas. Assim, a camada de deple\u00e7\u00e3o aumenta e os el\u00e9trons e lacunas n\u00e3o t\u00eam energia suficiente para transpassar a barreira de potencial, ou seja, n\u00e3o h\u00e1 condu\u00e7\u00e3o de corrente el\u00e9trica.", 15, 30, 262, 2700);
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
    else if(cont2 == 4) { //polariza\u00e7\u00e3o reversa
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
    else if(cont3 == 4) { //polariza\u00e7\u00e3o reversa

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
           port[cont].set_spx(0.4f);
         }
         for(cont = 10; cont < 13; cont++) {
           port[cont].set_spx(-0.4f);
         }
         passe2 = 1;
       }
       for(cont = 3; cont < 10; cont++) {
         if(port[cont].get_x() >= 150-9*(cont2+cont3) && cont != neut5) { // mudei aqui
            if(port[cont].get_spx() > 0)
              port[cont].invert_spx();
            else
              port[cont].set_spx(-0.4f);
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
              port[cont].set_spx(+0.4f);
         }
       }
       for(cont = 0; cont < 20; cont++) {
      
         if(port[cont].get_x() < 1000 && port[cont].get_x() > - 1000 && cont != neut5 && cont != neut6) {
           if(port[cont].get_y() >= 80 && port[cont].get_y() <= 100) {
             port[cont].set_spy(random(-0.8f, -0.1f));
           }
           else if(port[cont].get_y() <= 35 && port[cont].get_y() >= 15) {
             port[cont].set_spy(random(0.1f, 0.8f));
           }
           else
             port[cont].set_spy(random(-0.8f, 0.8f)); //mudei aqui - falta mudar nos el\u00e9trons da polariza\u00e7\u00e3o
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
               port[cont].set_spx(random(-0.6f, -0.4f));
             }
             else if(port[cont].get_x() >= 15+9*(cont2+cont3) && port[cont].get_x() <= 15+9*(cont2+cont3) + 20) { // mudei aqui
               port[cont].set_spx(random(0.4f, 0.6f));
             }
             else if(port[cont].get_x() > 150)
               port[cont].set_spx(-0.4f);
             else if(port[cont].get_x() < 15+9*(cont2+cont3))
               port[cont].set_spx(0.4f);
             else
               port[cont].set_spx(random(-0.4f, 0.6f));
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
                 port[cont].set_spx(random(0.4f, 0.6f));
               }
               else if(port[cont].get_x() <= 150-9*(cont2+cont3) && port[cont].get_x() >= 130-9*(cont2+cont3)) { // mudei aqui
                 port[cont].set_spx(random(-0.4f, -0.6f));
               }
               else if(port[cont].get_x() < 15)
                  port[cont].set_spx(0.4f);
               else if(port[cont].get_x() > 150-9*(cont2+cont3))
                 port[cont].set_spx(-0.4f);
               else
                 port[cont].set_spx(random(-0.4f, 0.6f));
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
         pg5.strokeWeight(2.5f);
         pg5.line(165, 315, 20, 315);
         pg5.line(20, 315, 20, 140);
         pg5.line(20, 140, 25, 140);
         pg5.line(233, 315, 378, 315);
         pg5.line(378, 315, 378, 140);
         pg5.line(378, 140, 373, 140);
         pg5.strokeWeight(1);
         
         if(pol == 1) { //polariza\u00e7\u00e3o direta
           
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
             pp[1].set_spx(-0.4f);
             pp[1].set_spy(0);
             pp[1].change_pg(pg5);
             
             pp[3].set_xc(233);
             pp[3].set_yc(323);
             pp[3].set_spx(0.4f);
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
             pp[5].set_spy(-0.4f);
           }
           if(pp[5].get_y() < 153 && passe16 == 1) {
             pp[5].change_pg(pg6);
             pp[5].set_spx(0.4f);
             pp[5].set_spy(random(-0.4f, 0.4f));//aqui
             pp[5].set_yc(48);
             pp[5].set_xc(16);
             passe16 = 2;
           }
           else if(passe16 == 2) {
             if(pp[5].get_y() >= 80 && pp[5].get_y() <= 100) {
               pp[5].set_spy(random(-0.8f, -0.1f));
             }
             else if(pp[5].get_y() <= 35 && pp[5].get_y() >= 15) {
               pp[5].set_spy(random(0.1f, 0.8f));
             }
             else
               pp[5].set_spy(random(-0.8f, 0.8f));

             if(pp[5].get_x() <= 15 || pp[5].get_x() >= 150 - 9*(cont2+cont3)) { 
               pp[5].invert_spx();
             }
           }
           //el\u00e9tron de cobertura
           if(passe17 == 1 || passe17 == 2) {
             pp[4].mover();
             pp[4].display(); 
           }
           if(passe17 == 1 && pp[4].get_x() > 370) {
             pp[4].set_xc(370);
             pp[4].set_spx(0);
             pp[4].set_spy(-0.4f);
           }
           if(pp[4].get_y() < 153 && passe17 == 1) {
             pp[4].change_pg(pg7);
             pp[4].set_spx(0.4f);
             pp[4].set_spy(random(-0.4f, 0.4f));
             pp[4].set_yc(48);
             pp[4].set_xc(145);
             passe17 = 2;
           }
           else if(passe17 == 2) { //ok
             if(pp[4].get_y() >= 80 && pp[4].get_y() <= 100) {
               pp[4].set_spy(random(-0.8f, -0.1f));
             }
             else if(pp[4].get_y() <= 35 && pp[4].get_y() >= 15) {
               pp[4].set_spy(random(0.1f, 0.8f));
             }
             else
               pp[4].set_spy(random(-0.8f, 0.8f));

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
             pp[1].set_spy(-0.4f);
           }
           if(pp[3].get_x() > 385 && passe5 == 0) {
             pp[3].set_xc(385);
             pp[3].set_spx(0);
             pp[3].set_spy(-0.4f);
             
           }
           if(pp[1].get_y() < 133 && passe5 == 0) {
             pp[1].change_pg(pg6);
             pp[1].set_spx(0.4f);
             pp[1].set_spy(random(-0.4f, 0.4f));//aqui
             pp[1].set_yc(48);
             pp[1].set_xc(16);
             passe5 = 1;
             if(passe16 == 0 && passe17 == 0)
               cont2--;
           }
           else if(passe5 == 1) {
             if(pp[1].get_y() >= 80 && pp[1].get_y() <= 100) {
               pp[1].set_spy(random(-0.8f, -0.1f));
             }
             else if(pp[1].get_y() <= 35 && pp[1].get_y() >= 15) {
               pp[1].set_spy(random(0.1f, 0.8f));
             }
             else
               pp[1].set_spy(random(-0.8f, 0.8f));

             if(pp[1].get_x() <= 15 || pp[1].get_x() >= 150 - 9*(cont2+cont3)) { 
               pp[1].invert_spx();
             }
             for(j = 3; j < 10 && neut5 == -1; j++) {
                if(port[j].get_x() < 700 && port[j].get_x() > -700 && port[j].get_y() > -700 && port[j].get_y() < 700) {
                 neut5 = j;
                 port[neut5].set_spx(0.4f); 
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
               port[neut5].set_spy(+0.4f);
               port[neut5].set_spx(0);
               
             }
             else if(port[neut5].get_y() > 323 && passe_n5 == 2) {
               passe_n5 = 3;
               port[neut5].set_spy(0);
               port[neut5].set_spx(-0.4f);
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
                 port[neut5].set_spy(-0.6f);
               else if(port[neut5].get_y() < 45)
                 port[neut5].set_spy(0.6f);
               else
                 port[neut5].set_spy(0);
             }
             
           }
           if(pp[3].get_y() < 133 && passe6 == 0) {
             pp[3].change_pg(pg7);
             pp[3].set_spx(-0.4f);
             pp[3].set_spy(0);
             pp[3].set_yc(48);
             pp[3].set_xc(149);
             passe6 = 1;
           }
           else if(passe6 == 1) {
             if(pp[3].get_y() >= 80 && pp[3].get_y() <= 100) {
               pp[3].set_spy(random(-0.8f, -0.1f));
             }
             else if(pp[3].get_y() <= 35 && pp[3].get_y() >= 15) {
               pp[3].set_spy(random(0.1f, 0.8f));
             }
             else
               pp[3].set_spy(random(-0.8f, 0.8f));
             if(pp[3].get_x() <= 15 + 9*(cont2+cont3) || pp[3].get_x() >= 150) { //arrumar aqui
               pp[3].invert_spx();
             }
             for(j = 13; j < 20 && neut6 == -1; j++) {
                if(port[j].get_x() < 700 && port[j].get_x() > -700 && port[j].get_y() > -700 && port[j].get_y() < 700) {
                 neut6 = j;
                 port[neut6].set_spx(-0.4f); 
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
               port[neut6].set_spy(+0.4f);
               port[neut6].set_spx(0);
               
             }
             else if(port[neut6].get_y() > 323 && passe_n6 == 2) {
               passe_n6 = 3;
               port[neut6].set_spy(0);
               port[neut6].set_spx(0.4f);
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
                 port[neut6].set_spy(-0.6f);
               else if(port[neut6].get_y() < 45)
                 port[neut6].set_spy(0.6f);
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
           // movimenta\u00e7\u00e3o dos el\u00e9trons/lacunas provenientes do gerador de tens\u00e3o
           if(pp[0].get_x() < 13 && passe5 == 0) {
             pp[0].set_xc(13);
             pp[0].set_spx(0);
             pp[0].set_spy(-0.4f);
             
           }
           if(pp[2].get_x() > 385 && passe5 == 0) {
             pp[2].set_xc(385);
             pp[2].set_spx(0);
             pp[2].set_spy(-0.4f);
             
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
      text("Campo el\u00e9trico", 233, 350);
      textFont(fonte2);
      text("Reiniciar", 370, 500);
      image(ret, 405, 484, 18, 18);
      text("Sem polariza\u00e7\u00e3o", 346, 555);
      image(forw, 401, 539, 24, 24);
      
      if(pol == 1) {
        textFont(fonte2);
        text("Polariza\u00e7\u00e3o reversa", 334, 527);
        image(forw, 401, 512, 24, 24);
        textFont(fonte1);
        text("Jun\u00e7\u00e3o p-n diretamente polarizada", 233, 145);
        textFont(fonte7);
        textAlign(LEFT);
        text("Na condi\u00e7\u00e3o de polariza\u00e7\u00e3o direta, a bateria impulsiona lacunas para a regi\u00e3o p e el\u00e9trons para a regi\u00e3o n, ocasionando a diminui\u00e7\u00e3o do campo el\u00e9trico e permitindo, assim, o deslocamento de portadores atrav\u00e9s da jun\u00e7\u00e3o - isto \u00e9, a condu\u00e7\u00e3o de corrente el\u00e9trica. ", 43, 446, 216, 200);
      }
      else {
        textFont(fonte2);
        text("Polariza\u00e7\u00e3o direta", 339, 527);
        image(forw, 401, 512, 24, 24);
        textFont(fonte1);
        text("Jun\u00e7\u00e3o p-n reversamente polarizada", 233, 145);
        textFont(fonte7);
        textAlign(LEFT);
        text("A inje\u00e7\u00e3o de el\u00e9trons na regi\u00e3o p e a de lacunas na regi\u00e3o n ocasiona recombina\u00e7\u00f5es e, portanto, o aumento do n\u00famero de cargas fixas de sinais opostos. Assim, o campo el\u00e9trico fica mais intenso e a zona de deple\u00e7\u00e3o se alarga, impedindo a condu\u00e7\u00e3o de corrente el\u00e9trica.", 43, 446, 216, 200);
      }
    }
    else {
      fill(0);
      text("Regi\u00e3o p", 95, 190);
      text("Regi\u00e3o n", 366, 190);
      if(play4 == true) {
        text("Zona de deple\u00e7\u00e3o", 233, 185);
        text("Campo el\u00e9trico", 233, 350);
        if(passe4 == 1) {
          textFont(fonte2);
          text("Reiniciar", 370, 500);
          image(ret, 405, 484, 18, 18);
          text("Polariza\u00e7\u00e3o direta", 339, 527);
          text("Polariza\u00e7\u00e3o reversa", 334, 555);
          image(forw, 401, 512, 24, 24);
          image(forw, 401, 539, 24, 24);
        }
     
        textFont(fonte7);
        text("Na aus\u00eancia de polariza\u00e7\u00e3o, h\u00e1 um deslocamento natural de lacunas para a regi\u00e3o n e de el\u00e9trons para a regi\u00e3o p devido \u00e0 intera\u00e7\u00e3o el\u00e9trica. A recombina\u00e7\u00e3o de portadores gera cargas fixas de sinais opostos nos dois lados da jun\u00e7\u00e3o e, consequentemente, um campo el\u00e9trico proporcional ao n\u00famero destas cargas fixas e uma zona de deple\u00e7\u00e3o, que funciona como uma barreira \u00e0 movimenta\u00e7\u00e3o de el\u00e9trons e lacunas atrav\u00e9s da jun\u00e7\u00e3o.", 43, 368, 380, 200);
        textFont(fonte1);
        text("Jun\u00e7\u00e3o p-n n\u00e3o polarizada", 233, 145);
  
        
      }
    }
    strokeWeight(4.8f);
    noFill();
    stroke(220, 220, 220);
    strokeJoin(BEVEL);
    rect(30, 110, 400, 460);
    if(play4 == false) {
     
      fill(0);
      text("Regi\u00e3o p", 95, 190);
      text("Regi\u00e3o n", 366, 190);
      textAlign(CENTER);
      textFont(fonte2);
      text("Aqui consideramos a jun\u00e7\u00e3o p-n em tr\u00eas condi\u00e7\u00f5es: n\u00e3o-polarizada (ou em aberto), polarizada diretamente" +
      " e polarizada reversamente. Ilustramos o movimento das lacunas eletr\u00f4nicas e dos el\u00e9trons livres majorit\u00e1rios na jun\u00e7\u00e3o e o seu papel na " +
      "condu\u00e7\u00e3o de corrente el\u00e9trica nas tr\u00eas condi\u00e7\u00f5es.\n\nIniciar", 60, 365, 360, 300);
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
    sf3.getVA().text("O circuito representado na anima\u00e7\u00e3o 'O diodo ideal em um circuito excitado por um sinal senoidal' \u00e9 composto de um gerador de tens\u00e3o senoidal, um diodo ideal e um resistor. A amplitude da onda fornecida pelo gerador de tens\u00e3o pode ser ajustada pelo controlador do tipo slider denominado 'Amplitude do sinal de entrada', no canto inferior esquerdo da tela. Conectado ao circuito h\u00e1 um oscilosc\u00f3pio de dois canais e dois displays. O canal 1 (CH1) exibe o sinal de entrada, proveniente do gerador de tens\u00e3o. O canal 2 (CH2) fornece a forma de onda da tens\u00e3o no resistor.\nO diodo ideal \u00e9 um modelo para o dispositivo ativo denominado diodo, que consiste em uma jun\u00e7\u00e3o p-n e opera conforme descrito na aba 'Jun\u00e7\u00e3o p-n'. No modelo do diodo ideal, assume-se que o diodo s\u00f3 permite a passagem de corrente el\u00e9trica em um sentido, do \u00e2nodo (indicado por A) para o c\u00e1todo (indicado por K) e que o potencial de barreira da jun\u00e7\u00e3o p-n, que nos diodos reais \u00e9 de aproximadamente 0,7 V, \u00e9 nulo. O \u00e2nodo corresponde ao lado p e o c\u00e1todo ao lado n da jun\u00e7\u00e3o.\nPara que o diodo ideal conduza, ele deve estar polarizado diretamente, e, como o potencial de barreira \u00e9 assumido como nulo, basta que a diferen\u00e7a de tens\u00e3o entre \u00e2nodo e c\u00e1todo seja maior do que zero, em um primeiro momento. Quando o diodo ideal entra em condu\u00e7\u00e3o, a queda de tens\u00e3o nele assume o valor do potencial de barreira, e, portanto, \u00e9 nula (porque se trata do modelo do diodo ideal; j\u00e1 no diodo real, a queda de tens\u00e3o \u00e9 de aproximadamente 0,7 V durante a condu\u00e7\u00e3o); nesta situa\u00e7\u00e3o, o diodo ideal funciona como uma chave fechada.\nQuando o diodo ideal \u00e9 polarizado reversamente, isto \u00e9, quando a tens\u00e3o no c\u00e1todo \u00e9 superior \u00e0 tens\u00e3o no \u00e2nodo, n\u00e3o flui corrente atrav\u00e9s dele e dizemos que o diodo est\u00e1 em \u201ccorte\u201d. Toda a tens\u00e3o negativa imposta pela fonte recai sobre o diodo, j\u00e1 que n\u00e3o h\u00e1 corrente el\u00e9trica fluindo no circuito e que, pela Lei de Ohm, a tens\u00e3o no resistor \u00e9 nula. Portanto, o diodo funciona como uma chave aberta.\nAs posi\u00e7\u00f5es do c\u00e1todo e do \u00e2nodo podem ser invertidas clicando-se sobre o diodo. Quando o \u00e2nodo est\u00e1 \u00e0 esquerda e o c\u00e1todo \u00e0 direita, o diodo dest\u00e1 posicionado de forma que s\u00f3 h\u00e1 condu\u00e7\u00e3o de corrente el\u00e9trica no semiciclo positivo da tens\u00e3o senoidal proveniente do gerador, ou seja, quando a tens\u00e3o que se tenta impor no \u00e2nodo \u00e9 maior do que a tens\u00e3o no c\u00e1todo. Nessas condi\u00e7\u00f5es, a tens\u00e3o no resistor \u201cimita\u201d a forma de onda da tens\u00e3o proveniente do gerador, uma vez que a queda de tens\u00e3o no diodo \u00e9 nula. Quando a fonte entra no semiciclo negativo, a tens\u00e3o no \u00e2nodo \u00e9 inferior \u00e0 tens\u00e3o no c\u00e1todo: o diodo est\u00e1 em corte. Como n\u00e3o h\u00e1 condu\u00e7\u00e3o de corrente el\u00e9trica, a tens\u00e3o no resistor \u00e9 nula.\nQuando o c\u00e1todo est\u00e1 \u00e0 esquerda e o \u00e2nodo \u00e0 direita, a tens\u00e3o no \u00e2nodo ser\u00e1 inferior \u00e0 tens\u00e3o no c\u00e1todo no semiciclo positivo da fonte. Nesta situa\u00e7\u00e3o, o diodo est\u00e1 em corte e a tens\u00e3o no resistor \u00e9 nula porque a corrente fluindo no circuito \u00e9 nula. Quando a fonte entra no semiciclo negativo,  a tens\u00e3o que se tenta impor no \u00e2nodo \u00e9 maior do que a do c\u00e1todo; o diodo conduz e tens\u00e3o no resistor tem a mesma forma de onda da tens\u00e3o no gerador.", 15, 30, 220, 2200);
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
    strokeWeight(4.8f);
    rect(300, 110, 450, 460);
    stroke(0);
    strokeWeight(2);
    
    //aqui
    pushMatrix();
    translate(32, -27);
    
    line(490, 170, 700, 170);
    line(490, 170, 490, 300);
    line(700, 170, 700, 300);
    line(490, 300, 577.5f, 300);
    line(619.5f, 300, 700, 300);
    ellipse(490, 235, 36, 36);
    arc(484, 235, 12, 12, 0, PI);
    arc(496, 235, 12, 12, PI, TWO_PI);
    for(i = 0 ; i <3; i++) {
      line(577.5f + 16.8f*i, 300, 581.7f + 16.8f*i, 290);
      line(581.7f + 16.8f*i, 290, 585.9f + 16.8f*i, 300);
      if(i != 2) {
        line(585.9f + 16.8f*i, 300, 590.1f + 16.8f*i, 310);
        line(590.1f + 16.8f*i, 310, 594.3f + 16.8f*i, 300);
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
    text("Esta anima\u00e7\u00e3o \u00e9 composta de um circuito eletr\u00f4nico simples e de um oscilosc\u00f3pio. O canal CH1 do oscilosc\u00f3pio exibe a forma de onda da tens\u00e3o de entrada, e o canal CH2, a forma de onda da tens\u00e3o de sa\u00edda, que \u00e9 tomada no resistor. O diodo \u00e9 ideal. Troque as posi\u00e7\u00f5es do c\u00e1todo (K) e do \u00e2nodo (A) do diodo clicando sobre ele para verificar sua influ\u00eancia na tens\u00e3o de sa\u00edda. Para modificar a amplitude do sinal de entrada, clique sobre o slider 'Amplitude do sinal de entrada' e deslize-o.", 307, 494, 435, 120); 
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
    strokeWeight(0.5f);
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
      y_sin = sin(angle_sin*(numOfWaves/0.8f));
   
      y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
   
      line(prevX, prevY, x_sin, y_sin);
   
      prevX = x_sin;
      prevY = y_sin;
    }
   
    prevX = prevY = 0.0f;
    
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
        y_sin = sin(angle_sin*(numOfWaves/0.8f));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
      
     
      prevX = prevY = 0.0f;
      
      popMatrix(); 
  
      pushMatrix();
      translate(601, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 37; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = sin(angle_sin*(numOfWaves/0.8f));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
      
     
      prevX = prevY = 0.0f;
      
      popMatrix();
      
      pushMatrix();
      translate(672, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 19; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = sin(angle_sin*(numOfWaves/0.8f));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
      
     
      prevX = prevY = 0.0f;
      
      popMatrix();
      
      stroke(225, 255, 8);
      line(530 + 36, 400 + amplitude/2, 530 + 71.11f, 400 + amplitude/2);
      line(530 + 37 + 71.11f, 400 + amplitude/2, 530 + 143, 400 + amplitude/2); 
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
        y_sin = -1*sin(angle_sin*(numOfWaves/0.8f));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
     
      prevX = prevY = 0.0f;
      
      popMatrix();
      
      pushMatrix();
      translate(636, 400 + amplitude/2);
      scale(1, -1);
     
      for(int count=0; count < 37; ++count)
      {
        x_sin = count;
     
        angle_sin = radians(count);
        y_sin = -1*sin(angle_sin*(numOfWaves/0.8f));
     
        y_sin = map(y_sin,-1,1,-amplitude2/2,amplitude2/2);
     
        line(prevX, prevY, x_sin, y_sin);
     
        prevX = x_sin;
        prevY = y_sin;
      }
     
      prevX = prevY = 0.0f;
      
      popMatrix();
      
      stroke(225, 255, 8);
      line(530, 400 + amplitude/2, 530 + 36, 400 + amplitude/2);
      line(530 + 71.11f, 400 + amplitude/2, 530 + 106, 400 + amplitude/2);
      line(530 + 71.11f + 71.11f, 400 + amplitude/2, 530 + 124 + 36, 400 + amplitude/2);
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
    sf4.getVA().text("A anima\u00e7\u00e3o 'Opera\u00e7\u00e3o intr\u00ednseca do TJB' apresenta um transistor de jun\u00e7\u00e3o bipolar (TJB). O transistor de jun\u00e7\u00e3o bipolar \u00e9 dividido em tr\u00eas partes: o emissor, a base e o coletor. Existem dois tipos de TJB: o npn, que \u00e9 o representado nesta anima\u00e7\u00e3o, e o pnp. " +
    "No transistor npn, o emissor e o coletor s\u00e3o feitos de material semicondutor do tipo n e a base de material semicondutor do tipo p. Nele, os el\u00e9trons s\u00e3o os portadores majorit\u00e1rios. No transistor pnp, o emissor e o coletor s\u00e3o de material p e a base de material n, o que faz com que, nesta variedade do TJB, as lacunas sejam os portadores majorit\u00e1rios. " +
    "Nesta anima\u00e7\u00e3o, ilustraremos apenas o funcionamento do transistor npn, uma vez que ele \u00e9 o mais utilizado comercialmente e o entendimento da sua opera\u00e7\u00e3o \u00e9 facilitado pelo fato de nele os el\u00e9trons serem os portadores majorit\u00e1rios - estudantes, geralmente, est\u00e3o mais familiarizados com o conceito de el\u00e9trons do que com o de lacunas. Al\u00e9m do mais, a opera\u00e7\u00e3o dos transistores npn e pnp \u00e9 bastante semelhante.\n" +
    "Nos transistores de jun\u00e7\u00e3o bipolar, o emissor e o coletor s\u00e3o fortemente dopados e apresentam baixa resistividade. A base, por sua vez, \u00e9 levemente dopada, e, por isso, tem elevada resistividade. Essas caracter\u00edsticas dos semicondutores empregados na fabrica\u00e7\u00e3o dos TJBs desempenham um papel importante na sua opera\u00e7\u00e3o, como veremos adiante.\nInicialmente, a tens\u00e3o entre o emissor e a base e entre a base e o coletor s\u00e3o nulas. Por isso, nessas condi\u00e7\u00f5es, os el\u00e9trons est\u00e3o confinados no emissor e no coletor. As lacunas da base n\u00e3o foram representadas porque, no TJB npn, a corrente el\u00e9trica que flui do emissor ao coletor se deve majoritariamente ao fluxo de el\u00e9trons entre essas regi\u00f5es. " +
    "Clique sobre o slide da fonte de tens\u00e3o 1 para elevar a tens\u00e3o entre emissor e base para aproximadamente 0,6 V. Observe que a jun\u00e7\u00e3o p-n/emissor-base est\u00e1 sob polariza\u00e7\u00e3o direta e que sua camada de deple\u00e7\u00e3o diminui \u00e0 medida que o potencial aplicado aumenta, ou seja, \u00e0 medida que o slide da fonte de tens\u00e3o se desloca para cima. O valor de 0,6 V para a tens\u00e3o entre emissor e base corresponde \u00e0 tens\u00e3o de barreira da jun\u00e7\u00e3o p-n de sil\u00edcio dopado e \u00e9 o m\u00ednimo necess\u00e1rio para que esta jun\u00e7\u00e3o conduza, exatamente como nos diodos reais.\n"+
    "Com a jun\u00e7\u00e3o base-emissor polarizada diretamente, alguns el\u00e9trons do emissor conseguem atravessar a zona de deple\u00e7\u00e3o e instalam-se na base. Note que a corrente que flui entre a base e o emissor \u00e9 pequena devido \u00e0 elevada resistividade do material que comp\u00f5e a base.\n" +
    "Clique sobre o slide da fonte de tens\u00e3o 2 para elevar a tens\u00e3o entre emissor e coletor a 5 V. Esta fonte deve fornecer um potencial superior a 0,6 V para que o transistor opere no modo ativo, que \u00e9 o representado nesta anima\u00e7\u00e3o. Os outros dois modos de opera\u00e7\u00e3o do transistor - satura\u00e7\u00e3o e corte - ser\u00e3o explorados na aba 'Modos de opera\u00e7\u00e3o'. No modo ativo, o coletor precisa estar em um potencial mais elevado do que a base, ou seja, a jun\u00e7\u00e3o base-coletor deve estar polarizada reversamente. Note que a camada de deple\u00e7\u00e3o entre a base e o coletor aumenta conforme a tens\u00e3o aplicada aumenta.\n" +
    "A polariza\u00e7\u00e3o reversa aumenta o campo el\u00e9trico gerado pelas cargas fixas da jun\u00e7\u00e3o base-coletor. Este campo el\u00e9trico \u00e9 orientado do coletor em dire\u00e7\u00e3o \u00e0 base e, portanto, favor\u00e1vel ao deslocamento dos el\u00e9trons localizados na base para o coletor. A corrente que flui entre o coletor e o emissor \u00e9 intensa devido \u00e0 baixa resistividade do material que os comp\u00f5e. Esta corrente \u00e9 proporcional \u00e0 corrente entre emissor e base segundo um fator que depende apenas das caracter\u00edsticas construtivas do transistor e que, tipicamente, varia entre 50 e 200, podendo chegar a 1000 em dispositivos especiais.", 10, 20, 255, 2200);
    sf4.endVAdraw();
    rectMode(CORNER);
    imageMode(CORNER);
    
    //programa\u00e7\u00e3o dos pgs
    
    pg9.beginDraw();
    pg9.background(183, 232, 180, 100);
    for(cont = 0; cont < 6; cont++) {
      if(passe13[cont] == 2) {
        v2[cont].display_lacuna(); 
        if(v2[cont].get_xc() >= 110 || v2[cont].get_xc() <= 10)
            v2[cont].invert_spx();
         if(v2[cont].get_y() >= 70 && v2[cont].get_y() <= 110)
           v2[cont].set_spy(random(-0.8f, -0.1f));
         else if(v2[cont].get_y() <= 50 && v2[cont].get_y() >= 10)
           v2[cont].set_spy(random(0.1f, 0.8f));
         else
           v2[cont].set_spy(random(-0.8f, 0.8f)); 
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
         n2[cont].set_spy(random(-0.8f, -0.1f));
       }
       else if(n2[cont].get_y() <= 30 && n2[cont].get_y() >= 10) {
         n2[cont].set_spy(random(0.1f, 0.8f));
       }
       else
         n2[cont].set_spy(random(-0.8f, 0.8f)); 
       if(n2[cont].get_y() <= 10 || n2[cont].get_y() >= 110) {
         n2[cont].invert_spy();
       }
     }
    }
    for(cont = 0; cont < 6; cont++) { 
      if(verif3[cont] == 1) {
        n1[cont].display();
        if(n1[cont].get_y() >= 100 && n1[cont].get_y() <= 110) {
           n1[cont].set_spy(random(-0.8f, -0.1f));
         }
         else if(n1[cont].get_y() <= 20 && n1[cont].get_y() >= 10) {
           n1[cont].set_spy(random(0.1f, 0.8f));
         }
         else
           n1[cont].set_spy(random(-0.8f, 0.8f)); 
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
           v2[cont].set_spy(random(-0.8f, -0.1f));
        }
        else if(v2[cont].get_y() <= 30 && v2[cont].get_y() >= 10) {
           v2[cont].set_spy(random(0.1f, 0.8f));
        }
        else
           v2[cont].set_spy(random(-0.8f, 0.8f)); 
        if((v2[cont].get_y() <= 10 && v2[cont].get_y() >= -1000) || (v2[cont].get_y() >= 110 && v2[cont].get_y() <= 1000)) {
           v2[cont].invert_spy();
        }
      }
    }
    pg9.endDraw();
    
    pg10.beginDraw();
    pg10.background(192, 237, 237, 100);
    //el\u00e9trons passando (emissor - coletor)
    for(cont = 6; cont < 12; cont++) {
      if(passe13[cont] == 5) {
        v2[cont].display(); 
        if(v2[cont].get_xc() >= 110 || v2[cont].get_xc() <= 10)
          v2[cont].invert_spx();
        v2[cont].set_spy(random(-0.6f, -0.8f));
        v2[cont].set_spx(random(-0.4f, 0.4f));
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
         b[0].set_spy(random(-0.8f, -0.1f));
       }
       else if(b[0].get_y() <= 15 && b[0].get_y() >= 5) {
         b[0].set_spy(random(0.1f, 0.8f));
       }
       else
         b[0].set_spy(random(-0.8f, 0.8f)); 
       if(b[0].get_y() <= 5 || b[0].get_y() >= 55) {
         b[0].invert_spy();
       }
    }
    else if(passe10 == 4) {
       b[3].display();
       if(b[3].get_xc() >= 110 || b[3].get_xc() <= 10)
        b[3].invert_spx();
       if(b[3].get_y() >= 45 && b[3].get_y() <= 55) {
         b[3].set_spy(random(-0.8f, -0.1f));
       }
       else if(b[3].get_y() <= 15 && b[3].get_y() >= 5) {
         b[3].set_spy(random(0.1f, 0.8f));
       }
       else
         b[3].set_spy(random(-0.8f, 0.8f)); 
       if(b[3].get_y() <= 5 || b[3].get_y() >= 55) {
         b[3].invert_spy();
       }
    }
    for(cont = 0; cont < 10; cont++) {
      if(verif2[cont] == 1) { 
         n1[cont].display();
         if(ce == true && be == true && y_sl2 <= 65) {
           if(n1[cont].get_y() >= 45 && n1[cont].get_y() <= 55 && cont >= 6) {
             n1[cont].set_spy(random(-0.8f, -0.1f));
           }
           else if(n1[cont].get_y() <= 15 && n1[cont].get_y() >= 5 && cont >= 6) {
             n1[cont].set_spy(random(0.1f, 0.8f));
           }
           else if(cont >= 6)
             n1[cont].set_spy(random(-0.8f, 0.8f)); 
           if(((n1[cont].get_y() <= 5 && n1[cont].get_y() >= -1000) || (n1[cont].get_y() >= 55 && n1[cont].get_y() <= 1000)) && cont >= 6) {
             n1[cont].invert_spy();
           }
           if(cont < 6 && verif3[cont] == 0) {
             n1[cont].set_spy(-0.6f);
             if(n1[cont].get_y() < 5) {
               n1[cont].change_pg(pg9);
               verif3[cont] = 1;
               n1[cont].set_yc(random(95, 105));
             }
           }
         }
         else if(ce == false || (ce == true && y_sl2 > 65)) {
            if(n1[cont].get_y() >= 45 && n1[cont].get_y() <= 55) {
               n1[cont].set_spy(random(-0.8f, -0.1f));
             }
             else if(n1[cont].get_y() <= 15 && n1[cont].get_y() >= 5) {
               n1[cont].set_spy(random(0.1f, 0.8f));
             }
             else
               n1[cont].set_spy(random(-0.8f, 0.8f)); 
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
           v2[cont].set_spy(random(-0.8f, -0.1f));
         else if(v2[cont].get_y() <= 20 && v2[cont].get_y() >= 10)
           v2[cont].set_spy(random(0.1f, 0.8f));
         else
           v2[cont].set_spy(random(-0.8f, 0.8f)); 
         if(v2[cont].get_y() <= 10 || v2[cont].get_y() >= 110)
           v2[cont].invert_spy();
      }
      else if(passe13[cont] == 4) {
         v2[cont].set_spy(random(-0.6f, -0.8f));
         v2[cont].set_spx(random(-0.4f, 0.4f));
         if(v2[cont].get_y() < 10) {
           v2[cont].change_pg(pg10);
           passe13[cont] = 5;
           v2[cont].set_yc(50);
         }
      }
    }
    //el\u00e9tron proveniente da fonte entre a base e o emissor
    if(passe11 == 3 && passe10 != 4) {
       b[3].display();
       if(b[3].get_xc() >= 110 || b[3].get_xc() <= 10)
        b[3].invert_spx();
       if(b[3].get_y() >= 100 && b[3].get_y() <= 110 && passe10 != 3) {
         b[3].set_spy(random(-0.8f, -0.1f));
       }
       else if(b[3].get_y() <= 20 && b[3].get_y() >= 10 && passe10 != 3) {
         b[3].set_spy(random(0.1f, 0.8f));
       }
       else if(passe10 == 3) {
         if(b[3].get_yc() >= 10) {
           b[3].set_spy(random(-0.6f, -0.8f));
           b[3].set_spx(random(-0.4f, 0.4f));
         }
         else {
           passe10 = 4;
           b[3].change_pg(pg10);
           b[3].set_xc(60);
           b[3].set_yc(54);
         }
       }
       else if(passe10 != 4)
         b[3].set_spy(random(-0.8f, 0.8f)); 
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
       n1[cont].set_spy(random(-0.8f, -0.1f));
     }
     else if(n1[cont].get_y() <= 20 && n1[cont].get_y() >= 10) {
       n1[cont].set_spy(random(0.1f, 0.8f));
     }
     else
       n1[cont].set_spy(random(-0.8f, 0.8f)); 
     if(n1[cont].get_y() <= 10 || n1[cont].get_y() >= 110) {
       n1[cont].invert_spy();
     }
    }
    if(be == false || (be == true && y_sl >= 290)) {
      for(cont = 0; cont < 10; cont++) {
       if(n1[cont].get_y() >= 100 && n1[cont].get_y() <= 110) {
         n1[cont].set_spy(random(-0.8f, -0.1f));
       }
       else if(n1[cont].get_y() <= 20 && n1[cont].get_y() >= 10) {
         n1[cont].set_spy(random(0.1f, 0.8f));
       }
       else
         n1[cont].set_spy(random(-0.8f, 0.8f)); 
       if(n1[cont].get_y() <= 10 || n1[cont].get_y() >= 110) {
         n1[cont].invert_spy();
       }
      } 
    }
    else {
      for(cont = 0; cont < 10 && verif2[cont] == 0; cont++) {
        n1[cont].set_spy(random(-0.4f, -0.8f));
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
    pg8.rect(80, 255, 120, 15 - (65 - (y_sl - 290))/8); //diminuir\u00e1
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
    pg8.strokeWeight(2); //fontes de tens\u00e3o
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
      y_sl = y_sl - 0.4f;
    pg8.line(32, y_sl, 48, y_sl);
    if(ce == true && y_sl2 > 65)
      y_sl2 = y_sl2 - 0.4f;
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
         if(verif4[i] == 1) //verificador para garantir que a lacuna j\u00e1 tenha "sa\u00eddo" do gerador
           v2[i].display_lacuna();
         if(v2[i].get_yc() <= 38 && passe13[i] == 0) {
           passe13[i] = 1;
           v2[i].set_spx(-0.4f);
           v2[i].set_spy(0);
         }
         else if(passe13[i] == 1 && v2[i].get_xc() <= 148) {
           v2[i].change_pg(pg9);
           v2[i].set_xc(60);
           v2[i].set_yc(15);
           v2[i].set_spx(random(-0.4f, 0.6f));
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
         if(verif4[i] == 1) //verificador para garantir que o el\u00e9tron j\u00e1 tenha "sa\u00eddo" do gerador
           v2[i].display();
         if(v2[i].get_yc() >= 410 && passe13[i] == 0) {
           passe13[i] = 1;
           v2[i].set_spx(-0.6f);
           v2[i].set_spy(0);
         }
         else if(passe13[i] == 1 && v2[i].get_xc() <= 148) {
           v2[i].set_spx(0);
           v2[i].set_spy(-0.6f);
           passe13[i] = 2;
         }
         else if(passe13[i] == 2 && v2[i].get_yc() <= 392) {
           passe13[i] = 3;
           v2[i].change_pg(pg11);
           v2[i].set_xc(60);
           v2[i].set_yc(105);
           v2[i].set_spx(random(0.4f, 0.6f));
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
          v2[i].set_spy(-0.4f);
          neut3[i] = neut4[i] = -1;
        }
        for(i = 6; i < 12; i++) {
          v2[i].set_yc(185 - 200*(i - 6));
          v2[i].set_spy(0.4f);
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
         b[0].set_spx(0.4f);
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
         b[3].set_spx(0.6f);
         b[3].set_spy(0);
       }
       else if(passe11 == 1 && b[3].get_xc() >= 148) {
         passe11 = 2;
         b[3].set_spx(0);
         b[3].set_spy(-0.6f);
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
        b[0].set_spy(-0.4f);
        b[3].set_spx(0);
        b[3].set_spy(0.6f);
      }
    }
    pg8.popMatrix();
    pg8.endDraw();
    image(pg8, 25, 105);
    fill(0);
    textFont(fonte1);
    text("Opera\u00e7\u00e3o intr\u00ednseca do TJB", 232, 96);
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
        text("Com as fontes de tens\u00e3o 1 e 2 desligadas, as jun\u00e7\u00f5es p-n base-emissor e base-coletor est\u00e3o em aberto e o transistor n\u00e3o conduz." +
       " Clique sobre o slider da fonte de tens\u00e3o 1 para polarizar a jun\u00e7\u00e3o base-emissor diretamente, fazendo-a conduzir.", 32, 515, 410, 200);
      }
      if(y_sl <= 290 && y_sl2 > 65) {
        textFont(fonte7);
        text("Os el\u00e9trons se acumulam na base, que est\u00e1 em um potencial mais elevado. A corrente de base \u00e9 pequena devido \u00e0 alta resistividade/baixa dopagem dessa regi\u00e3o. N\u00e3o h\u00e1 corrente de coletor pois a jun\u00e7\u00e3o base-coletor est\u00e1 em aberto. Clique sobre o slider da fonte de tens\u00e3o 2 para polariz\u00e1-la reversamente.", 32, 515, 410, 200);
      }
      if(y_sl <= 290 && y_sl2 <= 65) {
        textFont(fonte7);
        text("O coletor est\u00e1 em um potencial mais elevado do que o da base. Assim, os el\u00e9trons acumulados na base se deslocam para o coletor. A corrente de coletor \u00e9 mais intensa que a de base porque o coletor \u00e9 fortemente dopado, e, portanto, tem baixa resistividade. O transistor est\u00e1 operando no modo ativo.", 32, 515, 410, 200);
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
    
    strokeWeight(4.8f);
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
     if(vb > 0.7f && vb <= 5) {
       v_b = 0.7f;
       ib = (vb - 0.7f)/200;
       ic = 100*ib;
       ie = ic + ib;
       v_c = 5 - 2*ic;

     }
     else if(vb < 0.7f) {
       ib = 0;
       ic = 0;
       ie = 0;
       v_c = 5;
       v_b = vb;

     }
     else if(vb > 5) {
      v_c = 0;
      ic = 2.5f;
      v_b = 0.7f;
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
      pg1.image(led_on, 370, 230, 40, 84.8f);
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
   strokeWeight(4.8f);
   stroke(220, 220, 220);
   rect(25, 105, 400, 475);
   textFont(fonte1);
   fill(0);
   textAlign(LEFT);
     
   if(op2 == 'B') {
    fill(0);
    text("O TJB como chave eletr\u00f4nica", 93, 95);
    textFont(fonte2);
    text("6 V", 170, 493);
    text("Chave C", 90, 430);
    text("Modos de opera\u00e7\u00e3o do TJB", 140, 570);
    image(back, 110, 548);
    textFont(fonte4);
    text("2", 125, 477);
    text("1", 125, 451);
    textFont(fonte7);
    textAlign(CENTER);
    text("Clique sobre a chave C para observar a opera\u00e7\u00e3o do TJB como chave eletr\u00f4nica.", 26, 517, 400, 150);
    if(chave == false) {
      text("Com a chave na posi\u00e7\u00e3o 1, a tens\u00e3o na base \u00e9 nula e o TJB est\u00e1 em corte. Dessa forma, o circuito do LED fica em aberto e o LED n\u00e3o acende.", 272, 120, 140, 200);
    }
    else {
      text("Com a chave na posi\u00e7\u00e3o 2, a tens\u00e3o na base \u00e9 grande o bastante para que o TJB sature (analise a aba 'Modos de opera\u00e7\u00e3o do TJB' desta anima\u00e7\u00e3o). Entre os terminais de coletor e emissor se forma um 'curto-circuito virtual' e, assim, o LED acende.", 35, 118, 180, 200);
    }
    textAlign(LEFT);
   }
   else {
     fill(0);
     text("Modos de opera\u00e7\u00e3o do TJB", 105, 95);
     textFont(fonte2);
     text("O TJB como chave eletr\u00f4nica", 110, 567);
     image(forw, 305, 548);
     text(nf(vb, 1, 2) + " V", 125, 445);
     text("Vb = " + nf(v_b, 1, 2) + " V\nVc = " + nf(v_c, 1, 2) + " V\nIb = " + nf(ib, 1, 4) + " mA\nIc = " + nf(ic, 1, 4) + " mA\nIe = " + nf(ie, 1, 4) + " mA", 40, 145);
     textFont(fonte7);
     textAlign(CENTER);
     text("Utilize as setas da fonte de tens\u00e3o vari\u00e1vel para aumentar ou diminuir a tens\u00e3o de base e alterar o modo de opera\u00e7\u00e3o do TJB.", 26, 508, 400, 150);
     textAlign(LEFT);
     if(vb > 0.7f && vb <= 5) {
       textFont(fonte1);
       fill(0, 0, 255);
       text("Modo ativo", 288, 140);
       fill(0);
       textFont(fonte7);
       textAlign(CENTER);
       text("Neste modo, o TJB conduz e as correntes de coletor e de base guardam uma rela\u00e7\u00e3o de proporcionalidade.", 258, 160, 160, 200);
       textAlign(LEFT);
     }
     else if(vb < 0.7f) {
       textFont(fonte1);
       fill(0, 0, 255);
       text("Corte", 310, 140);
       fill(0);
       textFont(fonte7);
       textAlign(CENTER);
       text("Neste modo, o TJB n\u00e3o conduz e produz uma tens\u00e3o em aberto entre o coletor e o emissor. ", 258, 160, 160, 200);
       textAlign(LEFT);
     }
     else if(vb > 5) {
      textFont(fonte1);
      fill(0, 0, 255);
      text("Satura\u00e7\u00e3o", 298, 130);
      fill(0);
      textFont(fonte7);
      textAlign(CENTER);
      text("Neste modo, o TJB conduz, mas as correntes de coletor e de base n\u00e3o s\u00e3o proporcionais.", 262, 140, 160, 200);
      text("A tens\u00e3o entre coletor e emissor \u00e9 nula, ou seja, esses terminais funcionam como um curto-circuito.", 280, 188, 125, 200);
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
    sf5.getVA().text("Esta anima\u00e7\u00e3o possui duas abas: 'Modos de opera\u00e7\u00e3o do TJB' e 'O TJB como chave eletr\u00f4nica'." +
    "Em ambas as abas, o TJB representado na forma circuital \u00e9 do tipo npn e o fator de proporcionalidade entre a corrente de coletor e a de base, denominado beta, vale 100. Nas representa\u00e7\u00f5es circuitais de TJBs npn ou pnp, o emissor \u00e9 sempre a extremidade do transistor onde est\u00e1 desenhada a seta indicativa do sentido da corrente el\u00e9trica. Assim, na aba 'Modos de opera\u00e7\u00e3o do TJB', Ve indica o potencial" +
    " do emissor. Analogamente, Vb e Vc s\u00e3o os potenciais de base e coletor. Ib, Ic e Ie se referem \u00e0s correntes de base, coletor e emissor, respectivamente.\nNesta aba, utilize a fonte de tens\u00e3o vari\u00e1vel para elevar ou reduzir a tens\u00e3o aplicada ao resistor da base do TJB e observar a sua influ\u00eancia no modo de opera\u00e7\u00e3o do transistor e nas demais tens\u00f5es e correntes dos circuito.\n" +
    "No modo de opera\u00e7\u00e3o ativo, a jun\u00e7\u00e3o base-emissor deve estar diretamente polarizada e a jun\u00e7\u00e3o base-coletor reversamente polarizada. Portanto, nessa situa\u00e7\u00e3o Vc > Vb > Ve. Pelo circuito, Ve = 0. Como a jun\u00e7\u00e3o base-emissor est\u00e1 diretamente polarizada, a diferen\u00e7a de tens\u00e3o Vb - Ve vale aproximadamente 0,7 V. Este potencial corresponde ao potencial de barreira da jun\u00e7\u00e3o p-n/base-emissor para dispositivos de sil\u00edcio em temperaturas inferiores \u00e0 temperatura m\u00e1xima de opera\u00e7\u00e3o, que varia entre 150\u00b0C e 200\u00b0C. " +
    "Logo, Vb = 0,7 V no modo ativo.\nConforme ilustrado na aba 'O TJB', no transistor npn a corrente de base tem sentido para dentro da base. Assim, se o potencial aplicado ao resistor da base for inferior ao potencial t\u00edpico da base no modo ativo - 0,7 V -, n\u00e3o fluir\u00e1 corrente pela base. Consequentemente, Vb assumir\u00e1 o valor do potencial ajustado na fonte de tens\u00e3o vari\u00e1vel, pois, na aus\u00eancia de corrente, n\u00e3o h\u00e1 queda de tens\u00e3o no resistor da base. Nessas condi\u00e7\u00f5es, ambas as jun\u00e7\u00f5es p-n (base-emissor e base-coletor) est\u00e3o reversamente polarizadas e, portanto, o transistor est\u00e1 em corte. Abaixe a tens\u00e3o aplicada ao resistor da base para 0,5 V ou menos para verificar esse fato. Note que, por n\u00e3o haver corrente de coletor, Vc = 5 V e o transistor se comporta como uma chave aberta entre os terminais do coletor e do emissor.\n" +
    "A an\u00e1lise anterior permite concluir que o potencial aplicado ao resistor da base tem influ\u00eancia sobre a corrente de base. Como, no modo ativo, Ic e Ib s\u00e3o proporcionais, isto \u00e9, Ic =      x Ib, a influ\u00eancia do potencial do resistor da base se estende \u00e0 corrente de coletor e, consequentemente, \u00e0 tens\u00e3o no coletor. Se o potencial fornecido pela fonte de tens\u00e3o for elevado a valores que determinem corrente de coletor grande o bastante para que Vc < Vb (sendo que Vb = 0,7 V, porque a jun\u00e7\u00e3o base-emissor se mant\u00e9m diretamente polarizada), o transistor saturar\u00e1. Na satura\u00e7\u00e3o, ambas as jun\u00e7\u00f5es p-n (base-emissor e base-coletor) est\u00e3o diretamente polarizadas e, como Vb = 0,7 V, Vc passa a valer 0 V. Eleve a tens\u00e3o na fonte vari\u00e1vel para valores superiores a 5,5 V para verificar esse fato. Note que, como Vc = Ve = 0 V, na satura\u00e7\u00e3o o trasnsistor se comporta como um curto-circuito ou uma chave fechada entre os terminais do coletor e do emissor. \n" +
    "A aba 'O TJB como chave eletr\u00f4nica' trata do funcionamento do transistor como chave eletr\u00f4nica. Entre o coletor e o emissor, h\u00e1 uma bateria DC e um LED (diodo emissor de luz). A tens\u00e3o fornecida pela bateria \u00e9 a tens\u00e3o nominal do LED. O LED s\u00f3 acende quando o circuito \u00e9 fechado, isto \u00e9, quando o transistor atua como um curto circuito. Isto acontece na satura\u00e7\u00e3o. Quando o transistor funciona como uma chava aberta - no corte -, o LED permanece apagado. Utilize a chave C para controlar o funcionamento do transistor como chave. Quando a chave C estiver na posi\u00e7\u00e3o 1, o transistor estar\u00e1 saturado, pois a bateria de 6 V imp\u00f5e Ib grande o bastante para que Vc < Vb, como verificamos na an\u00e1lise realizada na aba 'Modos de opera\u00e7\u00e3o do TJB'. Note que o LED acende. Quando a chave C \u00e9 passada para a posi\u00e7\u00e3o 2, a queda de tens\u00e3o no resistor n\u00e3o condiz com o sentido de Ib, que deve ser para dentro da base. Assim, n\u00e3o h\u00e1 corrente na base e o transistor est\u00e1 em corte, funcionando como chave aberta. Por isso, o LED n\u00e3o acende.", 10, 20, 270, 2200);
    sf5.getVA().image(beta, 178, 1169, 20, 20);
    sf5.endVAdraw();
    rectMode(CORNER);
    imageMode(CORNER);
  }
  else if(window == 6) {
    configs(window);
    fill(0);
    textFont(fonte1);
    text("Este tutorial se divide em cinco abas de teoria e quatro de informa\u00e7\u00f5es extras, identificadas pelo nome na por\u00e7\u00e3o superior da tela. Para acessar uma aba, clique no bot\u00e3o em forma de seta correspondente. Cada uma das abas aborda conceitos relacionados a diodos e transistores de jun\u00e7\u00e3o bipolar, compreendendo desde aspectos de sua fabrica\u00e7\u00e3o, a partir de materiais semicondutores, at\u00e9 o seu funcionamento propriamente dito.\nTodas as abas cont\u00eam uma ou mais anima\u00e7\u00f5es e um texto explicativo de apoio. As instru\u00e7\u00f5es referentes a cada uma delas est\u00e3o presentes nas pr\u00f3prias anima\u00e7\u00f5es, que tamb\u00e9m contam com uma breve descri\u00e7\u00e3o dos elementos e processos nelas ilustrados. Se voc\u00ea tiver d\u00favidas ou quiser saber mais, consulte o texto de apoio.\n\nA aba 'O sil\u00edcio e dopantes do tipo p e n' trata da distribui\u00e7\u00e3o at\u00f4mica do sil\u00edcio e explora a possibilidade de altera\u00e7\u00e3o de sua condutividade pela dopagem com boro (p) ou f\u00f3sforo (n). Esta aba se divide em tr\u00eas sub abas sequenciais, que podem ser acessadas clicando-se nos bot\u00f5es de prosseguir (      ) e voltar (      ), identificados com o nome da sub aba de destino, na parte inferior da tela.\n\n", 60, 110, 680, 500);
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
    text("\u00c1tomos de sil\u00edcio", 270, 204);
    text("\u00c1tomos de boro", 267, 320);
    text("\u00c1tomos de f\u00f3sforo", 269, 452);
    text("El\u00e9tron", 606, 185);
    text("Lacuna eletr\u00f4nica", 608, 244);
    text("S\u00edlicio tipo p", 608, 305);
    text("S\u00edlicio tipo n", 608, 366);
    text("Camada de deple\u00e7\u00e3o", 608, 426);
    text("Campo el\u00e9trico", 608, 480);
    
  }
  else if(window == 8) {
    configs(window);
    textFont(fonte7);
    fill(0);
    text("SEDRA, Adel S.; SMITH, Kenneth C., Microeletr\u00f4nica. 5a. ed. S\u00e3o Paulo: Pearson Prentice Hall, 2007.\n\nHonsberg, Christiana; Bowden, Stuart. 'Band Gap'. Photovoltaic Education.\nhttp://www.pveducation.org/pvcdrom/pn-junction/band-gap. Acesso em 10 set. 2012\n\n'Introdu\u00e7\u00e3o aos Semicondutores'.\nhttp://aquarius.ime.eb.br/~aecc/FundEngEle/Semicondutores.pdf. Acesso em 10 set. 2012\n\nCoates, Eric. 'Bipolar Junction Transistors (BJTs)'. Learn about-Electronics\nhttp://www.learnabout-electronics.org/bipolar_junction_transistors_01.php. Acesso em 10 set. 2012", 20, 100, 365, 400);
    text("Chandler, Nathan. 'How Transistors Work'. How Stuff Works.\nhttp://electronics.howstuffworks.com/transistor4.htm. Acesso em 15 set. 2012\n\nDutra, Daniel A.; Mayrinck, Igor B.; Costa Jr., Marcos E.; Holder, Robinson P.; Conhalata, Rodrigo S.; Silveira, Sandro E. 'Caracteriza\u00e7\u00e3o da Varia\u00e7\u00e3o de Par\u00e2metros de Diodos e Transistores em Fun\u00e7\u00e3o da Temperatura'.\nhttp://www.cpdee.ufmg.br/~jramirez/disciplinas/materiais/trab6.pdf. Acesso em 2 nov. 2012", 415, 100, 365, 400);
    textFont(fonte2);
    text("Imagens e \u00edcones:", 30, 386, 740, 500);
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
    text("Desenvolvimento: Luana Ianara Rubini Ruiz (Aluna do 3\u00ba ano do Curso de Engenharia El\u00e9trica - Sistemas Eletr\u00f4nicos, Escola Polit\u00e9cnica da Universidade de S\u00e3o Paulo, S\u00e3o Paulo - SP, Brasil)\nOrienta\u00e7\u00e3o: Ivette Frida Cymbaum Oppenheim (Departamento de Engenharia Metal\u00fargica e de Materiais, Escola Polit\u00e9cnica da Universidade de S\u00e3o Paulo, S\u00e3o Paulo - SP, Brasil)\nTexto: Luana Ianara Rubini Ruiz e Ivette Frida Cymbaum Oppenheim\nCriado em: Processing                    \u00daltima atualiza\u00e7\u00e3o: 24/janeiro/2013", 60, 128, 680, 420);
    text("\nApoio: O tutorial 'Diodos e Transistores de Jun\u00e7\u00e3o Bipolar' foi desenvolvido no contexto do projeto 'Anima\u00e7\u00f5es para disciplinas Introdut\u00f3rias de Ci\u00eancia e Engenharia dos Materiais', integrante do 'Programa Ensinar com Pesquisa' da Pr\u00f3-Reitoria de Gradua\u00e7\u00e3o da Universidade de S\u00e3o Paulo, \u00e0 qual expressamos nossos agradecimentos pela concess\u00e3o da bolsa de Inicia\u00e7\u00e3o Cient\u00edfica (Projeto 5767 - ano 2012).", 60, 258, 680, 420);  
    text("Sobre o Processing: 'Processing' \u00e9 uma linguagem de programa\u00e7\u00e3o e ambiente de desenvolvimento com foco principal nas artes visuais, aproveitando-se da plataforma Java e de uma comunidade virtual intensamente atuante por meio do compartilhamento de c\u00f3digos e bibliotecas atrav\u00e9s da Internet. Surgiu como iniciativa de Casey Reas e Ben Fry, pesquisadores do Instituto de Tecnologia de Massachussets (MIT) dos E.U.A, sendo, desde sua cria\u00e7\u00e3o em 2001, disponibilizado gratuitamente como software livre, acess\u00edvel a qualquer interessado a partir do download no site \u2018processing.org', onde podem ser encontradas informa\u00e7\u00f5es adicionais sobre 'Processing'.", 60, 400, 680, 410);
  }
  
  
}
class Particulas {
 
 float angle, d, raio, x_c, y_c, freq, x, y, speedx, speedy, x_ant, y_ant;
 float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
 int c1, c2;
 PGraphics pg;
 
 Particulas(float temp_angle, float temp_d, float temp_raio, float temp_x_c, float temp_y_c, float temp_freq, float temp_speedx, float temp_speedy, int temp_c1, int temp_c2, PGraphics temp_pg) {
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
 
 public void display() {
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
 
 public void orbitar() {
   angle-= freq;
   x = x_c + cos(radians(angle))*raio;
   y = y_c + sin(radians(angle))*raio;
 }
 
 public void mover() {
   x_c = x_c + speedx;
   y_c = y_c + speedy;
 }
 
 public float get_ang() {
   return angle;
 }
 
 public float get_x() {
   float r;
   r = x_c + cos(radians(angle))*raio;
   return r;
 }
 
 public float get_y() {
   float r;
   r = y_c + sin(radians(angle))*raio;
   return r;
 }
 
 public float get_xc() {
   return x_c;
 }
 
 public float get_spx() {
   return speedx;
 }
 
 public float get_spy() {
   return speedy;
 }
 
 public float get_yc() {
   return y_c;
 }
 
 public void set_xc(float temp) {
   x_c = temp;
 }
 
 public void set_yc(float temp) {
   y_c = temp;
 }
 
 public void set_ang(float temp) {
   angle = temp;
 }
 
 public void invert_spx() {
    speedx = -speedx; 
 }
 
 public void invert_spy() {
    speedy = -speedy; 
 }
 
 public void set_spx(float sp) {
    speedx = sp; 
 }
 
 public void set_spy(float sp) {
    speedy = sp; 
 }
 
 public void change_pg(PGraphics pgr) {
    pg = pgr;  
 }
 
 public void display_lacuna() {
    x = x_c + cos(radians(angle))*raio;
    y = y_c + sin(radians(angle))*raio;
    pg.stroke(147, 7, 89);
    pg.strokeWeight(1.5f);
    pg.line(x - 4, y - 4, x + 4, y + 4);
    pg.line(x + 4, y - 4, x - 4, y + 4);
 }
 public void encontrar_lacuna() {
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
    if((x >= x2 - 0.5f && x <= x2 + 0.5f) || (y >= y2 - 0.5f && y <= y2 + 0.5f)) {
      speedx = 0;
      speedy = 0;
      p[19].display_lacuna();
      display();
      q = 1;
    }
    else if((x <= x2 - 0.5f || x >= x2 + 0.5f) && (y <= y2 - 0.5f || y >= y2 + 0.5f) && q == 0) {
      mover();
      display();
      p[19].display_lacuna();
      pg4.strokeWeight(1.5f);
      pg4.stroke(147, 7, 89);
      pg4.line(x2 - 4, y2 - 4, x2 + 4, y2 + 4);
      pg4.line(x2 + 4, y2 - 4, x2 - 4, y2 + 4);
    }
  }
}




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
  int bg, border;
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
  
  public PopUp(Object _parent, int _x, int _y, int _w, int _h, int _b, String _renderer, int _fadeintime, float _maxalpha, int _bg, int _border) {
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
               float _maxalpha, int _bg, int _border, int _ID, String _name) {
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
          K = (fadeMode) ? ( 1.0f/(1-initialAlpha/maxalpha) ) : ( maxalpha/initialAlpha ) ;
          fadeTimer.setTime(round(K*fadeTime));
          fadeTimer.restart();
        }
        timePercent = PApplet.parseFloat(fadeTimer.elapsed())/ ( K*fadeTime );
        if (fadeMode) { // fading in
          alphaPercent = initialAlpha/maxalpha + timePercent;
          if (alphaPercent >= 1 - 0.005f) { // 0.005 is tolerance
            alphaPercent = 1;  
            isActive = true; 
            restartFade = false; isFading = false; // deactivate daemon 
            // leave initial settings for next daemon call (request fading restart)
            initialAlpha = maxalpha;
          }
        } else { // fading out
          alphaPercent = initialAlpha/maxalpha - timePercent;
          if (alphaPercent <= 0 + 0.005f) { // 0.005 is tolerance
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
          float timePercent = PApplet.parseFloat(autoFadeTimer.elapsed()) / (PApplet.parseFloat(autoFadeTime));
            if (timePercent >= 1 - 0.005f) { // 0.005 is tolerance
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
    
  public void setMovableTrigger(PImage _custommap, int _mc) { // set custom screenregion (WILL BE RESIZED TO FIT WITHIN POPUP!)
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
      borders.setBackground( new Color(PApplet.parseInt(red(border)), PApplet.parseInt(green(border)), PApplet.parseInt(blue(border))) );
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
  int bg;
  String MODE;
  PeasyCam cam;
  boolean space3D;
  
  boolean m_isPressed, m_wasPressed, mw_hasChanged, enableMouse, enableMouseWheel;
  //MouseWheelEvent mwe;
  
  boolean globalSetupDoneFlag, plugAppletSetupDone;
  
  Object parent; PopUp parent2;
  Method myDraw, myMousePressed, myMouseReleased, myMouseClicked;
  
  public plugApplet(Object _parent, PopUp _parent2, int _w, int _h, String _MODE, int _bg) {
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
    if (key == 'k') println("H\u00c1!");
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
  private int bg, fg;
  private boolean hasCloseBeenCalled;
  
  public loadingPopUp(int _x, int _y, int _dim, int _bg, int _fg, int _num, int _period) {
    x = _x; y = _y; dim = _dim; bg = _bg; fg = _fg; num = _num; period = _period;
    canvas = new LPApplet();
    add(canvas);
    setSize(dim, dim);
    setLocation(x - round(dim*0.5f), y - round(dim*0.5f));
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
    int eshade;
    float [] posx, posy;
    int i;
    int cur_fill;
    
    
    public LPApplet() {
      super();
      ed = dim*PI/(1.2f*num + PI);
      r = (dim*0.95f - ed)*0.5f;
      posx = new float[num]; posy = new float[num];
      for (i = 0; i < posx.length; i++) {
        posx[i] = 0.5f*dim + r*sin(-i*2*PI/num);
        posy[i] = 0.5f*dim + r*cos(-i*2*PI/num);
      }
    }
    
    public void setup() {
      size(dim, dim, P2D);
      eshade = lerpColor(bg, fg, 0.5f);
      noStroke();
      ellipseMode(CENTER);
    }
    
    public void draw() {
      cur_fill = eshade;
      
      background(bg);
      
      for (i = 0; i < num; i++) {
        cur_fill = (PApplet.parseInt(frameCount/period) % num == i) ? (fg) : (eshade) ;
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
  
  public int addPopUp(Object _parent, String _name, int _x, int _y, int _w, int _h, int _b, String _renderer, int _fadeintime, float _maxalpha, int _bg, int _border) {
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
    returner[0] = x - w*0.5f - flag*margin; returner[1] = y - h*0.5f - flag*margin;
    returner[2] = x + w*0.5f + flag*margin; returner[3] = y + h*0.5f + flag*margin;
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
  int bg, border;
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
  
  public ImageBox(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, int _bg, int _border, int _bstroke) {
    // flat colour
    super(_parent, _layer);
    bg = _bg; border = _border; margin = _bstroke;
    setBoundingBox(_x, _y, _w - 2*margin, _h - 2*margin);
    imgMode = 0; copylayer = false;
    noBG = (alpha(bg) != 0) ? (false) : (true) ;
  }
  
  public ImageBox(PApplet _parent, PGraphics _layer, float _x, float _y, float _w, float _h, 
                  int _bg, PImage _BG, int _imgMode, int _border, int _bstroke) {
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
    offX = PApplet.parseInt((_BG.width - w)*0.5f); offY = PApplet.parseInt((_BG.height - h)*0.5f);
    if (offX < 0) offX = 0; // deactivate offset if not needed
    if (offY < 0) offY = 0;
    minW = min(PApplet.parseInt(w), _BG.width);
    minH = min(PApplet.parseInt(h), _BG.height);
    switch(imgMode) {
      case 1: //mantain aspect ratio, centered in bounding box, no scaling. Will cut if image is bigger.
        BG = createImage(minW, minH, _BG.format);
        BG.copy(_BG, offX, offY, minW, minH, 0, 0, minW, minH);
        break;
      case 2: //mantain aspect ratio, centered in bounding box, scaled to fit
        float multiplier = min(w/PApplet.parseFloat(_BG.width), h/PApplet.parseFloat(_BG.height));
        minW = PApplet.parseInt(multiplier*_BG.width); minH = PApplet.parseInt(multiplier*_BG.height);
        BG = createImage(minW, minH, _BG.format);
        BG.copy(_BG, 0, 0, _BG.width, _BG.height, 0, 0, minW, minH);
        break;
      case 3: //do not mantain aspect ratio, scale to fit
        BG = createImage(PApplet.parseInt(w), PApplet.parseInt(h), _BG.format);
        BG.copy(_BG, 0, 0, _BG.width, _BG.height, 0, 0, PApplet.parseInt(w), PApplet.parseInt(h));
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
  
  public void setBG(boolean _noBG, int _bg) { // configures solid background
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
    int _w, _h; _w = PApplet.parseInt(w + 2*bsize); _h = PApplet.parseInt(h + 2*bsize);
    BG = createImage(_w, _h, RGB);
    int offXo = PApplet.parseInt(x - _w/2), offYo = PApplet.parseInt(y - _h/2); int offXt = 0, offYt = 0;
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
    _x = (bbb[0] + bbb[2])*0.5f;
    _y = (bbb[1] + bbb[3])*0.5f;
    
    setBoundingBox(_x, _y, _w, _h);
  }
  
  private PImage buildRectFrame(int _bg, int _border, int _bsize, float _shadePercent, float _lightPercent, int _angle, int _balpha, int _bordermode) {
    // build PImage to throw at the other function, so everything is more general
    int _w, _h; _w = PApplet.parseInt(w) + 2*bsize; _h = PApplet.parseInt(h) + 2*bsize;
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
    int _w, _h; _w = PApplet.parseInt(w) + 2*bsize; _h = PApplet.parseInt(h) + 2*bsize;
    PImage img = createImage(_w, _h, ARGB);
    if (_bg.width != _w || _bg.height != _h) {
      if (GLOBAL.VERBOSE) println(this + ".buildRectFrame: images not of the same size; resizing");
      _bg.resize(_w, _h);
    }
    img.loadPixels(); _bg.loadPixels();
    
    int [] endColour = new int[4]; int cur_endColour; 
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
        m = k = 1 - PApplet.parseFloat(py)/PApplet.parseFloat(_bsize); // default: m = k
        switch(bordermode) {
          case 2: m = (k < 0.5f) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5f) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break; // use to avoid corner problem when drawing very thick lines
        }
        cur_endColour = endColour[0]; cur_endColourpct = endColourpct[0];
      } else if ((revpx < _bsize) && (revpx <= py) && (revpy > revpx)) {
        //region = 2;
        m = k = 1 - PApplet.parseFloat(revpx)/PApplet.parseFloat(_bsize);
        switch(bordermode) {
          case 2: m = (k < 0.5f) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5f) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break;
        }
        cur_endColour = endColour[1]; cur_endColourpct = endColourpct[1];
      } else if ((revpy < _bsize) && (revpy <= revpx) && (px > revpy)) {
        //region = 3;
        m = k = 1 - PApplet.parseFloat(revpy)/PApplet.parseFloat(_bsize);
        switch(bordermode) {
          case 2: m = (k < 0.5f) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5f) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
          case 4: m = 1; break;
        }
        cur_endColour = endColour[2]; cur_endColourpct = endColourpct[2];
      } else if ((px < _bsize) && (px < py) && (px <= revpy)) {
        //region = 4;
        m = k = 1 - PApplet.parseFloat(px)/PApplet.parseFloat(_bsize);
        switch(bordermode) {
          case 2: m = (k < 0.5f) ? (1 - 2*k) : (1 - 2*(1 - k)) ; break;
          case 3: m = (k < 0.5f) ? (cos(k*PI)) : (cos((1 - k)*PI)); break;
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
  
  public void setBorder(int _bsize, int _border, float _shadePercent, float _lightPercent, int _angle, int _balpha, int _bordermode) {
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
  
  public void setBorder(int _bstroke, int _border) {
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
  
  public Label(PApplet _parent, PGraphics _layer, PFont _FONT, String _LText, float _x, float _y, float _w, float _h, int _Foreground) {
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
        yalignoffset = -(PApplet.parseFloat(WText.length)*0.5f - 1)*fontSize - 0.5f*(layer.textAscent() - layer.textDescent());
        break;
      case TOP:
        yalignoffset = -h*0.5f +fontSize;
        break;
      case BOTTOM:
        yalignoffset = +h*0.5f - (WText.length - 1)*fontSize - 0.5f*(layer.textAscent() - layer.textDescent());
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
    
    tolerance = 0.05f; // 5% tolerance
    fontSize = (HLocked) ? ( (1 - tolerance)*hbckp / (PApplet.parseFloat(TextBlocks.length)) ) : ( FontSize ) ; // maximum fontsize
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
                    fontSize -= 0.2f;
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
          fontSize -= 0.2f;
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
  int Foreground;
  ImageBox BG; //default background is nothing (transparent; label is an overlayed piece of text)
  
  public HLabel(PApplet _parent, PGraphics _layer, PFont _FONT, String _LText, float _x, float _y, float _w, float _h, int _Foreground, float _fontSize) {
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
  
  public HLabel(PApplet _parent, PGraphics _layer, PFont _FONT, String _LText, float _x, float _y, float _w, float _h, int _Foreground) {
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
        xalignoffset = -w*0.5f;
        layer.textAlign(LEFT, BASELINE);
        break;
      case RIGHT:
        xalignoffset = +w*0.5f;
        layer.textAlign(RIGHT, BASELINE);
        break;
    }
  }
  
  public void calculateYoff() {
    yalignoffset = 0.5f*(layer.textAscent() - layer.textDescent());
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
    tolerance = 0.05f;
    
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
        cur_signal = (lineHeight > (1 - 0.5f*tolerance)*h) ? (-1) : (+1) ;
        if (cur_signal + prev_signal == 0) {
          increment  *= 0.9f;
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
                int state0, int state1, int state2, int state3, int state4, 
                PFont _FONT, String _BText, int t0, int t1, int t2, int t3, int t4) {
    // rectangular button, constant text; if needed, change text by setter
    this(_parent, _layer, _x, _y, max(_w - _bdr, 3), max(_h - _bdr, 3)); bdr = _bdr;
    
    int [] cstate = new int[6], tstate = new int[6]; int [] bstate = new int[6];
    cstate[0] = state0; cstate[1] = state1; cstate[2] = state2; cstate[3] = state3; cstate[4] = state4;
    cstate[5] = color(255 - red(state2), 255 - green(state2), 255 - blue(state2), alpha(state2));
    tstate[0] = t0; tstate[1] = t1; tstate[2] = t2; tstate[3] = t3; tstate[4] = t4;
    tstate[5] = color(255 - red(t2), 255 - green(t2), 255 - blue(t2), alpha(t2));
    bstate[0] = bstate[1] = -1; bstate[2] = bstate[3] = bstate[4] = bstate[5] = 1;
    // we might want null labels or imageboxes (bar of Scroll object or cute little hovering labels)
    for (int i = 0; i < 6; i++) {
      try {
      statesI[i] = new ImageBox(parent, layer, x, y, w, h, cstate[i], 0, 0);
      statesI[i].setBorder(max(bdr, 1), cstate[i], 0.6f, 0.6f, bstate[i], PApplet.parseInt(alpha(cstate[i])), 4);
      statesT[i] = new Label(parent, layer, _FONT, _BText, x, y, w, h, tstate[i]);
      } catch (Exception e) {}
    }
    active[0] = new screenRegion(PApplet.parseInt(w), PApplet.parseInt(h));
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
  
  public void mapActive(int _mc, boolean _isInclusive, int _state) {
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
  
  public void mapActive(PImage _amap, int _mc, boolean _isInclusive, int _index) {
    // _isInclusive == true => mc maps active area
    // _isInclusive == false => mc maps everything that is not active area
    int _w, _h; _w = PApplet.parseInt(w); _h = PApplet.parseInt(h);
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
        active[0].fit(PApplet.parseInt(w), PApplet.parseInt(h), true, 0, 0, 0, 0);
        active[5] = active[4] = active[3] = active[2] = active[1] = active[0];
      } else {
        if (abs(_state) > 6) {
          if (GLOBAL.VERBOSE) println("Button: mapActive: _state out of range");
          return;
        } else if (_state == -6) _state = 6;
        if (_state == 6) {
          for (int i = 0; i < 6; i++) {
            try {
              active[i].fit(PApplet.parseInt(w), PApplet.parseInt(h), true, 0, 0, 0, 0);
            } catch (Exception e) {}
          }
        } else {
          try {
            active[_state].fit(PApplet.parseInt(w), PApplet.parseInt(h), true, 0, 0, 0, 0);
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
            display_state = ((PApplet.parseInt(macBlinker.elapsed()/macBlinkTime) % numBlinks) % 2 == 0) ? (5) : (2) ;
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
  
  int c_0, c_1, c_2, c_3, c_4, c_fg, c_rail; // c_fg is foreground (arrows), constant.
  
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
                float _railpercent, boolean _vertical, int _c_fg, int _c_0, int _c_2, int _c_4,
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
    c_1 = lerpColor(c_0, c_2, 0.5f); c_3 = lerpColor(c_2, c_4, 0.5f);
    c_rail = lerpColor(c_0, color(255, 255, 255, alpha(c_0)), 0.7f);
    
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
      cx[0] = PApplet.parseInt(x); cw[0] = PApplet.parseInt(w) -4;
      for(i = 1; i < 5; i++) {
        cx[i] = cx[i-1]; //pointers trickery
        cw[i] = cw[i-1];
      }
      cw[3] = cw[4] = PApplet.parseInt(w);
    } else {
      fixeddim = h;
      vardim = w;
      fixedpos = y;
      varpos = x;
      cy[0] = PApplet.parseInt(y); ch[0] = PApplet.parseInt(h) -4;
      for(i = 1; i < 5; i++) {
        cy[i] = cy[i-1]; //pointers trickery
        ch[i] = ch[i-1];
      }
      ch[3] = ch[4] = PApplet.parseInt(h);
    }
    
    RVratio = vardim/VA_d;
    bar_var = PApplet.parseInt(min(1, RVratio)*vardim*railpercent) - 4;
    but_var = PApplet.parseInt(vardim*(1 - railpercent)/2);
    
    float tmp; int tmp1, tmp2;
    tmp = 0.5f*(vardim - but_var);
    tmp1 = PApplet.parseInt(varpos - tmp); tmp2 = PApplet.parseInt(varpos + tmp);
    
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
    int i; int[][] co = new int[5][5];
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
    _trick = createGraphics(PApplet.parseInt(diag) +1, PApplet.parseInt(diag) +1, P2D); // little offscreen buffer to rotate image
    _trick.beginDraw();
    _trick.background(0xffFFFFFF);
    _trick.imageMode(CENTER);
    _trick.pushMatrix();
    _trick.translate(_trick.width/2, _trick.height/2);
    _trick.rotate(_angle);
    _trick.image(_loaded, 0, 0);
    _trick.popMatrix();
    
    sina = abs(sin(_angle)); cosa = abs(cos(_angle)); bsize = 2;
    W = PApplet.parseInt(_loaded.height*sina + _loaded.width*cosa);
    H = PApplet.parseInt(_loaded.height*cosa + _loaded.width*sina);
    
    _map.copy(_trick, (_trick.width - W)/2, (_trick.height - H)/2, 
              W, H, bsize, bsize, _bg.width - 2*bsize, _bg.height -2*bsize); 
    
    _trick.endDraw();
    
    returner = createImage(_bg.width, _bg.height, ARGB);
    _map.loadPixels(); returner.loadPixels(); _bg.loadPixels();
      
    for (i = bsize*_bg.width; i < returner.pixels.length - bsize*_bg.width; i++) { // trim out upper and lower borders
      if (i % _bg.width >= bsize && i % _bg.width < _bg.width - bsize) // trim out left and right borders
        returner.pixels[i] = lerpColor(c_fg, _bg.pixels[i], brightness(_map.pixels[i])/255.0f) ;
    }
    returner.updatePixels();
    
    return returner;
  }
  
  public void update() {
    int i;
    float tmp;
    int [] rail_var = new int[4]; // aux helper vars
    
    if (railpercent == 0) { controlsflags[0] = 0; controlsflags[3] = controlsflags[4] = 0; }
    tmp = varpos - vardim*0.5f + but_var + barpos +2;
    barabspos = PApplet.parseInt(tmp + bar_var*0.5f);
    rail_var[0] = barpos; rail_var[1] = PApplet.parseInt(vardim*railpercent - bar_var - barpos) -4;
    rail_var[2] = PApplet.parseInt(tmp - rail_var[0]*0.5f); rail_var[3] = PApplet.parseInt(varpos + vardim*0.5f - but_var - rail_var[1]*0.5f) -2;
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
      if (stillpressed > 0) buttonsAction = PApplet.parseInt((stillpressed + 1)/2)*2;
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
    int px = PApplet.parseInt(mx - x + xoff); int py = PApplet.parseInt(my - y + yoff);
    Point p = new Point(PApplet.parseInt(mx - eoffx), PApplet.parseInt(my - eoffy));
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
    setRBarPos(PApplet.parseInt(barpos + MWsensitivity*deltaMWDisplacement));
  }
  
  public void updateScrollB(float _multiplier) { // buttons
    setRBarPos(PApplet.parseInt(barpos + _multiplier*Bsensitivitystep));
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
    
    setRBarPos(PApplet.parseInt(barpos + _multiplier*vardim*railpercent*RVratio));
    
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
      if (_barpos > tmp) _barpos = PApplet.parseInt(tmp);
    }
    if (barpos != _barpos) {
      barpos = _barpos;
      update();
      updateDisplayFlag = true;
    }
  }
  
  public void setVBarPos(int _vbarpos) { // virtual barpos   
    setRBarPos(PApplet.parseInt(PApplet.parseFloat(_vbarpos)*(RVratio*railpercent)));
  }
  
  public int getRBarPos() { // real barpos
    return barpos;
  }
  
  public int getVBarPos() { // virtual barpos
    return PApplet.parseInt(PApplet.parseFloat(barpos)/(RVratio*railpercent));
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
  int c_fg, c_0, c_2, c_4;
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
                     int _c_fg, int _c_0, int _c_2, int _c_4) {
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
    scrollbars[0] = new Scroll(parent, layer, x - f1*0.5f*hmargin, y + 0.5f*(h - vmargin), 
                              w - f1*hmargin, vmargin, railpercent, false, 
                              c_fg, c_0, c_2, c_4, w + dw);
    railpercent = 1 - 2*hmargin/h;
    scrollbars[1] = new Scroll(parent, layer, x + 0.5f*(w - hmargin), y - f0*0.5f*vmargin +1.5f, // +1.5 e -1 = gambiarra
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
    
    updateVABBox(PApplet.parseInt(x + xoff*0.5f - _hmargin*0.5f), PApplet.parseInt(y + yoff*0.5f - _vmargin*0.5f), 
                 PApplet.parseInt(w - dw - 2*VAbsize - _hmargin), PApplet.parseInt(h - dh - 2*VAbsize - _vmargin));
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
      zoom += deltaMWDisplacement*0.05f;
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
      scrollbars[0].x = x - f1*0.5f*hmargin;
      scrollbars[0].w = vardim0;
      scrollbars[1].y = y - f0*0.5f*vmargin;
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
          
          restore0 = PApplet.parseInt(barpos0);
          newpos = v0;
          scrollbars[0].setVBarPos(PApplet.parseInt(newpos));
        } else {
          scrollbars[0].setRBarPos(restore0);
        }
      }
      if (Uindex != 1) {
        if (Usign < 0) {
          if (pUindex != Uindex) scrollbars[0].setRBarPos(restore0);
          
          restore1 = PApplet.parseInt(barpos1);
          newpos = v1;
          scrollbars[1].setVBarPos(PApplet.parseInt(newpos));
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
      VA.fill(0xffFFFFFF);
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
  public void setBorderSF(int _SFbsize, int bcolor, float spercent, float lpercent, int angle, int balpha, int btype) { 
    margin = _SFbsize;
    borderSF = new ImageBox(this.parent, layer, x, y, w, h, color(0, 0, 1, 0), 0, 0);
    borderSF.setBorder(PApplet.parseInt(margin), bcolor, spercent, lpercent, 0, 255, 2);
  }
  
  public void setBorderVA(int _VAbsize, int bcolor, float spercent, float lpercent, int angle, int balpha, int btype) {
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
  int [] ticks_colours;

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
    multipliers.add(0, 1.0f);

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
    ticks_colours = new int[ticks_resolution];

    reinitTicksScreenBuffer();
    reinitTicksBuffer();
    reinitShapeBuffer();
  }

  public void display() { 
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

  public void updateShapeBuffer() {
    if (mode_ticks % 2 == 0) return;

    int i, j, signal;
    float d, endpos, startpos;
    float curx, cury, tmp;
    float curx2, cury2, tmp2, tmp3, tmp4;
    int curcolour;

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

          curcolour = cur.interpColour( PApplet.parseFloat(i)/(PApplet.parseFloat(shape_resolution)) );

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

          curcolour = cur.interpColour( PApplet.parseFloat(i)/(PApplet.parseFloat(shape_resolution)) );
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

          curcolour = cur.interpColour( PApplet.parseFloat(i)/(PApplet.parseFloat(shape_resolution)) );
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

  public void reinitShapeBuffer() {
    offscreenShapeBuffer = createGraphics(PApplet.parseInt(oldw) + 4, PApplet.parseInt(oldh) + 4, P2D);
  }

  public void resetShapeBuffer() {
    offscreenShapeBuffer.beginDraw();
    offscreenShapeBuffer.background(0, 0);
    offscreenShapeBuffer.endDraw();
  }

  /* handling of ticks */

  public float [] scaleCoords(float _min_coord, float _max_coord, float _cur_coord, 
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

  public float [][] scaleCoords(float [] _origCoords, float [] _origLengths, float _axisAmplitude) {
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

  public int value2template(float _value) {
    // returns the index of point template to use.
    // Pattern of this implementation (can be overriden by subclass):
    // value % 10 == 0 -> template 2
    // value % 5 == 0 -> template 1
    // others: template 0

    // assumes point templates have been already filled by setters
    // but has simple error handling against empty templates:
    // defaults to biggest template index available
    int returner = 0;

    switch(PApplet.parseInt(_value % 10)) {
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

  public SPoint [] id2pos_ell(float _id, SPoint _origin, int _signal, int _templateIndex) { // ellipse (polar)
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

  public SPoint id2pos_bar(float _id, SPoint _dirvec, SPoint _origin, float _step, SPoint _templatept) { // bar (cartesian)
    // this implementation assumes linear positioning. Can be overriden by subclass
    SPoint returner;

    returner = new SPoint(_origin);
    returner.x += (_dirvec.x*_id*_step + _templatept.x); // templatept must be centered
    returner.y += (_dirvec.y*_id*_step + _templatept.y);

    return returner;
  }
  public SPoint value2pos_bar(float value, SPoint _dirvec, SPoint _origin, 
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

  public float id2value(float _id, float _lowerBound, float _upperBound, int _resolution) {
    // this implementation assumes linear scale. Can be overriden by subclass
    return ( _lowerBound + _id*(_upperBound - _lowerBound) / (_resolution -1) );
  }

  public SPoint [] centreTickPoints(SPoint [] _tickpoints) {
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

  public void addAutoTicks() {
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

  private void addTick_lowlvl(boolean _isCustom, float _value, int _colour) { 
    /* add tick at low level, in order. With previously set curtickpoints
     Assumes all ticks were added in order 
     arraylist ticks is private to avoid adding out of order; to force add methods
     to be used. They all eventually get to this method, that is why this is low-level */
    int i, compare, signal;
    tick cur;
    float [] placeholder; 
    // trickery to avoid branching at if (a < b) inside loop to find where to add tick
    int [] curcolour; 
    int flag;
    // trickery to avoid branching at loop to find determine whether to use custom colour or colour array
    if (_isCustom) {
      curcolour = new int[1];
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

  public void addCustomTick(float value, int templateIndex, int _colour) { 
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

  public void clearAllTicks() {
    ticks.clear();
  }

  public void clearAllTemplates() {
    ticktemplates_pts.clear();
    ticktemplates_len.clear();
  }

  public void resetTicksScreenBuffer() {
    ticksMapScreenBuffer.beginDraw();
    ticksMapScreenBuffer.background(0);
    ticksMapScreenBuffer.endDraw();
  }

  public void reinitTicksScreenBuffer() {
    int q = (abs(mode_shape) > 1) ? (round( sqrt(oldw*oldw + oldh*oldh) )) : ( round(3*max(oldw, oldh) - 2*min(t_sw, t_sh)) ) ;
    ticksMapScreenBuffer = createGraphics(q, q, P2D);
  }

  public void resetTicksBuffer() {
    offscreenTicksBuffer.beginDraw();
    offscreenTicksBuffer.background(0, 0);
    offscreenTicksBuffer.endDraw();
  }

  public void reinitTicksBuffer() {
    float maxlength; // of all templates

    maxlength = 0;

    for (int j = 0; j < ticktemplates_len.size(); j++) {
      float [] ticklengths = (float []) ticktemplates_len.get(j);

      for (int i = 0; i < ticklengths.length; i++) {
        maxlength = max(maxlength, ticklengths[i]);
      }
    }

    offset = maxlength + 1;
    offscreenTicksBuffer = createGraphics(PApplet.parseInt(oldw + 2*offset), PApplet.parseInt(oldh + 2*offset), P2D);

    for (int i = 0; i < ticks.size(); i++) {
      tick cur = (tick) ticks.get(i);
      cur.setLayer(offscreenTicksBuffer);
    }
  }

  public void fillTicksBuffer() {
    if (mode_ticks % 2 == 0) return;

    int i;
    tick cur;

    for (i = 0; i < ticks.size(); i++) {
      cur = (tick) ticks.get(i);
      cur.requestUpdate(2);
      cur.update();
    }
  }
  
  public void fillTicksScreenBuffer() {
    if (abs(mode_shape) > 1) {
      fillTicksScreenBuffer_bar();
    } else {
      fillTicksScreenBuffer_ell();
    }
  }

  public void fillTicksScreenBuffer_ell() {
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

  public void fillTicksScreenBuffer_bar() {
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

  public int getNumberOfTicks() {
    return ticks.size();
  }

  public tick getTickReference(int _index) {
    tick returner = null;
    try {
      returner = (tick) ticks.get(_index);
    } 
    catch (Exception e) {
    }
    return returner;
  }

  public void addTickTemplate(SPoint [] _tickpoints, float [] _ticklengths) {
    addTickTemplate(_tickpoints, _ticklengths, ticktemplates_pts.size());
  }

  public void addTickTemplate(SPoint [] _tickpoints, float [] _ticklengths, int _index) {
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
    multipliers.add(index, 1.0f);
  }

  public void setTemplateMultiplier(float _multiplier, int _index) {
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

  public void rotateDeg(float _dtheta) { // rotate in degrees
    rotateRad(radians(_dtheta));
  }
  public void rotateRad(float _dtheta) {
    dtheta = _dtheta;
    sint = sin(dtheta);
    cost = cos(dtheta);
    float [] newDims = rotateBoundingBox(oldw, oldh, dtheta);
    setBoundingBox(x, y, newDims[0], newDims[1]);
  }
  public float getRotationDeg() {
    return degrees(dtheta);
  }
  public float getRotationRad() {
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
    int colour;

    tick(PGraphics _layer, SPoint _p1, SPoint _p2, SPoint _p3, float _w1, float _w2, float _w3, 
    int _colour, float _value, boolean _isCustom) {
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

    tick(PGraphics _layer, SPoint [] _points, float [] _lengths, int _colour, float _value, boolean _isCustom) {
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

    public void findSecondaryPointsBounds() { // find secondary points taking first and last points as definers of primary directing axis
      int i, j, k;
      SPoint lastPoint = primary[primary.length -1];
      SPoint firstPoint = primary[0];
      int tmp = min(lengths.length, primary.length);
      secondary = new SPoint[2*tmp];
      SPoint perpendicularAxis = findPerpendicularAxis(lastPoint, firstPoint);
      for (i = 0; i < tmp; i++) {
        float tmp1 = lengths[i]*0.5f;
        secondary[i] = new SPoint(primary[i].x + perpendicularAxis.x*tmp1, primary[i].y + perpendicularAxis.y*tmp1);
        secondary[i+tmp] = new SPoint(primary[i].x - perpendicularAxis.x*tmp1, primary[i].y - perpendicularAxis.y*tmp1);
      }

      SPoint [] tmpp = new SPoint[2*tmp];

      boolean foundDupe = false;
      k = 0;
      for (i = 0; i < 2*tmp; i++) {
        for (j = i+1; j < 2*tmp && !foundDupe; j++) {
          if (secondary[j].isEqualTo(secondary[i], 0.01f)) {
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
      centrePoint = new SPoint( accX / (PApplet.parseFloat(tmpp2.length)), accY / (PApplet.parseFloat(tmpp2.length)) );
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

    public void allocVectors(int primaryLength) {
      primaryLength = abs(primaryLength);
      primary = new SPoint[primaryLength];
      lengths = new float[primaryLength];
      secondary = new SPoint[2*primaryLength];
    }

    public void update() {
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

    public void redrawTick() { 
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

    public void setPoint(SPoint _p, int _index) {
      try {
        primary[_index] = _p;
      } 
      catch (Exception e) {
      }
    }

    public SPoint getPoint(int _index) {
      SPoint returner = new SPoint(0, 0);
      try {
        returner = new SPoint(primary[_index]);
      } 
      catch (Exception e) {
      }

      return returner;
    }

    public SPoint dirAxis() { //unit direction vector
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

    public void setValue(float _value) {
      value = _value;
    }

    public float getValue() {
      return value;
    }

    public void setLayer(PGraphics _layer) {
      layer = (_layer == null) ? (g) : (_layer) ;
    }

    public void enableSmooth(boolean _enableSmooth, boolean _callUpdateNow) {
      if (enableSmooth != _enableSmooth) {
        enableSmooth = _enableSmooth;
        updateRequested = 2;
        if (_callUpdateNow) update();
      }
    }

    public void setColour(int _colour, boolean _callUpdateNow) {
      if (colour != _colour) {
        colour = _colour;
        updateRequested = 2;
        if (_callUpdateNow) update();
      }
    }

    public void requestUpdate(int _updateRequested) {
      updateRequested = _updateRequested;
    }

    public SPoint getCentre() {
      return zentrum;
    }

    public SPoint getPrimaryPoint(int _index) {
      if (_index < 0 || _index > primary.length) return null;
      else return primary[_index];
    }
  }

  /* handling of colours */

  public void fillTicksColours() {
    int i, j, start;
    float end;

    for (j = 0; j < ticks_colourProfiles.size(); j++) {
      colourStorage cur = getTicksColourProfile(j);
      end = cur.endPercent()*ticks_resolution;
      start = PApplet.parseInt(cur.startPercent()*ticks_resolution);
      for (i = start; i < end; i++) {
        ticks_colours[i] = cur.interpColour( PApplet.parseFloat(i - start) / (end - start) );
      }
    }
  }

  public void addTicksColourProfile(int _einc, int _ausc, float _einp, float _ausp) {
    // here, percent positions apply to number of ticks
    ticks_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    bckp_ticks_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    Collections.sort(ticks_colourProfiles); // will sort profiles; changes indexing order
    Collections.sort(bckp_ticks_colourProfiles);
  }
  public ArrayList getTicksColourProfiles() {
    return ticks_colourProfiles;
  }
  public colourStorage getTicksColourProfile(int i) {
    return ( (colourStorage) ticks_colourProfiles.get(i) );
  }

  public void addShapeColourProfile(int _einc, int _ausc, float _einp, float _ausp) {
    // here, percent positions apply to number of ticks
    shape_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    bckp_shape_colourProfiles.add( new colourStorage(_einc, _ausc, _einp, _ausp) );
    Collections.sort(shape_colourProfiles); // will sort profiles; changes indexing order
    Collections.sort(bckp_shape_colourProfiles);
  }
  public ArrayList getShapeColourProfiles() {
    return shape_colourProfiles;
  }
  public colourStorage getShapeColourProfile(int i) {
    return ( (colourStorage) shape_colourProfiles.get(i) );
  }

  public void setTicksColourProfileLimit(int i, float _newEinPercent) {
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

  public void moveTicksColoursBoundary(int i, float _newEinPercent) {
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

  public void setShapeColourProfileLimit(int i, float _newEinPercent) {
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

  public void moveShapeColoursBoundary(int i, float _newEinPercent) {
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
    int einc, ausc; //start (ein) and end (aus) colours
    float einp, ausp; //start (ein) and end (aus) percentage positions

    colourStorage(int _einc, int _ausc, float _einp, float _ausp) {
      einc = _einc; 
      ausc = _ausc;
      einp = _einp; 
      ausp = _ausp;
    }

    public int interpColour(float percent) {
      return lerpColor(einc, ausc, percent);
    }

    public int startColour() {
      return einc;
    }

    public int endColour() {
      return ausc;
    }

    public float startPercent() {
      return einp;
    }

    public float endPercent() {
      return ausp;
    }

    public int compareTo(Object _other) {
      colourStorage other = (colourStorage) _other;
      float diff = other.einp - this.einp;
      if (diff > 0) return -1;
      else if (diff < 0) return 1;
      else return 0;
    }
  }

  // in case you rather do the drawing yourself
  public PGraphics getTicksBuffer() {
    return offscreenTicksBuffer;
  }
  public PGraphics getShapeBuffer() {
    return offscreenShapeBuffer;
  }

  // I/O
  public void mPressed() {
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

  public void mDragged() {
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
        gaugeRadius = 0.99f*oldw/2;
        
        modulus = sqrt(_rx*_rx + _ry*_ry);
        
        tmp1 = gaugeRadius/modulus*_rx + screenbufoffx + oldw/2;
        tmp2 = gaugeRadius/modulus*_ry + screenbufoffy + oldh/2;
      }
      prv_IOfloatid = cur_IOfloatid;
      cur_IOfloatid = click2id(round(tmp1), round(tmp2));
    }
  }

  public void mReleased() {
    watchDrag = false;
  }

  public float click2id(int _px, int _py) {
    // returns id in linear scale. Can be overriden by subclass,
    // although it would be more clever to override the upperlevel
    // method that receives this id all at once (id2value, id2pos)
    int regionId;
    int regionColour;
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
  
  public float getClickId() {
    return cur_IOfloatid;
  }
  public float getPClickId() {
    return prv_IOfloatid;
  }
  
  public boolean isOnFocus() {
    return (click2id(round(mouseX - x + xoff + screenbufoffx + oldw/2), 
                     round(mouseY - y + yoff + screenbufoffy + oldh/2)) != -1);
  }
  
  public void setModeTicks(int _mode_ticks) {
    mode_ticks = _mode_ticks;
  }
  public void setModeShape(int _mode_shape) {
    mode_shape = _mode_shape;
  }
  public void setMDraggedOffsets(float _trickx, float _tricky) {
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
                    float _butw, float _buth, int state0, int state2,
                    float _minV, float _maxV, int _numTicks) {
    // assumes dimmer is horizontal. Allows direction to be changed later. Take that into account when setting w, h
    super(_parent, _layer);
    setBoundingBox(_barx, _bary, _barw, _barh);
    oldw = _barw; oldh = _barh;
    bardir = new SPoint(1, 0); // default
    maxV = _maxV; minV = _minV; numTicks = _numTicks;
    dtheta = 0; sint = 0; cost = 1; posid = 0.1f;
    watchDrag = false;
    cfactor = 1 + 1.0f/numTicks;
    colouringMode = s_cprofile = t_cprofile = 0;
    
    // -- bar gauge --
    float [] _coords = new float[4];
    _coords[0] = 0; _coords[1] = h*0.1f; _coords[2] = h*0.9f; _coords[3] = h; // defaults
    float [] _wds = new float[4];
    int t = numTicks*10;
    _wds[0] = _wds[3] = w*0.5f/t; _wds[1] = _wds[2] = w/t; // defaults
    displayMode = 2;
    baseGauge = new Gauge(parent, layer, x, y, oldw, oldh, 0.5f, _coords, _wds, 2, displayMode, 2, numTicks, minV, maxV);
    
    SPoint [] newtemplate = new SPoint[4];
    for (int i=0; i < 4; i++) newtemplate[i] = new SPoint(_coords[i], 0); // routines assume coords are on x, y is dummy
    baseGauge.addTickTemplate(newtemplate, _wds);
    baseGauge.setTemplateMultiplier(1.0f, 1);
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
    baseButton = new Button(parent, layer, btnpos.x, btnpos.y, oldw/(numTicks-1), oldh*1.5f, 2,
                            state0, lerpColor(state0, state2, 0.5f), state2, 0, 0,
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
  
  public void rotateDeg(float _dtheta) { // rotate in degrees
    rotateRad(radians(_dtheta));
  }
  public void rotateRad(float _dtheta) {
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
  public float getRotationDeg() {
    return degrees(dtheta);
  }
  public float getRotationRad() {
    return dtheta;
  }
  
  public void setShapeResolution(int _shape_res) {
    if (baseGauge.shape_resolution != _shape_res) {
      baseGauge.shape_resolution = _shape_res;
      baseGauge.updateShapeBuffer();
    }
  }
  public void setTicksResolution(int _ticks_res) {
  }
  
  public Gauge gauge() {
    return baseGauge;
  }
  public Button button() {
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
    if (perpendicularAxis.crossProductWith(testPos) < 0.1f) return 1;
    else if (perpendicularAxis.crossProductWith(testPos) > 0.1f) return -1;
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
        posid = numTicks/2.0f + side*numTicks/2.0f;
      } else {
        posid = baseGauge.getClickId()*cfactor;
      }
      setBaseButtonPos(posid, 0, 0);
      if (colouringMode % 2 != 0) baseGauge.moveShapeColoursBoundary(s_cprofile, posid/numTicks);
      if (colouringMode > 1) baseGauge.moveTicksColoursBoundary(t_cprofile, posid/numTicks);
    }
  }
  
  public void setColourFollow(int _colouringMode, int _s_cprofile, int _t_cprofile) {
    colouringMode = _colouringMode;
    s_cprofile = _s_cprofile;
    t_cprofile = _t_cprofile;
    if (colouringMode % 2 != 0) baseGauge.moveShapeColoursBoundary(s_cprofile, posid/numTicks);
    if (colouringMode > 1) baseGauge.moveTicksColoursBoundary(t_cprofile, posid/numTicks);
  }
}
// for screenRegion






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
        recta = new Rectangle2D.Float(p1 - p3*0.5f, p2 - p4*0.5f, p3, p4);
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
        ellip = new Ellipse2D.Float(p1 - p3*0.5f, p2 - p4*0.5f, p3, p4);
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
        rrect = new RoundRectangle2D.Float(p1 - p3*0.5f, p2 - p4*0.5f, p3, p4, arcw, arch);
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
        arcc = new Arc2D.Float(p1 - p3*0.5f, p2 - p4*0.5f, p3, p4, start, extent, type);
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
  
  public void addImage(PImage img, int mc, boolean _isInclusive) { // define a custom contour
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
          if (alpha(img.pixels[i]) == 0) Map.setPixel(i % w, PApplet.parseInt (i / w), true);
        }
      } else {
        for (i = 0; i < w*h; i++) {
          if (img.pixels[i] == mc) Map.setPixel(i % w, PApplet.parseInt (i / w), true);
        }
      }
    } else {
      if (alpha(mc) == 0) {
        for (i = 0; i < w*h; i++) {
          if (alpha(img.pixels[i]) != 0) Map.setPixel(i % w, PApplet.parseInt (i / w), true);
        }
      } else {
        for (i = 0; i < w*h; i++) {
          if (img.pixels[i] != mc) Map.setPixel(i % w, PApplet.parseInt (i / w), true);
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
      
      sx = (OLD.width > _newW) ? (round(OLD.width*0.5f - _newW + _xoffset1)) : (_xoffset1);
      sy = (OLD.height > _newH) ? (round(OLD.height*0.5f - _newH + _yoffset1)) : (_yoffset1);
      if (sx < 0 || sx > OLD.width) sx = 0;
      if (sy < 0 || sy > OLD.height) sy = 0;
      sw = min(OLD.width, _newW);
      sh = min(OLD.height, _newH);
      
      dx = (OLD.width > _newW) ? (_xoffset2) : (round((_newW - OLD.width)*0.5f + _xoffset2)) ;
      dy = (OLD.height > _newH) ? (_yoffset2) : (round((_newH - OLD.height)*0.5f + _yoffset2)) ;
      if (dx < 0 || dx > _newW) dx = 0;
      if (dy < 0 || dy > _newH) dy = 0;
      
      NEW = createImage(_newW, _newH, RGB);
      for (int i = 0; i < NEW.pixels.length; i++) NEW.pixels[i] = 0xff000000;
      NEW.copy(OLD, sx, sy, sw, sh, dx, dy, sw, sh);
    }
    
    Map = new binaryMap(_newW, _newH);
    addImage(NEW, 0xffFFFFFF, true);
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
        if (this.probe(new Point( i % w, PApplet.parseInt(i / w) ))) returner.pixels[i] = 0xffFFFFFF;
        else returner.pixels[i] = 0xff000000;
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
  private int [] colours;
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
  
  public topoMap(PImage _clonableMap, int _x, int _y, int [] _colours, int _levelCount) {
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
        if (!found) TOPOMAP.pixels[i] = 0xff000000;
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
    colours = new int[1]; colours[0] = 0xff000001;
    DISPLAYMAP = createImage(w, h, RGB);
  }
  
  public void reset() {
    TOPOMAP.loadPixels();
    for (int i = 0; i < TOPOMAP.pixels.length; i++) TOPOMAP.pixels[i] = keepAlpha(0);
    TOPOMAP.updatePixels();
  }
  
  public void setLevelDisplayColours(int [] _colours) {
    int i;
    
    int [] newcolours = new int [levelCount]; 
    // newcolours and not colours directly to avoid circular reference if calling this method from within addLevel
    for (i = 0; i < min(levelCount, _colours.length); i++) {
      newcolours[i] = _colours[i];
    }
    if (levelCount > _colours.length) {
      for (i = _colours.length; i < levelCount; i++) newcolours[i] = 0xffFFFFFF;
      // dummy display colour because colour info is missing
    }
    colours = new int [levelCount];
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
  
  public void buildDisplayMap(int [] _colours) {
    recolour4display(_colours);
  }
  
  public void buildDisplayMap() {
    recolour4display(colours);
  }
  
  private void recolour4display(int [] _colours) {
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
        if (!found) DISPLAYMAP.pixels[i] = 0xff000000;
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
      if (addLevel.isPointInRegion(new Point(i % w, PApplet.parseInt(i / w)) )) {
        if (TOPOMAP.pixels[i] == 0xff000000) TOPOMAP.pixels[i] = levelIDColour;
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
      if (TOPOMAP.pixels[i] == levelIDColour) TOPOMAP.pixels[i] = 0xff000000; 
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
  
  public int keepAlpha(int _color) {
    int result, tmp;
  
    tmp = _color << 8; // trunk to avoid stack overflow or resetting of alpha bits
    // locks either on 0x00000000 (black) or 0xFFFFFFFF (white)
    result = (tmp >> 8) | 0xFF000000;
  
    return result;
  }
  
  public int removeAlpha(int _color) {
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
      int testcolor = TOPOMAP.pixels[p.y*w + p.x];
      returner = (testcolor == 0xff000000) ? ( 0 ) : ( removeAlpha(testcolor) );
    } catch(ArrayIndexOutOfBoundsException e) {
      if (GLOBAL.VERBOSE) println("topoMap: point (" + nfp(p.x, 4, 2) + "; " + nfp(p.y, 4, 2) + ") not in topoMap's boundaries!");
      returner = -1;
    }
    return returner;
  }
  
  public int findDisplayColourRel(Point p) {
    int levelID = findLevelRel(p);
    return findDisplayColourRel(levelID);
  }
  
  public int findDisplayColourRel(int _levelID) {
    int returner;
    try {
      returner = colours[_levelID];
    } catch(Exception e) {
      if (GLOBAL.VERBOSE) println("topoMap: point not in topoMap's boundaries or display colour not defined for level " + _levelID);
      returner = 0xffFFFFFF;
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
class SPoint {
  float x, y;

  SPoint(float _x, float _y) {
    x = _x; 
    y = _y;
  }

  SPoint(SPoint _p) {
    this(_p.x, _p.y);
  }

  public boolean isEqualTo(SPoint _p, float _eps) {
    return ( (abs(_p.x - x) <= _eps && abs(_p.y - y) <= _eps) );
  }

  public float distTo(SPoint _p) {
    return ( distTo(_p.x, _p.y) );
  }

  public float distTo(float _x, float _y) {
    float distx = x - _x;
    float disty = y - _y;
    return abs(sqrt( distx*distx + disty*disty ));
  }

  public float getProjectionOf(SPoint _p) {
    return ( getProjectionOf(_p.x, _p.y) );
  }

  public float getProjectionOf(float _x, float _y) { 
    // returns multiplier that represents orthog projection of (_x, _y) on this vector
    float _dotProduct;
    _dotProduct = dotProductWith(_x, _y);
    if (_dotProduct == 0) return 0;
    return ( _dotProduct / (x*x + y*y) );
  }

  public float dotProductWith(float _x, float _y) {
    return (_x*x + _y*y);
  }

  public float crossProductWith(SPoint _p) {
    return ( crossProductWith(_p.x, _p.y) );
  }

  public float crossProductWith(float _x, float _y) {
    return (x*_y - y*_x);
  }

  public float modulus() {
    return (sqrt(x*x + y*y));
  }
  
  public void normaliseMe() {
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

  public int compareTo(Object _other) {
    clockPoint other = (clockPoint) _other;
    return less(new SPoint(x, y), new SPoint(other.x, other.y), clockCentre, h );
  }
}

public int less(SPoint _a, SPoint _b, SPoint _zentrum, float h) {
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
    if (det < -0.1f) 
      returner = 1;
    else if (det > 0.1f) 
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

public SPoint findPerpendicularAxis(SPoint _start, SPoint _end) { //storing in a SPoint class because we need a group of 2 floats, but technically it is a 2D vector
  SPoint returner;
  SPoint dirVector = new SPoint(_end.x - _start.x, _end.y - _start.y); //storing in a SPoint class, but this is a 2D vector

  if (abs(dirVector.y) < 0.1f) {
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

public float [] rotateBoundingBox(float _w, float _h, float _angle) {
  /* rotates object of initial bounding box {_w, _h} and returns new bounding 
  box that contains rectangle of dimentions {_w, _h} rotated by angle _angle (in radians) */
  float sina = abs(sin(_angle));
  float cosa = abs(cos(_angle));
  
  float [] returner = new float[2];
  returner[0] = (_h*sina + _w*cosa);
  returner[1] = (_h*cosa + _w*sina);
  
  return (returner);
}
    
public void configs(int window) {
    background(240, 240, 240);
    stroke(195, 195, 195);
    strokeWeight(1);
    textFont(fonte6);
    textAlign(CENTER);

    fill(208, 237, 242);
    smooth();
   
    if(window == 0) {
      beginShape();
      vertex(680, 530);
      vertex(695, 545);
      vertex(680, 560);
      vertex(760, 560);
      vertex(775, 545);
      vertex(760, 530);
      vertex(680, 530);
      endShape();
      
      beginShape();
      vertex(676, 530);
      vertex(691, 545);
      vertex(676, 560);
      vertex(600, 560);
      vertex(615, 545);
      vertex(600, 530);
      vertex(676, 530);
      endShape();
    }
    else {
      fill(0);
      image(ret, 755, 10, 24, 24);
      text("Voltar ao in\u00edcio da anima\u00e7\u00e3o", 660, 26);
      
      fill(208, 237, 242);
      if(window == 6)
       fill(14, 3, 98);
      beginShape();
      vertex(7, 36);
      vertex(22, 21);
      vertex(7, 6);
      vertex(100, 6);
      vertex(115, 21);
      vertex(100, 36);
      vertex(7, 36);
      endShape();
      
      fill(0);
      if(window == 6) //instru\u00e7\u00f5es
        fill(255); 
      text("Instru\u00e7\u00f5es", 14, 13, 98, 30);
      
      fill(208, 237, 242);
      if(window == 7)
        fill(14, 3, 98);
      beginShape();
      vertex(104, 36);
      vertex(119, 21);
      vertex(104, 6);
      vertex(190, 6);
      vertex(205, 21);
      vertex(190, 36);
      vertex(104, 36);
      endShape();
      
      fill(0);
      if(window == 7)
        fill(255);
      text("Legendas", 108, 13, 98, 30);
      
      fill(208, 237, 242);
      if(window == 8)
        fill(14, 3, 98);  
      beginShape();
      vertex(194, 36);
      vertex(209, 21);
      vertex(194, 6);
      vertex(290, 6);
      vertex(305, 21);
      vertex(290, 36);
      vertex(194, 36);
      endShape();
      
      fill(0);
      if(window == 8)
        fill(255);
      text("Refer\u00eancias", 202, 13, 98, 30);
      
      fill(208, 237, 242);
      if(window == 9)
        fill(14, 3, 98);
      beginShape();
      vertex(294, 36);
      vertex(309, 21);
      vertex(294, 6);
      vertex(365, 6);
      vertex(380, 21);
      vertex(365, 36);
      vertex(294, 36);
      endShape();
      
      fill(0);
      if(window == 9)
        fill(255);
      text("Cr\u00e9ditos", 290, 13, 98, 30);
      
      fill(208, 237, 242);
      
    }
   
    if(window == 5)  
      fill(14, 3, 98);
    if(window == 0) {   
      beginShape(); //w5
      vertex(590, 45);
      vertex(605, 30);
      vertex(590, 15);
      vertex(778, 15);
      vertex(793, 30);
      vertex(778, 45);
      vertex(590, 45);
      endShape();
    }
    else {
      beginShape(); //w5
      vertex(590, 72);
      vertex(605, 57);
      vertex(590, 42);
      vertex(778, 42);
      vertex(793, 57);
      vertex(778, 72);
      vertex(590, 72);
      endShape();
    }
    
    fill(208, 237, 242);
    
    if(window == 4)  
      fill(14, 3, 98);
    if(window == 0) {
      beginShape(); //w4
      vertex(484, 45);
      vertex(499, 30);
      vertex(484, 15);
      vertex(586, 15);
      vertex(601, 30);
      vertex(586, 45);
      vertex(484, 45);
      endShape();
    }
    else {
      beginShape(); //w4
      vertex(484, 72);
      vertex(499, 57);
      vertex(484, 42);
      vertex(586, 42);
      vertex(601, 57);
      vertex(586, 72);
      vertex(484, 72);
      endShape(); 
    }
    fill(208, 237, 242);
    
    if(window == 3)  
      fill(14, 3, 98);
    if(window == 0) {
      beginShape();
      vertex(314, 45);
      vertex(329, 30);
      vertex(314, 15);
      vertex(480, 15);
      vertex(495, 30);
      vertex(480, 45);
      vertex(314, 45);
      endShape(); 
    }
    else {
      beginShape();
      vertex(314, 72);
      vertex(329, 57);
      vertex(314, 42);
      vertex(480, 42);
      vertex(495, 57);
      vertex(480, 72);
      vertex(314, 72);
      endShape();  
    }
    fill(208, 237, 242);
    
    if(window == 2)  
      fill(14, 3, 98);
    if(window == 0) {
      beginShape();
      vertex(224, 45);
      vertex(239, 30);
      vertex(224, 15);
      vertex(310, 15);
      vertex(325, 30);
      vertex(310, 45);
      vertex(224, 45);
      endShape();
    }
    else {
      beginShape();
      vertex(224, 72);
      vertex(239, 57);
      vertex(224, 42);
      vertex(310, 42);
      vertex(325, 57);
      vertex(310, 72);
      vertex(224, 72);
      endShape(); 
    }
    
    fill(208, 237, 242);
    
    if(window == 1)  
      fill(14, 3, 98);
    if(window == 0) {
      beginShape();
      vertex(7, 45);
      vertex(22, 30);
      vertex(7, 15);
      vertex(220, 15);
      vertex(235, 30);
      vertex(220, 45);
      vertex(7, 45);
      endShape();
    }
    else {
      beginShape();
      vertex(7, 72);
      vertex(22, 57);
      vertex(7, 42);
      vertex(220, 42);
      vertex(235, 57);
      vertex(220, 72);
      vertex(7, 72);
      endShape(); 
    }
    
    fill(0);
    if(window == 0) {
      text("Instru\u00e7\u00f5es", 610, 538, 80, 30);
      text("Cr\u00e9ditos", 688, 538, 80, 30);
    }
    if(window == 1)
      fill(255);
    if(window == 0)
      text("O sil\u00edcio e dopantes tipo p e n", 16, 23, 213, 30);
    else 
      text("O sil\u00edcio e dopantes tipo p e n", 16, 50, 213, 30); //somei 35
    fill(0);
    if(window == 2)
      fill(255);
    if(window == 0)
      text("Jun\u00e7\u00e3o p-n", 219, 23, 115, 30);
    else
      text("Jun\u00e7\u00e3o p-n", 219, 50, 115, 30);
    fill(0);
    if(window == 3)
      fill(255);
    if(window == 0)
      text("Diodo de jun\u00e7\u00e3o bipolar", 322, 23, 170, 25);
    else
      text("Diodo de jun\u00e7\u00e3o bipolar", 322, 50, 170, 25);
    fill(0);
    if(window == 4)
      fill(255);
    if(window == 0)
      text("Jun\u00e7\u00e3o n-p-n", 491, 23, 110, 25);
    else
      text("Jun\u00e7\u00e3o n-p-n", 491, 50, 110, 25);
    fill(0);
    if(window == 5)
      fill(255);
    if(window == 0)
      text("Transistor de jun\u00e7\u00e3o bipolar", 590, 23, 200, 25);
    else
      text("Transistor de jun\u00e7\u00e3o bipolar", 590, 50, 200, 25);
}   
 
public void mouseClicked() {
  float ics, igrec;
  ics = mouseX;
  igrec = mouseY;
  if(window == 3 && ics >= mouse_x + 30 + 271 && ics <= mouse_x + 66 + 271 && igrec >= mouse_y - 390 && igrec <= mouse_y + 36 - 390 && (passe15 == 0 ||  passe15 == 2)) {
    passe15 = 1;
  }
  else if(passe15 == 1)
    passe15 = 2;

  if(window == 1 && vai == -1 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577) {
    k = 0;
    vai = 0;
    ics = 1000;
    igrec = 1000;
    q = 0;
  } 
  else if(window == 1 && vai == 0 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    vai = 1;
    ics = 1000;
    igrec = 1000;
    q = 0;
  } 
  else if(window == 1 && vai == 1 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    q = 0;
    vai = 2;
    ics = 1000;
    igrec = 1000;
  }
  else if(window == 1 && vai == 2 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    q = 0;
    vai = 3;
    ics = 1000;
    igrec = 1000;
  }
  else if(window == 1 && vai == 3 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    q = 0;
    vai = 4;
    ics = 1000;
    igrec = 1000;
  }
  else if(window == 1 && vai == 4 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    q = 0;
    vai = 5;
    ics = 1000;
    igrec = 1000;
  }
  else if(window == 1 && vai == 5 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    q = 0;
    vai = 6;
    ics = 1000;
    igrec = 1000;
  }
  else if(window == 1 && vai == 6 && play3 == true && ics > 303 && ics < 327 && igrec > 553 && igrec < 577 && q == 1) {
    k = 0;
    q = 0;
    vai = 7;
    ics = 1000;
    igrec = 1000; //aqui inserir a op\u00e7\u00e3o de reiniciar
  }
  else if(window == 1 && vai == 7 && play3 == true && ics > 303 && ics < 323 && igrec > 556 && igrec < 576 && q == 1 && sub_aba == 2) {
      ics = 1000;
      igrec = 1000;
      for (i = 0; i < 3; i++) {
        p[i].set_ang(0 + 90*i);
      }
      for (i = 3; i < 7; i++) {
        p[i].set_ang(0 + 90*(i - 3));
      }
      for (i = 7; i < 11; i++) {
        p[i].set_ang(0 + 90*(i - 7));
      }
      for (i = 11; i < 15; i++) {
        p[i].set_ang(0 + 90*(i - 11));
      }
      for (i = 15; i < 19; i++) {
        p[i].set_ang(0 + 90*(i - 15));
      }
      for (i = 20; i < 48; i++) {
        p[i].set_ang(0 + 90*i);
      } 
      q = 0;
      k = 0;
      vai = -1;
      yc13 = 15.5f;
      yc23 = 164.5f;
      xc13 = 24;
      xc23 = 173;
      for (i = 0; i < 3; i++) { //ok
        p[i].set_spx(0);
        p[i].set_spy(0);
        p[i].set_xc(120);
        p[i].set_yc(110);
      }
      for (i = 3; i < 7; i++) {//ok
        p[i].set_xc(xc13 + 20);
        p[i].set_yc(110);
        p[i].set_spx(0.5f);
        p[i].set_spy(0);
      }
      for (i = 7; i < 11; i++) {//ok
        p[i].set_xc(xc23 + 20);
        p[i].set_spx(-0.5f);
        p[i].set_spy(0);
        p[i].set_yc(110);
      }
      for (i = 11; i < 15; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_spy(0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      for (i = 15; i < 19; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_spy(-0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      p[19].set_xc(120);
      p[19].set_yc(110);
      p[19].set_ang(270);
      p[19].set_spx(0);
      p[19].set_spy(0);
      
      for (i = 20; i < 24; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 24; i < 28; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 28; i < 32; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 32; i < 36; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 36; i < 40; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 40; i < 44; i++) {//ok
        p[i].set_yc(110);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 44; i < 48; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 95);
      }
  }
  
}
  public void mousePressed() {
  float ics, igrec;
  ics = mouseX;
  igrec = mouseY;
  if(window != 0 && ics >= 755 && ics <= 779 && igrec >= 10 && igrec <= 34) {
    window = 0;
    ics = 1000;
    igrec = 1000;
    play1 = false;
    play2 = false;
    play3 = false;
    r1 = false;
    r2 = false;
    t6 = 0;
    for(j = 0; j <= 3; j++) {
      pass[j] = 0; 
    }
  }
  if(window == 1) {
   if(sub_aba == 1 && ics >= 720 && ics <= 752 && igrec >= 530 && igrec <= 562) {
     dopante = 'p';
     sub_aba = 2;
     ics = 1000;
     igrec = 1000;
     play2 = false;
     play3 = false;
     for(j = 0; j <= 3; j++) {
       pass[j] = 0; 
     }
      for (i = 0; i <= 4; i++) {
         y1[i] = y0[i];
      }
      for (i = 0; i < 3; i++) {
        p[i].set_ang(0 + 90*i);
      }
      for (i = 3; i < 7; i++) {
        p[i].set_ang(0 + 90*(i - 3));
      }
      for (i = 7; i < 11; i++) {
        p[i].set_ang(0 + 90*(i - 7));
      }
      for (i = 11; i < 15; i++) {
        p[i].set_ang(0 + 90*(i - 11));
      }
      for (i = 15; i < 19; i++) {
        p[i].set_ang(0 + 90*(i - 15));
      }
      for (i = 20; i < 48; i++) {
        p[i].set_ang(0 + 90*i);
      } 
      q = 0;
      k = 0;
      vai = -1;
      yc13 = 15.5f;
      yc23 = 164.5f;
      xc13 = 24;
      xc23 = 173;
      for (i = 0; i < 3; i++) { //ok
        p[i].set_spx(0);
        p[i].set_spy(0);
        p[i].set_xc(120);
        p[i].set_yc(110);
      }
      for (i = 3; i < 7; i++) {//ok
        p[i].set_xc(xc13 + 20);
        p[i].set_yc(110);
        p[i].set_spx(0.5f);
        p[i].set_spy(0);
      }
      for (i = 7; i < 11; i++) {//ok
        p[i].set_xc(xc23 + 20);
        p[i].set_spx(-0.5f);
        p[i].set_spy(0);
        p[i].set_yc(110);
      }
      for (i = 11; i < 15; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_spy(0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      for (i = 15; i < 19; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_spy(-0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      p[19].set_xc(120);
      p[19].set_yc(110);
      p[19].set_ang(270);
      p[19].set_spx(0);
      p[19].set_spy(0);
      
      for (i = 20; i < 24; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 24; i < 28; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 28; i < 32; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 32; i < 36; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 36; i < 40; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 40; i < 44; i++) {//ok
        p[i].set_yc(110);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 44; i < 48; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 95);
      }
 
   }
  
   else if(sub_aba == 2 && ics >= 352 && ics <= 384 && igrec >= 552 && igrec <= 584) {
     sub_aba = 1;
     ics = 1000;
     igrec = 1000;
     play1 = false;
     r1 = false;
   }
   else if(sub_aba == 2 && ics >= 755 && ics <= 787 && igrec >= 525 && igrec <= 557) {
     dopante = 'n';
     sub_aba = 3;
     ics = 1000;
     igrec = 1000;
     play2 = false;
     play3 = false;
     for (i = 0; i <= 4; i++) {
        y1[i] = y0[i];
     }
     r2 = false;
     t6 = 0;
     n[48].set_xc(120);
     n[48].set_yc(110);
   }
   else if(sub_aba == 3 && ics >= 373 && ics <= 405 && igrec >= 535 && igrec <= 567) {
     dopante = 'p';
     sub_aba = 2;
     ics = 1000;
     igrec = 1000;
     play2 = false;
     play3 = false;
     for(j = 0; j <= 3; j++) {
       pass[j] = 0; 
     }
     for (i = 0; i <= 4; i++) {
        y1[i] = y0[i];
     }
      q = 0;
      k = 0;
      vai = -1;
      yc13 = 15.5f;
      yc23 = 164.5f;
      xc13 = 24;
      xc23 = 173;
      for (i = 0; i < 3; i++) {
        p[i].set_ang(0 + 90*i);
      }
      for (i = 3; i < 7; i++) {
        p[i].set_ang(0 + 90*(i - 3));
      }
      for (i = 7; i < 11; i++) {
        p[i].set_ang(0 + 90*(i - 7));
      }
      for (i = 11; i < 15; i++) {
        p[i].set_ang(0 + 90*(i - 11));
      }
      for (i = 15; i < 19; i++) {
        p[i].set_ang(0 + 90*(i - 15));
      }
      for (i = 20; i < 48; i++) {
        p[i].set_ang(0 + 90*i);
      } 
      for (i = 0; i < 3; i++) { //ok
        p[i].set_spx(0);
        p[i].set_spy(0);
        p[i].set_xc(120);
        p[i].set_yc(110);
      }
      for (i = 3; i < 7; i++) {//ok
        p[i].set_xc(xc13 + 20);
        p[i].set_yc(110);
        p[i].set_spx(0.5f);
        p[i].set_spy(0);
      }
      for (i = 7; i < 11; i++) {//ok
        p[i].set_xc(xc23 + 20);
        p[i].set_spx(-0.5f);
        p[i].set_spy(0);
        p[i].set_yc(110);
      }
      for (i = 11; i < 15; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_spy(0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      for (i = 15; i < 19; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_spy(-0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      p[19].set_xc(120);
      p[19].set_yc(110);
      p[19].set_ang(270);
      p[19].set_spx(0);
      p[19].set_spy(0);
      
      for (i = 20; i < 24; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 24; i < 28; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 28; i < 32; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 32; i < 36; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 36; i < 40; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 40; i < 44; i++) {//ok
        p[i].set_yc(110);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 44; i < 48; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 95);
      }
 
   }
   
   if(r1 == false && ics >= 380 && ics <= 404 && igrec >= 460 && igrec <= 484 && sub_aba == 1 && sen > -0.01f && sen < 0.01f) {
     r1 = true;
     sen = 5;
   }
   if(ics >= 275 && ics <= 293 && igrec >= 275 && igrec <= 293 && (sub_aba == 2 || sub_aba == 3) && pass[0] == 1 && pass[1] == 1 && pass[2] == 1 && pass[3] == 1) {
     for(j = 0; j <= 3; j++) {
       pass[j] = 0; 
     }
     for (i = 0; i <= 4; i++) {
       y1[i] = y0[i];
     }
   }
   if(sub_aba == 3 && play3 == true && r2 == true && ics > 303 && ics < 323 && igrec > 556 && igrec < 576) {
     r2 = false;
     t6 = 0;
     n[48].set_xc(120);
     n[48].set_yc(110);
   }
   
  } 
  if(window == 5) {
    pg1.rect(410, 30, 60, 40);
    if(op2 == 'A' && ics >= 305 && ics <= 337 && igrec >= 548 && igrec <= 580) {
      op2 = 'B';
      ics = 1000;
      igrec = 1000;
    }
    else if(op2 == 'B' && ics >= 110 && ics <= 142 && igrec >= 548 && igrec <= 580) {
      op2 = 'A';
      ics = 1000;
      igrec = 1000;
    }
    if(op2 == 'B' && ((ics >= 90 && ics <= 120 && igrec >= 440 && igrec <= 460) || (ics >= 90 && ics <= 130 && igrec >= 437 && igrec <= 443))) {
      if(chave == false) {
        chave = true;
      }
      else
        chave = false;
      ics = 1000;
      igrec = 1000;
    }
    else if(get(PApplet.parseInt(ics), PApplet.parseInt(igrec)) == color(66, 70, 72) && op2 == 'A') {
      if(igrec <= 435 && vb < 6) {
        vb = vb + 0.5f;
      }
      else if(vb > 0 && igrec >= 445)
        vb = vb - 0.5f;
      ics = 1000;
      igrec = 1000;
    }
  }
  if((window == 1 && ics >= 20 && ics <= 420 && igrec >= 160 && igrec <= 500 && play1 == false && sub_aba == 1) || (r1 == true && window == 1 && sub_aba == 1)){
    r1 = false;
    play1 = true;
    ics = -1000;
    igrec = -1000;
    t1 = t2 = 0;
    xc1 = -50;
    xc2 = 400;
    yc1 = 300;
    yc2 = -110;
    xc12 = -80.5f;
    xc22 = 429.5f;
    for (i = 4; i < 8; i++) {
      el[i].set_xc(xc1 + 25);
      el[i].set_ang(0 + 90*(i - 4)); 
    }
    for (i = 8; i < 12; i++) {
      el[i].set_xc(xc2 + 25);
      el[i].set_ang(0 + 90*(i - 8)); 
    }
    for (i = 12; i < 16; i++) {
      el[i].set_yc(yc1 + 25);
      el[i].set_ang(0 + 90*(i - 12));  
    }
    for (i = 16; i < 20; i++) {
      el[i].set_yc(yc1 + 25);
      el[i].set_ang(0 + 90*(i - 16)); 
    }
    for (i = 20; i < 24; i++) {
      el[i].set_yc(yc2 + 25);
      el[i].set_ang(0 + 90*(i - 20));
    }
    for (i = 24; i < 28; i++) {
      el[i].set_yc(yc2 + 25);
      el[i].set_ang(0 + 90*(i - 24));  
    }
    for (i = 28; i < 32; i++) {
      el[i].set_xc(xc12 + 25);
      el[i].set_ang(0 + 90*(i - 28));
    }
    for (i = 32; i < 36; i++) {
      el[i].set_xc(xc22 + 25);
      el[i].set_ang(0 + 90*(i - 32));
    }
    sen = 5;
  }
  else if(window == 1 && ics >= 20 && ics <= 330 && igrec >= 110 && igrec <= 300 && (sub_aba == 2 || sub_aba == 3) && play2 == false){
    play2 = true;
    ics = -1000;
    igrec = -1000;
    for (i = 0; i <= 4; i++) {
      y1[i] = y0[i];
    }
    for(j = 0; j <= 3; j++) {
       pass[j] = 0; 
     }
  }
  else if(window == 1 && ics >= 20 && ics <= 330 && igrec >= 333 && igrec <= 583 && play3 == false && sub_aba == 2){
    play3 = true;
    ics = -1000;
    igrec = -1000;
    q = 0;
    k = 0;
    vai = -1;
    yc13 = 15.5f;
    yc23 = 164.5f;
    xc13 = 24;
    xc23 = 173;
    for (i = 0; i < 3; i++) {
      p[i].set_ang(0 + 90*i);
    }
    for (i = 3; i < 7; i++) {
      p[i].set_ang(0 + 90*(i - 3));
    }
    for (i = 7; i < 11; i++) {
      p[i].set_ang(0 + 90*(i - 7));
    }
    for (i = 11; i < 15; i++) {
      p[i].set_ang(0 + 90*(i - 11));
    }
    for (i = 15; i < 19; i++) {
      p[i].set_ang(0 + 90*(i - 15));
    }
    for (i = 20; i < 48; i++) {
      p[i].set_ang(0 + 90*i);
    } 
    for (i = 0; i < 3; i++) { //ok
      p[i].set_spx(0);
      p[i].set_spy(0);
      p[i].set_xc(120);
      p[i].set_yc(110);
    }
    for (i = 3; i < 7; i++) {//ok
      p[i].set_xc(xc13 + 20);
      p[i].set_yc(110);
      p[i].set_spx(0.5f);
      p[i].set_spy(0);
    }
    for (i = 7; i < 11; i++) {//ok
      p[i].set_xc(xc23 + 20);
      p[i].set_spx(-0.5f);
      p[i].set_spy(0);
      p[i].set_yc(110);
    }
    for (i = 11; i < 15; i++) {//ok
      p[i].set_yc(yc13 + 20);
      p[i].set_spy(0.5f);
      p[i].set_spx(0);
      p[i].set_xc(120);
    }
    for (i = 15; i < 19; i++) {//ok
      p[i].set_yc(yc23 + 20);
      p[i].set_spy(-0.5f);
      p[i].set_spx(0);
      p[i].set_xc(120);
    }
    p[19].set_xc(120);
    p[19].set_yc(110);
    p[19].set_ang(270);
    p[19].set_spx(0);
    p[19].set_spy(0);
    
    for (i = 20; i < 24; i++) {//ok
      p[i].set_yc(yc13 + 20);
      p[i].set_xc(xc13 + 20);
    }
    for (i = 24; i < 28; i++) {//ok
      p[i].set_yc(yc13 + 20);
      p[i].set_xc(xc23 + 20);
    }
    for (i = 28; i < 32; i++) {//ok
      p[i].set_yc(yc23 + 20);
      p[i].set_xc(xc13 + 20);
    }
    for (i = 32; i < 36; i++) {//ok
      p[i].set_yc(yc23 + 20);
      p[i].set_xc(xc23 + 20);
    }
    for (i = 36; i < 40; i++) {//ok
      p[i].set_yc(yc13 + 20);
      p[i].set_xc(xc23 + 95);
    }
    for (i = 40; i < 44; i++) {//ok
      p[i].set_yc(110);
      p[i].set_xc(xc23 + 95);
    }
    for (i = 44; i < 48; i++) {//ok
      p[i].set_yc(yc23 + 20);
      p[i].set_xc(xc23 + 95);
    }
 
  }
  else if(window == 1 && ics >= 20 && ics <= 330 && igrec >= 333 && igrec <= 583 && play3 == false && sub_aba == 3){
    play3 = true;
    ics = -1000;
    igrec = -1000;
    r2 = false;
    t6 = 0;
    n[48].set_xc(120);
    n[48].set_yc(110);
  }
  else if(window == 2 && ics >= 260 && igrec >= 510 && ics <= 292 && igrec <= 542) {
    play4 = true;
    ics = -1000;
    igrec = -1000;
    
  }
 
  else if(window == 3 && ics >= 643 && ics <= 662 && igrec >= 136 && igrec <= 160) {
    ics = 1000;
    igrec = 1000;
    if(op == 1)
      op = 2;
    else
      op = 1;
  }
  else if(window == 4 && play5 == true && be == false && ics >= 25 + 32 && ics <= 25 + 48 && igrec >= 105 + 331 && igrec <= 105 + 339)
    be = true;
  else if(window == 4 && ce == false && play5 == true && ics >= 25 + 242 && ics <= 25 + 258 && igrec >= 105 + 141 && igrec <= 105 + 149 && be == true && y_sl < 290)
    ce = true;
  if(window == 4 && play5 == false && ics >= 250 && ics <= 282 && igrec >= 526 && igrec <= 558) {
    play5 = true;
    ics = 1000;
    igrec = 1000;
  }
  if((igrec >= 15 && igrec <= 45 && window == 0) || (igrec >= 42 && igrec <= 72 && window != 0)) {
    if(ics >= 24 && ics <= 220 && window != 1) {
      r2 = false;
      t6 = 0;
      n[48].set_xc(120);
      n[48].set_yc(110);
      play1 = false;
      play2 = false;
      play3 = false;
      sub_aba = 1;
      r1 = false;
      window = 1;
      t1 = t2 = 0;
      xc1 = -50;
      xc2 = 400;
      ics = -1000;
      igrec = -1000;
      yc1 = 300;
      yc2 = -110;
      xc12 = -80.5f;
      xc22 = 429.5f;
      for (i = 4; i < 8; i++) {
        el[i].set_xc(xc1 + 25);
        el[i].set_ang(0 + 90*(i - 4)); 
      }
      for (i = 8; i < 12; i++) {
        el[i].set_xc(xc2 + 25);
        el[i].set_ang(0 + 90*(i - 8)); 
      }
      for (i = 12; i < 16; i++) {
        el[i].set_yc(yc1 + 25);
        el[i].set_ang(0 + 90*(i - 12));  
      }
      for (i = 16; i < 20; i++) {
        el[i].set_yc(yc1 + 25);
        el[i].set_ang(0 + 90*(i - 16)); 
      }
      for (i = 20; i < 24; i++) {
        el[i].set_yc(yc2 + 25);
        el[i].set_ang(0 + 90*(i - 20));
      }
      for (i = 24; i < 28; i++) {
        el[i].set_yc(yc2 + 25);
        el[i].set_ang(0 + 90*(i - 24));  
      }
      for (i = 28; i < 32; i++) {
        el[i].set_xc(xc12 + 25);
        el[i].set_ang(0 + 90*(i - 28));
      }
      for (i = 32; i < 36; i++) {
        el[i].set_xc(xc22 + 25);
        el[i].set_ang(0 + 90*(i - 32));
      }
      for (i = 0; i <= 4; i++) {
        y1[i] = y0[i];
      }
      //come\u00e7a aqui
      for (i = 0; i < 3; i++) {
        p[i].set_ang(0 + 90*i);
      }
      for (i = 3; i < 7; i++) {
        p[i].set_ang(0 + 90*(i - 3));
      }
      for (i = 7; i < 11; i++) {
        p[i].set_ang(0 + 90*(i - 7));
      }
      for (i = 11; i < 15; i++) {
        p[i].set_ang(0 + 90*(i - 11));
      }
      for (i = 15; i < 19; i++) {
        p[i].set_ang(0 + 90*(i - 15));
      }
      for (i = 20; i < 48; i++) {
        p[i].set_ang(0 + 90*i);
      } 
      q = 0;
      k = 0;
      vai = -1;
      yc13 = 15.5f;
      yc23 = 164.5f;
      xc13 = 24;
      xc23 = 173;
      for (i = 0; i < 3; i++) { //ok
        p[i].set_spx(0);
        p[i].set_spy(0);
        p[i].set_xc(120);
        p[i].set_yc(110);
      }
      for (i = 3; i < 7; i++) {//ok
        p[i].set_xc(xc13 + 20);
        p[i].set_yc(110);
        p[i].set_spx(0.5f);
        p[i].set_spy(0);
      }
      for (i = 7; i < 11; i++) {//ok
        p[i].set_xc(xc23 + 20);
        p[i].set_spx(-0.5f);
        p[i].set_spy(0);
        p[i].set_yc(110);
      }
      for (i = 11; i < 15; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_spy(0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      for (i = 15; i < 19; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_spy(-0.5f);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      p[19].set_xc(120);
      p[19].set_yc(110);
      p[19].set_ang(270);
      p[19].set_spx(0);
      p[19].set_spy(0);
      
      for (i = 20; i < 24; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 24; i < 28; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 28; i < 32; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc13 + 20);
      }
      for (i = 32; i < 36; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 20);
      }
      for (i = 36; i < 40; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 40; i < 44; i++) {//ok
        p[i].set_yc(110);
        p[i].set_xc(xc23 + 95);
      }
      for (i = 44; i < 48; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_xc(xc23 + 95);
      }
    }
  }
  if((igrec >= 15 && igrec <= 45 && window == 0) || (igrec >= 42 && igrec <= 72 && window != 0)) {
    if(ics >= 239 && ics <= 310 && window != 2) {
      window = 2;
      ics = -1000;
      igrec = -1000;
      //inserir aqui
      cont2 = 0;
      cont3 = 0;
      for(i = 0; i < 10; i++)
        verif[i] = 0;
      passe5 = 0;
      pol = 1;
      passe8 = 0;
      passe16 = 0;
      passe_n6 = 0;
      neut6 = -1;
      x_c1 = 57;
      x_c2 = -27;
      passe6 = 0;
      passe7 = 0;
      passe_n5 = 0;
      passe17 = 0;
      play4 = false;
      passe4 = 0;
      on = false;
      passe2 = 0;
      neut5 = -1;
      cont = 0;
      for(i = 0; i < 3; i++) {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*i);
        port[i].set_yc(random(35, 75));
        port[i].set_spx(random(-0.4f, 0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      for(i = 10; i < 13; i++) {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(35, 75));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      for(i = 3; i < 10; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
      for(i = 13; i < 20; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
      for(i = 0; i < 2; i++) {
        pp[i].change_pg(pg5);
        pp[i].set_xc(165);
        pp[i].set_yc(323);
        pp[i].set_spx(-0.4f);
        pp[i].set_spy(0);
      }
      for(i = 2; i < 4; i++) {
        pp[i].change_pg(pg5);
        pp[i].set_xc(233);
        pp[i].set_yc(323);
        pp[i].set_spx(0.4f);
        pp[i].set_spy(0);
      }
      pp[4].set_xc(233);
      pp[4].set_yc(305);
      pp[4].set_spx(0.4f);
      pp[4].set_spy(0);
      pp[4].change_pg(pg5);
      
      pp[5].set_xc(165);
      pp[5].set_yc(305);
      pp[5].set_spx(-0.4f);
      pp[5].set_spy(0);
      pp[5].change_pg(pg5);

    }
  }
  if((igrec >= 15 && igrec <= 45 && window == 0) || (igrec >= 42 && igrec <= 72 && window != 0)) {
    if(ics >= 329 && ics <= 480 && window != 3) {
      window = 3;
      ics = -1000;
      igrec = -1000;
      passe15 = 0;
      amplitude2 = amplitude;
    }
    else if(ics >= 499 && ics <= 586 && window != 4) {
      window = 4;
      ics = -1000;
      igrec = -1000;
     
      t7 = 0;
      play5 = false;
      passe10 = 0;
      passe11 = 0;
      passe12 = 0;
      passe14 = 0;
      passe9 = 0;
      t4 = 0;
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
      be = false;
      ce = false;
      y_sl = 355;
      y_sl2 = 165;
      neut = -1;
      neut2 = -1;
      for(i = 0; i < 20; i++) {
        //n1[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg11);
        n1[i].set_xc(random(11, 109));
        n1[i].set_yc(11 + 4.8f*i);
        n1[i].set_spx(random(-0.4f, 0.4f));
        n1[i].set_spy(random(-0.4f, 0.4f));
        n1[i].change_pg(pg11);
      }
      for(i = 0; i < 20; i++) {
        //n2[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg9);
        n2[i].set_xc(random(11, 109));
        n2[i].set_yc(11 + 4.8f*i);
        n2[i].set_spx(random(-0.4f, 0.4f));
        n2[i].set_spy(random(-0.4f, 0.4f));
        n2[i].change_pg(pg9);
      }
      for(i = 0; i < 3; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 270, 0, 0, -0.4, 0, 0, pg8); //lacunas
        b[i].set_xc(32);
        b[i].set_yc(270);
        b[i].set_spx(0);
        b[i].set_spy(-0.4f);
        b[i].change_pg(pg8);
      }
      for(i = 3; i < 6; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 376, 0, 0, 0.6, 0, 0, pg8); //el\u00e9trons
        b[i].set_xc(32);
        b[i].set_yc(376);
        b[i].set_spx(0);
        b[i].set_spy(0.6f);
        b[i].change_pg(pg8);
      }
      for(i = 0; i < 6; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 45 + 200*i, 0, 0, -0.4, 0, 0, pg8); //lacunas
        v2[i].set_xc(242);
        v2[i].set_yc(45 + 200*i);
        v2[i].set_spx(0);
        v2[i].set_spy(-0.4f);
        v2[i].change_pg(pg8);
      }
       for(i = 6; i < 12; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 185 - 200*(i - 6), 0, 0, 0.4, 0, 0, pg8); //el\u00e9trons
        v2[i].set_xc(242);
        v2[i].set_yc(185 - 200*(i - 6));
        v2[i].set_spx(0);
        v2[i].set_spy(0.4f);
        v2[i].change_pg(pg8);
      }
    }
    else if(ics >= 605 && ics <= 781 && window != 5) {
      window = 5;
      ics = -1000;
      igrec = -1000;
    }
  }
  if(passe4 == 1 && ((on == false && ics >= 405 && ics <= 423 && igrec >= 484 && igrec <= 502) || (on == true && ics >= 401 && ics <= 425 && igrec >= 539 && igrec <= 563))) {
    ics = -1000;
    igrec = -1000;
    //inserir aqui
    cont2 = 0;
    cont3 = 0;
    for(i = 0; i < 10; i++)
      verif[i] = 0;
    passe5 = 0;
    pol = 1;
    passe8 = 0;
    passe16 = 0;
    passe_n6 = 0;
    neut6 = -1;
    x_c1 = 57;
    x_c2 = -27;
    passe6 = 0;
    passe7 = 0;
    passe_n5 = 0;
    passe17 = 0;
    passe4 = 0;
    on = false;
    passe2 = 0;
    neut5 = -1;
    cont = 0;
    for(i = 0; i < 3; i++) {
      port[i].change_pg(pg6);
      port[i].set_xc(144.2f - 13.8f*i);
      port[i].set_yc(random(35, 75));
      port[i].set_spx(random(-0.4f, 0.4f));
      port[i].set_spy(random(-0.4f, 0.4f));
    }
    for(i = 10; i < 13; i++) {
      port[i].change_pg(pg7);
      port[i].set_xc(20 + 13.8f*(i-10));
      port[i].set_yc(random(35, 75));
      port[i].set_spx(random(0.4f, 0.6f));
      port[i].set_spy(random(-0.4f, 0.4f));
    }
    for(i = 3; i < 10; i++) {
      if(i > 6) {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      else {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(-0.6f, -0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
    }
    for(i = 13; i < 20; i++) {
      if(i > 16) {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(-0.6f, -0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      else {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
    }
    for(i = 0; i < 2; i++) {
      pp[i].change_pg(pg5);
      pp[i].set_xc(165);
      pp[i].set_yc(323);
      pp[i].set_spx(-0.4f);
      pp[i].set_spy(0);
    }
    for(i = 2; i < 4; i++) {
      pp[i].change_pg(pg5);
      pp[i].set_xc(233);
      pp[i].set_yc(323);
      pp[i].set_spx(0.4f);
      pp[i].set_spy(0);
    }
    pp[4].set_xc(233);
    pp[4].set_yc(305);
    pp[4].set_spx(0.4f);
    pp[4].set_spy(0);
    pp[4].change_pg(pg5);
    
    pp[5].set_xc(165);
    pp[5].set_yc(305);
    pp[5].set_spx(-0.4f);
    pp[5].set_spy(0);
    pp[5].change_pg(pg5);
  }
  if(passe4 == 1 && (on == false || (on == true && pol == 0))&& ics >= 401 && ics <= 425 && igrec >= 512 && igrec <= 536) {
     on = true;
     pol = 1;
     cont2 = 3;
     cont3 = 3;
     ics = 1000;
     igrec = 1000;
     //reiniciando el\u00e9trons e lacunas
     if(neut6 >= 6) {
       for(i = 3; i < 6; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
       }
     }
     else {
       for(i = 3; i < 7; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
       }
     }
    for (i = 7; i < 10 && i != neut6; i++) {
      port[i].set_xc(720);
      port[i].set_yc(800);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    for (i = 13; i < 16 && i != neut5; i++) {
      port[i].set_xc(900);
      port[i].set_yc(1200);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    if(neut5 < 17 && neut5 != -1) {
      for(i = 17; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
    }
    else {
      for(i = 16; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
    }
     if(neut5 != -1) {
       port[neut5].set_xc(pp[1].get_xc());
       port[neut5].set_yc(pp[1].get_yc());
       port[neut5].change_pg(pg6);
     }
     if(neut6 != -1) {
       port[neut6].set_xc(pp[3].get_xc());
       port[neut6].set_yc(pp[3].get_yc());
       port[neut6].change_pg(pg7);
     }
     neut6 = -1;
     neut5 = -1;
     passe_n5 = 0;
     passe_n6 = 0;
     pp[1].set_xc(165);
     pp[1].set_yc(323);;
     pp[1].set_spx(-0.4f);
     pp[1].set_spy(0);
     pp[1].change_pg(pg5);
     pp[3].set_xc(233);
     pp[3].set_yc(323);
     pp[3].set_spx(0.4f);
     pp[3].set_spy(0);
     pp[3].change_pg(pg5);
     passe5 = 0;
     passe6 = 0;
     passe16 = 0;
     passe17 = 0;
     pp[4].set_xc(233);
     pp[4].set_yc(305);
     pp[4].set_spx(0.4f);
     pp[4].set_spy(0);
     pp[4].change_pg(pg5);
    
     pp[5].set_xc(165);
     pp[5].set_yc(305);
     pp[5].set_spx(-0.4f);
     pp[5].set_spy(0);
     pp[5].change_pg(pg5);
  }

  else if(passe4 == 1 && ics >= 401 && ics <= 425 && ((igrec >= 539 && igrec <= 563 && on == false) || (on == true && pol == 1 && igrec >= 512 && igrec <= 536))) {
    on = true;
    pol = 0;
    passe5 = 0;
    passe6 = 0;
    for(i = 3; i < 7; i++) {
      if(i > 6) {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      else {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(-0.6f, -0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
    }
    for (i = 7; i < 10; i++) {
      port[i].set_xc(720);
      port[i].set_yc(800);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    for(i = 16; i < 20 && i != neut5; i++) {
      if(i > 16) {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(-0.6f, -0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      else {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
    }
    for (i = 13; i < 16 && i != neut5; i++) {
      port[i].set_xc(900);
      port[i].set_yc(1200);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }

    pp[0].change_pg(pg5);
    pp[0].set_xc(165);
    pp[0].set_yc(323);
    pp[0].set_spx(-0.4f);
    pp[0].set_spy(0);
    
    pp[2].change_pg(pg5);
    pp[2].set_xc(233);
    pp[2].set_yc(323);
    pp[2].set_spx(0.4f);
    pp[2].set_spy(0);
    cont2 = 3;
    cont3 = 3;
    
  }
  if(window == 2 && play4 == true && passe4 == 1 && on == true && pol == 1 && ics >= 405 && ics <= 423 && igrec >= 484 && igrec <= 502) {
     on = true;
     pol = 1;
     cont2 = 3;
     cont3 = 3;
     ics = 1000;
     igrec = 1000;
     //reiniciando el\u00e9trons e lacunas
     if(neut6 >= 6) {
       for(i = 3; i < 6; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
       }
     }
     else {
       for(i = 3; i < 7; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
       }
     }
    for (i = 7; i < 10 && i != neut6; i++) {
      port[i].set_xc(720);
      port[i].set_yc(800);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    for (i = 13; i < 16 && i != neut5; i++) {
      port[i].set_xc(900);
      port[i].set_yc(1200);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    if(neut5 < 17 && neut5 != -1) {
      for(i = 17; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
    }
    else {
      for(i = 16; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
    }
     if(neut5 != -1) {
       port[neut5].set_xc(pp[1].get_xc());
       port[neut5].set_yc(pp[1].get_yc());
       port[neut5].change_pg(pg6);
     }
     if(neut6 != -1) {
       port[neut6].set_xc(pp[3].get_xc());
       port[neut6].set_yc(pp[3].get_yc());
       port[neut6].change_pg(pg7);
     }
     neut6 = -1;
     neut5 = -1;
     passe_n5 = 0;
     passe_n6 = 0;
     pp[1].set_xc(165);
     pp[1].set_yc(323);;
     pp[1].set_spx(-0.4f);
     pp[1].set_spy(0);
     pp[1].change_pg(pg5);
     pp[3].set_xc(233);
     pp[3].set_yc(323);
     pp[3].set_spx(0.4f);
     pp[3].set_spy(0);
     pp[3].change_pg(pg5);
     passe5 = 0;
     passe6 = 0;
     passe16 = 0;
     passe17 = 0;
     pp[4].set_xc(233);
     pp[4].set_yc(305);
     pp[4].set_spx(0.4f);
     pp[4].set_spy(0);
     pp[4].change_pg(pg5);
    
     pp[5].set_xc(165);
     pp[5].set_yc(305);
     pp[5].set_spx(-0.4f);
     pp[5].set_spy(0);
     pp[5].change_pg(pg5);
  }
  if(window == 2 && play4 == true && passe4 == 1 && on == true && pol == 0 && ics >= 405 && ics <= 423 && igrec >= 484 && igrec <= 502) {
    on = true;
    pol = 0;
    passe5 = 0;
    passe6 = 0;
    for(i = 3; i < 7; i++) {
      if(i > 6) {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      else {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2f - 13.8f*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(-0.6f, -0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
    }
    for (i = 7; i < 10; i++) {
      port[i].set_xc(720);
      port[i].set_yc(800);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    for(i = 16; i < 20 && i != neut5; i++) {
      if(i > 16) {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(-0.6f, -0.4f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
      else {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8f*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(0.4f, 0.6f));
        port[i].set_spy(random(-0.4f, 0.4f));
      }
    }
    for (i = 13; i < 16 && i != neut5; i++) {
      port[i].set_xc(900);
      port[i].set_yc(1200);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }

    pp[0].change_pg(pg5);
    pp[0].set_xc(165);
    pp[0].set_yc(323);
    pp[0].set_spx(-0.4f);
    pp[0].set_spy(0);
    
    pp[2].change_pg(pg5);
    pp[2].set_xc(233);
    pp[2].set_yc(323);
    pp[2].set_spx(0.4f);
    pp[2].set_spy(0);
    cont2 = 3;
    cont3 = 3;
    
  }
  if(window == 2 && play4 == true && passe4 == 1 && on == true && pol == 1 && ics >= 405 && ics <= 423 && igrec >= 484 && igrec <= 502) {
     on = true;
     pol = 1;
     cont2 = 3;
     cont3 = 3;
     ics = 1000;
     igrec = 1000;
     //reiniciando el\u00e9trons e lacunas
     if(neut6 >= 6) {
       for(i = 3; i < 6; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
       }
     }
     else {
       for(i = 3; i < 7; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2f - 13.8f*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
       }
     }
    for (i = 7; i < 10 && i != neut6; i++) {
      port[i].set_xc(720);
      port[i].set_yc(800);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    for (i = 13; i < 16 && i != neut5; i++) {
      port[i].set_xc(900);
      port[i].set_yc(1200);
      port[i].set_spx(0);
      port[i].set_spy(0);
    }
    if(neut5 < 17 && neut5 != -1) {
      for(i = 17; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
    }
    else {
      for(i = 16; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6f, -0.4f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8f*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4f, 0.6f));
          port[i].set_spy(random(-0.4f, 0.4f));
        }
      }
    }
     if(neut5 != -1) {
       port[neut5].set_xc(pp[1].get_xc());
       port[neut5].set_yc(pp[1].get_yc());
       port[neut5].change_pg(pg6);
     }
     if(neut6 != -1) {
       port[neut6].set_xc(pp[3].get_xc());
       port[neut6].set_yc(pp[3].get_yc());
       port[neut6].change_pg(pg7);
     }
     neut6 = -1;
     neut5 = -1;
     passe_n5 = 0;
     passe_n6 = 0;
     pp[1].set_xc(165);
     pp[1].set_yc(323);;
     pp[1].set_spx(-0.4f);
     pp[1].set_spy(0);
     pp[1].change_pg(pg5);
     pp[3].set_xc(233);
     pp[3].set_yc(323);
     pp[3].set_spx(0.4f);
     pp[3].set_spy(0);
     pp[3].change_pg(pg5);
     passe5 = 0;
     passe6 = 0;
     passe16 = 0;
     passe17 = 0;
     pp[4].set_xc(233);
     pp[4].set_yc(305);
     pp[4].set_spx(0.4f);
     pp[4].set_spy(0);
     pp[4].change_pg(pg5);
    
     pp[5].set_xc(165);
     pp[5].set_yc(305);
     pp[5].set_spx(-0.4f);
     pp[5].set_spy(0);
     pp[5].change_pg(pg5);
  }
  if(window == 4 && play5 == true && t7 != 0 && t - t7 >= 60000 && ics >= 273 && ics <= 297 && igrec >= 532 && igrec <= 556) {
      ics = -1000;
      igrec = -1000;
     
      t7 = 0;
      passe10 = 0;
      passe11 = 0;
      passe12 = 0;
      passe14 = 0;
      passe9 = 0;
      t4 = 0;
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
      be = false;
      ce = false;
      y_sl = 355;
      y_sl2 = 165;
      neut = -1;
      neut2 = -1;
      for(i = 0; i < 20; i++) {
        //n1[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg11);
        n1[i].set_xc(random(11, 109));
        n1[i].set_yc(11 + 4.8f*i);
        n1[i].set_spx(random(-0.4f, 0.4f));
        n1[i].set_spy(random(-0.4f, 0.4f));
        n1[i].change_pg(pg11);
      }
      for(i = 0; i < 20; i++) {
        //n2[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg9);
        n2[i].set_xc(random(11, 109));
        n2[i].set_yc(11 + 4.8f*i);
        n2[i].set_spx(random(-0.4f, 0.4f));
        n2[i].set_spy(random(-0.4f, 0.4f));
        n2[i].change_pg(pg9);
      }
      for(i = 0; i < 3; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 270, 0, 0, -0.4, 0, 0, pg8); //lacunas
        b[i].set_xc(32);
        b[i].set_yc(270);
        b[i].set_spx(0);
        b[i].set_spy(-0.4f);
        b[i].change_pg(pg8);
      }
      for(i = 3; i < 6; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 376, 0, 0, 0.6, 0, 0, pg8); //el\u00e9trons
        b[i].set_xc(32);
        b[i].set_yc(376);
        b[i].set_spx(0);
        b[i].set_spy(0.6f);
        b[i].change_pg(pg8);
      }
      for(i = 0; i < 6; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 45 + 200*i, 0, 0, -0.4, 0, 0, pg8); //lacunas
        v2[i].set_xc(242);
        v2[i].set_yc(45 + 200*i);
        v2[i].set_spx(0);
        v2[i].set_spy(-0.4f);
        v2[i].change_pg(pg8);
      }
       for(i = 6; i < 12; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 185 - 200*(i - 6), 0, 0, 0.4, 0, 0, pg8); //el\u00e9trons
        v2[i].set_xc(242);
        v2[i].set_yc(185 - 200*(i - 6));
        v2[i].set_spx(0);
        v2[i].set_spy(0.4f);
        v2[i].change_pg(pg8);
      }
  }
  if(window != 0 && window != 9 && ics >= 309 && ics <= 380 && igrec >= 6 && igrec <= 36) {
   ics = 1000;
   igrec = 1000;
   window = 9; 
  }
  else  if(window != 0 && window != 8 && ics >= 209 && ics <= 305 && igrec >= 6 && igrec <= 36) {
   ics = 1000;
   igrec = 1000;
   window = 8; 
  }
  else  if(window != 0 && window != 7 && ics >= 119 && ics <= 205 && igrec >= 6 && igrec <= 36) {
   ics = 1000;
   igrec = 1000;
   window = 7; 
  }
  else  if(window != 0 && window != 6 && ics >= 22 && ics <= 115 && igrec >= 6 && igrec <= 36) {
   ics = 1000;
   igrec = 1000;
   window = 6; 
  }
  else if(window == 0 && ics >= 695 && ics <= 775 && igrec >= 530 && igrec <= 560) {
   ics = 1000;
   igrec = 1000;
   window = 9;
  }
  else if(window == 0 && ics >= 615 && ics <= 691 && igrec >= 530 && igrec <= 560) {
   ics = 1000;
   igrec = 1000;
   window = 6;
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "BJ_transistor_v2" });
  }
}
