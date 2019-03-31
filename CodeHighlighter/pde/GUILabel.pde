// GUILabel.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUILabel extends GUIComponent {
  
  String text;
  int textSize;
  
  GUILabel (int x, int y, int w, int h, String text, int textSize) {
    super (x,y,w,h);
    this. pos = new PVector (x, y);
    this. size = new PVector (w, h);
    this. text = text;
    this. textSize = textSize;
  }
  
  void setText (String text) {
    this. text = text;
  }
  
  void draw () {
    fill (GUIColorPrimary);
    noStroke ();
    rect (pos.x,pos.y,size.x,size.y);
    textSize (textSize);
    fill (0);
    text (text, pos.x, pos.y+size.y);
  }
  
  void update() {
  }
  
}