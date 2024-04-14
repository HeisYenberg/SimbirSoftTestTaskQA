package com.heisyenberg.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TransactionsPage extends PageObject {
    private static final String TRANSACTIONS_URL =
            "https://www.globalsqa.com/" +
                    "angularJs-protractor/BankingProject/#/listTx";

    public TransactionsPage(WebDriver driver) {
        super(driver);
        getElement(By.xpath("//button[contains(.,'Transactions')]"))
                .click();
        driver.get(TRANSACTIONS_URL);
        driver.navigate().refresh();
    }

    public List<String[]> getTransactions() {
        By by = By.cssSelector("tr[id^='anchor']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
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
