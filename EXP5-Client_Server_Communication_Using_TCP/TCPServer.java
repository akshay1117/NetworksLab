import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 6789;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            int n = in.readInt();
            int[][] matrix = (int[][]) in.readObject();

            String type = identifyMatrixType(matrix, n);
            out.writeObject(type);
            out.flush();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Server thread exception: " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }

    private String identifyMatrixType(int[][] matrix, int n) {
        boolean isUpper = true;
        boolean isLower = true;
        boolean isDiagonal = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && matrix[i][j] != 0) {
                    isDiagonal = false;
                }
                if (i > j && matrix[i][j] != 0) {
                    isUpper = false;
                }
                if (i < j && matrix[i][j] != 0) {
                    isLower = false;
                }
            }
        }

        if (isDiagonal) {
            return "Diagonal Matrix";
        } else if (isUpper) {
            return "Upper Triangular Matrix";
        } else if (isLower) {
            return "Lower Triangular Matrix";
        } else {
            return "Not a special matrix";
        }
    }
}
