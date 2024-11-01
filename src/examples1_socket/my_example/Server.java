package examples1_socket.my_example;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(5555);
    }

    private ServerSocket serverSocket;
    ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("listening on port " + port);
        handleClients();
    }

    private void handleClients() {
        while (true) {
            ClientHandler clientHandler = null;
            try {
                clientHandler = new ClientHandler(serverSocket.accept());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            clientHandler.start();
            clientHandlers.add(clientHandler);
        }
    }

    public void close() {
        try {
            for (ClientHandler clientHandler : clientHandlers)
                clientHandler.close();

            serverSocket.close();

            System.out.println(this + " closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}