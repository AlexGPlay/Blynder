package atrahasis.core.logging;

import java.io.PrintStream;
import java.util.Date;

public class Logging{

	public static PrintStream oldOutPrintStream, oldErrPrintStream;
	
	public enum LOG_LEVELS {
		NONE(0),
		INFO(1),
		ERROR(2),
		DEBUG(3),
		ALL(4);
		
		private final int levelCode;
		LOG_LEVELS(int levelCode) { this.levelCode = levelCode; }
		public int getLevelCode() { return this.levelCode; }
	}
	
	public static LOG_LEVELS LOG_LEVEL = LOG_LEVELS.ERROR;
	
	public static int info(String info) {
		return sendLog(LOG_LEVELS.INFO, info);
	}
	
	public static int error(String error) {
		return sendLog(LOG_LEVELS.ERROR, error);
	}
	
	public static int debug(String debug) {
		return sendLog(LOG_LEVELS.DEBUG, debug);
	}
	
	private static int sendLog(LOG_LEVELS sendLevel, String message) {
		if(LOG_LEVEL.getLevelCode() < sendLevel.getLevelCode()) return sendLevel.getLevelCode();
		oldOutPrintStream.printf("[%s] - [%s] - %s\n", sendLevel.name(), new Date(), message);
		return sendLevel.getLevelCode();
	}
	
}
