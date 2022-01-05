package co.com.sofka.runners.services.soap.cucumber;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/soap/CountryCurrency.feature",
                    "src/test/resources/features/services/soap/CurrencyName.feature"},
        glue = {"co.com.sofka.setpdefinitions.services.soap"}
)

public class CountryInfoService {
}
