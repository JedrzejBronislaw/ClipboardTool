package main;

import java.awt.EventQueue;

public class Main {
	
	public static Timer timer = new Timer();
	public static Window window;

	public static void main(String[] args) {
		timer.start();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				window = new Window();
			}
		});
	}

}
