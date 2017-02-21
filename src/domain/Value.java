package domain;

public class Value {
	public String name;
	
	public Value(String name2){
		this.name = name2;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name;
	}

	public void setName(String newValueName) {
		this.name = newValueName;
		
	}
}
