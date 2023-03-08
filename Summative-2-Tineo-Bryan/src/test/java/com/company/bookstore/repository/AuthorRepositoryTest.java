package com.company.bookstore.repository;


import com.company.bookstore.models.Author;
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
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Before
    public void setUp() throws Exception{
        authorRepository.deleteAll();
    }


    @Test
    public void addGetDeleteAuthor(){

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
        author.setBookId(0);

        author = authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());

        //Testing Add        assertEquals(author1.get(),author);

        authorRepository.deleteById(author.getId());

        //Testing Get
        author1 = authorRepository.findById(author.getId());


        //Testing Delete
        assertFalse(author1.isPresent());


    }

    @Test
    public void updateAuthor(){
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
        author.setBookId(0);

        author = authorRepository.save(author);

        //Updating the author
        author.setEmail("hola@gmail.com");
        author.setCity("Santa Monica");

        authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());
        //Testing update
        assertEquals(author1.get(),author);
    }

    @Test
    public void getAllAuthors(){
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
        author.setBookId(0);

        author = authorRepository.save(author);

        author = new Author();
        author.setFirstName("Salvador");
        author.setLastName("Murriel");
        author.setStreet("Ochoa Street");
        author.setState("MA");
        author.setPostalCode("12345");
        author.setPhone("777-999-3333");
        author.setEmail("saldorva@gmail.com");
        author.setCity("Lima");
        author.setBookId(0);

        author = authorRepository.save(author);

        List<Author> aList = authorRepository.findAll();
        assertEquals(aList.size(),2);
    }

}