package org.example.testClasses;

import org.example.features.LoginFeature;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClassForLogin {
    LoginFeature loginFeature;

    @BeforeTest
    public void init() throws Exception {
        loginFeature = new LoginFeature();
    }
    @Test
    public void testBrowserLaunch() throws InterruptedException {
        loginFeature.LaunchBrowser();
    }
    @Test(priority = 1, dependsOnMethods = "testBrowserLaunch")
    public void testLogin() {
        loginFeature.login();
    }
    @Test(dependsOnMethods = "testLogin")
    public void testVerifyLogin() throws InterruptedException {
        loginFeature.verifyLogin();
    }
    @Test(priority = 2, dependsOnMethods = "testBrowserLaunch")
    public void testVerifyInvalidLogin() throws InterruptedException {
        loginFeature.invalidLogin();
    }
}
