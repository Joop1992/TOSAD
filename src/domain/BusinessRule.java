package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessRule {

	private String code;
	private String description;
	private String table;
	private String when;
	
	private BusinessRuleType type;
	private List<Value> values;
	private Operator operator;
	private Attribute attribute;

	public BusinessRule(Attribute att, String code, String desc, String tab, String when) {
		this.attribute = att;
		this.code = code;
		this.description = desc;
		this.table = tab;
		this.when = when;
		this.values = new ArrayList<Value>();
	}

	public BusinessRule( String code, String desc, String tab, String when) {
		this.code = code;
		this.description = desc;
		this.table = tab;
		this.when = when;
		this.values = new ArrayList<Value>();
	}

	public Attribute getAttribute() {
		return this.attribute;
	}
	
	public void setAttribute(Attribute a){
		this.attribute = a;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	public String getTable() {
		return this.table;
	}

	public String getWhen() {
		return this.when;
	}

	public void setRuleType(BusinessRuleType brt) {
		this.type = brt;
	}

	public BusinessRuleType getRuleType() {
		return this.type;
	}

	public void addValue(Value v) {
		this.values.add(v);
	}
	
	public List<Value> getValues() {

		// this may only execute when the type is NOT an 'Other' type.
		if (!type.getAbbreviation().equals("AOTH") && !type.getAbbreviation().equals("EOTH")
				&& !type.getAbbreviation().equals("TOTH") && !type.getAbbreviation().equals("TCMP")){
			//take all the values
			for (Value v : this.values) {
				try {
					Double.parseDouble(v.getName());
				} catch (NumberFormatException nfe) {
					//this value contains a non numeric value
					//if it contains a comma and it's a list rule
					if (v.getName().contains(",") && this.type.getAbbreviation().equalsIgnoreCase("ALIS")) {
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
		return this.values;
	}

	public void setOperator(Operator o) {
		this.operator = o;
	}

	public Operator getOperator() {
		return this.operator;
	}

}
