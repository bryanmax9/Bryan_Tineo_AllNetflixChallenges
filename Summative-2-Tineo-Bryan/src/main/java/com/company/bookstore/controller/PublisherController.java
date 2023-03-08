package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {




    @Autowired
    PublisherRepository publisherRepository;



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



    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher) {

        return publisherRepository.save(publisher);
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @DeleteMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        publisherRepository.deleteById(id);
    }

    @GetMapping("/publisher/{id}")
    public Publisher findPublisherById(@PathVariable int id) {

        Optional<Publisher> PublisherFromRepo = publisherRepository.findById(id);
        if (PublisherFromRepo.isPresent()) {
            return PublisherFromRepo.get();
        } else {
            return null;
        }

    }

    //Read all publishers
    @GetMapping("/allpublishers")
    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }
}
