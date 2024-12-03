import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


import static org.openqa.selenium.By.linkText;

public class LandingPageTests extends BeforeAfterClass {
    @Test
    public void activeCategoryTest(){
        //Click on  'კატეგორიები'
        WebElement categoryBTN = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/p[contains(text(),'კატეგორიები')]")));
        categoryBTN.click();
        //Choose 'სპორტი'
        WebElement sportElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'სპორტი')]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(sportElement).perform();
        //Choose 'კარტინგი'
        WebElement carting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[contains(text(),'კარტინგი') and @weight='regular']")));
        carting.click();
        //Validate that the URL is https://swoop.ge/category/2058/sporti/kartingi/
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();
        //Click on Swoop logo
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        WebElement swoopImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='h-6 w-auto']")));
        js.executeScript("arguments[0].click();", swoopImage);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://swoop.ge/"));

        String expectedUrl = "https://swoop.ge/";
        String actualCurrentUrl = driver.getCurrentUrl();
        //Validate that the logo takes the user back to landingPage
        Assert.assertEquals(actualCurrentUrl, expectedUrl);
        System.out.println("Navigation to the expected URL is successful.");
    }
}
