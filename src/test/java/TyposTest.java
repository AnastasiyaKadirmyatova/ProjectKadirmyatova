import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {

    @Test
    public void verifyTypos() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/typos");

        SoftAssert softAssert = new SoftAssert();
        for (int i = 1; i <= 10; i++) {
            softAssert.assertEquals(driver.findElement(By.xpath("//p[2]")).getText(),
                    "Sometimes you'll see a typo, other times you won't.");
            driver.navigate().refresh();
        }
        driver.quit();
        softAssert.assertAll();
    }
}
