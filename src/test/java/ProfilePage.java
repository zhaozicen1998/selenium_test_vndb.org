import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends PageBase {

    private By cssBy = By.id("css");
    private By profileSubmitBy = By.xpath("//div[@class='mainbox'][2]//input[@class='submit']");
    private By changeSkinBy = By.id("skin");
    private By changeUsernameRadioBy = By.xpath("/html/body/div[4]/form/fieldset/div[1]/table/tr[2]/td[2]/label/input");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Custom css
    public ProfilePage changeCSS(String css) {
        this.waitAndReturnElement(cssBy).sendKeys(css);
        this.waitAndReturnElement(profileSubmitBy).click();
        this.waitAndReturnElement(profileSubmitBy);
        return new ProfilePage(this.driver);
    }

    // Change skin
    public ProfilePage changeSkin(String skinName) {
        Select select = new Select(this.waitAndReturnElement(changeSkinBy));
        select.selectByVisibleText(skinName);
        this.waitAndReturnElement(profileSubmitBy).click();
        this.waitAndReturnElement(profileSubmitBy);
        return new ProfilePage(this.driver);
    }

    // Click on the "change username" radio box
    public ProfilePage clickChangeUsernameRadioButton() {
        this.waitAndReturnElement(changeUsernameRadioBy).click();
        return new ProfilePage(this.driver);
    }

}
