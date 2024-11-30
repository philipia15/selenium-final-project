import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.By.linkText;

public class MoviePageTests extends BeforeAfterClass {
    @BeforeTest
    @Test
    public void beforeTest(){
        super.beforeTest();
    }
    @Test
    public void MoviePage(){
        //Go to the 'კინო' section
        WebElement filmSection = wait.until(ExpectedConditions.elementToBeClickable(linkText("კინო")));
        filmSection.click();

        WebElement firstFilm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'w-full group')]")));
        firstFilm.click();

        WebElement caveCinema = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'კავეა ისთ ფოინთი')]"))) ;

        Actions actions = new Actions(driver);
        actions.moveToElement(caveCinema).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(caveCinema));

    }


}
