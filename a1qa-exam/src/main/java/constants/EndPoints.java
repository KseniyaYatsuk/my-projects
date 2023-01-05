package constants;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class EndPoints {

    static ISettingsFile configEnvironment = new JsonSettingsFile("config.json");
    static ISettingsFile environment = new JsonSettingsFile("end_points.json");

    private static final String BASE_PATH = configEnvironment.getValue("/basePath").toString();

    public static final String GET_TOKEN = String.format(BASE_PATH, environment.getValue("/getToken"));
    public static final String GET_TESTS_LIST_JSON = String.format(BASE_PATH, environment.getValue("/getTestsListJson"));
    public static final String PUT_TEST = String.format(BASE_PATH, environment.getValue("/putTest"));
    public static final String PUT_ATTACHMENT = String.format(BASE_PATH, environment.getValue("/putAttachment"));
    public static final String PUT_LOG = String.format(BASE_PATH, environment.getValue("/putLog"));
}
