package org.example.testClasses;

import org.example.features.FormSubmissionFeature;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestClassForFormSubmission {
    FormSubmissionFeature formSubmission = new FormSubmissionFeature();

    public TestClassForFormSubmission() throws Exception {
    }

    @Test(priority = 0)
    public void testOpenForm()
    {
        formSubmission.openForm();
    }
    @Test(priority = 1)
    public void testFormSubmissionClick()
    {
        formSubmission.formSubmissionClick();
    }
    @Test(priority = 2)
    public void testEnterName()
    {
        formSubmission.enterName();
    }
    @Test(priority = 3)
    public void testEnteremail()
    {
        formSubmission.enterEmail();
    }
    @Test(priority = 4)
    public void testEnterContact()
    {
        formSubmission.enterContact();
    }
    @Test(priority = 5)
    public void testuploadFile()
    {
        formSubmission.uploadFile();
    }
    @Test(priority = 6)
    public void testselectColor() throws InterruptedException {
        formSubmission.selectColor();
    }
    @Test(priority = 7)
    public void testselectFood() throws InterruptedException {
        formSubmission.selectFood();
    }
    @Test(priority = 8)
    public void testselectCountry()
    {
        formSubmission.selectCountry();
    }
    @Test(priority = 9)
    public void testsubmitForm() throws InterruptedException {
        formSubmission.submitForm();
    }
}
