import ge.tbc.testAutomation.data.Constants;
import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class HolidayPageTests extends BeforeAfterClass {
    @BeforeClass
    public void beforeTest(){
        super.beforeTest();
    }
    //1.1 descendingOrderTest
    @Test
    public void descendingOrderTest(){


        driver.get(Constants.MAIN_URL);
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("დასვენება")));
        holidaySection.click();



        //sort offers from most expensive to the least expensive
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'შესაბამისობით')]")));
        sortButton.click();

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'ფასით კლებადი')]")));
        sortByExpensiveOption.click();


        // Find the most expensive offer
        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));
        double maxPrice = 0;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("₾", "").replace(",", "").trim();
            double price = Double.parseDouble(priceText);
            if (maxPrice < price) {
                maxPrice = price;
            }

        }

        //Validate the most expensive offer is displayed first
        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));
        String firstOfferPriceText = firstOfferPriceElement.getText().replace("₾", "").replace(",", "").trim();
        double firstOfferPrice = Double.parseDouble(firstOfferPriceText);

        Assert.assertEquals("The most expensive offer is not displayed first!", maxPrice, firstOfferPrice, 0.01);
    }
    @AfterTest
    public void afterTest(){
        super.afterTest();
    }
}
