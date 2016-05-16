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
		if(keyCode == 37 && this.game.mySnake.direction != "right") {
			// left
			direction = "left";
			changeDirection(direction);
		} else if(keyCode == 38 && this.game.mySnake.direction != "down") {
			// up
			direction = "up";
			changeDirection(direction);
		} else if(keyCode == 39 && this.game.mySnake.direction != "left") {
			// right
			direction = "right";
			changeDirection(direction);
		} else if(keyCode == 40 && this.game.mySnake.direction != "up") {
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

