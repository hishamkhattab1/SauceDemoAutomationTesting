package TestCases;

import Utilities.DataProviders;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    @Test (dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void testLogin(String Username,String Password,String ErrorMessage){
        loginPage.loginProcess(Username,Password);
        loginPage.AssertLoginProcess(ErrorMessage);
    }
}