import java.net.*;
import java.io.*;
import java.util.*;

public class Main {
	
	public static String memberFile = "trivastmembers.txt";
	public static String outFile = "deltagare.txt";
	
    public static void main(String[] args) throws Exception {
	
		LinkedList<Lopare> members = getMembers();
		LinkedList<Lopare> allaDeltagare = getAllRunners();
			
		Writer out = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
		
		for(int i=0; i<members.size();i++){
			Lopare ml = members.get(i);
			String member = ml.getName();
			String memBirth = ml.getBirth();
			int count=0;
			Lopare resLopare = new Lopare(null,null,null,null,null,null);
			// Räkna hur många i samma namn
			for(int a=0; a<allaDeltagare.size(); a++){
				Lopare l = allaDeltagare.get(a);
				String lopare = l.getName();
				String birth = l.getBirth();
				if(lopare.equals(member) && birth.equals(memBirth)){
					count++;
					resLopare = l;
				}
			}
			
			String name = resLopare.getName();
			String birth = resLopare.getBirth();
			if(count == 1 && memBirth.equals(birth)){
				String time = resLopare.getTime();
				String[] splits = resLopare.getSplits();
				String place = resLopare.getPlace();
				String number = resLopare.getNumber();
				
				out.write(name + "#" + time + "#" + birth + "#" + splits[0] + "#" + splits[1] + "#" + splits[2] + "#" + splits[3] + "#" + place + "#" + number +"#\n");
				//System.out.println(name + "#" + time + "#" + birth + "#" + splits[0] + "#" + splits[1] + "#" + splits[2] + "#" + splits[3] + "#" + place + "#" + number +"#");
			}
			else if(count > 1)
				System.out.println(name + " - ??? Finns flera");
		}
		out.close();
    }
	
	  
  /**
  for(int i=0; i<members.size();i++){
			Lopare ml = members.get(i);
			String member = ml.getName();
			String memBirth = ml.getBirth();
			
			out.write(member+"\n");
			//System.out.println(member + " - " + memBirth);
			}
			out.close();
			System.out.println("tmp.txt writen!..");
	*/	
	
	public static LinkedList<Lopare> getMembers(){
		LinkedList<Lopare> members = new LinkedList<Lopare>();
		try{
			FileInputStream fstream = new FileInputStream(memberFile);
			Scanner scanner = new Scanner(fstream,"UTF-8");
	   
			String strLine;
			while (scanner.hasNext())   {
				strLine = scanner.nextLine();
				String[] memberArr = strLine.split("\t");
				String mem = memberArr[1]+", "+memberArr[0];
				String birth = memberArr[10];
				Lopare lopare = new Lopare(mem,null,birth,null,null,null);
				members.add(lopare);
			}
			scanner.close();
		}
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return members;
	}
	
	public static LinkedList<Lopare> getAllRunners(){
		LinkedList<Lopare> list = new LinkedList<Lopare>();
        String inputLine;
		try{
			for(int page=1; page<240; page++){//240
				URL gbglink = new URL("http://results.goteborgsvarvet.com/resultat/2011/index.php?page="+page+"&content=list&event=GHLF&lang=EN&num_results=250&pid=search&search[name]=&search[firstname]=&search[club]=&search[nation]=&search[start_no]=&search[yob]=&search[postcode]=&search[postcode_range_from]=&search[postcode_range_to]=&search[city]=&search_sort=name&search_sort_order=ASC&top_results=3&type=search");
		        URLConnection yc = gbglink.openConnection();
				Scanner in = new Scanner(yc.getInputStream(),"UTF-8");

		        while (in.hasNext()){
					inputLine = in.nextLine();
					if(inputLine.contains("<tr valign=\"top\"")){
						inputLine = in.nextLine(); // inputLine = Place line
						int startIndex = 5+inputLine.indexOf("<td >");
						int endIndex = inputLine.indexOf("</td>");
						String place = inputLine.substring(startIndex,endIndex);
						
						inputLine = in.nextLine(); // inputLine = Number line
						startIndex = 5+inputLine.indexOf("<td >");
						endIndex = inputLine.indexOf("</td>");
						String number = inputLine.substring(startIndex,endIndex);
					
						inputLine = in.nextLine(); // inputLine = Name
						startIndex = 11+inputLine.indexOf("ageclass=\">");
						endIndex = inputLine.indexOf("</a>");
						String name = inputLine.substring(startIndex,endIndex);
					
						inputLine = in.nextLine(); // YOB
						startIndex = 5+inputLine.lastIndexOf("<td >");
						endIndex = inputLine.indexOf("</td>");
						String birth = "19"+inputLine.substring(startIndex,endIndex);
						
						in.nextLine(); // Postcode
						in.nextLine(); // City
						
						inputLine = in.nextLine(); // 5k
						startIndex = 7+inputLine.indexOf("split");
						endIndex = inputLine.indexOf("</td>");
						String split1 = inputLine.substring(startIndex,endIndex);
						
						inputLine = in.nextLine(); // 10k
						startIndex = 7+inputLine.indexOf("split");
						endIndex = inputLine.indexOf("</td>");
						String split2 = inputLine.substring(startIndex,endIndex);
						
						inputLine = in.nextLine(); // 15k
						startIndex = 7+inputLine.indexOf("split");
						endIndex = inputLine.indexOf("</td>");
						String split3 = inputLine.substring(startIndex,endIndex);
						
						inputLine = in.nextLine(); // 20k
						startIndex = 7+inputLine.indexOf("split");
						endIndex = inputLine.indexOf("</td>");
						String split4 = inputLine.substring(startIndex,endIndex);
						
						String[] splits = {split1,split2,split3,split4};
						
						in.nextLine(); // Pred. finish
						inputLine = in.nextLine(); // finish
						startIndex = 7+inputLine.indexOf("right");
						endIndex = inputLine.indexOf("</td>");
						String finish = inputLine.substring(startIndex,endIndex);
						
						Lopare lopare = new Lopare(name, finish, birth, splits, place, number);
						list.add(lopare);
					}
				}
				in.close();
			}
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return list;
	}
}