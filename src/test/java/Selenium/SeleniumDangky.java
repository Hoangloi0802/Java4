package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class SeleniumDangky {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "registerData")
    public void testRegister(String username, String password, String fullname, String email, String expectedMessage) throws InterruptedException {
        driver.get("http://localhost:8080/ASS_PD10302/Home"); 


        WebElement dropdownButton = driver.findElement(By.xpath("//ul[@class='dropdown-menu']/.."));
        dropdownButton.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-menu")));


        WebElement registerLink = driver.findElement(By.xpath("//a[@data-bs-target='#dangky']"));
        registerLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dangky")));

        WebElement usernameField = driver.findElement(By.id("username1"));
        WebElement passwordField = driver.findElement(By.id("password1"));
        WebElement fullnameField = driver.findElement(By.id("fullname1"));
        WebElement emailField = driver.findElement(By.id("email1"));
        WebElement registerButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        passwordField.clear();
        fullnameField.clear();
        emailField.clear();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        fullnameField.sendKeys(fullname);
        emailField.sendKeys(email);
        registerButton.click();

        Thread.sleep(2000); 

        try {
            WebElement successMessage = driver.findElement(By.className("alert-danger"));
            String actualMessage = successMessage.getText();
            Assert.assertEquals(actualMessage, expectedMessage, "Thông báo đăng ký không đúng");
        } catch (Exception e) {
            WebElement errorMessage = driver.findElement(By.className("alert-danger"));
            String actualMessage = errorMessage.getText();
            Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không đúng!");
        }
    }

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {
        return new Object[][]{
            {"test22", "123231", "Thái Hoàng Lợi", "test22@gmail.com", "Đăng ký thành công."},  // TC05
            {"hoangloi", "123123", "Thái Test", "loihau2@gmail.com", "Email đã được sử dụng"},     // TC06
            {"", "123123", "Thái Test", "", "Vui lòng nhập đầy đủ thông tin"}  // TC08
        };
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
