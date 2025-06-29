package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String path;
    private String body;

    public HttpRequest(InputStream inputStream) throws IOException {
        super();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> requestStr = readRequest(bufferedReader);

        if (!requestStr.isEmpty()) {
        this.setRequestLine(requestStr.getFirst());
        this.setHeaders(requestStr);

        if (this.method == HttpMethod.POST || this.method == HttpMethod.PUT || this.method == HttpMethod.PATCH) {
            this.setBody(bufferedReader);
            }
        }
    }

    private List<String> readRequest(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            lines.add(line);
        }

        return lines;
    }

    protected void setHeaders(List<String> headers) {

        for (int i = 1; i < headers.size(); i++) {
            String line = headers.get(i);
            int colonIdx = line.indexOf(':');

            if (colonIdx != -1) {
                String key = line.substring(0, colonIdx).trim();
                String value = line.substring(colonIdx + 1).trim();

                this.addHeader(key, value);
            }
        }

    }

    private void setRequestLine(String requestLine) {
        String[] splitRequestLine = requestLine.split(" ");

        this.setMethod(splitRequestLine[0]);
        this.setPath(splitRequestLine[1]);
        this.setHttpVersion(splitRequestLine[2]);
    }

    private void setMethod(String methodStr) {
        method = HttpMethod.fromString(methodStr);
    }

    private void setPath(String path) {
        this.path = path;
    }

    private void setBody(BufferedReader bufferedReader) throws IOException {
        int contentLength = Integer.parseInt(this.getHeader("Content-Length"));

        char[] bodyChars  = new char[contentLength];
        int readChars = bufferedReader.read(bodyChars);
        this.body = new String(bodyChars, 0, readChars);
    }

    public String getBody() {
        return body;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }
}