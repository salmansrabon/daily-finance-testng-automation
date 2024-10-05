package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    //@Test(priority = 1, description = "User registration with all fields")
    public void doRegistration1() throws IOException, ParseException, InterruptedException {
        driver.findElement(By.xpath("//a")).click();
        RegistrationPage registrationPage=new RegistrationPage(driver);
        Faker faker=new Faker();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password="1234";
        String phonenumber="0150"+ Utils.generateRandomId(1000000,9999999);
        String address=faker.address().fullAddress();
        UserModel userModel=new UserModel();
        userModel.setFirstName(firstname);
        userModel.setLastName(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phonenumber);
        userModel.setAddress(address);
        registrationPage.doRegistration(userModel);

        String filePath="./src/test/resources/users.json";
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstname",firstname);
        jsonObject.put("lastname",lastname);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phonenumber",phonenumber);
        jsonObject.put("address",address);

        Utils.addToJsonArray(filePath, jsonObject);

    }
    @Test(priority = 2, description = "User registration with only mandatory fields")
    public void doRegistration2() throws IOException, ParseException, InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/login");
        driver.findElement(By.xpath("//a")).click();
        RegistrationPage registrationPage=new RegistrationPage(driver);
        Faker faker=new Faker();
        String firstname=faker.name().firstName();
        String email=faker.internet().emailAddress();
        String password="1234";
        String phonenumber="0150"+ Utils.generateRandomId(1000000,9999999);
        UserModel userModel=new UserModel();
        userModel.setFirstName(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phonenumber);
        registrationPage.doRegistration(userModel);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successMessage= driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successMessage);

        String filePath="./src/test/resources/users.json";
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstname",firstname);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phonenumber",phonenumber);

        Utils.addToJsonArray(filePath, jsonObject);


    }
}
