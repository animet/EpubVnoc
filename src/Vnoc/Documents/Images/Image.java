package Vnoc.Documents.Images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Vnoc.Enums.Documents.Images.ImageFormat;
import Vnoc.Exceptions.Documents.Images.UnknownImageFormatException;
import Vnoc.Misc.Path;

public class Image {

	static int imgCounter = 0;
	String fileName;
	String fileExtension;
	BufferedImage image;
	ImageFormat imageFormat;
	int lineIndex;
	public Image(byte[] imageAsBytes, int lineIndex, String fileExtension)
	{
		imageFormat = getImageFormat(fileExtension);
		this.lineIndex = lineIndex;
		if (fileExtension.equals("jpg"))
			this.fileExtension = "jpeg";
		else
			this.fileExtension = fileExtension;
		this.fileName = "img" + String.valueOf(imgCounter);
		setBufferedImage(imageAsBytes);
		imageAsBytes = null;
		imgCounter++;
	}
	
	public void dispose()
	{
		fileExtension = null;
		lineIndex = 0;
		image = null;
	}
	
	public String getFileExtension()
	{
		return fileExtension;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public ImageFormat getImageFormat()
	{
		return imageFormat;
	}
	
	public int getLineIndex()
	{
		return lineIndex;
	}
	
	public Size getSize()
	{
		return new Size(image.getWidth(), image.getHeight());
	}
	
	public void save(String path)
	{
		try {
			ImageIO.write(image, fileExtension, new File(path + "\\OEBPS\\Images\\" + fileName +"."+ fileExtension));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(String path, ImageFormat imgFormat)
	{
		
		try {
			convertTo(imgFormat);
			ImageIO.write(image, fileExtension, new File(path + "\\OEBPS\\Images\\" + fileName +"."+ fileExtension));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnknownImageFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void setSize(int width, int height)
	{
		java.awt.Image resizedImg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new BufferedImage(resizedImg.getWidth(null), resizedImg.getHeight(null), 
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(resizedImg,0,0,null);
	}
	
	protected void convertTo(ImageFormat imageFormat) throws UnknownImageFormatException
	{
		if (this.imageFormat.equals(ImageFormat.UNKNOWN))
			throw new UnknownImageFormatException();
		
		if (!this.imageFormat.equals(imageFormat))
		{
			switch(imageFormat)
			{
			case JPEG:
				this.fileExtension = getImageFormatString(imageFormat);
				this.imageFormat = imageFormat;
				break;
			case PNG:
				this.fileExtension = getImageFormatString(imageFormat);
				this.imageFormat = imageFormat;
				break;
			case GIF:
				this.fileExtension = getImageFormatString(imageFormat);
				this.imageFormat = imageFormat;
				break;
			}
		}
	}
	
	protected ImageFormat getImageFormat(String imgFormatStr)
	{
		if (imgFormatStr.toUpperCase().equals("JPG") || imgFormatStr.toUpperCase().equals("JPEG"))
			return ImageFormat.JPEG;
		else if (imgFormatStr.toUpperCase().equals("PNG"))
			return ImageFormat.PNG;
		else if (imgFormatStr.toUpperCase().equals("GIF"))
			return ImageFormat.GIF;
		else
			return ImageFormat.UNKNOWN;
	}
	
	protected String getImageFormatString(ImageFormat imgFormat)
	{
		String imgFormatStr = "";
		switch(imgFormat)
		{
		case JPEG:
			imgFormatStr = "JPG";
			break;
		case PNG:
			imgFormatStr = "PNG";
			break;
		case GIF:
			imgFormatStr = "GIF";
			break;
		}
		return imgFormatStr;
	}
	
	protected void setBufferedImage(byte[] imageAsBytes)
	{
		try {
			
			FileOutputStream fos = new FileOutputStream(Path.getTempPath() + "\\" + fileName +"."+ fileExtension);
			fos.write(imageAsBytes);
			fos.flush();
			fos.close();
			File imgFile = new File(Path.getTempPath() + "\\" + fileName +"."+ fileExtension);
			image = ImageIO.read(imgFile);
			imgFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
