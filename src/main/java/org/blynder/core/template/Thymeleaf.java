package org.blynder.core.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 
 * A wrapper that contains the ThymeLeaf logic to process an HTML and insert
 * the variables given by the user. The variables will be inside a Model class.
 *
 */
public class Thymeleaf implements ITemplateEngine{

	private TemplateEngine engine = new TemplateEngine();
	
	/**
	 * 
	 * This method will process a given HTML using the ThymeLeaf template engine.
	 * This will allow the user to make HTML templates.
	 * @param template
	 * The HTML that has to be processed.
	 * @param model
	 * The model that contains the user variables.
	 * @return
	 * A string that contains the processed HTML code.
	 * 
	 */
	public String processHtml(String template, Model model) {
		Context ctx = new Context();
		ctx.setVariables(model.getVariables());
		return engine.process(template, ctx);
	}
	
}
