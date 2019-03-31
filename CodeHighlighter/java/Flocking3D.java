import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Flocking3D extends PApplet {

float DEPTH = 800;

WindowFlocking flocking;

PApplet MAIN_APPLET = this;

int   BOID_ACCURACY         = 5 ;
float BOID_DEBUG_DIRS_MAG   = 20;
float BOID_DEBUG_AVGVEL_MAG = 200;
float BOID_DEBUG_AVGACC_MAG = 200;
float BOID_ALIGNMENT_DIST   = 50;

float DEF_SLIDER_MIN   = 0 ;
float DEF_SLIDER_MAX   = 10;
float DEF_SLIDER_RATIO = 0.01f;
int   DEF_SLIDER_LOOSE = 1 ;
int   DEF_DISPLAY_ACC  = 2 ;

int   DISPLAY_ACCURACY_FRAMERATE  = 3;
int   DISPLAY_ACCURACY_NUMBOIDS   = 3;

float MIN_COHESION                = DEF_SLIDER_MIN  ;
float MAX_COHESION                = DEF_SLIDER_MAX  ;
float DEF_COHESION                = 0               ;
float RATIO_COHESION              = DEF_SLIDER_RATIO;
int   LOOSE_COHESION              = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_COHESION   = DEF_DISPLAY_ACC ;

float MIN_ALIGNMENTD              = 0               ;
float MAX_ALIGNMENTD              = 1000            ;
float DEF_ALIGNMENTD              = 100             ;
float RATIO_ALIGNMENTD            = DEF_SLIDER_RATIO;
int   LOOSE_ALIGNMENTD            = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_ALIGNMENTD = 0               ;

float MIN_SEPERATION              = DEF_SLIDER_MIN  ;
float MAX_SEPERATION              = DEF_SLIDER_MAX  ;
float DEF_SEPERATION              = 0               ;
float RATIO_SEPERATION            = DEF_SLIDER_RATIO;
int   LOOSE_SEPERATION            = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_SEPERATION = DEF_DISPLAY_ACC ;

float MIN_ALIGNMENT               = DEF_SLIDER_MIN  ;
float MAX_ALIGNMENT               = DEF_SLIDER_MAX  ;
float DEF_ALIGNMENT               = 1               ;
float RATIO_ALIGNMENT             = DEF_SLIDER_RATIO;
int   LOOSE_ALIGNMENT             = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_ALIGNMENT  = DEF_DISPLAY_ACC ;

float MIN_RANDOMNESS              = DEF_SLIDER_MIN  ;
float MAX_RANDOMNESS              = DEF_SLIDER_MAX  ;
float DEF_RANDOMNESS              = 1               ;
float RATIO_RANDOMNESS            = DEF_SLIDER_RATIO;
int   LOOSE_RANDOMNESS            = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_RANDOMNESS = DEF_DISPLAY_ACC ;

float MIN_ATTRACTION              = DEF_SLIDER_MIN  ;
float MAX_ATTRACTION              = DEF_SLIDER_MAX  ;
float DEF_ATTRACTION              = 0               ;
float RATIO_ATTRACTION            = DEF_SLIDER_RATIO;
int   LOOSE_ATTRACTION            = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_ATTRACTION = DEF_DISPLAY_ACC ;

float MIN_MAXVEL                  = DEF_SLIDER_MIN  ;
float MAX_MAXVEL                  = DEF_SLIDER_MAX  ;
float DEF_MAXVEL                  = 2               ;
float RATIO_MAXVEL                = DEF_SLIDER_RATIO;
int   LOOSE_MAXVEL                = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_MAXVEL     = DEF_DISPLAY_ACC ;

float MIN_MAXFORCE                = 0               ;
float MAX_MAXFORCE                = 2               ;
float DEF_MAXFORCE                = 0.025f           ;
float RATIO_MAXFORCE              = 0.001f           ;
int   LOOSE_MAXFORCE              = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_MAXFORCE   = 4               ;

public void mouseReleased() {
  GUI_mouseReleased();
}

public void mousePressed() {
  GUI_mousePressed();
}

/*
REQUIRED GUI

DONE: lb & sl  : Seperation, Alignment, Randomness, Attraction, Alignment Dist
DONE: bt       : Clear Boids, Reset Boids, Reset Params (Sliders), Add Boids (1, 10, 100), Del Boids ( 1, 10, 100)
DONE: tbt      : Debug Directions, Debug Alignment Fields, Debug Avg Vel, Debug Avg Accel
DONE: lb       : Framerate, Num Boids
DONE: evt      : Clear Boids, Reset Boids, Reset Params (Sliders), Add Boids (1, 10, 100), Del Boids ( 1, 10, 100)
DONE: lb & sl  : maxVel, maxForce
*/

GUILabel lbSeperation;
GUILabel lbAlignment ;
GUILabel lbRandomness;
GUILabel lbAttraction;
GUILabel lbFramerate ;
GUILabel lbNumBoids  ;
GUILabel lbMaxVel    ;
GUILabel lbMaxForce  ;
GUILabel lbAlignmentD;
GUILabel lbCohesion  ;

GUISlider slSeperation;
GUISlider slAlignment ;
GUISlider slRandomness;
GUISlider slAttraction;
GUISlider slMaxVel    ;
GUISlider slMaxForce  ;
GUISlider slAlignmentD;
GUISlider slCohesion  ;

GUIButton btBoidsClear ;
GUIButton btBoidsReset ;
GUIButton btParamsReset;
GUIButton btAddBoids1  ;
GUIButton btAddBoids10 ;
GUIButton btAddBoids100;
GUIButton btDelBoids1  ;
GUIButton btDelBoids10 ;
GUIButton btDelBoids100;

GUIToggleButton tbtDebugDirs  ;
GUIToggleButton tbtDebugAlign ;
GUIToggleButton tbtDebugAvgVel;
GUIToggleButton tbtDebugAvgAcc;

GUIEvent evtBoidsClear ;
GUIEvent evtBoidsReset ;
GUIEvent evtParamsReset;
GUIEvent evtAddBoids1  ;
GUIEvent evtAddBoids10 ;
GUIEvent evtAddBoids100;
GUIEvent evtDelBoids1  ;
GUIEvent evtDelBoids10 ;
GUIEvent evtDelBoids100;

public void settings() {
  size(500, 500);
  //fullScreen();
}

public void resetParams() {
  
  slSeperation.setValue(DEF_SEPERATION);
  slAlignment .setValue(DEF_ALIGNMENT );
  slRandomness.setValue(DEF_RANDOMNESS);
  slAttraction.setValue(DEF_ATTRACTION);
  slMaxVel    .setValue(DEF_MAXVEL    );
  slMaxForce  .setValue(DEF_MAXFORCE  );
  slAlignmentD.setValue(DEF_ALIGNMENTD);
  slCohesion  .setValue(DEF_COHESION  );
  
}

public void setup() {
  
  flocking = new WindowFlocking();
  
  evtBoidsClear  = new GUIEvent() { public void run() { flocking.clearBoids();  } };
  evtBoidsReset  = new GUIEvent() { public void run() { flocking.resetBoids();  } };
  evtParamsReset = new GUIEvent() { public void run() { resetParams();          } };
  evtAddBoids1   = new GUIEvent() { public void run() { flocking.addBoid(1  );  } };
  evtAddBoids10  = new GUIEvent() { public void run() { flocking.addBoid(10 );  } };
  evtAddBoids100 = new GUIEvent() { public void run() { flocking.addBoid(100);  } };
  evtDelBoids1   = new GUIEvent() { public void run() { flocking.delBoid(1  );  } };
  evtDelBoids10  = new GUIEvent() { public void run() { flocking.delBoid(10 );  } };
  evtDelBoids100 = new GUIEvent() { public void run() { flocking.delBoid(100);  } };
  
  GUIColorPrimary = color(255/2);
  GUIColorSecondary = color(255/2, 255/2);
  println(red(GUIColorPrimary) + " " + green(GUIColorPrimary) + " " + blue(GUIColorPrimary) + " " + alpha(GUIColorPrimary));
  println(red(GUIColorSecondary) + " " + green(GUIColorSecondary) + " " + blue(GUIColorSecondary) + " " + alpha(GUIColorSecondary));  
  lbSeperation = new GUILabel(10     ,  10, width/2 - 10, 20, "Seperation    :"   , 20);
  lbAlignment  = new GUILabel(10     ,  40, width/2 - 10, 20, "Alignment     :"   , 20);
  lbRandomness = new GUILabel(10     ,  70, width/2 - 10, 20, "Randomness    :"   , 20);
  lbAttraction = new GUILabel(10     , 100, width/2 - 10, 20, "Attraction    :"   , 20);
  lbFramerate  = new GUILabel(10     , 280, width/2 - 10, 20, "Framerate     : 60", 20);
  lbNumBoids   = new GUILabel(width/2, 280, width/2 - 10, 20, "Num Boids     : 0" , 20);
  lbMaxVel     = new GUILabel(10     , 310, width/2 - 10, 20, "Max Vel       : "  , 20);
  lbMaxForce   = new GUILabel(10     , 340, width/2 - 10, 20, "Max Force     : "  , 20);
  lbAlignmentD = new GUILabel(10     , 370, width/2 - 10, 20, "Alignment Dist: "  , 20);
  lbCohesion   = new GUILabel(10     , 400, width/2 - 10, 20, "Cohesion      : "  , 20);
  
  slSeperation = new GUISlider(width/2,  10, width/2 - 10, 20, MIN_SEPERATION, MAX_SEPERATION, LOOSE_SEPERATION);
  slAlignment  = new GUISlider(width/2,  40, width/2 - 10, 20, MIN_ALIGNMENT , MAX_ALIGNMENT , LOOSE_ALIGNMENT );
  slRandomness = new GUISlider(width/2,  70, width/2 - 10, 20, MIN_RANDOMNESS, MAX_RANDOMNESS, LOOSE_RANDOMNESS);
  slAttraction = new GUISlider(width/2, 100, width/2 - 10, 20, MIN_ATTRACTION, MAX_ATTRACTION, LOOSE_ATTRACTION);
  slMaxVel     = new GUISlider(width/2, 310, width/2 - 10, 20, MIN_MAXVEL    , MAX_MAXVEL    , LOOSE_MAXVEL    );
  slMaxForce   = new GUISlider(width/2, 340, width/2 - 10, 20, MIN_MAXFORCE  , MAX_MAXFORCE  , LOOSE_MAXFORCE  );
  slAlignmentD = new GUISlider(width/2, 370, width/2 - 10, 20, MIN_ALIGNMENTD, MAX_ALIGNMENTD, LOOSE_ALIGNMENTD);
  slCohesion   = new GUISlider(width/2, 400, width/2 - 10, 20, MIN_COHESION  , MAX_COHESION  , LOOSE_COHESION  );
  
  btBoidsClear  = new GUIButton(10+0*(width-20)/3, 130, (width-20)/3, 20, "Clear Boids" , evtBoidsClear);
  btBoidsReset  = new GUIButton(10+1*(width-20)/3, 130, (width-20)/3, 20, "Reset Boids" , evtBoidsReset);
  btParamsReset = new GUIButton(10+2*(width-20)/3, 130, (width-20)/3, 20, "Reset Sliders", evtParamsReset);
  
  btAddBoids1   = new GUIButton(10+0*(width-20)/3, 160, (width-20)/3, 20, "Add 1 Boid"   , evtAddBoids1  );
  btAddBoids10  = new GUIButton(10+1*(width-20)/3, 160, (width-20)/3, 20, "Add 10 Boids" , evtAddBoids10 );
  btAddBoids100 = new GUIButton(10+2*(width-20)/3, 160, (width-20)/3, 20, "Add 100 Boids", evtAddBoids100);
  
  btDelBoids1   = new GUIButton(10+0*(width-20)/3, 190, (width-20)/3, 20, "Del 1 Boid"   , evtDelBoids1  );
  btDelBoids10  = new GUIButton(10+1*(width-20)/3, 190, (width-20)/3, 20, "Del 10 Boids" , evtDelBoids10 );
  btDelBoids100 = new GUIButton(10+2*(width-20)/3, 190, (width-20)/3, 20, "Del 100 Boids", evtDelBoids100);
  
  tbtDebugDirs   = new GUIToggleButton(10     , 220, width/2 - 10, 20, "Debug Dirs"     , "Don't Debug Dirs"    , false);
  tbtDebugAlign  = new GUIToggleButton(width/2, 220, width/2 - 10, 20, "Debug Alignment", "Dont Debug Alignment", false);
  tbtDebugAvgVel = new GUIToggleButton(10     , 250, width/2 - 10, 20, "Debug Avg Vel"  , "Don't Debug Avg Vel" , false);
  tbtDebugAvgAcc = new GUIToggleButton(width/2, 250, width/2 - 10, 20, "Debug Avg Acc"  , "Don't Debug Avg Acc" , false);
  
  slSeperation.setValue(DEF_SEPERATION);
  slAlignment .setValue(DEF_ALIGNMENT );
  slRandomness.setValue(DEF_RANDOMNESS);
  slAttraction.setValue(DEF_ATTRACTION);
  slMaxVel    .setValue(DEF_MAXVEL    );
  slMaxForce  .setValue(DEF_MAXFORCE  );
  slAlignmentD.setValue(DEF_ALIGNMENTD);
  slCohesion  .setValue(DEF_COHESION  );
  
  slSeperation.ratio = RATIO_SEPERATION;
  slAlignment .ratio = RATIO_ALIGNMENT ;
  slRandomness.ratio = RATIO_RANDOMNESS;
  slAttraction.ratio = RATIO_ATTRACTION;
  slMaxVel    .ratio = RATIO_MAXVEL    ;
  slMaxForce  .ratio = RATIO_MAXFORCE  ;
  slAlignmentD.ratio = RATIO_ALIGNMENTD;
  slCohesion  .ratio = RATIO_COHESION  ;
  
  PApplet.runSketch(new String[] { "WindowFlocking" }, flocking);
  
  textFont(loadFont("Monospaced-48.vlw"));
}

public boolean getDebugDirs() {
  return tbtDebugDirs.state();
}

public boolean getDebugAlign() {
  return tbtDebugAlign.state();
}

public boolean getDebugAvgVel() {
  return tbtDebugAvgVel.state();
}

public boolean getDebugAvgAcc() {
  return tbtDebugAvgAcc.state();
}

public float getCohesion(int acc) {
  float tens = pow(10, acc);
  return round ( slCohesion.getValue() * tens ) / tens;
}

public float getAlignmentD(int acc) {
  float tens = pow(10, acc);
  return round(slAlignmentD.getValue() * tens ) / tens;
}

public float getSeperation(int acc) {
  float tens = pow(10, acc);
  return round(slSeperation.getValue() * tens ) / tens;
}

public float getAlignment (int acc) {
  float tens = pow(10, acc);
  return round(slAlignment .getValue() * tens ) / tens;
}

public float getRandomness(int acc) {
  float tens = pow(10, acc);
  return round(slRandomness.getValue() * tens ) / tens;
}

public float getAttraction(int acc) {
  float tens = pow(10, acc);
  return round(slAttraction.getValue() * tens ) / tens;
}

public float getMaxVel    (int acc) {
  float tens = pow(10, acc);
  return round(slMaxVel    .getValue() * tens ) / tens;
}

public float getMaxForce  (int acc) {
  float tens = pow(10, acc);
  return round(slMaxForce  .getValue() * tens ) / tens;
}

float backgroundR = 255/2;
float backgroundG = 255/2;
float backgroundB = 255/2;

public void updateBackgroundRandom() {
  float a = 1;
  backgroundR += random(-a, a);
  backgroundG += random(-a, a);
  backgroundB += random(-a, a);
  float b = 100;
  backgroundR = constrain(backgroundR, b, 255-b);
  backgroundG = constrain(backgroundG, b, 255-b);
  backgroundB = constrain(backgroundB, b, 255-b);
  float c = 5;
  while(abs(backgroundR-backgroundG)<c)updateBackgroundRandom();
  while(abs(backgroundG-backgroundB)<c)updateBackgroundRandom();
  while(abs(backgroundR-backgroundB)<c)updateBackgroundRandom();
}

public void updateBackgroundMousePos() {
  
  backgroundR = mouseX;
  backgroundG = mouseY;
  backgroundB = 10;
  
  
  backgroundR = constrain(backgroundR, 0, 255);
  backgroundG = constrain(backgroundG, 0, 255);
  backgroundB = constrain(backgroundB, 0, 255);
}

public void updateBackgroundParams() {
  backgroundR = map(getSeperation(5), 0, 10, 0, 255);
  backgroundG = map(getAlignment (5), 0, 10, 0, 255);
  backgroundB = map(getAttraction(5), 0, 10, 0, 255);
}

public void setFramerate(float fps) {
  float tens = pow(10, DISPLAY_ACCURACY_FRAMERATE);
  lbFramerate.text = "Framerate: " + round(fps * tens) / tens;
}

public void setNumBoids() {
  lbNumBoids.text = "Num Boids: " + flocking.boids.size();
}

public void draw() {
  background(255);
  //background(backgroundR,backgroundG,backgroundB);
  //updateBackgroundRandom();
  //updateBackgroundMousePos();
  //updateBackgroundParams();
  
  setFramerate(frameRate);
  setNumBoids();
  
  lbSeperation.text = "Seperation    : " +      getSeperation(DISPLAY_ACCURACY_SEPERATION);
  lbAlignment .text = "Alignment     : " +      getAlignment (DISPLAY_ACCURACY_ALIGNMENT );
  lbRandomness.text = "Randomness    : " +      getRandomness(DISPLAY_ACCURACY_RANDOMNESS);
  lbAttraction.text = "Attraction    : " +      getAttraction(DISPLAY_ACCURACY_ATTRACTION);
  lbMaxVel    .text = "Max Vel       : " +      getMaxVel    (DISPLAY_ACCURACY_MAXVEL    );
  lbMaxForce  .text = "Max Force     : " +      getMaxForce  (DISPLAY_ACCURACY_MAXFORCE  );
  lbAlignmentD.text = "Alignment Dist: " + (int)getAlignmentD(DISPLAY_ACCURACY_ALIGNMENTD);
  lbCohesion  .text = "Cohesion      : " +      getCohesion  (DISPLAY_ACCURACY_COHESION  );
  
  
  slSeperation  .update();
  slAlignment   .update();
  slRandomness  .update();
  slAttraction  .update();
  slMaxVel      .update();
  slMaxForce    .update();
  slAlignmentD  .update();
  slCohesion    .update();
  
  btBoidsClear  .update();
  btBoidsReset  .update();
  btParamsReset .update();
  
  btAddBoids1   .update();
  btAddBoids10  .update();
  btAddBoids100 .update();
  
  btDelBoids1   .update();
  btDelBoids10  .update();
  btDelBoids100 .update();
  
  tbtDebugDirs  .update();
  tbtDebugAlign .update();
  tbtDebugAvgVel.update();
  tbtDebugAvgAcc.update();
  
  lbSeperation  .draw();
  lbAlignment   .draw();
  lbRandomness  .draw();
  lbAttraction  .draw();
  lbFramerate   .draw();
  lbNumBoids    .draw();
  lbMaxVel      .draw();
  lbMaxForce    .draw();
  lbAlignmentD  .draw();
  lbCohesion    .draw();
  
  slSeperation  .draw();
  slAlignment   .draw();
  slRandomness  .draw();
  slAttraction  .draw();
  slMaxVel      .draw();
  slMaxForce    .draw();
  slAlignmentD  .draw();
  slCohesion    .draw();
  
  btBoidsClear  .draw();
  btBoidsReset  .draw();
  btParamsReset .draw();
  
  btAddBoids1   .draw();
  btAddBoids10  .draw();
  btAddBoids100 .draw();
  
  btDelBoids1   .draw();
  btDelBoids10  .draw();
  btDelBoids100 .draw();
  
  tbtDebugDirs  .draw();
  tbtDebugAlign .draw();
  tbtDebugAvgVel.draw();
  tbtDebugAvgAcc.draw();
  
  // SEPERATION LINES
  stroke(0);
  line(width/2,  10, width/2,  30-1);
  line(width/2,  40, width/2,  60-1);
  line(width/2,  70, width/2,  90-1);
  line(width/2, 100, width/2, 120-1);
  
  GUI_update();
}
class Boid {
  
  PVector pos;
  PVector vel;
  PVector acc;
  float size = 2;
  PApplet p;
  
  Boid(PApplet p) {
    this(p, new PVector(random(p.width), random(p.height), random(DEPTH)));
  }
  
  Boid(PApplet p, PVector pos) {
    this(p, pos, new PVector(random(-getMaxVel(BOID_ACCURACY), getMaxVel(BOID_ACCURACY)), random(-getMaxVel(BOID_ACCURACY), getMaxVel(BOID_ACCURACY)), random(-getMaxVel(BOID_ACCURACY), getMaxVel(BOID_ACCURACY))));
  }
  
  Boid(PApplet p, PVector pos, PVector vel) {
    this(p, pos, vel, new PVector(random(-getMaxForce(BOID_ACCURACY), getMaxForce(BOID_ACCURACY)), random(-getMaxForce(BOID_ACCURACY), getMaxForce(BOID_ACCURACY)), random(-getMaxForce(BOID_ACCURACY), getMaxVel(BOID_ACCURACY))));
  }
  
  Boid(PApplet p, PVector pos, PVector vel, PVector acc) {
    this.p   = p  ;
    this.pos = pos;
    this.vel = vel;
    this.acc = acc;
  }
  
  public void update(ArrayList<Boid> boids) {
    update(p, boids);
  }
  
  public void update(PApplet applet, ArrayList<Boid> boids) {
    checkBorders();
    
    
    acc.mult(0);
    acc.add(calcRandomness().mult(getRandomness(BOID_ACCURACY)));
    acc.add(calcSeperate(boids).mult(getSeperation(BOID_ACCURACY)));
    acc.add(calcAlignment(boids).mult(getAlignment(BOID_ACCURACY)));
    acc.add(calcAttraction(new PVector(applet.mouseX,applet.mouseY, DEPTH/2)).mult(getAttraction(BOID_ACCURACY)));
    acc.add(calcCohesion(boids).mult(getCohesion(BOID_ACCURACY)));
    
    vel.add(acc);
    vel.limit(getMaxVel(BOID_ACCURACY));
    pos.add(vel);
  }
  
  public PVector calcCohesion(ArrayList<Boid> boids) {
    
    PVector total = new PVector(0, 0, 0);
    int count = 0;
    
    for(Boid b : boids) {
      if(b == this) continue;
      if(PVector.dist(b.pos, pos) > getAlignmentD(BOID_ACCURACY)) continue;
      total.add(b.pos);
      count++;
    }
    
    if(count > 0) {
      
      total.div((float)count);
      total.normalize();
      total.mult(getMaxVel(BOID_ACCURACY));
      total.sub(vel);
      total.limit(getMaxForce(BOID_ACCURACY));
      
    }
    
    return total;
    
  }
  
  public PVector calcAlignment(ArrayList<Boid> boids) {
    PVector s = new PVector(0, 0, 0);
    int n = 0;
    
    for(Boid b : boids) {
      if(b.equals(this)) continue;
      if(b.pos.dist(pos) > BOID_ALIGNMENT_DIST) continue;
      n++;
      s.add(b.vel);
    }
    
    if(n>0) {
      s.div((float)n);
      s.normalize();
      s.mult(getMaxVel(BOID_ACCURACY));
      s.sub(vel);
      s.limit(getMaxForce(BOID_ACCURACY));
    }
    
    return s;
  }
  
  public PVector calcSeperate(ArrayList<Boid> boids) {
    float _seperation = 25;
    PVector s = new PVector(0,0,0);
    int n = 0;
    for(Boid b : boids) {
      float d = PVector.dist(pos, b.pos);
      if( d  > 0 && d < _seperation) {
        PVector diff = PVector.sub(pos, b.pos);
        diff.normalize();
        diff.div(d);
        s.add(diff);
        n++;
      }
    }
    if(n>0) {
      s.div((float)n);
      s.normalize();
      s.mult(getMaxVel(BOID_ACCURACY));
      s.sub(vel);
      s.limit(getMaxForce(BOID_ACCURACY));
    }
    return s;
  }
  
  public PVector calcAttraction(PVector spot) {
    
    PVector d = PVector.sub(spot, pos);
    d.normalize();
    d.mult(getMaxVel(BOID_ACCURACY));
    PVector s = PVector.sub(d, vel);
    s.limit(getMaxForce(BOID_ACCURACY));
    
    return s;
    
  }
  
  public PVector calcRandomness() {
    
    float x = (float) Math.random();
    float y = (float) Math.random();
    float z = (float) Math.random();
    
    if(Math.random() < 0.5f) x = -x;
    if(Math.random() < 0.5f) y = -y;
    if(Math.random() < 0.5f) z = -z;
    
    PVector r = new PVector(x, y, z);
    
    r.normalize();
    r.mult(getMaxVel(BOID_ACCURACY));
    r.limit(getMaxForce(BOID_ACCURACY));
    
    return r;
    
  }
  
  public void checkBorders() {
    if(pos.x < 0       ) pos.x = p.width ;
    if(pos.y < 0       ) pos.y = p.height;
    if(pos.z < 0       ) pos.z = DEPTH   ;
    if(pos.x > p.width ) pos.x = 0       ;
    if(pos.y > p.height) pos.y = 0       ;
    if(pos.z > DEPTH   ) pos.z = 0       ;
  }
  
  public void draw() {
    draw(p);
  }
  
  public void draw(PApplet applet) {
    //applet.fill(175);
    //applet.stroke(0);
    //applet.pushMatrix();
    //applet.translate(pos.x,pos.y);
    //applet.rotate(theta);
    //applet.beginShape(TRIANGLES);
    //applet.vertex(0, -size*2);
    //applet.vertex(-size, size*2);
    //applet.vertex(size, size*2);
    //applet.endShape();
    //applet.popMatrix();
    applet.fill(175);
    applet.stroke(175);
    applet.pushMatrix();
    applet.translate(pos.x, pos.y, pos.z);
    applet.box(size);
    applet.popMatrix();
    
    if(getDebugDirs()) {
      
      applet.stroke(255);
      applet.line(pos.x, pos.y, pos.z, pos.x + vel.x * BOID_DEBUG_DIRS_MAG, pos.y + vel.y * BOID_DEBUG_DIRS_MAG, pos.z + vel.z * BOID_DEBUG_DIRS_MAG);
      
    }
    
    if(getDebugAlign()) {
      applet.noFill();
      applet.stroke(0,255,0);
      //applet.ellipse(pos.x, pos.y, getAlignmentD(BOID_ACCURACY), getAlignmentD(BOID_ACCURACY));
      applet.pushMatrix();
      applet.translate(pos.x, pos.y, pos.z);
      applet.sphereDetail(3);
      applet.sphere(getAlignmentD(BOID_ACCURACY));
      applet.popMatrix();
    }
  }
  
}
// GUIButton.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUIButton extends GUIComponent {
  
  String text;
  GUIEvent startPress;
  GUIEvent endPress;
  GUIEvent anyPress;
  
  boolean clicked = false;
  
  public GUIButton(float x, float y, float w, float h, String text) {
    this(x, y, w, h, text, null, null, null);
  }
  
  public GUIButton(float x, float y, float w, float h, String text, GUIEvent startPress) {
    this(x, y, w, h, text, startPress, null, null);
  }
  
  public GUIButton(float x, float y, float w, float h, String text, GUIEvent startPress, GUIEvent endPress, GUIEvent anyPress) {
    super(x, y, w, h);
    
    this.text = text;
    this.startPress = startPress;
    this.endPress = endPress;
    this.anyPress = anyPress;
  }
  
  public void draw() {
    int col = clicked ? GUIColorPrimary : GUIColorSecondary;
    fill(col);
    noStroke();
    rect(pos.x,pos.y,size.x,size.y);
    fill(0);
    text(text, pos.x, pos.y+size.y);
  }
  
  public void update() {
    if(mouseHover()) {
      if(mouseClicked) {
        clicked = true;
        if(startPress!=null)startPress.run();
        if(anyPress!=null)anyPress.run();
      }
      
      if(mouseReleased) {
        clicked = false;
        if(endPress!=null)endPress.run();
        if(anyPress!=null)anyPress.run();
      }
    }
  }
  
}
// GUIComponent.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

abstract class GUIComponent {
  
  PVector pos;
  PVector size;
  
  GUIComponent(float x, float y, float w, float h) {
    pos = new PVector(x, y);
    size = new PVector(w, h);
  }
  
  public abstract void update();
  public abstract void draw();
  
  public boolean mouseHover() {
    return pos.x < mouseX && mouseX < pos.x + size.x && pos.y < mouseY && mouseY < pos.y + size.y;
  }
  
  
}
// GUIEvent.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

interface GUIEvent {
  public void run();
}
// GUIEventEmpty.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUIEventEmpty implements GUIEvent {
  public void run() {
    
  }
}
// GUIEventText.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUIEventText implements GUIEvent {
  String text;
  
  GUIEventText(String text) {
    this.text = text;
  }
  
  public void run() {
    
    println(text);
    
  }
}
// GUILabel.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUILabel extends GUIComponent {
  
  String text;
  int textSize;
  
  GUILabel(int x, int y, int w, int h, String text, int textSize) {
    super(x,y,w,h);
    this.pos = new PVector(x, y);
    this.size = new PVector(w, h);
    this.text = text;
    this.textSize = textSize;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public void draw() {
    fill(GUIColorPrimary);
    noStroke();
    rect(pos.x,pos.y,size.x,size.y);
    textSize(textSize);
    fill(0);
    text(text, pos.x, pos.y+size.y);
  }
  
  public void update() {
  }
  
}
// GUIManager.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

int GUIColorPrimary = color(255/2,255/2);
int GUIColorSecondary = color(255/3,255/2);

boolean mouseClicked = false;
boolean mouseReleased = false;

public void GUI_mouseReleased() {
  mouseReleased = true;
}

public void GUI_mousePressed() {
  mouseClicked = true;
}

public void GUI_update() {
  mouseClicked = false;
  mouseReleased = false;
}
// GUISlider.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG
// ADAPTED FROM SCROLLBAR EXAMPLE: https://processing.org/examples/scrollbar.html
// JAVA EXAMPLES PATH: Topics/GUI/Scrollbar

class GUISlider extends GUIComponent {
  float spos, newspos;    // x position of slider
  float sposMin, sposMax; // max and min values of slider
  int loose;              // how loose/heavy
  boolean over;           // is the mouse over the slider?
  boolean locked;
  float ratio;
  float vmin, vmax;
  
  public void setValue(float val) {
    val = constrain(val, vmin, vmax);
    spos = newspos = map(val, vmin, vmax, sposMin, sposMax);
  }
  
  public float getValue() {
    return map(spos, sposMin, sposMax, vmin, vmax);
  }

  GUISlider (float xp, float yp, int sw, int sh, float vMin, float vMax) {
    this(xp,yp,sw,sh,vMin,vMax,1);
  }

  GUISlider (float xp, float yp, int sw, int sh, float vMin, float vMax, int l) {
    super(xp,yp,sw,sh);
    vmin = vMin;
    vmax = vMax;
    int widthtoheight = sw - sh;
    ratio = (float)sw / (float)widthtoheight;
    ratio = 0.01f;
    spos = pos.x+size.x/2;
    newspos = spos;
    sposMin = pos.x;
    sposMax = pos.x + size.x - size.y;
    loose = l;
  }

  public void update() {
    over = overEvent();
    if (mousePressed && over) {
      locked = true;
    }
    if (!mousePressed) {
      locked = false;
    }
    if (locked) {
      newspos = constrain(mouseX-size.y/2, sposMin, sposMax);
    }
    if (abs(newspos - spos) > 1) {
      spos = spos + (newspos-spos)/loose;
    }
  }

  public float constrain(float val, float minv, float maxv) {
    return min(max(val, minv), maxv);
  }

  public boolean overEvent() {
    if (mouseX > pos.x && mouseX < pos.x+size.x &&
       mouseY > pos.y && mouseY < pos.y+size.y) {
      return true;
    } else {
      return false;
    }
  }

  public void draw() {
    noStroke();
    fill(GUIColorPrimary);
    rect(pos.x, pos.y, size.x, size.y);
    if (over || locked) {
      fill(0, 0, 0);
    } else {
      fill(30);
    }
    rect(spos, pos.y, size.y, size.y);
  }

  public float getPos() {
    // Convert spos to be values between
    // 0 and the total width of the scrollbar
    return spos * ratio;
  }
}
// GUIToggleButton.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUIToggleButton extends GUIComponent {
  
  String textOn;
  String textOff;
  boolean state;
  GUIEvent selectOn;
  GUIEvent selectOff;
  GUIEvent select;
  
  public GUIToggleButton(float x, float y, float w, float h, String textOn, String textOff, boolean defaultState) {
    this(x,y,w,h,textOn,textOff,defaultState,null,null,null);
  }
  
  public GUIToggleButton(float x, float y, float w, float h, String textOn, String textOff, boolean defaultState, GUIEvent selectOn, GUIEvent selectOff, GUIEvent select) {
    super(x,y,w,h);
    
    this.textOn = textOn;
    this.textOff = textOff;
    this.state = defaultState;
    this.selectOn = selectOn;
    this.selectOff = selectOff;
    this.select = select;
  }
  
  public boolean state() {
    return this.state;
  }
  
  public void draw() {
    int col = state ? GUIColorPrimary : GUIColorSecondary;
    fill(col);
    noStroke();
    rect(pos.x,pos.y,size.x,size.y);
    String text = state ? textOn : textOff;
    fill(0);
    text(text,pos.x,pos.y+size.y);
  }
  
  public void update() {
    if(mouseHover()) if(mouseClicked) {
      state = !state;
      if( state) if(selectOn  != null) selectOn .run();
      if(!state) if(selectOff != null) selectOff.run();
                 if(select    != null) select   .run();
    }
  }
  
}
class WindowFlocking extends PApplet {
  
  ArrayList<Boid> boids;
  
  boolean resetNextFrame=false;
  boolean clearNextFrame=false;
  int addNextFrame=0;
  
  public void settings() {
    size(800, 800, P3D);
    boids = new ArrayList<Boid>();
    //addBoid(500);
  }
  
  public void addBoid(int num) {
    //for(int i = 0; i < num; i++) boids.add(new Boid(this));
    addNextFrame+=num;
  }
  
  public void delBoid(int num) {
    //for(int i = 0; i < num && boids.size() > 0; i++) boids.remove(0);
    addNextFrame-=num;
  }
  
  public void clearBoids() {
    clearNextFrame = true;
  }
  
  public void resetBoids() {
    resetNextFrame = true;
  }
  
  public void draw() {
    this.background(0);
    setFramerate(this.frameRate);
    for(Boid b : boids) {
      b.update(boids);
      b.draw();
    }
    
    if(getDebugAvgVel()) {
      this.stroke(0,255,0);
      PVector avgVel = getAvgVel().normalize().mult(BOID_DEBUG_AVGVEL_MAG);
      this.line(this.width/2, this.height/2, this.width/2 + avgVel.x, this.height/2 + avgVel.y);
    }
    
    if(getDebugAvgAcc()) {
      this.stroke(255, 0, 0);
      PVector avgAcc = getAvgAcc().normalize().mult(BOID_DEBUG_AVGACC_MAG);
      this.line(this.width/2, this.height/2, this.width/2 + avgAcc.x, this.height/2 + avgAcc.y);
    }
    
    if(resetNextFrame) {
      resetNextFrame = false;
      int num = boids.size();
      boids.clear();
      addBoid(num);
    }
    
    if(clearNextFrame) {
      clearNextFrame = false;
      boids.clear();
    }
    
    if(addNextFrame > 0) {
      addNextFrame--;
      boids.add(new Boid(this));
    }
    
    if(addNextFrame < 0) {
      if(boids.size() == 0) addNextFrame = 0;
      else {
        addNextFrame++;
        boids.remove(0);
      }
    }
  }
  
  public PVector getAvgVel() {
    PVector v = new PVector();
    for(Boid b : boids) v.add(b.vel);
    if(boids.size()==0)return v;
    return v.div(boids.size());
  }
  
  public PVector getAvgAcc() {
    PVector v = new PVector();
    for(Boid b : boids) v.add(b.acc);
    if(boids.size()==0)return v;
    return v.div(boids.size());
  }
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Flocking3D" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
