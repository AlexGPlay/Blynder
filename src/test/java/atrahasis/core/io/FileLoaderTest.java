package atrahasis.core.io;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;

public class FileLoaderTest {

	private FileLoader loader = new FileLoader();
	
	@Test
	public void testLoader() {
		try {
			String content = loader.getContent("test.html");
			String expectedContent = "<div>Test</div>";
			assertEquals(expectedContent, content);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}

}
