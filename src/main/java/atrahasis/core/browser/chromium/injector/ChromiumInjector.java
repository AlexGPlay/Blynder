package atrahasis.core.browser.chromium.injector;

import java.lang.reflect.Field;
import java.net.URLClassLoader;

public class ChromiumInjector {

	private String basePath = "chromium/%s/java-cef-build-bin/bin/";
	private String extraPath = "chromium/class/Compilation.jar";
	private String libPath = basePath;
	
	public URLClassLoader addJar(OSDetector.OS os) {
		String path = basePath;

		if(os.equals(OSDetector.OS.Linux))
			path = getLinuxPath();

		if(os.equals(OSDetector.OS.Windows))
			path = getWindowsPath();

		if(os.equals(OSDetector.OS.Mac))
			path = getMacPath();

		URLClassLoader loader = new ClassLoaderFactory().injectDependency(path, extraPath);
		setLibraryPath(libPath);
		return loader;
	}
	
	private String getLinuxPath() {
		basePath = String.format(basePath, "linux64");
		libPath = basePath + "lib/linux64";
		basePath += "jcef.jar";
		
		return String.format(basePath, "linux64");
	}

	private String getWindowsPath() {
		basePath = String.format(basePath, "win64");
		libPath = basePath + "lib/win64";
		basePath += "jcef.jar";
		
		return String.format(basePath, "win64");
	}
	
	private String getMacPath() {
		return basePath;
	}
	
	private void setLibraryPath(String path){
	    System.setProperty("java.library.path", path);
	 
	    //set sys_paths to null
	    Field sysPathsField;
		try {
			sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
		    sysPathsField.setAccessible(true);
		    sysPathsField.set(null, null);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
