package org.labcorp.stepdefs.ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.labcorp.pages.CareersHomePage;
import org.labcorp.pages.MainPage;
import org.labcorp.utils.ConfigReader;
import org.labcorp.utils.ElementsUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LapCorpSetpDef {
    private static WebDriver driver;
    private MainPage mainPage;
    private CareersHomePage careersHomePage;
    private  String originalTab;
    @Before
    public void initializeDriver() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        System.out.println("*********URL:"+ConfigReader.getProperty("appurl"));
        driver.get(ConfigReader.getProperty("appurl"));

    }


    @After
    public void closeDriver() {
//        driver.quit();

    }

    @Given("user is already on Labcorp main page")
    public void user_is_already_on_labcorp_main_page() {
        mainPage = new MainPage(driver);
        mainPage.clickAcceptAllCookiesBtn();

    }

    @When("user click on Careers link")
    public void user_click_on_careers_link() {
        mainPage.clickOnCareersLink();
    }

    @When("user search {string} and select the position")
    public void user_search_and_select_the_position(String position) {
        mainPage.searchAndClickOnPosition(position);
    }

    @Then("user verifies the {string}, {string} and {string}")
    public void user_verifies_the_and(String jobTitle, String jobLocation, String jobId) {
        Assert.assertEquals(jobTitle, mainPage.getJobTitle());
        Assert.assertEquals(jobLocation, mainPage.getJobLocation());
        Assert.assertEquals(jobId, mainPage.getJobId());
    }

    @Then("user verifies additional requirement texts {string}, {string} and {string}")
    public void user_verifies_additional_requirement_texts_and(String req1, String req2, String req3) {
        Assert.assertTrue(mainPage.getRequirementText1(req1));
        Assert.assertTrue(mainPage.getRequirementText1(req2));
        Assert.assertTrue(mainPage.getRequirementText1(req3));
    }

    @Then("user click on ApplyNow button")
    public void user_click_on_apply_now_button() {
         originalTab = driver.getWindowHandle();
        mainPage.clickApplyNowBtn();

    }

    @Then("user again verifies {string}, {string}")
    public void user_again_verifies(String jobTitle, String startAppTxt) {
        ElementsUtil.switchToWindow(driver,originalTab);
        careersHomePage= new CareersHomePage(driver);
        Assert.assertEquals(startAppTxt, careersHomePage.getStartYourAppText());
        Assert.assertEquals(jobTitle, careersHomePage.getTitle());
        driver.close();
        driver.switchTo().window(originalTab);
    }

}
