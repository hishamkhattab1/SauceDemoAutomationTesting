package TestCases;

import org.testng.annotations.Test;

public class CartTest extends BaseTest{


    public void AssertRemoveAllItemFromCart(){
        loginPage.loginProcess("standard_user","secret_sauce");
        cartpage.addItemstoCart();
        cartpage.ClickCartBtn();
        cartpage.removeItemsFromCart();
        cartpage.AssertRemoveFromCart();
    }

    public void AssertContinueShopping(){
        loginPage.loginProcess("standard_user","secret_sauce");
        cartpage.ClickCartBtn();
        cartpage.ClickContinueShopping();
        cartpage.AssertContinueShopping();
    }


    public void AssertCheckOut(){
        loginPage.loginProcess("standard_user","secret_sauce");
        cartpage.ClickCartBtn();
        cartpage.ClickCheckOutBtn();
        cartpage.AssertCheckout();
    }
}
