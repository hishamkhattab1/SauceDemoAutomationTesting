package TestCases;

import Utilities.DataProviders;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest{
    String FName = "ahmed";
    String LName = "mohamed";
    int postal = 40000;

    @Test (dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void CheckoutProcess(String UN, String PW, String Message){
        try {
            loginPage.loginProcess(UN, PW);
            String status = loginPage.AssertLoginProcess(Message);
            if (status.equalsIgnoreCase("failure")) {
                    System.out.println("Skipping further tests for user: " + UN);
                    return; // Exit the test early
            }
            cartpage.addItemstoCart();
            cartpage.ClickCartBtn();
            checkoutpage.PressCheckOutBtn();
            checkoutpage.AssertCheckoutPage1();
            checkoutpage.InsertCheckoutData(FName, LName, postal);
            checkoutpage.AssertCheckoutPage2();
            System.out.println(UN + "passed checkout 2 ");
            checkoutpage.FinishCheckout();
            checkoutpage.AssertFinalPage();
            System.out.println(UN + "passed final");
        }
        catch (NoSuchElementException e){
            System.err.println("Exception encountered for user: " + UN);
            e.printStackTrace();
        }
    }

}
