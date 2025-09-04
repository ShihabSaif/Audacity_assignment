package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;

import static org.example.browserOpen.BrowserOpening.driver;

public class FormSubmissionFeature extends BasePage {
    //Read data from property file
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");
    String form_name = prop.getProperty("form_name");
    String form_email = prop.getProperty("form_email");
    String form_contact = prop.getProperty("form_contact");
    String colorID = prop.getProperty("colorID");
    String foodID = prop.getProperty("foodID");
    String countryID = prop.getProperty("countryID");
    String filePath = prop.getProperty("filePath");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public FormSubmissionFeature() throws Exception {
        super();
    }
    // Open Form
    public void openForm() {
        driver.get(url);
    }
    //form submission click
    public void formSubmissionClick()
    {
        WebElement formSubmissionMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("form-submission"))
        );
        // Click the element
        formSubmissionMenu.click();
    }
    // Name field
    public void enterName() {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys(form_name);
    }

    // Email field
    public void enterEmail() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys(form_email);
    }

    // Contact field
    public void enterContact() {
        WebElement contactField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contact")));
        contactField.sendKeys(form_contact);
    }

    // File upload
    public void uploadFile() {
        WebElement fileUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file")));
        fileUpload.sendKeys(new File(filePath).getAbsolutePath());
    }

    // Select color (Radio button)
    public void selectColor() throws InterruptedException {
        Thread.sleep(2500);
        WebElement colorRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id(colorID)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", colorRadio);
        colorRadio.click();
    }

    // Select food (Checkbox)
    public void selectFood() throws InterruptedException {
        Thread.sleep(2500);
        WebElement foodCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id(foodID)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", foodCheckbox);
        if (!foodCheckbox.isSelected()) {
            foodCheckbox.click();
        }
    }

    // Select country (Dropdown)
    public void selectCountry() {
        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(countryID);
    }

    // Submit form
    public void submitForm() throws InterruptedException {
        Thread.sleep(2500);
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        submitBtn.click();
    }

}
