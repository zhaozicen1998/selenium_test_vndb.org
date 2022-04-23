import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class AfterLoginPage extends PageBase {

    private By logoutBy = By.className("logout");
    private By loginLinkBy = By.xpath("//div//a[@href='/u/login?ref=%2F']");
    private By myProfileLinkBy = By.xpath("/html/body/div[3]/div[3]/div/a[1]");
    private By profileSubmitBy = By.xpath("//div[@class='mainbox'][2]//input[@class='submit']");


    public AfterLoginPage(WebDriver driver) {
        super(driver);
    }

    // Logout
    public MainPage logout() {
        this.waitAndReturnElement(logoutBy).click();
        this.waitAndReturnElement(loginLinkBy);
        return new MainPage(this.driver);
    }

    // Show profile
    public ProfilePage myProfile() {
        this.waitAndReturnElement(myProfileLinkBy).click();
        this.waitAndReturnElement(profileSubmitBy);
        return new ProfilePage(this.driver);
    }

}
