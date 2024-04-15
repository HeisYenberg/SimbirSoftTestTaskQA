package com.heisyenberg.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BalancePage extends PageObject {
    public BalancePage(WebDriver driver) {
        super(driver);
    }

    public String getBalance() {
        return driver.findElement(
                By.cssSelector("div.center strong:nth-child(2)")).getText();
    }
}
