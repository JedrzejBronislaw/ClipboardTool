package glowny;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Okno extends JFrame implements ActionListener {

	JButton przycisk = new JButton("Wy³acz");
	
	private static final long serialVersionUID = 1L;

	public Okno() {
		super("Schowek In¿yniera");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(200, 100));
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		ustawKomponenty();
		
		setVisible(true);
	}
	
	private void ustawKomponenty()
	{
		przycisk.addActionListener(this);
		przycisk.setBackground(Color.yellow);
		add(przycisk);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Glowny.timer.czyDziala())
		{
			//wy³¹czenie
			Glowny.timer.wstrzymaj();
			przycisk.setText("W³¹cz");
			przycisk.setBackground(Color.gray);
		} else
		{
			//w³¹czanie
			Glowny.timer.wznow();
			przycisk.setText("Wy³¹cz");
			przycisk.setBackground(Color.yellow);
		}
		
	}
}
