package org.blynder.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.blynder.core.browser.BrowserFactory;
import org.blynder.core.browser.IBrowser;
import org.blynder.core.browser.BrowserFactory.Browser;
import org.blynder.core.configurator.IConfigurator;
import org.blynder.core.exception.IllegalViewException;
import org.blynder.core.template.ITemplateEngine;
import org.blynder.core.template.Model;
import org.blynder.core.view.WindowSize.Sizing;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
	
	private ITemplateEngine templateEngine;
	private IBrowser browser;
	private WindowSize defaultSize;
	private WindowProps defaultProps;
	private boolean changedSize = false;
	
	public Window(IConfigurator configurator) {
		frame = new JFrame();
		this.defaultSize = configurator.getWindowSize();
		this.defaultProps = configurator.getWindowProps();
		this.templateEngine = configurator.getTemplateEngine();
		
		mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		
		frame.getContentPane().add(mainPane);
		
		frame.setVisible(false);
		
		addWindowCloseEvent();
		setDefaultWindowSize();
		setDefaultWindowProps();
	}
	
	private void addWindowCloseEvent() {
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	private void setDefaultWindowSize() {
		if(defaultSize.getSizing() == Sizing.AUTO)
			frame.setSize(100,100);
		else
			frame.setSize(defaultSize.getWidth(), defaultSize.getHeigth());
		
		frame.setResizable(defaultSize.isResizable());
	}
	
	private void setDefaultWindowProps() {
		frame.setTitle(defaultProps.getTitle());
		frame.setIconImage(defaultProps.getIconAsImage());
	}
	
	public void initializeBrowser(Browser browser) {
		this.browser = new BrowserFactory(browser).getBrowser();
		
		this.browser.loadHTML("<h1>loading</h1>");
		changeView(this.browser.getUI());
		
		frame.setVisible(true);
		frame.setVisible(false);
	}
	
	private void changeView(Component component) {
		mainPane.removeAll();
		mainPane.add(component, BorderLayout.CENTER);
	}
	
	public void setView(Object view, Model model, WindowSize size) throws IllegalViewException {
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
		
		setWindowSize(size);
	}
	
	private void setSwing(JPanel panel) {
		changeView(panel);
	}
	
	private void setHtml(String html, Model model) {
		String code = new HTMLHandler(templateEngine).getHtml(html, model);
		changeView(browser.getUI());
		browser.loadHTML(code);
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
	
	private void setWindowSize(WindowSize size) {
		if(size.getSizing() == Sizing.AUTO) {
			if(defaultSize.getSizing() == Sizing.AUTO) {
				frame.pack();
			}
			else if(defaultSize.getSizing() == Sizing.MANUAL && changedSize) {
				frame.setSize(defaultSize.getWidth(), defaultSize.getHeigth());
			}
		}
		
		else if(size.getSizing() == Sizing.MANUAL) {
			frame.setSize(size.getWidth(), size.getHeigth());
			changedSize = true;
		}
		
		frame.setResizable(size.isResizable());
		
		frame.revalidate();
		frame.repaint();
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
