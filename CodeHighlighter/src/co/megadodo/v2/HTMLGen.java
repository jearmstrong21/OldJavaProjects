package co.megadodo.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HTMLGen {

	public static String makeSpan(HState state, String word) {
		return "<span class='" + state.getCSSClass() + "'>" + word + "</span>";
	}
	public static HTMLGen makeGen(String fileName) throws FileNotFoundException {
		return makeGen(new File(fileName));
	}
	public static String quoteHTML(String str) {
		str = str.replace("&", "&amp");
		str = str.replace("<", "&lt");
		str = str.replace(">", "&gt");
		return str;
	}
	public static HTMLGen makeGen(File file) {
		HTMLGen gen = new HTMLGen();
		gen.inputFile = file;
		return gen;
	}
	protected String getInStr() {
		assert inputFile != null;
		Scanner sc;
		try {
			sc = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		String str = "";
		while(sc.hasNextLine()) {
			str += sc.nextLine() + "\n";
		}
		sc.close();
		return str;
	}
	
	protected HTheme theme;
	protected HLanguage lang;
	protected HCSSGenerator cssGen;
	protected File inputFile;
	protected String html;
	
	public HTMLGen() {
		this(new HTheme(), new HLanguage());
	}
	public HTMLGen(HTheme theme, HLanguage lang) {
		this(theme, lang, new HCSSGenerator(theme));
	}
	public HTMLGen(HTheme theme, HLanguage lang, HCSSGenerator cssGen) {
		this.theme = theme;
		this.lang = lang;
		this.cssGen = cssGen;
	}
	public HTheme getTheme() {
		return theme;
	}
	public HLanguage getLang() {
		return lang;
	}
	public HCSSGenerator getCssGen() {
		return cssGen;
	}
	public File getInputFile() {
		return inputFile;
	}
	public void setTheme(HTheme theme) {
		this.theme = theme;
	}
	public void setLang(HLanguage lang) {
		this.lang = lang;
	}
	public void setCssGen(HCSSGenerator cssGen) {
		this.cssGen = cssGen;
	}
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	public void smartSetLang() {
		assert inputFile != null;
		String path = inputFile.getName();
		if(path.endsWith(".java")) {
			lang = new HLanguageJava();
		}
		else {
			lang = new HLanguage();
		}
	}
	public String genHTML() {
		assert inputFile != null;
		html = "";
		
		boolean   docCommented = false;
		boolean blockCommented = false;
		String input = getInStr().replace("\t", theme.getTab());
		String[] lines = input.split("\n");
		for(int lineNum = 0; lineNum < lines.length; lineNum++) {
			String line = lines[lineNum];
			
			String[] words = line.split(" ");
			boolean inString = false;
			boolean lineCommented = false;
			for(int wordNum = 0; wordNum < words.length; wordNum++) {
				String word = quoteHTML(words[wordNum]);
				final String origWord = words[wordNum];
				
				if(word.trim().equals("/**")) {
					docCommented = true;
				} else if(word.trim().equals("/*")) {
					blockCommented = true;
				} else if(word.startsWith("\"") && !inString) {
					inString = true;
				} else if(origWord.trim().startsWith("//")) {
					lineCommented = true;
				}
				
				if(word.contains("@") && !inString) {
					word = makeSpan(HState.ANNOTATION, word);
				} else if(!blockCommented && !docCommented && !lineCommented && !inString) {
				
					if(lang.isKeywordNoOrn(word.trim())) word = makeSpan(HState.KEYWORD, word);
					else if(lang.isCodewordNoOrn(word.trim())) word = makeSpan(HState.CODEWORD, word);
					else word = makeSpan(HState.TEXT, word);
				
					word = lang.replaceOrnaments(word);
				} else if(docCommented) {
					word = makeSpan(HState.JAVADOC, word);
				} else if(blockCommented || lineCommented) {
					word = makeSpan(HState.COMMENT, word);
				} else if(inString) {
					word = makeSpan(HState.STRING, word);
				}
				
				if( (blockCommented || docCommented) && origWord.trim().equals("*/")) {
					blockCommented = false;
					docCommented = false;
				}
				if(origWord.endsWith("\"") && inString) {
					inString = false;
				}
				html += word;
				html += " ";
			}
			
			html += "\n";
		}
		
		return html;
	}
	public String genHTMLToFile(String title) {
		String str = "\n<!DOCTYPE html>";
			   str+= "\n<html>";
			   str+= "\n	<head>";
			   str+= "\n		<title>" + title + "</title>";
			   str+= "\n 		<style>";
			   str+= "\n" + cssGen.genCSS();
			   str+= "\n		</style>";
			   str+= "\n	</head>";
			   str+= "\n	<body>";
			   str+= "\n		<pre class='CODE_HIGHLIGHT_PRE'>";
			   str+= "\n" + genHTML();
			   str+= "\n		</pre>";
			   str+= "\n	</body>";
			   str+= "\n</html>";
			   
		return str;
	}
	public void writeHTMLToFile(PrintWriter writer, String title) {
		writer.println(genHTMLToFile(title));
	}
	
}
