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
            scanner.nextLine();

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
                    } else {
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
                            System.out.println("Account number is not 9 digits.");
                            break;
                        }
                        for (Customer c2 : customers) {
                            for (Account a : c2.getAccount()) {
                                if (a.getAccountNumber().equals(searchAccountNumber)) {
                                    found = true;
                                }
                            }
                        }
                        if (found) {
                            System.out.println("Account number is unavailable");
                            break;
                        } else {
                            System.out.println("What is the opening balance of the account?");
                            double balance = Double.parseDouble(scanner.nextLine());
                            Account a = new Account(balance, searchAccountNumber, accountType);
                            c.addAccount(a);
                            break;
                        }
                    }

                case 3: {
                    System.out.println("Enter the account number to withdraw from: ");
                    String accountNumber = scanner.nextLine();
                    for (Customer c : customers) {
                        for (Account a : c.getAccount()) {
                            if (a.getAccountNumber().equals(accountNumber)) {
                                System.out.println("Enter withdrawal amount: ");
                                double withdrawAmount = Double.parseDouble(scanner.nextLine());
                                if (withdrawAmount > a.getBalance()) {
                                    System.out.println("Withdrawal amount exceeds available balance");
                                } else {
                                    a.withdraw(withdrawAmount);
                                }
                                break;
                            }
                        }
                    }
                    break;
                }

                case 4: {
                    System.out.println("Enter the account number to deposit to: ");
                    String accountNumber = scanner.nextLine();
                    for (Customer c : customers) {
                        for (Account a : c.getAccount()) {
                            if (a.getAccountNumber().equals(accountNumber)) {
                                System.out.println("Enter deposit amount: ");
                                double depositAmount = Double.parseDouble(scanner.nextLine());
                                a.deposit(depositAmount);
                                break;
                            }
                        }
                    }
                    break;
                }

                case 5: {
                    System.out.println("Enter the account to transfer money out of: ");
                    String accountOne = scanner.nextLine();
                    System.out.println("Enter the account to transfer the money into: ");
                    String accountTwo = scanner.nextLine();
                    Account fromAccount = null;
                    Account toAccount = null;
                    for (Customer c : customers) {
                        for (Account a : c.getAccount()) {
                            if (a.getAccountNumber().equals(accountOne)) {
                                fromAccount = a;
                            }
                            if (a.getAccountNumber().equals(accountTwo)) {
                                toAccount = a;
                            }
                        }
                    }
                    if (fromAccount == null || toAccount == null) {
                        System.out.println("One or both account numbers not found.");
                        break;
                    }
                    System.out.println("Enter the amount to transfer: ");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    if (fromAccount.getBalance() < transferAmount) {
                        System.out.println("Transfer amount exceeds available balance.");
                        break;
                    } else {
                        fromAccount.withdraw(transferAmount);
                        toAccount.deposit(transferAmount);
                    }
                    break;
                }

                case 6:
                    running = false;
                    break;
            }
        }
        scanner.close();
    }
}