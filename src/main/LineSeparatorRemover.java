package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class LineSeparatorRemover implements Runnable {

	private String text;
	private String oldText;
	private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	@Override
	public void run() {
		oldText = text;
		text = getFromClipboard(text);
		System.out.println("Clipboard: " + text);
		
		if (!text.equals(oldText)) {
			text = process(text);
			setToClipboard(text);

			System.out.println("   -> " + text);
		}
	}

	private String process(String text) {
		return text.replace("\n", " ");
	}
	
	private String getFromClipboard(String defaultText) {
		String text = defaultText;
		
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
