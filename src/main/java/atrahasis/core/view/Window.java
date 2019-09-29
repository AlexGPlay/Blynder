package atrahasis.core.view;

import javax.swing.JPanel;

import atrahasis.core.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

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
			mainStage.show();
			mainStage.sizeToScene();
		});
		
	}
	
	public void setHtml(String html) {
		
		Platform.runLater( () -> {
			WebView browser = new WebView();
			WebEngine webEngine = browser.getEngine();
			webEngine.loadContent(html);
			
			JSObject win = 
                    (JSObject) webEngine.executeScript("window");
            win.setMember("app", new ViewClass());
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
