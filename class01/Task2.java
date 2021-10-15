package TestNG_HW.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Task2 {
    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void validLogin() {
        WebElement userName = driver.findElement(By.id("txtUsername"));
        userName.sendKeys("Admin");
        WebElement password = driver.findElement(By.id("txtPassword"));
        password.sendKeys("Hum@nhrm123");
        WebElement clickLogin = driver.findElement(By.id("btnLogin"));
        clickLogin.click();

    }
    @Test()
    public void validateLogo(){
        WebElement syntaxLogo = driver.findElement(By.xpath("//img[contains(@src,'/default/images/syntax.png')]"));
        Assert.assertTrue(syntaxLogo.isDisplayed(), "Logo is not Displayed");
        System.out.println("Logo is Displayed");
    }
}
