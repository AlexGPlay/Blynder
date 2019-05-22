package swingmvc.io;

import java.io.IOException;
import java.net.URISyntaxException;

public class FileManager {

	public String loadContent(String file) throws IOException, URISyntaxException {
		return new FileLoader().getContent(file);
	}
	
}
