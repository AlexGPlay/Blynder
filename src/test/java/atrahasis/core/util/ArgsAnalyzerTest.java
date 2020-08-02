package atrahasis.core.util;

import static org.junit.Assert.*;

import org.blynder.core.logging.Logging;
import org.blynder.core.logging.Logging.LOG_LEVELS;
import org.blynder.core.util.ArgsAnalyzer;
import org.junit.Test;

public class ArgsAnalyzerTest {
	
	@Test
	public void testDebuggerFlag() {
		Logging.LOG_LEVEL = LOG_LEVELS.ERROR;
		assertEquals(LOG_LEVELS.ERROR, Logging.LOG_LEVEL);
		
		String[] args1 = {
				"-d"
		};
		new ArgsAnalyzer(args1).execute();
		
		assertEquals(Logging.LOG_LEVELS.DEBUG, Logging.LOG_LEVEL);
		
		Logging.LOG_LEVEL = LOG_LEVELS.ERROR;
		assertEquals(LOG_LEVELS.ERROR, Logging.LOG_LEVEL);
		
		String[] args2 = {
				"--debug"
		};
		new ArgsAnalyzer(args2).execute();
		assertEquals(LOG_LEVELS.DEBUG, Logging.LOG_LEVEL);
	}

}
