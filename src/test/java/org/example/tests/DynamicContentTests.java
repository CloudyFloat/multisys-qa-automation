package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.DynamicContentPage;

import java.util.List;

public class DynamicContentTests {
    WebDriver driver;
    DynamicContentPage dynamicContentPage;

    @BeforeMethod
    public void setUp() {
        // Forces WebDriverManager to clear cache and download the matching binary fresh
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_content");
        dynamicContentPage = new DynamicContentPage(driver);
    }

    @Test
    public void testVerifyContentChange() {
        // Capture initial content before refresh
        List<String> initialContent = dynamicContentPage.getContentTextList();
        System.out.println("Initial Content: " + initialContent);

        // Refresh the page
        driver.navigate().refresh();

        // Capture content after refresh
        List<String> refreshedContent = dynamicContentPage.getContentTextList();
        System.out.println("Refreshed Content: " + refreshedContent);

        // Assert that at least some content changed upon refreshing
        boolean hasChanged = !initialContent.equals(refreshedContent);
        Assert.assertTrue(hasChanged, "The page content did not change after refreshing!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}