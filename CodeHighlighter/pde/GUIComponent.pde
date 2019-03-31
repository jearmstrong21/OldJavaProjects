// GUIComponent.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

abstract class GUIComponent {
  
  PVector pos;
  PVector size;
  
  GUIComponent( float x, float y, float w, float h) {
    pos  = new PVector (x, y);
    size = new PVector (w, h);
  }
  
  abstract void update();
  abstract void draw ();
  
  boolean mouseHover() {
    return pos.x < mouseX && mouseX < pos.x + size.x && pos.y < mouseY && mouseY < pos.y + size.y;
  }
  
  
}