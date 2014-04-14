package Vnoc.Tags;

import java.util.ArrayList;

public class Tag {

	//Public
	//Protected
	//Private
	static final String FIRST_OPEN_BRACKET = "<";
	static final String ONE_PRT_CLOSE_BRACKET = "/>";
	static final String TWO_PRT_1ST_SHUT_BRACKET = ">";
	static final String TWO_PRT_2ND_OPEN_BRACKET = "</";
	static final String TWO_PRT_2ND_SHUT_BRACKET = ">";
	static final String TAB = "\t";
	ArrayList<TagAttribute> attributes;
	ArrayList<Tag> children;
	int depthLevel;
	String innerText;
	String name;
	String stringTag;
	public Tag(String name)
	{
		Init();
		this.name = name;
	}
	private void Init()
	{
		attributes = new ArrayList<TagAttribute>();
		children = new ArrayList<Tag>();
		depthLevel = 0;
		innerText = "";
		stringTag = "";
	}
	
	//Public
	public void addAttribute(TagAttribute attrib)
	{
		attributes.add(attrib);
	}
	public void addAttribute(String name, String value)
	{
		attributes.add(new TagAttribute(name, value));
	}
	public void addChild(Tag tag)
	{
		tag.setDepthLevel(depthLevel + 1);
		children.add(tag);
	}
	public ArrayList<TagAttribute> getAttributes()
	{
		return attributes;
	}
	public Tag getChild(int index)
	{
		return children.get(index);
	}
	public ArrayList<Tag> getChildren()
	{
		return children;
	}
	public String getInnerText()
	{
		return innerText;
	}
	public String getName()
	{
		return name;
	}
	public String getSingleTagToString()
	{
		String singleStringTag = FIRST_OPEN_BRACKET + name + getAttributesToString();
		
		if (innerText.equals(""))
		{
			singleStringTag += " " + ONE_PRT_CLOSE_BRACKET;
		}
		else
		{
			singleStringTag += TWO_PRT_1ST_SHUT_BRACKET + innerText + TWO_PRT_2ND_OPEN_BRACKET
						+ name + TWO_PRT_2ND_SHUT_BRACKET;
		}
		return singleStringTag;
	}
	
	public String getTagFamilyToString()
	{
		stringTag = getTabs() + FIRST_OPEN_BRACKET + name + getAttributesToString();
		
		if (innerText.equals(""))
		{
			if (children.size() == 0)
				stringTag += " " + ONE_PRT_CLOSE_BRACKET;
			else
			{
				stringTag += TWO_PRT_1ST_SHUT_BRACKET + "\n";
				appendChildren();
				stringTag += getTabs() + TWO_PRT_2ND_OPEN_BRACKET + name + TWO_PRT_2ND_SHUT_BRACKET + "\n";
			}
		}
		else
		{
			stringTag += TWO_PRT_1ST_SHUT_BRACKET + innerText + TWO_PRT_2ND_OPEN_BRACKET
						+ name + TWO_PRT_2ND_SHUT_BRACKET + "\n";
		}
		return stringTag;
	}
	
	public void setDepthLevel(int level)
	{
		this.depthLevel = level;
		for(Tag t : children)
			t.setDepthLevel(level + 1);
	}
	
	public void setInnerText(String innerText)
	{
		this.innerText = innerText;
	}
	
	//Protected
	//Private
	
	private void appendChildren()
	{
		for(Tag t : children)
		{
			stringTag += t.getTagFamilyToString() + "\n";
		}
	}
	
	private String getAttributesToString()
	{
		String strAttrib = "";
		for(int i = 0; i < attributes.size(); i++)
		{
			strAttrib += " " + attributes.get(i).Name + "=\"" + attributes.get(i).Value + "\"";
		}
		
		if (attributes.size() > 0)
			strAttrib += " ";
		return strAttrib;
	}
	
	private String getTabs()
	{
		String tabs = "";
		for(int i = 0; i < depthLevel; i++)
			tabs += TAB;
		return tabs;
	}
	
}
