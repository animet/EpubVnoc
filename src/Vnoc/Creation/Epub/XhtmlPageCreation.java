package Vnoc.Creation.Epub;

import java.io.FileNotFoundException;








import javax.imageio.ImageIO;

import Vnoc.Documents.Document;
import Vnoc.Documents.Page;
import Vnoc.Enums.Creation.FormatMode;
import Vnoc.HTML.SignConverter;
import Vnoc.Tags.Tag;
import Vnoc.Tags.TagDocument;


public class XhtmlPageCreation {
	
	final String CUSTOM_LINE_BREAK = ";|nl|;";
	final String CUSTOM_LINE_BREAK_REGEX  = "\\;\\|nl\\|\\;";
	final String HTML_LINE_BREAK = "<br />";
	
	protected CssStyleSheetCreation cssStyleSheetCreation;
	protected Document doc;
	protected TagDocument tagDoc;
	Tag html;
	Tag body;
	
	public XhtmlPageCreation(Document doc)
	{
		this.doc = doc;
		initMembers();
	}
	protected void initMembers()
	{
		tagDoc = new TagDocument();
		html = new Tag("html");
		body = new Tag("body");
	}
	
	public void createXhtmlPages(String outputPath, FormatMode formatMode) throws FileNotFoundException
	{
		for (int i = 0; i < doc.getPages().size(); i++)
		{
			createXhtmlPage(doc.getPages().get(i), i+1, outputPath+"OEBPS", formatMode);
		}
	}
	
	protected void createXhtmlPage(Page page, int pageNumber, String OEBPSPath, FormatMode formatMode) throws FileNotFoundException
	{
		createXhtmlPageHead(OEBPSPath, page, pageNumber);
		Tag mainP = new Tag("p");
		
		if(formatMode.equals(FormatMode.DoLineBreaks) || formatMode.equals(FormatMode.DoSpacesPlaceOfLineBreaks))
			page = getFormatedPage(page, formatMode);
		
		page = getPageWithHtmlImages(page);
		
		String pageText = getPageText(page);
		int segmentIndexStartLength = 0;
		int segmentIndexEndLength = 0;
		for (int i = 0; i < page.fText.getSegmentSize(); i++)
		{
			Tag span = new Tag("span");
			span.addAttribute("class", "style" + String.valueOf(i));
			segmentIndexEndLength += page.fText.getSegmentCharacterLength(i);
			String textSegment = SignConverter.getHtmlFormatedText(pageText.substring(segmentIndexStartLength, segmentIndexEndLength));
			textSegment = textSegment.replace("&<noHtml;", "");
			textSegment = textSegment.replace("&noHtml>;", "");
			
			if(formatMode.equals(FormatMode.DoLineBreaks))
				textSegment = textSegment.replaceAll(CUSTOM_LINE_BREAK_REGEX, HTML_LINE_BREAK);
			
			span.setInnerText(textSegment);
			segmentIndexStartLength += page.fText.getSegmentCharacterLength(i);
			mainP.addChild(span);
		}
		body.addChild(mainP);
		html.addChild(body);
		tagDoc.addTag(html);
		tagDoc.save(OEBPSPath + "\\Text\\page" + getFourCharNumber(pageNumber) + ".xhtml");
	}
	
	private void createXhtmlPageHead(String OEBPSPath, Page page, int pageNumber) throws FileNotFoundException
	{
		initMembers();
		cssStyleSheetCreation = new CssStyleSheetCreation(page, pageNumber);
		cssStyleSheetCreation.createCssStyleSheet(OEBPSPath + "\\Styles");
		
		tagDoc.addDOCTYPE("html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\"");
		tagDoc.addXmlVersionAndEncoding();
		
		html.addAttribute("xmlns", "http://www.w3.org/1999/xhtml");
			Tag head = new Tag("head");
				Tag title = new Tag("title");
				title.setInnerText("Page " + String.valueOf(pageNumber));
				Tag link1 = new Tag("link");
				link1.addAttribute("rel", "stylesheet");
				link1.addAttribute("href", "../Styles/"+cssStyleSheetCreation.getCssStyleSheetFileName());
				link1.addAttribute("type", "text/css");
				Tag link2 = new Tag("link");
				link2.addAttribute("rel", "stylesheet");
				link2.addAttribute("href", "../Styles/page-template.xpgt");
				link2.addAttribute("type", "application/vnd.adobe-page-template+xml");
			head.addChild(title);
			head.addChild(link1);
			head.addChild(link2);
		html.addChild(head);
	}
	
	private Page getFormatedPage(Page page, FormatMode formatMode)
	{
		Page fPage = page;
		String lineBreak = "";
		if(formatMode.equals(FormatMode.DoLineBreaks))
			lineBreak = CUSTOM_LINE_BREAK;
		else if (formatMode.equals(FormatMode.DoSpacesPlaceOfLineBreaks))
			lineBreak = " ";
		
		for(int i = 0; i < page.fText.getLineCount(); i++)
		{
			page.fText.addStringToEndOfLine(i, lineBreak);
		}
		return fPage;
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
	
	private String getPageText(Page page)
	{
		String text = "";
		for(int i = 0; i < page.fText.getLineCount(); i++)
		{
			text += page.fText.getLine(i); //+ " ";
		}
		return text;
	}
	
	private Page getPageWithHtmlImages(Page page)
	{
		for(int i = 0; i < page.getImageCount(); i++)
		{
			String imageString = SignConverter.NO_HTML_SIGN_CONVERT_BEGIN + "<div style=\"text-align:center;\"><img alt=\"\" src=\"../Images/"+page.getImage(i).getFileName()+"."+page.getImage(i).getFileExtension()+"\" /></div><br />" + SignConverter.NO_HTML_SIGN_CONVERT_END;
			page.fText.addStringToEndOfLine(page.getImage(i).getLineIndex(), imageString);
		}
		return page;
	}
	
}
