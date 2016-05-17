import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mocha on 17/05/16.
 */
public class CreateSessionThread extends Thread {

    public Client client;

    public CreateSessionThread(Client client){
        this.client = client;
    }

    public void run(){
        for (int i = 0; i <= Client.TOTAL_PLAYER_NUM - 2; i++){
            try {
                Socket socket = this.client.serverSocket.accept();
                this.client.beConnected(new MySocket(socket));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
