import sun.reflect.generics.scope.DummyScope;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Client {
	
	public static int TOTAL_PLAYER_NUM = 4;
    public static int port = 12000;
    public static String address = "230.0.0.1";
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
    public ArrayList<MySocket> clientSockets;
    public ServerSocket serverSocket;
    public ArrayList<SessionThread> sessionThreads;
    public CreateSessionThread createSessionThread;
    public UserInputThread userInputThread;

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
            NetworkInterface ni = NetworkInterface.getByName("etho0");
            this.mss.joinGroup(this.group);

            this.multicastThread = new MulticastThread(this.sender, this.game, this.id);
            this.recvThread = new RecvThread(this.receiver, this.sender, this.game, this.id);
            this.serverSocket = new ServerSocket(this.port);
            this.sessionThreads = new ArrayList<SessionThread>();
            this.drawThread = new DrawThread(this.window);
            this.createSessionThread = new CreateSessionThread(this);
            this.userInputThread = new UserInputThread(this);
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
        if (this.stage.equals(Client.JOIN_STAGE)){
            this.createSessionThread.start();
            this.userInputThread.start();
        }
    }

    public MySocket connectOthers(String ip, String port) throws IOException {
        MySocket clientSocket = new MySocket(new Socket(ip, Integer.parseInt(port)));
        this.clientSockets.add(clientSocket);
        return clientSocket;
    }

    public SessionThread beConnected(MySocket mySocket){
        SessionThread sessionThread = new SessionThread(mySocket, this);
        this.sessionThreads.add(sessionThread);
        return sessionThread;
    }

}
