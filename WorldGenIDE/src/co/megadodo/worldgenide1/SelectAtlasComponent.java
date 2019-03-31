package co.megadodo.worldgenide1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import org.json.JSONObject;

public class SelectAtlasComponent extends JPanel {
	
	public Image texture;
	public String textureStr;
	public String name;
	public int x;
	public int y;
	
	public int w;
	public int h;
	
	public SelectAtlasComponent(int _w, int _h) {
		w = _w;
		h = _h;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
	}
	
	public void laodTexture(String tex) {
		textureStr = tex;
		texture = Toolkit.getDefaultToolkit().createImage(textureStr);
	}
	
	public void loadJSON(JSONObject object) {
	}

}
