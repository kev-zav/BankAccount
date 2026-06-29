import java.util.ArrayList;

public class Customer {

    private String name;
    private String cif;
    private String dob;

    private ArrayList<Account> accounts = new ArrayList<>();
    public Customer(String name, String cif, String dob) {
        this.name = name;
        this.cif = cif;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }
    public String getCif() {
        return cif;
    }
    public String getDob() {
        return dob;
    }
    public ArrayList<Account> getAccount() {
        return accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
}