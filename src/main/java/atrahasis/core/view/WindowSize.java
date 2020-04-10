package atrahasis.core.view;

public class WindowSize {

	public enum Sizing{
		AUTO, MANUAL
	}
	
	private Sizing sizing;
	private int heigth, width;
	private boolean resizable;
	
	public WindowSize() {
		this(true);
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
	
	public boolean isResizable() {
		return resizable;
	}
	
}
