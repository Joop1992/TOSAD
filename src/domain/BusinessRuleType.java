package domain;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleType {
	private String name;
	private Category category;
	private List<Operator> operators;
	private String abbreviation;
	
	public BusinessRuleType(String name2, String abbreviation){
		this.name = name2;
		this.operators = new ArrayList<Operator>();
		this.abbreviation = abbreviation;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setCategory(Category cat){
		this.category = cat;
	}
	
	public Category getCategory(){
		return this.category;
	}
	
	public void addOperator(Operator o){
		this.operators.add(o);
	}
	
	public List<Operator> getOperators(){
		return this.operators;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}
}
