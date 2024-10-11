package thaonth7.common;

import io.cucumber.java.en.And;

public class CommonStepsDefinitions {
    @And("I should see notification message")
    public void iShouldSeeNotificationMessage() {
        BaseTest.closeDriver();
    }
}
