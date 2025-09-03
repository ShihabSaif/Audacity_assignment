package org.example.browserOpen;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class BrowserOpening {
    public static WebDriver driver;
    public static BrowserOpening instance;

    private BrowserOpening() throws IOException {
        setUp();
    }
    public static BrowserOpening getInstance() throws Exception, ArrayIndexOutOfBoundsException, IndexOutOfBoundsException, IOException, InterruptedException {
        if (instance == null) {
            instance = new BrowserOpening();
        }
        return instance;
    }
    private void setUp() throws IOException {

        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        //windows
        System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
        driver.manage().window().maximize();
    }
}
