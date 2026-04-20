import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

    @Test
    public void dropdownTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));

        List<WebElement> optionsSelect = select.getOptions();
        Assert.assertTrue(optionsSelect.size() > 0, "Опции в dropdown отсутствуют");

        String firstOptionValue = optionsSelect.get(1).getText();
        select.selectByIndex(1); // Выбираем первый элемент
        Assert.assertEquals(firstOptionValue, select.getFirstSelectedOption().getText());

        String secondOptionValue = optionsSelect.get(2).getText();
        select.selectByIndex(2); // Выбираем первый элемент
        Assert.assertEquals(secondOptionValue, select.getFirstSelectedOption().getText());
    }
}
