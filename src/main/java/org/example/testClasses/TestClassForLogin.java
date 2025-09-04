package org.example.testClasses;

import org.example.features.LoginFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClassForLogin {
    private static final Logger log = LoggerFactory.getLogger(TestClassForLogin.class);
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
    @Test(priority = 1, dependsOnMethods = "testBrowserLaunch")
    public void testPasswordFieldSecurity() {
        loginFeature.passwordFieldSecurity();
    }

    @Test(dependsOnMethods = "testLogin")
    public void testVerifyLogin() throws InterruptedException {
        loginFeature.verifyLogin();
    }
    @Test(priority = 2, dependsOnMethods = "testBrowserLaunch")
    public void testVerifyInvalidLogin() throws InterruptedException {
        loginFeature.invalidLogin();
    }
    @Test(priority = 3)
    public void testRegistration()
    {
        loginFeature.registration();
    }
    @Test(priority = 4)
    public void testForgotPassword()
    {
        loginFeature.forgotPassword();
    }
}
