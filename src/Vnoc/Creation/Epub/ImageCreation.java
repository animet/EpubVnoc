package Vnoc.Creation.Epub;

import Vnoc.Documents.Document;
import Vnoc.Documents.Page;
import Vnoc.Documents.Images.Image;
import Vnoc.Documents.Images.Size;
import Vnoc.Enums.Documents.Images.ImageFormat;

public class ImageCreation {

	Size deviceResolution;
	Document doc;
	boolean isDeviceResolutionSet;
	String path;
	public ImageCreation(String path, Document doc)
	{
		this.path=path;
		this.doc=doc;
		this.isDeviceResolutionSet = false;
	}
	public ImageCreation(String path, Document doc, Size deviceResolution)
	{
		this.path = path;
		this.doc = doc;
		this.deviceResolution = deviceResolution;
		this.isDeviceResolutionSet = true;
	}
	
	public void createImages()
	{
		for(int i = 0; i < doc.getPages().size(); i++)
		{
			Page p = doc.getPages().get(i);
			for(int j = 0; j < p.getImageCount(); j++)
			{
				if (isDeviceResolutionSet)
					setImageResolution(p.getImage(j));
				p.getImage(j).save(path);
			}
		}
	}
	
	public void createImages(ImageFormat imgFormat)
	{
		for(int i = 0; i < doc.getPages().size(); i++)
		{
			Page p = doc.getPages().get(i);
			for(int j = 0; j < p.getImageCount(); j++)
			{
				if(isDeviceResolutionSet)
					setImageResolution(p.getImage(j));
				p.getImage(j).save(path, imgFormat);
			}
		}
	}
	
	private void setImageResolution(Image img)
	{
		Size imgRes = img.getSize();
		//Mal 0.1 = 8% Spielraum
		if(imgRes.Height > deviceResolution.Height - deviceResolution.Height * 0.08
				|| imgRes.Width > deviceResolution.Width - deviceResolution.Width * 0.08)
		{
			img.setSize(deviceResolution.Width - (int)(deviceResolution.Width * 0.08)
					, deviceResolution.Height - (int)(deviceResolution.Height * 0.08));
		}
	}
}
