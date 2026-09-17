package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.ProductsPage;
import shared.BaseTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsAddToCartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeEach
    public void setupProducts() {

        productsPage = new LoginPage(driver).successfullyLogin();

    }

    @Test
    public void addingProductToCartShowsCartBadgeWithCorrectNumber() {

        productsPage.addBackPackToCart();
        assertEquals(1, productsPage.getCartBadgeCount());

    }

    @Test
    public void addingMultipleProductToCartShowsCartBadgeWithCorrectNumber() {

        List<WebElement> allProductCards = productsPage.getAllProductCards();
        productsPage.addToCart(allProductCards.get(0));
        productsPage.addToCart(allProductCards.get(1));
        productsPage.addToCart(allProductCards.get(2));
        assertEquals(3, productsPage.getCartBadgeCount());

    }

    @Test
    public void addingAndRemovingProductShowsAndHidesBadge() {

        productsPage.addBackPackToCart();
        assertTrue(productsPage.isBadgeVisible());
        productsPage.removeBackpack();
        assertFalse(productsPage.isBadgeVisible());

    }

    // add and remove but not all
    // add and remove all - make sure the badge is gone



}
