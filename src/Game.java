import java.util.ArrayList;

public class Game {

	// snake instatnce
	public Snake mySnake;
	// which turn current is
	public int turn;
	// map instance
	public Map map;
	// other snakes
	public ArrayList<Snake> otherSnakes;
	// all snakes
	public ArrayList<Snake> allSnakes;

	// constructor
	public Game(String id) {
		this.turn = 0;
		this.mySnake = new Snake(id);
		this.otherSnakes = new ArrayList<Snake>();
		for(int i = 1 ; i <= Client.TOTAL_PLAYER_NUM; i++){
			if (Integer.parseInt(id) != i){
				Snake snake = new Snake(Integer.toString(i));
				this.otherSnakes.add(snake);
			}
		}
		this.allSnakes = this.otherSnakes;
		this.allSnakes.add(this.mySnake);
		this.map = new Map(this.allSnakes);
	}

	// is game over
	public boolean isGameOver(){
		if (this.mySnake.isCrashingAnyone(this.otherSnakes)){
			return true;
		} else {
			return false;
		}
	}

	// get snake by snake's id
	public Snake getSnakeById(String id){
		for (Snake snake : this.allSnakes){
			if (snake.id.equals(id)){
				return snake;
			}
		}
		return null;
	}

	// remove snake by id
	public boolean removeSnakeById(String id){
		Snake snake = this.getSnakeById(id);
		if (snake == null) {
			return false;
		} else {
			this.allSnakes.remove(snake);
			return true;
		}

	}
}
