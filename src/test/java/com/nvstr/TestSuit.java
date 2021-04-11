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

public class TestSuit extends WebDriverSettings{
    WebDriver driver = new ChromeDriver();

    @BeforeTest
            public void Browser() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.nvstr.com/");
        driver.manage().window().maximize();
    }
    @AfterTest
    public void close(){
        driver.close();
        driver.quit();
    }

    @Test
    public void SignIn() {

        //LoginButton
        driver.findElement(By.xpath("//span[normalize-space()='Log In']")).click();

        //User/Password entering and login
        driver.findElement(By.id("header_sign_in_email-login-field")).sendKeys(username);
        driver.findElement(By.id("header_sign_in_password-login-field")).sendKeys(pass);
        driver.findElement(By.name("commit")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.className("modal-dismiss")).click();
        System.out.println("SignIn: Pass");

    }

    @Test
    public void StockSearch() {

        //Open Trade page and find the stocks
        driver.findElement(By.xpath("//span[contains(text(),'TRADE')]")).click();
        driver.findElement(By.id("add-order-security-search")).sendKeys("T");
        System.out.println("StockSearch: Pass");
    }

    @Test(dependsOnMethods = "StockSearch")
    public void SelectStock() {

        //Select and open stock from dropdown menu
        WebElement Tesla = driver.findElement(By.id("security-2634"));
        Tesla.click();
        System.out.println("SelectStock: Pass");
   }

   @Test(dependsOnMethods = "SelectStock")
   public void BuyStock() throws InterruptedException {

      //Buy or Sell selection
      driver.findElement(By.xpath("//div[contains(@class,'toggle-left')]")).click();

      //Add number of shares
      driver.findElement(By.name("shares")).sendKeys("10");

      //Put "Buy" Button
      driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       Thread.sleep(1000);

       //Order Confirmation
      WebElement Paymentcomplete = driver.findElement(By.className("payment-complete-message"));
      Assert.assertEquals(Paymentcomplete.getText(), "Orders placed");
      driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
      driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
      System.out.println("BuyStock: Pass");
   }

   @Test(dependsOnMethods = "BuyStock")
    public void SellStock() throws InterruptedException {
       driver.findElement(By.id("add-order-security-search")).sendKeys("T");
       driver.findElement(By.id("security-2634")).click();

       //Select "Sell" Button
       driver.findElement(By.xpath("//div[contains(@class,'toggle-right')]")).click();

       //Add number of shares
       driver.findElement(By.name("shares")).sendKeys("10");

       //Click "Place Order" Button
       driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

       //Order Confirmation
       Thread.sleep(1000);
       WebElement Paymentcomplete = driver.findElement(By.className("payment-complete-message"));
       Assert.assertEquals(Paymentcomplete.getText(), "Orders placed");
       driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
       driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
       System.out.println("SellStock: Pass");

   }
   @Test(dependsOnMethods = "SellStock")
    public void ErrorEmptyShares() throws InterruptedException {
       driver.findElement(By.id("add-order-security-search")).sendKeys("T");
       driver.findElement(By.id("security-2634")).click();

       //Select "Sell" Button
       driver.findElement(By.xpath("//div[contains(@class,'toggle-right')]")).click();

       //Click "Place Order" Button
       driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();

       //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       Thread.sleep(1000);
       WebElement SharesError = driver.findElement(By.xpath("//fieldset[contains(@class,'')]//p[contains(@class,'text-field-error-message')][normalize-space()='Required Field']"));
       Assert.assertEquals(SharesError.getText(), "Required Field");
       WebElement BuyError = driver.findElement(By.className("text-block"));
       Assert.assertEquals(BuyError.getText(), "Please fill out all required fields.");

       driver.findElement(By.xpath("//div[contains(@class,'new-order-cancel-x no-shadow')]")).click();
       System.out.println("ErrorEmptyShares: Pass");
   }
   @Test(dependsOnMethods = "ErrorEmptyShares")
    public void ErrorBuySellSelection() throws InterruptedException {
       driver.findElement(By.id("add-order-security-search")).sendKeys("T");
       driver.findElement(By.id("security-2634")).click();

       //Add number of shares
       driver.findElement(By.name("shares")).sendKeys("140");

       //Click "Place Order" Button
       driver.findElement(By.xpath("//input[contains(@value,'Place Order')]")).click();
       Thread.sleep(1000);

       //Validate Buy/Sell error
       WebElement SharesError = driver.findElement(By.xpath("//p[normalize-space()='Required Field']"));
       Assert.assertEquals(SharesError.getText(), "Required Field");
       WebElement BuyError = driver.findElement(By.className("text-block"));
       Assert.assertEquals(BuyError.getText(), "Please fill out all required fields.");
       System.out.println("ErrorBuySellSelection: Pass");
   }



}
