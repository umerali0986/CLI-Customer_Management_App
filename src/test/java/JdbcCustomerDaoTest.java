import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.umercode.Dao.JdbcCustomerDao;
import org.umercode.Model.Customer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class JdbcCustomerDaoTest extends BaseDaoTests {

    private final Customer CUSTOMER_1 = new Customer(1,"Test 1", "Test1@gmail","7632221899", LocalDate.parse("1990-08-12"));
    private final Customer CUSTOMER_2 = new Customer(2,"Test 2", "Test2@gmail","52789076513", LocalDate.parse("1985-01-23"));
    private JdbcCustomerDao sut;

    @Before
    public void setUp(){
        sut = new JdbcCustomerDao(dataSource);
    }

    @Test
    public void getCustomers_return_valid_list_of_customer(){
        List<Customer> customers = sut.getCustomers();
        Assert.assertEquals(2,customers.size());

        assertionCustomerMatch(CUSTOMER_1,customers.get(0));
        assertionCustomerMatch(CUSTOMER_2,customers.get(1));
    }

    @Test
    public void getCustomersById_with_valid_Id_return_valid_customer(){
        Customer customer = sut.getCustomerById(1);
        Assert.assertNotNull("getCustomersById returned null customer",customer);

        assertionCustomerMatch(CUSTOMER_1,customer);

        Customer customer2 = sut.getCustomerById(2);
        Assert.assertNotNull("getCustomersById returned null customer",customer2);

        assertionCustomerMatch(CUSTOMER_2,customer2);

    }

    @Test
    public void getCustomersByName_with_valid_name_return_valid_customer(){
        List<Customer> customers = sut.getCustomerByName("Test 1");
        Assert.assertEquals(1,customers.size());

        assertionCustomerMatch(CUSTOMER_1,customers.get(0));
    }


    @Test
    public void getCustomersByEmail_with_valid_email_return_valid_customer(){
        Customer customer = sut.getCustomerByEmail("Test1@gmail");
        Assert.assertNotNull("getCustomersByEmail returned null customer",customer);

        assertionCustomerMatch(CUSTOMER_1,customer);

        Customer customer2 = sut.getCustomerByEmail("Test2@gmail");
        Assert.assertNotNull("getCustomersByEmail returned null customer",customer2);

        assertionCustomerMatch(CUSTOMER_2,customer2);

    }

    @Test
    public void createCustomer_return_customer(){
        Customer testCustomer = new Customer(0,"Test 3","Test3@gmail","5738709837",LocalDate.parse("1989-10-20"));
        Customer customer = sut.createCustomer(testCustomer);
        Assert.assertNotNull("createCustomer returned null customer",customer);

        Customer retreivedCustomer = sut.getCustomerById(customer.getCustomerId());
        testCustomer.setCustomerId(customer.getCustomerId());

        assertionCustomerMatch(testCustomer,retreivedCustomer);

    }


    @Test
    public void updateCustomer_return_updatedCustomer(){
        Customer customerToBeUpdate = sut.getCustomerById(1);

        customerToBeUpdate.setName("Test update 1");
        customerToBeUpdate.setEmail("Test1Update@gmail");
        customerToBeUpdate.setBirthdate(LocalDate.parse("2000-10-10"));
        customerToBeUpdate.setPhone("8872387929");


        Customer updatedCustomer = sut.updateCustomer(customerToBeUpdate);
        Assert.assertNotNull(updatedCustomer);

        assertionCustomerMatch(updatedCustomer,customerToBeUpdate);

    }


    @Test
    public void deleteCustomerById_return_numberOfRow_affected(){
        int numberOfRow = sut.deleteCustomerById(1);
        Assert.assertEquals(1,numberOfRow);

       Customer customer = sut.getCustomerById(1);
       Assert.assertNull(customer);

    }



    private void assertionCustomerMatch(Customer expected, Customer actual){
        Assert.assertEquals("Customer id doesn't match",expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals("Customer birthdate doesn't match",expected.getBirthdate(), actual.getBirthdate());
        Assert.assertEquals("Customer email doesn't match",expected.getEmail(), actual.getEmail());
        Assert.assertEquals("Customer name doesn't match",expected.getName(), actual.getName());
        Assert.assertEquals("Customer phone doesn't match",expected.getPhone(), actual.getPhone());
    }

}
