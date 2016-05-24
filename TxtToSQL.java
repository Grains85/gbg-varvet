import java.io.*;
import java.util.*;

public class TxtToSQL {
	
    public static void main(String[] args) throws Exception {
	
		FileInputStream fstream = new FileInputStream("deltagare.txt");
		Scanner scanner = new Scanner(fstream,"UTF-8");
	   
	   Writer out = new OutputStreamWriter(new FileOutputStream("gbg_sql.txt"), "UTF-8");
	   
		String strLine;
		while (scanner.hasNext())   {
			strLine = scanner.nextLine();
			String[] cols = strLine.split("#");
			
			String[] name = cols[0].split(",");
			String fname = name[0];
			String lname = name[1];
			
			out.write("INSERT INTO gbg_varv_11 VALUES ('"+ name[1].trim() + "','" + name[0] + "','" + cols[1] + "','" + cols[3] + "','" + 
			                                              cols[4] + "','" + cols[5] + "','" + cols[6] + "','" + cols[7] + "','" + cols[8] +"');\n");
		}
		out.close();
	}
}