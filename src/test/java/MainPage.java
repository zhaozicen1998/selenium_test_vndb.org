import com.beust.ah.A;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;


class MainPage extends PageBase {

    private By footerBy = By.id("footer");
    private By searchBarBy = By.name("sq");
    private By searchPlatformBy = By.id("advsearch_field10");

    private By loginLinkBy = By.xpath("//div//a[@href='/u/login?ref=%2F']");
    private By usernameBarBy = By.id("username");
    private By passwordBarBy = By.id("password");
    private By loginSubmitBy = By.xpath("/html/body/div[4]/form/fieldset/div[2]/fieldset/span/input");
    private By usernameBy = By.xpath("/html/body/div[3]/div[3]/h2/a");

    private By registerLinkBy = By.xpath("/html/body/div[3]/div[3]/div/a[3]");
    private By registerUsernameBy = By.xpath("//*[@id=\"username\"]");
    private By registerEmailBy = By.xpath("//*[@id=\"email\"]");
    private By registerAnswerBy = By.xpath("//*[@id=\"vns\"]");
    private By registerSubmitBy = By.xpath("/html/body/div[4]/form/fieldset/div[2]/fieldset/span/input");
    private By answerBy = By.xpath("/html/body/div[3]/div[4]/div/dl/dd[1]");


    private By emailAddressBy = By.xpath("//*[@id=\"active-mail\"]");
    private By verifyEmailBy = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/a");
    private By verifyEmailLinkBy = By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div/div[3]/a");

    private By registerSetNewPassword = By.xpath("//*[@id=\"newpass1\"]");
    private By registerRepeatPassword = By.xpath("//*[@id=\"newpass2\"]");
    private By setPasswordSubmit = By.xpath("/html/body/div[4]/form/fieldset/div[2]/fieldset/span/input");

    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://vndb.org/");
    }    
    
    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    // Search
    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + Keys.ENTER);
        // Filling or reading drop-down
        this.waitAndReturnElement(searchPlatformBy).click();

        return new SearchResultPage(this.driver);
    }

    // Login
    public AfterLoginPage login(String username, String password) {
        this.waitAndReturnElement(loginLinkBy).click();
        this.waitAndReturnElement(usernameBarBy).sendKeys(username);
        this.waitAndReturnElement(passwordBarBy).sendKeys(password);
        this.waitAndReturnElement(loginSubmitBy).click();
        this.waitAndReturnElement(usernameBy);
        return new AfterLoginPage(this.driver);
    }

    // getTitle
    public String getMainPageTitle() {
        return this.driver.getTitle();
    }

    // Register and E-mail checking
    public AfterLoginPage register(String username, String password) {
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        String mailWindow = driver.getWindowHandle();
        this.driver.get("https://www.linshiyouxiang.net/");
        String email = this.waitAndReturnElement(emailAddressBy).getAttribute("data-clipboard-text");
        driver.switchTo().window(originalWindow);
        this.waitAndReturnElement(registerLinkBy).click();
        this.waitAndReturnElement(registerUsernameBy).sendKeys(username);
        this.waitAndReturnElement(registerEmailBy).sendKeys(email);
        String answer = this.waitAndReturnElement(answerBy).getText();
        this.waitAndReturnElement(registerAnswerBy).sendKeys(answer);
        this.waitAndReturnElement(registerSubmitBy).click();
        driver.switchTo().window(mailWindow);
        this.waitAndReturnElement(verifyEmailBy).click();
        this.waitAndReturnElement(verifyEmailLinkBy).click();
        this.waitAndReturnElement(registerSetNewPassword).sendKeys(password);
        this.waitAndReturnElement(registerRepeatPassword).sendKeys(password);
        this.waitAndReturnElement(setPasswordSubmit).click();
        this.waitAndReturnElement(searchBarBy);
        return new AfterLoginPage(this.driver);
    }


}
