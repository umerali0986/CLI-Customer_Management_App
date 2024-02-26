package org.umercode;

import org.umercode.Controller.CustomerController;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final CustomerController customerController = new CustomerController();

    public void run(){
        System.out.println(" -------------------- ");
        System.out.println("| CUSTOMER MANAGEMENT |");
        System.out.println(" -------------------- ");
        System.out.println();


        boolean running = true;
        while(running){

            System.out.println("*** Main Menu ***");
            System.out.println();
            System.out.println("1) View Customer");
            System.out.println("2) Modify Customer");
            System.out.println("3) Add Customer");
            System.out.println("4) Delete Customer");
            System.out.println("5) Exist");
            System.out.println();

            System.out.print("Please select an option: ");
            String selectedString = scanner.nextLine();
            int selectedOption = 0;
            try {
                 selectedOption = Integer.parseInt(selectedString);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please select valid option");
                System.out.println();
                continue;
            }

            if(selectedOption == 1){
                handleViewCustomer();
            }
            else if (selectedOption == 2) {
                handleModifyCustomer();
            }
            else if (selectedOption == 3) {
                customerController.createCustomer();
            }
            else if (selectedOption == 4) {
                // TODO- add implemantation for delete customer
            }
            else if (selectedOption == 5) {
                System.exit(1);
            }
            else{
                System.out.println("Invalid input, please select valid option");
                System.out.println();
            }
        }

    }



    private void handleViewCustomer() {

        boolean running = true;
        while(running) {
            System.out.println();
            System.out.println("1) Show all customer");
            System.out.println("2) Search Customer By Id");
            System.out.println("3) Search Customer By Email");
            System.out.println("4) Search Customer By Name");
            System.out.println("5) return to main menu");
            System.out.println();
            
            System.out.print("Please select an option: ");
            String selectedString = scanner.nextLine();
            int selectedOption = 0;
            try {
                selectedOption = Integer.parseInt(selectedString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please select valid option");
                System.out.println();
                continue;
            }

            if(selectedOption == 1){
                customerController.getCustomers();
                System.out.println();
            }
            else if (selectedOption == 2) {

                customerController.getCustomerById();
            }
            else if (selectedOption == 3) {
                customerController.getCustomerByEmail();
            }
            else if (selectedOption == 4) {
                customerController.getCustomerByName();
            }
            else if (selectedOption == 5) {
                return;
            }
            else{
                System.out.println("Invalid input, please select valid option");
                System.out.println();
            }
        }
    }

    private void handleModifyCustomer() {

        boolean running = true;
        while(running){
            System.out.println();
            System.out.println("1) Modify customer by Id");
            System.out.println("2) Modify customer by Email");
            System.out.println("3) return to main menu");
            System.out.println();

            System.out.print("please select option: ");
            String userInput = scanner.nextLine();
            int selectedOption = 0;
            try{
                selectedOption = Integer.parseInt(userInput);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please select valid option");
                continue;
            }

            if(selectedOption == 1){
                customerController.updateCustomerById();
            }
            else if(selectedOption == 2){
                customerController.updateCustomerByEmail();
            }
            else if(selectedOption == 3){
                return;
            }
            else{
                System.out.println("Invalid input, please select valid option");
                System.out.println();
            }
        }
    }
}
