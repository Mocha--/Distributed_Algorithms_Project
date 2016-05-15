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

    public String id;
    public String stage;
    public Game game;

    public Sender sender;
    public Receiver receiver;
    public MulticastThread multicastThread;
    public RecvThread recvThread;

    public Client(String id) throws IOException{
    	try {
    		this.stage = Client.PLAY_STATE;
        	this.mss = new MulticastSocket(Client.port);
        	this.group = InetAddress.getByName(Client.address);
        	this.sender = new Sender(this.mss, this.group);
        	this.receiver = new Receiver(this.mss);
            this.multicastThread = new MulticastThread(this.sender, this.game, this.id);
            this.recvThread = new RecvThread(this.receiver, this.sender, this.game);
            this.id = id;
    	} catch (Exception e) {
    		System.out.println(e);
    	} 
    }

    public void setId(String id) {
        this.id = id;
    }

    public void startPlay() {
        if(this.stage.equals(Client.PLAY_STATE)){
            this.multicastThread.start();
            this.recvThread.start();
        }
    }

    public void joinGame() throws Exception {
    	this.sender.send(new String[]{"1", "hello"});
    	while(true){
    		String[] msg = this.receiver.receive();
    		if(msg[0].equals("4")) {
                this.sender.send(new String[]{"4", "letsstart"});
            } else if(msg[1].equals("letsstart")){
                this.stage = Client.PLAY_STATE;
                break;
            }
    	}
    }

}
