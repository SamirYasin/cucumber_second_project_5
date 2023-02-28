package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SmartBearBasePage;
import utils.Driver;

public class SmartBearSteps {


    WebDriver driver;
    SmartBearBasePage smartBearBasePage;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
        smartBearBasePage = new SmartBearBasePage();

    }

    @Given("user is on {string}")
    public void user_is_on(String url) {
        driver.get(url);
    }
    @When("user enters username as {string}")
    public void user_enters_username_as(String string) {
        smartBearBasePage.usernameInputBox.sendKeys(string);
    }

    @When("user enters password as {string}")
    public void user_enters_password_as(String string) {
        smartBearBasePage.passwordInputBox.sendKeys(string);
    }

    @When("user clicks on Login button")
    public void user_clicks_on_Login_button() {
        smartBearBasePage.loginBtn.click();
    }

    @Then("user should see {string} message")
    public void user_should_see_message(String string) {
        Assert.assertEquals(string, smartBearBasePage.invalidLoginMessage.getText())
    }

    @Then("user should be routed to {string}")
    public void user_should_be_routed_to(String string) {
        Assert.assertEquals(string, driver.getCurrentUrl());
    }

    @Then("validate below menu items are displayed")
    public void validate_below_menu_items_are_displayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearBasePage.);
        }
}

    @When("user clicks on {string} button")
    public void userClicksOnButton(String arg0) {

    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {

    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {

    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String arg0) {

    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String arg0) {
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int arg0) {
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String arg0) {
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String arg0) {
    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String arg0) {
    }
}
