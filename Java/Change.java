package net.codehobby.diff;

/**
 * This is a class to hold some data about a single difference/change between two files.
 * 
 * @author Jeff Crone
 */
public class Change
{
	private int operation;//Basically either insert or delete
	private int lineNumFile1;//The line number of file 1
	private int lineNumFile2;//The line number of file 2
	public static final int INSERT = 1;
	public static final int DELETE = 2;

	/**
	 * Sets the operation value, but checks to make sure it's a valid operation first.
	 * 
	 * @param newOperation The value to set operation to.
	 */
	public void setOperation( int newOperation )
	{
		if( (newOperation != INSERT) && (newOperation != DELETE) )
		{
			throw new IllegalArgumentException( "The operation parameter must be " + this.getClass().getSimpleName() + ".INSERT or " + this.getClass().getSimpleName() + ".DELETE" );
		}
		else
		{
			operation = newOperation;
		}
	}

	/**
	 * Returns the operation value.
	 * 
	 * @return The operation value.
	 */
	public int getOperation()
	{
		return operation;
	}

	/**
	 * Sets the line number 1 value.
	 * 
	 * @param newLineNum1 The value to set the line number 1 value to.
	 */
	public void setLineNumber1( int newLineNum1 )
	{
		lineNumFile1 = newLineNum1;
	}

	/**
	 * Returns the line number 1 value.
	 * 
	 * @return The line number 1 value.
	 */
	public int getLineNumber1()
	{
		return lineNumFile1;
	}

	/**
	 * Sets the line number 2 value.
	 * 
	 * @param newLineNum2 The value to set the line number 2 value to.
	 */
	public void setLineNumber2( int newLineNum2 )
	{
		lineNumFile2 = newLineNum2;
	}

	/**
	 * Returns the line number 2 value.
	 * 
	 * @return The line number 2 value.
	 */
	public int getLineNumber2()
	{
		return lineNumFile2;
	}
}
