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
		for(int i = 0; i < page.fText.getSegmentSize(); i++)
		{
			String fontSizePx = String.valueOf(page.fText.getSegmentFontSizeInPx(i));
			String fontFamily = page.fText.getSegmentFontName(i);
			String fontStyle = page.fText.getSegmentFontStyle(i);
			String fontWeight = page.fText.getSegmentFontWeight(i);
			writer.println(".style"+String.valueOf(i)+"{ font-family:" + fontFamily+";font-style:"+ fontStyle + ";font-weight:" +fontWeight+"; font-size:"+ fontSizePx +"px; "  + "}");
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
