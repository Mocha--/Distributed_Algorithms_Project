import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastThread extends Thread {
	public Sender sender;
	public Game game;
	public String id;
	
	public MulticastThread(Sender sender, Game game, String id) {
		this.sender = sender;
		this.game = game;
		this.id = id;
	}
	
	public void run() {
		try {
			while(true){
				// send id, direction, turn
				String[] message = new String [] {id, this.game.mySnake.direction, String.valueOf(game.turn)};
				sender.send(message);
				Thread.sleep(Client.GAME_INTERVAL);
			}
		} catch(Exception e) {
			System.out.println("Multicast Thread Error!");
			System.out.println(e);
		}
	}
}
