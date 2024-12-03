package com.thecatapi.runners;

import com.thecatapi.utils.BeforeSuite;
import com.thecatapi.utils.DataToFeature;
import io.cucumber.junit.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.thecatapi.stepDefinitions",
        tags = "@GetSearchCat",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
@RunWith(PersonalizedRunner.class)
public class GetSearchCatRunner {

    @BeforeSuite
    public static void test() throws InvalidFormatException, IOException {
        DataToFeature.overrideFeatureFiles("./src/test/resources/features/getSearchCat.feature");
    }
}
