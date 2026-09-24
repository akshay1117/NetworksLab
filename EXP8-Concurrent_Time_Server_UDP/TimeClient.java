import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        
        byte[] buf = "Time Request".getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 9876);
        
        System.out.println("Sending time request to server...");
        socket.send(packet);
        
        byte[] receiveBuf = new byte[256];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
        socket.receive(receivePacket);
        
        String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received Server Time: " + received);
        
        socket.close();
    }
}
