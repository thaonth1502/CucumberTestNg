package thaonth7.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import thaonth7.common.BaseTest;
import thaonth7.constants.ConfigData;
import thaonth7.helpers.CaptureHelper;
import thaonth7.keywords.WebUI;

public class StepsLoginCRM {
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        BaseTest.createDriver(ConfigData.BROWSER);
        WebUI.openURL(ConfigData.URL);
    }

    @When("I enter my username and my password")
    public void iEnterMyUsernameAndMyPassword() {
        WebUI.setText(By.xpath("//input[@id='email']"), ConfigData.EMAIL);
        WebUI.setText(By.xpath("//input[@id='password']"), ConfigData.PASSWORD);
    }

    @And("I click the Login button")
    public void iClickTheLoginButton() {
        WebUI.clickElement(By.xpath("//button[normalize-space()='Login']"));
    }

    @Then("I should be taken to the Dashboard page")
    public void iShouldBeTakenToTheDashboardPage() {
        CaptureHelper.screenshot("dashboardPage");
    }

    @And("I should see the {string} menu")
    public void iShouldSeeTheMenu(String arg0) {
    }
}
