import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastThread extends Thread {
	public Sender sender;
	public Game game;
	
	public MulticastThread(Sender sender) {
		this.sender = sender;
	}
	
	public void run() {
		try {
			while(true){
				String message = "test direction";
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
