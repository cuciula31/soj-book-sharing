package com.soj.booksharing.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class User implements UserDetails {

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
    @Size(min = 2, max = 30, message = "Name length must be between 2 (min) and 30 (max)")
    private String name;
    @Size(min = 2, max = 30, message = "Surname length must be between 2 (min) and 30 (max)")
    private String surname;
    @Size(min = 2, max = 30, message = "Username length must be between 2 (min) and 10 (max)")
    @JsonIgnore
    private String userName;
    @Size(min = 7, max = 200, message = "Surname length must be between 2 (min) and 30 (max)")
    private String password;
    @Size(min = 5, max = 30, message = "Email length must be between 5 (min) and 30 (max)")
    private String email;

    @ManyToMany(targetEntity = Book.class, mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"users", "rentedFrom", "rentedBook"})
    List<Book> ownedBooks;

    @OneToMany(targetEntity = RentedBook.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    Set<RentedBook> rentedBooks;

    @OneToMany(targetEntity = RentedBook.class, mappedBy = "rentedFrom", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"users", "rentedFrom", "rentedBook"})
    Set<RentedBook> rentedTo = new HashSet<>();

    @OneToMany(targetEntity = Wishlist.class, mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    Set<Wishlist> wishlist = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnoreProperties("user")
    List<Authority> authorities = new ArrayList<>();

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
    @Override
    public String getUsername() {
        return userName;
    }



    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public List<Book> getOwnedBooks() {
        return ownedBooks;
    }


    public Set<RentedBook> getRentedBooks() {
        return rentedBooks;
    }


    public Set<RentedBook> getRentedTo() {
        return rentedTo;
    }


    public Set<Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }
}
