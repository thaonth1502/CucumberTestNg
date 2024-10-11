package thaonth7.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import thaonth7.common.BaseTest;
import thaonth7.pages.CategoriesPage;
import thaonth7.pages.CommonPage;
import thaonth7.pages.LoginCMSPage;

public class StepCategories {
     LoginCMSPage loginCMSPage;
     CommonPage commonPage;
     CategoriesPage categoriesPage;

    @Given("User logged in the CMS system successfully by {string} role")
    public void userLoggedInTheCMSSystemSuccessfullyByRole(String arg0) {
        loginCMSPage = new LoginCMSPage();
        commonPage = loginCMSPage.loginAdminRole();
    }

    @Given("User is on the Category page")
    public void userIsOnTheCategoryPage() {
        commonPage.clickMenuProducts();
       categoriesPage = commonPage.openCategoriesPage();
    }

    @When("User click on Add New category button")
    public void userClickOnAddNewCategoryButton() {
        categoriesPage.clickAddNewCategoryButton();
    }

    @And("User input valid value into required fields")
    public void userInputValidValueIntoRequiredFields() {
        categoriesPage.inputValidValueIntoRequiredFields();
    }

    @And("User click on Save button")
    public void userClickOnSaveButton() {
        categoriesPage.clickSaveButton();
    }

    @Then("The message {string} displayed")
    public void theMessageDisplayed(String message) {
        categoriesPage.checkMessageSuccessful(message);
        BaseTest.closeDriver();
    }


}
