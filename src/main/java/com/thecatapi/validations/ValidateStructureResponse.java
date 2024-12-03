package com.thecatapi.validations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class ValidateStructureResponse {

    public static void validateNodeStructure(Response response) {
        JsonPath jsonPath = response.jsonPath();

        Map<String, Object> firstObject = jsonPath.getMap("[0]");

        String[] keys = {"id", "url", "width", "height"};
        for (String key : keys) {
            Assert.assertNotNull("El atributo '" + key + "' no está presente o es nulo.", firstObject.get(key));
        }
    }
}
