package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import static config.Setup.resourcePath;

public class Utils {
    public static void getAuth(WebDriver driver) throws IOException {
        //wait until the authToken is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')") != null);

        //get the authToken from the localstorage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken');");
        System.out.println("Auth Token Retrieved: " + authToken);

        // Save the auth token to a localstorage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken", authToken);
        FileWriter writer=new FileWriter(resourcePath+"/localstorage.json");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }
    public static void setAuth(WebDriver driver) throws ParseException, InterruptedException, IOException {
        JSONParser jsonParser=new JSONParser();
        JSONObject authObj= (JSONObject) jsonParser.parse(new FileReader( resourcePath+"/localstorage.json"));
        String authToken= authObj.get("authToken").toString();
        System.out.println(authToken);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", authToken);
        Thread.sleep(2000);
    }
    public static int generateRandomId(int min, int max){
        double randomId= Math.random()*(max-min)+min;
        return (int) randomId;
    }
    public static void addToJsonArray(String filePath, JSONObject jsonObject) throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(filePath));
        jsonArray.add(jsonObject);
        FileWriter fileWriter=new FileWriter(filePath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
    public static void main(String[] args) {
        int id=generateRandomId(100,300);
        System.out.println(id);
    }
}
