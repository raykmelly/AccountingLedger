package org.yearup;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountingLedgerApp {
    Scanner scanner = new Scanner(System.in);

    public void run() {
        // Initialize the loop
        homeScreen();
    }

    public void homeScreen(){
        while (true) {
            System.out.println("Chose an option from those below");
            System.out.println("D) Add deposit");
            System.out.println("P) Make payment (Debit)");
            System.out.println("L) Display ledger");
            System.out.println("X) Exit");
            System.out.print("Enter: ");

            try {
                String reply = scanner.nextLine();

                switch (reply.toUpperCase()){
                    case "D":
                        System.out.println("\nSending you to the deposit screen... \n");
                        addDeposit();
                        break;
                    case "P":
                        System.out.println("\nSending you to the payment screen... \n");
                        addPayment();
                        break;
                    case "L":
                        System.out.println("\nSending you to the ledger screen... \n");
                        promptLedgerScreen();
                        break;
                    case "X":
                        System.out.println("\nExiting program... \n");
                        System.exit(0);
                    default:
                        System.out.println("\nPlease select an option from those above\n");

                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError, please use a string to reply: " + e + "\n");
                homeScreen();

                return;
            }
        }
    }

    private void promptLedgerScreen() {
        System.out.println("Please enter a command");
        System.out.println("A) All - Display all entries");
        System.out.println("D) Deposits - Display only positive entries deposited into the account");
        System.out.println("P) Payments - Display only negative entries / payments");
        System.out.println("R) Reports - Custom search");
        System.out.println("H) Home - Return to home screen");
        System.out.print("Enter: ");

        try {
            String reply = scanner.nextLine();
            reply = reply.toUpperCase();
            switch (reply) {
                case "A":
                    System.out.println("\nDisplaying all entries from the ledger...\n");
                    displayAllEntries();
                    break;
                case "D":
                    System.out.println("\nDisplaying all deposits from the ledger...\n");
                    displayDeposits();
                    break;
                case "P":
                    System.out.println("\nDisplaying all payments from the ledger...\n");
                    displayPayments();
                    break;
                case "R":
                    System.out.println("\nSending you to the custom search...\n");

                    break;
                case "H":
                    System.out.println("\nSending you back to the home screen...\n");
                    break;
                default:
                    System.out.println("\nPlease select an option from those above\n");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nError, please use a string to reply: " + e + "\n");
            displayLedgerScreen();
        }
    }

    private void displayLedgerScreen() {

    }

    private void displayPayments() {

    }

    private void displayDeposits() {

    }

    private void displayAllEntries() {

    }

    private void addPayment() {
        System.out.println("Please enter the following information");
        System.out.print("Date (yyy-mm-dd): ");
        String dateString = scanner.nextLine();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate date = LocalDate.parse(dateString, dateFormatter);

        System.out.print("Time (hh:mm:ss): ");
        String timeString = scanner.nextLine();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime time = LocalTime.parse(timeString, timeFormatter);

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount (must be negative): ");
        double amount = scanner.nextDouble();
        amount = -Math.abs(amount);

        String fullString = date + "|" + time + "|" + desc + "|" + vendor + "|" + amount;
        System.out.println(fullString);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.csv",true))) {
            bw.newLine();
            bw.write(fullString);
            System.out.println();

        } catch (IOException e) {
            System.out.println("\nError writing file: " + e + "\n");
        }
    }

    private void addDeposit() {
        System.out.println("Please enter the following information");
        System.out.print("Date (yyy-mm-dd): ");
        String dateString = scanner.nextLine();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate date = LocalDate.parse(dateString, dateFormatter);

        System.out.print("Time (hh:mm:ss): ");
        String timeString = scanner.nextLine();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime time = LocalTime.parse(timeString, timeFormatter);

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount (must be positive): ");
        double amount = scanner.nextDouble();
        amount = Math.abs(amount);

        String fullString = date + "|" + time + "|" + desc + "|" + vendor + "|" + amount;
        System.out.println(fullString);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.csv",true))) {
            bw.newLine();
            bw.write(fullString);
            System.out.println();

        } catch (IOException e) {
            System.out.println("\nError writing file: " + e + "\n");
        }

    }
}
