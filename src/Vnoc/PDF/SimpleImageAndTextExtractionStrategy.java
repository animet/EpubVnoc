package Vnoc.PDF;

import java.io.IOException;
import java.util.ArrayList;

import Vnoc.Documents.Images.Image;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.pdf.parser.Vector;

public class SimpleImageAndTextExtractionStrategy extends SimpleTextExtractionStrategy{

	String textFormat;
	ArrayList<Float> baseLineY_Values;
	ArrayList<Image> images;
	public SimpleImageAndTextExtractionStrategy()
	{
		textFormat = "|";
		baseLineY_Values = new ArrayList<Float>();
		images = new ArrayList<Image>();
	}
	
	public ArrayList<Image> getImages()
	{
		return images;
	}
	
	public String getResultantText()
	{
		return super.getResultantText();
	}
	
	public String getWholeTextFormat()
	{
		CompressTextFormat(); //ohne Compress gehts
		return textFormat;
	}
	
	public void renderImage(ImageRenderInfo renderInfo)
	{
		try {
            PdfImageObject pdfImage = renderInfo.getImage();
            if (pdfImage == null) return;

            Image img = new Image(pdfImage.getImageAsBytes(), getImageLine(renderInfo.getStartPoint().get(1)), pdfImage.getFileType());
            images.add(img);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	
	float lastBaseY = -1;
	//textFormat: |CharacterLength:FontName:FontStyle:FontWeight:FontSize(Px)|
	public void renderText(TextRenderInfo renderInfo)
	{
		
		super.renderText(renderInfo);
		Vector curBaseLine = renderInfo.getBaseline().getStartPoint();
		if(lastBaseY != curBaseLine.get(1))
		{
			lastBaseY = curBaseLine.get(1);
			baseLineY_Values.add(curBaseLine.get(1));
		}
		Vector topRight = renderInfo.getAscentLine().getEndPoint();
		Rectangle rect = new Rectangle(curBaseLine.get(0), curBaseLine.get(1), topRight.get(0), topRight.get(1));
		
		String fontName = "";
		String fontStyle = tryToGetFontStyle(renderInfo.getFont().getPostscriptFontName());
		if (fontStyle.length() > 1)
			fontName = cutLastFontStyle_Weight(renderInfo.getFont().getPostscriptFontName());
		String fontWeight = tryToGetFontWeight(renderInfo.getFont().getPostscriptFontName());
		if (fontWeight.length() > 1)
			fontName = cutLastFontStyle_Weight(renderInfo.getFont().getPostscriptFontName());
		else if (fontName.equals(""))
			fontName = renderInfo.getFont().getPostscriptFontName();
		textFormat += renderInfo.getText().length() + ":" + fontName + ":" + fontStyle + ":" + fontWeight + ":" + rect.getHeight() + "|";
	}
	
	private void CompressTextFormat()
	{
		String compressedTextFormat = "|";
		String oldFontName = "";
		String oldFontStyle = "";
		String oldFontWeight = "";
		float oldFontSize = 0;
		int currentLength = 0;
		for(int i = 1; i < textFormat.split("\\|").length; i++)
		{
			String textSegment = textFormat.split("\\|")[i];
			boolean isEqual = true;
			if( !oldFontName.equals(textSegment.split("\\:")[1]) || oldFontSize != Float.valueOf(textSegment.split("\\:")[4]) 
					|| !oldFontStyle.equals(textSegment.split("\\:")[2]) || !oldFontWeight.equals(textSegment.split("\\:")[3]))
			{
				compressedTextFormat += currentLength + ":" + oldFontName + ":" + oldFontStyle + ":" + oldFontWeight + ":" + oldFontSize + "|";
				oldFontName = textSegment.split("\\:")[1];
				oldFontStyle = textSegment.split("\\:")[2];
				oldFontWeight = textSegment.split("\\:")[3];
				oldFontSize = Float.valueOf(textSegment.split("\\:")[4]);
				currentLength = Integer.valueOf(textSegment.split("\\:")[0]);
				isEqual = false;
			}
			
			if(isEqual)
				currentLength += Integer.valueOf(textSegment.split("\\:")[0]);
			
			if(i == textFormat.split("\\|").length - 1)
				compressedTextFormat += currentLength + ":" + oldFontName + ":" + oldFontStyle + ":" + oldFontWeight + ":" + oldFontSize + "|";
		}
		textFormat = compressedTextFormat;
	}
	
	private String cutLastFontStyle_Weight(String text)
	{
		return text.substring(0, text.length() - text.split("\\-")[text.split("\\-").length - 1].length() - 1);
	}
	
	private int getImageLine(float imageY_Position)
	{
		int lineIndex = 0;
		for(int i = 0; i < baseLineY_Values.size(); i++)
		{
			if (baseLineY_Values.get(i) < imageY_Position)
			{
				lineIndex = i;
				break;
			}
		}
		return lineIndex;
	}
	
	private String tryToGetFontStyle(String text)
	{
		String fontStyle = "";
		if (text.split("\\-")[text.split("\\-").length - 1].toUpperCase().equals("ITALIC"))
			fontStyle = "italic";
		else if (text.split("\\-")[text.split("\\-").length - 1].toUpperCase().equals("OBLIQUE"))
			fontStyle = "oblique";
		return fontStyle;
	}
	
	private String tryToGetFontWeight(String text)
	{
		String fontWeight = "";
		if (text.split("\\-")[text.split("\\-").length - 1].toUpperCase().equals("BOLD"))
			fontWeight = "bold";
		else if (text.split("\\-")[text.split("\\-").length - 1].toUpperCase().equals("BOLDER"))
			fontWeight = "bolder";
		else if (text.split("\\-")[text.split("\\-").length - 1].toUpperCase().equals("LIGHTER"))
			fontWeight = "lighter";
		
		return fontWeight;
	}
}

