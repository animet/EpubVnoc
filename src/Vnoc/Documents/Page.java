package Vnoc.Documents;


import java.util.ArrayList;
import java.util.Collection;

import Vnoc.Documents.Formatting.FormatedPageText;
import Vnoc.Documents.Images.Image;

public class Page {

	public FormatedPageText fText;
	ArrayList<Image> images;
	public Page()
	{
		fText = new FormatedPageText();
		images = new ArrayList<Image>();
	}
	
	public void addImage(Image img)
	{
		images.add(img);
	}
	public void addImages(Collection<? extends Image> images)
	{
		this.images.addAll(images);
	}
	
	public void disposeImages()
	{
		for(int i = 0; i < images.size(); i++)
			images.get(i).dispose();
	}
	
	public int getLineCount()
	{
		return fText.getLineCount();
	}
	
	public Image getImage(int index)
	{
		return images.get(index);
	}
	
	public int getImageCount()
	{
		return images.size();
	}
}
