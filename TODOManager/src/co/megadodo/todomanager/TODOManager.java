package co.megadodo.todomanager;

import javax.swing.UIManager;

public class TODOManager {

	public static void main(String[] args) {

		System.setProperty("apple.laf.useScreenMenuBar", "true");
        
        new TODOGUI();
        
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
