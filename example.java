import java.io.IOException;

import Vnoc.Conversion.PdfToDocument;
import Vnoc.Creation.Epub.ContentOpfCreation;
import Vnoc.Creation.Epub.EpubPreparation;
import Vnoc.Creation.Epub.ImageCreation;
import Vnoc.Creation.Epub.TableOfContentCreation;
import Vnoc.Creation.Epub.TocNcxCreation;
import Vnoc.Creation.Epub.XhtmlPageCreation;
import Vnoc.Documents.Document;
import Vnoc.Documents.Images.Size;
import Vnoc.Enums.Creation.FormatMode;
import Vnoc.Enums.Documents.Images.ImageFormat;
import Vnoc.Tags.TagDocument;
import Vnoc.Zip.CreateZip;

public class MainTest {

	public static void main(String[] args) throws IOException
	{
		Size deviceResolution = new Size(768, 1024);
		
		EpubPreparation preparation = new EpubPreparation("C:\\myEpub\\");
		preparation.prepare();
		
		PdfToDocument pdf2Doc = new PdfToDocument("sample.pdf");
		Document doc1 = pdf2Doc.getDocument();
		
		ImageCreation imageCreation = new ImageCreation("C:\\myEpub\\", doc1, deviceResolution);
		imageCreation.createImages();
		//imageCreation.createImages(ImageFormat.JPEG);
		
		TableOfContentCreation toc = new TableOfContentCreation(doc1, "C:\\myEpub\\");
		toc.create();
		
		XhtmlPageCreation pageCreation = new XhtmlPageCreation(doc1);
		pageCreation.createXhtmlPages("C:\\myEpub\\", FormatMode.DoLineBreaks);
		
		CreateZip createZip = new CreateZip("C:\\myEpub\\yeah.epub");
		createZip.createEpubZip("C:\\myEpub\\");
	}
}
