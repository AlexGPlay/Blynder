package atrahasis.core.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;

public class FileManagerTest {

	private FileManager fileManager = new FileManager();
	
	@Test
	public void testLoad() {
		try {
			String content = fileManager.loadContent("test.html");
			String expectedContent = "<div>Test</div>";
			assertEquals(expectedContent, content);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testFileSave() {
		try {
			String toSave = "<div>Test</div>";
			String fileName = "fileSaver.html";
			String filePath = fileManager.saveContent(fileName, toSave);
			File f = new File(fileName);
			f.delete();
			assertNotNull(filePath);
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}

}
