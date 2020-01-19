package atrahasis.core.view;

import javax.swing.JPanel;

import atrahasis.core.Application;
import atrahasis.core.template.Model;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * 
 * A wrapper class for the GUI of the framework, it makes the project able to
 * change the implementation of the window without the other parts of the project
 * needing the change anything.
 *
 */
public class Window{
	
	private Stage mainStage;
	
	public Window(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	public void setSwing(JPanel panel) {
		
		Platform.runLater(() -> {
			SwingNode node = new SwingNode();
			node.setContent(panel);
			StackPane pane = new StackPane();
			pane.getChildren().add(node);
			
			mainStage.setScene(new Scene(pane,150,150));
			mainStage.sizeToScene();
			mainStage.show();
		});
		
	}
	
	public void setHtml(String html, Model model) {
		
		Platform.runLater( () -> {
			String code = new HTMLHandler().getHtml(html, model);
			
			WebView browser = new WebView();
			WebEngine webEngine = browser.getEngine();
			webEngine.setJavaScriptEnabled(true);
			
			webEngine.getLoadWorker().stateProperty().addListener(
	            new ChangeListener<Worker.State>() {
	                public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
	                    if (newState == Worker.State.SUCCEEDED) {                        
	                        JSObject jso = (JSObject) webEngine.executeScript("window");
	                        jso.setMember("app", new ViewClass());
	                    }
	                }
	            }
	        );
            
			webEngine.loadContent(code);
			Scene mainScene = new Scene(browser);
			
			mainStage.setScene(mainScene);
			mainStage.sizeToScene();
			mainStage.show();
		});
	}
	
	public class ViewClass{
		
		public void navigate(String url) {
			Application.navigate(url);
		}
		
	}

}
