package com.soj.booksharing.data;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;

public class StringFormatters {


    //User

    public static String userAdded(Long id){
        return "User with id: %s added".formatted(id);
    }
    public static String userDeleted(Long id){
        return "User with id: %s deleted".formatted(id);
    }

    public static String userUpdated(Long id){
        return "User with id: %s updated".formatted(id);
    }

    //Books

    public static String bookAdded(Long id){
        return "Book with id: %s added".formatted(id);
    }

    public static String bookUpdated(Long id){
        return "Book with id: %s updated".formatted(id);
    }

    public static String bookDeleted(Long id){
        return "Book with id: %s deleted".formatted(id);
    }

    public static String bookWithTitleIsAvailable(Book b){
        return b.getBookTitle() + " by " + b.getAuthor() + " is available for renting";
    }

    public static String bookWithTitleIsUnavailable(Book b){
        return b.getBookTitle() + " by " + b.getAuthor() + " is unavailable for renting until " + b.getRentedBook().get(0).getEndDate();
    }

    public static String rentalExtended(Long id){
        return "Rental with id: %s successfully extended for one week".formatted(id);
    }

    public static String rentalFailed(){
        return "Your rental has extended before, you can't extend anymore";
    }

    //Rentals
    public static String availableBook(Book book) {
        return book.getBookTitle() + " by " + book.getAuthor() + " is available for renting";
    }

    public static String unavailableForRenting(Long bookId){
        return "We're sorry. Book with id: %s is already rented. You can add it on wishlist".formatted(bookId);
    }

    public static String invalidRentingPeriod(){
        return "You can select between 1 and 4, that's representing weeks of rental";
    }

    public static String whoRentedFromMe(RentedBook rentedBook){
        return rentedBook.getUser().getName() + " " + rentedBook.getUser().getSurname() + " rented " + rentedBook.getBook().getBookTitle() + " by " + rentedBook.getBook().getAuthor() + " until date of " + rentedBook.getEndDate();
    }

    public static String rental(RentedBook r){
        return "Rental with number " + r.getId() + " " + r.getBook().getBookTitle() + " by " + r.getBook().getAuthor() + " must be returned at date of " + r.getEndDate() + " to " + r.getRentedFrom().getName() + " " + r.getRentedFrom().getSurname();

    }

    //Wishlist
    public static String bookAddedToWishList(Book book){
        return book.getBookTitle() + " " + book.getAuthor() + " added to your wishlist";
    }

    public static String bookFailedToAdd(Book book){
        return book.getBookTitle() + " " + book.getAuthor() + " is already in your wishlist";
    }

    public static String wishRemovalFailed(){
        return "This wish doesn't exist";
    }

    public static String wishRemoved(Book b){
        return b.getBookTitle() + " by " + b.getAuthor() + " has been removed from your wishlist";
    }

    public static String itemWithIdNotFound(Long id){
        return "Item with id %s not found".formatted(id);
    }
}
