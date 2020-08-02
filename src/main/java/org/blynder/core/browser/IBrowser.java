package org.blynder.core.browser;

import java.awt.Component;

/**
 * 
 * Interface that allows multiple browser implementations. The implementations
 * in the release are the chromium bindings implementation and the javafx browser.
 *
 */
public interface IBrowser {

	/**
	 * 
	 * Loads an html in the browser component.
	 * @param html that is going to be loaded.
	 * 
	 */
	public void loadHTML(String html);
	
	/**
	 * 
	 * Returns the browser awt component. This method is used to load the component
	 * in the window.
	 * @return awt.component
	 * 
	 */
	public Component getUI();
	
}
