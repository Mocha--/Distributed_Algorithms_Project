import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Cube extends JPanel {
	private int height = 22;   
    private int width = 22;        
    private int size = 20;
    public Map map;
     
    public Cube(Map map)
    {
    	this.map = map;
        this.setPreferredSize(new Dimension(height * size + 10 ,width * size + 35));    
    }
     
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.black);
        for (int i = 0; i < Map.height; i++)
        {
            for (int j = 0; j < Map.width; j++)
            {
            	if(this.map.pos[i][j] > 0) {
            		g.fill3DRect(i * width, j * height, size, size, true);
            	}
            }
        }
    }
}
