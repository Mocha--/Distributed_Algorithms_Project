
import java.lang.System;
import java.net.NetworkInterface;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Node1 {
	private static int port = 8000;
	private static String address = "224.0.0.1";

	public static void main(String[] args) throws Exception {
		try {
			InetAddress group = InetAddress.getByName(address);
			MulticastSocket mss = null;
			mss = new MulticastSocket(port);
			mss.joinGroup(group);
			while (true) {
				String message = "Hello from node1";
				byte[] buffer = message.getBytes();
				System.out.println("before");
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length,
				group, port);
				mss.send(dp);
				System.out.println("after");
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
