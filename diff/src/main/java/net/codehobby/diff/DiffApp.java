package net.codehobby.diff;

/**
 * Diff
 * Takes two filenames as command line arguments and outputs the 
 * diff between the two.
 *
 */
public class DiffApp 
{
	public static void main( String[] args )
	{
		if( args.length != 2 )
		{
			System.err.println( "Please give two command line arguments in the form of the filenames of the two files you want to diff." );
		}
		else
		{
			Diff d = new Diff();
			System.out.println( "Taking the diff between \"" + args[0] + "\" and \"" + args[1] + "\"." );
			d.diff( args[0], args[1] );
		}
	}
}
