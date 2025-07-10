package main.java.com.myWebServer;

import main.java.com.myWebServer.server.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server("/home/trayenel/programmingProjects/web-server-x/src/main/java/com/myWebServer/config.yaml");

            server.listen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
