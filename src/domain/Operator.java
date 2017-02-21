package domain;

public class Operator {
	private String name;
	private String abbreviation;
	
	public Operator(String name2, String abbrev){
		this.name = name2;
		this.abbreviation = abbrev;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getAbbreviation(){
		return this.abbreviation;
	}
}
