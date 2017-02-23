import domain.*;
import generator.Generator;
import generator.GeneratorPLSQL;

public class Main {
	// this class is for testing purpose only!
	public static void main(String args[])   {
		
		
		/*
		STGroup group = new STGroupDir("C:/Users/Ivar/Workspace/TOSAD/templates/plsql",'$','$');
		ST superTemp = group.getInstanceOf("test");
		superTemp.add("amount", "1");
		superTemp.add("target", ":new.Attribute");
		superTemp.add("firstValue", "('dit','dat','en','ook','dit')");
		superTemp.remove("amount");
		superTemp.remove("firstValue");
		superTemp.add("amount", "2");
		superTemp.add("firstValue", "tochnietdit");
		
		String declareSubTemp = "";
		String beginSubTemp = "";
		
		for(int i = 0; i < 2; i++){
			
		}
		
		List<String> strings = Arrays .asList(Pattern.compile("\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\*\\&\\`").split(superTemp.render()));
		
		declareSubTemp = strings.get(0);
		beginSubTemp = strings.get(1);
		ST subTemp = group.getInstanceOf("plsql-ATT-LIST-NLI");
		subTemp.add("declareSub", declareSubTemp);
		subTemp.add("beginSub", beginSubTemp);
		System.out.println(subTemp.render());
		
		*/
		BusinessRuleType brt = new BusinessRuleType("attribute list","ALIS");
		Operator o = Operator.createOperator("In List", "INL");
		
		brt.addOperator(o);
		Value v = new Value("allah,hu,akbar,shalam,attack");
		Value x = new Value("l_passed is not passed. Dus asdf gaat dit gewoon goed.");
		
		Attribute a = new Attribute("warcry");
		
	//	Attribute ac = new Attribute("welAttribuut");
		
		BusinessRule br = new BusinessRuleBuilder().setCode("BRG_VBMG_ALIS_KLTN_AI_02").setDesc("description").setTab("newTable").setWhen("before delete").createBusinessRule();
		
		br.setOperator(o);

		br.setAttribute(a);
		br.setRuleType(brt);

		
		br.addValue(v);
	//	br.addValue(x);

		Generator gen = new GeneratorPLSQL();
		System.out.println(gen.fillTemplate(br));
		
		
	}

}
