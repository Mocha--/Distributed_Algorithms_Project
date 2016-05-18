import java.awt.*;
import java.util.ArrayList;

public class Map {

	// width of map
	public static int width = 30;
	// height of map
	public static int height = 30;
	// color constant
	public static Color EMPTY_COLOR = Color.GRAY;
	// pos array
	public Color[][] pos;
	// snakes on map
	public ArrayList<Snake> snakes;

	// constructor
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

	// refresh map
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

	// draw a snake on map
	public void drawSnake(Snake snake){
		for (Coordinate coor : snake.body){
			if (coor.row >= 0 && coor.row <= Map.height - 1 && coor.col >=0 && coor.col <= Map.width - 1){
				this.setPos(coor.row, coor.col, snake.color);
			}
		}
	}

	// setter function
	public void setPos(int row, int col, Color color){
		this.pos[row][col] = color;
	}
}
