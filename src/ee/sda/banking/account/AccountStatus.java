package ee.sda.banking.account;

public enum AccountStatus {

    OPEN("Open ee.sda.banking.account", true, true, true),
    LOCKED("Locked ee.sda.banking.account", true, true, false),
    CLOSED("Closed ee.sda.banking.account", false, false, false);

    boolean canSeeAmount;
    boolean canDeposit;
    boolean canWithdraw;
    String status;

    AccountStatus(String status, boolean canSeeAmount, boolean canDeposit, boolean canWithdraw) {
        this.canSeeAmount = canSeeAmount;
        this.canDeposit = canDeposit;
        this.canWithdraw = canWithdraw;
        this.status = status;
    }

    //empty constructor, kui kõik valued on false siis võib kasutada empty
    AccountStatus() {

    }



    public boolean canSeeAmount(){
        return canSeeAmount;
    }

    public boolean canDeposit(){
        return canDeposit;
    }

    public boolean canWithdraw(){
        return canWithdraw;
    }


    @Override
    public String toString() {
        return status;
    }
}
