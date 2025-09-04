package org.example.testClasses;

import org.example.features.EcommerceSite;
import org.testng.annotations.Test;

public class TestClassForEcommerce {
    EcommerceSite ecommerce = new EcommerceSite();

    public TestClassForEcommerce() throws Exception {
    }
    @Test(priority = 0)
    public void testClickEcommerce()
    {
        ecommerce.clickEcommerce();
    }
    @Test(priority = 1)
    public void testclickVisitDemoSite()
    {
        ecommerce.clickVisitDemoSite();
    }
    @Test(priority = 2, dependsOnMethods = "testclickVisitDemoSite")
    public void testinputEmail()
    {
        ecommerce.inputEmail();
    }
    @Test(priority = 3, dependsOnMethods = "testclickVisitDemoSite")
    public void testinputPassword()
    {
        ecommerce.inputPassword();
    }
    @Test(priority = 4, dependsOnMethods = "testclickVisitDemoSite")
    public void testloginButton() throws InterruptedException {
        ecommerce.loginButton();
    }
    @Test(priority = 5, dependsOnMethods = "testclickVisitDemoSite")
    public void testaddToCart() throws InterruptedException {
        ecommerce.addToCart();
    }
    @Test(priority = 6, dependsOnMethods = "testclickVisitDemoSite")
    public void testclickCart() throws InterruptedException {
        ecommerce.clickCart();
    }
}
