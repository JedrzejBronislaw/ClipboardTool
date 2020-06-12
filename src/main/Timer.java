package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Timer extends Thread implements ServiceOperator{
	
	private static final int INTERVAL = 1000;
	
	private String text;
	private String oldText;
	private boolean end = false;
	private boolean pause = false;
	
	private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	public Timer() {}
	
	@Override
	public void run() {
		while (!end) {
			synchronized (this) {
				while(pause)
					try {
						wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			}
			
			action();
			sleep();
		}
	}

	private void action() {
		oldText = text;
		text = getFromClipboard();
		System.out.println("Clipboard : " + text);
		
		if (text.equals(oldText)) {
			text = process(text);
			setToClipboard(text);
		}
	}

	private String process(String text) {
		return text.replace("\n", " ");
	}

	private void sleep() {
		try {
			sleep(INTERVAL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void end() {
		end = true;
	}
	
	@Override
	public synchronized void turnOff() {
		pause = true;
	}
	
	@Override
	public synchronized void turnOn() {
		pause = false;
		this.notify();
	}

	@Override
	public synchronized boolean isWorking() {
		return !pause;
	}
	

	private String getFromClipboard() {
		String text = null;
		
		if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor))
			try {
				text = (String) clipboard.getData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException|IOException e) {
				e.printStackTrace();
			}
		
		return text;
	}
	
	private void setToClipboard(String text) {
		try {
			clipboard.setContents(new StringSelection(text), null);
		} catch(IllegalStateException e) {
		}
	}
}
