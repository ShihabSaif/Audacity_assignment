package org.example.features;

import org.example.browserOpen.BasePage;
import org.example.utility.UTIL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DragAndDropFeature extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //Read data from property file
    UTIL util=new UTIL();
    Properties prop = util.readPropData();
    String url = prop.getProperty("url");

    public DragAndDropFeature() throws Exception, IOException, InterruptedException {
        driver.get(url);
    }

    public void clickDragAndDrop()
    {
        // Locate the element using XPath (span text) and wait until clickable
        WebElement dragDropList = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Drag and Drop List']"))
        );
        // Click the element
        dragDropList.click();
    }

    public void dragAndDropBlock() {
        // Locate "Drag Me" block
        WebElement dragMe = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[draggable='true']")));

        // Locate "Drop Here" block
        WebElement dropHere = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Drop Here')]")));

        // Scroll both elements into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dragMe);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dropHere);

        // JavaScript with coordinates for proper HTML5 drag-and-drop
        String jsDragAndDrop = """
    function triggerDragAndDrop(source, target) {
        const rectSource = source.getBoundingClientRect();
        const rectTarget = target.getBoundingClientRect();

        const dataTransfer = new DataTransfer();

        function fireEvent(element, type, clientX, clientY) {
            const event = new DragEvent(type, {
                bubbles: true,
                cancelable: true,
                dataTransfer: dataTransfer,
                clientX: clientX,
                clientY: clientY
            });
            element.dispatchEvent(event);
        }

        // Fire dragstart on source
        fireEvent(source, "dragstart", rectSource.x + rectSource.width/2, rectSource.y + rectSource.height/2);

        // Fire dragenter + dragover on target
        fireEvent(target, "dragenter", rectTarget.x + rectTarget.width/2, rectTarget.y + rectTarget.height/2);
        fireEvent(target, "dragover", rectTarget.x + rectTarget.width/2, rectTarget.y + rectTarget.height/2);

        // Fire drop on target
        fireEvent(target, "drop", rectTarget.x + rectTarget.width/2, rectTarget.y + rectTarget.height/2);

        // Fire dragend on source
        fireEvent(source, "dragend", rectSource.x + rectSource.width/2, rectSource.y + rectSource.height/2);
    }
    triggerDragAndDrop(arguments[0], arguments[1]);
    """;

        ((JavascriptExecutor) driver).executeScript(jsDragAndDrop, dragMe, dropHere);

    }
}
