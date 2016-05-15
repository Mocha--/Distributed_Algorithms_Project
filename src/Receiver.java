import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
	
	MulticastSocket mss;
	
	public Receiver(MulticastSocket mss) {
		this.mss = mss;
	}
	
	// convert string to array
	public String[] parseMessage(String message) {
		int length = message.length();
		String substring = message.substring(1, length - 1);
		String[] data = substring.split(", ");
		return data;
	}
		
	public String[] receive() throws Exception {
		String[] message = {};
		try {
			byte[] buffer = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length); 
			mss.receive(dp);
			String messageString = new String(dp.getData(), 0, dp.getLength());
			message = parseMessage(messageString);
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;	
	}
}
