import java.net.InetAddress;
import java.net.MulticastSocket;


class Client {

    public static int port = 8080;
    public static int address = '224.0.0.1';

    public int id;
    public int totalPlayerNum;
    public Map map;
    public Snake snake;
    public MulticastThread multicastThread;
    public RecvThread recvThread;

    public Client(){
        this.multicastThread = new MulticastThread(mss, group);
        this.recvThread = new RecvThread();
    }

    public void setId(id){
        this.id = id;
    }

    public void startPlay(){
        this.multicastThread.start();
        this.recvThread.start();
    }

    public void joinGame(){

    }

}
