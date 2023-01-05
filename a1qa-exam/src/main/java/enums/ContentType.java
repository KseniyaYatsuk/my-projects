package enums;

public enum ContentType {

    IMAGE_PNG("image/png");

    private final String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
