package TestNG_HW.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Task3 {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test()
    public void AuthenticationMsg(){
        WebElement userName = driver.findElement(By.id("txtUsername"));
        userName.sendKeys("Admin");
        WebElement clickLogin = driver.findElement(By.id("btnLogin"));
        clickLogin.click();
        String actual = driver.findElement(By.id("spanMessage")).getText();
        String expected = "Password cannot be empty";
        Assert.assertEquals(actual,expected, "Message is not Displayed");
        System.out.println("Message : \"Password cannot be empty\" is Displayed");



    }
}