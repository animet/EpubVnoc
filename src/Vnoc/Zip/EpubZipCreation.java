package Vnoc.Zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import Vnoc.Log.Logger;

public class EpubZipCreation {
	
	ArrayList<String> filePaths;
	String outputPath;
	public EpubZipCreation(String outputPath)
	{
		this.outputPath = outputPath;
		filePaths = new ArrayList<String>();
	}
	
	public void createEpubZip(String rawEpubFilePath) throws IOException
	{
		Logger.addLogMessage("Creating epub (zip) file");
		filePaths = new ArrayList<String>();
		setFilePaths(rawEpubFilePath+"\\META-INF");
		setFilePaths(rawEpubFilePath+"\\OEBPS");
		
		FileOutputStream fos = new FileOutputStream(outputPath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		addMimeType(rawEpubFilePath+"\\mimetype", fos, zos);
		
		for(String filePath : filePaths)
		{
			zos.setLevel(Deflater.DEFAULT_COMPRESSION);
			ZipEntry ze = new ZipEntry(filePath.replace(rawEpubFilePath, ""));
			zos.putNextEntry(ze);
			FileInputStream fis = new FileInputStream(filePath);
			int length;
			byte[] buffer = new byte[1024];
			while((length = fis.read(buffer))>0)
			{
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
		}
		zos.close();
		fos.close();
	}
	
	private void addMimeType(String mimetypePath, FileOutputStream fos, ZipOutputStream zos) throws IOException
	{
		zos.setLevel(Deflater.NO_COMPRESSION);
		ZipEntry ze = new ZipEntry("mimetype");
		zos.putNextEntry(ze);
		FileInputStream fis = new FileInputStream(mimetypePath);
		int length;
		byte[] buffer = new byte[1024];
		while((length = fis.read(buffer))>0)
		{
			zos.write(buffer, 0, length);
		}
		zos.closeEntry();
		fis.close();
	}
	
	
	private void setFilePaths(String path)
	{
		File dir = new File(path);
		for(File f : dir.listFiles())
		{
			if(f.isDirectory())
				setFilePaths(f.getAbsolutePath());
			else
				filePaths.add(f.getAbsolutePath());
		}
	}
	
}
