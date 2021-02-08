package POMs;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.*;


public class EmagSearchPage {

    WebDriver driver;

    public EmagSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    //////////////////////////////////////////////////////XPaths//////////////////////////////////////////////////////////////////

    By acceptCookies = By.xpath("//button[text()='Accept']");

    By cardBottomBody = By.cssSelector("div.card-section-btm");
    By productNewPrice = By.cssSelector("p.product-new-price");
    By addToCart = By.cssSelector("button[type=submit]");

    By closePopUp = By.xpath("//button[@class[contains(string(),'close')] and @aria-label[contains(string(),'Inchide')]]");

    By myCartButton = By.id("my_cart");

    By productSections = By.xpath("//div[@class='cart-widget cart-line']");
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Accept Cookies window
    public void clickAcceptCookies() throws InterruptedException {
        Thread.sleep(1500);
        if(driver.findElements(acceptCookies).size()>0){
            driver.findElement(acceptCookies).click();
        }
    }

    //Waits for the pop up window and closes it
    public void closePopUp(){
        WebDriverWait waitElement = new WebDriverWait(driver,30);
        waitElement.until(ExpectedConditions.elementToBeClickable(closePopUp));
        driver.findElement(closePopUp).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) { }
    }

    //Clicks on the cart icon to go to the Cart section
    public void clickCartIcon(){
            try {
                Thread.sleep(1000);
            } catch (Exception e) { }
        driver.findElement(myCartButton).click();
    }


    // Searches all products on page, finds the highest priced one and adds it to cart
    public void addToCartMostExpensiveProduct(int nrOfProducts) throws InterruptedException {

        //Accepts cookies if pop up present
        this.clickAcceptCookies();

        // creates an array for all the products on the page
        ArrayList<WebElement> productsList = new ArrayList<WebElement>();

        WebElement cheapestProduct = null;
        Integer eachProductsPrice;
        Integer minPrice = 1;

        //Lists all products on the page and parses their prices
        List<WebElement> products = driver.findElements(cardBottomBody);
        for(WebElement eachProduct: products) {
             eachProductsPrice = Integer.parseInt(eachProduct.findElement(productNewPrice).getText().replaceAll("[^0-9]",""));

     //       System.out.println(eachProductsPrice);
            // Adds all the products to the ArrayList - method created like this in case we only want the highest priced product !! only 1 - can be easily modified without using array
            if(eachProductsPrice > minPrice) {
                minPrice = eachProductsPrice;
                System.out.println(minPrice);
                cheapestProduct = eachProduct.findElement(addToCart);
                productsList.add(cheapestProduct);
            }
        }

        System.out.println("The Highest price is:" +minPrice);

        //Adds the 2 most expensive products on the page
        for(int nrOfWantedProducts = 1;nrOfWantedProducts <= nrOfProducts;nrOfWantedProducts++ ){
//            JavascriptExecutor js = (JavascriptExecutor) driver;
            Actions actions = new Actions(driver);
            actions.moveToElement(cheapestProduct).perform();
            Thread.sleep(1000);
//            js.executeScript("window.scrollBy(0,600)");
            productsList.get(productsList.size() - nrOfWantedProducts).click();

            this.closePopUp();
        }

        this.goToCartAndCheckProducts(nrOfProducts);

    }

    //Checks cart and asserts that the products were added
    public void goToCartAndCheckProducts(int nrOfProducts){
    this.clickCartIcon();
        Assert.assertTrue(driver.findElements(productSections).size()== nrOfProducts);
    }

}
