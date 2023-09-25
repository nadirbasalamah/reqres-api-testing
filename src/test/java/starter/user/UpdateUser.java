package starter.user;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUser {
    private static String url = "https://reqres.in/api/";

    @Step("I set API endpoint for update user")
    public String setApiEndpoint() {
        return url + "users/2";
    }

    @Step("I send request to update user with valid inputs")
    public void sendUpdateUserRequest() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("name", "morpheus");
        requestBody.put("job","zion resident");

        SerenityRest.given()
                .header("Content-Type","application/json")
                .body(requestBody.toString())
                .put(setApiEndpoint());
    }

    @Step("I receive valid data for updated user")
    public void receiveValidUpdatedUserData() {
        restAssuredThat(response -> response.body("'name'", equalTo("morpheus")));
        restAssuredThat(response -> response.body("'job'", equalTo("zion resident")));
    }

}
