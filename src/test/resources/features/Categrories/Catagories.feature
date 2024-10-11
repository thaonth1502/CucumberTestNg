Feature: Manage Categories

  Background:
    Given User logged in the CMS system successfully by "Admin" role

  Scenario: Add new Category
    Given User is on the Category page
    When User click on Add New category button
    And User input valid value into required fields
    And User click on Save button
    Then The message "Category has been inserted successfully" displayed
