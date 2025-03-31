import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set đường dẫn ChromeDriver (thay đổi nếu cần)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String expectedMessage) {
        driver.get("http://localhost:8080/ASS_PD10302/Login"); // Thay bằng URL thật

        // Nhập email và mật khẩu
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        emailField.clear();
        passwordField.clear();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        // Lấy thông báo hiển thị
        WebElement messageElement = driver.findElement(By.id("message")); // Giả định có element chứa thông báo
        String actualMessage = messageElement.getText();

        // Kiểm tra kết quả mong đợi
        Assert.assertEquals(actualMessage, expectedMessage, "Kết quả không đúng!");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
            {"hoangloi@gmail.com", "MK123", "Đăng nhập thành công chuyển đến trang chủ"},  // TC01
            {"hoangloi@gmail.com", "saiMK", "Tài khoản hoặc mật khẩu không đúng"},        // TC03
            {"saiemail@gmail.com", "MK123", "Tài khoản không tồn tại"},                   // TC02
            {"hoangloi@gmail.com", "", "Vui lòng nhập đầy đủ thông tin"},                 // TC04
        };
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
