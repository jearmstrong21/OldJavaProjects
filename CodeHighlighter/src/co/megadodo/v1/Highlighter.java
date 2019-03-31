package co.megadodo.v1;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;


/*
 * This class highlights a given file.
 * Below this comment there is a test class put
 * there to prove that the comment does not extend
 * past this starslash mark.
 */ class TestClass {
	int i;
}
 /**
 * @author jackarmstrong
 * Highlighter class.
 */
public class Highlighter {

	public static void main(String[] args) {
		Highlighter highlighter = new Highlighter();
		String s = " BLAH BLAH BLAH " ;
//		highlighter.setFileNameIn( "src/co/megadodo/Highlighter.java" );
		highlighter.setFileNameIn("input.txt");
//		highlighter.setFileNameIn("Flocking3D.java");
//		highlighter.setFileNameIn("Flocking3D.pde");
		highlighter.setFileNameOut("index.html");
		highlighter.writeHTML();
	}
	
	private String fileNameIn;
	private String fileNameOut;
	
	public Highlighter() {
		
	}
	public void setFileNameOut(String fn) {
		this.fileNameOut = fn;
	}
	public File getFileOut() {
		File f;
		
		try {
			f = new File(fileNameOut);
		} catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
		
		try {
			if(!f.exists()) f.createNewFile();
		} catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
		
		return f;
	}
	public PrintWriter getWriter(File file) {
		if(file == null) return null;
		
		PrintWriter writer;
		
		try {
			writer = new PrintWriter(file);
		} catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
		
		return writer;
		
	}
	public void setFileNameIn(String fn) {
		this.fileNameIn = fn;
	}
	public File getFileIn() {
		File f;
		try {
			f = new File(fileNameIn);
		} catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
		
		try {
			if(!f.exists()) f.createNewFile();
		} catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
		
		return f;
	}
	public String getInStr() {
		String s = "";
		
		Scanner sc;
		
		try {
			sc = new Scanner(getFileIn());
		} catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
		
		while(sc.hasNextLine()) {
			s += sc.nextLine();
			s += "\n";
		}
		
		sc.close();
		
		return s;
	}
	
	public void writeHTML() {
		PrintWriter writer = getWriter(getFileOut());
		
		if(writer == null) return;
		
		String in = getInStr();
		
		writer.println( "<!DOCTYPE html>");
		writer.println( "<html>");
		writer.println( "<head>");
		writer.println( "	<style>");
		writer.println( "		.comment {");
		writer.println( "			color: " + HighlightState.COMMENT.getCol() + ";");
		writer.println( "		}");
		writer.println( "		.keyword {");
		writer.println( "			color: " + HighlightState.KEYWORD.getCol() + ";");
		writer.println( "			font-weight: bold;");
		writer.println( "		}");
		writer.println( "		.text {");
		writer.println( "			color: " + HighlightState.TEXT.getCol() + ";");
		writer.println( "		}");
//		writer.println( "		.number {");
//		writer.println( "			color: " + HighlightState.NUMBER.getCol() + ";");
//		writer.println( "		}");
		writer.println( "		.string {");
		writer.println( "			color: " + HighlightState.STRING.getCol() + ";");
		writer.println( "		}");
		writer.println( "		.ornament {");
		writer.println( "			color: " + HighlightState.ORNAMENT.getCol() + ";");
		writer.println( "		}");
		writer.println( "		.code_ornament {");
		writer.println( "			color: " + HighlightState.CODE_ORNAMENT.getCol() + ";");
		writer.println( "		}");
		writer.println( "		.codeword {");
		writer.println( "			color: " + HighlightState.CODEWORD.getCol() + ";");
		writer.println( "		}");
		writer.println( "		body {");
		writer.println( "			background-color: " + HighlightState.BACKGROUND.getCol() + ";");
		writer.println( "		}");
		writer.println( "	</style>");
		writer.println( "</head>");
		writer.println( "<body>");
		writer.println( "<pre>");

		Language lang = new LanguageJava();
//		Language lang = new Language();
		if(fileNameIn.endsWith(".java")) lang = new LanguageJava();
		if(fileNameIn.endsWith(".pde")) lang = new LanguageProcessing();
		
		in = lang.format(in);
		
		boolean blockCommented = false;
		boolean docCommented = false;
		String[] lines = in.split("\n");
		for(int lineNum = 0; lineNum < lines.length; lineNum++) {
			String line = lines[lineNum];
			boolean lineCommented = false;
			boolean inString = false;
			String[] words = line.split(" ");
			for(int wordNum = 0; wordNum < words.length; wordNum++) {
				String word = words[wordNum];
				
				String[] parts = word.split("[;()]");
				for(String s : parts) {
					if(s.equals("//")) {
						lineCommented = true;
					}
					if(s.equals("/**")) {
						docCommented = true;
					}
					else if(s.equals("/*")) {
						blockCommented = true;
					}
					if(s.equals("*/")) {
						blockCommented = false;
						docCommented = false;
					}
				}
				word = replaceOrnaments(word);
				writer.print(word);
				writer.print(" ");
			}
			
			
			writer.println();
		}
		
		writer.println("</pre>");
		writer.println("</body>");
		writer.println("</html>");
		
		writer.close();
	}
	
	public boolean isWhitespace(String str) {
		return str.trim().equals("");
	}
	public String escapeHTML(String str) {
//		str = str.replace("&", "&amp");
		str = str.replace("<", "&lt");
		str = str.replace(">", "&gt");
		return str;
	}
	public String getStr(Stack<Character> stck) {
		String s = "";
		while(!stck.empty()) {
			s+=stck.pop();
		}
		return new StringBuffer(s).reverse().toString();
	}
	public String makeClassedSpan(String clazz, String s) {
		return "<span class='" + clazz + "'>" + s + "</span>";
	}
	public String makeNumberSpan(int i) {
		return makeClassedSpan("number", i + "");
	}
	public String makeOrnamentSpan(String s) {
		return makeClassedSpan("ornament", s);
	}
	public boolean isOrnament(String s) {
		if(s.length() != 1) return false;
		char c = s.charAt(0);
		return c == ';' || c == '(' || c == ')' || c == '[' || c == ']' || c == '{'
				|| c == '}' || c == '.' || c == '+' || c == '-' || c == '*';
	}
	public String replaceOrnaments(String word) {

		word = word.replace(";", makeOrnamentSpan(";"));
		word = word.replace("(", makeOrnamentSpan("("));
		word = word.replace(")", makeOrnamentSpan(")"));
		word = word.replace("[", makeOrnamentSpan("["));
		word = word.replace("]", makeOrnamentSpan("]"));
		word = word.replace("{", makeOrnamentSpan("{"));
		word = word.replace("}", makeOrnamentSpan("}"));
		word = word.replace(".", makeOrnamentSpan("."));
		word = word.replace("+", makeOrnamentSpan("+"));
		word = word.replace("-", makeOrnamentSpan("-"));
		word = word.replace("*", makeOrnamentSpan("*"));
//		word = word.replace("/", makeOrnamentSpan("/"));
//		word = word.replace("^", makeOrnamentSpan("^"));
//		word = word.replace("=", makeOrnamentSpan("="));
		return word;
	}
	public boolean isCodeOrnament(String s) {
		return s.startsWith("@");
	}
	public String replaceNumbers(String word) {
		for(int i = 0; i < 10; i++) {
			word = word.replace(i + "", makeNumberSpan(i));
		}
		return word;
	}
	public final String highlightHTML(String source)
	{
		// from web
		// replaces elements with html code
	    source = source.replaceAll("<([^>/]*)/>", "&lt;~blue~$1~/~/&gt;");
	    source = source.replaceAll("<([^>]*)>", "&lt;~blue~$1~/~&gt;");
	    source = source.replaceAll("([\\w]+)=\"([^\"]*)\"", "~red~$1~/~~black~=\"~/~~green~$2~/~~black~\"~/~");
	    source = source.replaceAll("~([a-z]+)~", "<span style=\"color: $1;\">");
	    source = source.replace("~/~", "</span>");
	    return source;
	}
	
}
