package Vnoc.Misc;

public class Random {

	final static String charRange = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	public static String getRandomString(int length)
	{
		String rand = "";
		int max = charRange.length();
		for(int i = 0; i < length; i++)
		{
			rand += charRange.charAt((int)(Math.random()*max));
		}
		return rand;
	}
}
