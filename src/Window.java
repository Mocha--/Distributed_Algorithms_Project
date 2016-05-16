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
        this.add(new Cube());
        this.pack();
        this.setVisible(true);
		// add keyboard listener to the window
		this.addKeyListener((KeyListener) new KeyboardListener(client.game));
	}
}

class Cube extends JPanel {
	private int height = 22;   
    private int width = 22;        
    private int size = 20;   
     
    public Cube()
    {
        this.setPreferredSize(new Dimension(height * size + 10 ,width * size + 35));    
    }
     
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        for (int i = 0; i < Map.width; i++)
        {
            for (int j = 0; j < Map.height; j++)
            {
                g.fill3DRect(i * width, j * height, size, size, true);
            }
        }
    }
}
