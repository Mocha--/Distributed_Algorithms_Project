/**
 * Created by mocha on 17/05/16.
 */
public class StartPlayThread extends Thread {

    public Client client;
    public boolean isInPlay;

    public StartPlayThread(Client client){
        this.client = client;
        this.isInPlay = false;
    }

    public void run(){
        while(true){
            if (this.client.stage.equals(Client.PLAY_STATE) && this.isInPlay == false){
                this.client.drawThread.start();
                this.client.multicastThread.start();
                for (SessionThread sessionThread: this.client.sessionThreads){
                    sessionThread.start();
                }
                this.isInPlay = true;
                System.out.println("game start");
            }

            try {
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
