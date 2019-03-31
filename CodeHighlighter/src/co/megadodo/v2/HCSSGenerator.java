package co.megadodo.v2;

public class HCSSGenerator {

	private HTheme theme;
	
	public HCSSGenerator() {
		this(new HThemeEclipse());
	}
	
	public HCSSGenerator(HTheme theme) {
		this.theme = theme;
	}
	
	public String genCSS() {
		return genCSS(false);
	}
	
	public String genCSS(boolean inHTML) {
		String str = "";
		if(inHTML) {
			str = "<style>\n";
		}
		
		for(HState state : HState.values()) {
			str += "." + state.getCSSClass() + " {\n";
			str += "\tcolor: " + theme.getColor(state) + ";\n";
			str += "\tfont-weight: " + theme.getFontWeight(state) + ";\n";
			str += "}\n";
		}
		
		str += ".CODE_HIGHLIGHT_PRE {";
		str += "\tbackground-color: " + theme.getBackgroundColor() + ";\n";
		str += "\tpadding-left: 10px;\n";
		str += "\tpadding-top: 10px;\n";
		str += "}";
		
		if(inHTML) {
			str += "</style>";
		}
		return str;
	}
	
}
