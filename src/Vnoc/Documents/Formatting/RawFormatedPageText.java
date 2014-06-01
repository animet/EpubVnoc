package Vnoc.Documents.Formatting;

import java.util.ArrayList;


public class RawFormatedPageText {

	protected ArrayList<String> lines;
	protected ArrayList<TextSegment> textSegments;
	public RawFormatedPageText()
	{
		initMembers();
	}
	protected void initMembers()
	{
		lines = new ArrayList<String>();
		textSegments = new ArrayList<TextSegment>();
	}
	
	public void addLine(String line)
	{
		lines.add(line);
	}
	public void addTextSegment(TextSegment textSegment)
	{
		textSegments.add(textSegment);
	}
	
	public String getLine(int index)
	{
		return lines.get(index);
	}
	public int getLineCount()
	{
		return lines.size();
	}
	
	public TextSegment getTextSegment(int index)
	{
		return textSegments.get(index);
	}
	
	public int getTextSegmentCount()
	{
		return textSegments.size();
	}
	
	public void setTextSegment(int index, TextSegment textSegment)
	{
		textSegments.set(index, textSegment);
	}
}
