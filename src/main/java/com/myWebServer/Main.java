package main.java.com.myWebServer;

import main.java.com.myWebServer.server.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);

            server.listen();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
