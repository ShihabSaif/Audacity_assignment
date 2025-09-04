package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.example.browserOpen.BrowserOpening.driver;

public class FormSubmissionFeature extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public FormSubmissionFeature() throws Exception {
        super();
        PageFactory.initElements(driver, this);
    }

    public void formSubmissionClick()
    {
        WebElement formSubmissionMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("form-submission"))
        );

        // Click the element
        formSubmissionMenu.click();
    }

    // 🔹 Element getters
    public WebElement getNameField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
    }

    public WebElement getEmailField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }

    public WebElement getContactField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contact")));
    }

    public WebElement getDateField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("date")));
    }

    public WebElement getFileUploadField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("file")));
    }

    public WebElement getColorOption(String color) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(color)));
    }

    public WebElement getFoodOption(String food) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(food)));
    }

    public WebElement getCountryDropdown() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
    }

    public WebElement getSubmitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-submit")));
    }
}

