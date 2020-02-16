package atrahasis.core.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atrahasis.core.chromium.Browser;
import atrahasis.core.template.Model;

public class Window{
	
	private JFrame frame;
	private JPanel mainPane;
	
	private Browser browser;
	
	public Window() {
		frame = new JFrame();
		mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		frame.getContentPane().add(mainPane);
		
		frame.setVisible(false);
	}
	
	public void initializeChromium() {
		browser = new Browser();
		
		System.out.println("GUI subroutine");
		browser.loadHTML("<h1>loading</h1>");
		changeView(browser.getUI());
		
		frame.setSize(100, 100);
		frame.setVisible(true);
		frame.setVisible(false);
	}
	
	private void changeView(Component component) {
		mainPane.removeAll();
		mainPane.add(component, BorderLayout.CENTER);
		frame.pack();
		
		frame.repaint();
	}
	
	public void setSwing(JPanel panel) {
		changeView(panel);
	}
	
	public void setHtml(String html, Model model) {
		String code = new HTMLHandler().getHtml(html, model);
		changeView(browser.getUI());
		browser.loadHTML(code);
		frame.setSize(800,800);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

}
