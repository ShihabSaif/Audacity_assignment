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
    public EcommerceSite() throws Exception
    {
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
    public void loginButton() throws InterruptedException
    {
        // Locate button by its type and text
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@type='submit' and contains(.,'Login')]")
                )
        );

        // Scroll into view before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", loginButton);

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
    public void clickCart() throws InterruptedException
    {
        // 1. Wait for all cart spans to be present
        List<WebElement> cartSpans = wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("span[role='button'] > svg")
                ));

        // 2. Pick the 1st one (or adjust index if multiple carts exist)
        WebElement firstCartSvg = cartSpans.get(0);

        // 3. Scroll the parent span into view
        WebElement firstCartSpan = firstCartSvg.findElement(By.xpath("./..")); // parent span
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                firstCartSpan
        );

        // 4. Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstCartSpan);

    }
    public void clickCheckout() throws InterruptedException
    {
        // 1. Wait for the Checkout button to be present
        WebElement checkoutButton = wait
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(., 'Checkout')]")
                ));

// 2. Scroll it into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                checkoutButton
        );

        // 3. Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);

    }
    public void fillCheckoutPageAndClickContinue() throws InterruptedException
    {
        WebElement nameInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Ex. John']")));
        nameInput.sendKeys("John");
        driver.findElement(By.cssSelector("input[placeholder='Ex. Doe']")).sendKeys("Doe");

        //Locating Continue button
        WebElement continueButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.//span[text()='Continue']]")));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                continueButton
        );

        // Click using JS to bypass overlays
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);

        //Locating Finish button
        // 1. Locate the Finish button by its span text
        WebElement finishButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[.//span[text()='Finish']]")
                ));

        // 2. Scroll it into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                finishButton
        );

        // 3. Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finishButton);
    }
    public void clickContinueSHopping()
    {
        // 1. Locate the Continue Shopping button by its span text
        WebElement continueShoppingBtn = wait
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[.//span[text()='Continue Shopping']]")
                ));

        // 2. Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                continueShoppingBtn
        );

        // 3. Click with JS (avoids interception issues)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueShoppingBtn);

    }
    public void clickFavorite() throws InterruptedException
    {
        clickContinueSHopping();
        // 1. Wait for all heart buttons to be present
        List<WebElement> heartButtons = wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("span[role='button'] > button > svg")
                ));

        // 2. Pick the 1st one (index 0)
        WebElement fourthHeartSvg = heartButtons.get(0);

        // 3. Scroll the parent button into view
        WebElement fourthHeartButton = fourthHeartSvg.findElement(By.xpath("./..")); // parent button
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                fourthHeartButton
        );

        // 4. Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fourthHeartButton);

    }
}
