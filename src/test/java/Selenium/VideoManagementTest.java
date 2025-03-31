package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class VideoManagementTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("http://localhost:8080/ASS_PD10302/video/index");
    }

    @Test(priority = 1)
    public void testAddVideo() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("editor-tab"))).click();

 
        driver.findElement(By.id("url")).sendKeys("https://www.youtube.com/watch?v=UpsB7RseMxU");
        driver.findElement(By.id("title")).sendKeys("NGÔI NHÀ BIẾT NÓI");
        driver.findElement(By.id("description")).sendKeys("Hôm nay trên đường về tôi có nhặt được 1 chiếc ví, có vẻ cũng đã khá cũ, bên trong chỉ còn giấy tờ tùy thân của người đàn ông này. Tôi tìm đến địa chỉ nhưng....lạ thật. \r\n"
        		+ "Trước mặt tôi là 1 ngôi nhà đã cháy xém, bỏ hoang tự khi nào. Có vẻ người đó không còn ở đây nữa!");
        driver.findElement(By.id("views")).sendKeys("1000000");

        driver.findElement(By.id("active1")).click();

        WebElement createButton = driver.findElement(By.id("them11"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButton);
        Thread.sleep(500); 

        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("list-tab"))).click();

        Assert.assertTrue(
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(), 'NGÔI NHÀ BIẾT NÓI')]")
            )).isDisplayed(),
            "Không tìm thấy video mới!"
        );
    }


    @Test(priority = 2)
    public void testUpdateVideo() throws InterruptedException {
        driver.findElement(By.id("list-tab")).click();

 
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(), 'NGÔI NHÀ BIẾT NÓI')]/following-sibling::td/a[contains(text(), 'Edit')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
        Thread.sleep(500);
        editButton.click();


        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
        titleField.clear();
        titleField.sendKeys("NGÔI NHÀ BIẾT NÓI 2");
        Thread.sleep(500); 

        WebElement capnhat = wait.until(ExpectedConditions.elementToBeClickable(By.id("capnhat")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", capnhat);
        Thread.sleep(500);
        capnhat.click();

        driver.findElement(By.id("list-tab")).click();
        WebElement updatedVideoRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'NGÔI NHÀ BIẾT NÓI 2')]")));
        Assert.assertNotNull(updatedVideoRow, "Cập nhật video thất bại!");
    }


    @Test(priority = 3)
    public void testDeleteVideo() throws InterruptedException {

        driver.findElement(By.id("list-tab")).click();

        WebElement xoa = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(), 'NGÔI NHÀ BIẾT NÓI 2')]/following-sibling::td/a[contains(text(), 'Delete')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", xoa);
        Thread.sleep(500);

        xoa.click();

        Thread.sleep(500); 
        driver.navigate().refresh(); 
        
        List<WebElement> deletedVideo = driver.findElements(By.xpath("//td[contains(text(), 'NGÔI NHÀ BIẾT NÓI 2')]"));
        Assert.assertTrue(deletedVideo.isEmpty(), "Video vẫn tồn tại sau khi xóa!");
    }



    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
