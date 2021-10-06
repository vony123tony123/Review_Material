import java.awt.print.Printable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import sun.print.PSPrinterJob.PluginPrinter;

public class IOTextFile {
	
	private static Formatter output; // outputs text to a file 
	private static Scanner input;

	private static void openFile(String filepath,boolean is_read)
	   {
	      try
	      {
	    	 if(is_read==false)
	    		 output = new Formatter(filepath); // open the file
	    	 else
	    	 	input = new Scanner(Paths.get(filepath)); 
	      }
	      catch (SecurityException securityException)
	      {
	         System.err.println("Write permission denied. Terminating.");
	         System.exit(1); // terminate the program
	      } 
	      catch (FileNotFoundException fileNotFoundException)
	      {
	         System.err.println("Error opening file. Terminating.");
	         System.exit(1); // terminate the program
	      } 
	      catch (IOException ioException)
	      {
	          System.err.println("Error opening file. Terminating.");
	          System.exit(1);
	       } 
	   } 
	
	 private static void closeFile()
	   {
	      if (output != null)
	         output.close();
	      if (input != null)
	          input.close();
	   } 
	 
	// read record from file
	   public static String readRecords(String filepath)
	   {
		  String str="";
		  openFile(filepath,true);
		  try 
	      {
	         while (input.hasNext()) // while there is more to read
	         {
	            str+=input.nextLine();
	            str+="\n";
	         }
	      } 
	      catch (NoSuchElementException elementException)
	      {
	         System.err.println("File improperly formed. Terminating.");
	      } 
	      catch (IllegalStateException stateException)
	      {
	         System.err.println("Error reading from file. Terminating.");
	      } 
		  closeFile();
		  return str;
	   } // end method readRecords
	   
	   
	// add records to file
	   public static void addRecords(String str,String filepath)
	   {
		 openFile(filepath,false);
         try
         {
            output.format("%s",str);                             
         } 
         catch (FormatterClosedException formatterClosedException)
         {
            System.err.println("Error writing to file. Terminating.");
         } 
         catch (NoSuchElementException elementException)
         {
            System.err.println("Invalid input. Please try again.");
         } 
         closeFile();
	   }
}
