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
    public Sender sender;
    public Receiver receiver;
    public MulticastThread multicastThread;
    public RecvThread recvThread;

    public Client(){
    	this.mss = new MulticastSocket(Client.port);
    	this.group = InetAddress.getByName(Client.address);
    	this.sender = new Sender(this.mss, this.group);
        this.multicastThread = new MulticastThread(this.sender);
        this.recvThread = new RecvThread(this.receiver);
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
