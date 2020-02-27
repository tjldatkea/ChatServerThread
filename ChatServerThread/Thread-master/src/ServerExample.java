import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {

    private ServerSocket serverSocket;

    public ServerExample() throws IOException {
        serverSocket = new ServerSocket(5055);
        System.out.println("Server created");
    }

    public void startListening() throws IOException {
        System.out.println("Server going to start accepting connections");

        Runnable serverTask = new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Server thread started");
                    Socket socket = serverSocket.accept();
                    System.out.println("Client socket connected");

                    BufferedReader bufferedreader =
                            new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));

                    while (true) {
                        String receivedMsg = bufferedreader.readLine();
                        System.out.println("Received from client: " + receivedMsg);
                        newClientMessageReceived(receivedMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread serverThread = new Thread(serverTask);
        System.out.println("Before starting server thread");
        serverThread.start();


    }


    private void newClientMessageReceived(String msg) {

        if (msg.startsWith("JOIN")) {
            receiveJoin(msg);
        }
        if (msg.equals("QUIT")) {
            // Process client quitting: print USERNAME has disconnected
        }
        if (msg.startsWith("DATA")) {
            // Print chat message on server
        }

    }

    private void receiveJoin(String joinMessage) {
        // send J_OK to client
        String ok = "J_OK";
        // Send to back to client
    }

}
