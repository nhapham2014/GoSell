package vn.camautomation;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.RegisterPage;

import java.util.Set;

/**
 * Unit test for simple App.
 */
public class TestRegister
{
    WebDriver driver;
    RegisterPage RegisterPage;
    @Before
    public void setup() {
        this.driver = new ChromeDriver();
        driver.get("https://admin.gosell.vn/signup");
        driver.manage().window().maximize();
        this.RegisterPage = new RegisterPage(this.driver);

    }
    @After
    public void close(){
        driver.quit();
    }
    @Test
    public void Test_ErrorWhenEmptyFields()
    {
        RegisterPage.txtEmail.clear();
        RegisterPage.txtPass.clear();
        RegisterPage.btnRegister.click();
        Assert.assertEquals("Mục này không được để trống",RegisterPage.getErrorEmailEmpty());
        Assert.assertEquals("Mục này không được để trống",RegisterPage.getErrorPassEmpty());
    }
    @Test
    public void Test_ErrorWhenInputInvalidNumberPhone(){
        RegisterPage.txtEmail.clear();
        RegisterPage.txtPass.clear();
        RegisterPage.btnRegister.click();
        String invalidnumberphone="01234567";
        RegisterPage.txtEmail.sendKeys(invalidnumberphone);
        Assert.assertEquals("Số điện thoại không hợp lệ",RegisterPage.getErrorNumberPhone(invalidnumberphone));
    }
    @Test
    public void Test_ErrorWhenInputInvalidEmail(){
        RegisterPage.txtEmail.clear();
        RegisterPage.txtPass.clear();
        RegisterPage.btnRegister.click();
        String invalidemail="nha@gmail";
        RegisterPage.txtEmail.sendKeys(invalidemail);
        Assert.assertEquals("Không đúng địa chỉ hộp thư",RegisterPage.getErrorNumberPhone(invalidemail));
    }
    @Test
    public void Test_ErrorWhenInputInvalidPass(){
        RegisterPage.txtEmail.clear();
        RegisterPage.txtPass.clear();
        RegisterPage.btnRegister.click();
        String invalidpass="123";
        RegisterPage.txtPass.sendKeys(invalidpass);
        Assert.assertEquals("Mật khẩu phải dài ít nhất 8 ký tự và có ít nhất 1 chữ, 1 số và 1 ký tự đặc biệt",RegisterPage.getErrorPass(invalidpass));
    }
    @Test
    public void Test_HideErrorWhenInputValiddata(){
        RegisterPage.txtEmail.clear();
        RegisterPage.txtPass.clear();
        RegisterPage.btnRegister.click();
        RegisterPage.txtEmail.sendKeys("nhapham@gmail.com");
        RegisterPage.txtPass.sendKeys("Nha1234!");
        Assert.assertEquals(false,RegisterPage.checkExistErrorEmail());
        Assert.assertEquals(false,RegisterPage.checkExistErrorPass());
    }
    @Test
    public void Test_ErrorWhenEmptyCode() throws InterruptedException {
        RegisterPage.txtEmail.clear();
        RegisterPage.txtPass.clear();
        RegisterPage.txtEmail.sendKeys("nhapham@gmail.com");
        RegisterPage.txtPass.sendKeys("Nha1234!");
        RegisterPage.btnRegister.click();
        Thread.sleep(2000);
        Set<String> lstWindow = this.driver.getWindowHandles();
        lstWindow.forEach(h->{
            this.driver.switchTo().window(h);
        });
        RegisterPage.btnConfirm.click();
        Thread.sleep(2000);
        Assert.assertEquals("Mã xác thực không đúng",RegisterPage.getErrorVerifyCodeWhenEmpty());
    }

}
