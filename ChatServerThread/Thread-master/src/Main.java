import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        // Move below to server project
        ServerExample server = new ServerExample();
        server.startListening();

        // Keep in client project
        System.out.println("Before creating new client");
        Client client = new Client("Kenneth");
        client.join();
        client.startHeartbeating();


    }

    private void threadExample() {
        NumberExample number = new NumberExample();
        number.setNumber(1);

        Thread thread1 = new Thread(() -> {
            System.out.println("Inside first thread");
            while (true) {
                int i = number.getNumber();
                number.addOne(i);
                number.printNumber();
            }

        });

        ExampleTask exampleTask = new ExampleTask(number);
        Thread thread2 = new Thread(exampleTask);
        thread2.start();
        thread1.start();
        System.out.println("Inside the main thread");
        while(true) {

        }
    }

}
