package Vnoc.Creation.Epub;

import Vnoc.Documents.Document;
import Vnoc.Tags.TagDocument;

public class TableOfContentCreation {

	Document doc;
	String outputPath;
	public TableOfContentCreation(Document doc, String outputPath)
	{
		this.doc = doc;
		this.outputPath = outputPath;
	}
	
	public void create()
	{
		ContentOpfCreation contenOpfCreation = new ContentOpfCreation(doc);
		TagDocument contentOpf = contenOpfCreation.getContentOpfTagDocument();
		contentOpf.save(outputPath+"OEBPS\\content.opf");
		
		TocNcxCreation tocNcxCreation = new TocNcxCreation(doc);
		TagDocument tocNcx = tocNcxCreation.getTocNcxTagDocument();
		tocNcx.save(outputPath+"OEBPS\\toc.ncx");
	}
}
