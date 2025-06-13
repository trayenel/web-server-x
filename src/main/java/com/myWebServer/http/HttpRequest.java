package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpMethod;
import main.java.com.myWebServer.http.enums.HttpVersion;

import java.io.IOException;
import java.io.InputStream;

public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String path;

    public HttpRequest(InputStream inputStream) throws IOException {
        super();
        String[] requestStr = read(inputStream);

        this.setMethod(requestStr[0]);
        this.setPath(requestStr[1]);
        this.setHttpVersion(requestStr[2]);
    }

    private String[] read(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();

        do {
            result.append((char) inputStream.read());
        } while (inputStream.available() > 0);

        return result.toString().split("[ \\r\\n]+");
    }

    @Override
    protected void setHttpVersion(String versionStr) {
        httpVersion = HttpVersion.fromString(versionStr);
    }

    private void setMethod(String methodStr) {
        method = HttpMethod.fromString(methodStr);
    }

    private void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }
}