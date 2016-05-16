import java.util.*;

public class Snake {

    public static String LEFT = "left";
    public static String RIGHT = "right";
    public static String UP = "up";
    public static String DOWN = "down";
    public static String DEFAULT_DIRECTION = "up";

    public String id;
    public ArrayList<Coordinate> body;
    public String direction;
    public Coordinate head;
    
    public Snake(String id, ArrayList<Coordinate> body, String direction) {
        this.id = id;
        this.body = body;
        this.head = this.body.get(this.body.size() - 1);
        this.direction = direction;
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
