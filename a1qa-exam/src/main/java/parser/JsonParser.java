package parser;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> parseListOfData(String json, Class<T[]> clazz) {
        List<T> tests = new ArrayList<>();
        try {
            tests = List.of(objectMapper.readValue(json, clazz));
        } catch (IOException e) {
            Logger.getInstance().error(e.toString());
            throw new NullPointerException();
        }
        return tests;
    }
}
