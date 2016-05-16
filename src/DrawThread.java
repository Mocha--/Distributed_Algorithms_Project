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
                sleep(Client.GAME_INTERVAL);
                this.window.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
