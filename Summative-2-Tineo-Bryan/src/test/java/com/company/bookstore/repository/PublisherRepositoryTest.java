package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepositoryTest {
    //Use this example for creating or everything:
//    {
//        "name": "Example Publisher",
//            "street": "123 Main St",
//            "city": "Anytown",
//            "state": "CA",
//            "postalCode": "12345",
//            "phone": "555-555-5555",
//            "email": "publisher@example.com"
//    }

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Before
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

    }

    @Test
    public void addGetDeletePublisher(){
        //We are creating publisher and author since book has foreign key for both(they all connect)
        //Creating a Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Anthony");
        publisher.setStreet("123 Main");
        publisher.setCity("LA");
        publisher.setState("CA");
        publisher.setPostalCode("12345");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("publi@gmail.com");

        publisher = publisherRepository.save(publisher);

        //Creating an author
        Author author = new Author();
        author.setFirstName("Jhon");
        author.setLastName("Doe");
        author.setStreet("123 Main St");
        author.setCity("Anytown");
        author.setState("CA");
        author.setPostalCode("12345");
        author.setPhone("555-123-4567");
        author.setEmail("johndoe@example.com");

        author = authorRepository.save(author);

        //Create Book
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setPublishDate("2022-01-01");
        book.setAuthorId(author.getId());
        book.setTitle("The book Title");
        book.setPublisherId(publisher.getId());
        book.setPrice(10);

        book = bookRepository.save(book);

        author.setBookId(book.getId());

        authorRepository.save(author);



        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());
        //Testing Adding publisher
        assertEquals(publisher1.get(),publisher);

        //I order to delete publisher we need to first delete book then author and then publisher
        bookRepository.deleteById(book.getId());
        authorRepository.deleteById(author.getId());
        publisherRepository.deleteById(publisher.getId());

        //Testing Get
        publisher1 = publisherRepository.findById((publisher.getId()));

        assertFalse(publisher1.isPresent());
    }

    @Test
    public void updatePublisher(){
        //We are creating publisher and author since book has foreign key for both(they all conect)
        //Creating a Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Anthony");
        publisher.setStreet("123 Main");
        publisher.setCity("LA");
        publisher.setState("CA");
        publisher.setPostalCode("12345");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("publi@gmail.com");

        publisher = publisherRepository.save(publisher);

        //Creating an author
        Author author = new Author();
        author.setFirstName("Jhon");
        author.setLastName("Doe");
        author.setStreet("123 Main St");
        author.setCity("Anytown");
        author.setState("CA");
        author.setPostalCode("12345");
        author.setPhone("555-123-4567");
        author.setEmail("johndoe@example.com");

        author = authorRepository.save(author);

        //Create Book
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setPublishDate("2022-01-01");
        book.setAuthorId(author.getId());
        book.setTitle("The book Title");
        book.setPublisherId(publisher.getId());
        book.setPrice(10);

        book = bookRepository.save(book);

        author.setBookId(book.getId());

        authorRepository.save(author);

        //Updating the Publisher information
        publisher.setName("Pablo Escobar");
        publisher.setEmail("pablito@gmail.com");

        //saving the changes
        publisherRepository.save(publisher);

        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());
        assertEquals(publisher1.get(),publisher);

    }

    @Test
    public void getAllPublishers(){
        //We are creating publisher and author since book has foreign key for both(they all connect)
        //Creating a Publisher
        Publisher publisher = new Publisher();
        publisher.setName("Anthony");
        publisher.setStreet("123 Main");
        publisher.setCity("LA");
        publisher.setState("CA");
        publisher.setPostalCode("12345");
        publisher.setPhone("555-555-5555");
        publisher.setEmail("publi@gmail.com");

        publisher = publisherRepository.save(publisher);

        //Creating an author
        Author author = new Author();
        author.setFirstName("Jhon");
        author.setLastName("Doe");
        author.setStreet("123 Main St");
        author.setCity("Anytown");
        author.setState("CA");
        author.setPostalCode("12345");
        author.setPhone("555-123-4567");
        author.setEmail("johndoe@example.com");

        author = authorRepository.save(author);

        //Create Book
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setPublishDate("2022-01-01");
        book.setAuthorId(author.getId());
        book.setTitle("The book Title");
        book.setPublisherId(publisher.getId());
        book.setPrice(10);

        book = bookRepository.save(book);

        author.setBookId(book.getId());

        authorRepository.save(author);

        //SECOND PUBLISHER, AUTHOR, and BOOK added to the tables
        Publisher publisher2 = new Publisher();
        publisher2.setName("Salvador");
        publisher2.setStreet("167 Main");
        publisher2.setCity("HI");
        publisher2.setState("MA");
        publisher2.setPostalCode("12345");
        publisher2.setPhone("555-555-5555");
        publisher2.setEmail("salva@gmail.com");

        publisher2 = publisherRepository.save(publisher2);

        //Creating an author
        Author author2 = new Author();
        author2.setFirstName("Connor");
        author2.setLastName("Especho");
        author2.setStreet("999 Main St");
        author2.setCity("Lima");
        author2.setState("LI");
        author2.setPostalCode("12345");
        author2.setPhone("555-123-4567");
        author2.setEmail("conn@example.com");

        author2 = authorRepository.save(author2);

        //Create Book
        Book book2 = new Book();
        book2.setIsbn("5674839274");
        book2.setPublishDate("2022-01-01");
        book2.setAuthorId(author2.getId());
        book2.setTitle("La Marmota Mala");
        book2.setPublisherId(publisher2.getId());
        book2.setPrice(10);

        book2 = bookRepository.save(book2);

        author2.setBookId(book2.getId());

        authorRepository.save(author2);

        List<Publisher> pList = publisherRepository.findAll();
        assertEquals(pList.size(),2);

    }

}