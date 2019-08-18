package lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        AuthService.connect();

        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMSG(msg);
        }
    }

    public void wisperMsg(ClientHandler from, String to, String msg) {

        for (ClientHandler client : clients) {
            if (client.getNick().equals(to)) {
                client.sendMSG("[W from: " + from.getNick() + "] " + msg);
                break;
            }
        }
        from.sendMSG("[W to: " + to + "] " + msg);
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
