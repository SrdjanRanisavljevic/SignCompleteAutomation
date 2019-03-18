$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/cucumber/features/Register.feature");
formatter.feature({
  "line": 1,
  "name": "Register features",
  "description": "",
  "id": "register-features",
  "keyword": "Feature"
});
formatter.before({
  "duration": 5682875437,
  "status": "passed"
});
formatter.before({
  "duration": 15681103072,
  "status": "passed"
});
formatter.before({
  "duration": 153180724,
  "status": "passed"
});
formatter.before({
  "duration": 38318,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "User is in Launcher Screen",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User selects Create Account",
  "keyword": "And "
});
formatter.match({
  "location": "RegisterSteps.userIsInLauncherScreen()"
});
formatter.result({
  "duration": 2665418022,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectsCreateAccount()"
});
formatter.result({
  "duration": 1794558875,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "[first uninstall] Register a new user using magic link",
  "description": "",
  "id": "register-features;[first-uninstall]-register-a-new-user-using-magic-link",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "User enters a valid e-mail address",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "User selects a country from the list",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "User enters birth date",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "User proceeds with Consents",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "User enters a display name",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "User clicks on E-mail Me Magic Link",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "User clicks Check E-mail from Magic Link",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "User unlocks the app from received e-mail",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "User skips tutorial",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "User is in Home View",
  "keyword": "Then "
});
formatter.match({
  "location": "RegisterSteps.userEntersAValidEMailAddress()"
});
formatter.result({
  "duration": 4921020662,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectACountryFromTheList()"
});
formatter.result({
  "duration": 3157779618,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersBirthDate()"
});
formatter.result({
  "duration": 6126500557,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userProceedsWithConsents()"
});
formatter.result({
  "duration": 11718889604,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersADisplayName()"
});
formatter.result({
  "duration": 4201405231,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksOnEMailMeMagicLink()"
});
formatter.result({
  "duration": 1710614479,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksCheckEMailFromMagicLink()"
});
formatter.result({
  "duration": 3957450361,
  "status": "passed"
});
formatter.match({
  "location": "PrivateEmailSteps.userUnlocksTheAppFromReceivedEMail()"
});
formatter.result({
  "duration": 29709972080,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSkipsTutorial()"
});
formatter.result({
  "duration": 16221755014,
  "status": "passed"
});
formatter.match({
  "location": "HomeScreenSteps.userValidatesThatHeIsInTheHomeView()"
});
formatter.result({
  "duration": 7346412108,
  "status": "passed"
});
formatter.after({
  "duration": 5013849655,
  "status": "passed"
});
formatter.after({
  "duration": 68408,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 19,
  "name": "[first uninstall] User is in Home Screen after a successful Register even when interacting with the app",
  "description": "",
  "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 20,
  "name": "User enters a valid e-mail address",
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "User selects a country from the list",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "User enters birth date",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "User proceeds with Consents",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "User enters a display name",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "User clicks on E-mail Me Magic Link",
  "keyword": "When "
});
formatter.step({
  "line": 26,
  "name": "User clicks Check E-mail from Magic Link",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "User unlocks the app from received e-mail",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "User skips tutorial",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "User is in Home View",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "User \"\u003cperforms an action\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 31,
  "name": "User open the app",
  "keyword": "And "
});
formatter.step({
  "line": 32,
  "name": "User is in \"\u003cexpected screen\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 33,
  "name": "",
  "description": "",
  "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app;",
  "rows": [
    {
      "cells": [
        "performs an action",
        "expected screen"
      ],
      "line": 34,
      "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app;;1"
    },
    {
      "cells": [
        "puts the app in the background",
        "Home Screen"
      ],
      "line": 35,
      "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app;;2"
    },
    {
      "cells": [
        "closes the app",
        "Home Screen"
      ],
      "line": 36,
      "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5809907679,
  "status": "passed"
});
formatter.before({
  "duration": 14272677411,
  "status": "passed"
});
formatter.before({
  "duration": 148962643,
  "status": "passed"
});
formatter.before({
  "duration": 36821,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "User is in Launcher Screen",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User selects Create Account",
  "keyword": "And "
});
formatter.match({
  "location": "RegisterSteps.userIsInLauncherScreen()"
});
formatter.result({
  "duration": 2842863367,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectsCreateAccount()"
});
formatter.result({
  "duration": 1811295052,
  "status": "passed"
});
formatter.scenario({
  "line": 35,
  "name": "[first uninstall] User is in Home Screen after a successful Register even when interacting with the app",
  "description": "",
  "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 20,
  "name": "User enters a valid e-mail address",
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "User selects a country from the list",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "User enters birth date",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "User proceeds with Consents",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "User enters a display name",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "User clicks on E-mail Me Magic Link",
  "keyword": "When "
});
formatter.step({
  "line": 26,
  "name": "User clicks Check E-mail from Magic Link",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "User unlocks the app from received e-mail",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "User skips tutorial",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "User is in Home View",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "User \"puts the app in the background\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 31,
  "name": "User open the app",
  "keyword": "And "
});
formatter.step({
  "line": 32,
  "name": "User is in \"Home Screen\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "RegisterSteps.userEntersAValidEMailAddress()"
});
formatter.result({
  "duration": 4529569630,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectACountryFromTheList()"
});
formatter.result({
  "duration": 2931811982,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersBirthDate()"
});
formatter.result({
  "duration": 6052061840,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userProceedsWithConsents()"
});
formatter.result({
  "duration": 11991943563,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersADisplayName()"
});
formatter.result({
  "duration": 4296527428,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksOnEMailMeMagicLink()"
});
formatter.result({
  "duration": 1710301557,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksCheckEMailFromMagicLink()"
});
formatter.result({
  "duration": 4187423592,
  "status": "passed"
});
formatter.match({
  "location": "PrivateEmailSteps.userUnlocksTheAppFromReceivedEMail()"
});
formatter.result({
  "duration": 34061865908,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSkipsTutorial()"
});
formatter.result({
  "duration": 6311284788,
  "status": "passed"
});
formatter.match({
  "location": "HomeScreenSteps.userValidatesThatHeIsInTheHomeView()"
});
formatter.result({
  "duration": 7143898729,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "puts the app in the background",
      "offset": 6
    }
  ],
  "location": "RegisterSteps.user(String)"
});
formatter.result({
  "duration": 6584959239,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userOpenTheApp()"
});
formatter.result({
  "duration": 554600080,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Home Screen",
      "offset": 12
    }
  ],
  "location": "RegisterSteps.userIsIn(String)"
});
formatter.result({
  "duration": 6248582999,
  "status": "passed"
});
formatter.after({
  "duration": 5111509343,
  "status": "passed"
});
formatter.after({
  "duration": 79857,
  "status": "passed"
});
formatter.before({
  "duration": 5448841424,
  "status": "passed"
});
formatter.before({
  "duration": 14446569937,
  "status": "passed"
});
formatter.before({
  "duration": 142350562,
  "status": "passed"
});
formatter.before({
  "duration": 44148,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "User is in Launcher Screen",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User selects Create Account",
  "keyword": "And "
});
formatter.match({
  "location": "RegisterSteps.userIsInLauncherScreen()"
});
formatter.result({
  "duration": 3119199244,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectsCreateAccount()"
});
formatter.result({
  "duration": 1875745905,
  "status": "passed"
});
formatter.scenario({
  "line": 36,
  "name": "[first uninstall] User is in Home Screen after a successful Register even when interacting with the app",
  "description": "",
  "id": "register-features;[first-uninstall]-user-is-in-home-screen-after-a-successful-register-even-when-interacting-with-the-app;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 20,
  "name": "User enters a valid e-mail address",
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "User selects a country from the list",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "User enters birth date",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "User proceeds with Consents",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "User enters a display name",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "User clicks on E-mail Me Magic Link",
  "keyword": "When "
});
formatter.step({
  "line": 26,
  "name": "User clicks Check E-mail from Magic Link",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "User unlocks the app from received e-mail",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "User skips tutorial",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "User is in Home View",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "User \"closes the app\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 31,
  "name": "User open the app",
  "keyword": "And "
});
formatter.step({
  "line": 32,
  "name": "User is in \"Home Screen\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "RegisterSteps.userEntersAValidEMailAddress()"
});
formatter.result({
  "duration": 4953717341,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectACountryFromTheList()"
});
formatter.result({
  "duration": 3581533775,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersBirthDate()"
});
formatter.result({
  "duration": 6449906590,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userProceedsWithConsents()"
});
formatter.result({
  "duration": 12107850633,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersADisplayName()"
});
formatter.result({
  "duration": 4732735239,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksOnEMailMeMagicLink()"
});
formatter.result({
  "duration": 1715598274,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksCheckEMailFromMagicLink()"
});
formatter.result({
  "duration": 3808052344,
  "status": "passed"
});
formatter.match({
  "location": "PrivateEmailSteps.userUnlocksTheAppFromReceivedEMail()"
});
formatter.result({
  "duration": 35485557418,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSkipsTutorial()"
});
formatter.result({
  "duration": 6420737949,
  "status": "passed"
});
formatter.match({
  "location": "HomeScreenSteps.userValidatesThatHeIsInTheHomeView()"
});
formatter.result({
  "duration": 7156486255,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "closes the app",
      "offset": 6
    }
  ],
  "location": "RegisterSteps.user(String)"
});
formatter.result({
  "duration": 303125460,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userOpenTheApp()"
});
formatter.result({
  "duration": 581817325,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Home Screen",
      "offset": 12
    }
  ],
  "location": "RegisterSteps.userIsIn(String)"
});
formatter.result({
  "duration": 7582880656,
  "status": "passed"
});
formatter.after({
  "duration": 5163197797,
  "status": "passed"
});
formatter.after({
  "duration": 34663,
  "status": "passed"
});
formatter.before({
  "duration": 4703050627,
  "status": "passed"
});
formatter.before({
  "duration": 14563728553,
  "status": "passed"
});
formatter.before({
  "duration": 132408277,
  "status": "passed"
});
formatter.before({
  "duration": 37306,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "User is in Launcher Screen",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User selects Create Account",
  "keyword": "And "
});
formatter.match({
  "location": "RegisterSteps.userIsInLauncherScreen()"
});
formatter.result({
  "duration": 2908730067,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectsCreateAccount()"
});
formatter.result({
  "duration": 1923563446,
  "status": "passed"
});
formatter.scenario({
  "line": 38,
  "name": "[first uninstall] Register a too young user",
  "description": "",
  "id": "register-features;[first-uninstall]-register-a-too-young-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 39,
  "name": "User enters a valid e-mail address",
  "keyword": "Given "
});
formatter.step({
  "line": 40,
  "name": "User selects a country from the list",
  "keyword": "And "
});
formatter.step({
  "line": 41,
  "name": "User enters a birth date from a recent date",
  "keyword": "And "
});
formatter.step({
  "line": 42,
  "name": "User is notified that he is too young to register",
  "keyword": "Then "
});
formatter.match({
  "location": "RegisterSteps.userEntersAValidEMailAddress()"
});
formatter.result({
  "duration": 4628185629,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectACountryFromTheList()"
});
formatter.result({
  "duration": 2972678373,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userEntersABirthDateFromARecentDate()"
});
formatter.result({
  "duration": 12004461161,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userIsNotifiedThatHeIsTooYoungToRegister()"
});
formatter.result({
  "duration": 2231051989,
  "status": "passed"
});
formatter.after({
  "duration": 5165389169,
  "status": "passed"
});
formatter.after({
  "duration": 44709,
  "status": "passed"
});
formatter.before({
  "duration": 4347315528,
  "status": "passed"
});
formatter.before({
  "duration": 15028997119,
  "status": "passed"
});
formatter.before({
  "duration": 147928031,
  "status": "passed"
});
formatter.before({
  "duration": 41443,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "User is in Launcher Screen",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User selects Create Account",
  "keyword": "And "
});
formatter.match({
  "location": "RegisterSteps.userIsInLauncherScreen()"
});
formatter.result({
  "duration": 2437661124,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSelectsCreateAccount()"
});
formatter.result({
  "duration": 1893099975,
  "status": "passed"
});
formatter.scenario({
  "line": 45,
  "name": "[first uninstall] Trying to register an already registered user and continue with Login flow",
  "description": "",
  "id": "register-features;[first-uninstall]-trying-to-register-an-already-registered-user-and-continue-with-login-flow",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 46,
  "name": "User enters an already registered e-mail address",
  "keyword": "Given "
});
formatter.step({
  "line": 47,
  "name": "User is in Check E-mail Screen",
  "keyword": "Then "
});
formatter.step({
  "line": 48,
  "name": "User is recognized as to be already registered",
  "keyword": "And "
});
formatter.step({
  "line": 49,
  "name": "User clicks Check E-mail from Magic Link",
  "keyword": "When "
});
formatter.step({
  "line": 50,
  "name": "User unlocks the app from received e-mail",
  "keyword": "And "
});
formatter.step({
  "line": 51,
  "name": "User skips tutorial",
  "keyword": "And "
});
formatter.step({
  "line": 52,
  "name": "User is in Home View",
  "keyword": "Then "
});
formatter.match({
  "location": "RegisterSteps.userEntersAnAlreadyRegisteredEMailAddress()"
});
formatter.result({
  "duration": 3905174432,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userIsInCheckEMailScreen()"
});
formatter.result({
  "duration": 4244667547,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userIsRecognizedAsToBeAlreadyRegistered()"
});
formatter.result({
  "duration": 580799756,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userClicksCheckEMailFromMagicLink()"
});
formatter.result({
  "duration": 3640815666,
  "status": "passed"
});
formatter.match({
  "location": "PrivateEmailSteps.userUnlocksTheAppFromReceivedEMail()"
});
formatter.result({
  "duration": 34811622319,
  "status": "passed"
});
formatter.match({
  "location": "RegisterSteps.userSkipsTutorial()"
});
formatter.result({
  "duration": 5518559852,
  "status": "passed"
});
formatter.match({
  "location": "HomeScreenSteps.userValidatesThatHeIsInTheHomeView()"
});
formatter.result({
  "duration": 7366727666,
  "status": "passed"
});
formatter.after({
  "duration": 5147484192,
  "status": "passed"
});
formatter.after({
  "duration": 47204,
  "status": "passed"
});
});