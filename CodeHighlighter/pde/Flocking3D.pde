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
float DEF_SLIDER_RATIO = 0.01;
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
float DEF_MAXFORCE                = 0.025           ;
float RATIO_MAXFORCE              = 0.001           ;
int   LOOSE_MAXFORCE              = DEF_SLIDER_LOOSE;
int   DISPLAY_ACCURACY_MAXFORCE   = 4               ;

void mouseReleased() {
  GUI_mouseReleased();
}

void mousePressed() {
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

void settings () {
  size (500, 500);
  //fullScreen();
}

void resetParams() {
  
  slSeperation.setValue(DEF_SEPERATION);
  slAlignment .setValue(DEF_ALIGNMENT );
  slRandomness.setValue(DEF_RANDOMNESS);
  slAttraction.setValue(DEF_ATTRACTION);
  slMaxVel    .setValue(DEF_MAXVEL    );
  slMaxForce  .setValue(DEF_MAXFORCE  );
  slAlignmentD.setValue(DEF_ALIGNMENTD);
  slCohesion  .setValue(DEF_COHESION  );
  
}

void setup () {
  
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
  
  GUIColorPrimary = color( 255/2);
  GUIColorSecondary = color( 255/2, 255/2);
  println( red( GUIColorPrimary) + " " + green( GUIColorPrimary) + " " + blue( GUIColorPrimary) + " " + alpha( GUIColorPrimary));
  println( red( GUIColorSecondary) + " " + green( GUIColorSecondary) + " " + blue( GUIColorSecondary) + " " + alpha( GUIColorSecondary));  
  lbSeperation = new GUILabel(10     ,  10, width /2 - 10, 20, "Seperation    :"    , 20);
  lbAlignment  = new GUILabel(10     ,  40, width /2 - 10, 20, "Alignment     :"    , 20);
  lbRandomness = new GUILabel(10     ,  70, width /2 - 10, 20, "Randomness    :"    , 20);
  lbAttraction = new GUILabel(10     , 100, width /2 - 10, 20, "Attraction    :"    , 20);
  lbFramerate  = new GUILabel(10     , 280, width /2 - 10, 20, "Framerate     : 60" , 20);
  lbNumBoids   = new GUILabel(width/2, 280, width /2 - 10, 20, "Num Boids     : 0"  , 20);
  lbMaxVel     = new GUILabel(10     , 310, width /2 - 10, 20, "Max Vel       : "   , 20);
  lbMaxForce   = new GUILabel(10     , 340, width /2 - 10, 20, "Max Force     : "   , 20);
  lbAlignmentD = new GUILabel(10     , 370, width /2 - 10, 20, "Alignment Dist: "   , 20);
  lbCohesion   = new GUILabel(10     , 400, width /2 - 10, 20, "Cohesion      : "   , 20);
  
  slSeperation = new GUISlider( width /2,  10, width /2 - 10, 20, MIN_SEPERATION, MAX_SEPERATION, LOOSE_SEPERATION);
  slAlignment  = new GUISlider( width /2,  40, width /2 - 10, 20, MIN_ALIGNMENT , MAX_ALIGNMENT , LOOSE_ALIGNMENT );
  slRandomness = new GUISlider( width /2,  70, width /2 - 10, 20, MIN_RANDOMNESS, MAX_RANDOMNESS, LOOSE_RANDOMNESS);
  slAttraction = new GUISlider( width /2, 100, width /2 - 10, 20, MIN_ATTRACTION, MAX_ATTRACTION, LOOSE_ATTRACTION);
  slMaxVel     = new GUISlider( width /2, 310, width /2 - 10, 20, MIN_MAXVEL    , MAX_MAXVEL    , LOOSE_MAXVEL    );
  slMaxForce   = new GUISlider( width /2, 340, width /2 - 10, 20, MIN_MAXFORCE  , MAX_MAXFORCE  , LOOSE_MAXFORCE  );
  slAlignmentD = new GUISlider( width /2, 370, width /2 - 10, 20, MIN_ALIGNMENTD, MAX_ALIGNMENTD, LOOSE_ALIGNMENTD);
  slCohesion   = new GUISlider( width /2, 400, width /2 - 10, 20, MIN_COHESION  , MAX_COHESION  , LOOSE_COHESION  );
  
  btBoidsClear  = new GUIButton(10+0*( width -20)/3, 130, (width -20)/3, 20, "Clear Boids" , evtBoidsClear);
  btBoidsReset  = new GUIButton(10+1*( width -20)/3, 130, (width -20)/3, 20, "Reset Boids" , evtBoidsReset);
  btParamsReset = new GUIButton(10+2*( width -20)/3, 130, (width -20)/3, 20, "Reset Sliders", evtParamsReset);
  
  btAddBoids1   = new GUIButton(10+0*( width -20)/3, 160, (width -20)/3, 20, "Add 1 Boid"    , evtAddBoids1  );
  btAddBoids10  = new GUIButton(10+1*( width -20)/3, 160, (width -20)/3, 20, "Add 10 Boids"  , evtAddBoids10 );
  btAddBoids100 = new GUIButton(10+2*( width -20)/3, 160, (width -20)/3, 20, "Add 100 Boids" , evtAddBoids100);
  
  btDelBoids1   = new GUIButton(10+0* (width -20)/3, 190, (width -20)/3, 20, "Del 1 Boid"    , evtDelBoids1  );
  btDelBoids10  = new GUIButton(10+1* (width -20)/3, 190, (width -20)/3, 20, "Del 10 Boids"  , evtDelBoids10 );
  btDelBoids100 = new GUIButton(10+2* (width -20)/3, 190, (width -20)/3, 20, "Del 100 Boids" , evtDelBoids100);
  
  tbtDebugDirs   = new GUIToggleButton (10      , 220, width /2 - 10, 20, "Debug Dirs"      , "Don't Debug Dirs"     , false);
  tbtDebugAlign  = new GUIToggleButton (width /2, 220, width /2 - 10, 20, "Debug Alignment" , "Dont Debug Alignment" , false);
  tbtDebugAvgVel = new GUIToggleButton (10      , 250, width /2 - 10, 20, "Debug Avg Vel"   , "Don't Debug Avg Vel"  , false);
  tbtDebugAvgAcc = new GUIToggleButton (width /2, 250, width /2 - 10, 20, "Debug Avg Acc"   , "Don't Debug Avg Acc"  , false);
  
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
  
  textFont (loadFont( "Monospaced-48.vlw" ));
}

boolean getDebugDirs() {
  return tbtDebugDirs.state();
}

boolean getDebugAlign() {
  return tbtDebugAlign.state();
}

boolean getDebugAvgVel() {
  return tbtDebugAvgVel.state();
}

boolean getDebugAvgAcc() {
  return tbtDebugAvgAcc.state();
}

float getCohesion( int acc) {
  float tens = pow (10, acc);
  return round ( slCohesion.getValue() * tens ) / tens;
}

float getAlignmentD( int acc) {
  float tens = pow (10, acc);
  return round (slAlignmentD.getValue() * tens ) / tens;
}

float getSeperation (int acc) {
  float tens = pow (10, acc);
  return round (slSeperation.getValue() * tens ) / tens;
}

float getAlignment (int acc) {
  float tens = pow (10, acc);
  return round (slAlignment .getValue() * tens ) / tens;
}

float getRandomness (int acc) {
  float tens = pow (10, acc);
  return round( slRandomness.getValue() * tens ) / tens;
}

float getAttraction( int acc) {
  float tens = pow (10, acc);
  return round (slAttraction.getValue() * tens ) / tens;
}

float getMaxVel    (int acc) {
  float tens = pow (10, acc);
  return round (slMaxVel    .getValue() * tens ) / tens;
}

float getMaxForce  (int acc) {
  float tens = pow( 10, acc);
  return round( slMaxForce  .getValue() * tens ) / tens;
}

void setFramerate (float fps) {
  float tens = pow (10, DISPLAY_ACCURACY_FRAMERATE);
  lbFramerate.text = "Framerate: " + round (fps * tens) / tens;
}

void setNumBoids() {
  lbNumBoids.text = "Num Boids: " + flocking.boids.size();
}

void draw () {
  background(255);
  
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
  
  
  slSeperation   .update();
  slAlignment    .update();
  slRandomness   .update();
  slAttraction   .update();
  slMaxVel       .update();
  slMaxForce     .update();
  slAlignmentD   .update();
  slCohesion     .update();
  
  btBoidsClear   .update();
  btBoidsReset   .update();
  btParamsReset  .update();
  
  btAddBoids1    .update();
  btAddBoids10   .update();
  btAddBoids100  .update();
  
  btDelBoids1    .update();
  btDelBoids10   .update();
  btDelBoids100  .update();
  
  tbtDebugDirs   .update();
  tbtDebugAlign  .update();
  tbtDebugAvgVel .update();
  tbtDebugAvgAcc .update();
  
  lbSeperation   .draw();
  lbAlignment    .draw();
  lbRandomness   .draw();
  lbAttraction   .draw();
  lbFramerate    .draw();
  lbNumBoids     .draw();
  lbMaxVel       .draw();
  lbMaxForce     .draw();
  lbAlignmentD   .draw();
  lbCohesion     .draw();
  
  slSeperation   .draw();
  slAlignment    .draw();
  slRandomness   .draw();
  slAttraction   .draw();
  slMaxVel       .draw();
  slMaxForce     .draw();
  slAlignmentD   .draw();
  slCohesion     .draw();
 
  btBoidsClear   .draw();
  btBoidsReset   .draw();
  btParamsReset  .draw();
  
  btAddBoids1    .draw();
  btAddBoids10   .draw();
  btAddBoids100  .draw();
  
  btDelBoids1    .draw();
  btDelBoids10   .draw();
  btDelBoids100  .draw();
  
  tbtDebugDirs   .draw();
  tbtDebugAlign  .draw();
  tbtDebugAvgVel .draw();
  tbtDebugAvgAcc .draw();
  
  // SEPERATION LINES
  stroke (0);
  line (width/2,  10, width/2,  30-1);
  line (width/2,  40, width/2,  60-1);
  line (width/2,  70, width/2,  90-1);
  line (width/2, 100, width/2, 120-1);
  
  GUI_update();
}