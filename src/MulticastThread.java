import java.lang.System;

public class MulticastThread extends Thread {

	public Client client;
	public Sender sender;
	public Game game;
	public String id;
	
	public MulticastThread(Client client) {
		this.client = client;
		this.sender = client.sender;
		this.game = client.game;
		this.id = client.id;
	}

	// multicast messages to other players
	public void run() {
		try {
			while(true){
				if (this.client.stage.equals(Client.GAME_OVER_STAGE)){
					break;
				}
				// send id, direction, turn
				String[] message = Message.nextDirection(id, this.game.turn, this.game.mySnake.direction);
				sender.send(message);
				Thread.sleep(Client.GAME_INTERVAL);
			}
		} catch(Exception e) {
			System.out.println("Multicast Thread Error!");
			System.out.println(e);
		}
	}
}
