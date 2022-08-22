package com.soj.booksharing.services;

import com.soj.booksharing.entity.PendingRental;
import com.soj.booksharing.entity.RentedBook;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalService {

    //Create
    ResponseEntity<String> addNew(RentedBook rentedBook);
//    String addNew(Long bookId, Long userId, RentingIntervals rentingIntervals);
    //There will let possibility create using RentedBook object, or using book/user id`s

    //Read
    ResponseEntity<List<RentedBook>> fetchAll();

    ResponseEntity<List<PendingRental>> fetchAllPending();

    ResponseEntity<RentedBook> fetchById(Long id);
    ResponseEntity<PendingRental> fetchPending();

    //Update
    ResponseEntity<String> update(RentedBook rentedBook, Long id);

    //Delete
    ResponseEntity<String> delete(Long id);

    //Read books
//    List<Book> rentedBooksByUser(Long userId);
    ResponseEntity<List<String>> availableBooks();

    ResponseEntity<Boolean> checkIfBookAvailable(Long bookId);

    ResponseEntity<String> extend(Long id);
}
