  void mousePressed() {
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
      yc13 = 15.5;
      yc23 = 164.5;
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
   
   if(r1 == false && ics >= 380 && ics <= 404 && igrec >= 460 && igrec <= 484 && sub_aba == 1 && sen > -0.01 && sen < 0.01) {
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
    else if(get(int(ics), int(igrec)) == color(66, 70, 72) && op2 == 'A') {
      if(igrec <= 435 && vb < 6) {
        vb = vb + 0.5;
      }
      else if(vb > 0 && igrec >= 445)
        vb = vb - 0.5;
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
    xc12 = -80.5;
    xc22 = 429.5;
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
    yc13 = 15.5;
    yc23 = 164.5;
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
      xc12 = -80.5;
      xc22 = 429.5;
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
      //começa aqui
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
        port[i].set_xc(144.2 - 13.8*i);
        port[i].set_yc(random(35, 75));
        port[i].set_spx(random(-0.4, 0.4));
        port[i].set_spy(random(-0.4, 0.4));
      }
      for(i = 10; i < 13; i++) {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(35, 75));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
      }
      for(i = 3; i < 10; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
      }
      for(i = 13; i < 20; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
      }
      for(i = 0; i < 2; i++) {
        pp[i].change_pg(pg5);
        pp[i].set_xc(165);
        pp[i].set_yc(323);
        pp[i].set_spx(-0.4);
        pp[i].set_spy(0);
      }
      for(i = 2; i < 4; i++) {
        pp[i].change_pg(pg5);
        pp[i].set_xc(233);
        pp[i].set_yc(323);
        pp[i].set_spx(0.4);
        pp[i].set_spy(0);
      }
      pp[4].set_xc(233);
      pp[4].set_yc(305);
      pp[4].set_spx(0.4);
      pp[4].set_spy(0);
      pp[4].change_pg(pg5);
      
      pp[5].set_xc(165);
      pp[5].set_yc(305);
      pp[5].set_spx(-0.4);
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
        n1[i].set_yc(11 + 4.8*i);
        n1[i].set_spx(random(-0.4, 0.4));
        n1[i].set_spy(random(-0.4, 0.4));
        n1[i].change_pg(pg11);
      }
      for(i = 0; i < 20; i++) {
        //n2[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg9);
        n2[i].set_xc(random(11, 109));
        n2[i].set_yc(11 + 4.8*i);
        n2[i].set_spx(random(-0.4, 0.4));
        n2[i].set_spy(random(-0.4, 0.4));
        n2[i].change_pg(pg9);
      }
      for(i = 0; i < 3; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 270, 0, 0, -0.4, 0, 0, pg8); //lacunas
        b[i].set_xc(32);
        b[i].set_yc(270);
        b[i].set_spx(0);
        b[i].set_spy(-0.4);
        b[i].change_pg(pg8);
      }
      for(i = 3; i < 6; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 376, 0, 0, 0.6, 0, 0, pg8); //elétrons
        b[i].set_xc(32);
        b[i].set_yc(376);
        b[i].set_spx(0);
        b[i].set_spy(0.6);
        b[i].change_pg(pg8);
      }
      for(i = 0; i < 6; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 45 + 200*i, 0, 0, -0.4, 0, 0, pg8); //lacunas
        v2[i].set_xc(242);
        v2[i].set_yc(45 + 200*i);
        v2[i].set_spx(0);
        v2[i].set_spy(-0.4);
        v2[i].change_pg(pg8);
      }
       for(i = 6; i < 12; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 185 - 200*(i - 6), 0, 0, 0.4, 0, 0, pg8); //elétrons
        v2[i].set_xc(242);
        v2[i].set_yc(185 - 200*(i - 6));
        v2[i].set_spx(0);
        v2[i].set_spy(0.4);
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
      port[i].set_xc(144.2 - 13.8*i);
      port[i].set_yc(random(35, 75));
      port[i].set_spx(random(-0.4, 0.4));
      port[i].set_spy(random(-0.4, 0.4));
    }
    for(i = 10; i < 13; i++) {
      port[i].change_pg(pg7);
      port[i].set_xc(20 + 13.8*(i-10));
      port[i].set_yc(random(35, 75));
      port[i].set_spx(random(0.4, 0.6));
      port[i].set_spy(random(-0.4, 0.4));
    }
    for(i = 3; i < 10; i++) {
      if(i > 6) {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2 - 13.8*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
      }
      else {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2 - 13.8*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(-0.6, -0.4));
        port[i].set_spy(random(-0.4, 0.4));
      }
    }
    for(i = 13; i < 20; i++) {
      if(i > 16) {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(-0.6, -0.4));
        port[i].set_spy(random(-0.4, 0.4));
      }
      else {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
      }
    }
    for(i = 0; i < 2; i++) {
      pp[i].change_pg(pg5);
      pp[i].set_xc(165);
      pp[i].set_yc(323);
      pp[i].set_spx(-0.4);
      pp[i].set_spy(0);
    }
    for(i = 2; i < 4; i++) {
      pp[i].change_pg(pg5);
      pp[i].set_xc(233);
      pp[i].set_yc(323);
      pp[i].set_spx(0.4);
      pp[i].set_spy(0);
    }
    pp[4].set_xc(233);
    pp[4].set_yc(305);
    pp[4].set_spx(0.4);
    pp[4].set_spy(0);
    pp[4].change_pg(pg5);
    
    pp[5].set_xc(165);
    pp[5].set_yc(305);
    pp[5].set_spx(-0.4);
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
     //reiniciando elétrons e lacunas
     if(neut6 >= 6) {
       for(i = 3; i < 6; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
       }
     }
     else {
       for(i = 3; i < 7; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
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
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
      }
    }
    else {
      for(i = 16; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
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
     pp[1].set_spx(-0.4);
     pp[1].set_spy(0);
     pp[1].change_pg(pg5);
     pp[3].set_xc(233);
     pp[3].set_yc(323);
     pp[3].set_spx(0.4);
     pp[3].set_spy(0);
     pp[3].change_pg(pg5);
     passe5 = 0;
     passe6 = 0;
     passe16 = 0;
     passe17 = 0;
     pp[4].set_xc(233);
     pp[4].set_yc(305);
     pp[4].set_spx(0.4);
     pp[4].set_spy(0);
     pp[4].change_pg(pg5);
    
     pp[5].set_xc(165);
     pp[5].set_yc(305);
     pp[5].set_spx(-0.4);
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
        port[i].set_xc(144.2 - 13.8*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
      }
      else {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2 - 13.8*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(-0.6, -0.4));
        port[i].set_spy(random(-0.4, 0.4));
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
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(-0.6, -0.4));
        port[i].set_spy(random(-0.4, 0.4));
      }
      else {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
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
    pp[0].set_spx(-0.4);
    pp[0].set_spy(0);
    
    pp[2].change_pg(pg5);
    pp[2].set_xc(233);
    pp[2].set_yc(323);
    pp[2].set_spx(0.4);
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
     //reiniciando elétrons e lacunas
     if(neut6 >= 6) {
       for(i = 3; i < 6; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
       }
     }
     else {
       for(i = 3; i < 7; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
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
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
      }
    }
    else {
      for(i = 16; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
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
     pp[1].set_spx(-0.4);
     pp[1].set_spy(0);
     pp[1].change_pg(pg5);
     pp[3].set_xc(233);
     pp[3].set_yc(323);
     pp[3].set_spx(0.4);
     pp[3].set_spy(0);
     pp[3].change_pg(pg5);
     passe5 = 0;
     passe6 = 0;
     passe16 = 0;
     passe17 = 0;
     pp[4].set_xc(233);
     pp[4].set_yc(305);
     pp[4].set_spx(0.4);
     pp[4].set_spy(0);
     pp[4].change_pg(pg5);
    
     pp[5].set_xc(165);
     pp[5].set_yc(305);
     pp[5].set_spx(-0.4);
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
        port[i].set_xc(144.2 - 13.8*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
      }
      else {
        port[i].change_pg(pg6);
        port[i].set_xc(144.2 - 13.8*(i));
        port[i].set_yc(random(30, 80));
        port[i].set_spx(random(-0.6, -0.4));
        port[i].set_spy(random(-0.4, 0.4));
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
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(-0.6, -0.4));
        port[i].set_spy(random(-0.4, 0.4));
      }
      else {
        port[i].change_pg(pg7);
        port[i].set_xc(20 + 13.8*(i-10));
        port[i].set_yc(random(30, 70));
        port[i].set_spx(random(0.4, 0.6));
        port[i].set_spy(random(-0.4, 0.4));
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
    pp[0].set_spx(-0.4);
    pp[0].set_spy(0);
    
    pp[2].change_pg(pg5);
    pp[2].set_xc(233);
    pp[2].set_yc(323);
    pp[2].set_spx(0.4);
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
     //reiniciando elétrons e lacunas
     if(neut6 >= 6) {
       for(i = 3; i < 6; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
       }
     }
     else {
       for(i = 3; i < 7; i++) {
        if(i > 6) {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg6);
          port[i].set_xc(144.2 - 13.8*(i));
          port[i].set_yc(random(30, 80));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
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
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
        }
      }
    }
    else {
      for(i = 16; i < 20 && i != neut5; i++) {
        if(i > 16) {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(-0.6, -0.4));
          port[i].set_spy(random(-0.4, 0.4));
        }
        else {
          port[i].change_pg(pg7);
          port[i].set_xc(20 + 13.8*(i-10));
          port[i].set_yc(random(30, 70));
          port[i].set_spx(random(0.4, 0.6));
          port[i].set_spy(random(-0.4, 0.4));
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
     pp[1].set_spx(-0.4);
     pp[1].set_spy(0);
     pp[1].change_pg(pg5);
     pp[3].set_xc(233);
     pp[3].set_yc(323);
     pp[3].set_spx(0.4);
     pp[3].set_spy(0);
     pp[3].change_pg(pg5);
     passe5 = 0;
     passe6 = 0;
     passe16 = 0;
     passe17 = 0;
     pp[4].set_xc(233);
     pp[4].set_yc(305);
     pp[4].set_spx(0.4);
     pp[4].set_spy(0);
     pp[4].change_pg(pg5);
    
     pp[5].set_xc(165);
     pp[5].set_yc(305);
     pp[5].set_spx(-0.4);
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
        n1[i].set_yc(11 + 4.8*i);
        n1[i].set_spx(random(-0.4, 0.4));
        n1[i].set_spy(random(-0.4, 0.4));
        n1[i].change_pg(pg11);
      }
      for(i = 0; i < 20; i++) {
        //n2[i] = new Particulas(0, 6, 0, random(11, 109), 11 + 4.8*i, 0, random(-0.4, 0.4), random(-0.4, 0.4), 0, 0, pg9);
        n2[i].set_xc(random(11, 109));
        n2[i].set_yc(11 + 4.8*i);
        n2[i].set_spx(random(-0.4, 0.4));
        n2[i].set_spy(random(-0.4, 0.4));
        n2[i].change_pg(pg9);
      }
      for(i = 0; i < 3; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 270, 0, 0, -0.4, 0, 0, pg8); //lacunas
        b[i].set_xc(32);
        b[i].set_yc(270);
        b[i].set_spx(0);
        b[i].set_spy(-0.4);
        b[i].change_pg(pg8);
      }
      for(i = 3; i < 6; i++) {
        //b[i] = new Particulas(0, 6, 0, 32, 376, 0, 0, 0.6, 0, 0, pg8); //elétrons
        b[i].set_xc(32);
        b[i].set_yc(376);
        b[i].set_spx(0);
        b[i].set_spy(0.6);
        b[i].change_pg(pg8);
      }
      for(i = 0; i < 6; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 45 + 200*i, 0, 0, -0.4, 0, 0, pg8); //lacunas
        v2[i].set_xc(242);
        v2[i].set_yc(45 + 200*i);
        v2[i].set_spx(0);
        v2[i].set_spy(-0.4);
        v2[i].change_pg(pg8);
      }
       for(i = 6; i < 12; i++) {
        //v2[i] = new Particulas(0, 6, 0, 242, 185 - 200*(i - 6), 0, 0, 0.4, 0, 0, pg8); //elétrons
        v2[i].set_xc(242);
        v2[i].set_yc(185 - 200*(i - 6));
        v2[i].set_spx(0);
        v2[i].set_spy(0.4);
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
