package com.thecatapi.questions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Map;

@AllArgsConstructor
public class StructureValidation implements Question<Boolean> {

    protected Response response;
    protected String keys;

    @Override
    public Boolean answeredBy(Actor actor) {
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> firstObject = jsonPath.getMap("[0]");

        String[] keysArray = keys.split(",");
        for (String key : keysArray) {
            if (firstObject.get(key) == null) {
                throw new AssertionError("The key '" + key + "' is not present");
            }
        }
        return true;
    }

    public static StructureValidation matches(Response response, String keys) {
        return new StructureValidation(response, keys);
    }
}
