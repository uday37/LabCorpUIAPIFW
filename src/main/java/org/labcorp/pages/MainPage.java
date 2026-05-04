package org.labcorp.pages;


import org.labcorp.utils.ElementsUtil;
import org.labcorp.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    WebDriver driver;

    // Locators
    private By acceptAllCookiesBtn = By.id("onetrust-accept-btn-handler");
    private By careersLnk = By.linkText("Careers");
    private By searchPosition = By.xpath("//input[@placeholder='Search job title or location']");
    private String selectSearchedPosition  = "//a[contains(@data-ph-at-job-title-text, '%s')]";
    private By jobTitle = By.className("job-title");
    private By jobLocation = By.xpath("//span[@class='au-target job-location']");
    private By jobId = By.xpath("//span[@class='au-target jobId']");
    private By requirementText1 = By.xpath("//li[contains(text(),'Development and delivery of training to operationa')]");
//    private By requirementText2 = By.xpath("//li[contains(text(),'4 years or more experience in a GxP regulatory environment')]");
//    private By requirementText3 = By.xpath("//li[contains(text(),'Able to clearly articulate processes to provide training");
private String requirementText  = "//li[contains(text(), '%s')]";

    private By applyNowBtn = By.xpath("//ppc-content[text()='Apply Now']");
    // Constructor to initialize driver
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }



    public void clickAcceptAllCookiesBtn(){
        WaitUtils.waitForElementVisible(driver, acceptAllCookiesBtn);
        WaitUtils.waitForElementClickable(driver, acceptAllCookiesBtn);
         driver.findElement(acceptAllCookiesBtn).click();
    }

    public void clickOnCareersLink(){
        WaitUtils.waitForElementClickable(driver, careersLnk);
        driver.findElement(careersLnk).click();
    }

    public void searchAndClickOnPosition(String position){
        WaitUtils.waitForElementPresence(driver, searchPosition);

        driver.findElement(searchPosition).sendKeys(position);
        By searchedPosition = ElementsUtil.generateUniqueLocator(selectSearchedPosition ,position);
        System.out.println("******** Position ********:"+searchedPosition);
        WaitUtils.waitForElementClickable(driver, searchedPosition);
        driver.findElement(searchedPosition).click();
    }

    public String getJobTitle(){
        WaitUtils.waitForElementPresence(driver, jobTitle);
        return  driver.findElement(jobTitle).getText();
    }

    public String getJobLocation(){
        WaitUtils.waitForElementPresence(driver, jobLocation);
        String location = driver.findElement(jobLocation).getText();
        location=location.replaceAll("\\R", "");
        location=location.replaceAll("Location", "");
        System.out.println("location:"+location );

        return  location;
    }

    public String getJobId(){
        WaitUtils.waitForElementPresence(driver, jobId);
        String jobid = driver.findElement(jobId).getText();
        jobid=jobid.replaceAll("\\R", "");
        jobid=jobid.replaceAll("Job ID :", "");
        System.out.println("location:"+jobid );
        return jobid.trim();
    }

    public boolean getRequirementText1(String requirement){
        By requirementEle = ElementsUtil.generateUniqueLocator(requirementText ,requirement);
        WaitUtils.waitForElementPresence(driver, requirementEle);
        return  driver.findElement(requirementEle).isDisplayed();
    }

    public void clickApplyNowBtn(){
        WaitUtils.waitForElementClickable(driver, applyNowBtn);
        driver.findElement(applyNowBtn).click();
    }


}
