package co.megadodo.v2;

public enum HState {

	COMMENT,
	KEYWORD,
	CODEWORD,
	JAVADOC,
	TEXT,
	STRING,
	ORNAMENT,
	ANNOTATION,
	LINK;			// https://extension1.extension2.(....).extensionn.sitename/anythingwithoutspaces

	public String getCSSClass() {
		return "CODE_HIGHLIGHT_" + name();
	}
	
}
