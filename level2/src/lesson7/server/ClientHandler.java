package lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    DataInputStream in;
    DataOutputStream out;
    private Server server;
    private Socket socket;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            new Thread(() -> {
                try {
                    // цикл авторизации
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth ")) {
                            String[] token = str.split(" ");

                            String newNick = AuthService.getNickByLoginAndPass(token[1], token[2]);
                            if (newNick != null) {
                                sendMSG("/authok");
                                nick = newNick;
                                server.subscribe(this);
                                System.out.println("Клиент " + nick + " авторизовался");
                                break;
                            } else {
                                sendMSG("Неверный логин / пароль");
                            }
                        }
                    }
                    //цикл работы
                    while (true) {
                        String str = in.readUTF();
                        System.out.println("A message from a client: " + str);

                        if (str.equalsIgnoreCase("/end")) {
                            break;
                        }

                        if (str.startsWith("/w")) {
                            String to = str.split(" ")[1];
                            String msg = str.split(" ")[2];
                            server.wisperMsg(this, to, msg);

                        } else {
                            server.broadcastMsg("[" + this.nick + "] " + str);
                        }
                        out.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                    System.out.println("Клиент " + nick + " отключился");
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    public void sendMSG(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
