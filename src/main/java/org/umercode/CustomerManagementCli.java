package org.umercode;

import org.umercode.Controller.CustomerController;
import org.umercode.Dao.JdbcCustomerDao;
import org.umercode.Model.Customer;

import java.time.LocalDate;
import java.util.List;

public class CustomerManagementCli {
    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
//        JdbcCustomerDao customerDao = new JdbcCustomerDao();
//        CustomerController customerController = new CustomerController();
//        customerController.getCustomers();
//        List<Customer> customers = customerDao.getCustomers();
//            for(Customer customer : customers){
//                System.out.println(customer);
//            }

//        Customer customer = customerDao.getCustomerByEmail("johnsmith@gmail.com");
        //        System.out.println(customer);

//        List<Customer> customers = customerDao.getCustomerByName("john");
//        for(Customer customer : customers){
//                System.out.println(customer);
//            }

//        Customer newCustomer = new Customer(0,"Akil Ahmed","akil@gmail.com","7683906784", LocalDate.parse("1995-10-22"));
//        System.out.println(customerDao.createCustomer(newCustomer));

//        Customer updateCustomer = new Customer(3,"Akil Ahmed","akilahmed@gmail.com","7683906785", LocalDate.parse("1995-10-22"));
//        System.out.println(customerDao.updateCustomer(updateCustomer));


      //  System.out.println(customerDao.deleteCustomerById(3));

    }
}