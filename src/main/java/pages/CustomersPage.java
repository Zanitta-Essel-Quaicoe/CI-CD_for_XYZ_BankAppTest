package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CustomersPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.xpath("//input[@type='text']"); // Ensure correct locator
    private By customerRows = By.xpath("//table//tr");
    private By deleteButtons = By.xpath("//button[text()='Delete']");

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit wait
    }

    // Method to search for a customer by name
    public boolean searchCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)); // Wait for search box
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.clear();
        searchInput.sendKeys(customerName);

        // Wait for results
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(customerRows));
        List<WebElement> rows = driver.findElements(customerRows);

        // Debugging: Print all customers found
        System.out.println("Searching for customer: " + customerName);
        System.out.println("Customers found:");
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }

        return rows.size() > 1; // If more than 1 row, customer exists
    }

    // Method to delete a customer by name
    public void deleteCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)); // Wait for search box
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.clear();
        searchInput.sendKeys(customerName);

        // Wait for delete button to appear
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtons));

        List<WebElement> deleteBtns = driver.findElements(deleteButtons);
        if (!deleteBtns.isEmpty()) {
            System.out.println("Deleting customer: " + customerName);
            deleteBtns.get(0).click(); // Click the first delete button
        } else {
            System.out.println("No delete button found for customer: " + customerName);
        }

        // Wait and verify deletion
        boolean isDeleted = !searchCustomer(customerName);
        System.out.println("Customer deleted: " + isDeleted);
    }
}
