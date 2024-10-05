package config;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;


public class Setup {
    public WebDriver driver;
    public static final String resourcePath="./src/test/resources";
    @BeforeTest
    public void setup() throws ParseException, InterruptedException, IOException {
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://dailyfinance.roadtocareer.net");

    }
    //@AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
