package com.soj.booksharing.services;

import com.soj.booksharing.dao.UserDAO;
import com.soj.booksharing.data.RentingIntervals;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;

import java.util.Date;
import java.util.List;

public interface RentalService {

    //Create
    String addNew(RentedBook rentedBook);
//    String addNew(Long bookId, Long userId, RentingIntervals rentingIntervals);
    //There will let possibility create using RentedBook object, or using book/user id`s

    //Read
    List<RentedBook> fetchAll();

    RentedBook fetchById(Long id);

    //Update
    String update(RentedBook rentedBook, Long id);

    //Delete
    String delete(Long id);

    //Read books
//    List<Book> rentedBooksByUser(Long userId);
    List<Book> availableBooks();

//    List<String> whoRentedMyBooks(Long userId);

}
