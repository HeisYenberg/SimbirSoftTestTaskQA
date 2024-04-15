package com.heisyenberg;

import com.heisyenberg.pageobjects.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainTest {
    private static final String GRID_URL = "http://localhost:4444/wd/hub";
    private static final String LOGIN_URL = "https://www.globalsqa.com/" +
            "angularJs-protractor/BankingProject/#/login";
    private static final String[] TRANSACTIONS = {"Credit", "Debit"};
    private static WebDriver driver;

    @Step
    @BeforeAll
    public static void setup() throws MalformedURLException {
        ChromeOptions capabilities = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(GRID_URL), capabilities);
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("Harry Potter");
        loginPage.login();
    }

    @Step
    private static void testDeposit() {
        Integer fibonacci = Fibonacci.getFibonacci();
        DepositPage depositPage = new DepositPage(driver);
        depositPage.open();
        depositPage.setAmount(fibonacci);
        depositPage.submit();
        BalancePage balancePage = new BalancePage(driver);
        Assertions.assertEquals(balancePage.getBalance(), fibonacci.toString());
    }

    @Step
    private static void testWithdraw() {
        BalancePage balancePage = new BalancePage(driver);
        WithdrawalPage withdrawalPage = new WithdrawalPage(driver);
        withdrawalPage.open();
        withdrawalPage.setAmount(Fibonacci.getFibonacci());
        withdrawalPage.submit();
        Assertions.assertEquals(balancePage.getBalance(), "0");
    }

    @Step
    private static void testTransactions() {
        TransactionsPage transactionsPage = new TransactionsPage(driver);
        transactionsPage.open();
        List<String[]> transactions = transactionsPage.getTransactions();
        for (int i = 0; i < transactions.size(); ++i) {
            String amount = transactions.get(i)[1];
            String type = transactions.get(i)[2];
            Assertions.assertEquals(
                    Fibonacci.getFibonacci().toString(), amount);
            Assertions.assertEquals(type, TRANSACTIONS[i]);
        }
        CSVWriter.writeCSV(transactions);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void test() {
        testDeposit();
        testWithdraw();
        testTransactions();
    }
}
