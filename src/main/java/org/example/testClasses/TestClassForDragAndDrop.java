package org.example.testClasses;

import org.example.features.DragAndDropFeature;
import org.testng.annotations.Test;

public class TestClassForDragAndDrop {
    DragAndDropFeature dragAndDrop = new DragAndDropFeature();

    public TestClassForDragAndDrop() throws Exception {
    }
    @Test(priority = 0)
    public void TestDragAndDropClick()
    {
        dragAndDrop.clickDragAndDrop();
    }
    @Test(priority = 1)
    public void TestDragAndDropBlock()
    {
        dragAndDrop.dragAndDropBlock();
    }
}
