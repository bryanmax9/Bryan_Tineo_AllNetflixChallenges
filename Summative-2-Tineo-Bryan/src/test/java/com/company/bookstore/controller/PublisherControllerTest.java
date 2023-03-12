//package com.company.bookstore.controller;
//
//import com.company.bookstore.models.Publisher;
//import com.company.bookstore.repository.PublisherRepository;
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
//@WebMvcTest(PublisherController.class)
//public class PublisherControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private ObjectMapper mapper = new ObjectMapper();
//
//    //In class we didn't do Mockmvc with repositories
//    //It turns out that according to the documentation from Spring
//    // We need "@MockBean" in order for Spring to know the beans for our repository
//    //If we don't do this, we will get an error that beans are not found for authorRepository
//    //Here the sources that We used:
//    //https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/mock/mockito/MockBean.html
//    //https://www.baeldung.com/spring-boot-testing#webmvctest-annotation
//    @MockBean
//    private PublisherRepository publisherRepository;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize the mock objects used in the tests
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddPublisher() throws Exception{
//        //Creating a Publisher
//        Publisher publisher = new Publisher();
//        publisher.setName("Anthony");
//        publisher.setStreet("123 Main");
//        publisher.setCity("LA");
//        publisher.setState("CA");
//        publisher.setPostalCode("12345");
//        publisher.setPhone("555-555-5555");
//        publisher.setEmail("publi@gmail.com");
//
//        String inputJson = mapper.writeValueAsString(publisher);
//
//        mockMvc.perform(
//                post("/publishers")
//                        .content(inputJson)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated());
//
//    }
//
//    //In class we didn't went over update
//    //We used the following sources to guide:
//    //https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#integration-testing-support-jdbc-test-utils
//    @Test
//    public void testUpdatePublisher() throws Exception{
//        //Creating a Publisher
//        Publisher publisher = new Publisher();
//        publisher.setId(1);
//        publisher.setName("Anthony");
//        publisher.setStreet("123 Main");
//        publisher.setCity("LA");
//        publisher.setState("CA");
//        publisher.setPostalCode("12345");
//        publisher.setPhone("555-555-5555");
//        publisher.setEmail("publi@gmail.com");
//
//        //Mock the publisher repository to return the same publisher objec when save() is called
//        when(publisherRepository.save(publisher)).thenReturn(publisher);
//
//        //Send a  PUT request to the  "/publishers" endpoint with the  Publisher object in the request body
//        mockMvc.perform(MockMvcRequestBuilders.put("/publishers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"id\": 1, \"name\": \"Anthony\", \"street\": \"123 Main\", \"city\": \"LA\", \"state\": \"CA\", \"postalCode\": \"12345\", \"phone\": \"555-555-5555\", \"email\": \"publi@gmail.com\" }")
//                .accept(MediaType.APPLICATION_JSON))
//                //Expect a 204 status code in the response
//                .andExpect(status().isNoContent());
//
//    }
//
//    //Delete also wasn't covered in class
//    //For this one we used the following sources, is the same as the  update source:
//    //https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-framework
//    @Test
//    public void testDeletePublisher() throws Exception{
//        // Mock  the Publisher repository to return an Optional.empty() when findById is called
//        when(publisherRepository.findById(1)).thenReturn(Optional.empty());
//
//        //Send Delete request to the "/publisher/{id}" endpoint
//        mockMvc.perform(MockMvcRequestBuilders.delete("/publisher/1")
//                .accept(MediaType.APPLICATION_JSON))
//                //Expect  a 204 status  code in response
//                .andExpect(status().isNoContent());
//
//        //verify that the publisher repository's deleteById() methos was called with the correctt id
//        verify(publisherRepository,times(1)).deleteById(1);
//    }
//
//    //In class we didn't also learn about testingget by id
//    //Here are the sources:
//    //https://www.baeldung.com/spring-boot-testing#crud
//    //https://spring.io/guides/gs/testing-web/
//    @Test
//    public void findPublisherById_ReturnsPublisher_Success() throws Exception{
//        //Creating a Publisher
//        Publisher publisher = new Publisher();
//        publisher.setId(1);
//        publisher.setName("Anthony");
//        publisher.setStreet("123 Main");
//        publisher.setCity("LA");
//        publisher.setState("CA");
//        publisher.setPostalCode("12345");
//        publisher.setPhone("555-555-5555");
//        publisher.setEmail("publi@gmail.com");
//
//        //set up mock  PublisherRepository
//        given(publisherRepository.findById(1)).willReturn(Optional.of(publisher));
//
//        //Perform GET request
//        mockMvc.perform(get("/publisher/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Anthony")))
//                .andExpect(jsonPath("$.city", is("LA")));
//
//    }
//
//
//    //Source: https://www.baeldung.com/spring-boot-testing
//    //Also: https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
//    @Test
//    public void testFindAllPublishers() throws Exception{
//        List<Publisher> publishers = new ArrayList<>();
//        //Creating a Publisher
//        Publisher publisher = new Publisher();
//        publisher.setId(1);
//        publisher.setName("Anthony");
//        publisher.setStreet("123 Main");
//        publisher.setCity("LA");
//        publisher.setState("CA");
//        publisher.setPostalCode("12345");
//        publisher.setPhone("555-555-5555");
//        publisher.setEmail("publi@gmail.com");
//        publishers.add(publisher);
//
//        //Creating a second publisher
//        Publisher publisher2 = new Publisher();
//        publisher2.setId(2);
//        publisher2.setName("Emily");
//        publisher2.setStreet("456 Oak");
//        publisher2.setCity("New York");
//        publisher2.setState("NY");
//        publisher2.setPostalCode("67890");
//        publisher2.setPhone("555-123-4567");
//        publisher2.setEmail("emily.publi@gmail.com");
//        publishers.add(publisher2);
//
//        when(publisherRepository.findAll()).thenReturn(publishers);
//
//        mockMvc.perform(get("/allpublishers"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Anthony"))
//                .andExpect(jsonPath("$[0].city").value("LA"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].name").value("Emily"))
//                .andExpect(jsonPath("$[1].city").value("New York"));
//
//    }
//
//
//
//
//
//}