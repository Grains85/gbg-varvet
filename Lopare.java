public class Lopare{

	public String name;
	public String time;
	public String birth;
	public String[] splits;
	public String number;
	public String place;

	public Lopare(String name, String time, String birth, String[] splits, String place, String number){
		this.name = name;
		this.time = time;
		this.birth = birth;
		this.splits = splits;
		this.place = place;
		this.number = number;
	}
	
	public String getName(){
		return name;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getBirth(){
		return birth;
	}
	
	public String getPlace(){
		return place;
	}
	
	public String getNumber(){
		return number;
	}
	
	public String[] getSplits(){
		return splits;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setTime(String t){
		time = t;
	}
	
	public void setBirth(String b){
		birth = b;
	}
	
	public void setPlace(String p){
		place = p;
	}
	public void setNumber(String n){
		number = n;
	}
}