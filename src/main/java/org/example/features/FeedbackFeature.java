package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.example.browserOpen.BrowserOpening.driver;

public class FeedbackFeature extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");
    String signin_email = prop.getProperty("signin_email");
    String signin_password = prop.getProperty("signin_password");
    public FeedbackFeature() throws Exception, IOException, InterruptedException {
    }
    public void SignIn()
    {
        // Wait until the Sign In link is present
        WebElement signInLink = wait
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[normalize-space(text())='Sign In']")
                ));
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center'});",
                signInLink
        );
        // Click with JS (avoids interception issues)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInLink);

        //Enter email
        // Wait for the email input to be visible
        WebElement emailInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("email")
                ));
        // Send keys to the input
        emailInput.sendKeys(signin_email);

        //Enter password
        // Wait for the password input to be visible
        WebElement passwordInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("password")
                ));
        // Send keys to the password field
        passwordInput.sendKeys(signin_password);

        //Click sign in button
        // Wait until the submit button is present
        WebElement signInButton = wait
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@type='submit' and contains(.,'Sign')]")
                ));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                signInButton
        );

        // Click the button
        signInButton.click();
    }
    public void clickPractiseSite()
    {
        WebElement practiceSiteLink = wait
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a.nav-link[href='/practice-site']")
                ));

        practiceSiteLink.click();

    }
    public void clickStartTestingNow()
    {
        WebElement startTestingNow = wait
                .until(ExpectedConditions.elementToBeClickable(
                        By.linkText("Start Testing Now")
                ));
        startTestingNow.click();

    }
    public void FeedbackField()
    {
        // Locate textarea using placeholder attribute
        WebElement commentBox = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Write Comment...']")));

        // Send input to textarea
        commentBox.sendKeys("This is my automated test comment.");
    }
    public void submitFeedback()
    {
        // Locate button by visible text
        WebElement submitBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[normalize-space()='Submit']")));

        // Click the button
        submitBtn.click();
    }
}
