package starter.user;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateUser {
    private static String url = "https://reqres.in/api/";

    @Step("I set API endpoint for create user")
    public String setApiEndpoint() {
        return url + "users";
    }

    @Step("I send request to create user with valid inputs")
    public void sendCreateUserRequest() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("name","morpheus");
        requestBody.put("job", "zion resident");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(setApiEndpoint());
    }

    @Step("I receive status code 201")
    public void receiveStatusCode201() {
        restAssuredThat(response -> response.statusCode(201));
    }

    @Step("I receive valid data for created user")
    public void receiveValidCreatedUserData() {
        restAssuredThat(response -> response.body("'name'", equalTo("morpheus")));
        restAssuredThat(response -> response.body("'job'", equalTo("zion resident")));
    }
}
