package Vnoc.Documents.Formatting;

import java.util.ArrayList;


public class FormatedPageText {

	protected ArrayList<String> lines;
	protected ArrayList<Integer> segmentCharacterLength;
	protected ArrayList<Float> segmentFontSizeInPx;
	protected ArrayList<String> segmentFontName;
	protected ArrayList<String> segmentFontStyle;
	protected ArrayList<String> segmentFontWeight;
	public FormatedPageText()
	{
		initMembers();
	}
	protected void initMembers()
	{
		lines = new ArrayList<String>();
		segmentCharacterLength = new ArrayList<Integer>();
		segmentFontSizeInPx = new ArrayList<Float>();
		segmentFontName = new ArrayList<String>();
		segmentFontStyle = new ArrayList<String>();
		segmentFontWeight = new ArrayList<String>();
	}
	
	public void addLine(String line)
	{
		lines.add(line);
	}
	public void addSegmentCharacterLength(int length)
	{
		segmentCharacterLength.add(length);
	}
	public void addSegmentFontSizeInPx(float size)
	{
		segmentFontSizeInPx.add(size);
	}
	public void addSegmentFontName(String name)
	{
		segmentFontName.add(name);
	}
	public void addSegmentFontStyle(String style)
	{
		this.segmentFontStyle.add(style);
	}
	public void addSegmentFontWeight(String weight)
	{
		this.segmentFontWeight.add(weight);
	}
	
	public void addStringToEndOfLine(int lineIndex, String str)
	{
		//Falls nur Bilder vorhanden sind:
		//if (!lines.contains("<p>"))
			//addEmptyLine();
		String oldLine = lines.get(lineIndex);
		int lineLengthToLineIndex = 0;
		for (int i = 0; i <= lineIndex; i++)
		{
			lineLengthToLineIndex += lines.get(i).length();
		}
		
		int segmentCharLength = 0;
		int countedSegmentIndex = 0;
		for (int i = 0; i < getSegmentSize(); i++)
		{
			if (segmentCharLength + segmentCharacterLength.get(i) < lineLengthToLineIndex)
				segmentCharLength += segmentCharacterLength.get(i);
			else
			{
				countedSegmentIndex = i;
				break;
			}
		}
		
		segmentCharacterLength.set(countedSegmentIndex, segmentCharacterLength.get(countedSegmentIndex) + str.length());
		lines.set(lineIndex, oldLine + str);
	}
	
	
	public String getLine(int index)
	{
		return lines.get(index);
	}
	public int getLineCount()
	{
		return lines.size();
	}
	
	public int getSegmentCharacterLength(int index)
	{
		return segmentCharacterLength.get(index);
	}
	
	public float getSegmentFontSizeInPx(int index)
	{
		return segmentFontSizeInPx.get(index);
	}
	
	public String getSegmentFontName(int index)
	{
		return segmentFontName.get(index);
	}
	
	public String getSegmentFontStyle(int index)
	{
		return segmentFontStyle.get(index);
	}
	
	public String getSegmentFontWeight(int index)
	{
		return segmentFontWeight.get(index);
	}
	
	public int getSegmentSize()
	{
		return segmentCharacterLength.size();
	}
	
	public void setSegmentCharacterLength(int index, int length)
	{
		segmentCharacterLength.set(index, length);
	}
	public void setSegmentFontSizeInPx(int index, float size)
	{
		segmentFontSizeInPx.set(index, size);
	}
	public void setSegmentFontName(int index, String name)
	{
		segmentFontName.set(index, name);
	}
	public void setSegmentFontStyle(int index, String style)
	{
		this.segmentFontStyle.set(index, style);
	}
	public void setSegmentFontWeight(int index, String weight)
	{
		this.segmentFontWeight.set(index, weight);
	}
	public void setLine(int index, String line)
	{
		lines.set(index, line);
	}
	
	public void replaceCharOfLineAt(int lineIndex, String str, int charIndex)
	{
		String oldLine = lines.get(lineIndex);
		int lineLengthToLineIndex = 0;
		for (int i = 0; i <= lineIndex; i++)
		{
			lineLengthToLineIndex += lines.get(i).length();
		}
		
		int segmentCharLength = 0;
		int countedSegmentIndex = -1;
		for (int i = 0; i < getSegmentSize(); i++)
		{
			if (segmentCharLength + segmentCharacterLength.get(i) < lineLengthToLineIndex)
				segmentCharLength += segmentCharacterLength.get(i);
			else
			{
				countedSegmentIndex = i;
				break;
			}
		}
		
		if (countedSegmentIndex > -1)
		{
			segmentCharacterLength.set(countedSegmentIndex, segmentCharacterLength.get(countedSegmentIndex) + str.length() - 1);
		}
		lines.set(lineIndex, oldLine.substring(0, charIndex) + str + oldLine.substring(charIndex + 1, oldLine.length()-1));
	}
	
	private void addEmptyLine()
	{
		lines.add("");
		segmentCharacterLength.add(0);
	}
}
