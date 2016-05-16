import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.element.VariableElement;

public class RecvThread extends Thread{
	
	public Receiver receiver;
	public Sender sender;
	public Game game;
	public int nodeId;
	public ArrayList<String> receivedNodes;
	public int lastTurn;
	public int[] nodes;
	
	public RecvThread(Receiver receiver, Sender sender, Game game, String nodeId) {
		this.receivedNodes = new ArrayList<String>();
		this.receiver = receiver;
		this.sender = sender;
		this.game = game;
		// current player
		this.nodeId = Integer.parseInt(nodeId);
		this.lastTurn = 0;
		// nodes[1] = 0
		this.nodes = new int[5];
		for(int i = 0; i < 5; i++) {
			this.nodes[i] = 0;
		}
	}
	
	public void resetNodes() {
		for(int i = 0; i < 5; i++) {
			this.nodes[i] = 0;
		}
	}
	
	public void run() {
		try {
			while (true) {
				if(this.game.turn > this.lastTurn) {
					this.lastTurn = game.turn;
					this.receivedNodes = new ArrayList<String>();
				}
				String[] message = this.receiver.receive();
				String id = message[0];
				String turn = message[2];
				
				// make sure in the same turn
				if(Integer.parseInt(turn) == this.game.turn) {
					int nodeId = Integer.parseInt(id);
					// first time receive message from this node
					if(this.nodes[nodeId] == 0 && nodeId != this.nodeId) {
						this.sender.send(message);
						this.nodes[nodeId] = 1;
						System.out.println("first time receive message from node " + nodeId);
					} else {
						this.nodes[nodeId]++ ;
					}
					
					int sum = 0;
					// check
					for(int i = 0; i < 5; i++) {
						sum += this.nodes[i];
					}
					// (n - 1) * n messages
					if(sum == 12) {
						resetNodes();
						this.game.mySnake.move();
						this.game.map.refresh();
					}
				}
			}
		} catch(Exception e) {
			System.out.println("Receive Thread Error!");
			System.out.println(e);
		}
	}
	
}
 