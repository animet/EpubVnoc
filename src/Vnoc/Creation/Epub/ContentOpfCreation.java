package Vnoc.Creation.Epub;


import Vnoc.Documents.Document;
import Vnoc.Documents.Page;
import Vnoc.Tags.Tag;
import Vnoc.Tags.TagAttribute;
import Vnoc.Tags.TagDocument;

public class ContentOpfCreation {

	Document doc;
	TagDocument tagDoc;
	Tag rootPackage;
	public ContentOpfCreation(Document doc)
	{
		this.doc = doc;
		tagDoc = new TagDocument();
		rootPackage = new Tag("package");
		rootPackage.addAttribute(new TagAttribute("xmlns", "http://www.idpf.org/2007/opf"));
		rootPackage.addAttribute(new TagAttribute("unique-identifier", "BookID"));
		rootPackage.addAttribute(new TagAttribute("version", "2.0"));
		
	}
	
	public TagDocument getContentOpfTagDocument()
	{
		createMetaData();
		createManifest();
		createSpine();
		tagDoc.addTag(rootPackage);
		return tagDoc;
	}
	
	private void createMetaData()
	{
		tagDoc.addXmlVersionAndEncoding();
		Tag metadata = new Tag("metadata");
		metadata.addAttribute("xmlns:dc", "http://purl.org/dc/elements/1.1/");
		metadata.addAttribute("xmlns:opf", "http://www.idpf.org/2007/opf");
			Tag title = new Tag("dc:title");
			title.setInnerText(doc.getTitle());
			Tag language = new Tag("dc:language");
			language.setInnerText(doc.getLanguage());
			Tag rights = new Tag("dc:rights");
			rights.setInnerText(doc.getRights());
			Tag creator = new Tag("dc:creator");
			creator.addAttribute("opf:role", "aut");
			creator.setInnerText(doc.getCreator());
			Tag publisher = new Tag("dc:publisher");
			publisher.setInnerText(doc.getPublisher());
			Tag id = new Tag("dc:identifier");
			id.addAttribute("id", "BookID");
			id.addAttribute("opf:scheme", "UUID");
			id.setInnerText(doc.getIdentifier());
			Tag meta = new Tag("meta");
			meta.addAttribute("name", doc.getVersionName());
			meta.addAttribute("content", doc.getVersionNumber());
		metadata.addChild(title);
		metadata.addChild(language);
		metadata.addChild(rights);
		metadata.addChild(creator);
		metadata.addChild(publisher);
		metadata.addChild(id);
		metadata.addChild(meta);
		rootPackage.addChild(metadata);
	}
	
	private void createManifest()
	{
		Tag manifest = new Tag("manifest");
			Tag itemNcx = new Tag("item");
			itemNcx.addAttribute("id", "ncx");
			itemNcx.addAttribute("href", "toc.ncx");
			itemNcx.addAttribute("media-type", "application/x-dtbncx+xml");
			//Images
			Tag itemxpgt = new Tag("item");
			itemxpgt.addAttribute("id", "page-template.xpgt");
			itemxpgt.addAttribute("href", "Styles/page-template.xpgt");
			itemxpgt.addAttribute("media-type", "application/vnd.adobe-page-template+xml");
			Tag itemStyleCss = new Tag("item");
			itemStyleCss.addAttribute("id", "stylesheet.css");
			itemStyleCss.addAttribute("href", "Styles/stylesheet.css");
			itemStyleCss.addAttribute("media-type", "text/css");
		manifest.addChild(itemNcx);
		manifest.addChild(itemxpgt);
		manifest.addChild(itemStyleCss);
			for(int i = 0; i < doc.getPages().size(); i++)
			{
				String pageFileName = "page" + getFourCharNumber(i + 1) + ".xhtml";
				Tag item = new Tag("item");
				item.addAttribute("id", pageFileName);
				item.addAttribute("href", "Text/" + pageFileName);
				item.addAttribute("media-type", "application/xhtml+xml");
				manifest.addChild(item);
			}
			for (int i = 0; i < doc.getPages().size(); i++)
			{
				Page p = doc.getPages().get(i);
				for(int j = 0; j < p.getImageCount(); j++)
				{
					String imgFileNameAndExtension = p.getImage(j).getFileName() + "." + p.getImage(j).getFileExtension();
					Tag item = new Tag("item");
					item.addAttribute("id", imgFileNameAndExtension);
					item.addAttribute("href", "Images/" + imgFileNameAndExtension);
					item.addAttribute("media-type", "image/" + p.getImage(j).getFileExtension());
					manifest.addChild(item);
				}
			}
			rootPackage.addChild(manifest);
	}
	
	private void createSpine()
	{
		Tag spine = new Tag("spine");
		spine.addAttribute("toc", "ncx");
		for(int i = 0; i < doc.getPages().size(); i++)
		{
			String pageFileName = "page" + getFourCharNumber(i + 1) + ".xhtml";
			Tag itemref = new Tag("itemref");
			itemref.addAttribute("idref", pageFileName);
			spine.addChild(itemref);
		}
		rootPackage.addChild(spine);
		
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
