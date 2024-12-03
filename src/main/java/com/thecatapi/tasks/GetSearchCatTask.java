package com.thecatapi.tasks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class GetSearchCatTask implements Task {
    private final String endpoint;

    @Override
    public <T extends Actor> void performAs(T actor) {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssured.config().redirect(RestAssured.config().getRedirectConfig().followRedirects(true));
        actor.attemptsTo(
                Get.resource(endpoint).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .headers("key", "Content-Type", "value", "application/json")
                                .log()
                                .all()
                )
        );
    }

    public static Performable withTypeData(String endpoint) {
        return instrumented(GetSearchCatTask.class, endpoint);
    }
}
