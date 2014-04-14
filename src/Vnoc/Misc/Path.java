package Vnoc.Misc;

import java.io.File;
import java.io.IOException;

public class Path {

	static String tempPath = "";
	static boolean isTempPathSet = false;
	public Path()
	{
	}
	
	public static String getTempPath()
	{
		if (!isTempPathSet)
			setTempPath();
		return tempPath;
	}
	
	private static void setTempPath()
	{
		try {
			File tmpFile = File.createTempFile("tmp1234123", ".tmp");
			String tempFilePath = tmpFile.getAbsolutePath();
			tempPath = tempFilePath.substring(0,tempFilePath.lastIndexOf(File.separator));
			tmpFile.delete();
			isTempPathSet = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
