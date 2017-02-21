package controller;

import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule;
import generator.Generator;

public class GeneratorController {
	public List<String> generate(String code, String language) {
		ArrayList<String> generatedList = new ArrayList<String>();
		List<BusinessRule> rulesToGenerate;
		Controller controller = new Controller();

		//if no rule is specified, we get all rules from the database
		if (code == null || code.equals("null") || code.equals("") || code.equals(" ")) {
			rulesToGenerate = controller.getAllRules();
		} 
		//else, we put 1 rule in an array (only 1 page needed to display)
		else {
			rulesToGenerate = new ArrayList<BusinessRule>();
			rulesToGenerate.add(controller.searchByCode(code));
		}
		//generically creating the Generator with specified language.
		String generatorName = "generator.Generator"+language.toUpperCase();
		Generator g = null;
		try {
			g = (Generator) Class.forName(generatorName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//generate the single rules, add them to string aray
		for (BusinessRule br : rulesToGenerate) {
			
			generatedList.add(g.fillTemplate(br));

		}


		return generatedList;
	}
	
}
