package atrahasis.core.logging;

import java.io.FileNotFoundException;

public class LoggingSetter {

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
