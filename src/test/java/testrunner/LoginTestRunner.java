package testrunner;

import config.Setup;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class LoginTestRunner  extends Setup {
    @Test(description = "Admin logs in successfully")
    public void doLogin() throws IOException, InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),\"Admin Dashboard\")]")));
    }


}
