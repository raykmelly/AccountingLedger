package org.yearup;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

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

                        return;
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
                    displayLedgerScreen();
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
        }
    }

    private void displayLedgerScreen() {
        System.out.println("Please enter a command");
        System.out.println("1) Month to date");
        System.out.println("2) Previous month");
        System.out.println("3) Year to date");
        System.out.println("4) Previous year");
        System.out.println("5) Search by vendor");
        System.out.println("0) Back");
        System.out.print("Enter: ");

        try {
            int reply = scanner.nextInt();
            switch (reply) {
                case 1:
                    System.out.println("\nSearching month to date...\n");
                    searchMonthToDate();
                    break;
                case 2:
                    System.out.println("\nSearch prev month...\n");
                    searchPrevMonth();
                    break;
                case 3:
                    System.out.println("\nSearch year to date...\n");
                    searchYearToDate();
                    break;
                case 4:
                    System.out.println("\nSearch previous year...\n");
                    searchPrevYear();
                    break;
                case 5:
                    System.out.println("\nSearching by vendor...\n");
                    searchByVendor();
                    break;
                case 0:
                    System.out.println("\nSending you back to the ledger screen...\n");
                    displayLedgerScreen();
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

    private void searchByVendor() {
        scanner.nextLine();
        System.out.print("Enter a vendor name: ");
        String reply = scanner.nextLine().strip();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                String vendor = split[3];

                if (vendor.equalsIgnoreCase(reply)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void searchPrevYear() {
        System.out.print("Enter a vendor name: ");
        int reply = scanner.nextInt();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                String date = split[0];
                String[] splitDate = date.split("-");
                int num = Integer.parseInt(splitDate[0]);
                if (num == (reply - 1)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void searchYearToDate() {
        System.out.print("Enter a vendor name: ");
        int reply = scanner.nextInt();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                String date = split[0];
                String[] splitDate = date.split("-");
                int num = Integer.parseInt(splitDate[0]);
                if (num == reply) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void searchPrevMonth() {
        System.out.print("Enter a vendor name: ");
        int reply = scanner.nextInt();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                String date = split[0];
                String[] splitDate = date.split("-");
                int num = Integer.parseInt(splitDate[1]);
                if (num == (reply - 1)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void searchMonthToDate() {
        System.out.print("Enter a vendor name: ");
        int reply = scanner.nextInt();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                String date = split[0];
                String[] splitDate = date.split("-");
                int num = Integer.parseInt(splitDate[0]);
                if (num == reply) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void displayPayments() {
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                double num = Double.parseDouble(split[4]);
                if (-Math.abs(num) == num) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void displayDeposits() {
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                double num = Double.parseDouble(split[4]);
                if (Math.abs(num) == num) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void displayAllEntries() {
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {

        }
        System.out.println();
    }

    private void addPayment() {
        System.out.println("Please enter the following information");

        LocalDate date = returnDate();

        LocalTime time = returnTime();

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

        scanner.nextLine();
    }

    private LocalDate returnDate(){
        System.out.print("Date (yyyy-mm-dd): ");
        String dateString = scanner.nextLine();
        LocalDate date = null;

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            date = LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Try again, incorrect input");
            return returnDate();
        }

        return date;
    }

    private LocalTime returnTime(){
        System.out.print("Time (hh:mm:ss): ");
        String timeString = scanner.nextLine();
        LocalTime time;

        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
            time = LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Try again, incorrect input");
            return returnTime();
        }

        return time;
    }

    private void addDeposit() {
        System.out.println("Please enter the following information");

        LocalDate date = returnDate();

        LocalTime time = returnTime();

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

        scanner.nextLine();
    }
}
