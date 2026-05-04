package org.labcorp.utils;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.labcorp.utils.ConfigReader;

import java.io.File;
import java.util.List;
import java.util.Map;

public class APIUtils {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    private static final String BASE_URL  = ConfigReader.getProperty("baseurl");
    protected static final String REQRES_URL = "https://reqres.in/api";

    public static Response getRequest(String url) {
        RestAssured.baseURI =BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.get(url);
//        System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        return response;
    }

//    public static void verifyResponse(Response response, Map<String, String> mp) {
//
//        System.out.println(response.asString());
//        JsonPath jsonPath = response.jsonPath();
//        String path= jsonPath.get(mp.get("path"));
//        List<String> emails = jsonPath.getList("headers.Host");
//        String firstUserName = jsonPath.getString("data[0].first_name");
//
//    }

    public static Response postRequest(String url, File playLoad) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
//        request.header("Authorization", "Bearer " + token)
//                .header("Content-Type", "application/json");

        Response response = request.body(playLoad).post(url);
        System.out.println(response.asString());
        return response;
    }
}
