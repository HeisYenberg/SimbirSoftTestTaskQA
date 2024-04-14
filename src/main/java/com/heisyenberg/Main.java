package com.heisyenberg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        WebDriver driver = new RemoteWebDriver(
                new URL("http://192.168.1.8:4444/wd/hub"), capabilities);
        System.out.println(driver);
        driver.get("https://www.globalsqa.com/" +
                "angularJs-protractor/BankingProject/#/login");
        driver.quit();
    }
}
