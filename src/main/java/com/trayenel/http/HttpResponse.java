package com.trayenel.http;


import com.trayenel.http.enums.HttpStatusCode;
import com.trayenel.http.enums.HttpVersion;

import java.util.HashMap;

public class HttpResponse extends HttpMessage {
    private final HttpStatusCode statusCode;
    private final String body;

    public HttpResponse(HttpVersion version, int statusCode, String body, HashMap<String, String> headers) {
        super();

        this.httpVersion = version;
        this.statusCode = HttpStatusCode.fromCode(statusCode);
        this.body = body;

        this.setHeaders(headers);
    }

    public static String stringify(HttpResponse response) {
        return response.getHttpVersion().getText() + " " + response.getStatusCode().getCode() + " " + response.getStatusCode().getDescription() + "\n" + response.getHeaders() + "\n\n" + response.getBody();
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

     public String getBody() {
        return body;
    }
}


