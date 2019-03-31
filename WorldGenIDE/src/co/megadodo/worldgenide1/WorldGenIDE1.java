package co.megadodo.worldgenide1;

import java.io.File;

import javax.swing.JFrame;

import org.json.JSONObject;

public class WorldGenIDE1 {

	public static void main(String[] args) {
		new WorldGenIDE1();
	}
	
	public static File loadFile(String str) {
		return new File(str);
	}
	
	JFrame frame;
	
	public WorldGenIDE1() {
		frame = new JFrame();
		frame.setTitle("WorldGenIDE1");
		frame.setSize(1500,1100);//1500x1100
		
		SelectAtlasComponent comp = new SelectAtlasComponent(32, 32);
		JSONObject obj = new JSONObject();
		obj.put("AtlasName","Test");
		obj.put("x",4);
		obj.put("y",0);
		comp.loadJSON(obj);
		
		frame.setVisible(true);
	}

}
