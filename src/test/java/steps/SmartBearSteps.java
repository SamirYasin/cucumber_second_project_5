package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import javafx.scene.control.Tab;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.SmartBearBasePage;
import utils.Driver;
import utils.DropdownHandler;
import utils.TableHandler;
import utils.Waiter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmartBearSteps {


    WebDriver driver;
    SmartBearBasePage smartBearBasePage;

    WebElement table;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
        smartBearBasePage = new SmartBearBasePage();
        table = smartBearBasePage.table;
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
        Assert.assertEquals(string, smartBearBasePage.invalidLoginMessage.getText());
    }

    @Then("user should be routed to {string}")
    public void user_should_be_routed_to(String string) {
        Assert.assertEquals(string, driver.getCurrentUrl());
    }

    @Then("validate below menu items are displayed")
    public void validate_below_menu_items_are_displayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearBasePage.menuItemsList.get(i).getText());
        }
}

    @When("user clicks on {string} button")
    public void userClicksOnButton(String string) {
        smartBearBasePage.selectBtnByVisibleText(string);
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for(int i = 1; i < TableHandler.getRowCount(table); i++) {
            Assert.assertTrue(TableHandler.getCheckbox(table, i, 0).isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (int i = 1; i < TableHandler.getRowCount(table); i++) {
            Assert.assertFalse(TableHandler.getCheckbox(table, i, 0).isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String string) {
        switch (string) {
            case "Order":
                smartBearBasePage.menuItemsList.get(2).click();
                break;
            case "View all orders":
                smartBearBasePage.menuItemsList.get(0).click();
                break;
            default:
                throw new NotFoundException();
        }
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String string) {
        DropdownHandler.clickOnDropdownOption(smartBearBasePage.productDropdown
                ,smartBearBasePage.productDropdownOptions, string);
    }
    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int num) {
        smartBearBasePage.productQuantityInput.sendKeys(String.valueOf(num));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        smartBearBasePage.customerNameInput.sendKeys("John Doe");
        smartBearBasePage.customerStreetInput.sendKeys("123 Main st");
        smartBearBasePage.customerCityInput.sendKeys("Chicago");
        smartBearBasePage.customerStateInput.sendKeys("IL");
        smartBearBasePage.customerZipCodeInput.sendKeys("60451");
        }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBearBasePage.masterCard.click();
        smartBearBasePage.customerCardExpInput.sendKeys("12/24");
        smartBearBasePage.customerCardNumberInput.sendKeys("123456789");
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String string) {
        Assert.assertTrue(smartBearBasePage.subHeading.isDisplayed());
        Assert.assertEquals(string , smartBearBasePage.subHeading.getText());
        Assert.assertEquals("John Doe", TableHandler.getCell(table, 1, 1).getText());
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
        Assert.assertEquals("John Doe", TableHandler.getCell(table, 1, 1).getText());

        Assert.assertEquals("FamilyAlbum", TableHandler.getCell(table, 1, 2).getText());
        Assert.assertEquals("2", TableHandler.getCell(table, 1, 3).getText());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String today = formatter.format(date);
        Assert.assertEquals(today, TableHandler.getCell(table, 1, 4).getText());

        Assert.assertEquals("123 Main st", TableHandler.getCell(table, 1, 5).getText());
        Assert.assertEquals("Chicago", TableHandler.getCell(table, 1, 6).getText());
        Assert.assertEquals("IL", TableHandler.getCell(table, 1, 7).getText());
        Assert.assertEquals("60451", TableHandler.getCell(table, 1, 8).getText());

        Assert.assertEquals("MasterCard", TableHandler.getCell(table, 1, 9).getText());
        Assert.assertEquals("123456789", TableHandler.getCell(table, 1, 10).getText());
        Assert.assertEquals("12/24", TableHandler.getCell(table, 1, 11).getText());
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String string) {
        Assert.assertTrue(smartBearBasePage.subHeading.isDisplayed());
        Assert.assertEquals(string, smartBearBasePage.subHeading.getText());
    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String string) {
        Assert.assertEquals(string, smartBearBasePage.emptyOrderMessage.getText());
    }
}
