package com.heisyenberg;

import com.heisyenberg.pageobjects.DepositPage;
import com.heisyenberg.pageobjects.LoginPage;
import com.heisyenberg.pageobjects.TransactionsPage;
import com.heisyenberg.pageobjects.WithdrawalPage;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class MainTest {
    private static final String GRID_URL = "http://localhost:4444/wd/hub";
    private static final String LOGIN_URL = "https://www.globalsqa.com/" +
            "angularJs-protractor/BankingProject/#/login";
    private static final String[] TRANSACTIONS = {"Credit", "Debit"};
    private static WebDriver driver;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        ChromeOptions capabilities = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(GRID_URL), capabilities);
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("Harry Potter");
        loginPage.login();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    private static Integer getFibonacci() {
        LocalDate date = LocalDate.now();
        int dayOfMonth = date.getDayOfMonth() + 1;
        int a = 1;
        int b = 1;
        int result = 1;
        for (int i = 3; i <= dayOfMonth; ++i) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    @Attachment(value = "Transaction History", type = "text/csv")
    public static String writeCSV(List<String[]> transactions) {
        try (FileWriter writer = new FileWriter("transactions.csv")) {
            StringJoiner joiner = new StringJoiner("\n");
            for (String[] transaction : transactions) {
                joiner.add(transaction[0] + "," + transaction[1] + "," +
                        transaction[2]);
            }
            writer.write(joiner.toString());
            return joiner.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testTransactions() {
        TransactionsPage transactionsPage = new TransactionsPage(driver);
        List<String[]> transactions = transactionsPage.getTransactions();
        String fibonacci = getFibonacci().toString();
        for (int i = 0; i < transactions.size(); ++i) {
            String amount = transactions.get(i)[1];
            String type = transactions.get(i)[2];
            Assertions.assertEquals(fibonacci, amount);
            Assertions.assertEquals(type, TRANSACTIONS[i]);
        }
        writeCSV(transactions);
    }

    private String getBalance() {
        WebElement element = driver.findElement(
                By.cssSelector("div.center strong:nth-child(2)"));
        return element.getText();
    }

    @Order(1)
    @Test
    public void testDeposit() {
        Integer fibonacci = getFibonacci();
        DepositPage depositPage = new DepositPage(driver);
        depositPage.setAmount(fibonacci);
        depositPage.submit();
        Assertions.assertEquals(getBalance(), fibonacci.toString());
    }

    @Order(2)
    @Test
    public void testWithdrawal() {
        Integer balance = 0;
        WithdrawalPage withdrawalPage = new WithdrawalPage(driver);
        withdrawalPage.setAmount(getFibonacci());
        withdrawalPage.submit();
        Assertions.assertEquals(getBalance(), balance.toString());
        testTransactions();
    }
}
