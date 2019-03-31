// GUIEventText.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

class GUIEventText implements GUIEvent {
  String text;
  
  GUIEventText(String text) {
    this.text = text;
  }
  
  void run() {
    
    println (text);
    
  }
}