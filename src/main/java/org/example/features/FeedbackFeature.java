package org.example.features;

import org.example.browserOpen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class FeedbackFeature extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public FeedbackFeature() throws Exception, IOException, InterruptedException {
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
