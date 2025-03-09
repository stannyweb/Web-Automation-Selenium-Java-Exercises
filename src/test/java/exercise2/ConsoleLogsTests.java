package exercise2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.log.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConsoleLogsTests {

    ChromeDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void viewBrowserConsoleLogs() {
//        Get the dev tools & create a session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

//        Enable console logs
        devTools.send(Log.enable());

//        Add a listener to the console log
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("----------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("Broken URL: " + logEntry.getUrl());
        });

//        Load the URL
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }
}
