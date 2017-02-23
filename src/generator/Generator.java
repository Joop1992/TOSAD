package generator;

import domain.BusinessRule;
import org.stringtemplate.v4.ST;

public interface Generator {
	// returns the stringtemplate of
	ST getSuperTemplate(String name, String type);

	public String fillTemplate(BusinessRule br);

}
