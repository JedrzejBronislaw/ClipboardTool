package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {

	private static final Color onColor = Color.yellow;
	private static final Color offColor = Color.gray;

	private JButton button = new JButton("Turn off");
	
	private static final long serialVersionUID = 1L;

	public Window() {
		super("Engineer's clipboard");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(200, 100));
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		placeComponents();
		
		setVisible(true);
	}
	
	private void placeComponents() {
		button.addActionListener(this);
		button.setBackground(onColor);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Main.timer.isWorking()) {
			Main.timer.turnOff();
			button.setText("Turn on");
			button.setBackground(offColor);
		} else {
			Main.timer.turnOn();
			button.setText("Turn off");
			button.setBackground(onColor);
		}
		
	}
}
