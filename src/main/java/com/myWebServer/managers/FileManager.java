package main.java.com.myWebServer.managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager implements Manager {
    private final Path path;


    public FileManager(Path path) {
        super();
        this.path = path;
    }


    @Override
    public String start() {

        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("File not found " + path);

            return null;
        }
    }

    @Override
    public void stop() {

    }

    public void writeString(String route) {
        try {
            Files.writeString(path, route, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Specified path does not exist");
        }
    }

    @Override
    public String getStatus() {
        return "Loaded file: " + this.path;
    }
}
