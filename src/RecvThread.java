import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class RecvThread extends Thread{
	
	public Receiver receiver;
	public Sender sender;
	public String[] receivedNodes = {};
	
	public RecvThread(Receiver receiver, Sender sender) {
		this.receiver = receiver;
		this.sender = sender;
	}
	
	public void run() {
		try {
			while (true) {
				String[] message = this.receiver.receive();
				System.out.println("Received message " + message);
				/*
				 *  1. parse message, get id, direction, turn 
				    2. get lastTurn from node
				    3. if lastTurn == turn, send message, else, do not send
				 * 
				 */
				//sender.send(message);
				System.out.print("Receive Thread Sent Message!");
			}
		} catch(Exception e) {
			System.out.println("Receive Thread Error!");
			System.out.println(e);
		}
	}
	
}
 