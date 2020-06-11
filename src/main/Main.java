package main;

import java.awt.EventQueue;

public class Main {
	
	private static Timer timer = new Timer();
	private static Window window;

	public static void main(String[] args) {
		timer.start();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				window = new Window();
				window.setOperator(timer);
			}
		});
	}

}
