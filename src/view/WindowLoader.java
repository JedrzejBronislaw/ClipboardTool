package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;

public class WindowLoader {
	
	private final static String FILE_PATH = "/MainWindow.fxml";
	
	@Getter
	private Parent node;
	@Getter
	private MainWindowController controller;

	public void load() {
		Parent node;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FILE_PATH));
		
    	try {
    		node = loader.load();
    	} catch (IOException e) {
    		e.printStackTrace();
    		node = null;
    	}

    	this.node = node;
    	this.controller = loader.getController();
	}
}
