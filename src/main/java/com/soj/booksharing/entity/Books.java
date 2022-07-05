package com.soj.booksharing.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Books {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    public Books() {
    }

    public Books(String book_title, String author) {
        this.bookTitle = book_title;
        this.author = author;
    }

    @Column(name = "book_title")
    @NotNull
    private String bookTitle;
    @Column(name = "author")
    @NotNull
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}
