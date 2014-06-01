package Vnoc.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Logger {

	static final String LOG_FILE_NAME = "log.txt";
	static String logString = "";
	static ArrayList<String> subLogFileNames;
	static ArrayList<String> subLogFileContents;
	static Writer writer;
	static String _logFolderPath;
	
	public static void initLogger(String logFolderPath)
	{
		logString = "";
		subLogFileContents = new ArrayList<String>();
		subLogFileNames = new ArrayList<String>();
		_logFolderPath = logFolderPath;
		File logFile = new File(_logFolderPath+"\\"+LOG_FILE_NAME);
		if (logFile.exists())
			logFile.delete();
	}
	
	public static void addLogMessage(String message)
	{
		logString += message + "\n";
		try 
		{
			File logFile = new File(_logFolderPath+"\\"+LOG_FILE_NAME);
			if (!logFile.exists())
				logFile.createNewFile();
			writer = new PrintWriter(new FileOutputStream(logFile, true));
			writer.append(message + "\n");
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void addCriticalLogMessage(String message)
	{
		String tempLogString = "!--!--!--!--!--!--!Critical Message:!--!--!--!--!--!--!\n"+message+"\n!--!--!--!--!--!--!--!--!--!!--!--!--!--!--!--!";
				
		logString += tempLogString + "\n";
		try 
		{
			File logFile = new File(_logFolderPath+"\\"+LOG_FILE_NAME);
			if (!logFile.exists())
				logFile.createNewFile();
			writer = new PrintWriter(new FileOutputStream(logFile, true));
			writer.append(tempLogString + "\n");
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void addLogMessageWithStackTrace(String message, StackTraceElement[] stackTrace)
	{
		String tmpLogString = "";
		tmpLogString += message + "\n";
		tmpLogString += "------StackTrace Start:------\n";
		for(StackTraceElement ste : stackTrace)
		{
			tmpLogString += ste.toString() + "\n";
		}
		tmpLogString += "------StackTrace End:------\n";
		
		try 
		{
			File logFile = new File(_logFolderPath+"\\"+LOG_FILE_NAME);
			if (!logFile.exists())
				logFile.createNewFile();
			writer = new PrintWriter(new FileOutputStream(logFile, true));
			writer.append(tmpLogString);
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		logString += tmpLogString;
	}
	
	public static void addLinkedLogMessage(String message, String subLogFileName, String subLogFileContent)
	{
		logString += message + " - linked to sublog:" + subLogFileName + "\n";
		try 
		{
			File logFile = new File(_logFolderPath+"\\"+LOG_FILE_NAME);
			if (!logFile.exists())
				logFile.createNewFile();
			writer = new PrintWriter(new FileOutputStream(logFile, true));
			writer.append(message + " - linked to sublog:" + subLogFileName + "\n");
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		subLogFileNames.add(subLogFileName);
		subLogFileContents.add(subLogFileContent);
		saveSubLog(subLogFileName, subLogFileContent);
	}
	
	public static void printLog()
	{
		System.out.println(logString);
	}
	
	
	private static void saveSubLog(String subLogFileName, String subLogFileContent)
	{
		try {
			if (!new File(_logFolderPath + "\\" + "sublogs").exists())
				new File(_logFolderPath + "\\" + "sublogs").mkdir();
			File subLogFile = new File(_logFolderPath + "\\sublogs\\" + subLogFileName);
			if (!subLogFile.exists())
				subLogFile.createNewFile();
			writer = new PrintWriter(subLogFile);
			writer.write(subLogFileContent);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
