import java.util.ArrayList;

public class Map {

	public static int width = 10;
	public static int height = 10;
	public int[][] pos;
	public ArrayList<Snake> snakes;
	
	public Map(ArrayList<Snake> snakes){
		this.pos = new int[Map.height][Map.width];
		for (int i = 0 ; i <= Map.width - 1 ; i++){
			for( int j = 0 ; j <= Map.height - 1; j++){
				this.pos[i][j] = 0;
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
			this.setPos(coor.row, coor.col, Integer.parseInt(snake.id));
		}
	}

	public void setPos(int row, int col, int val){
		this.pos[row][col] = val;
	}
}
