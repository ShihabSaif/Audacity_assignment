package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginFeature extends BasePage {
    //Read data from property file
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");
    String email = prop.getProperty("email");
    String password = prop.getProperty("password");
    String invalid_email = prop.getProperty("invalid_email");
    String invalid_password = prop.getProperty("invalid_password");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public LoginFeature() throws Exception, IOException, InterruptedException {
        PageFactory.initElements(driver, this);
    }
    public void LaunchBrowser() throws InterruptedException {
        driver.navigate().to(url);
    }
    public void login()
    {
        // Wait for email field and enter text
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email"))
        );
        emailField.sendKeys(email);

        // Wait for password field and enter text
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        passwordField.sendKeys(password);

        // Wait for login button, then move and click using Actions
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit"))
        );

        Actions actions = new Actions(driver);
        actions.moveToElement(loginBtn).click().perform();
    }
    public void verifyLogin() throws InterruptedException {
        WebElement headerElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h2.text-xl.font-oswald.mb-3.uppercase.not-odd\\:font-bold")
                )
        );
        String header = headerElement.getText();
        Assert.assertEquals(header.trim().toUpperCase(), "LOGIN SUCCESSFUL", "Login success header mismatch!");
    }
    public void invalidLogin()
    {

        // Wait until the "Login" span is clickable
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Login']"))
        );

        // Click the element
        loginBtn.click();

        // Enter invalid email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        emailField.sendKeys(invalid_email);

        // Enter invalid password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(invalid_password);

        // Click login button
        WebElement invalidLoginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit"))
        );
        invalidLoginBtn.click();

        // Wait for error message span
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[@class='title text-black text-md']")
                )
        );

        // Assert error text
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be displayed for invalid login");
        Assert.assertEquals(errorMsg.getText().trim(), "Your email and password both are invalid!");

    }
}
