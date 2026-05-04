package org.labcorp.pages;

import org.labcorp.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersHomePage {

    WebDriver driver;

    private By careerTitle = By.xpath("//h3");
    private By startYourApplication = By.xpath("//h2");

    // Constructor to initialize driver
    public CareersHomePage(WebDriver driver) {
        this.driver = driver;
    }


    public String getTitle() {
        WaitUtils.waitForElementVisible(driver, careerTitle);
        return driver.findElement(careerTitle).getText();
    }

    public String getStartYourAppText() {
        WaitUtils.waitForElementVisible(driver, startYourApplication);
        return driver.findElement(startYourApplication).getText();
    }

}
