package controller;

import java.util.ArrayList;
import java.util.List;

import persistence.trigger.TriggerDAO;

public class TriggerDAOService {
	private TriggerDAO targetDao = new TriggerDAO();
	
	public String insertTrigger(String trigger){
		return this.targetDao.insertTrigger(trigger);
	}
	
	public ArrayList<String> getAllTriggers(){
		return this.targetDao.getAllTriggers();
	}
	
	public List<String> getExistingTriggers(String code){
		return this.targetDao.getExistingTriggers(code);
	}

}
