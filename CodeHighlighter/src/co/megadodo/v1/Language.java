package co.megadodo.v1;

import java.util.ArrayList;

public class Language {
	
	public ArrayList<String> keywords = new ArrayList<String>();
	public ArrayList<String> codewords = new ArrayList<String>();
	
	public Language() {
		
	}
	
	public boolean isWord(String str) {
		return isCodeword(str) || isKeyword(str);
	}
	
	public boolean isCodeword(String str) {
		return codewords.contains(str);
	}
	
	public boolean isKeyword(String str) {
		return keywords.contains(str);
	}
	
	public boolean containsCodeword(String str) {
		for(String s : codewords) { 
			if(str.contains(s)) {
				return true;
			}
		}
		return false;
	}
	
	public String format(String str) {
		return str;
	}
	
	public boolean containsKeyword(String str) {
		for(String s : keywords) { 
			if(str.contains(s)) {
				return true;
			}
		}
		return false;
	}

}
