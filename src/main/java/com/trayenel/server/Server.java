package com.trayenel.server;

import com.trayenel.base.Configurable;
import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.base.FileManager;
import com.trayenel.url.UrlRouter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Server implements Configurable {
    private final HashMap<String, HttpHandler> handlers = new HashMap<>();
    private final UrlRouter urlRouter = new UrlRouter();
    private final FileManager fileManager = new FileManager();
    private Map<String, Object> config;

    public Server(String configFilePath) throws IOException {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configFilePath);

        this.config = yaml.load(inputStream);

        this.loadConfig(config);
    }

    public void listen() throws IOException {
        Map<String, Object> serverSettings = (Map<String, Object>) this.config.get("Server");
        int port = Integer.parseInt((String) serverSettings.get("Port"));

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on port " + port);

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
                    HttpHandler handler = HttpHandler.createHandler(request, this.fileManager, this.urlRouter, (String) serverSettings.get("HtmlFilesPath"));
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
    public void loadConfig(Map<String, Object> config) {
        this.config = config;

        Map<String, Object> routes = (Map<String, Object>) this.config.get("Routes");
        this.urlRouter.loadConfig(routes);

        this.config.remove("Routes");
        this.config.remove("DB");
    }
}