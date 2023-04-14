package vn.camautomation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.LoginPage;

import java.util.Set;

public class TestLogin {
    WebDriver driver;
    LoginPage LoginPage;
    @Before
    public void setup(){
        this.driver=new ChromeDriver();
        driver.get("https://admin.gosell.vn/login");
        driver.manage().window().maximize();
        this.LoginPage = new LoginPage(driver);
    }
    /*@After
    public void close(){
        driver.quit();
    }*/
    @Test
    public void Test_ErrorEmailPassWhenEmptyAtAdminPage() throws InterruptedException {
        LoginPage.optionAdmin.click();
        LoginPage.dpxCountryID.click();
        Thread.sleep(2000);
        LoginPage.optionCountry.click();
        LoginPage.txtEmail.clear();
        LoginPage.txtPass.clear();
        LoginPage.btnLogin.click();
        Assert.assertEquals("Mục này không được để trống",LoginPage.getErrorEmail());
        Assert.assertEquals("Mục này không được để trống",LoginPage.getErrorPass());
    }
    @Test
    public void Test_ErrorEmailPassWhenInputIncorrectDataAtAdminPage() throws InterruptedException {
        LoginPage.optionAdmin.click();
        LoginPage.dpxCountryID.click();
        Thread.sleep(2000);
        LoginPage.optionCountry.click();
        LoginPage.txtEmail.clear();
        LoginPage.txtPass.clear();
        LoginPage.txtEmail.sendKeys("gow450622@nezid.com");
        LoginPage.txtPass.sendKeys("123");
        LoginPage.btnLogin.click();
        Thread.sleep(2000);
        Assert.assertEquals("Email/Số điện thoại và mật khẩu không chính xác",LoginPage.getErrorEmailPassWhenInputIncorrectData());
    }
    @Test
    public void Test_ErrorEmailPassWhenEmptyAtStaffPage() throws InterruptedException {
        LoginPage.optionStaff.click();
        LoginPage.txtEmail.clear();
        LoginPage.txtPass.clear();
        LoginPage.btnLogin.click();
        Thread.sleep(1000);
        Assert.assertEquals("Email/Số điện thoại và mật khẩu không chính xác",LoginPage.getErrorEmailPassWhenInputIncorrectData());
    }
    @Test
    public void Test_ErrorEmailPassWhenInputIncorrectDataAtStaffPage() throws InterruptedException {
        LoginPage.optionStaff.click();
        LoginPage.txtEmail.clear();
        LoginPage.txtPass.clear();
        LoginPage.txtEmail.sendKeys("nha@gmail.com");
        LoginPage.txtPass.sendKeys("123");
        LoginPage.btnLogin.click();
        Thread.sleep(1000);
        Assert.assertEquals("Email/Số điện thoại và mật khẩu không chính xác",LoginPage.getErrorEmailPassWhenInputIncorrectData());
    }
    @Test
    public void Test_ErrorWhenInputIncorrectDataWhenLoginByFB() throws InterruptedException {
        LoginPage.optionAdmin.click();
        LoginPage.btnLoginFB.click();
        Thread.sleep(2000);
        Set<String> lstwindow = driver.getWindowHandles();
        lstwindow.forEach(h->{
            this.driver.switchTo().window(h);
        });
        LoginPage.txbEmailFB.sendKeys("nha@gmail.com");
        LoginPage.txbPassFB.sendKeys("123456");
        LoginPage.btnSubmitFB.click();
        Thread.sleep(3000);
        Assert.assertEquals("Please re-enter your password\n" +
                "The password that you've entered is incorrect. Forgotten password?",LoginPage.getErrorEmailPassWhenLoginWithIncorrectAccount());
    }

}
