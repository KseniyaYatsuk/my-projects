package parser;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class JsonParser {

    private static String configData = "src/main/resources/config_data.json";
    private static String testData = "src/main/resources/test_data.json";

    static ObjectMapper objectMapper = new ObjectMapper();

    public static HashMap<String, String> parseConfigData() {
        File file = new File(configData);
        HashMap<String, String> configData = new HashMap<>();
        try {
            configData = objectMapper.readValue(file, HashMap.class);
        } catch (IOException e) {
            Logger.getInstance().error(e.toString());
            throw new NullPointerException();
        }
        return configData;
    }

    public static HashMap<String, String> parseTestData() {
        File file = new File(testData);
        HashMap<String, String> configData = new HashMap<>();
        try {
            configData = objectMapper.readValue(file, HashMap.class);
        } catch (IOException e) {
            Logger.getInstance().error(e.toString());
            throw new NullPointerException();
        }
        return configData;
    }
}
