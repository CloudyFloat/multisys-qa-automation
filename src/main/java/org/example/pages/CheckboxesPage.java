package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckboxesPage {
    WebDriver driver;

    // XPath locators for the checkboxes on the page
    By checkboxes = By.xpath("//form[@id='checkboxes']/input");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCheckboxes() {
        return driver.findElements(checkboxes);
    }

    public void clickCheckbox(int index) {
        getCheckboxes().get(index).click();
    }

    public boolean isCheckboxSelected(int index) {
        return getCheckboxes().get(index).isSelected();
    }
}