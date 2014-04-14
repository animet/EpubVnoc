package Vnoc.Creation.Epub;

import Vnoc.Documents.Document;
import Vnoc.Tags.Tag;
import Vnoc.Tags.TagDocument;

public class TocNcxCreation {

	Document doc;
	TagDocument tagDoc;
	Tag rootNcx;
	public TocNcxCreation(Document doc)
	{
		this.doc = doc;
		tagDoc = new TagDocument();
		rootNcx = new Tag("ncx");
		rootNcx.addAttribute("xmlns", "http://www.daisy.org/z3986/2005/ncx/");
		rootNcx.addAttribute("version", "2005-1");
	}
	
	public TagDocument getTocNcxTagDocument()
	{
		createMeta();
		createNavMap();
		tagDoc.addTag(rootNcx);
		return tagDoc;
	}
	
	private void createMeta()
	{
		tagDoc.addXmlVersionAndEncoding();
		tagDoc.addDOCTYPE(" ncx PUBLIC \"-//NISO//DTD ncx 2005-1//EN\"   \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\"");
		Tag head = new Tag("head");
			Tag id = new Tag("meta");
			id.addAttribute("name", "dtb:uid");
			id.addAttribute("content", doc.getIdentifier());
			Tag depth = new Tag("meta");
			depth.addAttribute("name", "dtb:depth");
			depth.addAttribute("content", String.valueOf(doc.getPages().size()));
			Tag totalPageCount = new Tag("meta");
			totalPageCount.addAttribute("name", "dtb:totalPageCount");
			totalPageCount.addAttribute("content", "0");
			Tag maxPageNumber = new Tag("meta");
			maxPageNumber.addAttribute("name", "dtb:maxPageNumber");
			maxPageNumber.addAttribute("content", "0");
		head.addChild(id);
		head.addChild(depth);
		head.addChild(totalPageCount);
		head.addChild(maxPageNumber);
		Tag title = new Tag("docTitle");
			Tag text = new Tag("text");
			text.setInnerText(doc.getTitle());
		title.addChild(text);
		rootNcx.addChild(head);
		rootNcx.addChild(title);
	}
	
	private void createNavMap()
	{
		Tag navMap = new Tag("navMap");
		for (int i = 0; i < doc.getPages().size(); i++)
		{
			Tag navPoint = new Tag("navPoint");
			navPoint.addAttribute("id", "navPoint-"+String.valueOf(i+1));
			navPoint.addAttribute("playOrder", String.valueOf(i+1));
				Tag navLabel = new Tag("navLabel");
					Tag text = new Tag("text");
					text.setInnerText("Page" + String.valueOf(i+1));
					navLabel.addChild(text);
				Tag content = new Tag("content");
				String pageFileName = "page" + getFourCharNumber(i + 1) + ".xhtml";
				content.addAttribute("src", "Text/"+pageFileName);
			navPoint.addChild(navLabel);
			navPoint.addChild(content);
			navMap.addChild(navPoint);
		}
		rootNcx.addChild(navMap);
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
