package utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

public final class JsonUtils {

    private JsonUtils() {
    }

    public static <T> T getJsonObjectFromFile(String fileName, Class<T> classToDeserialize) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), classToDeserialize);
       } catch (IOException e) {
            throw new RuntimeException(format("Exception while deserializing file: %s to class: %s", fileName, classToDeserialize.getName()));
        }
    }

    public static <T> List<T> getJsonArrayFromFile(String fileName, Class<T> classToDeserialize) {
        ObjectMapper mapper = new ObjectMapper();
        Class<?> clazz;
        try {
            clazz = Class.forName(classToDeserialize.getName());
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), type);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(format("Exception while deserializing file: %s to class: %s\n%s", fileName, classToDeserialize.getName(), e.getMessage()));
        }
    }

    public static <T> T getJsonObject(ValidatableResponse response, Class<T> classToDeserialize, String path) {
        return response.extract().jsonPath().getObject(path, classToDeserialize);
    }

}
