package spotifyKnockoff;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorLogger {
	public static void log(String errorMessage){
		//Save the following information to errorlog.txt
		
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy" +"\t"+ "HH:mm:ss"+ "\t").format(new Date());
		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter("errorlog.txt", true); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    out.write(timeStamp);
		    out.write(errorMessage+"\n");
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		
		//Date, Time, errorMessage \n
		
	}

}
