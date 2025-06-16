package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpStatusCode;
import main.java.com.myWebServer.http.enums.HttpVersion;

public class HttpResponse extends HttpMessage {
    private HttpStatusCode statusCode;
    private String body;

    public HttpResponse() {
        super();
    }

    public HttpResponse(HttpVersion version, int statusCode, String body) {
        super();

        this.httpVersion = version;
        this.statusCode = HttpStatusCode.fromCode(statusCode);
        this.body = body;
    }

    public static String stringify(HttpResponse response) {
        return response.getHttpVersion().getText() + " " + response.getStatusCode().getCode() + " " + response.getStatusCode().getDescription() + "\n" + "\n" + response.getBody();
    }

    public HttpResponse(HttpRequest request) {
        super();
        setHttpVersion(request.getHttpVersion().getText());
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    protected void setHttpVersion(String httpVersion) {
        this.httpVersion = HttpVersion.fromString(httpVersion);
    }

    public String getBody() {
        return body;
    }
}


