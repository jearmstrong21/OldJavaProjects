package co.megadodo.codehighlighter;

public enum HState {

	COMMENT,
	KEYWORD,
	CODEWORD,
	JAVADOC,
	TEXT,
	STRING,
	ORNAMENT,
	ANNOTATION;

	public String getCSSClass() {
		return "CODE_HIGHLIGHT_" + name();
	}
	
}
