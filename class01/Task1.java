package TestNG_HW.class01;

import org.testng.annotations.*;

public class Task1 {
    @BeforeClass
    public void beforeClass(){
        System.out.println("This will execute before Class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("This will execute after Class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("__________________________________________________");

    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
    }
    @Test
    public void method1(){
        System.out.println("1st ----- Method");
    }
    @Test
    public void method2(){
        System.out.println("2nd ----- Method");
    }
    @Test
    public void method3(){
        System.out.println("3rd ----- Method");
    }
}
