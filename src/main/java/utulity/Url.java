package utulity;

public class Url {

    private final static String BASE_URI = "https://restful-booker.herokuapp.com";

    public static String getBaseUri() {
        return BASE_URI;
    }

    public static String getBaseUri(String resourcePath) {
        return BASE_URI + resourcePath;
    }
}
