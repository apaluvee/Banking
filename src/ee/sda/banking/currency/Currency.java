package ee.sda.banking.currency;

public enum Currency {

    EUR("euro", 1),
    USD("dollar", 0.8),
    RUB("rubble", 0.003),
    PLN("pln", 0.25),
    YEN("yen", 0.32),
    DAK("dak", 0.24),
    EEK("kroon", 0.07);

    double exchangeRateToEuro;

    String name;


    //constructor
    Currency(String name, double exchangeRateToEuro) {
        this.exchangeRateToEuro = exchangeRateToEuro;
        this.name = name;
    }

    public double convertToEuros(double amount) {
        return amount * exchangeRateToEuro;
    }


    //converting
    public double convertToCurrency(double amount, Currency currency) {
        double eurosAmount = convertToEuros(amount);
        return eurosAmount / currency.exchangeRateToEuro;
    }




    @Override
    public String toString() {
        return name;
    }
}
