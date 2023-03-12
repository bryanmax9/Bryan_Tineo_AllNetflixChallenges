//package com.company.bookstore.controller;
//
//
//import com.company.bookstore.models.Author;
//import com.company.bookstore.repository.AuthorRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.mockito.BDDMockito.given;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(AuthorController.class)
//public class AuthorControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @MockBean
//    private AuthorRepository authorRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void addGetDeleteAuthor() throws Exception {
//        //Creating an author
//        Author author = new Author();
//        author.setFirstName("Jhon");
//        author.setLastName("Doe");
//        author.setStreet("123 Main St");
//        author.setCity("Anytown");
//        author.setState("CA");
//        author.setPostalCode("12345");
//        author.setPhone("555-123-4567");
//        author.setEmail("johndoe@example.com");
//
//        when(authorRepository.save(any(Author.class))).thenReturn(author);
//
//        mockMvc.perform(post("/authors/{bookId}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(author)))
//                .andExpect(status().isCreated());
//
//        Optional<Author> author1 = authorRepository.findById(author.getId());
//        assertTrue(author1.isPresent());
//        assertEquals(author1.get(), author);
//
//        mockMvc.perform(delete("/author/{id}", author.getId()))
//                .andExpect(status().isNoContent());
//
//        author1 = authorRepository.findById(author.getId());
//        assertFalse(author1.isPresent());
//    }
//
//
//
//
//}

