package main.java.com.myWebServer.http;

public record Header(String key, String value) {
    public Header(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        this.key = key;
        this.value = value;
    }
}
