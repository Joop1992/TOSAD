package generator;

import domain.BusinessRule;

public class GeneratorJAVA implements Generator{

	@Override
	public String fillTemplate(BusinessRule br) {
		
		return "DIY Java template starterkit:\ngoodluck.";
	}

}
