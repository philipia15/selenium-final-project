import ge.tbc.testAutomation.util.FetchPricesFromAPI;
import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.By.*;

public class HolidayPageTests extends BeforeAfterClass {
    @Test
    public void descendingOrderTest() {
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();

        //sort offers from most expensive to the least expensive
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'შესაბამისობით')]")));
        sortButton.click();

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'ფასით კლებადი')]")));
        sortByExpensiveOption.click();

        //Validate the most expensive offer is displayed first
        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));

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

        //sort offers from most expensive to the least expensive
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'შესაბამისობით')]")));
        sortButton.click();

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'ფასით ზრდადი')]")));
        sortByExpensiveOption.click();

        //Validate the most expensive offer is displayed first
        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@weight='bold' and contains(., '₾')]")));

        String firstOfferPriceText = firstOfferPriceElement.getText().replace("₾", "").replace(",", "").trim();
        double firstOfferPrice = Double.parseDouble(firstOfferPriceText);

        Double minPrice = FetchPricesFromAPI.FetchMinPrice();
        System.out.println("Maximum Price: " + minPrice);
        System.out.println("Maximum Price: " + firstOfferPrice);
        Assert.assertEquals(firstOfferPrice, minPrice);
    }
    @Test
    public void filterTest(){
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();

        //Choose 'მთის კურორტები' category on the left side.
        WebElement mountainResorts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'მთის კურორტები')]")));
        mountainResorts.click();

        //Choose 'სრული გადახდა' filter
        WebElement fullPayment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='radio-გადახდის ტიპი-1']")));
        fullPayment.click();

        // Validate its presence
        WebElement fullPaymentOnTop = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'სრული გადახდა')]")));
        Assert.assertTrue(fullPaymentOnTop.isDisplayed(), "'სრული გადახდა' tag is not displayed above the list of offers");

        //sort offers from most expensive to the least expensive
        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("//p[contains(text(),'შესაბამისობით')]")));
        sortButton.click();

        WebElement sortByExpensiveOption = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//p[contains(text(),'ფასით კლებადი')]")));
        sortByExpensiveOption.click();

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
        //Go to the 'დასვენება' section
        WebElement holidaySection = wait.until(ExpectedConditions.elementToBeClickable(linkText("დასვენება")));
        holidaySection.click();

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