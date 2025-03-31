package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String expectedMessage) {
        driver.get("http://localhost:8080/ASS_PD10302/Home");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

      
        WebElement dropdownButton = driver.findElement(By.xpath("//ul[@class='dropdown-menu']/.."));
        dropdownButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-menu")));

        WebElement registerLink = driver.findElement(By.xpath("//a[@data-bs-target='#dangnhap']"));
        registerLink.click();

        WebElement emailField = driver.findElement(By.id("idOrEmail"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        emailField.clear();
        passwordField.clear();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        try {
            WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));
            String actualMessage = alertMessage.getText();
            Assert.assertEquals(actualMessage, expectedMessage, "Thông báo không đúng!");
        } catch (TimeoutException e) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("Home"), "Không có thông báo lỗi nhưng cũng không điều hướng đúng.");
            Assert.assertEquals(expectedMessage, "Đăng nhập thành công!", "Đăng nhập không thành công.");
        }

        // 🔹 Đăng xuất nếu đăng nhập thành công
        if (expectedMessage.equals("Đăng nhập thành công!")) {
            try {
                // Mở dropdown menu nếu chưa mở
                WebElement logoutDropdown = driver.findElement(By.xpath("//ul[@class='dropdown-menu']/.."));
                if (!logoutDropdown.getAttribute("class").contains("show")) {
                    logoutDropdown.click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-menu")));
                }

                // Nhấn vào nút đăng xuất
                WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("dangxuat")));
                logoutButton.click();

                // Kiểm tra nếu đăng xuất thành công (hiển thị nút đăng nhập)
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-bs-target='#dangnhap']")));

                System.out.println("Đăng xuất thành công!");

            } catch (Exception e) {
                System.out.println("Lỗi khi đăng xuất: " + e.getMessage());
            }
        }
    }



    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
            {"hoangloi0802@gmail.com", "123", "Đăng nhập thành công!"}, 
            {"demo4", "123", "Tài khoản không tồn tại."},
            {"hoangloi0802", "12345", "Sai tài khoản hoặc mật khẩu"},
            {"sai", "345", "Sai tài khoản hoặc mật khẩu"}
        };
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}