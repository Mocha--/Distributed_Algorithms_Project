import java.awt.*;
import java.util.*;

public class Snake {
    /*  some direction constants */
    public static String LEFT = "left";
    public static String RIGHT = "right";
    public static String UP = "up";
    public static String DOWN = "down";
    public static String DEFAULT_DIRECTION = "down";

    /* some color constants*/
    public static Color COLOR_BLUE = Color.BLUE;
    public static Color COLOR_RED = Color.RED;
    public static Color COLOR_BLACK = Color.BLACK;
    public static Color COLOR_GREEN = Color.GREEN;

    public String id;
    public Color color;
    public ArrayList<Coordinate> body;
    public String direction;
    public Coordinate head;

    // constructor
    public Snake(String id) {
        this.id = id;
        this.body = new ArrayList<Coordinate>();
        if (this.id.equals("1")){
            Coordinate coor = new Coordinate(5, 5);
            this.color = Snake.COLOR_BLUE;
            this.body.add(coor);
        } else if (this.id.equals("2")){
            Coordinate coor = new Coordinate(5, Map.width - 6);
            this.color = Snake.COLOR_RED;
            this.body.add(coor);
        } else if(this.id.equals("3")) {
            Coordinate coor = new Coordinate(Map.height - 6, 5);
            this.color = Snake.COLOR_BLACK;
            this.body.add(coor);
        } else if(this.id.equals("4")) {
            Coordinate coor = new Coordinate(Map.height - 6, Map.width - 6);
            this.color = Snake.COLOR_GREEN;
            this.body.add(coor);
        } else {
            System.out.println("Invalid id");
        }
        this.head = this.body.get(this.body.size() - 1);
        this.direction = Snake.DEFAULT_DIRECTION;
    }

    // move one step
    public Coordinate move(){
        if(this.direction.equals(Snake.LEFT)){
            this.body.add(new Coordinate(this.head.row, this.head.col - 1));
        } else if (this.direction.equals(Snake.RIGHT)){
            this.body.add(new Coordinate(this.head.row, this.head.col + 1));
        } else if (this.direction.equals(Snake.UP)){
            this.body.add(new Coordinate(this.head.row - 1, this.head.col));
        } else if( this.direction.equals(Snake.DOWN)){
            this.body.add(new Coordinate(this.head.row + 1, this.head.col));
        } else {
            System.out.print("wrong direction");
        }
        this.head = this.body.get(this.body.size() - 1);
        return this.head;
    }

    // is the snake crashing anybody
    public boolean isCrashingAnyone(ArrayList<Snake> snakes){
        for (Snake snake: snakes){
            if (this.isCrashingSomeone(snake)){
                return true;
            }
        }
        return false;
    }

    // is the snake crashing the border
    public boolean isCrashingBorder(){
        if (this.head.row < 0 || this.head.row >= Map.height || this.head.col < 0 || this.head.col >= Map.width){
            return true;
        } else {
            return false;
        }
    }

    // is the snake crashing someone
    public boolean isCrashingSomeone(Snake snake){
    	int count = 0;
        for (Coordinate coor : snake.body){
            if (this.head.isSame(coor)) {
                count ++;
            }
        }
        if(count == 2 && this.id.equals(snake.id)) {
        	return true;
        } else if (count == 1 && !this.id.equals(snake.id)) {
        	return true;
        }
        return false;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }


}
