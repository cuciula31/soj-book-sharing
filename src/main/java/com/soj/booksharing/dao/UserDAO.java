package com.soj.booksharing.dao;

import com.soj.booksharing.entity.Book;

import java.util.List;

public class UserDAO {
    private String name;
    private String surname;
    private List<Book> ownedBooks;
    private List<RentalDAO> rentedBooks;
}
