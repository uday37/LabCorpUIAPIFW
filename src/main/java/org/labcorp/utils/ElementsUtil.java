package org.labcorp.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.Set;

public class ElementsUtil {

    public static By generateUniqueLocator(String dynamicXpath, String textValue) {
        String formattedXpath = String.format(dynamicXpath, textValue);
        System.out.println("formattedXpath:"+formattedXpath);
        return By.xpath(formattedXpath);
    }

    public static void switchToWindow(WebDriver driver, String originalTab) {
        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

    }
}
