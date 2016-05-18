import java.io.IOException;
import java.util.ArrayList;

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

    public void end() throws IOException {
        for (MySocket s:this.sockets){
        	if(!s.socket.isClosed()) {
        		 s.end();
        	}  
        }
    }
}
