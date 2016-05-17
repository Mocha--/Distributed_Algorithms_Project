import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SessionThread extends Thread {


    public MySocket socket;
    public Client client;

    public SessionThread(MySocket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        try{
            while(true) {
            	// receive message
            	String[] message = this.socket.recvMsg();
            	String id = message[0];
            	String type = message[1];
            	// game is running
            	if(type.equals(Message.NEXT_DIRECTION)) {
            		String turn = message[2];
    				String direction = message[3];
    				// in the same turn
    				if(this.client.game.turn == Integer.parseInt(turn)) {
    					// convert id from string to integer
    					int nodeId = Integer.parseInt(id);
    					// first time receive message from this node
    					if(this.client.nodes[nodeId] == 0) {
    						// send this message to other nodes
    						this.socket.sendMsg(message);
    						System.out.println("first time receive message from node " + nodeId);
    					}
    					this.client.nodes[nodeId]++ ;
    					// check
    					for(int i = 1; i <= Client.TOTAL_PLAYER_NUM ; i++) {
    						if(this.client.nodes[i] == Client.TOTAL_PLAYER_NUM - 1) {
    							// change snake direction
    							this.client.game.getSnakeById(Integer.toString(i)).setDirection(direction);
    							// move the snake
    							this.client.game.getSnakeById(Integer.toString(i)).move();
    							// reset the node record
    							this.client.nodes[i] = 0;
    						}
    					}
    				}
            	}
				
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}
