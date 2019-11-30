package ee.sda.banking.exceptions;

public class WithdrawException extends BankException {

    public WithdrawException(String operation) {
        super("Not enough money on bank account. " + operation);
    }
}
