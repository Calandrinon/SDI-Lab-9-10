package ro.ubb.catalog.client.ui;

import java.util.Scanner;

public class UI {
    private final ClientUI clientUI;
    private final AdminUI adminUI;
    private boolean running;

    public UI(ClientUI clientUI, AdminUI adminUI) {
        this.clientUI = clientUI;
        this.adminUI = adminUI;
        this.running = true;
    }


    public void displayMenu() {
        System.out.println("1. Client role");
        System.out.println("2. Admin role");
        System.out.println("0. Exit");
    }


    public void run() {
        Scanner input = new Scanner(System.in);
        int option = 0;


        while (this.running) {
            this.displayMenu();
            System.out.print("Enter an option: ");
            option = input.nextInt();
            try {
                switch (option) {
                    case 0 -> this.running = false;
                    case 1 -> this.clientUI.login();
                    case 2 -> this.adminUI.run();
                    default -> System.out.println("Enter an option between 0 and 2.");
                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }
}