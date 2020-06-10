package glowny;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Timer extends Thread {
	String tekst, staryTekst;
	boolean koniec = false;
	boolean pausa = false;
	
	
	public Timer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		while (!koniec)
		{
			synchronized (this) {
				while(pausa)
					if (pausa)
						try {
							wait();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
			}
			
			staryTekst = tekst;
			pobierzZeSchowka();
			if (tekst.equals(staryTekst))
			{
				przetworz();
				ZapiszDoSchowka();
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void zakoncz()
	{
		koniec = true;
	}
	
	public void wstrzymaj()
	{
		synchronized (this) {
			pausa = true;
		}
	}
	
	public void wznow()
	{
		synchronized (this) {
			pausa = false;
			this.notify();
		}
	}

	public boolean czyDziala()
	{
		synchronized (this) {
			return !pausa;
		}
	}
	
	
	private void pobierzZeSchowka()
	{
		Clipboard schowek = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		if (schowek.isDataFlavorAvailable(DataFlavor.stringFlavor))
		{
			try {
				tekst = (String) schowek.getData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException e) {
				System.out.println("Wyj¹tek: UnsupportedFlavorException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Wyj¹tek: IOException");
				e.printStackTrace();
			}
			
			System.out.println("Schowek : " + tekst);
		}
	}
	
	private void przetworz()
	{
		tekst = tekst.replace("\n", " ");
	}
	
	private void ZapiszDoSchowka()
	{
		Clipboard schowek = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		schowek.setContents(new StringSelection(tekst), null);
	}
}
