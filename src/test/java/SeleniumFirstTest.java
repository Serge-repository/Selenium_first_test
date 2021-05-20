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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class SeleniumFirstTest {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setupBrowser(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@AfterClass
	public void closeBworser(){
		driver.quit();
	}

	@BeforeMethod
	public void navigateAction(){
		driver.get("https://google.com/ncr");
	}

	@Test
	public void testSearchByEnterClick() {
		driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
		WebElement stats = wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));

		assertTrue(stats.getText().contains("results"));
	}

	@Test
	public void testSearchBySearchButtonClick() {
		driver.findElement(By.name("q")).sendKeys("cheese");
		driver.findElements(By.xpath("//input[@name='btnK']")).get(1).click();
		WebElement stats = wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));

		assertTrue(stats.getText().contains("results"));
	}
}
