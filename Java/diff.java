package net.codehobby.diff;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Diff
{
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
}
