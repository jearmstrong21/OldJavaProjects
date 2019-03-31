package co.megadodo.lolcodeide1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.tree.TreePath;

public class LOLCodeIDE1 extends JFrame {
	
	public static LOLCodeIDE1 inst = null;

	public final static ArrayList<String> KEYWORDS = genKeywords();
	
	private static final ArrayList<String> genKeywords() {
		ArrayList<String> keywords = new ArrayList<String>();
		// taken:
		String text = "YR, R ,I HAS A,ITZ,BTW,OBTW,TLDR,HAI,KTHXBYE,YARN,NUMBR,NUMBAR,TROOF,BUKKIT,FAIL,WIN,:),:>,:o,:\",::,(,),{,},[,],AN,SMOOSH,MKAY,SUM OF,DIFF OF,PRODUKT OF,QUOSHUNT OF,MOD OF,BIGGR OF,SMALLR OF,BOTH OF,EITHER OF,WON OF,NOT,ALL OF,ANY OF,BOTH SAEM,DIFFRINT,IT,MAEK, A ,IS NOW A,R MAEK,VISIBLE,GIMMEH,O RLY?,YA RLY,NO WAI,OIC,WTF?,OMG,OMGWTF,GTFO,IM IN YR,IM OUTTA YR,UPPIN,NERFIN,HOW IZ I,IF U SAY SO,FOUND YR,I IZ";
		return new ArrayList<String>(Arrays.<String>asList(text.split(",")));
//		return keywords;
	}
	
	private static final long serialVersionUID = -2194415725037893033L;

	public static void main(String[] args) {
		inst = new LOLCodeIDE1();
	}
	
	FileFilter lolFilter = new FileFilter() {
		
		@Override
		public boolean accept(File pathname) {
			String[] list = pathname.getName().split("\\.");
			if(list.length != 2) return false;
			return list[1].equals("lol");
		}
	};
	
	int WIDTH = 1300;
	int HEIGHT = 700;
	
	File openedFile = null;
	LOLCodeEditor editor;
	LOLCodeMenubar menubar;
	LOLCodeProjectManager projMan;
	LOLCodeConsole console;
	LOLCodePrefs prefs;
	BorderLayout layout;
	
	public LOLCodeIDE1() {
		inst = this;
		this.setTitle("LOLCodeIDE1");
		this.setSize(WIDTH, HEIGHT);
		prefs = new LOLCodePrefs();
		layout = new BorderLayout();
		console = new LOLCodeConsole(WIDTH,200);
		editor = new LOLCodeEditor(WIDTH-250, HEIGHT-500);
		editor.requestFocus();
		menubar = new LOLCodeMenubar();
		projMan = new LOLCodeProjectManager(200, HEIGHT);
		projMan.tree.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent me) {
				projManClick(me);
			}
			
		});
		projMan.tree.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) projManClick();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) projManClick();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) projManClick();
			}
		});
		this.setLayout(layout);
		this.add(projMan, BorderLayout.LINE_START);
//		this.add(editor, BorderLayout.CENTER);
//		this.add(console, BorderLayout.LINE_END); // SOUTH = bottom
		this.add(editor, BorderLayout.LINE_END);
		this.add(console, BorderLayout.SOUTH);
		this.setJMenuBar(menubar);
		 
		bindMenubarActions();
		
		this.setVisible(true);
		
//		openLOL(new File(LOLCodePrefs.ROOT_FILE + "Functions.lol"));
		
	}

	public void reset() {
		// TODO easy to find this area
//		projMan.setFontSize();
//		projMan.updateTree();
		
//		editor.setFontSize();
	}
	
	// this function is only called when editor is typed in, which means
	// that editor is enabled, which is only enabled when a file is opened,
	// which means that a file is opened, which means that openedFile is not
	// null.
	public void unsave() {
		setTitle("*" + openedFile.getName());
		editor.saved = false;
	}
	
	// this function is only called when editor is typed in, which means
	// that editor is enabled, which is only enabled when a file is opened,
	// which means that a file is opened, which means that openedFile is not
	// null.
	public void save() {
		if(openedFile == null) return;
		try {
			PrintWriter writer = new PrintWriter(openedFile);
			writer.println(editor.getCode());
			writer.close();
			editor.save();
			setTitle(openedFile.getName());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public String getPath(TreePath path) {
		String str = "";
		while(path != null) {
			str = path.getLastPathComponent().toString() + "/" + str;
			path = path.getParentPath();
		}
		str = str.substring(0, str.length()-1);
		str = str.replaceFirst("LOLCode/", "");
		return LOLCodePrefs.ROOT_FILE + str;
	}
	
	public void projManClick(MouseEvent me) {
		if(me.getClickCount() > 1) {
			TreePath tp = projMan.tree.getPathForLocation(me.getX(), me.getY());
			if(tp != null) {
				openLOL(new File(getPath(tp)));
				System.out.println(getPath(tp));
			}
		}
	}
	
	public void projManClick() {
		TreePath tp = projMan.tree.getSelectionPath();
		if(tp != null) {
			openLOL(new File(getPath(tp)));
			System.out.println(getPath(tp));
		}
	}
	
	public void bindMenubarActions() {
		menubar.itemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openLOLFull();
			}
		});
		menubar.itemSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		menubar.itemPref.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prefs.show();
			}
		});
	}
	
	public File promptForOpenFile() {
		JFileChooser chooser = new JFileChooser(LOLCodePrefs.ROOT_FILE);
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.CANCEL_OPTION) return null;
		return chooser.getSelectedFile();
	}
	
	public void openLOLFull() {
		openLOL(promptForOpenFile());
	}
	
	public String getText(File file) {
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		String text = "";
		while(sc.hasNextLine()) {
			text+=sc.nextLine();
			text+="\n";
		}
		sc.close();
		return text;
	}
	
	public void openLOL(File file) { 
		if(file == null) return;
		if(file.isDirectory()) return;
		if(!file.exists()) return;
		openedFile = file;
		String text = getText(file);

		editor.lolEnable();
		editor.setCode(text);
		this.setTitle(file.getName());
	}
	
}
