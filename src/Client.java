import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Client {

    // total player number
	public static int TOTAL_PLAYER_NUM = 4;
    // the port where server is established
    public static int port = 8080;
    // game stage constant
    public static String JOIN_STAGE = "join";
    // game stage constant
    public static String PLAY_STAGE = "play";
    // game stage constant
    public static String GAME_OVER_STAGE = "gameover";
    // game stage constant
    public static String GAME_WIN_STAGE = "gamewin";
    // the interval of every movement
    public static int GAME_INTERVAL = 1000;
    // all nodes info
    public int[] nodes;

    public String id;
    public String stage;
    public Game game;
    public Window window;
    public Sender sender;

    // socket list
    public ArrayList<MySocket> clientSockets;
    // server socket
    public ServerSocket serverSocket;
    // session thread list
    public ArrayList<SessionThread> sessionThreads;
    // userinput thread which is runing before the game starts
    public UserInputThread userInputThread;
    // a thread to start game
    public StartPlayThread startPlayThread;
    // a thread to send messages
    public MulticastThread multicastThread;
    // a thread to draw UI
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

    // join the game
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

    // connect to other player
    public MySocket connectOthers(String ip, String port) throws IOException {
        MySocket clientSocket = new MySocket(new Socket(ip, Integer.parseInt(port)));
        this.clientSockets.add(clientSocket);
        return clientSocket;
    }

    // being connected by other players
    public SessionThread beConnected(MySocket mySocket){
        SessionThread sessionThread = new SessionThread(mySocket, this);
        this.sessionThreads.add(sessionThread);
        return sessionThread;
    }

}
