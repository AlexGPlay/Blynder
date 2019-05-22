package swingmvc.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swingmvc.io.FileManager;

public class HTMLHandler {

	public String getHtml(String file) {
		List<String> matches = new ArrayList<>();
		String res = "<body>Error</body>";
		try {
			res = new FileManager().loadContent(file);
		} catch (IOException | URISyntaxException e) {}
		
		Matcher m = Pattern.compile("href=\".\"")
				.matcher(res);
		
		while(m.find())
			matches.add(m.group());
		
		for(String s : matches) {
			String test = s.replace("href=", "app.navigate(");
			test += ")";
			
			test = "href='' onclick='" + test + "'";
			res = res.replace(s, test);
		}
		
		return res;
	}
	
}
