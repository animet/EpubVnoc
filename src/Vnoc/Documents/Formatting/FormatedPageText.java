package Vnoc.Documents.Formatting;

import java.util.ArrayList;


public class FormatedPageText {

	ArrayList<FormatedLine> formatedLines;
	public FormatedPageText(RawFormatedPageText rawFormatedPageText)
	{
		formatedLines = new ArrayList<FormatedLine>();
		convertRawFormatedPageText(rawFormatedPageText);
	}
	
	public FormatedLine getFormatedLine(int index)
	{
		return formatedLines.get(index);
	}
	
	public int getFormatedLinesCount()
	{
		return formatedLines.size();
	}
	
	public void setFormatedLine(int index, FormatedLine formatedLine)
	{
		formatedLines.set(index, formatedLine);
	}
	
	public int getLineCount()
	{
		return formatedLines.size();
	}
	
	protected void convertRawFormatedPageText(RawFormatedPageText rawFormatedPageText)
	{
		int startTextSegmentIndex = 0;
		for (int i = 0; i < rawFormatedPageText.getLineCount(); i++)
		{
			int lineLength = rawFormatedPageText.getLine(i).length();
			int usedLineChars = 0;
			ArrayList<TextSegment> lineTextSegments = new ArrayList<TextSegment>();
			for (int k = startTextSegmentIndex; k < rawFormatedPageText.getTextSegmentCount(); k++)
			{
				TextSegment textSegment = rawFormatedPageText.getTextSegment(k);
				int charLength = textSegment.characterLength;
				if (usedLineChars + charLength <= lineLength)
				{
					lineTextSegments.add(textSegment);
					usedLineChars += charLength;
					if (usedLineChars == lineLength)
					{
						startTextSegmentIndex = k + 1;
						break;
					}
				}
				else
				{
					int availableLineCharLength = lineLength - usedLineChars;
					int remainingTextSegmentCharLength = textSegment.characterLength - availableLineCharLength;
					TextSegment newLineTextSegment = new TextSegment(availableLineCharLength, textSegment.fontSizeInPx, textSegment.fontName, textSegment.fontStyle, textSegment.fontWeight);
					TextSegment remainingTextSegment = new TextSegment(remainingTextSegmentCharLength, textSegment.fontSizeInPx, textSegment.fontName, textSegment.fontStyle, textSegment.fontWeight);
					lineTextSegments.add(newLineTextSegment);
					rawFormatedPageText.setTextSegment(k, remainingTextSegment);
					startTextSegmentIndex = k;
					break;
				}
			}
			FormatedLine formatedLine = new FormatedLine(rawFormatedPageText.getLine(i), lineTextSegments);
			this.formatedLines.add(formatedLine);
		}
	}
}
