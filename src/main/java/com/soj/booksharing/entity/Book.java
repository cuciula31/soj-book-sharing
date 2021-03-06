package com.soj.booksharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min = 2, max = 50, message = "Book title length must be between 2 (min) and 50 (max)")
    private String bookTitle;
    @Column(name = "author")
    @Size(min = 2, max = 30, message = "Book author length must be between 2 (min) and 30 (max)")
    private String author;

    @ManyToMany()
    @JoinTable(name = "book_owner",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("ownedBooks")
    private List<User> users = new ArrayList<>();

    @OneToMany(targetEntity = RentedBook.class, mappedBy = "book")
    @JsonIgnoreProperties({"users", "rentedFrom","rentedBook", "book"})
    private List<RentedBook> rentedBook = new ArrayList<>();

    @OneToMany(targetEntity = Wishlist.class, mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Wishlist> wishlist = new ArrayList<>();

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

    public List<RentedBook> getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(List<RentedBook> rentedBook) {
        this.rentedBook = rentedBook;
    }

    public List<Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }
}
