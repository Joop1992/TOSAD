package domain;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Value> getValues(BusinessRule businessRule) {

        // this may only execute when the type is NOT an 'Other' type.
        if (!getAbbreviation().equals("AOTH") && !getAbbreviation().equals("EOTH")
                && !getAbbreviation().equals("TOTH") && !getAbbreviation().equals("TCMP")){
            //take all the values
            for (Value v : businessRule.values) {
                try {
                    Double.parseDouble(v.getName());
                } catch (NumberFormatException nfe) {
                    //this value contains a non numeric value
                    //if it contains a comma and it's a list rule
                    if (v.getName().contains(",") && getAbbreviation().equalsIgnoreCase("ALIS")) {
                        //split the comma seperated list by comma and save list items
                        List<String> listItems = Arrays.asList(v.getName().split("\\s*,\\s*"));
                        String newValueName = "";

                        //loop through all items, add ' and , based on their position
                        for (int i = 0; i < listItems.size(); i++) {
                            String replaced = listItems.get(i).replaceAll("&#x27;", "");

                            if (i != listItems.size() - 1) {
                                newValueName += ("'" + replaced + "', ");
                            } else {
                                newValueName += ("'" + replaced + "'");
                            }
                            v.setName(newValueName);
                        }
                    }
                    //not a list rule but it do is a non numeric value
                    else {
                        //add single quotes around the string
                        v.setName("'" + v.getName() + "'");
                    }
                }
            }
        }
        return businessRule.values;
    }
}
