package com.simbirsoft.bankingproject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WithdrawalPage extends PageObject {
    public WithdrawalPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        getElement(By.xpath("//button[contains(.,'Withdrawl')]"))
                .click();
    }

    public void setAmount(Integer amount) {
        getElement(By.cssSelector("input.form-control.ng-pristine.ng-untouched"
                + ".ng-invalid.ng-invalid-required[type='number']"))
                .sendKeys(amount.toString());
    }

    public void submit() {
        getElement(By.cssSelector("button.btn.btn-default[type='submit']"))
                .click();
    }
}
