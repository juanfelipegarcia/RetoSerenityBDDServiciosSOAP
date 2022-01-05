package co.com.sofka.setpdefinitions.services.soap;

import co.com.sofka.ServiceSetup;
import co.com.sofka.models.SearchCurrency;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static co.com.sofka.questions.ResturnSoapServiceResponse.resturnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.currencyname.Patch.NAMEDV;
import static co.com.sofka.utils.service.soap.currencyname.Response.NAME_DV_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CurrencyName extends ServiceSetup {

    private static final Logger LOGGER = Logger.getLogger(CountryCurrency.class);
    private SearchCurrency searchCurrencyNameIso;

    //Scenario1
    @Given("que el usuario quiera buscar el codigo ISO {string}")
    public void queElUsuarioQuieraBuscarElIndicativo(String currencyISO) {
        try {
            super.setup();
            searchCurrencyNameIso = new SearchCurrency();
            searchCurrencyNameIso.setCodigo(currencyISO);
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el usuario hace la petición de busqueda la divisa")
    public void elUsuarioHaceLaPeticionDeBusquedaLaDivisa() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("el ususario debería obtener como ISO {string}")
    public void elUsusarioDeberiaObtenerComoCapital(String respuesta) {
        try {
            searchCurrencyNameIso.setRespuesta(respuesta);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda debe ser: ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }


    //Scenario2
    @Given("que el usuario quiera buscar un indicativo  que no concuerda {string}")
    public void queElUsuarioQuieraBuscarUnIndicativoQueNoConcuerda(String currencyISOError) {
        try {
            super.setup();
            searchCurrencyNameIso = new SearchCurrency();
            searchCurrencyNameIso.setCodigo(currencyISOError);
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el usuario hace la petición de busqueda de la divisa solicitada")
    public void elUsuarioHaceLaPeticionDeBusquedaDeLaDivisa() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("el ususario debería obtener como resultado {string}")
    public void elUsusarioDeberiaObtenerComoResultado(String respuesta) {
        try {
            searchCurrencyNameIso.setRespuesta(respuesta);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda debe ser: ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    private SearchCurrency searchCurrencyNameIso(){
        return searchCurrencyNameIso;
    }


    private String bodyRequest(){
            return String.format(readFile(NAMEDV.getValue()), searchCurrencyNameIso().getCodigo());
    }

    private String bodyResponse(){
        return String.format(NAME_DV_RESPONSE.getValue(), searchCurrencyNameIso().getRespuesta());
    }



}
