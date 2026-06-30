import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("1. View customer profile");
            System.out.println("2. Open account");
            System.out.println("3. Withdraw");
            System.out.println("4. Deposit");
            System.out.println("5. Transfer funds");
            System.out.println("6. Complete and exit");
            int choice = scanner.nextInt();


            switch (choice) {
                case 1: 
                    System.out.println("Enter a CIF number");
                    String searchCif = scanner.nextLine();
                    for (Customer c : customers) {
                        if (c.getCif().equals(searchCif)) {
                            System.out.println(c.getCif());
                            System.out.println(c.getName());
                            System.out.println(c.getDob());
                            for (Account a : c.getAccount()) {
                                System.out.println(a.getAccountNumber() + " " + a.getAccountType() + ": " + a.getBalance());
                                System.out.println();
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter a CIF number to assign the customer: ");
                    boolean found = false;
                    searchCif = scanner.nextLine();
                        for (Customer c : customers) {
                            if (c.getCif().equals(searchCif)) {
                                found = true;
                            }
                        }
                        if (found) {
                            System.out.println("CIF number is invalid or unavailable.");
                            break;
                        }
                        else {
                            System.out.println("Enter customer name: ");
                            String name = scanner.nextLine();
                            System.out.println("Enter customer date of birth: ");
                            String dob = scanner.nextLine();
                            Customer c = new Customer(name, searchCif, dob);
                            customers.add(c);
                            System.out.println("What account would the customer like to open: ");
                            String accountType = scanner.nextLine();
                            System.out.println("Assign a 9 digit number to the account");
                            String searchAccountNumber = scanner.nextLine();
                            found = false;
                            if (searchAccountNumber.length() != 9) {
                                found = true;
                                System.out.println("Account number is not 9 digits.");
                                break;
                            }
                            for (Customer c2 : customers) {
                                for (Account a : c2.getAccount()) {
                                    if (a.getAccountNumber().equals(searchAccountNumber)) {
                                        found =true;
                                    }
                                }
                            }
                            if (found) {
                                System.out.println("Account number is unavailable");
                                break;        
                            }
                            else {
                                System.out.println("What is the opening balance of the account?");
                                double balance = scanner.nextDouble();
                                Account a = new Account(balance, accountType, searchAccountNumber);
                                c.addAccount(a);
                                break;
                        }
                    }
                }
            }
            scanner.close();
        }
    }