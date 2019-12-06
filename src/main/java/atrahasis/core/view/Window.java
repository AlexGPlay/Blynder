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
		
		browser = new Browser();
		frame.setVisible(true);
	}
	
	private void changeView(Component component) {
		mainPane.removeAll();
		mainPane.add(component, BorderLayout.CENTER);
		frame.pack();
	}
	
	public void setSwing(JPanel panel) {
		changeView(panel);
	}
	
	public void setHtml(String html, Model model) {
		String code = new HTMLHandler().getHtml(html, model);
		browser.loadHTML(code);
		changeView(browser.getUI());	
	}

}
