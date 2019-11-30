package ee.sda.banking;

import ee.sda.banking.account.AccountStatus;
import ee.sda.banking.account.DebitAccount;
import ee.sda.banking.currency.Currency;
import ee.sda.banking.exceptions.OperationNotAllowedException;
import ee.sda.banking.exceptions.WithdrawException;

public class BankingMain {

    public static void main(String[] args) {


        Currency currency = Currency.EUR;

        System.out.println("10 " + currency);

        double rublesAmount = 1000;
        double eurosAmount = Currency.RUB.convertToEuros(rublesAmount);

        System.out.println(rublesAmount + " " + Currency.RUB + " = " + eurosAmount + " " + Currency.EUR);


        //erinevad currencyd .values() meetodiga, uus array: allCurrencies
        Currency[] allCurrencies = Currency.values();

        for (Currency c : allCurrencies) {
            System.out.println("Available currency: " + c);
        }

        Currency currentCurrency = Currency.EEK;


        for (Currency c : allCurrencies) {
            switch (c) {
                case EUR:
                    System.out.println("euro");
                case EEK:
                    System.out.println("eesti kroon not in use ");
                    break;
                default:
                    System.out.println(c + " is in use");
            }
        }

        System.out.println();


        double yenAmount = Currency.RUB.convertToCurrency(1000, Currency.YEN);
        System.out.println(yenAmount);

        System.out.println();


        //Account status
        AccountStatus[] statusTypes = AccountStatus.values();

        for (AccountStatus status : statusTypes) {
            System.out.println("Status: " + status);

            System.out.println("Can see: " + status.canSeeAmount());
            System.out.println("Can deposit: " + status.canDeposit());
            System.out.println("Can withdraw: " + status.canWithdraw());
        }


        System.out.println();

        DebitAccount debitAccount = new DebitAccount("John Doe");
        debitAccount.deposit(1000);
        debitAccount.withdraw(100);
        debitAccount.pay(500);

        System.out.println("Money left: " + debitAccount.getAmount());
        System.out.println("Account status: " + debitAccount.getStatus());


        debitAccount.setStatus(AccountStatus.LOCKED);
        System.out.println("Account status: " + debitAccount.getStatus());

        try{
            debitAccount.pay(200);
        } catch (OperationNotAllowedException e) {
            debitAccount.setStatus(AccountStatus.OPEN);
        }


        System.out.println("Money left: " + debitAccount.getAmount());
        System.out.println();

        debitAccount.setStatus(AccountStatus.OPEN);
        DebitAccount friendsAccount = new DebitAccount("Friend One");
        debitAccount.transfer(100, friendsAccount);
        System.out.println("Our money: " + debitAccount.getAmount());
        System.out.println("Friends money: " + friendsAccount.getAmount());
        System.out.println();



      /*  DebitAccount friendsAccount2 = new DebitAccount("Friend One");
        friendsAccount2.setStatus(AccountStatus.LOCKED);
        debitAccount.transfer(100,friendsAccount2);
        System.out.println("Our money: "+ debitAccount.getAmount());
        System.out.println("Friends money: "+friendsAccount2.getAmount());*/

        debitAccount.withdraw(300, Currency.EUR);
        System.out.println("Current money: " + debitAccount.getAmount());
        debitAccount.deposit(1000, Currency.YEN);
        System.out.println("Deposited 1000 YEN in euros is: " + debitAccount.getAmount() + "EUR");

        debitAccount.deposit(1000, Currency.RUB);
        System.out.println("Current money in Euros: " + debitAccount.getAmount());

        DebitAccount thirdAccount = new DebitAccount("Third", Currency.YEN);

        System.out.println();

        friendsAccount.setStatus(AccountStatus.CLOSED);
        try {
            System.out.println("Money left on friends ee.sda.banking.account: " + friendsAccount.getAmount());
        } catch (RuntimeException e) {
            System.out.println("error " + e.getMessage());
        } finally {

        }

        System.out.println();

        System.out.println("Current money in Euros: " + debitAccount.getAmount());
        try {
            debitAccount.withdraw(324, Currency.EUR);
            System.out.println("Current money in Euros: " + debitAccount.getAmount());
        } catch (WithdrawException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();


    }

}
