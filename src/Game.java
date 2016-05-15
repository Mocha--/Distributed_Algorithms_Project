public class Game {
	
	public Snake snake;
	public Integer turn;
	public Map map;
	
	public Game(Snake snake, Map map) {
		this.turn = 0;
		this.map = map;
		this.snake = snake;
	}
}
