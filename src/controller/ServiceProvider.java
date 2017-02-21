package controller;

public class ServiceProvider {
	public static BusinessRuleDAOService ruleService = new BusinessRuleDAOService();
	public static TriggerDAOService triggerService = new TriggerDAOService();
	
	public static BusinessRuleDAOService getBusinessRuleService(){
		return ruleService;
	}
	
	public static TriggerDAOService getTriggerService(){
		return triggerService;
	}
}
