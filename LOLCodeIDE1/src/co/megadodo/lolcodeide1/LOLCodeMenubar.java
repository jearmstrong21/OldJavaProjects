package co.megadodo.lolcodeide1;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class LOLCodeMenubar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5141616469848503226L;
	JMenu menuFile;
		JMenuItem itemSave;
		JMenuItem itemOpen;
		JMenuItem itemPref;
	JMenu menuProg;
		JMenuItem itemRun;
	JMenu menuTemplates;
		JMenuItem itemTemplates;
	JMenu menuSamples;
		JMenuItem itemSamples;
		
	int keyMask = 4;
	char keyMaskChar = (char) keyMask;
	public LOLCodeMenubar() {
		menuFile = new JMenu("File");
			itemSave = new JMenuItem("Save");
			itemSave.setAccelerator(KeyStroke.getKeyStroke('S', keyMask));
			itemOpen = new JMenuItem("Open");
			itemOpen.setAccelerator(KeyStroke.getKeyStroke('O', keyMask));
			itemPref = new JMenuItem("Preferences");
			itemPref.setAccelerator(KeyStroke.getKeyStroke(',', keyMask));
		menuFile.add(itemSave);
		menuFile.add(itemOpen);
		menuFile.add(itemPref);
		menuProg = new JMenu("Program");
			itemRun = new JMenuItem("Run program");
			itemRun.setAccelerator(KeyStroke.getKeyStroke('R', keyMask));
		menuProg.add(itemRun);
		menuTemplates = new JMenu("Templates");
			itemTemplates = new JMenuItem("Templates");
		menuTemplates.add(itemTemplates);
		menuSamples = new JMenu("Samples");
			itemSamples = new JMenuItem("Samples");
		menuSamples.add(itemSamples);
		this.add(menuFile);
		this.add(menuProg);
		this.add(menuTemplates);
		this.add(menuSamples);
	}
	
}
