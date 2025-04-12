package TestCases;

import Utilities.DataProviders;
import org.testng.annotations.Test;


public class HomeTest extends BaseTest {

    @Test (dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void addItemsToCartTest(String UN,String PW, String Message){
        loginPage.loginProcess(UN, PW);
        String status = loginPage.AssertLoginProcess(Message);
        EscapeLockedUsers(status,UN);
        int btnAddCount = homePage.AddAllItemsToCart("btn_inventory");
        homePage.AssertCart(true,"shopping_cart_badge",btnAddCount,0);
    }

   @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void RemoveItemsTest(String UN,String PW, String Message){
        loginPage.loginProcess(UN, PW);
        String status = loginPage.AssertLoginProcess(Message);
        if (status.equalsIgnoreCase("failure")) {
           System.out.println("Skipping further tests for user: " + UN);
           return; // Exit the test early
        }
        int btnAddCount = homePage.AddAllItemsToCart("btn_primary");
        int btnRemoveCount = homePage.RemoveAllItemsFromCart("btn_secondary");
        homePage.AssertCart(false,"shopping_cart_badge",btnAddCount,btnRemoveCount);
    }

    @Test (dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void SortFeatureTesting(String UN,String PW, String Message){
        loginPage.loginProcess(UN, PW);
        String status = loginPage.AssertLoginProcess(Message);
        if (status.equalsIgnoreCase("failure")) {
                System.out.println("Skipping further tests for user: " + UN);
                return; // Exit the test early
        }
        homePage.sortFeatureTest(UN);
    }

    @Test (dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void CheckFooterSocialMedia(String UN,String PW, String Message){
        loginPage.loginProcess(UN, PW);
        String status = loginPage.AssertLoginProcess(Message);
        // Test Twitter Icon
        if (status.equalsIgnoreCase("failure")) {
            System.out.println("Skipping further tests for user: " + UN);
            return; // Exit the test early
        }
        homePage.ClickTwitterIcon("//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a");
        homePage.switchToNewTabAndAssert(driver,"x.com");
        // Test Facebook Icon
        homePage.ClickFBIcon("//*[@id=\"page_wrapper\"]/footer/ul/li[2]/a");
        homePage.switchToNewTabAndAssert(driver,"facebook.com");
        // Test LinkedInIcon
        homePage.ClickLinkedInIcon("//*[@id=\"page_wrapper\"]/footer/ul/li[3]/a");
        homePage.switchToNewTabAndAssert(driver,"linkedin.com");
    }
}
