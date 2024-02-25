package org.umercode.Dao;

import org.umercode.Model.Customer;

import java.util.List;

public interface CustomerDao {


     List<Customer> getCustomers();

     Customer getCustomerById(int id);

     List<Customer> getCustomerByName(String name);

     Customer getCustomerByEmail(String email);

     Customer createCustomer(Customer customer);

     Customer updateCustomer(Customer customer);

     int deleteCustomerById(int id);



}
