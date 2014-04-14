package Vnoc.Tags;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TagDocument {

	final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	final String TAB = "\t";
	boolean isXmlVersionSet;
	ArrayList<Tag> tags;
	String saveString;
	public TagDocument()
	{
		isXmlVersionSet = false;
		tags = new ArrayList<Tag>();
		saveString = "";
	}
	
	public void addDOCTYPE(String content)
	{
		if(isXmlVersionSet)
			removeXmlVersionAndEncoding();
		saveString = "<!DOCTYPE " + content + " >" + saveString;
		addXmlVersionAndEncoding();
	}
	
	public void addTag(Tag tag)
	{
		tags.add(tag);
	}
	
	public boolean addXmlVersionAndEncoding()
	{
		if (!isXmlVersionSet)
		{
			saveString = XML_VERSION + "\n" + saveString;
			isXmlVersionSet = true;
			return true;
		}
		else
			return false;
	}
	
	public boolean removeXmlVersionAndEncoding()
	{
		if(isXmlVersionSet)
		{
			String tmpStr = "";
			for(int i = 1; i < saveString.split("\\\n").length; i++)
			{
				tmpStr += saveString.split("\\\n")[i];
			}
			saveString = tmpStr;
			return true;
		}
		else
			return false;
	}
	
	public void save(String path)
	{
		for(Tag t : tags)
		{
			saveString += t.getTagFamilyToString();
		}
		try {
			PrintWriter writer = new PrintWriter(path);
			writer.println(saveString);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
