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
            if (this.client.stage.equals(Client.PLAY_STAGE) && this.isInPlay == false && this.client.sessionThreads.size() == Client.TOTAL_PLAYER_NUM - 1){
                this.client.drawThread.start();
                this.client.multicastThread.start();
                for (SessionThread sessionThread: this.client.sessionThreads){
                    sessionThread.start();
                }
                this.isInPlay = true;
                System.out.println("-----Game Start-----");
                break;
            }

            try {
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
