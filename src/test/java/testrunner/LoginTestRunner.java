package testrunner;

import config.Setup;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginTestRunner  extends Setup {
    @Test(priority = 1, description = "Admin logs in successfully")
    public void doLogin() throws IOException, InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),\"Admin Dashboard\")]")));
    }
    @Test(priority = 2)
    public void getAllData() {
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        try (FileWriter csvWriter = new FileWriter("./src/test/resources/users_data.csv")) {
            for (WebElement row : allRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                StringBuilder rowData = new StringBuilder();
                for (WebElement cell : cells) {
                    // Append cell data to the rowData and separate with commas
                    rowData.append(cell.getText()).append(",");
                }
                // Remove the last comma from the end of the row data
                if (rowData.length() > 0) {
                    rowData.setLength(rowData.length() - 1);
                }
                // Write the row data to the CSV file and start a new line
                csvWriter.append(rowData.toString()).append("\n");
            }

            System.out.println("Data successfully written to CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
