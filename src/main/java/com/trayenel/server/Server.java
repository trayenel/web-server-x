package com.trayenel.server;

import com.trayenel.base.Configurable;
import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.managers.FileManager;
import com.trayenel.url.UrlRouter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server implements Configurable {
    private final HashMap<String, HttpHandler> handlers = new HashMap<>();
    private final UrlRouter urlRouter = new UrlRouter();
    private final FileManager fileManager = new FileManager();
    private int port;
    private String htmlFilesPath;

    public Server(String configFilePath) throws IOException {
        this.fileManager.loadFile(Path.of(configFilePath));
        this.loadConfig(fileManager.start());
    }

    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);

        System.out.println("Listening on port " + this.port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                System.out.println("New connection established from " + clientSocket.getInetAddress());

                HttpRequest request = new HttpRequest(inputStream);

                if (!handlers.containsKey(request.getPath())) {
                    HttpHandler handler = HttpHandler.createHandler(request, this.fileManager, this.urlRouter, this.htmlFilesPath);
                    handlers.put(request.getPath(), handler);
            }

                HttpHandler handler = handlers.get(request.getPath());
                HttpResponse response = handler.handleRequest();

                byte[] bytes = HttpResponse.stringify(response).getBytes(StandardCharsets.UTF_8);

                outputStream.write(bytes);
                outputStream.flush();
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                clientSocket.close();
            }
        }
    }

    @Override
    public void loadConfig(String configFile) {
        boolean loaded = false;
        Pattern pattern = Pattern.compile("^ *([^:\\n]+):\\s*\"([^\"]+)\"", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(configFile);

        System.out.println("Loading config file:\n" + configFile + "\n");

        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();

            if (key.equalsIgnoreCase("port")) {
                this.port = Integer.parseInt(value);
            } if (key.equalsIgnoreCase("htmlFilesPath")) {
                this.htmlFilesPath = value;
            } if (key.equalsIgnoreCase("routesConfigPath")) {
                this.fileManager.loadFile(Path.of(value));
                this.urlRouter.loadConfig(this.fileManager.start());
            }

            loaded = true;
        }

        if (!loaded) {
            System.err.println("Unable to load config file");
        }
    }
}