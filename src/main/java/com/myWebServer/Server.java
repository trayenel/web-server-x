package main.java.com.myWebServer;

import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;
import main.java.com.myWebServer.http.enums.HttpMethod;
import main.java.com.myWebServer.http.enums.HttpVersion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private final ServerSocket serverSocket;

    Server(int port) throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSocket.setKeepAlive(true);
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            System.out.println("New connection established from " + clientSocket.getInetAddress());

            HttpRequest request = new HttpRequest(inputStream);
            HttpResponse response = null;

            if (request.getMethod().equals(HttpMethod.GET)) {
                switch (request.getPath()) {
                    case "/" -> response = new HttpResponse(HttpVersion.HTTP_1_1, 200, "Hello World");
                    default -> response = new HttpResponse(HttpVersion.HTTP_1_1, 404, "Not Found");
                }
            }

            if (response != null) {
                byte[] bytes = HttpResponse.stringify(response).getBytes(StandardCharsets.UTF_8);

                outputStream.write(bytes);
                outputStream.flush();

            }

            inputStream.close();
            outputStream.close();
        }
    }
}