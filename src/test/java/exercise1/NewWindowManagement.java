package exercise1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class NewWindowManagement {

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
    public void testNewWindowTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String newTabUrl = "http://www.automationpractice.pl/index.php?controller=authentication&back=my-account";
        driver.switchTo().newWindow(WindowType.TAB).get(newTabUrl);

        System.out.println("Title " + driver.getTitle());

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
        emailInput.sendKeys("stannyweb@gmail.com");

        WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate")));
        createAccountButton.click();


        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> windowIterator = allWindowTabs.iterator();
        String mainFirstTab = windowIterator.next();

        driver.switchTo().window(mainFirstTab);

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        searchInput.sendKeys("Shirts");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit_search")));
        searchButton.click();

        System.out.println(driver.getTitle());
    }
}
