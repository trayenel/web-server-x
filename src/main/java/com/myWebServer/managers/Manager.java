package main.java.com.myWebServer.managers;

public interface Manager {
    String start(String path);
    String getStatus();
    void stop();
}
