package atrahasis.core.logging;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.blynder.core.logging.Logging;
import org.blynder.core.logging.Logging.LOG_LEVELS;
import org.junit.Before;
import org.junit.Test;

public class LoggingTest {

	private String[] toPrint = {
			"INFO MESSAGE",
			"ERROR MESSAGE",
			"DEBUG MESSAGE"
	};
	
	private ByteArrayOutputStream baos;
	private PrintStream ps;
	
	@Before
	public void setOutputStream() {
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		Logging.oldOutPrintStream = ps;
	}
	
	@Test
	public void testNone() {
		Logging.LOG_LEVEL = LOG_LEVELS.NONE;
		Logging.info(toPrint[0]);
		Logging.error(toPrint[1]);
		Logging.debug(toPrint[2]);
		assertTrue(baos.toString().isEmpty());
	}
	
	@Test
	public void testInfo() {
		Logging.LOG_LEVEL = LOG_LEVELS.INFO;
		Logging.info(toPrint[0]);
		Logging.error(toPrint[1]);
		Logging.debug(toPrint[2]);
		assertTrue(baos.toString().contains(toPrint[0]));
		assertFalse(baos.toString().contains(toPrint[1]));
		assertFalse(baos.toString().contains(toPrint[2]));
	}
	
	@Test
	public void testError() {
		Logging.LOG_LEVEL = LOG_LEVELS.ERROR;
		Logging.info(toPrint[0]);
		Logging.error(toPrint[1]);
		Logging.debug(toPrint[2]);
		assertTrue(baos.toString().contains(toPrint[0]));
		assertTrue(baos.toString().contains(toPrint[1]));
		assertFalse(baos.toString().contains(toPrint[2]));
	}
	
	@Test
	public void testDebug() {
		Logging.LOG_LEVEL = LOG_LEVELS.DEBUG;
		Logging.info(toPrint[0]);
		Logging.error(toPrint[1]);
		Logging.debug(toPrint[2]);
		assertTrue(baos.toString().contains(toPrint[0]));
		assertTrue(baos.toString().contains(toPrint[1]));
		assertTrue(baos.toString().contains(toPrint[2]));
	}
	
	@Test
	public void testAll() {
		Logging.LOG_LEVEL = LOG_LEVELS.ALL;
		Logging.info(toPrint[0]);
		Logging.error(toPrint[1]);
		Logging.debug(toPrint[2]);
		assertTrue(baos.toString().contains(toPrint[0]));
		assertTrue(baos.toString().contains(toPrint[1]));
		assertTrue(baos.toString().contains(toPrint[2]));
	}


}
