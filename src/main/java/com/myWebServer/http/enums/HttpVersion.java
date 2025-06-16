package main.java.com.myWebServer.http.enums;

public enum HttpVersion {
    HTTP_1_0("HTTP/1.0"),
    HTTP_1_1("HTTP/1.1"),
    HTTP_2_0("HTTP/2.0");

    private final String text;

    HttpVersion(String text) {
        this.text = text;
    }

    public static HttpVersion fromString(String version) {
        for (HttpVersion httpVersion : HttpVersion.values()) {
            if (httpVersion.text.equalsIgnoreCase(version)) {
                return httpVersion;
            }
        }
        throw new IllegalArgumentException("Unsupported HTTP version: " + version);
    }

    public String getText() {
        return text;
    }
}
