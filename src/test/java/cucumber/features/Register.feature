Feature: Register features

  Background:
    Given User is in Launcher Screen
    And User selects Create Account


  Scenario: [first uninstall] Register a new user using magic link
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    When User clicks on E-mail Me Magic Link
    And User clicks Check E-mail from Magic Link
    And User validates that the email was received
    And User unlocks the app from received e-mail
    And User skips tutorial
    Then User is in Home View


  Scenario Outline:  [first uninstall] User is in Home Screen after a successful Register even when interacting with the app
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    When User clicks on E-mail Me Magic Link
    And User clicks Check E-mail from Magic Link
    And User validates that the email was received
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


  Scenario: [first uninstall] Register a too young user
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters a birth date from a recent date
    Then User is notified that he is too young to register


  Scenario: [first uninstall] Trying to register an already registered user and continue with Login flow
    Given User enters an already registered e-mail address
    Then User is in Check E-mail Screen
    And User is recognized as to be already registered
    When User clicks Check E-mail from Magic Link
    And User validates that the email was received
    And User unlocks the app from received e-mail
    And User skips tutorial
    Then User is in Home View


  Scenario: [first uninstall] Information is being kept when user navigates back from Register screens
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    When User clicks back from E-mail Me Magic Link
    And Validates the name displayed in SetUpProfile screen
    And User goes back from display name
    And Validates the selected special offers from Consents screen
    And User goes back from Consents
    And User validates the displayed date of birth
    And User goes back from birth day screen
    And Validates that a country is still selected
    When User goes back from Country Selection screen
    Then User validates the email displayed in Email Address screen


  Scenario: [first uninstall] Register a new user using verification code
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    When User clicks on E-mail Me Magic Link
    And User validates that the email was received
    When User clicks on Enter Code
    And User enters the verification code
    And User skips tutorial
    Then User is in Home View


  Scenario: [first uninstall] Validate that register elements are displayed in relevant screens
    Given User is in E-mail Address view
    And User enters a valid e-mail address
    And Proceeds to Country Selection view
    And User selects a country from the list
    And User is in Birthday Selection view
    And User enters birth date
    When User proceeds to Consents
    And User is in Set Up Profile view
    And User enters a display name
    And User proceeds to Get Magic Link view
    When User clicks on E-mail Me Magic Link
    Then User is in Check Email link


  Scenario: Information is being kept if the user puts the app in background
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    And User clicks back from E-mail Me Magic Link
    When User puts the app in the background
    And User open the app
    And Validates the name displayed in SetUpProfile screen
    And User goes back from display name
    And Validates the selected special offers from Consents screen
    And User goes back from Consents
    And User validates the displayed date of birth
    And User goes back from birth day screen
    And Validates that a country is still selected
    When User goes back from Country Selection screen
    Then User validates the email displayed in Email Address screen


  Scenario: [first uninstall] Information is not kept when user closes the app and relaunches it
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    And User clicks back from E-mail Me Magic Link
    When User closes the app
    And User open the app
    Then User is in Launcher Screen


  Scenario: Verify that user cannot Register with an invalid email address
    Given User enters invalid email in the email field

      | tccc.janegmail.com    |
      | tccc.janedoe@gmailcom |
      | tccc.janedoe          |
      |                       |


  Scenario: User is able to navigate to Privacy Policy from Consents View
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    When User is in Consents View
    And User clicks on Privacy Notice link
    Then User is in Privacy Policy View


  Scenario: User can navigate to Privacy Policy and Terms of Service from GetMagicLink View
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    When User proceeds with Consents
    And User enters a display name
    And User is in GetMagicLink View
    And User clicks on Privacy Policy link
    And User is in Privacy Policy View
    But User clicks on X button
    And User is in GetMagicLink View
    When User clicks on Terms of Service
    Then User is in Terms of Service View

  Scenario: Set Up Profile view is displayed after user selects a date that gives him minimum age allowed for the selected country
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date for minimum age
    Then User is in Set Up Profile view

  Scenario: [first uninstall] Verify that user can request a new magic link
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    And User clicks on E-mail Me Magic Link
    And User clicks on Send Link Again
    And User receives a new magic link notification
    And User validates that the email was received
    And User unlocks the app from received e-mail
    And User skips tutorial
    Then User is in Home View

  Scenario: [first uninstall] Verify that an error message is displayed when opening a wrong email with the magic link
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    And User clicks on E-mail Me Magic Link
    And User clicks Check E-mail from Magic Link
    When User tries to unlock the app from an old received e-mail
    Then An error message is displayed when unlocking the app from an old received e-mail

# SRDJAN ADDED SCENARIOS

  Scenario Outline: [first uninstall] User cannot register if the age is under the age limit
    Given User enters a valid e-mail address
    And User selects a "<country>" in the "country" field
    And User selects that he is "<1 year below the lower age limit>" years old - user is in the "1 year below the lower age limit"
    Then User is notified that he is too young to register
    Examples:
      | country          | 1 year below the lower age limit  |
      | Austria          | 13               |
      | Belarus          | 11               |
      | Cyprus           | 12               |
      | Czech Republic   | 12               |
      | Estonia          | 11               |
      | Greece           | 12               |
      | Latvia           | 11               |
      | Lithuania        | 11               |
      | Malta            | 12               |
      | Moldova          | 13               |
      | Russia           | 11               |
      | Serbia           | 12               |
      | Switzerland      | 12               |
      | Ukraine          | 13               |

  Scenario Outline: [first uninstall] User is forwarded to Set Up Screen after entering minimum age needed for registration
    Given User enters a valid e-mail address
    And User selects a "<country>" in the "country" field
    And User selects that he is "<lower age limit>" years old - user is in the "lower age limit"
    Then User is in Set Up Profile view
    Examples:
      | country          | lower age limit  |
      | Austria          | 14               |
      | Belarus          | 12               |
      | Cyprus           | 13               |
      | Czech Republic   | 13               |
      | Estonia          | 12               |
      | Greece           | 13               |
      | Latvia           | 12               |
      | Lithuania        | 12               |
      | Malta            | 13               |
      | Moldova          | 14               |
      | Russia           | 12               |
      | Serbia           | 13               |
      | Switzerland      | 13               |
      | Ukraine          | 14               |


  Scenario Outline: [first uninstall] User is a year below the age needed for privacy consents and is forwarded to Set Up Profile Screen
    Given User enters a valid e-mail address
    And User selects a "<country>" in the "country" field
    And User selects that he is "<1 year below the age needed for privacy consents>" years old - user is in the "1 year below the age needed for privacy consents"
    Then User is in Set Up Profile view
    Examples:
      | country          | 1 year below the age needed for privacy consents  |
      | Austria          | 15               |
      | Belarus          | 17               |
      | Cyprus           | 17               |
      | Czech Republic   | 17               |
      | Estonia          | 17               |
      | Greece           | 17               |
      | Latvia           | 17               |
      | Lithuania        | 17               |
      | Malta            | 16               |
      | Moldova          | 17               |
      | Russia           | 17               |
      | Serbia           | 17               |
      | Switzerland      | 15               |
      | Ukraine          | 17               |

  Scenario Outline: [first uninstall] User enters minimum age needed for privacy consents
    Given User enters a valid e-mail address
    And User selects a "<country>" in the "country" field
    And User selects that he is "<minimum age for privacy consents>" years old - user is in the "minimum age for privacy consents"
    Then User is in Consents View
    Examples:
      | country          | minimum age for privacy consents  |
      | Austria          | 16               |
      | Belarus          | 18               |
      | Cyprus           | 18               |
      | Czech Republic   | 18               |
      | Estonia          | 18               |
      | Greece           | 18               |
      | Latvia           | 18               |
      | Lithuania        | 18               |
      | Malta            | 17               |
      | Moldova          | 18               |
      | Russia           | 18               |
      | Serbia           | 18               |
      | Switzerland      | 16               |
      | Ukraine          | 18               |