import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class RecvThread extends Thread{
	
	public Receiver receiver;
	
	public RecvThread(Receiver receiver) {
		this.receiver = receiver;
	}
	
	public void run() {
		try {
			while (true) {
				String direction = this.receiver.receive();
				System.out.println("Received direction " + direction);
				System.out.print("Receive Thread Sent Message!");
			}
		} catch(Exception e) {
			System.out.println("Receive Thread Error!");
			System.out.println(e);
		}
	}
	
}
 