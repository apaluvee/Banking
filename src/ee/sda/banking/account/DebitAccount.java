package ee.sda.banking.account;

import ee.sda.banking.currency.Currency;
import ee.sda.banking.exceptions.OperationNotAllowedException;
import ee.sda.banking.exceptions.WithdrawException;

public class DebitAccount {

    private String owner;

    private AccountStatus status;

    private double amount; //default value is 0


    private Currency currency = Currency.EUR; //default on euros


    public DebitAccount(String owner) {
        this.owner = owner;
        this.status = AccountStatus.OPEN;

    }

    //uus constructor kui tahad currencyt muuta
    public DebitAccount(String owner, Currency currency) {
        this(owner);
        this.currency = currency;
    }


    public double getAmount() throws RuntimeException {
        if (status.canSeeAmount()) {
            return amount;
        }
        throw new OperationNotAllowedException("Not allowed to see the amount of money.");
    }


    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus newStatus) {
        status = newStatus;
    }

    public void withdraw(double amountToWithdraw) throws WithdrawException {
        if (status.canWithdraw()) {
            amount = amount - amountToWithdraw;
            if (amount < 0) {
                throw new WithdrawException("Can't withdraw.");
            }
            return;
        }
        throw new OperationNotAllowedException("withdraw");
    }

    public void withdraw(double amountToWithdraw, Currency currency) {
        double withdrawAmount = currency.convertToCurrency(amountToWithdraw, this.currency);
        withdraw(withdrawAmount);
    }

    /*public void withdraw(double amountToWithdraw, Currency currency) {
        if (status.canWithdraw()) {
            double withdrawAmount = currency.convertToCurrency(amountToWithdraw, Currency.EUR);
            amount = amount - withdrawAmount;
        }
    }*/


    public void deposit(double amountToDeposit) {
        if (status.canDeposit()) {
            amount = amount + amountToDeposit;
            return;
        }
        throw new OperationNotAllowedException("deposit");
    }

    public void deposit(double amountToDeposit, Currency currency) {
        double depositAmount = currency.convertToCurrency(amountToDeposit, this.currency);
        deposit(depositAmount);
    }


    public void pay(double amountToPay) {
        if (status.canWithdraw()) {
            //amount = amount - amountToPay;
            withdraw(amountToPay);
            return;
        }
        throw new OperationNotAllowedException("pay");
    }

    public void pay(double amountToPay, Currency currency) {
        double payAmount = currency.convertToCurrency(amountToPay, this.currency);
        pay(payAmount);
    }


    public void transfer(double amountToTransfer, DebitAccount account) {
        if (status.canWithdraw() && account.getStatus().canDeposit) { // need to check if another ee.sda.banking.account can receive money
            //amount = amount - amountToTransfer; //sama: amount -= amountToTransfer
            withdraw(amountToTransfer);
            double exchangedAmount = currency.convertToCurrency(amountToTransfer, account.currency);
            account.deposit(exchangedAmount);
        }
    }


}
