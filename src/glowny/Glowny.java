package glowny;

import java.awt.EventQueue;

public class Glowny {
	public static Timer timer = new Timer();
	public static Okno  okno;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		timer.start();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				okno = new Okno();
			}
		});
	}

}
