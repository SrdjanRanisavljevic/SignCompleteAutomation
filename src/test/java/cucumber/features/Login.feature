Feature: Login features

  Background:
    Given User is in Launcher Screen
    And User selects Log In


  Scenario:[first uninstall] Verify elements from login screen
    Given The elements from Login screen are displayed


  Scenario: first uninstall] Verify that user can login with valid credentials
    Given User enters a registered e-mail address
    And User clicks on proceed with Login
    And User clicks Check E-mail from Magic Link
    And User unlocks the app from received e-mail
    And User skips tutorial
    Then User is in Home View


