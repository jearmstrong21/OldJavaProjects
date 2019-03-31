package co.megadodo.TokenizerHighlighter;

import java.util.ArrayList;

public class Tokenizer {
	
	public static void main(String[] args) {
		String code = "class Tokenizer {public static}";
		ArrayList<String> tokens = tokenize(code, new LanguageJava());
		for(String token : tokens) {
			System.out.println(token);
		}
	}
	
	public static ArrayList<String> tokenize(String code, Language lang) {
		ArrayList<String> list = new ArrayList<String>();
		String buffer = "";
		char ch;
		while(code.length() > 0) {
			ch = code.charAt(0);
			code = code.substring(1);
			if(ch == ' ') {
				
			}
		}
		return list;
	}

}
