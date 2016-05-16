import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	public Game game;
	public Cube cube;
	public Window(Game game) {
		this.game = game;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cube = new Cube(this.game.map);
        this.add(this.cube);
        this.pack();
        this.setVisible(true);
		// add keyboard listener to the window
		this.addKeyListener((KeyListener) new KeyboardListener(this.game));
	}
}

