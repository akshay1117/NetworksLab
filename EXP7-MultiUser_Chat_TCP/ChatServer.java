import java.io.*;
import java.util.*;
import java.net.*;

public class ChatServer {
    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started...");
        while (true) {
            Socket s = ss.accept();
            System.out.println("New Client Connected...");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            ClientHandler ch = new ClientHandler(s, dis, dos);
            clients.add(ch);
            Thread t = new Thread(ch);
            t.start();
        }
    }

    static class ClientHandler implements Runnable {
        Socket s;
        DataInputStream dis;
        DataOutputStream dos;

        ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }

        public void run() {
            try {
                while (true) {
                    String msg = dis.readUTF();
                    for (ClientHandler client : ChatServer.clients) {
                        if (client != this) {
                            client.dos.writeUTF(msg);
                        }
                    }
                }
            } catch (Exception ex) {
                try {
                    ChatServer.clients.remove(this);
                    s.close();
                } catch (Exception e) {

                }
            }
        }
    }
}
