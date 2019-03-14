package cucumber.cucumberTests;

import api.coca.cola.home.screen.HomeView;
import cucumber.api.java.en.Then;

public class HomeScreenSteps {

    @Then("^User is in Home View$")
    public void userValidatesThatHeIsInTheHomeView() {
        HomeView homeView = new HomeView();
        homeView.validateElementsFromHomeView();
    }

}
