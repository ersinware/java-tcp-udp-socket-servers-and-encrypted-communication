package examples1_socket.my_example;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    private ObjectInputStream in;
    private ObjectOutputStream out;

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getMessageFromClient();
        sendMessageToClient();
        close();
    }

    public void getMessageFromClient() {
        try {
            System.out.println(in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToClient() {
        try {
            out.writeObject("message from server");
        } catch (IOException e) {
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