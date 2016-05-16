import java.awt.*;
import java.util.ArrayList;

public class Map {

	public static int width = 50;
	public static int height = 20;
	public static Color EMPTY_COLOR = Color.GRAY;

	public Color[][] pos;
	public ArrayList<Snake> snakes;
	
	public Map(ArrayList<Snake> snakes){
		this.pos = new Color[Map.height][Map.width];
		for (int row = 0 ; row <= Map.height - 1 ; row++){
			for( int col = 0 ; col <= Map.width - 1; col++){
				this.setPos(row, col, Map.EMPTY_COLOR);
			}
		}
		this.snakes = snakes;
		this.refresh();
	}

	public void refresh() {
		for (int row = 0 ; row <= Map.height - 1 ; row++) {
			for( int col = 0 ; col <= Map.width - 1; col++) {
				this.setPos(row, col, Map.EMPTY_COLOR);
			}
		}
		for(Snake snake : this.snakes) {
			this.drawSnake(snake);
		}
	}

	public void drawSnake(Snake snake){
		for (Coordinate coor : snake.body){
			this.setPos(coor.row, coor.col, snake.color);
		}
	}

	public void setPos(int row, int col, Color color){
		this.pos[row][col] = color;
	}
}
