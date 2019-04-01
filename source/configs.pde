    
void configs(int window) {
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
      text("Voltar ao início da animação", 660, 26);
      
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
      if(window == 6) //instruções
        fill(255); 
      text("Instruções", 14, 13, 98, 30);
      
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
      text("Referências", 202, 13, 98, 30);
      
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
      text("Créditos", 290, 13, 98, 30);
      
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
      text("Instruções", 610, 538, 80, 30);
      text("Créditos", 688, 538, 80, 30);
    }
    if(window == 1)
      fill(255);
    if(window == 0)
      text("O silício e dopantes tipo p e n", 16, 23, 213, 30);
    else 
      text("O silício e dopantes tipo p e n", 16, 50, 213, 30); //somei 35
    fill(0);
    if(window == 2)
      fill(255);
    if(window == 0)
      text("Junção p-n", 219, 23, 115, 30);
    else
      text("Junção p-n", 219, 50, 115, 30);
    fill(0);
    if(window == 3)
      fill(255);
    if(window == 0)
      text("Diodo de junção bipolar", 322, 23, 170, 25);
    else
      text("Diodo de junção bipolar", 322, 50, 170, 25);
    fill(0);
    if(window == 4)
      fill(255);
    if(window == 0)
      text("Junção n-p-n", 491, 23, 110, 25);
    else
      text("Junção n-p-n", 491, 50, 110, 25);
    fill(0);
    if(window == 5)
      fill(255);
    if(window == 0)
      text("Transistor de junção bipolar", 590, 23, 200, 25);
    else
      text("Transistor de junção bipolar", 590, 50, 200, 25);
}   
 
