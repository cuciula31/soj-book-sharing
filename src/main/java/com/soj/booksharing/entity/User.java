package com.soj.booksharing.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {

    public User() {
    }

    public User(String name, String surname, String userName, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
    @NotNull

    private String surname;
    @NotNull
    @Size(min = 2, max = 30)
    private String userName;
    @NotNull
    @Size(min = 7, max = 30)
    private String password;
    //@NotNull
    private String email;

    @ManyToMany(targetEntity = Book.class, mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Book> ownedBooks;

    @OneToMany(targetEntity = RentedBook.class ,mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    Set<RentedBook> rentedBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String user) {
        this.userName = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public void setOwnedBooks(List<Book> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    public Set<RentedBook> getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(Set<RentedBook> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }
}
