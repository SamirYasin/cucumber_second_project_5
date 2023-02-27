package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import utils.Driver;

public class BaseSteps {


    WebDriver driver;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
    }

    @Given("user is on {string}")
    public void user_is_on(String url) {
        // Write code here that turns the phrase above into concrete actions
        driver.get(url);
    }
}
