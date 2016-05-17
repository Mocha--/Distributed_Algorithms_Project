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
    					if(this.client.nodes[nodeId] == 0 && !this.client.id.equals(id)) {
    						// send this message to other nodes
    						this.client.sender.send(message);
    					}
    					this.client.nodes[nodeId]++ ;
    					// check
    					for(int i = 1; i <= Client.TOTAL_PLAYER_NUM ; i++) {
    						if(this.client.nodes[i] == Client.TOTAL_PLAYER_NUM - 1) {
    							// change snake direction
    							this.client.game.getSnakeById(Integer.toString(i)).setDirection(direction);
    							// move the snake
    							this.client.game.getSnakeById(Integer.toString(i)).move();
    							if(this.client.game.mySnake.isCrashingBorder() || this.client.game.mySnake.isCrashingAnyone(this.client.game.allSnakes)) {
    								this.client.sender.send(Message.gameOver(this.client.id));
    								for(SessionThread s: this.client.sessionThreads) {
    									s.interrupt();
    								}
    							}
    							// reset the node record
    							this.client.nodes[i] = 0;
    						}
    					}
    				}
            	} else if(type.equals(Message.GAME_OVER)) {
            		this.interrupt();
            	}
				sleep(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }

}
