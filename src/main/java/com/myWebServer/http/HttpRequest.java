package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpMethod;

import java.io.IOException;
import java.io.InputStream;

public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String path;
    private String body;

    public HttpRequest(InputStream inputStream) throws IOException {
        super();
        String[] requestStr = read(inputStream);

        this.extractHeaders(requestStr);
        this.setMethod(requestStr[0]);
        this.setPath(requestStr[1]);
        this.setHttpVersion(requestStr[2]);

        if (this.method == HttpMethod.POST || this.method == HttpMethod.PUT || this.method == HttpMethod.PATCH) {
            this.setBody(requestStr[requestStr.length - 1]);
        }
    }

    private String[] read(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();

        do {
            result.append((char) inputStream.read());
        } while (inputStream.available() > 0);

        return result.toString().split("[ \\r\\n]+");
    }

    protected void extractHeaders(String[] headers) {
        for (int i = 4; i < headers.length - 1; i += 2) {
            String key = headers[i - 1];
            String value = headers[i];

            this.addHeader(key, value);
        }
    }

    private void setMethod(String methodStr) {
        method = HttpMethod.fromString(methodStr);
    }

    private void setPath(String path) {
        this.path = path;
    }

    private void setBody(String body) {
        this.body = body;
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