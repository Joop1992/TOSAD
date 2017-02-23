package domain;

import java.util.ArrayList;
import java.util.List;

public class BusinessRule {

	private String code;
	private String description;
	private String table;
	private String when;
	
	public BusinessRuleType type;
	public List<Value> values;
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

	public void setOperator(Operator o) {
		this.operator = o;
	}

	public Operator getOperator() {
		return this.operator;
	}

}
