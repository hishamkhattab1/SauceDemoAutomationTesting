package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class MenuPanel{
    private WebDriver driver;
    public MenuPanel(WebDriver driver){
        this.driver = driver;
    }

    public void ClickMenuButton(){
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        Actions actions = new Actions(driver);
        WebElement menu = driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
        actions.moveToElement(menu).perform();
    }

    public void AssertAboutPage(){
        ClickMenuButton();
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[2]/div[1]/nav/a[2]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"),"error");
    }

    public void AssertLogout(){
        ClickMenuButton();
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[2]/div[1]/nav/a[3]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
    }
}
