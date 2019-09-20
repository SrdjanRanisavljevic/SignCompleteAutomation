$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/cucumber/features/SignLogin.feature");
formatter.feature({
  "line": 1,
  "name": "SignLogin",
  "description": "",
  "id": "signlogin",
  "keyword": "Feature"
});
formatter.before({
  "duration": 74022800,
  "status": "passed"
});
formatter.before({
  "duration": 112800,
  "status": "passed"
});
formatter.before({
  "duration": 33691086400,
  "status": "passed"
});
formatter.before({
  "duration": 1043960200,
  "status": "passed"
});
formatter.before({
  "duration": 46900,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "#Background:"
    },
    {
      "line": 4,
      "value": "#Given User is in Launcher Screen"
    }
  ],
  "line": 7,
  "name": "Sign Login Feature",
  "description": "",
  "id": "signlogin;sign-login-feature",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "User is in Sign launcher screen",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "User clicks on Sign login button",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "User enters Sign userName",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "User enters Sign Password",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "User clicks on Sign Sign in button",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Random Waiter for Vesa to see what is happening",
  "keyword": "And "
});
formatter.match({
  "location": "SignLauncher.swipe3TimesToGetTheLoginButton()"
});
formatter.result({
  "duration": 10540698800,
  "status": "passed"
});
formatter.match({
  "location": "SignLauncher.clickOnLoginButton()"
});
formatter.result({
  "duration": 2995066400,
  "status": "passed"
});
formatter.match({
  "location": "SignLauncher.enterUserName()"
});
formatter.result({
  "duration": 3337044600,
  "status": "passed"
});
formatter.match({
  "location": "SignLauncher.enterPassword()"
});
formatter.result({
  "duration": 3915625500,
  "status": "passed"
});
formatter.match({
  "location": "SignLauncher.clickOnSignInButton()"
});
formatter.result({
  "duration": 3700127500,
  "status": "passed"
});
formatter.match({
  "location": "SignLauncher.randomSleep()"
});
formatter.result({
  "duration": 10072460200,
  "status": "passed"
});
formatter.after({
  "duration": 2184970600,
  "status": "passed"
});
formatter.after({
  "duration": 23400,
  "status": "passed"
});
});