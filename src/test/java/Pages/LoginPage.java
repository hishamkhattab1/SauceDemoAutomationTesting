package Pages;

import Utilities.ExcelUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class LoginPage {
    String homepageURL = "inventory.html";
    String actualLoginEMessage = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3";

    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void loginProcess(String username,String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String AssertLoginProcess(String ErrorMessage){
        if (driver.getCurrentUrl().contains(homepageURL)){
            Assert.assertTrue(driver.getCurrentUrl().contains(homepageURL),"Login Failed!");
            return "success";
        }
        else {
            Assert.assertEquals(driver.findElement(By.xpath(actualLoginEMessage)).getText(),ErrorMessage);
            return "failure";
        }
    }
}
