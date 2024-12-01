import ge.tbc.testAutomation.util.FetchPricesFromAPI;
import ge.tbc.testAutomation.util.BeforeAfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.linkText;

public class MoviePageTests extends BeforeAfterClass {
    @Test
    public void MoviePage(){

        Actions actions = new Actions(driver);

        //Go to the 'კინო' section
        WebElement filmSection = wait.until(ExpectedConditions.elementToBeClickable(linkText("კინო")));
        filmSection.click();

        WebElement firstFilm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'w-full group')]")));
        firstFilm.click();

        WebElement caveaCinema = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'კავეა ისთ ფოინთი')]")));
        String cinemaNameStr = caveaCinema.getText();
        actions.moveToElement(caveaCinema).perform();
        wait.until(ExpectedConditions.visibilityOf(caveaCinema));


        WebElement caveCinemaLastOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'კავეა ისთ ფოინთი')]/parent::*/following-sibling::*/descendant::div[contains(@class, 'cursor-pointer')][last()]")));
        actions.moveToElement(caveCinemaLastOption).perform();
        caveCinemaLastOption.click();


        //movieName in PopUp Window
        WebElement filmNamePopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex  justify-start items-start flex-col gap-2']/h2")));
        String filmNamePopUpStr = filmNamePopUp.getText();
        System.out.println(filmNamePopUpStr);

        //Date in Popup Window
        WebElement dateTimePopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex  justify-start items-start flex-col gap-2']//p[@class='text-2sm leading-4 font-tbcx-regular text-primary_black-90-value'][last()-1]")));
        String dateTimePopUpStr = dateTimePopUp.getText();
        System.out.println(dateTimePopUpStr);


        String returnCinemaName = FetchPricesFromAPI.FetchCinemaName();
        Assert.assertEquals(returnCinemaName, cinemaNameStr);

        String returnMovieName = FetchPricesFromAPI.FetchMovieName();
        Assert.assertEquals(filmNamePopUpStr, returnMovieName);

        String returnDate = FetchPricesFromAPI.FetchDate();
        Assert.assertEquals(dateTimePopUpStr, returnDate);

        WebElement freeSeats = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='relative w-fit h-fit mx-auto mt-5 desktop:mt-15']/div[contains(@class, 'cursor-pointer')]")));
        WebElement FreeSeatlegendChart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='w-2.5 h-2.5 rounded-full bg-primary_green-100-value']")));

        String firstColor = freeSeats.getCssValue("background-color");
        String secondColor = FreeSeatlegendChart.getCssValue("background-color");

        try {
            if (!firstColor.equals(secondColor)) {
                throw new AssertionError("ფერები არ ემთხვევა ერთმანეთს: პირველი ფერი: " + firstColor + ", მეორე ფერი: " + secondColor);
            }
            System.out.println("Assertion Passed: ფერები ემთხვევა ერთმანეთს!");
        } catch (AssertionError e) {
            System.err.println("Assertion Failed: " + e.getMessage());
        }

        freeSeats.click();

        WebElement registerCreate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'შექმენი')]")));
        registerCreate.click();

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
        email.sendKeys("polina.philipia.gmail.com");

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
        password.sendKeys("Polina123@");

        WebElement passwordRepeat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='PasswordRetype']")));
        passwordRepeat.sendKeys("Polina123@");

        WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'მდედრობითი')]")));
        gender.click();

        WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
        name.sendKeys("Polina");

        WebElement surname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='surname']")));
        surname.sendKeys("Philipia");

        WebElement typeDate = driver.findElement(By.xpath("//span[@class='select2 select2-container select2-container--default']"));
        actions.moveToElement(typeDate).perform();
        typeDate.click();

        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(), '2001')][last()]")));
        suggestion.click();

        WebElement Phone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Phone']")));
        actions.moveToElement(Phone).perform();
        Phone.sendKeys("592154413");

        WebElement getCode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='new-code time outtime']")));
        actions.moveToElement(getCode).perform();
        getCode.click();

        WebElement phoneCode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='PhoneCode']")));
        phoneCode.sendKeys("1234");

        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='checkmark']")));
        actions.moveToElement(checkbox).perform();
        checkbox.click();

        WebElement registration = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='registrationBtn']")));
        actions.moveToElement(registration).perform();
        registration.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error wrong']")));
        Assert.assertTrue(errorMessage.isDisplayed(), "The error element is not visible!");
    }
}
