package controller;

import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule;
import generator.Generator;

public class GeneratorController {
	public List<String> generate(GenerateParameter generateParameter) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		ArrayList<String> generatedList = new ArrayList<String>();
		List<BusinessRule> rulesToGenerate;
		Controller controller = new Controller();

		//if no rule is specified, we get all rules from the database
		if (generateParameter.getCode() == null || generateParameter.getCode().equals("null") || generateParameter.getCode().equals("") || generateParameter.getCode().equals(" ")) {
			rulesToGenerate = controller.getAllRules();
		} 
		//else, we put 1 rule in an array (only 1 page needed to display)
		else {
			rulesToGenerate = new ArrayList<BusinessRule>();
			rulesToGenerate.add(controller.searchByCode(generateParameter.getCode()));
		}
		//generically creating the Generator with specified language.
		String generatorName = "generator.Generator"+ generateParameter.getLanguage().toUpperCase();
		Generator g = null;
		g = (Generator) Class.forName(generatorName).newInstance();
		
		
		//generate the single rules, add them to string aray
		for (BusinessRule br : rulesToGenerate) {
			
			generatedList.add(g.fillTemplate(br));

		}


		return generatedList;
	}

	public static class GenerateParameter
	{
		private final String code;
		private final String language;

		public GenerateParameter(String code, String language)
		{
			this.code = code;
			this.language = language;
		}

		public String getCode()
		{
			return code;
		}

		public String getLanguage()
		{
			return language;
		}
	}
}
