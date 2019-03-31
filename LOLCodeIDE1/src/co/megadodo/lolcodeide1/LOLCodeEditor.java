package co.megadodo.lolcodeide1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;


public class LOLCodeEditor extends JPanel implements KeyListener, DocumentListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4916466352256722148L;
	JEditorPane area;
	JScrollPane pane;
	Font font;
	LayoutManager manager;
	Color col = new Color(255, 109, 106,255);
	boolean saved = true;
	
	public LOLCodeEditor(int w, int h) {
		super();
		this.area = new JEditorPane();
		this.area.setSize(w,h);
		this.area.setPreferredSize(new Dimension(w,h));
		this.area.setEnabled(false);
		this.pane = new JScrollPane(area);
		this.manager = new BorderLayout();
		this.setLayout(manager);
		this.add(pane, BorderLayout.CENTER);
		this.setTextSize(10);
//		this.addKeyListener(this);
//		area.addKeyListener(this);
		area.getDocument().addDocumentListener(this);
		
		this.highlight = new DefaultHighlighter();
		this.painter = new DefaultHighlighter.DefaultHighlightPainter(col);
		area.setHighlighter(highlight);
	}
	
	public void setTextSize(int size) {
		this.font = new Font(Font.MONOSPACED, Font.PLAIN, size);
		this.setFont(font);
		this.area.setFont(font);
	}
	
	public String getCode() {
		return area.getText();
	}
	
	public void lolEnable() {
		area.setEnabled(true);
	}
	
	public void setCode(String text) {
		area.setText(text);
		highlight();
	}
	
	Highlighter highlight;
	HighlightPainter painter;
	
	public void setFontSize() {
		setFont(new Font(Font.MONOSPACED, Font.PLAIN, LOLCodePrefs.TEXTSIZE_EDITOR));
		area.setFont(getFont());
	}
	
	public void highlight() {
		this.highlight = new DefaultHighlighter();
		this.painter = new DefaultHighlighter.DefaultHighlightPainter(col);
		area.setHighlighter(highlight);
		for(String s : LOLCodeIDE1.KEYWORDS) {
			highlight(s);
		}
//		String text = getCode();
//		int len = text.length();
//		for(int i = 0; i < len; i++) {
//			highlight()
//		}
	}
	
	public void highlight(String str) {
		String text = getCode();
		int len = str.length();
//		while(text.contains(str)) {
//			int ind = text.indexOf(str);
//			highlight(ind, ind+len);
//			text = text.substring(ind+len);
//		}
		int index = text.indexOf(str);

		while ( index >= 0 ) {
		    try {
				this.highlight.addHighlight(index, index+len, painter);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		    index = text.indexOf(str, index+len);
		}
	}
	
	public void save() {
		saved = true;
	}
	
	public void highlight(int p1, int p2) {
		try {
			highlight.addHighlight(p1, p2, painter);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public int getTextSize() {
		return this.font.getSize();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		highlight();
//		saved = false;
//		LOLCodeIDE1.inst.unsave();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		highlight();
//		saved = false;
//		LOLCodeIDE1.inst.unsave();
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		highlight();
//		saved = false;
//		LOLCodeIDE1.inst.unsave();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		highlight();
		saved = false;
		LOLCodeIDE1.inst.unsave();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		highlight();
		saved = false;
		LOLCodeIDE1.inst.unsave();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		highlight();
		saved = false;
		LOLCodeIDE1.inst.unsave();
	}
	
}
