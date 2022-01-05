package co.com.sofka.utils.service.soap.countrycurrency;


public enum Patch {
    COUNTRYDV(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\services\\soap\\countryCurrency\\CountryCurrency.xml");

    private final String value;

    Patch(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
