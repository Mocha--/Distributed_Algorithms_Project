import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
	
	MulticastSocket mss;
	
	public Receiver(MulticastSocket mss) {
		this.mss = mss;
	}
	
	public String receive() throws Exception{
		String message = null;
		try {
			byte[] buffer = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length); 
			mss.receive(dp);
			message = new String(dp.getData(), 0, dp.getLength());
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;	
	}
}
