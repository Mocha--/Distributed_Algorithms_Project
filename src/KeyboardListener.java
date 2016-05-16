import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyboardListener extends KeyAdapter{
	public Game game;
	
	public KeyboardListener(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String direction = "up";
		if(keyCode == 37) {
			// left
			direction = "left";
			changeDirection(direction);
		} else if(keyCode == 38) {
			// up
			direction = "up";
			changeDirection(direction);
		} else if(keyCode == 39) {
			// right
			direction = "right";
			changeDirection(direction);
		} else if(keyCode == 40) {
			// down
			direction = "down";
			changeDirection(direction);
		}
		System.out.println(direction);
	}
	
	private void changeDirection(String direction) {
		// change snake direction
		this.game.mySnake.direction = direction;
	}
}

