package co.megadodo.TokenizerHighlighter;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Language {
	
	// TODO: replaceKeywords, replaceCodewords IMPORTANTO

	public ArrayList<String> keywords;
	public ArrayList<String> codewords;
	public ArrayList<String> ornaments;
	
	public Language() {
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
	public String removeOrnaments(String str) {
		for(String o : ornaments) {
			str = str.replace(o, "");
		}
		return str;
	}
}
