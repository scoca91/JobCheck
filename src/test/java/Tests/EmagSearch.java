package Tests;

import POMs.EmagSearchPage;
import POMs.InitiateDriver;
import org.testng.annotations.Test;



public class EmagSearch extends InitiateDriver {


    // Searches all products on page and add to cart the most expensive  one
    //@nrOfProducts - specifies the number of the most expensive products to be added in the cart - ex: 1 product / 2 products / n products
    @Test
    public void addtoCart2MostExpensive() throws InterruptedException {
        EmagSearchPage searchPage = new EmagSearchPage(driver);
        searchPage.addToCartMostExpensiveProduct(2);
//


    }

}
