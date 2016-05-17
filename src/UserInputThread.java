import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * thread responsible for user's input
 */
public class UserInputThread extends Thread {

    /**
     * client
     */
    public Client client;

    /**
     * user's input
     */
    public BufferedReader userIn;

    /**
     * constructor
     * @param  client
     * @return
     */
    public UserInputThread(Client client){
        this.client = client;
//        this.start();
    }

    public void run(){
        System.out.println("Please Enter Others' Ip Address: ");
        for (int i = 0; i <= Client.TOTAL_PLAYER_NUM - 2; i++){
            this.userIn = new BufferedReader(new InputStreamReader(System.in));
            String input = null;

            try {
                input = userIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String ip = input.split(",")[0];
            String port = input.split(",")[1];

            try {
                client.connectOthers(ip, port);
                System.out.println("Connect Successfully, Waiting for Others: ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}