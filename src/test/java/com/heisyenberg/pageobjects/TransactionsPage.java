package com.heisyenberg.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TransactionsPage extends PageObject {
    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        getElement(By.xpath("//button[contains(.,'Transactions')]"))
                .click();
    }

    public List<String[]> getTransactions() {
        By by = By.cssSelector("tr[id^='anchor']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        List<String[]> transactions = new ArrayList<>();
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements) {
            String[] transaction = new String[3];
            transaction[0] = element.findElement(
                    By.cssSelector("td:nth-child(1)")).getText();
            transaction[1] = element.findElement(
                    By.cssSelector("td:nth-child(2)")).getText();
            transaction[2] = element.findElement(
                    By.cssSelector("td:nth-child(3)")).getText();
            transactions.add(transaction);
        }
        return transactions;
    }
}
