package Vnoc.Documents.Formatting;

import java.util.ArrayList;

public class FormatedLine {

	String text;
	ArrayList<TextSegment> textSegments;
	public FormatedLine(String text, ArrayList<TextSegment> textSegments)
	{
		this.text = text;
		this.textSegments = textSegments;
	}
	
	public void addTextAfter(String text, int charIndex)
	{
		int textSegmentLength = 0;
		for (int i = 0; i < textSegments.size(); i++)
		{
			if (textSegmentLength + textSegments.get(i).characterLength - 1 >= charIndex)
			{
				TextSegment txtSegment = textSegments.get(i);
				txtSegment.characterLength += text.length();
				textSegments.set(i, txtSegment);
				String textCopy = new String(this.text);
				this.text = this.text.substring(0, charIndex + 1) + text;
				if (charIndex < (textCopy.length() - 1))
					this.text += textCopy.substring(charIndex + 1, this.text.length() - 1);
				break;
			}
			
			textSegmentLength += textSegments.get(i).characterLength;
		}
	}
	
	public void addTextSegment(TextSegment textSegment)
	{
		textSegments.add(textSegment);
	}
	
	public String getText()
	{
		return text;
	}
	
	public TextSegment getTextSegment(int index)
	{
		return textSegments.get(index);
	}
	
	public ArrayList<TextSegment> getTextSegments()
	{
		return textSegments;
	}
	
	public int getTextSegmentsCount()
	{
		return textSegments.size();
	}
	
	public int length()
	{
		return text.length();
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
}
