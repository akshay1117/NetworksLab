import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 4444);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
             
            System.out.print("Enter file name/path to request from server: ");
            String fileName = scanner.nextLine();
            out.println(fileName);
            
            String pidInfo = in.readLine();
            System.out.println(pidInfo); // Display server PID
            
            String status = in.readLine();
            if ("SUCCESS".equals(status)) {
                System.out.println("\n--- File Contents ---");
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("---------------------");
            } else {
                System.out.println("\nServer replied with error:");
                System.out.println(in.readLine()); // Print error msg
            }
        }
    }
}
