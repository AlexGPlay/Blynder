package atrahasis.core.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class Thymeleaf {

	private TemplateEngine engine = new TemplateEngine();
	
	public String processHtml(String template, Model model) {
		Context ctx = new Context();
		ctx.setVariables(model.getVariables());
		return engine.process(template, ctx);
	}
	
}
