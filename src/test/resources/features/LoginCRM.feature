Feature: Login to CRM
  As a user, I want to be able to log into the CMR system
  So that I can manage customer information

  Scenario: Successful login
    Given I am on the login page
    When I enter my username and my password
    And I click the Login button
    Then I should be taken to the Dashboard page
    And I should see the "Customer" menu