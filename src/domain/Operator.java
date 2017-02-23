package domain;

public class Operator {
	private String name;
	private String abbreviation;
	
	private Operator(String name2, String abbrev){
		this.name = name2;
		this.abbreviation = abbrev;
	}

    public static Operator createOperator(String name2, String abbrev)
    {
        return new Operator(name2, abbrev);
    }

    public String getName(){
		return this.name;
	}
	
	public String getAbbreviation(){
		return this.abbreviation;
	}
}
