//package org.labcorp.hooks;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.labcorp.utils.ConfigReader;
//import org.openqa.selenium.WebDriver;
//
//import java.util.concurrent.TimeUnit;
//
//public class Hooks {
//
//    public static WebDriver driver;
//
////
////    @Before
////    public void initializeDriver(){
////
////        driver = WebDriverManager.chromedriver().getWebDriver();
////        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
////        driver.manage().window().maximize();
////        driver.get(ConfigReader.getProperty("url"));
////
////    }
////
////
////    @After
////    public void closeDriver(){
////        driver.quit();
////
////    }
//}
