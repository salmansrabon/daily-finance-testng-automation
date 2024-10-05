package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

public class AdminProfilePage {
    @FindBy(css = "[type=button]")
    List<WebElement> buttons;
    @FindBy(className = "upload-input")
    WebElement btnUpload;
    WebDriver driver;
    public AdminProfilePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void uploadImage() throws InterruptedException {
        Thread.sleep(1000);
        buttons.get(1).click(); //click view button
        buttons.get(1).click(); //click edit button
        btnUpload.sendKeys(System.getProperty("user.dir")+"/src/test/resources/salman.jpeg");
        buttons.get(1).click(); //click upload button
        buttons.get(1).click(); //click update button
        //handle alert
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

    }

}
