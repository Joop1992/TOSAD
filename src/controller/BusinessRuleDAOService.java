package controller;

import java.util.List;

import domain.BusinessRule;
import persistence.businessRule.BusinessRuleDAO;

public class BusinessRuleDAOService {
	private BusinessRuleDAO toolDao = new BusinessRuleDAO();
	
	public List<BusinessRule> getAll(){
		return this.toolDao.getAllBusinessRules();
	}
	public BusinessRule searchByCode(String code){
		return this.toolDao.searchByCode(code);
	}
	public List<BusinessRule> searchRulesByCode(String code){
		return this.toolDao.searchMultipleByCode(code);
	}
}
