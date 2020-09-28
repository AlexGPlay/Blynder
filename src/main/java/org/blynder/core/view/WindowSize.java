package org.blynder.core.view;

/**
 * Class that allows the user to set the window size. It can be gave to the
 * configurator in order to make the size the default one or can be asked for
 * in a controller to change the view size.
 */
public class WindowSize {

	public enum Sizing{
		AUTO, MANUAL
	}
	
	private Sizing sizing;
	private int heigth, width;
	private Boolean resizable;
	
	public WindowSize() {
		this.sizing = Sizing.AUTO;
		this.resizable = null;
	}
	
	public WindowSize(boolean resizable) {
		this.sizing = Sizing.AUTO;
		this.resizable = resizable;
	}
	
	public WindowSize(int width, int heigth) {
		this(width, heigth, true);
	}
	
	public WindowSize(int width, int heigth, boolean resizable) {
		setDimensions(width, heigth);
		this.resizable = resizable;
	}
	
	public void setWidth(int width) {
		setDimensions(width, heigth);
	}
	
	public void setHeigth(int heigth) {
		setDimensions(width, heigth);
	}
	
	public void setDimensions(int width, int heigth) {
		this.heigth = heigth;
		this.width = width;
		this.sizing = Sizing.MANUAL;
	}
	
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	
	public Sizing getSizing() {
		return sizing;
	}
	
	public int getHeigth() {
		return heigth;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Boolean isResizable() {
		return resizable;
	}
	
}
