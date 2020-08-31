package utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public final class Utils {

    private Utils() {
    }

    public static String readFile(String filePath) {
        try {
            return IOUtils.toString(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)), Charset.defaultCharset().name());
        } catch (IOException e) {
            throw new RuntimeException(format("Exception while reading file %s", filePath));
        }
    }

    public static List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(filePath).toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(format("Exception while reading file %s", filePath));
        }
    }

}
