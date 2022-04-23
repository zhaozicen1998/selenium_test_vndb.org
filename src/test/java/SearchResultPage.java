import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class SearchResultPage extends PageBase {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    // 后退
    public MainPage backToIndexPage() {
        this.driver.navigate().back();
        return new MainPage(this.driver);
    }
           
}
