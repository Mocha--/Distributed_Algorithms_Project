/**
 * Created by mocha on 16/05/16.
 */
public class DrawThread extends Thread {

    public Window window;

    public DrawThread(Window window){
        this.window = window;
    }

    public void run(){
        try {
            while (true) {
//                this.window.updateWindow();
                sleep(Client.GAME_INTERVAL);
                this.window.game.mySnake.move();
                this.window.game.map.refresh();
                System.out.println("Current Head");
                System.out.println(this.window.game.map.pos[this.window.game.mySnake.head.row][this.window.game.mySnake.head.col]);
                System.out.println(this.window.game.mySnake.head.row);
                System.out.println(this.window.game.mySnake.head.col);
                System.out.println(this.window.game.map.pos[0][4]);
                this.window.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
