/**
 * Created by mocha on 16/05/16.
 */
public class DrawThread extends Thread {

    public Window window;
    public Client client;

    public DrawThread(Window window, Client client){
        this.window = window;
        this.client = client;
    }

    public void run(){
        try {
            while (true) {
                if (this.client.stage.equals(Client.GAME_OVER_STAGE)){
                    break;
                }
                sleep(Client.GAME_INTERVAL);
                this.window.game.map.refresh();
                this.window.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
