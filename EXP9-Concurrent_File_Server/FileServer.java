import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5001);
        long pid = ProcessHandle.current().pid();

        System.out.println("Server started...");
        System.out.println("Server PID: " + pid);

        while (true) {
            try (Socket s = ss.accept();
                 DataInputStream dis = new DataInputStream(s.getInputStream());
                 DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {

                String fileName = dis.readUTF();
                File file = new File(fileName);
                dos.writeUTF("Server PID: " + pid);

                if (file.isFile()) {
                    dos.writeUTF("File found: " + fileName);
                    dos.writeUTF("----- File Contents -----");

                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            dos.writeUTF(line);
                        }
                    }

                    dos.writeUTF("----- End of File -----");
                } else {
                    dos.writeUTF("File not found: " + fileName);
                }

                dos.writeUTF("END");
                dos.flush();
            } catch (Exception e) {
                System.out.println("Client error: " + e);
            }
        }
    }
}
