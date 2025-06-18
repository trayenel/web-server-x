package main.java.com.myWebServer.managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager implements Manager {
    private String lastContent = "";
    private Path path;

    public void loadFile(Path path) {
        this.path = path;
    }

    @Override
    public String start(String path) {
        Path filePath = Path.of(path);

        try {
            lastContent = Files.readString(filePath);

            return lastContent;
        } catch (IOException e) {
            System.err.println("File not found " + path);

            return null;
        }
    }

    @Override
    public void stop() {

    }

    public void writeString(String string) {
        try {
            Files.writeString(path, string);
        } catch (IOException e) {
            System.err.println("Specified path does not exist");
        }
    }

    @Override
    public String getStatus() {
        return "";
    }
}
