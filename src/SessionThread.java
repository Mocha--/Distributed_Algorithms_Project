import java.io.IOException;

public class SessionThread extends Thread {


    public MySocket socket;
    public Client client;

    public SessionThread(MySocket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

	// this thread is the main implementation of R-multicast
    public void run() {
        try{
            while(true) {
				// if game over, quit
				if (this.client.stage.equals(Client.GAME_OVER_STAGE)){
					System.out.println("-----You Lose-----");
					break;
				}

            	// receive message
            	String[] message = this.socket.recvMsg();
				// because the disconnection of client socket
                if (message == null){
                    this.socket.end();
					this.client.sessionThreads.remove(this);
					if (this.client.sessionThreads.size() == 0){
						this.client.sender.end();
						this.client.stage = Client.GAME_WIN_STAGE;
						System.out.println("-----You Win-----");
					}
                    break;
                }

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
						// record how many messages about other players the player has got
    					this.client.nodes[nodeId]++ ;
    					// check
    					for(int i = 1; i <= Client.TOTAL_PLAYER_NUM ; i++) {
    						if(this.client.nodes[i] == this.client.sessionThreads.size()) {
    							// change snake direction
    							this.client.game.getSnakeById(Integer.toString(i)).setDirection(direction);
    							// move the snake
    							this.client.game.getSnakeById(Integer.toString(i)).move();

								// game over
    							if(this.client.game.mySnake.isCrashingBorder() || this.client.game.mySnake.isCrashingAnyone(this.client.game.allSnakes)) {
    								for(SessionThread s: this.client.sessionThreads) {
    									s.socket.end();
    								}
                                    this.client.sender.end();
									this.client.stage = Client.GAME_OVER_STAGE;

    							}
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
