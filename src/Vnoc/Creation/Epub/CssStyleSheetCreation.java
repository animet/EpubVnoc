package Vnoc.Creation.Epub;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Vnoc.Documents.Page;

public class CssStyleSheetCreation {

	Page page;
	int pageNumber;
	public CssStyleSheetCreation(Page page, int pageNumber)
	{
		this.page = page;
		this.pageNumber = pageNumber;
	}
	
	public void createCssStyleSheet(String stylePath) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(stylePath+ "\\" + getCssStyleSheetFileName());
		int styleNumber = 0;
		for(int i = 0; i < page.formatedText.getFormatedLinesCount(); i++)
		{
			for (int k = 0; k < page.formatedText.getFormatedLine(i).getTextSegmentsCount(); k++)
			{
				String fontSizePx = String.valueOf(page.formatedText.getFormatedLine(i).getTextSegment(k).fontSizeInPx);
				String fontFamily = page.formatedText.getFormatedLine(i).getTextSegment(k).fontName;
				String fontStyle = page.formatedText.getFormatedLine(i).getTextSegment(k).fontStyle;
				String fontWeight = page.formatedText.getFormatedLine(i).getTextSegment(k).fontWeight;
				writer.println(".style"+String.valueOf(styleNumber)+"{ font-family:" + fontFamily+";font-style:"+ fontStyle + ";font-weight:" +fontWeight+"; font-size:"+ fontSizePx +"px; "  + "}");
				styleNumber++;
			}
		}
		writer.flush();
		writer.close();
	}
	
	public String getCssStyleSheetFileName()
	{
		return "page" + getFourCharNumber(pageNumber) + ".css";
	}
	
	
	private String getFourCharNumber(int number)
	{
		String strNum = String.valueOf(number);
		if(strNum.length() == 1)
			strNum = "000" + strNum;
		else if (strNum.length() == 2)
			strNum = "00" + strNum;
		else if (strNum.length() == 3)
			strNum = "0" + strNum;
		return strNum;
	}
	
}
