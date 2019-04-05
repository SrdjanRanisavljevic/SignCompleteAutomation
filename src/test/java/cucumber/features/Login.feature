Feature: Login features

  Background:
    Given User is in Launcher Screen
    And User selects Log In


  Scenario:[first uninstall] Verify elements from login screen
    Given The elements from Login screen are displayed


  Scenario: [first uninstall] Verify that user can login with valid credentials
    Given User enters a registered e-mail address
    And User clicks on proceed with Login
    And User clicks Check E-mail from Magic Link
    And User validates that the email was received
    And User unlocks the app from received e-mail
    And User skips tutorial
    Then User is in Home View


  Scenario: [first uninstall Verify that user can retype the email address after unsuccessful login
    Given User enters an unregistered e-mail address
    And User clicks on proceed with Login
    When User clicks on try again
    Then User is able to retype the e-mail address


  Scenario: [first uninstall] Verify that user cannot login with unregistered email
    Given User enters an unregistered e-mail address
    And User clicks on proceed with Login
    Then Wrong email message is displayed to the user


  Scenario: [first uninstall] Verify that user can access Register field from Login after unsuccessful login
    Given User enters an unregistered e-mail address
    And User clicks on proceed with Login
    When User clicks on Register button from from Pop-up message
    Then User is in E-mail Address screen from Create Account

  Scenario: Verify that email address is kept when the app is put in the background
    Given User enters a registered e-mail address
    And User puts the app in the background
    Then User opens the app and checks the email address is kept


  Scenario: Verify that email address is not kept when the app is closed and relaunched
    Given User enters a registered e-mail address
    When User closes the app
    And User open the app
    And User is in Launcher Screen
    And User selects Log In
    Then User checks the email address to not be kept


  Scenario: Verify that user cannot login with incorrect email format
    Given User enters an invalid email and cannot proceed further
      | tccc.johndoegmail.com |
      | tccc.johndoe@gmailcom |
      | tccc.johndoe          |
      |                       |


  Scenario Outline: [first uninstall] Verify that user is in Home Screen after a successful Login
    Given User enters a registered e-mail address
    And User clicks on proceed with Login
    And User clicks Check E-mail from Magic Link
    And User unlocks the app from received e-mail
    And User skips tutorial
    And User is in Home View
    And User "<performs an action>"
    And User open the app
    Then User is in "<expected screen>"
    Examples:
      | performs an action             | expected screen |
      | puts the app in the background | Home Screen     |
      | closes the app                 | Home Screen     |


  Scenario: [first uninstall] Verify that an error message is displayed when opening a wrong email with the magic link
    Given User enters a registered e-mail address
    And User clicks on proceed with Login
    And User clicks Check E-mail from Magic Link
    When User tries to unlock the app from an old received e-mail
    Then An error message is displayed when unlocking the app from an old received e-mail


  Scenario: [first uninstall] Verify that user can request a new magic link
    Given User enters a registered e-mail address
    And User clicks on proceed with Login
    And User clicks on Send Link Again
    And User receives a new magic link notification
    And User unlocks the app from received e-mail
    And User skips tutorial
    Then User is in Home View