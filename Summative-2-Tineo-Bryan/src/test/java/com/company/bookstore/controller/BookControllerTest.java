//package com.company.bookstore.controller;
//
//import com.company.bookstore.models.Book;
//import com.company.bookstore.repository.AuthorRepository;
//import com.company.bookstore.repository.BookRepository;
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
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private ObjectMapper mapper = new ObjectMapper();
//
//
//    //In class we didn't do Mockmvc with repositories
//    //It turns out that according to the documentation from Spring
//    // We need "@MockBean" in order for Spring to know the beans for our repository
//    //If we don't do this, we will get an error that beans are not found for bookRepository
//    //Here the sources that We used:
//    //https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/mock/mockito/MockBean.html
//    //https://www.baeldung.com/spring-boot-testing#webmvctest-annotation
//    @MockBean
//    private BookRepository bookRepository;
//
//
//
//    @BeforeEach
//    void setUp() {
//        // Initialize the mock objects used in the tests
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddAuthor() throws Exception{
//        //Create Book
//        Book book = new Book();
//        book.setIsbn("1234567890");
//        book.setPublishDate("2022-01-01");
//        book.setAuthorId(1);
//        book.setTitle("The book Title");
//        book.setPublisherId(1);
//        book.setPrice(10);
//
//        String inputJson = mapper.writeValueAsString(book);
//
//        mockMvc.perform(
//                post("/books")
//                        .content(inputJson)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated());
//    }
//
//    //In class we didn't went over update
//    //We used the following sources to guide:
//    //https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#integration-testing-support-jdbc-test-utils
//    @Test
//    public void testUpdateBook() throws Exception{
//        //Create Book
//        Book book = new Book();
//        book.setIsbn("1234567890");
//        book.setPublishDate("2022-01-01");
//        book.setAuthorId(1);
//        book.setTitle("The book Title");
//        book.setPublisherId(1);
//        book.setPrice(10);
//
//        //Mock Book repository to return the same book object when save() is called
//        when(bookRepository.save(book)).thenReturn(book);
//
//        //send PUT requestt to the "/books"  endpoint with the book object in the json body
//        mockMvc.perform(put("/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"id\": 1, \"isbn\": \"1234567890\", \"publisherDate\": \"2022-01-01\", \"authorId\": \"1\", \"title\": \"The book Title\", \"publisherId\": \"1\", \"price\": 10 }")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    //Delete also wasn't covered in class
//    //For this one we used the following sources, is the same as the  update source:
//    //https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-framework
//    @Test
//    public void testDeleteBook() throws Exception {
//        //Mock the Book repository to return an  Optional.empty when  findById() is called
//        when(bookRepository.findById(1)).thenReturn(Optional.empty());
//
//        //Sending Delete  request to "/book/{id}" endpoint
//        mockMvc.perform(MockMvcRequestBuilders.delete("/book/1")
//                .accept(MediaType.APPLICATION_JSON))
//        // Expect a 204 status code in the response
//                .andExpect(status().isNoContent());
//
//        //Now, we  verify that the BookRepository's deleteById  method was called in the correct id
//        verify(bookRepository, times(1)).deleteById(1);
//
//    }
//
//    //In class we didn't also learn about testing Get by id
//    //Here are the sources:
//    //https://www.baeldung.com/spring-boot-testing#crud
//    //https://spring.io/guides/gs/testing-web/
//    @Test
//    public void findBookById_ReturnsBook_Succes() throws Exception {
//        // Set  up  mock Book Object
//        //Create Book
//        Book book = new Book();
//        book.setId(1);
//        book.setIsbn("1234567890");
//        book.setPublishDate("2022-01-01");
//        book.setAuthorId(1);
//        book.setTitle("The book Title");
//        book.setPublisherId(1);
//        book.setPrice(10);
//
//        //set up mock Book repository
//        given(bookRepository.findById(1)).willReturn(Optional.of(book));
//
//
//
//        //Perform GET request
//        mockMvc.perform(get("/books/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id",is(1)))
//                .andExpect(jsonPath("$.isbn", is("1234567890")))
//                .andExpect(jsonPath("$.title", is("The book Title")));
//    }
//
//
//
//    //For this test, we also didn't got over this in class
//    //We used this sources  to create  this test:
//    //https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html
//    //https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
//    //https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.testing
//    @Test
//    public void findBooksByAuthorId_ReturnsListOfBooks() throws Exception {
//        List<Book> books = new ArrayList<>();
//        //Create Book
//        Book book = new Book();
//        book.setIsbn("1234567890");
//        book.setPublishDate("2022-01-01");
//        book.setAuthorId(1);
//        book.setTitle("The book Title");
//        book.setPublisherId(1);
//        book.setPrice(10);
//
//        //Create Book2
//        Book book2 = new Book();
//        book2.setIsbn("5674839274");
//        book2.setPublishDate("2022-01-01");
//        book2.setAuthorId(1);
//        book2.setTitle("La Marmota Mala");
//        book2.setPublisherId(2);
//        book2.setPrice(15);
//        books.add(book);
//        books.add(book2);
//        when(bookRepository.findByAuthorId(anyInt())).thenReturn(books);
//
//        mockMvc.perform(get("/books/author/{author_id}", 1)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].isbn").value("1234567890"))
//                .andExpect(jsonPath("$[0].authorId").value(1))
//                .andExpect(jsonPath("$[1].isbn").value("5674839274"))
//                .andExpect(jsonPath("$[1].authorId").value(1));
//    }
//
//    //Source: https://www.baeldung.com/spring-boot-testing
//    //Also: https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
//    @Test
//    public void testFindAllBooks() throws Exception{
//        List<Book> books = new ArrayList<>();
//        //Create Book
//        Book book = new Book();
//        book.setId(1);
//        book.setIsbn("1234567890");
//        book.setPublishDate("2022-01-01");
//        book.setAuthorId(1);
//        book.setTitle("The book Title");
//        book.setPublisherId(1);
//
//
//        //Create Book2
//        Book book2 = new Book();
//        book2.setIsbn("5674839274");
//        book2.setId(2);
//        book2.setPublishDate("2022-01-01");
//        book2.setAuthorId(1);
//        book2.setTitle("La Marmota Mala");
//        book2.setPublisherId(2);
//        book2.setPrice(15);
//        books.add(book);
//        books.add(book2);
//
//        when(bookRepository.findAll()).thenReturn(books);
//
//        mockMvc.perform(get("/allbooks"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$",hasSize(2)))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].isbn").value("1234567890"))
//                .andExpect(jsonPath("$[0].authorId").value(1))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].isbn").value("5674839274"))
//                .andExpect(jsonPath("$[1].authorId").value(1));
//    }
//
//}