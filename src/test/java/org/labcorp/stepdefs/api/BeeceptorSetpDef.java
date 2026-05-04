package org.labcorp.stepdefs.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.labcorp.pages.CareersHomePage;
import org.labcorp.pages.MainPage;
import org.labcorp.utils.APIUtils;
import org.labcorp.utils.ConfigReader;
import org.labcorp.utils.ElementsUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;
import java.util.Map;

public class BeeceptorSetpDef {
    File jsonFile = new File("src/main/resources/testdata/payload.json");
    Response response;
    Response postResponse;

    @Given("user hit the GET API {string}")
    public void user_hit_the_get_api_sample_request_author_beeceptor(String path) {
        response = APIUtils.getRequest(path);

    }

    @Then("user validates the {string}, {string} in response")
    public void user_validates_the_and_in_response(String path, String ip) {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(path, jsonPath.getString("path"));
        Assert.assertTrue(jsonPath.getString("ip").contains(ip));

    }

    @Then("user validates all headers in the response")
    public void user_validates_all_headers_in_the_response(DataTable dataTable) {
        JsonPath jsonPath = response.jsonPath();
//        System.out.println("****** HOST:" + jsonPath.get("headers.Host"));

//        String host= jsonPath.getString("headers.Host").;

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> val : data) {
//            System.out.println("Host:******** : " + val.get("Host"));
            Assert.assertEquals(val.get("Host"), jsonPath.get("headers.Host"));
//            Assert.assertEquals(val.get("Via"), jsonPath.get("headers.Via"));
            Assert.assertTrue(jsonPath.get("headers.Via").toString().contains(val.get("Via")));
            Assert.assertEquals(val.get("Sec-Fetch-Dest"), jsonPath.get("headers.Sec-Fetch-Dest"));
        }
    }


    @Given("user hit the POST API {string}")
    public void user_hit_the_post_api(String path) {
         postResponse=APIUtils.postRequest(path, jsonFile);


    }
    @Given("user validates customer information in the response")
    public void user_validates_customer_information_in_the_response() {
        JsonPath jsonPath = postResponse.jsonPath();
        System.out.println("******Name:" + jsonPath.get("rawBody.customer.name"));
        System.out.println("****** Email:" + jsonPath.get("rawBody.customer.email"));
        //couldn't get time to implement

    }
    @Given("user validates payment information in the response")
    public void user_validates_payment_information_in_the_response() {
        //couldn't get time to implement
    }
    @Given("user validates product information in the response")
    public void user_validates_product_information_in_the_response() {
//couldn't get time to implement
    }
}
