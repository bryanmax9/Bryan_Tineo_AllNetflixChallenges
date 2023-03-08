package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {




    @Autowired
    AuthorRepository authorRepository;



    //Use this example for creating or everything:
//    {
//        "firstName": "John",
//            "lastName": "Doe",
//            "street": "123 Main St",
//            "city": "Anytown",
//            "state": "CA",
//            "postalCode": "12345",
//            "phone": "555-123-4567",
//            "email": "johndoe@example.com",
//            "bookId": "0"
//
//    }



    //Create a new author record.
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor (@RequestBody Author author){
        return  authorRepository.save(author);
    }

    //Update an existing customer record.
    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthors(@RequestBody Author author){
        //When updating, make sure to add the id in the author object that you
        // send to this path in order for JPA to update  and not add a new author
        authorRepository.save(author);
    }

    //Delete an existing author record.
    @DeleteMapping("/author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id){
        authorRepository.deleteById(id);
    }

    //Find a Author record by id.
    @GetMapping("/authors/{id}")
    public Author findAuthorById(@PathVariable int id){

        Optional<Author> AuthorFromRepo = authorRepository.findById(id);
        if (AuthorFromRepo.isPresent()){
            //if there we find a authors with the given id
            //then return it
            return AuthorFromRepo.get();

        } else {
            //If we didn't found any Author with that id
            //then return null
            return null;
        }
    }

    //Read all authors
    @GetMapping("/allauthors")
    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }



}
