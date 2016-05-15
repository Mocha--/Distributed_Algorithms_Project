import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Node2 {

	private static int port = 8000;
	private static String address = "224.0.0.1";
	
	public static void main(String[] args) throws Exception {
		InetAddress group = InetAddress.getByName(address); 
		MulticastSocket msr = null;
		try {
			msr = new MulticastSocket(port);
			System.out.println(msr.getChannel());
			msr.joinGroup(group);
			byte[] buffer = new byte[1024];
			while (true) {
				System.out.println("before");
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length); 
				msr.receive(dp);
				System.out.println("after");
				String s = new String(dp.getData(), 0, dp.getLength()); 
				System.out.println("receive from node1:"+s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

