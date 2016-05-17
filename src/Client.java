import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Client {
	
	public static int TOTAL_PLAYER_NUM = 4;
    public static int port = 8080;
    public static String JOIN_STAGE = "join";
    public static String PLAY_STAGE = "play";
    public static String GAME_OVER_STAGE = "gameover";
    public static String GAME_WIN_STAGE = "gamewin";
    public static int GAME_INTERVAL = 2000;
    // all nodes info
    public int[] nodes;

    public String id;
    public String stage;
    public Game game;
    public Window window;
    public Sender sender;

    public ArrayList<MySocket> clientSockets;
    public ServerSocket serverSocket;
    public ArrayList<SessionThread> sessionThreads;
    public UserInputThread userInputThread;
    public StartPlayThread startPlayThread;
    public MulticastThread multicastThread;
    public DrawThread drawThread;

    public Client(String id) throws IOException{
    	try {
    		this.id = id;
            this.game = new Game(this.id);
            this.window = new Window(this.game);
    		this.stage = Client.JOIN_STAGE;
            this.nodes = new int[Client.TOTAL_PLAYER_NUM + 1];
    		for(int i = 0; i < Client.TOTAL_PLAYER_NUM + 1; i++) {
    			this.nodes[i] = 0;
    		}

            this.serverSocket = new ServerSocket(Client.port);
            this.sessionThreads = new ArrayList<SessionThread>();
            this.clientSockets = new ArrayList<MySocket>();
            this.sender = new Sender(this.clientSockets);
            this.drawThread = new DrawThread(this.window, this);

            // join stage
            this.userInputThread = new UserInputThread(this);
            this.startPlayThread = new StartPlayThread(this);

            // play stage
            this.multicastThread = new MulticastThread(this);


    	} catch (Exception e) {
    		System.out.println("Client Init Error!");
    		System.out.println(e);
    	} 
    }

    public void joinGame() throws Exception {
        if (this.stage.equals(Client.JOIN_STAGE)){
            this.userInputThread.start();
            this.startPlayThread.start();

            for (int i = 0; i <= Client.TOTAL_PLAYER_NUM - 2; i++) {
                Socket socket = this.serverSocket.accept();
                this.beConnected(new MySocket(socket));
                System.out.println("-----One Player Connected-----");
            }
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
