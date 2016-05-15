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
				String[] message = new String [] {id, "direction", String.valueOf(game.turn)};
				sender.send(message);
				System.out.print("Multicast Thread Sent Message!");
				Thread.sleep(1000);
			}
		} catch(Exception e) {
			System.out.println("Multicast Thread Error!");
			System.out.println(e);
		}
	}
}
