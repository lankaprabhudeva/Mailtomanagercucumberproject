Feature: Update Naukri Profile

  Background:
    Given I launch the browser and open the Naukri site

  Scenario: Login and update resume
    When I login with valid credentials
    And I navigate to the update profile page
    And I scroll down to the resume section
    And I upload my latest resume
    Then I close the browser
