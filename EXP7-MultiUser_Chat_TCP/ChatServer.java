import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started...");

        while (true) {
            Socket s = ss.accept();
            System.out.println("New Client Connected...");
            ClientHandler client = new ClientHandler(s);
            clients.add(client);
            client.start();
        }
    }

    static class ClientHandler extends Thread {
        Socket s;
        DataInputStream dis;
        DataOutputStream dos;

        ClientHandler(Socket s) throws IOException {
            this.s = s;
            this.dis = new DataInputStream(s.getInputStream());
            this.dos = new DataOutputStream(s.getOutputStream());
        }

        public void run() {
            try {
                while (true) {
                    String msg = dis.readUTF();
                    for (ClientHandler c : clients) {
                        if (c != this) {
                            c.dos.writeUTF(msg);
                        }
                    }
                }
            } catch (Exception e) {
                try {
                    clients.remove(this);
                    s.close();
                } catch (Exception ignored) {
                }
            }
        }
    }
}