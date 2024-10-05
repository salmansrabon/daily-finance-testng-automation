package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.io.IOException;

public class LoginPage {
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    WebDriver driver;


    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void doLogin(String email, String password) throws IOException, InterruptedException {
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        Utils.getAuth(driver);

    }

}
