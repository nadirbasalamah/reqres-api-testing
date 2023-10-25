package starter.user;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import starter.utils.GenerateToken;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUser {
    private static String url = "https://reqres.in/api/";

    @Step("I set API endpoint for create user")
    public String setApiEndpoint() {
        return url + "users";
    }

    @Step("I send request to create user with valid inputs")
    public void sendCreateUserRequest() {
        JSONObject requestBody = new JSONObject();

        // create Faker object
        Faker faker = new Faker();

        // generate value
        String name = faker.name().name();
        String job = faker.job().title();

        // insert value into request body
        requestBody.put("name",name);
        requestBody.put("job", job);

        String token = GenerateToken.generateToken();

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toString())
                .post(setApiEndpoint());
    }

    @Step("I receive status code 201")
    public void receiveStatusCode201() {
        restAssuredThat(response -> response.statusCode(201));
    }

    @Step("I receive valid data for created user")
    public void receiveValidCreatedUserData() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.CREATE_USER_RESPONSE_SCHEMA);

        restAssuredThat(response -> response.body("'name'", notNullValue()));
        restAssuredThat(response -> response.body("'job'", notNullValue()));
        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
