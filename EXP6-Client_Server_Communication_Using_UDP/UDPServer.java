import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ss = new DatagramSocket(5000);
        System.out.println("Server Started...");
        DatagramPacket rp = new DatagramPacket(new byte[1024], 1024);
        ss.receive(rp);
        String sentence = new String(rp.getData(), 0, rp.getLength());

        sentence = sentence.replaceAll("\\btbh\\b", "to be honest");
        sentence = sentence.replaceAll("\\big\\b", "i guess");
        sentence = sentence.replaceAll("\\btbf\\b", "to be frank");
        sentence = sentence.replaceAll("\\batm\\b", "at the most");
        sentence = sentence.replaceAll("\\birl\\b", "in real life");
        sentence = sentence.replaceAll("\\blol\\b", "laughing out loud");
        sentence = sentence.replaceAll("\\basap\\b", "as soon as possible");
        sentence = sentence.replaceAll("\\bomg\\b", "oh my god");
        sentence = sentence.replaceAll("\\bidk\\b", "i don't know");
        sentence = sentence.replaceAll("\\bnvm\\b", "never mind");

        ss.send(new DatagramPacket(sentence.getBytes(), sentence.length(), rp.getAddress(), rp.getPort()));
        ss.close();
    }
}
