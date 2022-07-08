package com.soj.booksharing.dao;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.User;

import java.util.Date;

public class RentalDAO {
    private User rentedBy;
    private Book rentedBook;
    private Date rentedUntil;
}
