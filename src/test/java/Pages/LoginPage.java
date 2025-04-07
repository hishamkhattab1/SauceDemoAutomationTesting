package Pages;

import Utilities.ExcelUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class LoginPage {

    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    /*@DataProvider(name = "loginData")
    public Object[][] getData(Method method) {
        String excelPath = "D:\\Freelancing\\Book1.xlsx";
        ExcelUtilities excel = new ExcelUtilities(excelPath, "Sheet1");

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        Object data[][] = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            }
        }
        return data;
    }*/



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

    public void AssertLoginProcess(String ErrorMessage){
        if (driver.getCurrentUrl().contains("inventory.html")){
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),"Login Failed!");
        }
        else {
            Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),ErrorMessage);
        }
    }
}
