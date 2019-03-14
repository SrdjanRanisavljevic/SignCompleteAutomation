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



