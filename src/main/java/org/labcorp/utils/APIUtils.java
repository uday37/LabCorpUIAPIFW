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

    public static Response getRequest(String resourcePath, Map headers) {
        RestAssured.baseURI =BASE_URL;
        System.out.println("BaseUrl:"+BASE_URL);
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.headers(headers).get(resourcePath);
        System.out.println("resourcePath:"+resourcePath);
//        System.out.println(response.asString());
//        JsonPath jsonPath = response.jsonPath();
        return response;
    }

    public static Response postRequest(String resourcePath, File playLoad) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        Response response = request.body(playLoad).post(resourcePath);
        System.out.println(response.asString());
        return response;
    }
}
