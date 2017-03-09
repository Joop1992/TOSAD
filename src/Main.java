import domain.*;
import generator.Generator;
import generator.GeneratorPLSQL;

public class Main {
	// this class is for testing purpose only!
	public static void main(String args[])   {
		BusinessRuleType brt = new BusinessRuleType("attribute list","ALIS");
		Operator o = Operator.createOperator("In List", "INL");
		
		brt.addOperator(o);
		Value v = new Value("allah,hu,akbar,shalam,attack");
		
		Attribute a = new Attribute("warcry");
		BusinessRule br = new BusinessRuleBuilder().setCode("BRG_VBMG_ALIS_KLTN_AI_02").setDesc("description").setTab("newTable").setWhen("before delete").createBusinessRule();
		
		br.setOperator(o);
		br.setAttribute(a);
		br.setRuleType(brt);
		br.addValue(v);

		Generator gen = new GeneratorPLSQL();
		System.out.println(gen.fillTemplate(br));
	}

}
