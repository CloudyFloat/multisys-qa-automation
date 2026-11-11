package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class DynamicContentPage {
    WebDriver driver;

    // Locator for the text sections that change dynamically on refresh
    By contentTextBlocks = By.cssSelector("#content .row div.large-10");

    public DynamicContentPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getContentTextList() {
        List<WebElement> elements = driver.findElements(contentTextBlocks);
        List<String> texts = new ArrayList<>();
        for (WebElement el : elements) {
            texts.add(el.getText());
        }
        return texts;
    }
}