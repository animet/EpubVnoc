package Vnoc.PDF;

import java.io.IOException;
import java.util.ArrayList;

import Vnoc.Documents.Images.Image;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

public class PdfImageAndTextExtraction {

	String[] rawPageTexts;
	ArrayList<ArrayList<Image>> images;
	public PdfImageAndTextExtraction(String sourcePath)
	{
		images = new ArrayList<ArrayList<Image>>();
		try {
			extractRawPagesAndImages(sourcePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ArrayList<Image>> getPageImages()
	{
		return images;
	}
	
	public String[] getRawPages()
	{
		return rawPageTexts;
	}
	
	private void extractRawPagesAndImages(String sourcePath) throws IOException
	{
		PdfReader reader = new PdfReader(sourcePath);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		SimpleImageAndTextExtractionStrategy strategy;
		String[] pages = new String[reader.getNumberOfPages()];
		for(int i = 1; i <= reader.getNumberOfPages(); i++)
		{
			strategy = parser.processContent(i, new SimpleImageAndTextExtractionStrategy());
			pages[i - 1] = strategy.getResultantText() + strategy.getWholeTextFormat();
			ArrayList<Image> pageImages = strategy.getImages();
			if(pageImages.size() > 0)
				images.add(pageImages);
		}
		reader.close();
		rawPageTexts = pages;
	}
}
