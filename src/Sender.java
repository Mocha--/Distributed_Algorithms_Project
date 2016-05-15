import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {
    public InetAddress group;
    public MulticastSocket mss;

    public Sender(MulticastSocket mss,InetAddress group){
    	this.mss = mss;
        this.group = group;
    }

    public void send(String msg) throws IOException{
        byte[] buffer = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, this.group, Client.port);
        this.mss.send(dp);
    }
}
