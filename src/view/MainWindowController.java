package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import repeater.ServiceOperator;

public class MainWindowController implements Initializable{
	
	private static final Color onColor = Color.YELLOW;
	private static final Color offColor = Color.GRAY;

	@FXML
	private Button button;

	private ServiceOperator operator;
	
	public void setOperator(ServiceOperator operator) {
		this.operator = operator;
		updateButton();
	}
	
	private void updateButton() {
		if (operator == null) return;
		
		if (operator.isWorking()) {
			button.setText("Turn off");
			button.setBackground(getBackground(onColor));
		} else {
			button.setText("Turn on");
			button.setBackground(getBackground(offColor));
		}
	}

	private Background getBackground(Color color) {
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		button.setOnAction(e -> {
			if (operator == null) return;
			
			if(operator.isWorking())
				operator.turnOff();
			else
				operator.turnOn();
			
			updateButton();
		});
	}
}
