package co.com.sofka.utils.service.soap.countrycurrency;

public enum Response {
    COUNTRYDV_RESPONSE1("<m:sISOCode>%s</m:sISOCode>"),
    COUNTRYDV_RESPONSE2("<m:sName>%s</m:sName>");


    private final String value;

    Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
