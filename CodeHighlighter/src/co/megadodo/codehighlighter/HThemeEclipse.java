package co.megadodo.codehighlighter;

public class HThemeEclipse extends HTheme {

	@Override
	public String getFontWeight(HState state) {
		if(state == HState.KEYWORD) return "bold";
		return super.getFontWeight(state);
	}
	
	@Override
	public String getTab() {
		return "    ";
	}
	
	@Override
	public String getBackgroundColor() {
		return "#EEEEEE";
	}
	
	@Override
	public String getColor(HState state) {
		switch(state) {
			case ORNAMENT	: return "#8B520C";
			case CODEWORD	: return "#00C5CD";
			case COMMENT	: return "#808080";
			case KEYWORD	: return "#C71585";
			case STRING		: return "#0000FF";
			case TEXT		: return "#000000";
			case JAVADOC	: return "#98AFC7";
			case ANNOTATION : return "#646464";
		}
		return "#FF0000"; // red = broken!
	}
	
}
