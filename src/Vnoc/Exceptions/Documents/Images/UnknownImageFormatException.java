package Vnoc.Exceptions.Documents.Images;

public class UnknownImageFormatException extends Exception {

	static final String message = "Unknown imageformat detected!";
	public UnknownImageFormatException()
	{
		super(message);
	}
}
