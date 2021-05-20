package ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class H21Task1 {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String correctLogin = "1303";
    String correctPassword = "Guru99";

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
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
    }

    @Test
    public void login() {
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(correctPassword);
        driver.findElement(By.name("btnLogin")).click();
        element = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertTrue(element.getText().contains("Log out"));
    }

    @Test
    public void login2() {
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(correctPassword);
        driver.findElement(By.name("btnLogin")).click();
        element = wait.until(presenceOfElementLocated(By.linkText("Log out")));
        assertTrue(element.getText().contains("Log out"));
    }

    @Test
    public void negativeIncorrectPassword() {
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void negativeIncorrectLogin() {
        driver.findElement(By.name("uid")).sendKeys("incorrect");
        driver.findElement(By.name("password")).sendKeys(correctPassword);
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void negativeLoginWithoutCreds() {
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void negativeNoLogin() {
        driver.findElement(By.name("uid")).click();
        driver.findElement(By.id("name-and-slogan")).click();
        element = wait.until(presenceOfElementLocated(By.id("message23")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void negativeNoPassword() {
        driver.findElement(By.name("password")).click();
        driver.findElement(By.id("name-and-slogan")).click();
        element = wait.until(presenceOfElementLocated(By.id("message18")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void negativeReset() {
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(correctPassword);
        driver.findElement(By.name("btnReset")).click();
        element = driver.findElement(By.name("uid"));
        String textInsideLogin = element.getAttribute("");
        String textInsidePassword = element.getAttribute("");
        assertNull(textInsideLogin);
        assertNull(textInsidePassword);
    }
}