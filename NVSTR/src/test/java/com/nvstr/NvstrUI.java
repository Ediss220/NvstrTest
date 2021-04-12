package com.nvstr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class NvstrUI extends WebDriverSettings{
    //Select Web-browser for test(just comment and uncomment what do you need)
      WebDriver driver = new ChromeDriver();
    //WebDriver driver = new FirefoxDriver();
    //WebDriver driver = new SafariDriver();
    //WebDriver driver = new EdgeDriver();
    //WebDriver driver = new OperaDriver();




    @BeforeTest
    public void Browser() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.nvstr.com/");
        driver.manage().window().maximize();

        //LoginButton
        driver.findElement(By.xpath("//span[normalize-space()='Log In']")).click();

        //Add Credentials
        driver.findElement(By.id("header_sign_in_email-login-field")).sendKeys(username);
        driver.findElement(By.id("header_sign_in_password-login-field")).sendKeys(pass);
        driver.findElement(By.name("commit")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.className("modal-dismiss")).click();

        //Data Validation
        WebElement PortfolioValue = driver.findElement(By.xpath("//div[normalize-space()='Portfolio Value']"));
        Assert.assertEquals(PortfolioValue.getText(), "Portfolio Value");
        System.out.println("Successfully Login");
        }
    @AfterTest
    public void close(){
        driver.close();
        driver.quit();
    }
    @Test (priority=1)
    public void TC01 () {

        //Open Trade page and find the stocks
        driver.findElement(By.xpath("//span[contains(text(),'TRADE')]")).click();
        driver.findElement(By.id("add-order-security-search")).sendKeys("T");

        //Data Validation
        WebElement DropDownMenuTSLA = driver.findElement(By.id("security-2634"));
        Assert.assertEquals(DropDownMenuTSLA.getText(), "TSLA Tesla Inc.");
        System.out.println("TC01: Pass");
    }
    @Test(priority=2,dependsOnMethods = "TC01")
    public void TC02() {

        //Select and open stock from dropdown menu
        driver.findElement(By.id("security-2634")).click();

        //Data Validation
        WebElement TSLA = driver.findElement(By.className("stock-symbol"));
        Assert.assertEquals(TSLA.getText(), "TSLA");
        driver.findElement(By.xpath("//div[contains(@class,'new-order-cancel-x no-shadow')]")).click();
        System.out.println("TC02: Pass");
   }
   @Test(priority=3)
   public void TC03() throws InterruptedException {

      //Open Stock
      driver.findElement(By.xpath("//span[contains(text(),'TRADE')]")).click();
      driver.findElement(By.id("add-order-security-search")).sendKeys("T");
      driver.findElement(By.id("security-2634")).click();

      //Select "Buy" Button
      driver.findElement(By.xpath("//div[contains(@class,'toggle-left')]")).click();

      //Add number of shares
      driver.findElement(By.name("shares")).sendKeys("10");

      //Click "Place Order" Button
      driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       Thread.sleep(1000);

      //Data Validation
      WebElement OrderConfirmation = driver.findElement(By.className("payment-complete-message"));
      Assert.assertEquals(OrderConfirmation.getText(), "Orders placed");
      driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
      driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
       Thread.sleep(2000);
      System.out.println("TC03: Pass");
   }

   @Test(priority=4)
    public void TC04() throws InterruptedException {

       //Open Stock
       driver.findElement(By.xpath("//span[contains(text(),'TRADE')]")).click();
       driver.findElement(By.id("add-order-security-search")).sendKeys("T");
       driver.findElement(By.id("security-2634")).click();

       //Select "Sell" Button
       driver.findElement(By.xpath("//div[contains(@class,'toggle-right')]")).click();

       //Add number of shares
       driver.findElement(By.name("shares")).sendKeys("10");

       //Click "Place Order" Button
       driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
       Thread.sleep(2000);

       //Data Validation
       WebElement Paymentcomplete = driver.findElement(By.className("payment-complete-message"));
       Assert.assertEquals(Paymentcomplete.getText(), "Orders placed");

       //Windows closing
       driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
       driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
       System.out.println("TC04: Pass");

   }
   @Test(priority=5)
    public void TC05() throws InterruptedException {

       //Open Stock
       driver.findElement(By.xpath("//span[contains(text(),'TRADE')]")).click();
       driver.findElement(By.id("add-order-security-search")).sendKeys("T");
       driver.findElement(By.id("security-2634")).click();

       //Select "Sell" Button
       driver.findElement(By.xpath("//div[contains(@class,'toggle-right')]")).click();

       //Click "Place Order" Button
       driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       Thread.sleep(1000);

       //Data Validation
       WebElement SharesError = driver.findElement(By.xpath("//fieldset[contains(@class,'')]//p[contains(@class,'text-field-error-message')][normalize-space()='Required Field']"));
       Assert.assertEquals(SharesError.getText(), "Required Field");
       WebElement BuyError = driver.findElement(By.className("text-block"));
       Assert.assertEquals(BuyError.getText(), "Please fill out all required fields.");

       //Windows closing
       driver.findElement(By.xpath("//div[contains(@class,'new-order-cancel-x no-shadow')]")).click();
       System.out.println("TC05: Pass");
   }
   @Test(priority=6)
    public void TC06() throws InterruptedException {

       //Open Stock
       driver.findElement(By.xpath("//span[contains(text(),'TRADE')]")).click();
       driver.findElement(By.id("add-order-security-search")).sendKeys("T");
       driver.findElement(By.id("security-2634")).click();

       //Add number of shares
       driver.findElement(By.name("shares")).sendKeys("140");

       //Click "Place Order" Button
       driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       Thread.sleep(1000);

       //Data Validation
       WebElement SharesError = driver.findElement(By.xpath("//p[normalize-space()='Required Field']"));
       Assert.assertEquals(SharesError.getText(), "Required Field");
       WebElement BuyError = driver.findElement(By.className("text-block"));
       Assert.assertEquals(BuyError.getText(), "Please fill out all required fields.");
       System.out.println("TC06: Pass");
   }

}
