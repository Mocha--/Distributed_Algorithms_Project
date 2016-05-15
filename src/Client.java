import java.net.InetAddress;
import java.net.MulticastSocket;


class Client {

    public static int port = 8080;
    public static String address = "224.0.0.1";
    public MulticastSocket mss;
    public InetAddress group;

    public int id;
    public int totalPlayerNum;
    public Map map;
    public Snake snake;
    public MulticastThread multicastThread;
    public RecvThread recvThread;

    public Client(){
    	this.mss = new MulticastSocket(Client.port);
    	this.group = InetAddress.getByName(Client.address);
        this.multicastThread = new MulticastThread(this.mss, this.group);
        this.recvThread = new RecvThread();
    }

    public void setId(int id){
        this.id = id;
    }

    public void startPlay(){
        this.multicastThread.start();
        this.recvThread.start();
    }

    public void joinGame(){

    }

}
