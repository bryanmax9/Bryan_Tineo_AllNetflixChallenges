package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepo;


    @Before
    public void setUp() throws Exception{
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer(){
        //we create customer
        Customer customer = new Customer();
        //We are giving information o tthis customer in order to test everything is working fine
        customer.setFirstName("Carlos");
        customer.setLastName("Ortega");
        customer.setEmail("Carlos@gmail.com");
        customer.setCompany("AT&T");
        customer.setPhone("111-222-3456");
        customer.setAddress1("Calle los Abetos 141");
        customer.setAddress2("Casa 3");
        customer.setCity("Lima");
        customer.setState("Lima");
        customer.setPostalCode("90815");
        customer.setCountry("Peru");

        customer = customerRepo.save(customer); // this will ouputt the customer

        //from the customer that we just added, we will find it an store it in this customer1 in order
        // to test that customer1 is the same to the one we added before to the table
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        //This is to test that we succesfully added the customer to the table of customers
        assertEquals(customer1.get(), customer);

    }

    @Test
    public void getAllCustomers(){
        //we create #1 customer
        Customer customer = new Customer();
        //We are giving information o this customer in order to test everything is working fine
        customer.setFirstName("Carlos");
        customer.setLastName("Ortega");
        customer.setEmail("Carlos@gmail.com");
        customer.setCompany("AT&T");
        customer.setPhone("111-222-3456");
        customer.setAddress1("Calle los Abetos 141");
        customer.setAddress2("Casa 3");
        customer.setCity("Lima");
        customer.setState("Lima");
        customer.setPostalCode("90815");
        customer.setCountry("Peru");

        customerRepo.save(customer); //Adding first customer to table of customers

        //we create #2 customer
        Customer customer2 = new Customer();
        //We are giving information o this customer in order to test everything is working fine
        customer2.setFirstName("Salvador");
        customer2.setLastName("Murriel");
        customer2.setEmail("mentita@gmail.com");
        customer2.setCompany("Tijerazo");
        customer2.setPhone("111-222-3456");
        customer2.setAddress1("Calle Los Alcones 324");
        customer2.setAddress2("departamento 2");
        customer2.setCity("Lima");
        customer2.setState("Lima");
        customer2.setPostalCode("90815");
        customer2.setCountry("Peru");

        customerRepo.save(customer2); // adding customer 2 into the table

        // in this list store all customers that are currently inside the table of customers
        //In this case since we added 2 customers, the list should have a size of 2
        List<Customer> customerList = customerRepo.findAll();

        //Proving that so far in the table there are only 2 customers added so far..
        assertEquals(2,customerList.size());
    }

    @Test
    public void updateCustomer() {
        //we create customer
        Customer customer = new Customer();
        //We are giving information o tthis customer in order to test everything is working fine
        customer.setFirstName("Carlos");
        customer.setLastName("Ortega");
        customer.setEmail("Carlos@gmail.com");
        customer.setCompany("AT&T");
        customer.setPhone("111-222-3456");
        customer.setAddress1("Calle los Abetos 141");
        customer.setAddress2("Casa 3");
        customer.setCity("Lima");
        customer.setState("Lima");
        customer.setPostalCode("90815");
        customer.setCountry("Peru");

        customerRepo.save(customer); // adding customer to table

        //Pretend user changed  its email(user updated it)
        customer.setEmail("Ortega@gmail.com");

        //save will update this customer in the table with this new email
        customerRepo.save(customer);

        //From the table of customer we are getting the customer we recently added inside customer 1
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        //checking if customer succesfully updated email
        assertEquals(customer1.get(),customer);

    }

    @Test
    public void deleteCustomer(){
        //we create customer
        Customer customer = new Customer();
        //We are giving information o tthis customer in order to test everything is working fine
        customer.setFirstName("Carlos");
        customer.setLastName("Ortega");
        customer.setEmail("Carlos@gmail.com");
        customer.setCompany("AT&T");
        customer.setPhone("111-222-3456");
        customer.setAddress1("Calle los Abetos 141");
        customer.setAddress2("Casa 3");
        customer.setCity("Lima");
        customer.setState("Lima");
        customer.setPostalCode("90815");
        customer.setCountry("Peru");

        customerRepo.save(customer); // adding customer to table of customers

        //So we added a customer to then delete it, so then we can check that we delete it succesfully
        //Deleting customer
        customerRepo.deleteById(customer.getId());

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        //Test that there is no customer in table of customers
        assertFalse(customer1.isPresent());
    }





}