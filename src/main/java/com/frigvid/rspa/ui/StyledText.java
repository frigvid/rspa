package com.frigvid.rspa.ui;

/* TEMPORARY */
public class StyledText
{
	private String text;
	private boolean bold;
	private boolean italic;
	private boolean underline;
	private int fontSize;
	
	private static final String DELIMITER = ";;;";
	
	private static final boolean DEFAULT_BOLD = false;
	private static final boolean DEFAULT_ITALIC = false;
	private static final boolean DEFAULT_UNDERLINE = false;
	private static final int DEFAULT_FONT_SIZE = 12;
	
	public StyledText(String text) {
		this(text, DEFAULT_BOLD, DEFAULT_ITALIC, DEFAULT_UNDERLINE, DEFAULT_FONT_SIZE);
	}
	
	public StyledText(String text, boolean bold, boolean italic, boolean underline, int fontSize) {
		this.text = text;
		this.bold = bold;
		this.italic = italic;
		this.underline = underline;
		this.fontSize = fontSize;
	}
	
	public String getText() { return text; }
	public boolean isBold() { return bold; }
	public boolean isItalic() { return italic; }
	public boolean isUnderline() { return underline; }
	public int getFontSize() { return fontSize; }
	
	@Override
	public String toString() {
		// Serialize the object to a string representation
		return text + DELIMITER + bold + DELIMITER + italic + DELIMITER + underline + DELIMITER + fontSize;
	}
	
	public static StyledText fromString(String serialized) {
		String[] components = serialized.split(DELIMITER);
		if (components.length != 5) {
			throw new IllegalArgumentException("Invalid serialized StyledText format");
		}
		String text = components[0];
		boolean bold = Boolean.parseBoolean(components[1]);
		boolean italic = Boolean.parseBoolean(components[2]);
		boolean underline = Boolean.parseBoolean(components[3]);
		int fontSize = Integer.parseInt(components[4]);
		return new StyledText(text, bold, italic, underline, fontSize);
	}
}
