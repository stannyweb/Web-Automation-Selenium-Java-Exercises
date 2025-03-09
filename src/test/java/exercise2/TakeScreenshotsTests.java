package exercise2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TakeScreenshotsTests {
    private WebDriver driver;
    private final String SCREENSHOT_PATH = "src/test/screenshots/";


    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://www.automationpractice.pl/index.php");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void takeScreenShotElement() throws IOException {
//        Създаване на обект WebDriverWait за изчакване на елемента
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//        Намиране на елемента лого с помощта на CSS селектор и изчакване да стане видим
        WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#header_logo>a")));

//        Правене на снимка на елемента и записване като временен файл
        File source = logo.getScreenshotAs(OutputType.FILE);

//        Създаване на обект File за дестинацията на снимката
        File destination = new File(SCREENSHOT_PATH + "Logo element screenshot.png");

//        Копиране на временния файл в дестинацията
        FileHandler.copy(source, destination);
    }

    @Test
    public void takeWebElementScreenShot() throws IOException {
//        Проверка и създаване на директорията, ако не съществува
        final Path path = Paths.get(SCREENSHOT_PATH);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headerElements = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("slider_row")));

        File source = headerElements.getScreenshotAs(OutputType.FILE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "header_elements" + timestamp + ".png";

        File destination = new File(SCREENSHOT_PATH, fileName);

        try {
            FileHandler.copy(source, destination);
            System.out.println("Screenshot saved to: " + destination.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    @Test
    public void takeFullPageScreenshot() throws IOException {
        File source = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
        String fullPage = "fullPage_screenshot.png";
        FileHandler.copy(source, new File(SCREENSHOT_PATH + fullPage));
    }
}
