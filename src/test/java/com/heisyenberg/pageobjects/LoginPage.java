package com.heisyenberg.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends PageObject {
    public LoginPage(WebDriver driver) {
        super(driver);
        getElement(By.cssSelector(
                "button.btn.btn-primary.btn-lg[ng-click='customer()']"))
                .click();
    }

    public void setUsername(String username) {
        WebElement element = getElement(By.id("userSelect"));
        Select select = new Select(element);
        select.selectByVisibleText(username);
    }

    public void login() {
        getElement(By.xpath("//button[contains(.,'Login')]"))
                .click();
    }
}