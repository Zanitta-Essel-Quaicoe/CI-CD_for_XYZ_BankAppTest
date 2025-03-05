package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeEach
    public  void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginPageElementsDisplayed() {
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page elements are not displayed properly.");
    }

    public void loginAsBankManager() {

        loginPage.clickBankManagerLogin();
    }

    public void loginAsCustomer(String customerName) {
        loginPage.clickCustomerLogin();
        loginPage.selectCustomer(customerName);
        loginPage.clickLogin();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
