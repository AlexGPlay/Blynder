package atrahasis.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atrahasis.core.browser.BrowserFactory;
import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.browser.IBrowser;
import atrahasis.core.template.Model;

public class Window{
	
	private JFrame frame;
	private JPanel mainPane;
	
	private IBrowser browser;
	
	public Window() {
		frame = new JFrame();
		mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		frame.getContentPane().add(mainPane);
		
		frame.setVisible(false);
		addWindowCloseEvent();
	}
	
	private void addWindowCloseEvent() {
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	public void initializeBrowser(Browser browser) {
		this.browser = new BrowserFactory(browser).getBrowser();
		
		System.out.println("GUI subroutine");
		this.browser.loadHTML("<h1>loading</h1>");
		changeView(this.browser.getUI());
		
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
