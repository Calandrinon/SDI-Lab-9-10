package ro.ubb.catalog.client.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.catalog.client.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ClientUI {
    private final RecordService recordController;
    private final TransactionService transactionController;
    private final UserService userController;
    private boolean running;
    private int userId;

    public ClientUI(UserService userController, RecordService recordController, TransactionService transactionController) {
        this.userController = userController;
        this.recordController = recordController;
        this.transactionController = transactionController;
        this.running = true;
    }

    public void login() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("login with your id: ");
        Integer givenID = input.nextInt();
        if(this.userController.exists(givenID)){
            this.run(givenID);
        }else {
            throw new RuntimeException("no such user");
        }
    }

    public void displayMenu() {
        System.out.println(" ------------------------------------- ");
        System.out.println("1. Buy record");
        System.out.println("2. List all records");
        System.out.println("3. Filter by price");
        System.out.println("0. Exit");
        System.out.println(" ------------------------------------- ");
    }


    public void buyRecord() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the ID of the record: ");
        int recordId = input.nextInt(); input.nextLine();

        System.out.print("Enter the quantity: ");
        int quantity = input.nextInt(); input.nextLine();

        transactionController.makeTransaction(userId, recordId, quantity);

    }

    private List<String> getPrettyPrint(Map<Integer, Integer> map){
        return map.keySet().stream()
                .map(key -> "you own " + map.get(key) + " of this record: " + key)
                .collect(Collectors.toList());
    }

    public void listOwnedRecords() throws ExecutionException, InterruptedException, SQLException {
        System.out.println("Your records:");
        this.transactionController.getRecordsByUser(this.userId).thenAccept(s -> System.out.println(getPrettyPrint(s)));
    }


    public void run(Integer userId) {
        this.running = true;
        Scanner input = new Scanner(System.in);
        int option = 0;

        while (this.running) {
            this.displayMenu();
            System.out.print("Enter an option: ");
            option = input.nextInt();

            this.userId = userId;

            try {
                switch (option) {
                    case 0 -> this.running = false;
                    case 1 -> this.buyRecord();
                    case 2 -> this.listOwnedRecords();
                    case 3 -> this.filterRecordsByPrice();
                    default -> System.out.println("Enter an option between 0 and 3.");
                }
            } catch(Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    private void filterRecordsByPrice() {
        System.out.println("please enter the upper limit for the price");
        Scanner input = new Scanner(System.in);
        try {
            this.recordController.filterByPrice(input.nextInt()).thenAccept(s -> s.forEach(System.out::println));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}