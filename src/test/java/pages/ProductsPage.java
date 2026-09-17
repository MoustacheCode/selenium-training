package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private By title = By.className("title");
    private By productCard = By.className("inventory_item");
    private By productPrice = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By productName = By.className("inventory_item_name");
    private By cartBadge = By.className("shopping_cart_badge");
    private By backPackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartButton = By.cssSelector("button");
    private By backpackRemoveButton = By.id("remove-sauce-labs-backpack");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getAllProductCards() {
        return waitForAllVisible(productCard);
    }

    public String titleText() {
        return textOf(title);
    }

    public String getProductPrice(WebElement element) {
        return element.findElement(productPrice).getText();
    }

    public void selectSortOption(String visibleText) {
        Select selectDropdown = new Select(waitForVisible(sortDropdown));
        selectDropdown.selectByVisibleText(visibleText);
    }

    public List<String> getAllProductNames() {
        return waitForAllVisible(productName).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getAllProductPrices() {
        return waitForAllVisible(productPrice).stream().map((price) -> Double.parseDouble(price.getText().replace("$", ""))).collect(Collectors.toList());
    }

    public int getCartBadgeCount() {
        List<WebElement> badge = driver.findElements(cartBadge);
        if (
                badge.isEmpty()) {
            return 0;
        }

        return Integer.parseInt (driver.findElement(cartBadge).getText());

    }

    public void addToCart(WebElement product) {
        WebElement addToCartBtn = product.findElement(addToCartButton);
        addToCartBtn.click();
    }

    public void addBackPackToCart() {
        WebElement addToCartButton = waitForClickable(backPackButton);
        addToCartButton.click();
    }

    public boolean isBadgeVisible() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public void removeBackpack() {
        WebElement removeBackpackBtn = waitForClickable(backpackRemoveButton);
        removeBackpackBtn.click();
    }

}