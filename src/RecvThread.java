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
	public String nodeId;
	public ArrayList<String> receivedNodes;
	public int lastTurn;
	
	public RecvThread(Receiver receiver, Sender sender, Game game, String nodeId) {
		this.receivedNodes = new ArrayList<String>();
		this.receiver = receiver;
		this.sender = sender;
		this.game = game;
		this.nodeId = nodeId;
		this.lastTurn = 0;
	}
	
	public void run() {
		try {
			while (true) {
				if(game.turn > this.lastTurn) {
					this.lastTurn = game.turn;
					this.receivedNodes = new ArrayList<String>();
				}
				String[] message = this.receiver.receive();
				String id = message[0];
				String turn = message[2];
				// if in the same turn and never handle this message
				if(Integer.parseInt(turn) == this.game.turn && !this.receivedNodes.contains(id) && !this.nodeId.equals(id)) {
					this.sender.send(message);
					this.receivedNodes.add(id);
					System.out.println("Turn : " + turn + "Handle node " + id);
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
 