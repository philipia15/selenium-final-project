import ge.tbc.testAutomation.util.FetchPricesFromAPI;
import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.By.*;

public class HolidayPageTests extends BeforeAfterClass {
    @Test
    public void descendingOrderTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();
        //sort offers from most expensive to the least expensive
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        WebElement sortButton = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//p[contains(text(),'შესაბამისობით')]")));
        js.executeScript("arguments[0].click();", sortButton);

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'ფასით კლებადი')]")));
        js.executeScript("arguments[0].click();", sortByExpensiveOption);

        //Validate the most expensive offer is displayed first

        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));

        String firstOfferPriceText = firstOfferPriceElement.getText().replace("₾", "").replace(",", "").trim();
        double firstOfferPrice = Double.parseDouble(firstOfferPriceText);

        Double maxPrice = FetchPricesFromAPI.FetchMaxPrice();
        System.out.println("Maximum Price: " + maxPrice);
        System.out.println("Maximum Price: " + firstOfferPrice);
        Assert.assertEquals(firstOfferPrice, maxPrice);
    }
    @Test
    public void ascendingOrderTest(){
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();

        //Find the least expensive offer among ALL offers
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'შესაბამისობით')]")));
        sortButton.click();

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'ფასით ზრდადი')]")));
        sortByExpensiveOption.click();
        //Validate the most expensive offer is displayed first
        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));

        String firstOfferPriceText = firstOfferPriceElement.getText().replace("₾", "").replace(",", "").trim();
        double firstOfferPrice = Double.parseDouble(firstOfferPriceText);

        Double minPrice = FetchPricesFromAPI.FetchMinPrice();
        System.out.println("Minimum Price: " + minPrice);
        System.out.println("Minimum Price: " + firstOfferPrice);
        Assert.assertEquals(firstOfferPrice, minPrice);
    }
    @Test
    public void filterTest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();
        //Choose 'მთის კურორტები' category on the left side.
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        WebElement mountainResorts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'მთის კურორტები')]")));
        js.executeScript("arguments[0].click();", mountainResorts);

        //Choose 'სრული გადახდა' filter
        String currentUrl1 = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl1)));
        WebElement fullPayment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'სრული გადახდა')]")));
        js.executeScript("arguments[0].click();", fullPayment);
        // Validate its presence
        WebElement fullPaymentOnTop = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'სრული გადახდა')]")));
        Assert.assertTrue(fullPaymentOnTop.isDisplayed(), "'სრული გადახდა' tag is not displayed above the list of offers");

        //sort offers from most expensive to the least expensive
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'შესაბამისობით')]")));
        js.executeScript("arguments[0].click();", sortButton);

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//p[contains(text(),'ფასით კლებადი')]")));
        js.executeScript("arguments[0].click();", sortByExpensiveOption);
        //Validate the most expensive offer is displayed first
        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));

        String firstOfferPriceText = firstOfferPriceElement.getText().replace("₾", "").replace(",", "").trim();
        double firstOfferPrice = Double.parseDouble(firstOfferPriceText);

        Double maxPrice = FetchPricesFromAPI.FetchMaxPriceMountain();
        System.out.println("Maximum Price: " + maxPrice);
        System.out.println("Maximum Price: " + firstOfferPrice);
        Assert.assertEquals(firstOfferPrice, maxPrice);
    }
    @Test
    public void priceRangeTest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        js.executeScript("arguments[0].click();", holidaySection);
        //Specify price range of your choice on the left side
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        WebElement range = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'1000₾ - დან')]")));
        range.click();

        String priceRange = range.getText().replaceAll("[^0-9]", "");
        double priceRangeToDouble = Double.parseDouble(priceRange);

        // Locate all the elements containing price information
        List<WebElement> priceElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));

        Double[] prices = FetchPricesFromAPI.extractPricesFromElements(priceElement);
        System.out.println("Price List: " + Arrays.toString(prices));
        for (Double price : prices) {
            if (price > priceRangeToDouble) {
                System.out.println("Price " + price + " is greater than 1000.");
                assert price > 1000 : "Expected price greater than 1000, but got " + price;
            } else {
                System.out.println("Price " + price + " is not greater than 1000.");
                assert price <= 1000 : "Expected price less than or equal to 1000, but got " + price;
            }
        }
    }
}