package main.java.com.myWebServer;

import main.java.com.myWebServer.managers.FileManager;
import main.java.com.myWebServer.server.Server;
import main.java.com.myWebServer.url.UrlRouter;

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
