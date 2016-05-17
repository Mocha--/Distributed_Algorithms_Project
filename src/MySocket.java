import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * a class which is sealed in a convenient way
 */
public class MySocket {

    /*
     * socket
     */
    public Socket socket;

    /**
     * print write which can write messages into output stream to the server
     */
    private PrintWriter out;

    /**
     * buffer reader which can read messages from input stream from the server
     */
    private BufferedReader in;

    /**
     * constructor
     * @param  socket
     * @return
     * @throws IOException
     */
    public MySocket(Socket socket) throws IOException{
        this.socket = socket;
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
    }

    /**
     * end this socket
     * @throws IOException
     */
    public void end() throws IOException {
        this.socket.close();
    }

    /**
     * send a message
     * @param msg [a json object]
     */
    public void sendMsg(String[] msg){
        this.out.println(Arrays.toString(msg));
        this.out.flush();
    }

    /**
     * receive message
     * @return [a json object]
     * @throws IOException
     */
    public String[] recvMsg() throws IOException{
        String s = this.in.readLine();
        if(s == null){
            return null;
        }
        return this.parseMessage(s);
    }

    public String[] parseMessage(String message) {
        int length = message.length();
        String substring = message.substring(1, length - 1);
        String[] data = substring.split(", ");
        return data;
    }
}
