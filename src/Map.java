import java.awt.*;
import java.util.ArrayList;

public class Map {

	public static int width = 10;
	public static int height = 10;
	public static Color EMPTY_COLOR = Color.GRAY;

	public Color[][] pos;
	public ArrayList<Snake> snakes;
	
	public Map(ArrayList<Snake> snakes){
		this.pos = new Color[Map.height][Map.width];
		for (int i = 0 ; i <= Map.width - 1 ; i++){
			for( int j = 0 ; j <= Map.height - 1; j++){
				this.setPos(i, j, Map.EMPTY_COLOR);
			}
		}
		this.snakes = snakes;
		this.refresh();
	}

	public void refresh(){
		for(Snake snake : this.snakes){
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
