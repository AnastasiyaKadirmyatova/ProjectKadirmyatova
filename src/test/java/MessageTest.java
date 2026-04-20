import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class MessageTest {

    @Test
    public void messageTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        driver.findElement(By.xpath("//a[text()='Click here']")).click();

        By locator1 = By.xpath("//div[contains(text(),'Action successful')]");
        By locator2 = By.xpath("//div[contains(text(),'Action unsuccessful, please try again')]");
        boolean result = checkElementPresence(driver, locator1, locator2);
    }

    public boolean checkElementPresence(WebDriver driver, By locator1, By locator2) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Пробуем найти первый элемент
            WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(locator1));
            System.out.println("Первый элемент найден.");
            return true;

        } catch (org.openqa.selenium.TimeoutException e) {
            // Если первый элемент не найден, пробуем найти второй
            try {
                WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(locator2));
                System.out.println("Второй элемент найден.");
                return true;

            } catch (org.openqa.selenium.TimeoutException ex) {
                // Ни один из элементов не найден
                System.out.println("Ни один из элементов не найден.");
                return false;
            }
        }
    }
}
