package entities.enums;

public enum CustomHttpHeaderNames {

    X_USER("X-User"),
    ;

    private String headerName;

    CustomHttpHeaderNames(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }

}
