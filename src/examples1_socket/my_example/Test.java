package examples1_socket.my_example;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
    private static final ArrayList<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++)
            connectClient();

        for (Thread thread : threads)
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }

    private static void connectClient() {
        Thread thread = new Thread(() -> {
            Client client = new Client();
            try {
                client.startConnection("127.0.0.1", 5555);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        threads.add(thread);
    }
}