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
  
  void setValue (float val) {
    val = constrain (val, vmin, vmax);
    spos = newspos = map (val, vmin, vmax, sposMin, sposMax);
  }
  
  float getValue() {
    return map (spos, sposMin, sposMax, vmin, vmax);
  }

  GUISlider (float xp, float yp, int sw, int sh, float vMin, float vMax) {
    this (xp,yp,sw,sh,vMin,vMax,1);
  }

  GUISlider (float xp, float yp, int sw, int sh, float vMin, float vMax, int l) {
    super (xp,yp,sw,sh);
    vmin = vMin;
    vmax = vMax;
    int widthtoheight = sw - sh;
    ratio = (float) sw / (float) widthtoheight;
    ratio = 0.01;
    spos = pos.x+size.x/2;
    newspos = spos;
    sposMin = pos.x;
    sposMax = pos.x + size.x - size.y;
    loose = l;
  }

  void update() {
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
    if (abs (newspos - spos) > 1) {
      spos = spos + (newspos-spos)/loose;
    }
  }

  float constrain (float val, float minv, float maxv) {
    return min (max (val, minv), maxv);
  }

  boolean overEvent() {
    if (mouseX > pos.x && mouseX < pos.x+size.x &&
       mouseY > pos.y && mouseY < pos.y+size.y) {
      return true;
    } else {
      return false;
    }
  }

  void draw() {
    noStroke ();
    fill (GUIColorPrimary);
    rect (pos.x, pos.y, size.x, size.y);
    if (over || locked) {
      fill (0, 0, 0);
    } else {
      fill (30);
    }
    rect (spos, pos.y, size.y, size.y);
  }

  float getPos() {
    // Convert spos to be values between
    // 0 and the total width of the scrollbar
    return spos * ratio;
  }
}