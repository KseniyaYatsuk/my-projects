package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import constants.EndPoints;
import enums.ContentType;
import models.Test;
import parser.JsonParser;

import java.util.List;

public class ProjectApiUtils {

    static ISettingsFile environment = new JsonSettingsFile("parameters.json");

    public static String getToken(int variant) {
        return ApiUtils.postRequest(String.join("", EndPoints.GET_TOKEN, "?",
                environment.getValue("/variant").toString(), String.valueOf(variant))).asString();
    }

    public static List<Test> getJsonTests(int projectId) {
        String response = ApiUtils.postRequest(String.join("", EndPoints.GET_TESTS_LIST_JSON, "?",
                environment.getValue("/projectId").toString(), String.valueOf(projectId))).asString();
        return JsonParser.parseListOfData(response, Test[].class);
    }

    public static int putTest(String SID, String projectName, String testName, String methodName,
                              String env, String startTime, String browser) {
        return Integer.parseInt(ApiUtils.postRequest(String.join("", EndPoints.PUT_TEST, "?",
                environment.getValue("/SID").toString(), SID, "&",
                environment.getValue("/projectName").toString(), projectName, "&",
                environment.getValue("/testName").toString(), testName, "&",
                environment.getValue("/methodName").toString(), methodName, "&",
                environment.getValue("/env").toString(), env, "&",
                environment.getValue("/startTime").toString(), startTime, "&",
                environment.getValue("/browser").toString(), browser)).asString());
    }

    public static void putAttachment(int testId, String key, String content, ContentType contentType) {
        ApiUtils.postUrlEncodedRequest(String.join("", EndPoints.PUT_ATTACHMENT, "?",
                environment.getValue("/testId").toString(), String.valueOf(testId), "&",
                environment.getValue("/contentType").toString(), contentType.getContentType()), key, content);
    }

    public static void putLog(int testId, String log) {
        ApiUtils.postRequest(String.join("", EndPoints.PUT_LOG, "?",
                environment.getValue("/testId").toString(), String.valueOf(testId), "&",
                environment.getValue("/content").toString(), log));
    }
}
