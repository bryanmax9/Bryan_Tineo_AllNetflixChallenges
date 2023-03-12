package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class BookController {

    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

//    Use this example for creating or everything:
//    {
//        "isbn": "1234567890",
//        "publishDate": "2022-01-01",
//        "title": "The Book Title",
//        "price": 10,
//    }

    //Create a new book record.
    @PostMapping("/books/{authorId}/{publisherId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book, @PathVariable int authorId, @PathVariable int publisherId) {
        // fetch the author object from the author table
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Publisher> publ = publisherRepository.findById(publisherId);

        if (author.isPresent()) {
            book.setPublisher(publ.get());
            book.setAuthor(author.get());
            return bookRepository.save(book);
        } else {
            throw new RuntimeException();
        }
    }

    //Update an existing book record.
    @PutMapping("/books/{authorId}/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book,@PathVariable int authorId, @PathVariable int publisherId) {
        //When updating, make sure to add the id in the customer object that you
        // send to this path in order for JPA to update  and not add a new book
        // fetch the author object from the author table
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Publisher> publ = publisherRepository.findById(publisherId);
        if (author.isPresent()) {
            book.setPublisher(publ.get());
            book.setAuthor(author.get());
            bookRepository.save(book);
        } else {
            throw new RuntimeException();
        }


    }

    //Delete an existing book record.
    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
    }

    //Find a book record by id.
    @GetMapping("/books/{id}")
    public Book findBookById(@PathVariable int id) {

        Optional<Book> bookFromRepo = bookRepository.findById(id);
        if (bookFromRepo.isPresent()) {
            //if there we find a book with the given id
            //then return it
            return bookFromRepo.get();

        } else {
            //If we didn't found any book with that id
            //then return null
            return null;
        }
    }

    @GetMapping("/books/author/{authorId}")
    public List<Book> findBooksByAuthorId(@PathVariable Author authorId) {

        List<Book> librosDelRepositorio = bookRepository.findAllByAuthor(authorId);

        if (!librosDelRepositorio.isEmpty()) {
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
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }


}