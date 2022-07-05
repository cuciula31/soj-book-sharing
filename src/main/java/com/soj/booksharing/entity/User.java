package com.soj.booksharing.entity;


import com.sun.istack.NotNull;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    public User() {
    }

    public User(String name, String surname, String userName, String password) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
    }

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    @NotBlank
    @Size(min=2, max=30)
    private String name;
    @NotNull

    private String surname;
    @NotNull
    @Size(min=2, max=30)
    private String userName;
    @NotNull
    @Size(min=7, max=30)
    private String password;

    @NotNull
    private String email;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "book_owner",
    joinColumns = @JoinColumn(name ="user_id"),
    inverseJoinColumns = @JoinColumn(name ="book_id"))
    private Set<Books> books = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<Books> getBooks(){
        return books;
    }

    public void setBooks(Set<Books> books){
        this.books = books;
    }
}
