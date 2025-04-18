package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import starter.user.UpdateUser;

public class UpdateUserSteps {

    @Steps
    UpdateUser updateUser;

    @Given("I set API endpoint for update user")
    public void setApiEndpoint() {
        updateUser.setApiEndpoint();
    }

    @When("I send request to update user with valid inputs")
    public void sendUpdateUserRequest() {
        updateUser.sendUpdateUserRequest();
    }

    @And("I receive valid data for updated user")
    public void receiveValidUpdatedUserData() {
        updateUser.receiveValidUpdatedUserData();
    }
}
