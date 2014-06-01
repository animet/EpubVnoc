package Vnoc.Creation.Epub;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import Vnoc.Documents.Document;
import Vnoc.Documents.Page;
import Vnoc.Documents.Formatting.FormatedPageText;
import Vnoc.Documents.Formatting.RawFormatedPageText;
import Vnoc.Documents.Formatting.TextSegment;
import Vnoc.Documents.Images.Image;
import Vnoc.Log.Logger;
import Vnoc.PDF.PdfImageAndTextExtraction;
import Vnoc.PDF.SimpleImageAndTextExtractionStrategy;

public class PdfToDocument {

	String path;
	PdfImageAndTextExtraction pdfTextExtraction;
	String separationRexEx;
	
	//PdfMetaDataExtraction
	public PdfToDocument(String path)
	{
		this.path = path;
		pdfTextExtraction = new PdfImageAndTextExtraction(path);
		separationRexEx = "\\" + String.valueOf((char)SimpleImageAndTextExtractionStrategy.TEXT_FORMAT_SEQUENCE_SEPARATION_CHAR);
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
		String[] rawPages = pdfTextExtraction.getRawPages();
		for(int i = 0; i < rawPages.length; i++)
		{
			Logger.addLogMessage("Begin processing raw page("+(i+1)+")");
			Page p = getPage(rawPages[i]);
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
		RawFormatedPageText rawFormatedText = new RawFormatedPageText();
		if (rawPage.length() > 1)
			text = rawPage.split(separationRexEx)[0];
		for(int i = 0; i < text.split("\\\n").length; i++)
		{
			rawFormatedText.addLine(text.split("\\\n")[i]);
		}
		for(int i = 2; i < rawPage.split(separationRexEx).length; i++)
		{
			String segment = rawPage.split(separationRexEx)[i];
			TextSegment textSegment = new TextSegment(Integer.valueOf(segment.split("\\:")[0])
						, Float.valueOf(segment.split("\\:")[4]), segment.split("\\:")[1]
						, segment.split("\\:")[2], segment.split("\\:")[3]);
			rawFormatedText.addTextSegment(textSegment);
		}
		page.formatedText = new FormatedPageText(rawFormatedText);
		return page;
	}
}
