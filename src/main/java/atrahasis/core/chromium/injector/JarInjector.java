package atrahasis.core.chromium.injector;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class JarInjector {

	public URLClassLoader injectDependency(String... path) {
		try {
			List<URL> urls = new ArrayList<>();
			
			for(String s : path) {
				File f = new File(s);
				urls.add( f.toURI().toURL() );
			}
			
			URL[] toParse = new URL[urls.size()];
			toParse = urls.toArray(toParse);
			
			URLClassLoader loader = new URLClassLoader(
					toParse,
					this.getClass().getClassLoader()
			);
			
			return loader;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
