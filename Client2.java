package chat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 22345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            out.println(username);
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            readThread.start();

            while (true) {
                String message = scanner.nextLine();
                out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
