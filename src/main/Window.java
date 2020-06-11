package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {

	JButton button = new JButton("Turn off");
	
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
	
	private void placeComponents()
	{
		button.addActionListener(this);
		button.setBackground(Color.yellow);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Main.timer.isWorking())
		{
			//wy³¹czenie
			Main.timer.turnOff();
			button.setText("Turn on");
			button.setBackground(Color.gray);
		} else
		{
			//w³¹czanie
			Main.timer.turnOn();
			button.setText("Turn off");
			button.setBackground(Color.yellow);
		}
		
	}
}
