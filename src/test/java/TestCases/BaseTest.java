package TestCases;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public MenuPanel menupanel;
    public CartPage cartpage;
    public CheckoutPage checkoutpage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("http://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        menupanel = new MenuPanel(driver);
        cartpage = new CartPage(driver);
        checkoutpage = new CheckoutPage(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void EscapeLockedUsers(String status,String UN){
        if (status.equalsIgnoreCase("failure")) {
            System.out.println("Skipping further tests for user: " + UN);
            return; // Exit the test early
        }
    }
}
