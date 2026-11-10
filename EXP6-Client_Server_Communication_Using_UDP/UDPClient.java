import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ss = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        ss.send(new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("localhost"), 5000));
        DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
        ss.receive(p);
        System.out.println(new String(p.getData(), 0, p.getLength()));
        ss.close();
        sc.close();
    }
}
