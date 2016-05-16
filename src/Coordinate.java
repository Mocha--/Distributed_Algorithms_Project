/**
 * Created by mocha on 16/05/16.
 */
public class Coordinate {
    public int row;
    public int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    public boolean isSame(Coordinate coor){
        if(this.row == coor.row && this.col == coor.col){
            return true;
        } else {
            return false;
        }
    }
}
