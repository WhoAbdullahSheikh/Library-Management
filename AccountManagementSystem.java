package com.mycompany.java;

import java.util.Scanner;

class BankAccount {
    private final int accountID;
    private double accountBalance;

    public BankAccount(int accountID, double initialBalance) {
        this.accountID = accountID;
        this.accountBalance = initialBalance;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void transferMoney(BankAccount toAccount, double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            toAccount.accountBalance += amount;
            System.out.printf("$%.2f transferred from account %d to account %d\n", amount, accountID, toAccount.getAccountID());
        } else {
            System.out.println("Insufficient funds to transfer");
        }
    }
}

class Bank {
    private BankAccount[] accounts;
    private int numAccounts;

    public Bank(String bankName, int branchNo) {
        this.accounts = new BankAccount[100];
        this.numAccounts = 0;
    }

    public void createAccount(double initialBalance) {
        BankAccount newAccount = new BankAccount(numAccounts + 1, initialBalance);
        accounts[numAccounts] = newAccount;
        numAccounts++;
        System.out.printf("New account created with ID %d and balance $%.2f\n", newAccount.getAccountID(), newAccount.getAccountBalance());
    }

    public void deleteAccount(int accountID) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountID() == accountID) {
                for (int j = i; j < numAccounts - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                numAccounts--;
                System.out.printf("Account %d deleted\n", accountID);
                return;
            }
        }
        System.out.printf("Account %d not found\n", accountID);
    }
}

class ManageAccount {
    private Bank bank;

    public ManageAccount() {
        bank = new Bank("My Bank", 123);
    }

    public void manageAccount() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1) Create saving account");
            System.out.println("2) Create current account");
            System.out.println("3) Delete account");
            System.out.println("4) Exit program");
            int choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter initial balance:");
                    double initialBalance = input.nextDouble();
                    bank.createAccount(initialBalance);
                }
                case 2 -> {
                    System.out.println("Enter initial balance:");
                    double initialBalance = input.nextDouble();
                    bank.createAccount(initialBalance);
                }
                case 3 -> {
                    System.out.println("Enter account ID:");
                    int accountID = input.nextInt();
                    bank.deleteAccount(accountID);
                }
                case 4 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again");
            }
        }
    }
}

public class AccountManagementSystem {
    public static void main(String[] args) {
        ManageAccount accountManager = new ManageAccount();
        accountManager.manageAccount();
    }
}
