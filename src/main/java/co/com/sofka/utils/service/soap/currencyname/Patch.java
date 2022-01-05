package co.com.sofka.utils.service.soap.currencyname;


public enum Patch {
    NAMEDV(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\services\\soap\\currencyName\\CurrencyName.xml");

    private final String value;

    Patch(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
