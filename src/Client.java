import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Client {
	
	public static int totalPlayerNum;
    public static int port = 8080;
    public static String address = "224.0.0.1";
    public static String JOIN_STAGE = "join";
    public static String PLAY_STATE = "play";

    public MulticastSocket mss;
    public InetAddress group;

    public int id;
    public String stage;
    
    public Map map;
    public Snake snake;
    public Sender sender;
    public Receiver receiver;
    public MulticastThread multicastThread;
    public RecvThread recvThread;

    public Client() throws IOException{
        this.stage = Client.JOIN_STAGE;
    	this.mss = new MulticastSocket(Client.port);
    	this.group = InetAddress.getByName(Client.address);
    	this.sender = new Sender(this.mss, this.group);
    	this.receiver = new Receiver(this.mss);
        this.multicastThread = new MulticastThread(this.sender);
        this.recvThread = new RecvThread(this.receiver);
    }

    public void setId(int id){
        this.id = id;
    }

    public void startPlay(){
        if(this.stage.equals(Client.PLAY_STATE)){
            this.multicastThread.start();
            this.recvThread.start();
        }
    }

    public void joinGame(){
    	this.sender.send([1,"hello"]);
    	while(true){
    		String[] msg = this.receiver.receive();
    		if(msg[0].equals("4")) {
                this.sender.send([4, 'letsstart']);
            } else if(msg[1].equals('letsstart')){
                this.stage = Client.PLAY_STATE;
                break;
            }
    	}
    }

}
