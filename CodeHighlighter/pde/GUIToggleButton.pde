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
  
  public GUIToggleButton (float x, float y, float w, float h, String textOn, String textOff, boolean defaultState) {
    this (x,y,w,h,textOn,textOff,defaultState,null,null,null);
  }
  
  public GUIToggleButton (float x, float y, float w, float h, String textOn, String textOff, boolean defaultState, GUIEvent selectOn, GUIEvent selectOff, GUIEvent select) {
    super (x,y,w,h);
    
    this. textOn = textOn;
    this. textOff = textOff;
    this. state = defaultState;
    this. selectOn = selectOn;
    this. selectOff = selectOff;
    this. select = select;
  }
  
  boolean state() {
    return this.state;
  }
  
  void draw () {
    color col = state ? GUIColorPrimary : GUIColorSecondary;
    fill (col);
    noStroke ();
    rect (pos.x,pos.y,size.x,size.y);
    String text = state ? textOn : textOff;
    fill (0);
    text (text,pos.x,pos.y+size.y);
  }
  
  void update() {
    if (mouseHover()) if (mouseClicked) {
      state = !state;
      if ( state) if (selectOn  != null) selectOn .run();
      if (!state) if (selectOff != null) selectOff.run();
                 if (select    != null) select   .run();
    }
  }
  
}