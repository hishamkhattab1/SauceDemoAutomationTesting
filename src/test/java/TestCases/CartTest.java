package TestCases;

import org.testng.annotations.Test;

public class CartTest extends BaseTest{

    @Test
    public void AssertRemoveAllItemFromCart(){
        loginPage.loginProcess("standard_user","secret_sauce");
        cartpage.addItemstoCart();
        cartpage.ClickCartBtn();
        cartpage.removeItemsFromCart();
        cartpage.AssertRemoveFromCart();
    }

    @Test
    public void AssertContinueShopping(){
        loginPage.loginProcess("standard_user","secret_sauce");
        cartpage.ClickCartBtn();
        cartpage.ClickContinueShopping();
        cartpage.AssertContinueShopping();
    }

    @Test
    public void AssertCheckOut(){
        loginPage.loginProcess("standard_user","secret_sauce");
        cartpage.ClickCartBtn();
        cartpage.ClickCheckOutBtn();
        cartpage.AssertCheckout();
    }
}
