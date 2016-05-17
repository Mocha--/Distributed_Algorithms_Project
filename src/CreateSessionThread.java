import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mocha on 17/05/16.
 */
public class CreateSessionThread extends Thread {

    public ServerSocket serverSocket;

    public CreateSessionThread(Client client){
        this.serverSocket = client.serverSocket;
    }

    public void run(){
        for (int i = 0; i <= Client.TOTAL_PLAYER_NUM - 2; i++){
            try {
                Socket socket = serverSocket.accept();
                Client.beConnected(new MySocket(socket));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
