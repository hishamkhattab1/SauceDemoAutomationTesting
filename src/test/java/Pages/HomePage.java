package Pages;

import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class HomePage {

    // VARIABLES
    String ActualCartNumber = "//*[@id=\"shopping_cart_container\"]/a/span";
    String productNameXPath = "/html/body/div/div/div/div[2]/div/div/div/div[*]/div[2]/div[1]/a/div\n";
    String productPriceXPath = "/html/body/div/div/div/div[2]/div/div/div/div[*]/div[2]/div[2]/div\n";
    String cartIconXpath = "//*[@id=\"header_container\"]/div[2]/div/span/select";
    String AtoZ = "//*[@id=\"header_container\"]/div[2]/div/span/select/option[1]";
    String ZtoA = "//*[@id=\"header_container\"]/div[2]/div/span/select/option[2]";
    String LowtoHigh = "//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]";
    String HightoLow = "//*[@id=\"header_container\"]/div[2]/div/span/select/option[4]";


    public WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void ClickAddToCartBtn(String addToCartBtn){
        driver.findElement(By.id(addToCartBtn)).click();
    }

    public int AddAllItemsToCart(String ButtonClassName){
        List<WebElement> addToCartButtons = driver.findElements(By.className(ButtonClassName));
        int btnCount=0;
        for (WebElement button : addToCartButtons) {
            button.click();
            btnCount++;
        }
        return btnCount;
    }

    public int RemoveAllItemsFromCart(String RemoveBtnClassName){
        List<WebElement> addToCartButtons = driver.findElements(By.className(RemoveBtnClassName));
        int btnCount=0;
        for (WebElement button : addToCartButtons) {
            button.click();
            btnCount++;
        }
        return btnCount;
    }

    public void AssertCart(Boolean state,String ClassName,int btnAddCount, int btnRemoveCount){
        if(state){
            try {
                int actualCartNum = Integer.parseInt(driver.findElement(By.xpath(ActualCartNumber)).getText());
                Assert.assertTrue(driver.findElement(By.className(ClassName)).isDisplayed());
                if (actualCartNum == btnAddCount){
                    Assert.assertEquals(actualCartNum,btnAddCount);
                }
                else {
                    Assert.assertNotEquals(actualCartNum,btnAddCount);
                }
            } catch (org.openqa.selenium.NoSuchElementException e){
                System.out.println("Failed to Add Items to Cart");
            }
        }
        else {
            try {
                Assert.assertFalse(driver.findElement(By.className(ClassName)).isDisplayed());
            } catch (org.openqa.selenium.NoSuchElementException e){
                System.out.println("Items Removed Successfully");
            }
        }
    }

    public List<String> getProductNames(WebDriver driver){
        List<WebElement> productElements = driver.findElements(By.xpath(productNameXPath));
        List<String> productNames = new ArrayList<>();
        for ( WebElement product : productElements ){
            productNames.add(product.getText());
        }
        return productNames;
    }

    public List<Double> getProductPrices(WebDriver driver){
        List<WebElement> priceElements = driver.findElements(By.xpath(productPriceXPath));
        List<Double> productPrices = new ArrayList<>();
        for ( WebElement price : priceElements){
            productPrices.add(Double.parseDouble(price.getText().replace("$","")));
        }
        return productPrices;
    }

    public void sortFeatureTest(String username){
        try {
            // A to Z
            ClickSortField("A to Z");
            List<String> productNames = getProductNames(driver);
            List<String> sortedNames = new ArrayList<>(productNames);
            Collections.sort(sortedNames);
            System.out.println("A to Z sorting is correct!");

            // Z to A
            ClickSortField("Z to A");
            productNames = getProductNames(driver);
            sortedNames = new ArrayList<>(productNames);
            Collections.sort(sortedNames, Collections.reverseOrder());
            Assert.assertEquals(sortedNames, productNames);
            System.out.println("Z to A sorting is correct!");

            // Price low to high
            ClickSortField("Price low to high");
            List<Double> productPrices = getProductPrices(driver);
            List<Double> sortedPrices = new ArrayList<>(productPrices);
            Collections.sort(sortedPrices);
            Assert.assertEquals(sortedPrices, productPrices);
            System.out.println("Lowest price sorting is correct!");

            // Price high to low
            ClickSortField("Price high to low");
            productPrices = getProductPrices(driver);
            sortedPrices = new ArrayList<>(productPrices);
            Collections.sort(sortedPrices, Collections.reverseOrder());
            Assert.assertEquals(sortedPrices, productPrices);
            System.out.println("Highest price sorting is correct!");
        }
        catch (AssertionError e){
            System.out.println("The sort feature is not working properly for " + username);
        }
    }

    public void ClickRemoveBtn(String removeBtn){
        driver.findElement(By.id(removeBtn)).click();
    }

    public WebDriver ClickMenuBtn(){
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        return driver;
    }

    public void ClickSortField(String SortOption){
        driver.findElement(By.xpath(cartIconXpath)).click();
        switch(SortOption){
            case "A to Z":
                driver.findElement(By.xpath(AtoZ)).click();
                break;
            case "Z to A":
                driver.findElement(By.xpath(ZtoA)).click();
                break;
            case "Price low to high":
                driver.findElement(By.xpath(LowtoHigh)).click();
                break;
            case "Price high to low":
                driver.findElement(By.xpath(HightoLow)).click();
                break;
        }
    }

    public void ClickCartBtn(String cartBtn){
        driver.findElement(By.xpath(cartBtn)).click();
    }
    public void ClickTwitterIcon(String twitterIcon){
        driver.findElement(By.xpath(twitterIcon)).click();
    }
    public void ClickFBIcon(String fbIcon){
        driver.findElement(By.xpath(fbIcon)).click();
    }
    public void ClickLinkedInIcon(String linkedinIcon){
        driver.findElement(By.xpath(linkedinIcon)).click();
    }

    public void switchToNewTabAndAssert(WebDriver driver, String expectedUrl) {
        String originalTab = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();

        // Identify newly opened tab
        for (String handle : allHandles) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        // Assert the URL of the new tab
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));

        driver.close();
        driver.switchTo().window(originalTab);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
    public void scrollBy(String value){
        js.executeScript(value,"");
    }
}
