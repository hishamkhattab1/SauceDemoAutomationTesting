package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPage {
    String cartErrorMessageXpath = "//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3";
    public WebDriver driver;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void PressCheckOutBtn(){
        driver.findElement(By.id("checkout")).click();
    }

    public void InsertCheckoutData(String FName,String LName,int postal){
        driver.findElement(By.id("first-name")).sendKeys(FName);
        driver.findElement(By.id("last-name")).sendKeys(LName);
        driver.findElement(By.id("postal-code")).sendKeys(String.valueOf(postal));
        driver.findElement(By.id("continue")).click();
    }

    public void FinishCheckout(){
        try {
            driver.findElement(By.id("finish")).click();
        }
        catch (NoSuchElementException e){
            System.out.println("Failed in the previous step");
        }

    }

    public void AssertCheckoutPage1(){
        if (driver.getCurrentUrl().contains("checkout")){
            Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
        }
        else{
            Assert.assertFalse(driver.getCurrentUrl().contains("checkout"));
        }
    }

    public void AssertCheckoutPage2(){
        if (driver.getCurrentUrl().contains("two")){
            Assert.assertTrue(driver.getCurrentUrl().contains("two"));
        }
        else{
            try {
                Assert.assertFalse(driver.getCurrentUrl().contains("two"));
            }
        catch (NoSuchElementException e) {
                String ExpectedcartErrorMessage = "Error: Last Name is required";
                String cartErrorMessage = driver.findElement(By.xpath(cartErrorMessageXpath)).getText();
                Assert.assertEquals(cartErrorMessage,ExpectedcartErrorMessage);
            }
        }
    }

    public void AssertFinalPage(){
        if (driver.getCurrentUrl().contains("complete")){
            Assert.assertTrue(driver.getCurrentUrl().contains("complete"));
        }
        else{
            try {
                Assert.assertFalse(driver.getCurrentUrl().contains("complete"));

            }
            catch (NoSuchElementException e) {
                // DO Nothing
            }
        }
    }
}
