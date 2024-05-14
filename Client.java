package chat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;
        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Received: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
