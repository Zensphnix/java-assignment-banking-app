package Assignment;

import java.util.Scanner;

public class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;
    private static int nextAccountNumber = 1001;
    private static final int MAX_ACCOUNTS = 100;

    public UserInterface() {
        accounts = new Account[MAX_ACCOUNTS];
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.println("\nCreate New Account");
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("You need to enter a name!");
                return;
            }
            System.out.print("Enter initial deposit amount: $");
            double initialDeposit = scanner.nextDouble();
            scanner.nextLine();
            if (initialDeposit < 0) {
                System.out.println("Can't start with negative money!");
                return;
            }
            System.out.print("Enter your email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine().trim();

            if (accountCount >= MAX_ACCOUNTS) {
                System.out.println("Too many accounts! Can't make more.");
                return;
            }

            Account newAccount = new Account(nextAccountNumber, name, initialDeposit, email, phoneNumber);
            accounts[accountCount] = newAccount;
            accountCount++;
            System.out.println("Account made! Your account number is: " + nextAccountNumber);
            nextAccountNumber++;
        } catch (Exception e) {
            System.out.println("Something went wrong! Try again.");
            scanner.nextLine();
        }
    }

    public void performDeposit() {
        System.out.println("\nDeposit Money");
        try {
            System.out.print("Enter your account number: ");
            int accountNum = scanner.nextInt();
            Account account = findAccount(accountNum);
            if (account == null) {
                System.out.println("Can't find the account!");
                return;
            }
            System.out.print("Enter deposit amount: $");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } catch (Exception e) {
            System.out.println("Please enter numbers only!");
            scanner.nextLine();
        }
    }

    public void performWithdrawal() {
        System.out.println("\nWithdraw Money");
        try {
            System.out.print("Enter your account number: ");
            int accountNum = scanner.nextInt();
            Account account = findAccount(accountNum);
            if (account == null) {
                System.out.println("Can't find the account!");
                return;
            }
            System.out.print("Enter withdrawal amount: $");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } catch (Exception e) {
            System.out.println("Please enter numbers only!");
            scanner.nextLine();
        }
    }

    public void showAccountDetails() {
        System.out.println("\nCheck Account Details");
        try {
            System.out.print("Enter your account number: ");
            int accountNum = scanner.nextInt();
            Account account = findAccount(accountNum);
            if (account == null) {
                System.out.println("Can't find the account!");
                return;
            }
            account.displayAccountDetails();
        } catch (Exception e) {
            System.out.println("Please enter a valid account number!");
            scanner.nextLine();
        }
    }

    public void updateContact() {
        System.out.println("\nChange Contact Info");
        try {
            System.out.print("Enter your account number: ");
            int accountNum = scanner.nextInt();
            scanner.nextLine();
            Account account = findAccount(accountNum);
            if (account == null) {
                System.out.println("Can't find the account!");
                return;
            }
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine().trim();
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine().trim();
            account.updateContactDetails(newEmail, newPhoneNumber);
        } catch (Exception e) {
            System.out.println("Something went wrong! Try again.");
            scanner.nextLine();
        }
    }

    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        System.out.println("\nAll Accounts");
        if (accountCount == 0) {
            System.out.println("No accounts yet.");
            return;
        }
        System.out.println("Total accounts: " + accountCount);
        System.out.println("Number | Name | Money");
        System.out.println("--------------------------");
        for (int i = 0; i < accountCount; i++) {
            System.out.printf("%-7d| %-10s| $%.2f%n",
                    accounts[i].getAccountNumber(),
                    accounts[i].getAccountHolderName(),
                    accounts[i].getBalance());
        }
    }

    public void mainMenu() {
        System.out.println("Welcome to the Bank!");
        int choice;
        do {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Check account info");
            System.out.println("5. Update contact info");
            System.out.println("6. Display all accounts");
            System.out.println("7. Exit");
            System.out.print("Pick a number: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: performDeposit(); break;
                    case 3: performWithdrawal(); break;
                    case 4: showAccountDetails(); break;
                    case 5: updateContact(); break;
                    case 6: displayAllAccounts(); break;
                    case 7: System.out.println("Thanks for using our bank!"); break;
                    default: System.out.println("That's not a choice! Pick 1-7.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number!");
                choice = 0;
                scanner.nextLine();
            }
        } while (choice != 7);
        scanner.close();
    }
}
