package main.java.com.myWebServer;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(2222);

            server.listen();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
