public class Sender {
    public InetAddress group;
    public MulticastSocket mss;

    Sender(mss, group){
        this.group = InetAddress.getByName(Client.address);
        this.mss = new MulticastSocket(Client.port);
        this.mss.joinGroup(this.group);
    }

    public void send(string msg){
        byte[] buffer = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, this.group, Client.port);
        this.mss.send(dp);
    }
}
