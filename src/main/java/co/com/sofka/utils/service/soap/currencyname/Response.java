package co.com.sofka.utils.service.soap.currencyname;

public enum Response {
    NAME_DV_RESPONSE("<m:CurrencyNameResult>%s</m:CurrencyNameResult>");

    private final String value;

    Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
