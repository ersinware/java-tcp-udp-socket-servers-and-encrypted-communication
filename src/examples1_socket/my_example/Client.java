package examples1_socket.my_example;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        sendMessageToServer();
        getMessageFromServer();
        close();
    }

    public void sendMessageToServer() {
        try {
            out.writeObject("message from " + this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMessageFromServer() {
        try {
            System.out.println(this + ": " + in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();

            System.out.println(this + " closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}