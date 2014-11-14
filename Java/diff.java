package net.codehobby.diff;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Diff
{
	private int MAXLINES = 2000;//Max number of lines to read in a file.
	private int ORIGIN = MAXLINES;//Subscript fro diagonal 0.
	ArrayList<String> A;//Lines of file1.
	ArrayList<String> B;//Lines of file2.

	/**
	 * This actually calculates the diff of two files.
	 * 
	 * @param file1 The filename of the first file.
	 * @param file2 The filename of the second file.
	 * @return An ArrayList of Change objects representing all the changes of the files.
	 */
	public ArrayList<Change> diff( String file1, String file2 )
	{
	}

	/**
	 * This will read the file pointed to by filename and put the lines in an ArrayList of Strings.
	 * 
	 * @param filename The filename to read.
	 * @return The ArrayList of Strings containing the text of the file.
	 */
	private ArrayList<String> readfile( String filename )
	{
		ArrayList<String> lines = new ArrayList<String>();

		try
		{
			BufferedReader bReader = new BufferedReader( new FileReader(filename) );//Set up the BufferedReader to read from the file pointed to by filename.
			String inputLine = null;
			
			while( (inputLine = bReader.readLine()) != null )
			{//Read each line of the file using bReader and put each one it turn into the inputLine string.
				lines.add( inputLine );//Put each inputLine (i.e. each line of the file) into the lines ArrayList.
			}
		} catch( IOException e )
		{
			System.err.println( "Error reading file \"" + filename "\": " + e.getMessage() );
			e.printStackTrace();
		}
		finally
		{
			return lines;
		}
	}
	
	/**
	 * Prints the differences between the two files.
	 * 
	 * @param changes A list of differences between the two files.
	 */
	private void printChanges( ArrayList<Change> changes )
	{
		Change a, b;
		int j = 0;
		for( int i = 0; i < changes.size(); i++ )
		{//For each change in the changes ArrayList, print the change.
			if( changes.get(i).getOperation() == Change.INSERT )
			{//If it's an insert, tell the user.
				System.out.println( "Inserted after line " + individualChange.getLineNumber1() + ":" );
			}
			else
			{//It's a delete, so tell the user.
				do
				{//Look to see if this is a block of deleted lines.
					//TODO: Check to see how many deleted lines there are.
					//Possibly use j to point to the command after the last deletion.
				}
				
				//TODO: Print out the deleted stuff.
			}
		}
	}
}
