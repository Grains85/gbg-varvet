import java.net.*;
import java.io.*;
import java.util.*;

public class Formatera{
	 public static void main(String[] args) throws Exception {
	
		String file = "res";
	try{
	  // Open the file that is the first 
	  // command line parameter
	  FileInputStream fstream = new FileInputStream(file+".txt");
	  // Get the object of DataInputStream
	  DataInputStream in = new DataInputStream(fstream);
	  BufferedReader br = new BufferedReader(new InputStreamReader(in));
	  
	  FileWriter fw = new FileWriter("out.txt");
	  BufferedWriter out = new BufferedWriter(fw);
		  
	  String strLine;
	  //Read File Line By Line
	  while ((strLine = br.readLine()) != null)   {
	  // Print the content on the console
		String[] memberArr = strLine.split(" - ");
		String line = memberArr[0]+"\t"+memberArr[1];
		
		if(line.contains("+")){
			line = line.replace("+","");
		}
		/**
		line = line.replaceAll("[®]","e");
			line = line.replaceAll("[Â]","ö");
			line = line.replaceAll("[à]","å");
			//line = line.replaceAll("[ñ]","ä");
			line = line.replaceAll("[±]","ä");
			//line += "å";
			*/
			//line = line.replaceAll("[ñ]","ä");
			
			// ö
			//line = line.replace("Ã","ö");
			
			//  ä
			//line = line.replaceAll("[±]","ä");
			line = line.replace("+ñ","ä");
			
			line += "\n";

			
		  out.write(line);
		  //Close the output stream
		  
  
		//System.out.println(line);
		
		/**
		if(line.contains("++é"))
			//line.replace("++é","ö");
			line += "öööööööööööööööö";
		if(line.contains("-++á"))
			line.replace("-++á","å");
		if(line.contains("++¦"))
			line.replace("++¦","ä");
		if(line.contains("&nbsp;"))
			line.replace("&nbsp;","DNF");
		System.out.println(line);
		*/
	  }


	  //Close the input stream
	  out.close();
	  in.close();
    }catch (Exception e){//Catch exception if any
	System.err.println("Error: " + e.getMessage());
  }
 
  }
}