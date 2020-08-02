package org.blynder.core.logging;

import java.io.FileNotFoundException;

/**
 * Class that will set the output stream and the error stream into the logging.
 */
public class LoggingSetter {

	/**
	 * The setLogging method will change the OutputStream and the ErrorOutputStream
	 * into the Logging custom class. To do so, the CustomPrintStream is created
	 * for both, the output stream and the error stream, the CustomPrintStream
	 * instance will use the Logging to print data.
	 */
	public void setLogging() {
		Logging.oldOutPrintStream = Logging.oldOutPrintStream == null ? System.out : Logging.oldOutPrintStream;
		Logging.oldErrPrintStream = Logging.oldErrPrintStream == null ? System.out : Logging.oldErrPrintStream;
		try {		
			System.setOut(new CustomPrintStream(Logging.LOG_LEVELS.INFO));
			System.setErr(new CustomPrintStream(Logging.LOG_LEVELS.ERROR));
		}
		catch(FileNotFoundException e) {
			System.console().printf("This session log might not work.");
			System.setOut(Logging.oldOutPrintStream);
			System.setErr(Logging.oldErrPrintStream);
		}
	}
	
}
