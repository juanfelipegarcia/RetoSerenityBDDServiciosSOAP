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
import static co.com.sofka.utils.service.soap.countrycurrency.Patch.COUNTRYDV;
import static co.com.sofka.utils.service.soap.countrycurrency.Response.COUNTRYDV_RESPONSE1;
import static co.com.sofka.utils.service.soap.countrycurrency.Response.COUNTRYDV_RESPONSE2;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;


public class CountryCurrency extends ServiceSetup {
    private static final Logger LOGGER = Logger.getLogger(CountryCurrency.class);
    private SearchCurrency searchCurrency;
    private SearchCurrency searchCurrencyName;
    private static final boolean VERDADERO = true;


    //Scenario1
    @Given("que el usuario quiera buscar el codigo {string}")
    public void queElUsuarioQuieraBuscarElIndicativo(String codigoIso) {
        try {
            super.setup();
            searchCurrency = new SearchCurrency();
            searchCurrency.setCodigo(codigoIso);

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el usuario hace la petición de busqueda la divisa requerida")
    public void elUsuarioHaceLaPeticionDeBusquedaLaDivisa() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest(VERDADERO))

            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("el ususario debería obtener como iso divisa {string}")
    public void elUsusarioDeberiaObtenerComoCapital(String respuesta) {
        try {
            searchCurrency.setRespuesta(respuesta);
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
    @Given("que el usuario quiera buscar un indicativo  que no coincidan {string}")
    public void queElUsuarioQuieraBuscarUnIndicativoQueNoConcuerda(String codigoIso) {
        try {
            super.setup();
            searchCurrencyName = new SearchCurrency();
            searchCurrencyName.setCodigo(codigoIso);
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @When("el usuario hace la petición de busqueda de la divisa")
    public void elUsuarioHaceLaPeticionDeBusquedaDeLaDivisa() {
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest(!VERDADERO))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("el ususario debería obtener como resultado el valor {string}")
    public void elUsusarioDeberiaObtenerComoResultado(String respuesta) {
        try {
            searchCurrencyName.setRespuesta(respuesta);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda debe ser: ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponse2()))
            );

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    private SearchCurrency searchCurrency(){
        return searchCurrency;
    }
    private SearchCurrency searchCurrencyName(){
        return searchCurrencyName;
    }

    private String bodyRequest(boolean estado){
        if (estado){
            return String.format(readFile(COUNTRYDV.getValue()), searchCurrency().getCodigo());
        }else {
            return String.format(readFile(COUNTRYDV.getValue()), searchCurrencyName().getCodigo());
        }

    }

    private String bodyResponse(){
        return String.format(COUNTRYDV_RESPONSE1.getValue(), searchCurrency().getRespuesta());
    }

    private String bodyResponse2(){
        return String.format(COUNTRYDV_RESPONSE2.getValue(), searchCurrencyName().getRespuesta());
    }



}
