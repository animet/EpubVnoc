package Vnoc.Creation.Epub;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

import Vnoc.Log.Logger;


public class EpubPreparation {

	final String MIMETYPE_CONTENT = "application/epub+zip";
	final String CONTAINER_XML_CONTENT = "<?xml version=\"1.0\"?>\n" 
	+ "<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">\n"
	+ "<rootfiles>\n<rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>\n</rootfiles>\n"	
    + "</container>";
	String outputPath;
	String pMETA_INF;
	String pOEBPS;
	String pImages;
	String pStyles;
	String pText;
	BufferedWriter writer;
	public EpubPreparation(String outputPath)
	{
		this.outputPath = outputPath;
		Logger.initLogger(outputPath);
		init();
	}
	private void init()
	{
		pMETA_INF = this.outputPath +"\\" + "META-INF";
		pOEBPS = this.outputPath + "\\" + "OEBPS";
		pImages = pOEBPS + "\\" + "Images";
		pStyles = pOEBPS + "\\" + "Styles";
		pText = pOEBPS + "\\" + "Text";
	}
	
	public void prepare() throws IOException
	{
		createFolders();
		Logger.addLogMessage("Folders created.");
		createNecessaryFiles();
		Logger.addLogMessage("Necessary files created.");
		copyPageTemplate();
	}
	
	private void createFolders()
	{
		new File(outputPath).mkdir();
		new File(pMETA_INF).mkdir();
		new File(pOEBPS).mkdir();
		new File(pImages).mkdir();
		new File(pStyles).mkdir();
		new File(pText).mkdir();
	}
	
	private void createNecessaryFiles() throws IOException
	{
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath + "\\mimetype")));
		writer.append(MIMETYPE_CONTENT);
		writer.flush();
		writer.close();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pMETA_INF + "\\container.xml")));
		writer.append(CONTAINER_XML_CONTENT);
		writer.flush();
		writer.close();
	}
	
	private void copyPageTemplate()
	{
		File source = new File("page-template.xpgt");
		File dest = new File(pStyles + "\\" + "page-template.xpgt");
		try {
			Files.copy(source.toPath(), dest.toPath());
			Logger.addLogMessage("Pagetemplate copied.");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.addLogMessageWithStackTrace("Couldn't copy pagetemplate!", e.getStackTrace());
		}
	}
}
