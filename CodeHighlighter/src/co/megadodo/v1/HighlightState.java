package co.megadodo.v1;

public enum HighlightState {

	COMMENT("#808080"),
//	TEXT("#FFFFFF"),
	TEXT("#000000"),
	KEYWORD("#C71585"),
	CODEWORD("#00C5CD"),
//	NUMBER("#FF00FF"),
	STRING("#0000FF"),
	CODE_ORNAMENT("#FF0000"),
	// semicolons, parens, brackets
//	BACKGROUND("#111111"),
	BACKGROUND("#EEEEEE"),
	ORNAMENT("#8B520C");
	
	String col;
	
	public String getCol() {
		return col;
	}
	
	HighlightState(String col) {
		this.col = col;
	}
	
}
