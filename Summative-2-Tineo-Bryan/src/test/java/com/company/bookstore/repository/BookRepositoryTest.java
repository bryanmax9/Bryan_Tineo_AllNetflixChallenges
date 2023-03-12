package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTest {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Before
    public void setUp() throws Exception{

        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

    }

    @Transactional
    @Test
    public void addGetDeleteBooks() {
        // Creating a Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Anthony");
        publisher.setStreet("123 Main");
        publisher.setCity("LA");
        publisher.setState("CA");
        publisher.setPostalCode("12345");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("publi@gmail.com");

        publisher = publisherRepository.save(publisher);

        // Creating an author
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("123 Main St");
        author.setCity("Anytown");
        author.setState("CA");
        author.setPostalCode("12345");
        author.setPhone("555-123-4567");
        author.setEmail("johndoe@example.com");

        author = authorRepository.save(author);

        // Create Book
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setPublishDate("2022-01-01");
        book.setAuthor(author);
        book.setTitle("The book Title");
        book.setPublisher(publisher);
        book.setPrice(10);

        book = bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());
        // testing ADD
        assertEquals(book1.get(), book);


        bookRepository.deleteById(book.getId());
        // testing GET
        book1 = bookRepository.findById(book.getId());

        // testing DELETE
        assertFalse(book1.isPresent());
    }

    @Transactional
    @Test
    public void updateBook(){
        // Creating a Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Anthony");
        publisher.setStreet("123 Main");
        publisher.setCity("LA");
        publisher.setState("CA");
        publisher.setPostalCode("12345");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("publi@gmail.com");

        publisher = publisherRepository.save(publisher);

        // Creating an author
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("123 Main St");
        author.setCity("Anytown");
        author.setState("CA");
        author.setPostalCode("12345");
        author.setPhone("555-123-4567");
        author.setEmail("johndoe@example.com");

        author = authorRepository.save(author);

        // Create Book
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setPublishDate("2022-01-01");
        book.setAuthor(author);
        book.setTitle("The book Title");
        book.setPublisher(publisher);
        book.setPrice(10);

        book = bookRepository.save(book);

        //Updating the book
        book.setTitle("Los tres marineros");
        book.setPrice(20);

        bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());
        //Testing update
        assertEquals(book1.get(),book);
    }

    @Transactional
    @Test
    public void testFindBooksByAuthor() {
        // create an author
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setStreet("123 Main St");
        author.setCity("Anytown");
        author.setState("CA");
        author.setPostalCode("12345");
        author.setPhone("555-123-4567");
        author.setEmail("johndoe@example.com");




        // create a book object
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setIsbn("1234567890");
        book1.setPrice(20);




        // create a list of books
        List<Book> books = new ArrayList<>();
        books.add(book1);

        author.setBooks(books);

        author = authorRepository.save(author);

        book1.setAuthor(author);

        bookRepository.save(book1);



        // call the findByAuthor method and pass in the author object
        List<Book> booksByAuthor = bookRepository.findByAuthor(author);

        // assert that the list of books returned by findByAuthor method contains the book objects we saved
        assertEquals(books, booksByAuthor);
    }










}


