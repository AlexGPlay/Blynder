package atrahasis.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atrahasis.core.browser.BrowserFactory;
import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.exception.IllegalViewException;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import atrahasis.core.browser.IBrowser;
import atrahasis.core.template.Model;

/**
 * 
 * A wrapper class for the GUI of the framework, it makes the project able to
 * change the implementation of the window without the other parts of the project
 * needing the change anything.
 *
 */
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
	
	public void setView(Object view, Model model) throws IllegalViewException {
		if(view instanceof JPanel)
			setSwing((JPanel)view);
		
		else if(view instanceof Scene)
			setJavaFx((Scene)view);
		
		else if(view instanceof String) {
			String viewName = (String)view;
			if(viewName.contains(".html"))
				setHtml(viewName, model);
			
			else if(viewName.contains(".fxml"))
				setFxml(viewName);
		}
	}
	
	private void setSwing(JPanel panel) {
		changeView(panel);
	}
	
	private void setHtml(String html, Model model) {
		String code = new HTMLHandler().getHtml(html, model);
		changeView(browser.getUI());
		browser.loadHTML(code);
		frame.setSize(800,800);
	}
	
	private void setFxml(String fxml) {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
			setJavaFx(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void setJavaFx(Scene scene) {
		JFXPanel panel = new JFXPanel();
		panel.setScene(scene);
		changeView(panel);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

}
