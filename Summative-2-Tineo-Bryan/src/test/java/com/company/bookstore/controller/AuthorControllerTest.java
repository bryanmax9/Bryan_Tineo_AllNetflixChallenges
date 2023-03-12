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
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
//
//    //In class we didn't do Mockmvc with repositories
//    //It turns out that according to the documentation from Spring
//    // We need "@MockBean" in order for Spring to know the beans for our repository
//    //If we don't do this, we will get an error that beans are not found for authorRepository
//    //Here the sources that We used:
//    //https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/mock/mockito/MockBean.html
//    //https://www.baeldung.com/spring-boot-testing#webmvctest-annotation
//    @MockBean
//    private AuthorRepository authorRepository;
//
//    private Author testAuthor;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize the mock objects used in the tests
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    public void addGetDeleteAuthor(){
//
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
//
//        author = authorRepository.save(author);
//
//        Optional<Author> author1 = authorRepository.findById(author.getId());
//
//        //Testing Add
//        assertTrue(author1.isPresent());
//        assertEquals(author1.get(), author);
//
//        authorRepository.deleteById(author.getId());
//
//        //Testing Get
//        author1 = authorRepository.findById(author.getId());
//
//        //Testing Delete
//        assertFalse(author1.isPresent());
//    }
//
//
//
//
//    //In class we didn't went over update
//    //We used the following sources to guide:
//    //https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#integration-testing-support-jdbc-test-utils
//    @Test
//    public void testUpdateAuthor() throws Exception {
//        // Create an Author object to send in the request body
//        Author author = new Author();
//        author.setId(1);
//        author.setFirstName("John");
//        author.setLastName("Doe");
//        author.setStreet("123 Main St");
//        author.setCity("Anytown");
//        author.setState("CA");
//        author.setPostalCode("12345");
//        author.setPhone("555-123-4567");
//        author.setEmail("johndoe@example.com");
//
//        // Mock the AuthorRepository to return the same Author object when save() is called
//        when(authorRepository.save(author)).thenReturn(author);
//
//        // Send a PUT request to the "/authors" endpoint with the Author object in the request body
//        mockMvc.perform(MockMvcRequestBuilders.put("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"street\": \"123 Main St\", \"city\": \"Anytown\", \"state\": \"CA\", \"postalCode\": \"12345\", \"phone\": \"555-123-4567\", \"email\": \"johndoe@example.com\", \"bookId\": 0 }")
//                        .accept(MediaType.APPLICATION_JSON))
//                // Expect a 204 status code in the response
//                .andExpect(status().isNoContent());
//    }
//
//    //Delete also wasn't covered in class
//    //For this one we used the following sources, is the same as the  update source:
//    //https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-framework
//    @Test
//    public void testDeleteAuthor() throws Exception {
//        // Mock the AuthorRepository to return an Optional.empty() when findById() is called
//        when(authorRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Send a DELETE request to the "/author/{id}" endpoint
//        mockMvc.perform(MockMvcRequestBuilders.delete("/author/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                // Expect a 204 status code in the response
//                .andExpect(status().isNoContent());
//
//        // Verify that the AuthorRepository's deleteById() method was called with the correct id
//        verify(authorRepository, times(1)).deleteById(1);
//    }
//
//
//    //In class we didn't also learn about testingget by id
//    //Here are the sources:
//    //https://www.baeldung.com/spring-boot-testing#crud
//    //https://spring.io/guides/gs/testing-web/
//    @Test
//    public void findAuthorById_ReturnsAuthor_Success() throws Exception {
//        // Set up mock Author object
//        Author author = new Author();
//        author.setId(1);
//        author.setFirstName("John");
//        author.setLastName("Doe");
//        author.setStreet("123 Main St");
//        author.setCity("Anytown");
//        author.setState("CA");
//        author.setPostalCode("12345");
//        author.setPhone("555-123-4567");
//        author.setEmail("johndoe@example.com");
//
//        // Set up mock AuthorRepository
//        given(authorRepository.findById(1)).willReturn(Optional.of(author));
//
//        // Perform GET request
//        mockMvc.perform(get("/authors/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("John")))
//                .andExpect(jsonPath("$.lastName", is("Doe")));
//    }
//
//    //Source: https://www.baeldung.com/spring-boot-testing
//    //Also: https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
//    @Test
//    public void testFindAllAuthors() throws Exception {
//        List<Author> authors = new ArrayList<>();
//        Author author1 = new Author();
//        author1.setId(1);
//        author1.setFirstName("John");
//        author1.setLastName("Doe");
//        authors.add(author1);
//
//        Author author2 = new Author();
//        author2.setId(2);
//        author2.setFirstName("Jane");
//        author2.setLastName("Doe");
//        authors.add(author2);
//
//        when(authorRepository.findAll()).thenReturn(authors);
//
//        mockMvc.perform(get("/allauthors"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].firstName").value("John"))
//                .andExpect(jsonPath("$[0].lastName").value("Doe"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].firstName").value("Jane"))
//                .andExpect(jsonPath("$[1].lastName").value("Doe"));
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}