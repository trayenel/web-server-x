package main.java.com.myWebServer;

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

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            OutputStream out = clientSocket.getOutputStream();

            System.out.println("New connection from " + clientSocket.getInetAddress());

            String html = "sug pula 8====D";

            final String CRLF = "\n\r"; // 13, 10

            String response = "HTTP/1.1 200 OK" + CRLF + // Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    "Content-Length: " + html.getBytes().length + CRLF + CRLF + // end of HEADER
                    html +
                    CRLF + CRLF;

            out.write(response.getBytes());
            out.flush();

            out.close();
            in.close();
        }
    }
}