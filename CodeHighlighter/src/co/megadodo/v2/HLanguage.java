package co.megadodo.v2;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class HLanguage {
	
	// TODO: replaceKeywords, replaceCodewords IMPORTANTO

	public ArrayList<String> keywords;
	public ArrayList<String> codewords;
	public ArrayList<String> ornaments;
	
	public HLanguage() {
		 keywords = new ArrayList<String>();
		codewords = new ArrayList<String>();
		ornaments = new ArrayList<String>();
	}
	
	public String ornamentRegex() {
		String str = "[";
		
		for(String s : ornaments) {
			str += Pattern.quote(s);
		}
		
		return str + "]";
	}
	
	public boolean isKeyword(String str) {
		return keywords.contains(str);
	}
	
	public boolean isCodeword(String str) {
		return codewords.contains(str);
	}
	
	public boolean isKeywordNoOrn(String str) {
		return isKeyword(removeOrnaments(str));
	}
	
	public boolean isCodewordNoOrn(String str) {
		return isCodeword(removeOrnaments(str));
	}
	
	public boolean isOrnament(String str) {
		return ornaments.contains(str);
	}
	
	public String replaceKeywords(String str) {
		for(String s : keywords) {
			str = str.replace(s, HTMLGen.makeSpan(HState.KEYWORD, s));
		}
		return str;
	}
	
	public String replaceCodewords(String str) {
		for(String s : codewords) {
			str = str.replace(s, HTMLGen.makeSpan(HState.CODEWORD, s));
		}
		return str;
	}
	
	public String replaceKeywordsSpaced(String str) {
		String[] words = str.split(" ");
		for(int i=0;i<words.length;i++) {
			if(isKeyword(words[i])) words[i] = HTMLGen.makeSpan(HState.KEYWORD, words[i]);
		}
		String s = "";
		for(String string : words) s += string + " ";
		return s;
	}
	public String replaceCodewordsSpaced(String str) {
		String[] words = str.split(" ");
		for(int i=0;i<words.length;i++) {
			if(isCodeword(words[i])) words[i] = HTMLGen.makeSpan(HState.CODEWORD, words[i]);
		}
		String s = "";
		for(String string : words) s += string + " ";
		return s;
	}
	
	public String replaceOrnamentsSpaced(String str) {
		String[] words = str.split(" ");
		for(int i=0;i<words.length;i++) {
			if(isOrnament(words[i])) words[i] = HTMLGen.makeSpan(HState.ORNAMENT, words[i]);
		}
		String s = "";
		for(String string : words) s += string + " ";
		return s;
	}
	
	public String removeOrnaments(String str) {
		for(String o : ornaments) {
			str = str.replace(o, "");
		}
		return str;
	}
	
	public String replaceOrnaments(String str) {
		for(String s : ornaments) {
			str = str.replace(s, HTMLGen.makeSpan(HState.ORNAMENT, s));
		}
		return str;
	}
	
}
