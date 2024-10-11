Feature: Login CMS

  @success
  Scenario: Login success
    Given User is on the login page
    When User enter email "admin@example.com" and Password "123456"
    And User click on Login button
    Then User redirect to admin page "https://cms.anhtester.com/admin"

  @success
  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User enter email "admin@example.com" and Password "123456"
    And User click on Login button
    Then User should be redirected to the dashboard


  @invalid
  Scenario: Unsuccessful login with invalid credentials
    Given User is on the login page
    When User enter email "admin11@example.com" and Password "12345667"
    And User click on Login button
    Then User should see an error message "Invalid login credentials"


  @invalid
  Scenario: Unsuccessful login with empty username
    Given User is on the login page
    When User enter email "" and Password "123456"
    And User click on Login button
    Then User should see an error message "Please fill out this field." under email field

  @invalid
  Scenario: Unsuccessful login with empty password
    Given User is on the login page
    When User enter email "admin@example.com" and Password ""
    And User click on Login button
    Then User should see an error message "Please fill out this field." under password field

  @invalid
  Scenario: Unsuccessful login with both fields empty
    Given User is on the login page
    When User enter email "" and Password ""
    And User click on Login button
    Then User should see an error message "Please fill out this field." under email field

