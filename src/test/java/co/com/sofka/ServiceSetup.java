package co.com.sofka;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;

public class ServiceSetup {
    protected static final String URL_BASE = "http://webservices.oorsprong.org";
    protected static final String RESOURCE = "/websamples.countryinfo/CountryInfoService.wso";
    protected final Actor actor = new Actor("Felipe");

    protected void setup(){
        actorCan();
    }

    private void actorCan(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headersCollection = new HashMap<>();
        headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        headersCollection.put("SOAPAction", "");
        return headersCollection;
    }
}
