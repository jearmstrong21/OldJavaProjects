package co.megadodo.codehighlighter;

public class HThemeWebstormDarcula extends HTheme {

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
		return "#393939";
	}
	
	@Override
	public String getColor(HState state) {
		switch(state) {
//			case ORNAMENT	: return "#CC7832";
			case ORNAMENT	: return "#D7935A";
			case CODEWORD	: return "#9876AA";
			case COMMENT	: return "#808080";
			case KEYWORD	: return "#CC7832";
			case JAVADOC	: return "#6A8759";
			case TEXT		: return "#ADC4D0";
			case STRING		: return "#6A8759";
			case ANNOTATION	: return "#6897BB";
//			case JAVADOC	: return "#98AFC7";
		}
		return "#FF0000"; // red = broken!
	}
	
}
