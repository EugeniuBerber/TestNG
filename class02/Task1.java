package TestNG_HW.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1 {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }
    @Test
    public void validLogin() {
        WebElement userName = driver.findElement(By.id("txtUsername"));
        userName.sendKeys("Admin");
        WebElement password = driver.findElement(By.id("txtPassword"));
        password.sendKeys("Hum@nhrm123");
        WebElement clickLogin = driver.findElement(By.id("btnLogin"));
        clickLogin.click();
    }
    @Test(dependsOnMethods = "validLogin")
    public void verificationOfEmployeeMenu(){
        WebElement pimBtn = driver.findElement(By.id("menu_pim_viewPimModule"));
        pimBtn.click();
        WebElement addEmployeeBtn = driver.findElement(By.cssSelector("a#menu_pim_addEmployee"));
        addEmployeeBtn.click();
        WebElement employeeFirstName= driver.findElement(By.name("firstName"));
        WebElement employeeLastName = driver.findElement(By.name("lastName"));
        WebElement photographOption = driver.findElement(By.id("photofile"));
        Assert.assertTrue(employeeFirstName.isDisplayed(), "FirstName input box is not Displayed");
        System.out.println("FirstName field input box is Displayed");
        Assert.assertTrue(employeeLastName.isDisplayed(), "LastName input box is not Displayed");
        System.out.println("LastName field input box is Displayed");
        Assert.assertTrue(photographOption.isDisplayed(), "Photograph option is notDisplayed");
        System.out.println("Photograph option button is Displayed");
    }

    @Test(dependsOnMethods = {"validLogin","verificationOfEmployeeMenu"})
    public void addEmployee() throws InterruptedException {
        WebElement employeeFirstName= driver.findElement(By.name("firstName"));
        WebElement employeeLastName = driver.findElement(By.name("lastName"));
        WebElement photographOption = driver.findElement(By.id("photofile"));
        employeeFirstName.sendKeys("Johnny");
        employeeLastName.sendKeys("Silverhead");
        photographOption.sendKeys("C:\\Users\\BloOdSugar\\Desktop\\download.jfif");
        Thread.sleep(4000);
        WebElement saveBtn = driver.findElement(By.id("btnSave"));
        saveBtn.click();
    }
    @Test(dependsOnMethods = "validLogin",priority = 1)
    public void verifyEmployee() throws InterruptedException {
        WebElement pimBtn = driver.findElement(By.id("menu_pim_viewPimModule"));
        pimBtn.click();
        WebElement employeeListBtn = driver.findElement(By.id("menu_pim_viewEmployeeList"));
        employeeListBtn.click();
        WebElement employeeSearchField = driver.findElement(By.id("empsearch_employee_name_empName"));
        Thread.sleep(4000);
        employeeSearchField.sendKeys("Johnny SilverHead");
        WebElement searchBtn = driver.findElement(By.id("searchBtn"));
        searchBtn.click();

        List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id=\"resultTable\"]/tbody/tr"));
        for (WebElement employee : employeeList) {
            String  target = "Johnny Silverhead";
            if (employee.getText().contains(target)){
                System.out.println("Employee: '"+target+"' was added successfully");
                System.out.println(employee.getText());
                break;
            }else
                System.out.println("Employee: '"+target+"' was not found");
        }


    }
}
