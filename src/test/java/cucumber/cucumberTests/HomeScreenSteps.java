package cucumber.cucumberTests;

import api.coca.cola.home.screen.HomeViewSign;
import cucumber.api.java.en.Then;

public class HomeScreenSteps {

    @Then("^User is in Home View$")
    public void userValidatesThatHeIsInTheHomeView() {
        HomeViewSign homeView = new HomeViewSign();
        homeView.validateElementsFromHomeView();
    }

}
