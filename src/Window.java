import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 *  this class is for creating UI window
	 */
	private static final long serialVersionUID = 1L;
	public Game game;
	public Cube cube;
	public static int width = 800;
	public static int height = 800;
	public Window(Game game) {
		this.game = game;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cube = new Cube(this.game.map);
        this.add(this.cube);
        this.setSize(Window.width, Window.height);
        this.setVisible(true);
		// add keyboard listener to the window
		this.addKeyListener((KeyListener) new KeyboardListener(this.game));
	}
}

