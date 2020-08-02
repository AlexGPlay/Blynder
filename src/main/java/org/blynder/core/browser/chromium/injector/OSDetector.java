package org.blynder.core.browser.chromium.injector;
public class OSDetector {

	public static enum OS {
		Windows,
		Mac,
		Linux
	}
	
	public OS getOS() {
		String name = System.getProperty("os.name").toLowerCase();
		
		if(name.contains("win"))
			return OS.Windows;
		
		else if(name.contains("mac"))
			return OS.Mac;
		
		else
			return OS.Linux;
	}
	
}
