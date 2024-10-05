package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AdminProfilePage;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class AdminProfileTestRunner extends Setup {
    @BeforeTest
    public void setAuth() throws IOException, InterruptedException, ParseException {
        Utils.setAuth(driver);
    }
    @Test
    public void uploadImage() throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/admin");
        AdminProfilePage userProfilePage=new AdminProfilePage(driver);
        userProfilePage.uploadImage();
    }
}
