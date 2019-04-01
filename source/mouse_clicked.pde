void mouseClicked() {
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
    igrec = 1000; //aqui inserir a opção de reiniciar
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
      yc13 = 15.5;
      yc23 = 164.5;
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
        p[i].set_spx(0.5);
        p[i].set_spy(0);
      }
      for (i = 7; i < 11; i++) {//ok
        p[i].set_xc(xc23 + 20);
        p[i].set_spx(-0.5);
        p[i].set_spy(0);
        p[i].set_yc(110);
      }
      for (i = 11; i < 15; i++) {//ok
        p[i].set_yc(yc13 + 20);
        p[i].set_spy(0.5);
        p[i].set_spx(0);
        p[i].set_xc(120);
      }
      for (i = 15; i < 19; i++) {//ok
        p[i].set_yc(yc23 + 20);
        p[i].set_spy(-0.5);
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
