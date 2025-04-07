package TestCases;

import Pages.LoginPage;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    @Test
    public void AboutCheck(){
        loginPage.loginProcess("standard_user","secret_sauce");
        menupanel.AssertAboutPage();
    }

    @Test
    public void LogoutCheck(){
        loginPage.loginProcess("standard_user","secret_sauce");
        menupanel.AssertLogout();
    }
}
