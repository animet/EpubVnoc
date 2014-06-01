package Vnoc.Documents;

import java.util.ArrayList;
import java.util.Collection;

public class Document {

	String creator;
	String identifier;
	String language;
	int lineCount;
	ArrayList<Page> pages;
	String publisher;
	String rights;
	String title;
	String versionName;
	String versionNumber;
	
	public Document()
	{
		init();
	}
	private void init()
	{
		creator = "";
		identifier = "";
		language = "";
		lineCount=0;
		pages = new ArrayList<Page>();
		publisher = "";
		rights = "";
		title = "";
		versionName = "";
		versionNumber = "";
	}
	
	public void addPage(Page page)
	{
		pages.add(page);
		lineCount += page.getFormatedLineCount();
	}
	public void addPages(Collection<? extends Page> pages)
	{
		this.pages.addAll(pages);
		for(Page p : pages)
			lineCount += p.getFormatedLineCount();
	}
	
	public ArrayList<Page> getPages()
	{
		return pages;
	}
	
	public String getCreator()
	{
		return creator;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public int getLineCount()
	{
		return lineCount;
	}
	
	public String getPublisher()
	{
		return publisher;
	}
	
	public String getRights()
	{
		return rights;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getVersionName()
	{
		return versionName;
	}
	
	public String getVersionNumber()
	{
		return versionNumber;
	}
	
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	public void setIdentifier(String id)
	{
		this.identifier = id;
	}
	
	public void setLanguage(String lang)
	{
		this.language = lang;
	}
	
	public void setPublisher(String publ)
	{
		this.publisher = publ;
	}
	
	public void setRights(String rights)
	{
		this.rights = rights;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setVersionName(String name)
	{
		this.versionName = name;
	}
	
	public void setVersionNumber(String number)
	{
		this.versionNumber = number;
	}
}
