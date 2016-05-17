import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Cube extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int height = 22;   
    private int width = 22;        
    private int size = 20;
    public Map map;
     
    public Cube(Map map) {
    	this.map = map;
        this.setPreferredSize(new Dimension(height * size + 10 ,width * size + 35));    
    }
     
    public void paintComponent(Graphics g) {

        for (int row = 0; row < Map.height; row++) {
            for (int col = 0; col < Map.width; col++) {
                g.setColor(this.map.pos[row][col]);
                g.fill3DRect(col * width, row * height, size, size, true);
            }
        }
    }
}
