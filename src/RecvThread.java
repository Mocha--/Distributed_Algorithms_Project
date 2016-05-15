import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Arrays;

public class RecvThread extends Thread{
	
	public Receiver receiver;
	public Sender sender;
	public Game game;
	public ArrayList<String> receivedNodes = new ArrayList<String>();
	public int lastTurn = 0;
	
	public RecvThread(Receiver receiver, Sender sender, Game game) {
		this.receiver = receiver;
		this.sender = sender;
		this.game = game;
	}
	
	public void run() {
		try {
			while (true) {
				if(game.turn > lastTurn) {
					lastTurn = game.turn;
					this.receivedNodes = new ArrayList<String>();
				}
				String[] message = this.receiver.receive();
				String id = message[0];
				String turn = message[2];
				// if in the same turn and never handle this message
				if(Integer.parseInt(turn) == game.turn && !receivedNodes.contains(id)) {
					sender.send(message);
					receivedNodes.add(id);
				} else {
					// ignore the message
				}
			}
		} catch(Exception e) {
			System.out.println("Receive Thread Error!");
			System.out.println(e);
		}
	}
	
}
 