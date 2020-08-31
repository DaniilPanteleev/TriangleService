package services.rest.endpoints;

import io.restassured.http.Method;
import org.javatuples.Pair;

import static io.restassured.http.Method.*;
import static java.lang.String.format;

public class TriangleEndpoints {

    public static Pair<Method, String> postTriangle() {
        return Pair.with(POST, "");
    }

    public static Pair<Method, String> getTriangle(String id) {
        return Pair.with(GET, format("/%s", id));
    }

    public static Pair<Method, String> deleteTriangle(String id) {
        return Pair.with(DELETE, format("/%s", id));
    }

    public static Pair<Method, String> getAllTriangles() {
        return Pair.with(GET, "/all");
    }

    public static Pair<Method, String> getTrianglePerimeter(String id) {
        return Pair.with(GET, format("/%s/perimeter", id));
    }

    public static Pair<Method, String> getTriangleArea(String id) {
        return Pair.with(GET, format("/%s/area", id));
    }

}
