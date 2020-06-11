package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Timer extends Thread {
	String text, oldText;
	boolean end = false;
	boolean pause = false;
	
	
	public Timer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		while (!end)
		{
			synchronized (this) {
				while(pause)
					if (pause)
						try {
							wait();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
			}
			
			oldText = text;
			getFromClipboard();
			if (text.equals(oldText))
			{
				process();
				setToClipboard();
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void end()
	{
		end = true;
	}
	
	public void turnOff()
	{
		synchronized (this) {
			pause = true;
		}
	}
	
	public void turnOn()
	{
		synchronized (this) {
			pause = false;
			this.notify();
		}
	}

	public boolean isWorking()
	{
		synchronized (this) {
			return !pause;
		}
	}
	
	
	private void getFromClipboard()
	{
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor))
		{
			try {
				text = (String) clipboard.getData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException e) {
				System.out.println("Exception: UnsupportedFlavorException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Exception: IOException");
				e.printStackTrace();
			}
			
			System.out.println("Clipboard : " + text);
		}
	}
	
	private void process()
	{
		text = text.replace("\n", " ");
	}
	
	private void setToClipboard()
	{
		Clipboard schowek = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		schowek.setContents(new StringSelection(text), null);
	}
}
