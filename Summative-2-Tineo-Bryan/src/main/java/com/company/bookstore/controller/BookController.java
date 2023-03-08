package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    //Use this example for creating or everything:
//    {
//        "isbn": "1234567890",
//        "publishDate": "2022-01-01",
//        "authorId": 1,
//        "title": "The Book Title",
//        "publisherId": 1,
//        "price": 10
//    }



    //Create a new book record.
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook (@RequestBody Book book){
        return  bookRepository.save(book);
    }

    //Update an existing book record.
    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book){
        //When updating, make sure to add the id in the customer object that you
        // send to this path in order for JPA to update  and not add a new book
        bookRepository.save(book);
    }

    //Delete an existing book record.
    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
    }

    //Find a book record by id.
    @GetMapping("/books/{id}")
    public Book findBookById(@PathVariable int id){

        Optional<Book> bookFromRepo = bookRepository.findById(id);
        if (bookFromRepo.isPresent()){
            //if there we find a book with the given id
            //then return it
            return bookFromRepo.get();

        } else {
            //If we didn't found any book with that id
            //then return null
            return null;
        }
    }
    @GetMapping("/books/author/{author_id}")
    public List<Book> findBooksByAuthorId(@PathVariable Integer author_id) {

        List<Book> librosDelRepositorio = bookRepository.findByAuthorId(author_id);

        if (!librosDelRepositorio.isEmpty()){
            //if there are customers found with the given state
            //then return them
            return librosDelRepositorio;

        } else {
            //If there are no customers found with that state
            //then return an empty list
            return new ArrayList<Book>();
        }

    }


    //Read all books
    @GetMapping("/allbooks")
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }


}
