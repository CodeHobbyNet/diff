package net.codehobby.diff;

import java.util.ArrayList;

public class diff
{
	private ArrayList<String> in_file( String filename )
	{
		ArrayList<String> lines = new ArrayList<String>();

		try
		{
			BufferedReader bReader = new BufferedReader( new FileReader(filename) );//Set up the BufferedReader to read from the file pointed to by filename.
			String inputLine = null;
			
			while( (inputLine = bReader.readLine()) != null )
			{
				lines.add( inputLine );
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
