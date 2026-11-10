import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        new Thread(() -> {
            try {
                while (true) System.out.println(dis.readUTF());
            } catch (Exception e) {}
        }).start();

        while (true) {
            dos.writeUTF(name + ": " + sc.nextLine());
        }
    }
}
