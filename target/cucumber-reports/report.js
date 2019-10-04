$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/cucumber/features/SignLogin.feature");
formatter.feature({
  "line": 1,
  "name": "SignLogin",
  "description": "",
  "id": "signlogin",
  "keyword": "Feature"
});
formatter.before({
  "duration": 119807100,
  "status": "passed"
});
formatter.before({
  "duration": 102100,
  "status": "passed"
});
formatter.before({
  "duration": 27716398000,
  "status": "passed"
});
formatter.before({
  "duration": 1033498900,
  "status": "passed"
});
formatter.before({
  "duration": 52601,
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
  "name": "Sign in with production user and change server to Stage",
  "keyword": "Given "
});
formatter.match({
  "location": "SignLauncher.changeServerToStage()"
});
formatter.result({
  "duration": 44492619499,
  "error_message": "java.lang.AssertionError: Cannot click on Login button from Sign Launcher screen\r\n\tat api.sign.launcher.SignLauncherView.clickOnLoginButton(SignLauncherView.java:94)\r\n\tat cucumber.cucumberTests.SignLauncher.changeServerToStage(SignLauncher.java:38)\r\n\tat ✽.Given Sign in with production user and change server to Stage(src/test/java/cucumber/features/SignLogin.feature:8)\r\n",
  "status": "failed"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 4385648201,
  "status": "passed"
});
formatter.after({
  "duration": 52600,
  "status": "passed"
});
});