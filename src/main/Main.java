package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repeater.RepeaterService;
import repeater.ServiceOperator;
import task.LineSeparatorRemover;
import view.WindowLoader;

public class Main extends Application {

	private static final String WINDOW_TITLE = "Engineer's clipboard";

	public static void main(String[] args) {
		Main.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		WindowLoader loader = new WindowLoader();
		ServiceOperator timer = new RepeaterService(new LineSeparatorRemover());
		
		loader.load();
		
		createAndShowScene(primaryStage, loader.getNode());
		
		timer.turnOn();
		loader.getController().setOperator(timer);
	}

	private void createAndShowScene(Stage stage, Parent parent) {
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle(WINDOW_TITLE);
		stage.setResizable(false);
		stage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		stage.show();
	}
}
