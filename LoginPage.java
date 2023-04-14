package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageObjectPage {
    public WebDriver driver;
    @FindBy(xpath = "//div[@defaultactive]/span[1]")
    public WebElement optionAdmin;
    @FindBy(xpath = "//div[@defaultactive]/span[2]")
    public WebElement optionStaff;
    @FindBy(css = "button[class*=\"select__value\"]")
    public WebElement dpxCountryID;
    @FindBy(xpath = "//div[contains(@class,\"optionListWrapper\")]//div[text()=\"Vietnam\"]")
    public WebElement optionCountry;
    @FindBy(css = "input[name=\"username\"]")
    public WebElement txtEmail;
    @FindBy(css = "input[name=\"password\"]")
    public WebElement txtPass;
    @FindBy(xpath = "//button[contains(@class,\"gs-button\")]")
    public WebElement btnLogin;
    @FindBy(xpath = "//button[contains(@class,\"btnSubmitFaceBook\")]")
    public WebElement btnLoginFB;
    @FindBy(css = "#email")
    public WebElement txbEmailFB;
    @FindBy(css = "#pass")
    public WebElement txbPassFB;
    @FindBy(css = "#loginbutton")
    public WebElement btnSubmitFB;
    By lcErrorEmail = By.xpath("//div[contains(@class,\"uik-form-input-group__vertical\")]/div[2]/div");
    By lcErrorPass = By.xpath("//div[contains(@class,\"uik-form-input-group__vertical\")]/div[3]/div/div");
    By lcErrorEmailPass = By.xpath("//div[@type=\"error\"]");
    By lcErrorFB = By.id("error_box");


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getErrorEmail() {
        WebElement txtErrorEmail = this.getDynamicElement(lcErrorEmail);
        return txtErrorEmail.getText();
    }

    public String getErrorPass() {
        WebElement txtErrorPass = this.getDynamicElement(lcErrorPass);
        return txtErrorPass.getText();
    }

    public String getErrorEmailPassWhenInputIncorrectData() {
        WebElement txtErrorEmailPass = this.getDynamicElement(lcErrorEmailPass);
        return txtErrorEmailPass.getText();
    }
    public String getErrorEmailPassWhenLoginWithIncorrectAccount() {
        WebElement txtErrorFB = this.getDynamicElement(lcErrorFB);
        return txtErrorFB.getText();
    }


}
