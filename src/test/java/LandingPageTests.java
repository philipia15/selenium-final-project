import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

import static org.openqa.selenium.By.linkText;

public class LandingPageTests extends BeforeAfterClass {
    @BeforeTest
    @Test
    public void beforeTest() {
        super.beforeTest();
    }
    @Test
    public void activeCategoryTest(){
        WebElement categoryBTN = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/p[contains(text(),'კატეგორიები')]")));
        categoryBTN.click();

        WebElement sportElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'სპორტი')]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(sportElement).perform();

        WebElement carting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[contains(text(),'კარტინგი') and @weight='regular']")));
        carting.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://swoop.ge/category/2058/sporti/kartingi/"));

        String actualCurrentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://swoop.ge/category/2058/sporti/kartingi/";
        Assert.assertEquals(actualCurrentUrl, expectedUrl);

        System.out.println("Navigation to the expected URL is successful.");

        WebElement categoryChain = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[contains(@class, 'flex-nowrap')]")));

        String navText = categoryChain.getText();
        // Validate if 'კარტინგი' exists within the nav element text
        assert navText.contains("კარტინგი") : "'კარტინგი' not found in the nav element. Found: " + navText;

        System.out.println("Validation passed: 'კარტინგი' is present in the nav element.");
    }

    @Test
    public void logoTest(){
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();

        WebElement swoopImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='h-6 w-auto']")));
        swoopImage.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://swoop.ge/"));

        String expectedUrl = "https://swoop.ge/";
        String actualCurrentUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualCurrentUrl, expectedUrl);
        System.out.println("Navigation to the expected URL is successful.");
    }
    @AfterTest
    public void afterTest(){
        super.afterTest();
    }
}
