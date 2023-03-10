package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "book")
public class Book {


    //The @Column decorator is in oder for the attributes to match the values in the squema
    //This method I got it  from this website: https://docs.oracle.com/javaee/7/api/javax/persistence/Column.html
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "publish_date")
    private String publishDate;

    //My mento provided me help and a source that explains the OneToMany Relationship:
//    https://tenmilesquare.com/resources/software-development/spring-boot-jpa-relationship-quick-guide/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false)
    @JsonIgnore // add this annotation to prevent infinite recursion during serialization
    private Author author;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id", insertable = false, updatable = false)
    //For Json ignore, this is the source: https://fasterxml.github.io/jackson-annotations/javadoc/2.11/com/fasterxml/jackson/annotation/JsonIgnore.html
    @JsonIgnore // add this annotation to prevent infinite recursion during serialization
    private Publisher publisher;

    @Column(name = "price")
    private Integer price;

    public Book(){}

    public Book(int id, String isbn, String publishDate, Author author, String title, Publisher publisher, Integer price) {
        this.id = id;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getPublishDate(), book.getPublishDate()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getPublisher(), book.getPublisher()) && Objects.equals(getPrice(), book.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getPublishDate(), getAuthor(), getTitle(), getPublisher(), getPrice());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", price=" + price +
                '}';
    }
}
