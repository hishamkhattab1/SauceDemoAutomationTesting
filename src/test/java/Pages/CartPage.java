package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


public class CartPage extends HomePage{
    String ActualCartNumber = "/html/body/div/div/div/div[1]/div[1]/div[3]/a/span";
    By CartNumIcon = By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span");
    String AddItemsClass = "btn_primary";
    String RemoveItemsClass = "cart_button";
    private String  CartBtnLocator = "//*[@id=\"shopping_cart_container\"]/a";

    public CartPage(WebDriver driver){
        super(driver);
    }

    public int getCartItemCount() {
        return Integer.parseInt(driver.findElement(By.xpath(ActualCartNumber)).getText());
    }

    public int addItemstoCart(){
       int i = this.AddAllItemsToCart(AddItemsClass);
       return i;
    }

    public int removeItemsFromCart() {
        int i = this.RemoveAllItemsFromCart(RemoveItemsClass);
        return i;
    }

    public void ClickCartBtn(){
        this.ClickCartBtn(CartBtnLocator);
    }

    public void ClickCheckOutBtn(){
        driver.findElement(By.id("checkout")).click();
    }

    public void ClickContinueShopping(){
        driver.findElement(By.id("continue-shopping")).click();
    }

    // ASSERTION

    public boolean isElementPresent() {
        List<WebElement> elements = driver.findElements(CartNumIcon);
        return !elements.isEmpty(); // Return true if at least one element is found, false otherwise
    }

    public void AssertRemoveFromCart(){
        if(isElementPresent()){
            Assert.assertFalse(isElementPresent());
        }
        else {
            Assert.assertTrue(isElementPresent());
        }
    }

    public void AssertContinueShopping(){
        if (driver.getCurrentUrl().contains("inventory.html")){
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        }
        else{
            Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"));
        }
    }

    public void AssertCheckout(){
        if (driver.getCurrentUrl().contains("checkout")){
            Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
        }
        else{
            Assert.assertFalse(driver.getCurrentUrl().contains("checkout"));
        }
    }
}
