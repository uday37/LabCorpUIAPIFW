package org.labcorp.stepdefs.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
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
import pojos.Customers;
import pojos.Items;
import pojos.Payments;
import pojos.RawBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeeceptorSetpDef {
    File jsonFile = new File("src/main/resources/testdata/payload.json");
    Response response;
    Response postResponse;
    String postJsonString;
    ObjectMapper mapper = new ObjectMapper();
    RawBody rawBodies= null;
    static  Map<String, String> headers = new HashMap<>();
    @BeforeAll
    public static void setUp(){

        headers.put("Host", "echo.free.beeceptor.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36 Edg/147.0.0.0");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("Accept-Encoding", "gzip, deflate, br, zstd");
        headers.put("Accept-Language", "en-US,en;q=0.9");
        headers.put("Priority", "u=0, i");
        headers.put("Sec-Ch-Ua", "\"Microsoft Edge\";v=\"147\"); \"Not.A/Brand\";v=\"8\"); \"Chromium\";v=\"147\"");
        headers.put("Sec-Ch-Ua-Mobile", "?0");
        headers.put("Sec-Ch-Ua-Platform", "\"Windows\"");
        headers.put("Sec-Fetch-Dest", "document");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("Sec-Fetch-Site", "none");
        headers.put("Sec-Fetch-User", "?1");
        headers.put("Upgrade-Insecure-Requests", "1");

    }
    @Given("user hit the GET API {string}")
    public void user_hit_the_get_api_sample_request_author_beeceptor(String path) {

        response = APIUtils.getRequest(path, headers);

    }

    @Then("user validates the {string}, {string} in response")
    public void user_validates_the_and_in_response(String path, String ip) {
        JsonPath jsonPath = response.jsonPath();
        System.out.println("JsonPath:"+response.asString());
        System.out.println("Path:"+jsonPath.getString("path"));
        Assert.assertEquals(path, jsonPath.getString("path"));
        Assert.assertTrue(jsonPath.getString("ip").contains(ip));

    }

    @Then("user validates all headers in the response")
    public void user_validates_all_headers_in_the_response() {
        JsonPath jsonPath = response.jsonPath();
        for (String key : headers.keySet()) {
            Assert.assertEquals(headers.get(key), jsonPath.get("headers."+key));
//        Assert.assertTrue(jsonPath.get("headers.Via").toString().contains(headers.get("Via")));
        }
    }


    @Given("user hit the POST API {string}")
    public void user_hit_the_post_api(String path) {
         postResponse=APIUtils.postRequest(path, jsonFile);


    }
    @Given("user validates customer information in the response")
    public void user_validates_customer_information_in_the_response(DataTable dataTable) {

        try {
            JsonPath jsonPath = postResponse.jsonPath();
            postJsonString = jsonPath.getString("rawBody");
            postJsonString=postJsonString.replace("janesmith@example.com", "\"janesmith@example.com\"");
//            System.out.println("postJsonString *********:"+postJsonString);

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//            JsonNode rootNode = mapper.readTree(postJsonString);

             rawBodies= mapper.readValue(postJsonString, RawBody.class);
            Customers customersJson = rawBodies.getCustomer();

            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> val : data) {
                Assert.assertEquals(val.get("name"), customersJson.getName());
                Assert.assertEquals(val.get("email"), customersJson.getEmail());
                Assert.assertEquals(val.get("phone"), customersJson.getPhone());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
    @Given("user validates payment information in the response")
    public void user_validates_payment_information_in_the_response(DataTable dataTable) {

        //            rawBodies = mapper.readValue(postJsonString, RawBody.class);
        Payments paymentsJson = rawBodies.getPayment();

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> val : data) {
            Assert.assertEquals(val.get("method"), paymentsJson.getMethod());
            Assert.assertEquals(val.get("transaction_id"), paymentsJson.getTransaction_id());
            Assert.assertEquals(val.get("amount"), paymentsJson.getAmount());
            Assert.assertEquals(val.get("currency"), paymentsJson.getCurrency());
        }

    }
    @Given("user validates product information in the response")
    public void user_validates_product_information_in_the_response(DataTable dataTable) {
        Items itemsJson[] = rawBodies.getItems();
        System.out.println("ItemsJson:"+itemsJson);

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        System.out.println("data:"+data);
        for(int i=0; i<itemsJson.length; i++){
//                System.out.println("List:"+data.get(i).get("product_id"));
                Assert.assertEquals(data.get(i).get("product_id"), itemsJson[i].getProduct_id());
                Assert.assertEquals(data.get(i).get("name"), itemsJson[i].getName());
                Assert.assertEquals(data.get(i).get("quantity"), itemsJson[i].getQuantity());
                Assert.assertEquals(data.get(i).get("price"), itemsJson[i].getPrice());
            }


    }
}
