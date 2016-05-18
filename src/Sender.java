import java.io.IOException;
import java.util.ArrayList;

public class Sender {

    // socket list
    public ArrayList<MySocket> sockets;

    public Sender(ArrayList<MySocket> sockets){
        this.sockets = sockets;
    }

    // send message to all players
    public void send(String[] msg) throws IOException{
        for (MySocket s:this.sockets){
            s.sendMsg(msg);
        }
    }

    // close every socket
    public void end() throws IOException {
        for (MySocket s:this.sockets){
        	if(!s.socket.isClosed()) {
        		 s.end();
        	}  
        }
    }
}
