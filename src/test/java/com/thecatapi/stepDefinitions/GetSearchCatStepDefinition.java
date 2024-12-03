package com.thecatapi.stepDefinitions;

import com.thecatapi.questions.StructureValidation;
import com.thecatapi.tasks.GetSearchCatTask;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.thecatapi.constants.Constants.BASE_URL;
import static com.thecatapi.validations.ValidateStructureResponse.validateNodeStructure;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class GetSearchCatStepDefinition {

    private static final String BASE_API_URL = BASE_URL;

    @When("I send a Get request to the endpoint {string}")
    public void iSendAGetRequestToTheEndpoint(String endpoint) {
        Actor user = Actor.named("user").whoCan(CallAnApi.at(BASE_API_URL));

        user.attemptsTo(
                GetSearchCatTask.withTypeData(endpoint)
        );
    }

    @Then("I validate the response code is {string} and the structure {string} is valid")
    public void iValidateTheResponseCodeIsAndTheStructureIsValid(String statusCode, String keys) {
        Response responseAPI = SerenityRest.lastResponse();
        String statusCodeResponse = String.valueOf(responseAPI.getStatusCode());
        Actor user = Actor.named("user");

        user.should(
                seeThat("The response code is ", response -> statusCodeResponse.equals(statusCode)),
                seeThat("The structure is valid", StructureValidation.matches(responseAPI, keys))
        );
    }
}
