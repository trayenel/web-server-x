package main.java.com.myWebServer.managers;

import java.io.IOException;
import java.nio.file.Path;

public interface Manager {
    String start(String path);
    String getStatus();
    void stop();
}
