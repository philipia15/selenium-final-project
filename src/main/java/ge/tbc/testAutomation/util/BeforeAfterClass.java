package ge.tbc.testAutomation.util;

import ge.tbc.testAutomation.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BeforeAfterClass {
    public WebDriver driver;
    public WebDriverWait wait;
    @Parameters({"browserType"})
    @BeforeClass
    public void beforeTest(@Optional("chrome") String browserType){
        if (browserType.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.driver.get(Constants.MAIN_URL);
        }
        else if(browserType.equals("edge")){
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
            this.driver.manage().window().maximize();
            this.driver.get(Constants.MAIN_URL);
        }else {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
            this.driver.manage().window().maximize();
            this.driver.get(Constants.MAIN_URL);
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        WebElement cookie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'ვეთანხმები')]")));
        cookie.click();
    }
    @AfterClass
    public void afterTest(){
        driver.quit();
    }
}
