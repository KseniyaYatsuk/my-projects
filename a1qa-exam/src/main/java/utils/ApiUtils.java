package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

    public static Response postRequest(String request) {
        return RestAssured.given()
                .when().post(request)
                .then()
                .extract().response();
    }

    public static Response postUrlEncodedRequest(String request, String key, String value) {
        return RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam(key, value)
                .when().post(request)
                .then()
                .extract().response();
    }
}
