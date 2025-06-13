package main.java.com.myWebServer;

import main.java.com.myWebServer.http.HttpRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    Server(int port) throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            HttpRequest request = new HttpRequest(inputStream);

            System.out.println("New " + request.getMethod() + " request " + "version: " + request.getHttpVersion() + " path: " + request.getPath());

            outputStream.close();
        }
    }
}