import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class UDPServer {
    private static final Map<String, String> dictionary = new HashMap<>();

    // Populate the dictionary with the requested abbreviations
    static {
        dictionary.put("tbh", "to be honest");
        dictionary.put("ig", "I guess");
        dictionary.put("tbf", "to be fair");
        dictionary.put("atm", "at the moment");
        dictionary.put("irl", "in real life");
        dictionary.put("lol", "laughing out loud");
        dictionary.put("asap", "as soon as possible");
        dictionary.put("omg", "oh my god");
        dictionary.put("ttyl", "talk to you later");
        dictionary.put("idk", "I don't know");
        dictionary.put("idc", "I don't care");
        dictionary.put("nvm", "never mind");
    }

    public static void main(String[] args) {
        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Server is listening on port " + port);
            byte[] receiveData = new byte[1024];

            while (true) {
                // Receive packet from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + sentence);

                // Translate sentence
                String translatedSentence = translate(sentence);

                // Send translated packet back to client
                byte[] sendData = translatedSentence.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String translate(String sentence) {
        String result = sentence;
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            // Use regex \b to match exact word boundaries, (?i) for case-insensitivity
            String regex = "(?i)\\b" + entry.getKey() + "\\b";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }
}
