package main.java.com.myWebServer.server;

import main.java.com.myWebServer.http.HttpHandler;
import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Server {
    private final ServerSocket serverSocket;
    private final HashMap<String, HttpHandler> handlers = new HashMap<>();

    public Server(int port) throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            System.out.println("New connection established from " + clientSocket.getInetAddress());

            HttpRequest request = new HttpRequest(inputStream);

            if (!handlers.containsKey(request.getPath())) {
                HttpHandler handler = HttpHandler.createHandler(request);
                handlers.put(request.getPath(), handler);
            }

            HttpHandler handler = handlers.get(request.getPath());
            HttpResponse response = handler.handleRequest();

            byte[] bytes = HttpResponse.stringify(response).getBytes(StandardCharsets.UTF_8);

            outputStream.write(bytes);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
        }
    }
}