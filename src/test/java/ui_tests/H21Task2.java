package ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;

public class H21Task2 {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;

    @BeforeClass
    public void actionsBeforeTestClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void actionsAfterTestClass() {
        driver.quit();
    }

    @BeforeMethod
    public void actionsBeforeTestMethod() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy" + Keys.ENTER);
    }

    @Test
    public void test() {
        int[] numberOfPages = {1, 2, 3, 4, 5};

        for (int x = 0; x < numberOfPages.length; x++) {
            List<String> myList = new ArrayList<>();
            List<WebElement> elementsList = driver.findElements(By.tagName("cite"));
            Iterator<WebElement> iterator = elementsList.iterator();
            for (WebElement webElement : elementsList) {
                if (webElement.getText().equals("")) {
                    iterator.next();
                } else {
                    myList.add(webElement.getText());
                }
            }

            for (String input : myList) {
                if (input.contains("stylus.ua")) {
                    System.out.println("STYLUS.UA found on " + numberOfPages[x] + " page");
                    return;
                }
            }

            if (numberOfPages[x] < 5) {
                driver.findElement(By.id("pnnext")).click();
            } else {
                System.out.println("STYLUS.UA not found on first 5 pages");
            }

        }
    }
}
