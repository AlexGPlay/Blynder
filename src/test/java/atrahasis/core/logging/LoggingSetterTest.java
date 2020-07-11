package atrahasis.core.logging;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggingSetterTest {
	
	private static ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private static PrintStream ps = new PrintStream(baos);
	
	@BeforeClass
	public static void setOutput() {
		Logging.oldOutPrintStream = null;
		Logging.oldErrPrintStream = null;
		Logging.LOG_LEVEL = Logging.LOG_LEVELS.ALL;
		System.setOut(ps);
		System.setErr(ps);
		new LoggingSetter().setLogging();
	}
	
	@Before
	public void resetOutput() throws IOException {
		baos.reset();
	}
	
	@Test
	public void testOutPrint() {
		System.out.print("Test print");
		assertTrue(baos.toString().contains("[INFO]"));
		assertTrue(baos.toString().contains("Test print"));
	}
	
	@Test
	public void testOutPrintln() {
		System.out.println("Test println");
		assertTrue(baos.toString().contains("[INFO]"));
		assertTrue(baos.toString().contains("Test println"));
	}
	
	@Test
	public void testOutPrintf() {
		System.out.printf("Test printf");
		assertTrue(baos.toString().contains("[INFO]"));
		assertTrue(baos.toString().contains("Test printf"));
	}
	
	@Test
	public void testErrPrint() {
		System.err.print("Test print");
		assertTrue(baos.toString().contains("[ERROR]"));
		assertTrue(baos.toString().contains("Test print"));
	}
	
	@Test
	public void testErrPrintln() {
		System.err.println("Test println");
		assertTrue(baos.toString().contains("[ERROR]"));
		assertTrue(baos.toString().contains("Test println"));
	}
	
	@Test
	public void testErrPrintf() {
		System.err.printf("Test printf");
		assertTrue(baos.toString().contains("[ERROR]"));
		assertTrue(baos.toString().contains("Test printf"));
	}

}
