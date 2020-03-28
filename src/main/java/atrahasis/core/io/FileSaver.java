package atrahasis.core.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * A built-in FileSaver, given a filename and a string content, this method will
 * create a file with that name and that content.
 *
 */
public class FileSaver {

	/**
	 * 
	 * Given a filename and a string, this method will store that content into
	 * a file with the given name. If the file already exists, this method
	 * will return the path to the file and won't write the file.
	 * @param fileName is the name of the file
	 * @param content that will be stored in the file
	 * @return The path to the file as string
	 * @throws IOException if there is a problem writing the file.
	 * 
	 */
	public String saveContent(String fileName, String content) throws IOException {
		File f = new File(fileName);
		if(f.exists())
			f.delete();
		
		return writeFile(f, content);
	}
	
	private String writeFile(File file, String content) throws IOException {
		if(file.getParentFile() != null)
			file.getParentFile().mkdirs();
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(content);
		fileWriter.close();
		
		return file.toURI().toString();
	}
	
}
