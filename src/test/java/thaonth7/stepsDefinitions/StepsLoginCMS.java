package thaonth7.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.BeforeClass;
import thaonth7.common.BaseTest;
import thaonth7.constants.ConfigData;
import thaonth7.drivers.DriverManager;
import thaonth7.keywords.WebUI;
import thaonth7.pages.LoginCMSPage;


public class StepsLoginCMS {

    LoginCMSPage loginCMSPage;


    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        BaseTest.createDriver();
        WebUI.openURL(ConfigData.URL);
    }

    @When("User enter email {string} and Password {string}")
    public void userEnterEmailAndPassword(String email, String password) {
        loginCMSPage = new LoginCMSPage();
        loginCMSPage.userEnterEmailAndPassword(email,password);
    }

    @And("User click on Login button")
    public void userClickOnLoginButton() {
        loginCMSPage.clickLoginButton();
    }

    @Then("User redirect to admin page {string}")
    public void userRedirectToAdminPage(String url) {
        WebUI.assertEquals(url, DriverManager.getDriver().getCurrentUrl(), "FAIL! Current url is not map.");
        BaseTest.closeDriver();
    }


    @Then("User should be redirected to the dashboard")
    public void userShouldBeRedirectedToTheDashboard() {
        WebUI.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://cms.anhtester.com/admin", "FAIL! Current url is not map.");
        BaseTest.closeDriver();
    }

    @Then("User should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String message) {
        loginCMSPage.checkErrorMessage(message);
        BaseTest.closeDriver();
    }

    @Then("User should see an error message {string} under email field")
    public void userShouldSeeAnErrorMessageUnderEmailField(String message) {
        loginCMSPage.checkEmailRequired(message);
        BaseTest.closeDriver();
    }

    @Then("User should see an error message {string} under password field")
    public void userShouldSeeAnErrorMessageUnderPasswordField(String message) {
        loginCMSPage.checkPasswordRequired(message);
        BaseTest.closeDriver();
    }

}
