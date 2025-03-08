package exercise1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRelativeLocator() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginPanel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.orangehrm" +
                "-demo-credentials")));

        WebElement usernameElement = driver.findElement(with(By.tagName("p")).near(loginPanel));
        WebElement passwordElement = driver.findElement(with(By.tagName("p")).near(usernameElement));

        System.out.println(usernameElement.getText());
        System.out.println(passwordElement.getText());

    }

    @Test
    public void testGetSocialMedia() {

        List<WebElement> allSocialMedia = driver.findElements(with(By.xpath("//а"))
                .near(By.cssSelector("div.orangehrm-login-footer-sm")));

        for (WebElement socialMedia : allSocialMedia) {
            String socialMediaText = socialMedia.getDomAttribute("href");
            System.out.println(socialMediaText);
        }
    }
}
