package co.megadodo.codehighlighter;

public class HThemeWebstormDefault extends HTheme {

	@Override
	public String getFontWeight(HState state) {
		if(state == HState.KEYWORD || state == HState.ORNAMENT) return "bold";
		return super.getFontWeight(state);
	}
	
	@Override
	public String getTab() {
		return "    ";
	}
	
	@Override
	public String getBackgroundColor() {
		return "#FFFFFF";
	}
	
	@Override
	public String getColor(HState state) {
		switch(state) {
//			case ORNAMENT	: return "#CC7832";
			case ORNAMENT	: return "#7A7A43";
//			case CODEWORD	: return "#CC7832";
			case CODEWORD	: return "#7A7A43";
			case COMMENT	: return "#808080";
			case KEYWORD	: return "#000080";
			case STRING		: return "#008000";
			case TEXT		: return "#000000";
			case JAVADOC	: return "#808080";
			case ANNOTATION	: return "#A0A0A0";
		}
		return "#FF0000"; // red = broken!
	}
	
}
