package atrahasis.core.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;

public class FileSaverTest {

	private FileSaver fileSaver = new FileSaver();
	
	@Test
	public void testFileSaver() {
		try {
			String toSave = "<div>Test</div>";
			String fileName = "fileSaver.html";
			String filePath = fileSaver.saveContent(fileName, toSave);
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
