import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Client {

    private Socket clientSocket;
    private PrintWriter outWriter;
    private String clientName;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public void join() throws UnknownHostException, IOException {
        String joinText = "JOIN kenneth localhost:5055";
        // connect socket + send joinText when connected
        clientSocket = new Socket("localhost", 5055);
        outWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Client sending: " + joinText + "\r\n");
        outWriter.println(joinText);

    }

    public void startHeartbeating() {

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                String alive = "IMAV";
                // Send to socket
                while (true) {

                    outWriter.println(alive);
                    // Sleep 1 minute after writing
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        };

        Thread timerThread = new Thread(timerTask);
        timerThread.start();

    }

    public void sendChatMessage(String chatText) {
        String msg = "DATA " + clientName + ":" + chatText;
        // Send msg to socket
        outWriter.println(msg);
    }

    public void disconnect() throws IOException {
        String quit = "QUIT";
        // Send quit to socket
        outWriter.println(quit);
        // close socket
        clientSocket.close();
    }

}
