import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 6789;

        try (Socket socket = new Socket(hostname, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the size of the square matrix (N): ");
            int n = scanner.nextInt();

            int[][] matrix = new int[n][n];
            Random random = new Random();

            System.out.println("Generated Matrix:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = random.nextInt(50) + 1; // [1, 50]
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }

            // Send matrix size and matrix to the server
            out.writeInt(n);
            out.writeObject(matrix);
            out.flush();

            // Receive the result
            String type = (String) in.readObject();
            System.out.println("Server says the matrix is: " + type);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
