package main.java.com.myWebServer.managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    private Path path;

    public FileManager() {
    }

    public FileManager(Path path) {
        this.path = path;
    }

    public void loadFile(Path path) {
        this.path = path;
    }

    public String start() {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("File not found " + path);

            return null;
        }
    }

    public void writeString(String route) {
        try {
            Files.writeString(path, route, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Specified path does not exist");
        }
    }

    public String getStatus() {
        return "Loaded file: " + this.path;
    }
}
