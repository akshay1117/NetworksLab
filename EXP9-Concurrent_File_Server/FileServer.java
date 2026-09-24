import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("File Server running on port 4444. Server PID: " + ProcessHandle.current().pid());

            while (true) {
                Socket socket = serverSocket.accept();
                new FileHandler(socket).start();
            }
        }
    }
}

class FileHandler extends Thread {
    private Socket socket;
    public FileHandler(Socket socket) { this.socket = socket; }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
             
            String fileName = in.readLine();
            System.out.println("Client requested file: " + fileName);
            
            long pid = ProcessHandle.current().pid();
            File file = new File(fileName);
            
            out.println("Server PID: " + pid);
            
            if (file.exists() && !file.isDirectory()) {
                out.println("SUCCESS");
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
            } else {
                out.println("ERROR");
                out.println("File not found or is a directory: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
