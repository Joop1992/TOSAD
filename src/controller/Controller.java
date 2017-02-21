package controller;

import java.util.List;

import domain.BusinessRule;

public class Controller {
		
	public List<BusinessRule> getAllRules(){
		return ServiceProvider.getBusinessRuleService().getAll();
	}
	
	public BusinessRule searchByCode(String code){
		return ServiceProvider.getBusinessRuleService().searchByCode(code);
	}	
	
	public String insertTrigger(String trigger){
		return ServiceProvider.getTriggerService().insertTrigger(trigger);
	}

	public List<String> getExistingTriggers(String code) {
		return ServiceProvider.getTriggerService().getExistingTriggers(code); 
	}

}
