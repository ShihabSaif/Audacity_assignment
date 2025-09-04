package org.example.testClasses;

import org.example.features.FormFieldCheckFeature;
import org.example.utility.UTIL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.example.browserOpen.BrowserOpening.driver;

public class TestClassForFormFieldCheck {    //Read data from property file
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");
    WebDriverWait wait;

    FormFieldCheckFeature formFeature;

    public TestClassForFormFieldCheck() throws IOException {
    }

    @BeforeTest
    public void init() throws Exception {
        formFeature = new FormFieldCheckFeature();
        driver.manage().window().maximize();
        driver.get(url); // replace with actual URL
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 0)
    public void testFormSubmissionClick()
    {
        formFeature.formSubmissionClick();
    }

    @Test(priority = 1)
    public void testNameField() {
        WebElement nameField = formFeature.getNameField();
        Assert.assertTrue(nameField.isDisplayed() && nameField.isEnabled(), "Name field not accessible!");
    }

    @Test(priority = 2)
    public void testEmailField() {
        WebElement emailField = formFeature.getEmailField();
        Assert.assertTrue(emailField.isDisplayed() && emailField.isEnabled(), "Email field not accessible!");
    }

    @Test(priority = 3)
    public void testContactField() {
        WebElement contactField = formFeature.getContactField();
        Assert.assertTrue(contactField.isDisplayed() && contactField.isEnabled(), "Contact field not accessible!");
    }

    @Test(priority = 4)
    public void testDateField() {
        WebElement dateField = formFeature.getDateField();
        Assert.assertTrue(dateField.isDisplayed() && dateField.isEnabled(), "Date field not accessible!");
    }

    @Test(priority = 5)
    public void testFileUploadField() {
        WebElement fileField = formFeature.getFileUploadField();
        Assert.assertTrue(fileField.isDisplayed() && fileField.isEnabled(), "File upload field not accessible!");
    }

    @Test(priority = 6)
    public void testColorRadioButtons() {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        for (String color : colors) {
            WebElement colorOption = formFeature.getColorOption(color);
            Assert.assertTrue(colorOption.isDisplayed() && colorOption.isEnabled(), color + " radio button not accessible!");
        }
    }

    @Test(priority = 7)
    public void testFoodCheckboxes() {
        String[] foods = {"Pasta", "Pizza", "Burger", "Sandwich"};
        for (String food : foods) {
            WebElement foodOption = formFeature.getFoodOption(food);
            Assert.assertTrue(foodOption.isDisplayed() && foodOption.isEnabled(), food + " checkbox not accessible!");
        }
    }

    @Test(priority = 8)
    public void testCountryDropdown() {
        WebElement countrySelect = formFeature.getCountryDropdown();
        Assert.assertTrue(countrySelect.isDisplayed() && countrySelect.isEnabled(), "Country dropdown not accessible!");
    }

    @Test(priority = 9)
    public void testSubmitButton() {
        WebElement submitBtn = formFeature.getSubmitButton();
        Assert.assertTrue(submitBtn.isDisplayed() && submitBtn.isEnabled(), "Submit button not accessible!");
    }
}
