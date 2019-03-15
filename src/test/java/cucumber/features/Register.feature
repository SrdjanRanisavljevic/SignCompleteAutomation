Feature: Register features

  Background:
    Given User is in Launcher Screen
    And User selects Create Account

  Scenario: [first uninstall] Register a new user
    Given User enters a valid e-mail address
    And User selects a country from the list
    And User enters birth date
    And User proceeds with Consents
    And User enters a display name
    When User clicks on E-mail Me Magic Link
    And User clicks Check E-mail from Magic Link
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