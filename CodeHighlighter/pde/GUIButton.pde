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
  
  public GUIButton (float x, float y, float w, float h, String text) {
    this (x, y, w, h, text, null, null, null);
  }
  
  public GUIButton( float x, float y, float w, float h, String text, GUIEvent startPress) {
    this (x, y, w, h, text, startPress, null, null);
  }
  
  public GUIButton( float x, float y, float w, float h, String text, GUIEvent startPress, GUIEvent endPress, GUIEvent anyPress) {
    super (x, y, w, h);
    
    this. text = text;
    this. startPress = startPress;
    this. endPress = endPress;
    this. anyPress = anyPress;
  }
  
  void draw () {
    color col = clicked ? GUIColorPrimary : GUIColorSecondary;
    fill (col);
    noStroke ();
    rect (pos.x,pos.y,size.x,size.y);
    fill (0);
    text (text, pos.x, pos.y+size.y);
  }
  
  void update () {
    if (mouseHover()) {
      if (mouseClicked) {
        clicked = true;
        if (startPress != null ) startPress.run();
        if (anyPress != null ) anyPress.run();
      }
      
      if (mouseReleased) {
        clicked = false;
        if (endPress != null) endPress.run();
        if (anyPress != null) anyPress.run();
      }
    }
  }
  
}