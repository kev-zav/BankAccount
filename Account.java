public class Account {

    private double balance;
    private String accountNumber;
    private String accountType;

    public Account(double balance, String accountNumber, String accountType) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountType = accountType;

    }
    public double getBalance() {
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public void withdraw(double withdrawalAmount) {
        balance = balance - withdrawalAmount;
        System.out.println("Your new balance is: " + balance);
    }
    public void deposit(double depositAmount) {
        balance = balance + depositAmount;
        System.out.println("Your new balance is: " + balance);
    }
    public void checkBalance() {
        System.out.println("Your balance is: " + balance);
    }
}