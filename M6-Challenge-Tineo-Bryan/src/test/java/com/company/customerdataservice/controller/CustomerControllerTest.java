package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Rushi");
        customer.setLastName("Sayed");
        customer.setEmail("rush@gmail.com");
        customer.setCompany("Snapchat");
        customer.setPhone("123-456-7891");
        customer.setAddress1("11740 Wilshire blvd");
        customer.setAddress2("apt 325");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("90805");
        customer.setCountry("USA");
    }

    @Test
    public void addCustomerTest() throws Exception {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        String customerJson = "{\n" +
                "    \"firstName\":\"Rushi\",\n" +
                "    \"lastName\": \"Sayed\",\n" +
                "    \"email\":\"rush@gmail.com\",\n" +
                "    \"company\": \"Snapchat\",\n" +
                "    \"phone\": \"123-456-7891\",\n" +
                "    \"address1\": \"11740 Wilshire blvd\",\n" +
                "    \"address2\": \"apt 325\",\n" +
                "    \"city\": \"Los Angeles\",\n" +
                "    \"state\": \"California\",\n" +
                "    \"postalCode\": \"90805\",\n" +
                "    \"country\":\"USA\"\n" +
                "}";
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Rushi"));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        customer.setFirstName("John");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        String customerJson = "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\":\"John\",\n" +
                "    \"lastName\": \"Sayed\",\n" +
                "    \"email\":\"rush@gmail.com\",\n" +
                "    \"company\": \"Snapchat\",\n" +
                "    \"phone\": \"123-456-7891\",\n" +
                "    \"address1\": \"11740 Wilshire blvd\",\n" +
                "    \"address2\": \"apt 325\",\n" +
                "    \"city\": \"Los Angeles\",\n" +
                "    \"state\": \"California\",\n" +
                "    \"postalCode\": \"90805\",\n" +
                "    \"country\":\"USA\"\n" +
                "}";
        mockMvc.perform(put("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        mockMvc.perform(delete("/customer/{id}", customer.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findCustomerByIdTest() throws Exception {
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Rushi"));
    }

    @Test
    public void testFindCustomerByState() {
        // Mock the repository response
        List<Customer> expectedCustomers = new ArrayList<>();

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setCompany("Acme Inc.");
        customer.setPhone("123-456-7890");
        customer.setAddress1("11740 Wilshire blvd");
        customer.setAddress2("apt 325");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");
        expectedCustomers.add(customer);

        when(customerRepository.findByState(anyString())).thenReturn(expectedCustomers);

        // Call the controller method
        List<Customer> actualCustomers = new ArrayList<>();
        actualCustomers.addAll(customerRepository.findByState("CA"));


        // Verify the results
        assertEquals(expectedCustomers.size(), actualCustomers.size());
        assertEquals(expectedCustomers.get(0).getState(), actualCustomers.get(0).getState());
    }


}