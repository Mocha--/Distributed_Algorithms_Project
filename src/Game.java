import java.util.ArrayList;

public class Game {

	public Snake mySnake;
	public int turn;
	public Map map;
	public ArrayList<Snake> otherSnakes;
	
	public Game(String id) {
		this.turn = 0;
	}

	public boolean isGameOver(){
		if (this.mySnake.isCrashingAnyone(this.otherSnakes)){
			return true;
		} else {
			return false;
		}
	}
}
