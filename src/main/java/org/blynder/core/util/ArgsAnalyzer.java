package org.blynder.core.util;

import org.blynder.core.logging.Logging;
import org.blynder.core.logging.Logging.LOG_LEVELS;

/**
 * Object that will read the params given to the application and do what is
 * intended with them.
 */
public class ArgsAnalyzer {

	private String[] args;
	
	public ArgsAnalyzer(String[] args) {
		this.args = args;
	}
	
	public void execute() {
		for(String arg : args) {
			if(arg.equals("-d") || arg.equals("--debug"))
				Logging.LOG_LEVEL = LOG_LEVELS.DEBUG;
		}
	}
}
