package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repo;


    //Here is an Object example that you can use to test this controller in insomnia
//    {
//            "firstName":"Rushi",
//            "lastName": "Sayed",
//            "email":"rush@gmail.com",
//            "company": "Snapchat",
//            "phone": "123-456-7891",
//            "address1": "11740 Wilshire blvd",
//            "address2": "apt 325",
//            "city": "Los Angeles",
//            "state": "California",
//            "postalCode": "90805",
//            "country":"USA"
//    }



    //Create a new customer record.
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer (@RequestBody Customer Customer){
        return  repo.save(Customer);
    }

    //Update an existing customer record.
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer){
        //When updating, make sure to add the id in the customer object that you
        // send to this path in order for JPA to update  and not add a new customer
        repo.save(customer);
    }

    //Delete an existing customer record.
    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
        repo.deleteById(id);
    }

    //Find a customer record by id.
    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable int id){

        Optional<Customer> cusomerFromRepo = repo.findById(id);
        if (cusomerFromRepo.isPresent()){
            //if there we find a custome with the given id
            //then return it
            return cusomerFromRepo.get();

        } else {
            //If we didn't found any customer with that id
            //then return null
            return null;
        }
    }


    //Find customer records by state.
    //In order to do this, I created a method for finding by state in the CustomerRepository(check customer repository to see method)
    //Find customer records by state.
    @GetMapping("/customers/state/{state}")
    public List<Customer> findCustomerByState(@PathVariable String state){

        //From the repository, we will store all customers with the state given into this list called "customersFromRepo"
        List<Customer> customersFromRepo = repo.findByState(state);

        if (!customersFromRepo.isEmpty()){
            //if there are customers found with the given state
            //then return them
            return customersFromRepo;

        } else {
            //If there are no customers found with that state
            //then return an empty list
            return new ArrayList<Customer>();
        }
    }


}
