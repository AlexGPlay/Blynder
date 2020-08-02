package org.blynder.core.view;

import java.awt.Image;

import org.blynder.core.io.FileManager;

/**
 * Class that allows the user to change the application icon and its name. The
 * WindowProps object can be used in a configurator in order to do so.
 */
public class WindowProps {

	private String icon;
	private String title;
	
	public WindowProps() {
		this(null);
	}
	
	public WindowProps(String title) {
		this(title, null);
	}
	
	public WindowProps(String title, String icon) {
		this.title = title;
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}
	
	public Image getIconAsImage() {
		if(icon == null)
			return null;
		
		return new FileManager().loadImage(icon);
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
