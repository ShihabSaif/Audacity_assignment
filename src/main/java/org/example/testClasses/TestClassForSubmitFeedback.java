package org.example.testClasses;

import org.example.features.FeedbackFeature;
import org.example.utility.UTIL;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.example.browserOpen.BrowserOpening.driver;

public class TestClassForSubmitFeedback {
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");
    WebDriverWait wait;

    FeedbackFeature feedback;

    public TestClassForSubmitFeedback() throws IOException {
    }
    @BeforeTest
    public void init() throws Exception {
        feedback = new FeedbackFeature();
        driver.manage().window().maximize();
        driver.get(url); // replace with actual URL
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority = 0)
    public void testFeedbackField()
    {
        feedback.FeedbackField();
    }

    @Test(priority = 1)
    public void testFeedbackSubmit()
    {
        feedback.submitFeedback();
    }

}
