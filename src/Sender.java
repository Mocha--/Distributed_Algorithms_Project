import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Sender {

    public ArrayList<MySocket> sockets;

    public Sender(ArrayList<MySocket> sockets){
        this.sockets = sockets;
    }

    public void send(String[] msg) throws IOException{
        for (MySocket s:this.sockets){
            s.sendMsg(msg);
        }
    }
}
