import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client {
	
	public static int TOTAL_PLAYER_NUM = 4;
    public static int port = 8080;
    public static String address = "224.0.0.1";
    public static String JOIN_STAGE = "join";
    public static String PLAY_STATE = "play";
    public static int GAME_INTERVAL = 1000;

    public MulticastSocket mss;
    public InetAddress group;

    public String id;
    public String stage;
    public Game game;
    public Window window;

    public Sender sender;
    public Receiver receiver;
    public MulticastThread multicastThread;
    public RecvThread recvThread;
    public DrawThread drawThread;

    public Client(String id) throws IOException{
    	try {
    		this.id = id;
            this.game = new Game(this.id);
            this.window = new Window(this.game);
    		this.stage = Client.PLAY_STATE;
        	this.mss = new MulticastSocket(Client.port);
        	this.group = InetAddress.getByName(Client.address);
        	this.sender = new Sender(this.mss, this.group);
        	this.receiver = new Receiver(this.mss);

            this.multicastThread = new MulticastThread(this.sender, this.game, this.id);
            this.recvThread = new RecvThread(this.receiver, this.sender, this.game, this.id);
            this.drawThread = new DrawThread(this.window);
    	} catch (Exception e) {
    		System.out.println("Client Init Error!");
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
            this.drawThread.start();
        }
    }

    public void joinGame() throws Exception {
    	this.sender.send(Message.joinGame(this.id));
    	while(true){
    		String[] msg = this.receiver.receive();
    		if(msg[0].equals(Integer.toString(Client.TOTAL_PLAYER_NUM))) {
                this.sender.send(Message.startGame(this.id));
            } else if(msg[0].equals(Integer.toString(Client.TOTAL_PLAYER_NUM)) && msg[1].equals(Message.START_GAME)){
                this.stage = Client.PLAY_STATE;
                break;
            }
    	}
    }

}
