package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class EcommerceSite extends BasePage {
    //Read data from property file
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");
    String ecommerce_email = prop.getProperty("ecommerce_email");
    String ecommerce_password = prop.getProperty("ecommerce_password");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public EcommerceSite() throws Exception {
        super();
        driver.navigate().to(url);
    }

    public void clickEcommerce()
    {
        // Locate element by its text
        WebElement ecommerceSite = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='E-Commerce Site']"))
        );
        // Click the element
        ecommerceSite.click();
    }
    public void clickVisitDemoSite()
    {
        // Locate the link by its visible text
        WebElement visitDemoSite = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Visit Demo Site"))
        );
        // Click the link
        visitDemoSite.click();
    }
    public void inputEmail()
    {
        // Locate input by id
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email"))
        );
        // Clear and type email
        emailField.clear();
        emailField.sendKeys(ecommerce_email);
    }
    public void inputPassword()
    {
        // Locate password input by id
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        // Clear and type password
        passwordField.clear();
        passwordField.sendKeys(ecommerce_password);
    }
    public void loginButton() throws InterruptedException {
        // Locate button by its type and text
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@type='submit' and contains(.,'Login')]")
                )
        );

        // Scroll into view before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", loginButton);

        // Small pause to let any animation/overlay settle
        Thread.sleep(500);

        // Click using JavaScript (more reliable if overlays exist)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }
    public void addToCart()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> addToCartButtons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//button[@type='button' and normalize-space()='Add to cart']")
                )
        );
        // Scroll to the first button and click
        WebElement firstButton = addToCartButtons.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", firstButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstButton);
    }
    public void clickCart() throws InterruptedException {
        // 1. Locate the span wrapping the SVG
        WebElement cartSpan = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='profile flex items-center gap-4']//span[@role='button'][.//svg]")
        ));

// 2. Scroll the element into view (center of screen)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                cartSpan
        );

// 3. Small wait for animations/overlays
        Thread.sleep(500);

// 4. Click using JavaScript (bypasses interception issues)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartSpan);

    }
}
