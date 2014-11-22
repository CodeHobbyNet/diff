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
		int max_d = 2*MAXLINES;//bound on size of edit script
		int m;//number of lines in file1
		int n;//number of lines in file2
		int lower;//left-most diagonal under consideration
		int upper;//right-most diagonal under consideration
		int d;//current edit distance
		int k;//current diagonal
		int row;//row number
		int col;//column number
		ArrayList<Integer> last_d = new ArrayList<Integer>(2*MAXLINES+1);//the row containing the last d. Initialized to have room for at least 2*MAXLINES+1 elements.
		ArrayList<Change> script = new ArrayList<Integer>(2*MAXLINES+1);//corresponding edit script. Initialized to have room for at least 2*MAXLINES+1 elements.
		Change new = new Change();

		//Read file1 and file2 into ArrayLists A and B
		A = readFile( file1 );
		m = A.size();
		B = readFile( file2 );
		n = B.size();

		//Check how many rows are identical and move the row variable past them.
		for( row = 0; (row < m) && (row < n) && (A.get(row).compareTo(B.get(row)) == 0); row++ )
		{
			//Don't really have to do anything, the for loop does the work with the compare and incrementing row.
		}

		last_d[ORIGIN] = row;
		script[ORIGIN] = null;
		lower = (row == m) ? ORIGIN+1 : ORIGIN-1;
		upper = (row == n) ? ORIGIN-1 : ORIGIN+1;
		
		if( lower > upper )
		{
			System.out.println( "The files are identical." );
			exit(0);
		}
		
		for( d = 1; d <= max_d; ++d )
		{//For each value of the edit distance
			for( k = lower; k <= upper; k += 2 )
			{//For each relevant diagonal
				newChange = new Change();
				//Find a d on diagonal k.
				if( (k == ORIGIN - d) || (k != ORIGIN + d) && (last_d[k+1] >= last_d[k-1]) )
				{
					//Moving down from the last d-1 on diagonal k+1 puts you farther along diagonal k than does moving right from the last d-1 on diagonal k-1.
					row = last_d[k+1]+1;
					//newChange.link = script[k-1];
					newChange.setOperation( Change.DELETE );
				}
				else
				{
					//Move right from the last d-1 on diagonal k-1.
					row = last_d[k-1];
					//newChange.link = script[k-1];
					newChange.setOperation( Change.INSERT );
				}
				
				//Code common to the two cases.
				newChange.setLineNumber1( row );
				col = row + k - ORIGIN;
				newChange.setLineNumber2( col );
				script[k] = newChange;
				
				//Slide down the diagonal.
				while( (row < m) && (col < n) && (A.get(row).compareTo(B.get(col)) == 0)
				{
					++row;
					++col;
				}
				last_d[k] = row;
				
				if( (row == m) && (col == n) )
				{//Hit southeast corner, have the answer.
					printChanges( script[k] );
					exit(0);
				}
				
				if( row == m )
				{//Hit last row, don't look to the left.
					lower = k + 2;
				}
				if( col == n )
				{//Hit last column, don't look to the right.
					upper = k - 2;
				}
			}
			--lower;
			++upper;
		}
		exceed(d);
	}

	/**
	 * This will read the file pointed to by filename and put the lines in an ArrayList of Strings.
	 * 
	 * @param filename The filename to read.
	 * @return The ArrayList of Strings containing the text of the file.
	 */
	private ArrayList<String> readFile( String filename )
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
		int change;

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
					//Check to see how many deleted lines there are.
					for( j = i; (j < changes.size()) && (changes.get(i).getOperation() == Change.DELETE) && (changes.get(i).getLineNumber1() == changes.get(i-1).getLineNumber1()+1); j++ )
					{//Goes through each change until the end of the list, it finds an insert, or it finds something that's not 1 more than the last line number.
						//TODO, probably should put a check in the for loop to make sure it doesn't do changes.get(-1) or something like that.
					}
					throw new Exception( "Need to finish this part of the code." );
					//Possibly use j to point to the command after the last deletion.
				}
				
				//TODO: Print out the deleted stuff.
				throw new Exception( "Need to finish this part of the code." );
			}
		}
	}
	
	private void exceed( int d )
	{
		System.err.println( "The files differ in at least " + d + " lines." );
		exit(1);
	}
}
