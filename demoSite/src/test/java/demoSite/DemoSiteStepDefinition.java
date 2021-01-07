package demoSite;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DemoSiteStepDefinition
{
    private WebDriver driver;

    // Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
    public static ChromeOptions chromeCfg()
    {
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions cOptions = new ChromeOptions();

        // Settings
        prefs.put("profile.default_content_setting_values.cookies", 2);
        prefs.put("network.cookie.cookieBehavior", 2);
        prefs.put("profile.block_third_party_cookies", true);

        // Create ChromeOptions to disable Cookies pop-up
        cOptions.setExperimentalOption("prefs", prefs);

        return cOptions;
    }

    @Before
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\cucumberExamples\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver(chromeCfg());
        driver.manage().window().setSize(new Dimension(1746, 758));
    }

    @Given("^the correct web address$")
    public void theCorrectWebAddress()
    {
        driver.get("http://thedemosite.co.uk/");
    }

    @When("^I register an account$")
    public void iRegisterAnAccount()
    {
        driver.findElement(By.linkText("3. Add a User")).click();
        driver.findElement(By.name("username")).sendKeys("guest");
        driver.findElement(By.name("password")).sendKeys("guest");
        driver.findElement(By.name("FormsButton2")).click();
    }

    @Then("^a new account is created$")
    public void aNewAccountIsCreated()
    {
        assertEquals(driver.findElement(By.cssSelector("blockquote:nth-child(2) > blockquote")).getText(), "The username: guest\nThe password: guest");
    }

    @When("^I attempt to login$")
    public void iAttemptToLogin()
    {
        driver.findElement(By.linkText("4. Login")).click();
        driver.findElement(By.name("username")).sendKeys("guest");
        driver.findElement(By.name("password")).sendKeys("guest");
        driver.findElement(By.name("FormsButton2")).click();
    }

    @Then("^I am logged into my account$")
    public void iAmLoggedIntoMyAccount()
    {
        assertEquals(driver.findElement(By.cssSelector("center > b")).getText(), "**Successful Login**");
    }

    @After
    public void tearDown()
    {
        driver.close();
    }
}
