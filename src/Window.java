import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	public Client client;
	public Window(Client client) {
		this.client = client;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Cube(client.game.map));
        this.pack();
        this.setVisible(true);
		// add keyboard listener to the window
		this.addKeyListener((KeyListener) new KeyboardListener(client.game));
	}
}

