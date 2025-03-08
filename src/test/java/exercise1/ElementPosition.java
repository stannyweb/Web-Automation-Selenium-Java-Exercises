package exercise1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ElementPosition {

    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://www.automationpractice.pl/index.php");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void getPositionDimension() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("logo")));

        Rectangle logoRect = logo.getRect();

        System.out.println("x: " + logoRect.getX());
        System.out.println("y: " + logoRect.getY());
        System.out.println("width: " + logoRect.getWidth());
        System.out.println("height: " + logoRect.getHeight());

    }
}
