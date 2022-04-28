import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class SeleniumTest {
    public WebDriver driver;
    String adblock = "/Users/Lenovo/Downloads/adblock_plus-3.11.4-an+fx.xpi";
    File adblockfile = new File(adblock);
    FirefoxProfile profile = new FirefoxProfile();

    @Before
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        profile.addExtension(adblockfile);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    // Test login and logout
    // Fill simple form and send
    // Logout
    public void testLoginAndLogout() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getFooterText().contains("contact@vndb.org"));

        AfterLoginPage afterLoginPage = mainPage.login("cola123","2BjdsDzw9nYnFWM");
        String bodyText = afterLoginPage.getBodyText();
        Assert.assertTrue(bodyText.contains("cola123"));
        Assert.assertTrue(bodyText.contains("My Profile"));
        mainPage = afterLoginPage.logout();
        bodyText = mainPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Login"));
    }

    @Test
    // Fill input (text,radio,check...)
    // Static Page test
    public void testSearch() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getFooterText().contains("contact@vndb.org"));

        SearchResultPage searchResultPage = mainPage.search("Kanon");
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Browse visual novels"));

    }

    @Test
    // Form sending with user
    public void testSearchAfterLogin() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getFooterText().contains("contact@vndb.org"));

        AfterLoginPage afterLoginPage = mainPage.login("cola123","2BjdsDzw9nYnFWM");

        SearchResultPage searchResultPage = mainPage.search("Kanon");
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Browse visual novels"));
    }

    @Test
    // Multiple page test from array (easily extendable static page tests)
    public void testSearchArray() {
        String[] searchQueries={"Giniro","AIR","narcissu"};
        for(String searchQuery : searchQueries) {
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            Assert.assertTrue(bodyText.contains("Browse visual novels"));
        }
    }

    @Test
    // Filling or reading textarea content
    public void testEditProfileCss() {
        MainPage mainPage = new MainPage(this.driver);
        AfterLoginPage afterLoginPage = mainPage.login("cola123","2BjdsDzw9nYnFWM");
        ProfilePage profilePage = afterLoginPage.myProfile();
        profilePage = profilePage.changeCSS("#mainbox {color:red;}");
        String bodyText = profilePage.getBodyText();
        Assert.assertTrue(bodyText.contains("cola123"));
    }

    @Test
    // Filling or reading drop-down
    public void testChangeSkin() {
        MainPage mainPage = new MainPage(this.driver);
        AfterLoginPage afterLoginPage = mainPage.login("cola123","2BjdsDzw9nYnFWM");
        ProfilePage profilePage = afterLoginPage.myProfile();
        profilePage = profilePage.changeSkin("Ever17 (bondi blue)");
        String bodyText = profilePage.getBodyText();
        Assert.assertTrue(bodyText.contains("cola123"));
    }

    @Test
    // Filling or reading Radio button
    public void testRadioButton() {
        MainPage mainPage = new MainPage(this.driver);
        AfterLoginPage afterLoginPage = mainPage.login("cola123","2BjdsDzw9nYnFWM");
        ProfilePage profilePage = afterLoginPage.myProfile();
        profilePage = profilePage.clickChangeUsernameRadioButton();
        String bodyText = profilePage.getBodyText();
        Assert.assertTrue(bodyText.contains("New username"));
    }

    @Test
    // Reading the page title
    public void testReadTitle() {
        MainPage mainPage = new MainPage(this.driver);
        String title = mainPage.getMainPageTitle();
        Assert.assertEquals(title, "The Visual Novel Database | vndb");
    }

    @Test
    // Back to previous page
    public void testBackToPreviousPage() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("Kanon");
        mainPage = searchResultPage.backToIndexPage();
        String title = mainPage.getMainPageTitle();
        Assert.assertEquals(title, "The Visual Novel Database | vndb");
    }

    @Test
    // Register
    // E-mail checking (eg. Registration with activation e-mail)
    // Note!!!!!!: You can only register twice for the same IP in 24 hours
    public void testRegister() {
        MainPage mainPage = new MainPage(this.driver);
        AfterLoginPage registered = mainPage.register("tigerhyqgr23","2BjdsDzw9nYnFWM");
        String bodyText = registered.getBodyText();
        Assert.assertTrue(bodyText.contains("tigerhyqgr23"));
    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
