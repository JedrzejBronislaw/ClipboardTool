package main;

import java.awt.EventQueue;

public class Main {
	public static Timer timer = new Timer();
	public static Window  window;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		timer.start();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				window = new Window();
			}
		});
	}

}
