package org.umercode.Dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.umercode.Exception.DaoException;
import org.umercode.Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(){
        BasicDataSource  dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/CustomerManagement");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer;";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()){
                customers.add(mapRowToCustomer(result));
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }
        return customers;
    }



    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE customer_id = ?;";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, customerId);
            if (result.next()){
                customer = mapRowToCustomer(result);
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }

        return customer;
    }

    @Override
    public List<Customer> getCustomerByName(String customerName) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE customer_name ILIKE ?;";

        try{
            customerName = "%" + customerName +"%";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, customerName);
            while (result.next()){
               customers.add(mapRowToCustomer(result));
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }

        return customers;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE email = ?;";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, email);
            if (result.next()){
                customer = mapRowToCustomer(result);
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }

        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = null;
        int newCustomerId = 0;
        String sql = "INSERT INTO customer (customer_name, email, phone, birthdate)" +
                     "VALUES (?,?,?,?) RETURNING customer_id;";
        try{
            newCustomerId = jdbcTemplate.queryForObject(sql,int.class, customer.getName(), customer.getEmail(),
                            customer.getPhone(),customer.getBirthdate());
            if (newCustomerId > 0){
                createdCustomer = getCustomerById(newCustomerId);
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }
        return createdCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer updatedCustomer = null;
        int numberOfRow = 0;

        String sql = "UPDATE customer SET customer_name = ?, email = ?, phone = ?, birthdate = ? " +
                     " WHERE customer_id = ?;";

        try{
            numberOfRow = jdbcTemplate.update(sql,customer.getName(), customer.getEmail(),
                    customer.getPhone(),customer.getBirthdate(), customer.getCustomerId());
            if (numberOfRow > 0){
                updatedCustomer = getCustomerById(customer.getCustomerId());
            }else{
                System.out.println("Couldn't update a customer, something went wrong.");
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }
        return updatedCustomer;
    }

    @Override
    public int deleteCustomerById(int customerId) {
        int numberOfRow = 0;
        String sql = "DELETE FROM customer WHERE customer_id = ?;";

        try{
            numberOfRow = jdbcTemplate.update(sql, customerId);
            if (numberOfRow <= 0){
                System.out.println("Couldn't update a customer, something went wrong.");
            }
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }

        return numberOfRow;
    }

    private Customer mapRowToCustomer(SqlRowSet result) {
        Customer customer = new Customer();
        customer.setCustomerId(result.getInt("customer_id"));
        customer.setName(result.getString("customer_name"));
        customer.setEmail(result.getString("email"));
        customer.setPhone(result.getString("phone"));
        if(result.getDate("birthdate") != null){
            customer.setBirthdate(result.getDate("birthdate").toLocalDate());
        }

        return customer;
    }
}
