import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            System.out.println("Time Server (UDP) is running on port 9876...");

            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // Handle each request concurrently in a new thread
                new Thread(() -> {
                    try {
                        String time = new Date().toString();
                        byte[] sendData = time.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, packet.getAddress(), packet.getPort());
                        socket.send(sendPacket);
                        System.out.println("Sent time to " + packet.getAddress() + ":" + packet.getPort());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
