package org.umercode.Controller;

import org.umercode.Dao.JdbcCustomerDao;
import org.umercode.Model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private final JdbcCustomerDao dao;
    private final Scanner scanner = new Scanner(System.in);

    public CustomerController(){
        dao = new JdbcCustomerDao();
    }

    public void getCustomers(){
        List<Customer> customers = dao.getCustomers();
        int counter = 1;
        for(int i = 0; i < customers.size(); i++){
            System.out.println( counter++ + ") " + customers.get(i).getName() + ", " + customers.get(i).getEmail() +
                                ", " + customers.get(i).getPhone() + ", " + customers.get(i).getBirthdate());
        }
    }


    public void getCustomerByName(){
        System.out.print("Please enter customer's name: ");
        String customerName = scanner.nextLine();
        List<Customer> customers = dao.getCustomerByName(customerName);
        if(customers.size() > 0) {
           for(Customer customer : customers){
               System.out.println(customer);
           }
        }
        else{
            System.out.println("No Customer with "+customerName+" name found.");
        }
    }

    public Customer getCustomerById(){
        Customer customer = null;
        int customerId = 0;
        boolean running = true;
        while(running) {
            System.out.println();
            System.out.print("Please enter customer's Id: ");
            String userInput = scanner.nextLine();
            try {
                customerId = Integer.parseInt(userInput);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input, please enter positive whole number");
                System.out.println();
                continue;
            }

            if(customerId > 0){
                 customer = dao.getCustomerById(customerId);
                if(customer != null){
                    System.out.println(customer);

                }
                else{
                    System.out.println("No Customer with Id "+customerId+" found.");
                }
               break;
            }
            else{
                System.out.println("Invalid input, please enter positive whole number");
                System.out.println();
            }
        }
            return customer;
    }

    public Customer getCustomerByEmail(){
        Customer customer = null;
        System.out.print("Please enter customer's email: ");
        String customerEmail = scanner.nextLine();

         customer = dao.getCustomerByEmail(customerEmail);
        if(customer != null){
            System.out.println(customer);
        }
        else{
            System.out.println("No Customer with "+customerEmail+" email found.");
        }
        return customer;
    }


    public void updateCustomerById() {
             Customer retreivedCustomer = getCustomerById();

            Customer updatedCustomer = dao.updateCustomer(promptToUpdateCustomer(retreivedCustomer));
            System.out.println("Customer updated successfully!");
            System.out.println(updatedCustomer);

    }

    public void updateCustomerByEmail() {
        Customer retreivedCustomer = getCustomerByEmail();

        Customer updatedCustomer = dao.updateCustomer(promptToUpdateCustomer(retreivedCustomer));
        System.out.println("Customer updated successfully!");
        System.out.println(updatedCustomer);

    }

    private Customer promptToUpdateCustomer(Customer retreivedCustomer){
        System.out.println();
        System.out.print("Enter customer's updated name (Leave blank to skip) : ");
        String updatedName = scanner.nextLine();
        if(updatedName.length() > 0){
            retreivedCustomer.setName(updatedName);
        }
        System.out.print("Enter customer's updated email (Leave blank to skip) : ");
        String updatedEmail = scanner.nextLine();
        if(updatedEmail.length() > 0){
            retreivedCustomer.setEmail(updatedEmail);
        }
        System.out.print("Enter customer's updated phone (Leave blank to skip) : ");
        String updatedPhone = scanner.nextLine();
        if(updatedPhone.length() > 0){
            retreivedCustomer.setPhone(updatedPhone);
        }
        return retreivedCustomer;
    }

    public void createCustomer(){
        String customerEmail = null;
        String customerName = null;
        String customerPhone = null;
        LocalDate customerBirthdate = null;

        System.out.println();
        System.out.print("Enter customer's full name : ");
         customerName = scanner.nextLine();
        boolean running = true;

        while(running){
            System.out.println();
            System.out.print("Enter customer's email: ");
             customerEmail = scanner.nextLine();
            if(!customerEmail.contains("@")){
                System.out.println("Invalid email format, please add valid email");
                continue;
            }
            break;
        }

        while(running){
            System.out.println();
            System.out.print("Enter customer's phone: ");
             customerPhone= scanner.nextLine();
            try{
                long phoneNumber = Long.parseLong(customerPhone);
                if(customerPhone.length() < 10 || customerPhone.length() > 12){
                    throw new NumberFormatException();
                }
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Invalid phone number, please enter valid phone number");
            }
        }

        while(running){
            System.out.print("Enter customer's birthdate: ");
            String birthdate= scanner.nextLine();
             customerBirthdate = null;
            try{
                customerBirthdate = LocalDate.parse(birthdate);
                break;
            }
            catch (RuntimeException e){
                System.out.println("Invalid date format, please enter in (YYYY-MM-DD) format");
            }
        }

        Customer newCustomer = new Customer(0,customerName, customerEmail, customerPhone,customerBirthdate);
        Customer createdCustomer = dao.createCustomer(newCustomer);

        System.out.println();
        System.out.println("The following customer was created successfully");
        System.out.println(createdCustomer);
    }

    public void deleteCustomer() {
        Customer retreivedCustomer = getCustomerById();
        if(retreivedCustomer != null){
            dao.deleteCustomerById(retreivedCustomer.getCustomerId());
            System.out.println("Customer with Id " + retreivedCustomer.getCustomerId() + " deleted successfully.");
        }
        else{
            System.out.println("Customer not found");
        }
    }
}
