package TestCases;

import org.testng.annotations.Test;


public class HomeTest extends BaseTest {

    @Test
    public void addItemsToCartTest(){
        loginPage.loginProcess("standard_user","secret_sauce");
        int btnAddCount = homePage.AddAllItemsToCart("btn_inventory");
        homePage.AssertCart(true,"shopping_cart_badge",btnAddCount,0);
    }

   @Test
    public void RemoveItemsTest(){
        loginPage.loginProcess("standard_user","secret_sauce");
        int btnAddCount = homePage.AddAllItemsToCart("btn_primary");
        int btnRemoveCount = homePage.RemoveAllItemsFromCart("btn_secondary");
        homePage.AssertCart(false,"shopping_cart_badge",btnAddCount,btnRemoveCount);
    }

    @Test (priority = 1)
    public void SortFeatureTesting(){
        loginPage.loginProcess("standard_user","secret_sauce");
        homePage.sortFeatureTest();
    }

    @Test
    public void CheckFooterSocialMedia(){
        loginPage.loginProcess("standard_user","secret_sauce");
        // Test Twitter Icon
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
