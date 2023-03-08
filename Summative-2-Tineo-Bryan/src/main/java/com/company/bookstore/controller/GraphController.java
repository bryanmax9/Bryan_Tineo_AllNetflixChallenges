package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphController {

//    Wiring Repositories
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;



    @QueryMapping
    public Author findAuthorById(@Argument Integer id){
        return authorRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Book findBookById(@Argument Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument Integer id){
        return publisherRepository.findById(id).orElse(null);
    }





}
