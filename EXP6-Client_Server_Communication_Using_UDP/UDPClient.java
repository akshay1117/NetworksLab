import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress address = InetAddress.getByName(hostname);

            System.out.println("Enter a sentence (using abbreviations): ");
            String sentence = scanner.nextLine();

            // Send the sentence to the server
            byte[] sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);

            // Receive the translated sentence from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String translatedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("\nTranslated string sent back to the client:");
            System.out.println(translatedSentence);

        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
