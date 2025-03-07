package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    // Locators
    private By customerDropdown = By.id("userSelect");
    private By currencyDropdown = By.id("currency");
    private By processButton = By.xpath("//button[text()='Process']");

    // Constructor
    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // Select an existing customer from the dropdown
    public void selectCustomer(String customerName) {
        wait.until(ExpectedConditions.elementToBeClickable(customerDropdown));
        Select customerSelect = new Select(driver.findElement(customerDropdown));
        customerSelect.selectByVisibleText(customerName);
        System.out.println("Selected customer: " + customerName);
    }


    // Select currency type (e.g., Dollar, Pound, Rupee)
    public void selectCurrency(String currency) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(currencyDropdown));
        Select currencySelect = new Select(driver.findElement(currencyDropdown));
        currencySelect.selectByVisibleText(currency);
    }

    // Click "Process" button to create the account
    public void clickProcessButton() {
        System.out.println("Clicking the 'Process' button...");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(processButton));

        try {
            System.out.println("Waiting for confirmation alert...");
            wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert detected: " + driver.switchTo().alert().getText());
            driver.switchTo().alert().accept(); // Accept success alert
            System.out.println("Alert accepted.");
        } catch (TimeoutException e) {
            System.out.println("No alert appeared — returning null.");
        }
    }

}
