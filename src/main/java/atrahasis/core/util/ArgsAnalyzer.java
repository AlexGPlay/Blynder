package atrahasis.core.util;

import atrahasis.core.logging.Logging;
import atrahasis.core.logging.Logging.LOG_LEVELS;

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
