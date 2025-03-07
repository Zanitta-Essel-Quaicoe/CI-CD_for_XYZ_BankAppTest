package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BankManagerPage {
    private WebDriver bankDriver;
    private WebDriverWait wait;

    // Locators
    private By addCustomerButton = By.xpath("//button[contains(text(), 'Add Customer')]");
    private By openAccountButton = By.xpath("//button[contains(text(), 'Open Account')]");
    private By customersButton = By.xpath("//button[contains(text(), 'Customers')]");

    // Constructor
    public BankManagerPage(WebDriver driver) {
        this.bankDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Click "Add Customer" tab
    public void clickAddCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerButton)).click();
    }

    // Click "Open Account" tab
    public void clickOpenAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(openAccountButton)).click();
    }

    // Click "Customers" tab
    public void clickCustomers() {
        wait.until(ExpectedConditions.elementToBeClickable(customersButton)).click();
    }
}
