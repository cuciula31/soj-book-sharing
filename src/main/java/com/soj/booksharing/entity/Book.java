package com.soj.booksharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    public Book() {
    }

    public Book(String book_title, String author) {
        this.bookTitle = book_title;
        this.author = author;
    }

    @Column(name = "book_title")
    @NotNull
    private String bookTitle;
    @Column(name = "author")
    @NotNull
    private String author;

    @ManyToMany()
    @JoinTable(name = "book_owner",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("ownedBooks")
    private List<User> users = new ArrayList<>();

    @OneToOne(targetEntity = RentedBook.class, mappedBy = "book")
    @JsonIgnoreProperties({"users", "rentedFrom","rentedBook", "book"})
    private RentedBook rentedBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public RentedBook getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(RentedBook rentedBook) {
        this.rentedBook = rentedBook;
    }
}
