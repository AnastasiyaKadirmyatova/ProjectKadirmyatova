import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class DynamicControlsTest {

    @Test
    public void verifyDynamicControls() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        assertNotNull(driver.findElement(By.xpath("//input")).getAttribute("disabled"), "Атрибут 'disabled' отсутствует.");
        driver.findElement(By.xpath("//button[text()='Enable']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertNull(driver.findElement(By.xpath("//input")).getAttribute("disabled"), "Атрибут 'disabled'.");

        driver.quit();
    }
}
