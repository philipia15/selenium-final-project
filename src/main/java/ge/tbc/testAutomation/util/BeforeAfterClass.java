package ge.tbc.testAutomation.util;

import ge.tbc.testAutomation.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BeforeAfterClass {
    public WebDriver driver;
    public WebDriverWait wait;

    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get(Constants.MAIN_URL);

    }
    public void afterTest(){
        driver.quit();
    }
}
