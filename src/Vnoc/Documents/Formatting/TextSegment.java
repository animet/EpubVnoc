package Vnoc.Documents.Formatting;

public class TextSegment {

	public int characterLength = 0;
	public float fontSizeInPx = 0;
	public String fontName = "";
	public String fontStyle = "";
	public String fontWeight = "";
	public TextSegment(int characterLength, float fontSizeInPx, String fontName, String fontStyle, String fontWeight)
	{
		this.characterLength = characterLength;
		this.fontSizeInPx = fontSizeInPx;
		this.fontName = fontName;
		this.fontStyle = fontStyle;
		this.fontWeight = fontWeight;
	}
}
