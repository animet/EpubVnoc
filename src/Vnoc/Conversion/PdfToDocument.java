package Vnoc.Conversion;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import Vnoc.Documents.Document;
import Vnoc.Documents.Page;
import Vnoc.Documents.Images.Image;
import Vnoc.PDF.PdfImageAndTextExtraction;
import Vnoc.PDF.SimpleImageAndTextExtractionStrategy;

public class PdfToDocument {

	String path;
	PdfImageAndTextExtraction pdfTextExtraction;
	//PdfMetaDataExtraction
	public PdfToDocument(String path)
	{
		this.path = path;
		pdfTextExtraction = new PdfImageAndTextExtraction(path);
		
	}
	
	public Document getDocument() throws IOException
	{
		Document doc = new Document();
		doc.addPages(getPages());
		//add MetaData
		return doc;
	}
	
	private ArrayList<Page> getPages() throws IOException
	{
		ArrayList<Page> pages = new ArrayList<Page>();
		for(String s : pdfTextExtraction.getRawPages())
		{
			Page p = getPage(s);
			pages.add(p);
		}
		for (int i = 0; i < pdfTextExtraction.getPageImages().size(); i++)
		{
			Page p = pages.get(i);
			p.addImages(pdfTextExtraction.getPageImages().get(i));
		}
		return pages;
	}
	
	private Page getPage(String rawPage)
	{
		return processRawPage(rawPage);
	}
	
	private Page processRawPage(String rawPage)
	{
		Page page = new Page();
		String text = "";
		if (rawPage.length() > 1)
			text = rawPage.split("\\|")[0];
		for(int i = 0; i < text.split("\\\n").length; i++)
		{
			page.fText.addLine(text.split("\\\n")[i]);
		}
		for(int i = 2; i < rawPage.split("\\|").length; i++)
		{
			String segment = rawPage.split("\\|")[i];
			page.fText.addSegmentCharacterLength(Integer.valueOf(segment.split("\\:")[0]));
			page.fText.addSegmentFontName(segment.split("\\:")[1]);
			page.fText.addSegmentFontStyle(segment.split("\\:")[2]);
			page.fText.addSegmentFontWeight(segment.split("\\:")[3]);
			page.fText.addSegmentFontSizeInPx(Float.valueOf(segment.split("\\:")[4]));
		}
		return page;
	}
}
