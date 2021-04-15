package ro.ubb.catalog.client.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.catalog.client.service.*;
import ro.ubb.catalog.core.model.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static ro.ubb.catalog.core.model.RecordType.*;

public class AdminUI {
    private final UserService userController;
    private final RecordService recordController;
    private final TransactionService transactionController;
    private boolean running;

    public AdminUI(UserService userController, RecordService recordController, TransactionService transactionController) {
        this.userController = userController;
        this.recordController = recordController;
        this.transactionController = transactionController;
        this.running = true;
    }

    public void displayMenu() {
        System.out.println(" ------------------------------------- ");
        System.out.println("1. Add record");
        System.out.println("2. Add user");
        System.out.println("3. List records");
        System.out.println("4. List users");
        System.out.println("5. Remove record");
        System.out.println("6. Remove user");
        System.out.println("7. Update record");
        System.out.println("8. Update user");
        System.out.println("9. Get most purchased records");
        System.out.println("10. Filter transactions by date");
        System.out.println("11. Filter users by the minimum amount of transactions");
        System.out.println("12. Filter records by the minimum amount of instances in stock");
        System.out.println("0. Exit");
        System.out.println(" ------------------------------------- ");
    }


    public void addRecord() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the record ID: ");
        int recordId = input.nextInt(); input.nextLine();
        System.out.print("Enter the album name: ");
        String name = input.nextLine();
        System.out.print("Enter the price: ");
        int price = input.nextInt(); input.nextLine();
        System.out.print("Enter the record type: ");
        String recordTypeAsString = input.nextLine().toLowerCase();
        RecordType recordType = switch (recordTypeAsString) {
            case "vinyl" -> VINYL;
            case "tape" -> TAPE;
            default -> CD;
        };
        this.recordController.add(recordId, price, name, 1, recordType).thenAccept(System.out::println);
    }


    public void listRecords() {
        System.out.println("Records:");
        try {
            this.recordController.getRepository().thenAccept(s -> s.forEach(System.out::println));
        } catch (SQLException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void removeRecord() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the record ID to delete: ");
        int recordId = input.nextInt(); input.nextLine();
        this.recordController.remove(recordId).thenAccept(System.out::println);

    }


    public void updateRecord() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the record ID to update: ");
        int recordId = input.nextInt(); input.nextLine();
        System.out.print("Enter the new album name: ");
        String name = input.nextLine();
        System.out.print("Enter the new price: ");
        int price = input.nextInt(); input.nextLine();
        System.out.print("Enter the new number of copies in stock: ");
        int newInStock = input.nextInt(); input.nextLine();
        System.out.print("Enter the new record type: ");
        String recordTypeAsString = input.nextLine().toLowerCase();
        RecordType recordType = switch (recordTypeAsString) {
            case "vinyl" -> VINYL;
            case "tape" -> TAPE;
            default -> CD;
        };

        System.out.println(recordType.toString());
        this.recordController.update(recordId, price, name, newInStock, recordType).thenAccept(System.out::println);
    }


    public void addUser() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("User ID: ");
        int userId = input.nextInt(); input.nextLine();
        System.out.print("First name: ");
        String firstName = input.nextLine();
        System.out.print("Second name: ");
        String lastName = input.nextLine();

        this.userController.add(userId, firstName, lastName, 0);
    }


    public void listUsers() throws ExecutionException, InterruptedException, SQLException {
        System.out.println("Users:");
        this.userController.getRepository().thenAccept(s -> s.forEach(System.out::println));
    }


    public void removeUser() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the user ID to delete: ");
        int userId = input.nextInt(); input.nextLine();

        this.userController.remove(userId).thenAccept(System.out::println);
    }


    public void updateUser() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("User ID to update: ");
        int userId = input.nextInt(); input.nextLine();
        System.out.print("New first name: ");
        String firstName = input.nextLine();
        System.out.print("New second name: ");
        String lastName = input.nextLine();

        this.userController.update(userId, firstName, lastName, 0).thenAccept(System.out::println);
    }


    public void run() {
        Scanner input = new Scanner(System.in);
        this.running = true;
        int option = 0;

        while (this.running) {
            this.displayMenu();
            System.out.print("Enter an option: ");
            option = input.nextInt();

            try {
                switch (option) {
                    case 0 -> this.running = false;
                    case 1 -> addRecord();
                    case 2 -> addUser();
                    case 3 -> listRecords();
                    case 4 -> listUsers();
                    case 5 -> removeRecord();
                    case 6 -> removeUser();
                    case 7 -> updateRecord();
                    case 8 -> updateUser();
                    case 9 -> mostPurchasedRecords();
                    case 10 -> filterTransactionsByDate();
                    case 11 -> filterUsersByMinimumTransactions();
                    case 12 -> filterByMinimumInStock();
                    default -> System.out.println("Enter an option between 1 and 12.");
                }
            }catch(Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    private void filterUsersByMinimumTransactions() {
        Scanner input = new Scanner(System.in);
        System.out.println("please enter the minimum amount of transactions: ");
        try {
            this.userController.filterByNumberOfTransactions(input.nextInt()).thenAccept(s -> s.forEach(System.out::println));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterTransactionsByDate() {
        System.out.println("enter the day you want the transactions from (dd/MM/yyyy): ");
        Scanner input = new Scanner(System.in);
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
            this.transactionController.filterByDate(date).thenAccept(s -> s.forEach(System.out::println));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostPurchasedRecords() {
        try {
            this.transactionController.getMostPurchasedRecords().thenAccept(s -> s.forEach(System.out::println));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void filterByMinimumInStock() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the minimum amount of instances in stock: ");
        try {
            this.recordController.filterByRecordsWithInStockGreaterThan(input.nextInt()).thenAccept(s -> s.forEach(System.out::println));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
