import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame{
	public Window() {
		getContentPane().setLayout(new GridLayout());
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				JPanel panel = new JPanel();
				panel.setBackground(Color.red);
				getContentPane().add(panel);
			}
		}
		// add keyboard listener to the window
		this.addKeyListener((KeyListener) new KeyboardListener());
	}
}
