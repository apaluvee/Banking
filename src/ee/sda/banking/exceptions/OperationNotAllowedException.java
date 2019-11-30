package ee.sda.banking.exceptions;

public class OperationNotAllowedException extends BankException {

    public OperationNotAllowedException(String operation) {
        super("Operation is not allowed: " + operation);
    }

}
