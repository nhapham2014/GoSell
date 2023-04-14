package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class RegisterPage extends PageObjectPage {
    public WebDriver driver;
    @FindBy(css = "button[class*=\"select__value\"]")
    public WebElement dpxCountryID;
    @FindBy(xpath = "//div[contains(@class,\"optionListWrapper\")]//div[text()=\"Vietnam\"]")
    public WebElement optionCountry;
    @FindBy(css = "input[name=\"username\"]")
    public WebElement txtEmail;
    @FindBy(css = "input[name=\"password\"]")
    public WebElement txtPass;
    @FindBy(id = "refCode")
    public WebElement txtrefCode;
    @FindBy(xpath = "//span[text()=\"Đăng ký\"]")
    public WebElement btnRegister;
    @FindBy(xpath = "//span[text()=\"Đăng nhập bằng\"]")
    public WebElement btnRegisterbyFb;
    @FindBy(xpath = "//span[text()=\"Xác thực và đăng nhập\"]")
    public WebElement btnConfirm;
    By lcErrorCode = By.xpath("//div[@type=\"error\"]");
    By lcErrorEmail = By.xpath("//form[@class=\"av-submitted av-invalid\"]/div[2]/div");
    By lcErrorPass = By.xpath("//form[@class=\"av-submitted av-invalid\"]/div[3]/div[1]/div/div");

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getErrorEmailEmpty() {
        WebElement txtErrorEmail = this.getDynamicElement(lcErrorEmail);
        return txtErrorEmail.getText();
    }

    public String getErrorPassEmpty() {
        WebElement txtErrorPass = this.getDynamicElement(lcErrorPass);
        return txtErrorPass.getText();
    }

    public String getErrorNumberPhone(String text) {
        WebElement txtErrorNumberPhone = this.getDynamicElement(lcErrorEmail);
        return txtErrorNumberPhone.getText();

    }

    public String getErrorEmail(String text) {
        WebElement txtErrorEmail = this.getDynamicElement(lcErrorEmail);
        return txtErrorEmail.getText();
    }

    public String getErrorPass(String text) {
        WebElement txtErrorPass = this.getDynamicElement(lcErrorPass);
        return txtErrorPass.getText();
    }

    public boolean checkExistErrorEmail() {
        if (driver.findElements(lcErrorEmail).size() > 0) {
            return true;
        }
        return false;
    }

    public boolean checkExistErrorPass() {
        if (driver.findElements(lcErrorPass).size() > 0) {
            return true;
        }
        return false;
    }

    public String getErrorVerifyCodeWhenEmpty() {

        WebElement txtErrorCode = this.getDynamicElement(lcErrorCode);
        return txtErrorCode.getText();
    }

}
