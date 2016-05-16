import java.awt.*;
import java.util.*;

public class Snake {

    public static String LEFT = "left";
    public static String RIGHT = "right";
    public static String UP = "up";
    public static String DOWN = "down";
    public static String DEFAULT_DIRECTION = "up";

    public static Color COLOR_BLUE = Color.BLUE;
    public static Color COLOR_RED = Color.RED;
    public static Color COLOR_BLACK = Color.BLACK;
    public static Color COLOR_GREEN = Color.GREEN;

    public String id;
    public Color color;
    public ArrayList<Coordinate> body;
    public String direction;
    public Coordinate head;
    
    public Snake(String id) {
        this.id = id;
        if (this.id.equals("1")){
            Coordinate coor = new Coordinate(0, 0);
            this.color = Snake.COLOR_BLUE;
            this.body.add(coor);
        } else if (this.id.equals("2")){
            Coordinate coor = new Coordinate(0, Map.width - 1);
            this.color = Snake.COLOR_RED;
            this.body.add(coor);
        } else if(this.id.equals("3")) {
            Coordinate coor = new Coordinate(Map.height - 1, Map.width - 1);
            this.color = Snake.COLOR_BLACK;
            this.body.add(coor);
        } else if(this.id.equals("4")) {
            Coordinate coor = new Coordinate(Map.height - 1, 0);
            this.color = Snake.COLOR_GREEN;
            this.body.add(coor);
        } else {
            System.out.println("Invalid id");
        }
        this.head = this.body.get(this.body.size() - 1);
        this.direction = Snake.DEFAULT_DIRECTION;
    }

    public Coordinate move(String direction){
        if(direction.equals(Snake.LEFT)){
            this.body.add(new Coordinate(this.head.row, this.head.col - 1));
        } else if (direction.equals(Snake.RIGHT)){
            this.body.add(new Coordinate(this.head.row, this.head.col + 1));
        } else if (direction.equals(Snake.UP)){
            this.body.add(new Coordinate(this.head.row - 1, this.head.col));
        } else if( direction.equals(Snake.DOWN)){
            this.body.add(new Coordinate(this.head.row + 1, this.head.col));
        } else {
            System.out.print("wrong direction");
        }
        this.head = this.body.get(this.body.size() - 1);
        return this.head;
    }

    public boolean isCrashingAnyone(ArrayList<Snake> snakes){
        for (Snake snake: snakes){
            if (this.isCrashingSomeone(snake)){
                return true;
            }
        }
        return false;
    }

    public boolean isCrashingSomeone(Snake snake){
        for (Coordinate coor : snake.body){
            if (this.head.isSame(coor)){
                return true;
            }
        }
        return false;
    }


}
