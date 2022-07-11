package com.soj.booksharing.data;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;

import java.util.List;

public class Checkers {

    public static Boolean checkIfRentalsContainsSpecificBook(List<Book> mappedBooks, Book bookToBeChecked){
        return mappedBooks.contains(bookToBeChecked);
    }

    public static Boolean checkIfRentingIntervalIsInRange(int interval){return interval > 4;}

    public static Boolean bookAvailable(Book b, BooksRepository booksRepository, RentalRepository rentalRepository){
        return RentalUtils.checkIfAvailable(b.getId(), booksRepository, rentalRepository);
    }
}
