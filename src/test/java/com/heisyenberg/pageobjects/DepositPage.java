package com.heisyenberg.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DepositPage extends PageObject {
    public DepositPage(WebDriver driver) {
        super(driver);
        getElement(By.xpath("//button[contains(.,'Deposit')]"))
                .click();
    }

    public void setAmount(Integer amount) {
        getElement(By.cssSelector("input[type='number']"))
                .sendKeys(amount.toString());
    }

    public void submit() {
        getElement(By.cssSelector("button.btn.btn-default[type='submit']"))
                .click();
    }
}
