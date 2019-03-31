// GUIManager.pde
// DO NOT EDIT ANY FILES IN THIS LIBRARY
// VERSION 1.0
// AUTHOR JARMSTRONG

color GUIColorPrimary = color (255/2,255/2);
color GUIColorSecondary = color (255/3,255/2);

boolean mouseClicked = false;
boolean mouseReleased = false;

void GUI_mouseReleased() {
  mouseReleased = true;
}

void GUI_mousePressed() {
  mouseClicked = true;
}

void GUI_update() {
  mouseClicked = false;
  mouseReleased = false;
}