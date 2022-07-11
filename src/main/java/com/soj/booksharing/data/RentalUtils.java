package com.soj.booksharing.data;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class RentalUtils {



    private static void insertRelationsBetweenUserOwnerAndBook(RentedBook rentedBook, User user, User owner, Book book) {
        user.getRentedBooks().add(rentedBook);
        owner.getRentedTo().add(rentedBook);
        book.getRentedBook().add(rentedBook);
    }

    private static void saveRental(UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository, User user, User owner, Book book, RentedBook rentedBook) {
        repository.save(owner);
        booksRepository.save(book);
        rentalRepository.save(rentedBook);
        repository.save(user);
    }

    public static Boolean checkIfAvailableUsersEqualsZero(List<User> availableUsers) {
        return availableUsers.size() == 0;
    }

    private static Boolean checkIfRentingPeriodNotInGivenIntervals(int interval) {
        return Checkers.checkIfRentingIntervalIsInRange(interval);
    }


    private static String availableUsersEqualsZeroAlert(Long bookId) {
        return StringFormatters.unavailableForRenting(bookId);
    }

    private static String intervalGreaterThanMaximum() {
        return StringFormatters.invalidRentingPeriod();
    }

    public static String checkIfSuccess(Long userId, Long bookId, Integer rentingPeriod, UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository) {

        User user = UserUtils.getUserById(userId, repository);
        Book book = BookUtils.getById(bookId,booksRepository);
        List<User> availableUsers = book.getUsers();
        User owner = null;

        if (rentalRepository.findAll().size() > 0) {
            List<RentedBook> rentalsWithSameBook = rentalRepository.findAll().stream().filter(r -> r.getBook().equals(book)).toList();
            List<User> ownersThatRentedAlready = rentalsWithSameBook.stream().map(RentedBook::getRentedFrom).toList();

                for (User u : availableUsers){
                    if (!ownersThatRentedAlready.contains(u)){
                        owner = u;
                    }
                }
        } else {
            owner = availableUsers.get(0);
        }

        if (owner == null && rentalRepository.findAll().size() > 0) {
            return availableUsersEqualsZeroAlert(book.getId());
        } else {
            if (checkIfRentingPeriodNotInGivenIntervals(rentingPeriod)) {
                return intervalGreaterThanMaximum();
            } else {
                RentedBook rentedBook = new RentedBook(user,
                        owner,
                        book,
                        RentingIntervals.getIntervalsForSpecificPeriod(rentingPeriod).getStartDate(),
                        RentingIntervals.getIntervalsForSpecificPeriod(rentingPeriod).getEndDate(),
                        false);
                RentalUtils.insertRelationsBetweenUserOwnerAndBook(rentedBook, user, owner, book);
                RentalUtils.saveRental(repository, booksRepository, rentalRepository, user, owner, book, rentedBook);
                return "You have rented successfully a book!";
            }
        }


    }


    public static Boolean checkIfAvailable(Long bookId, BooksRepository booksRepository, RentalRepository rentalRepository){
        Book book = BookUtils.getById(bookId,booksRepository);
        List<User> availableUsers = book.getUsers();
        User owner = null;

        if (rentalRepository.findAll().size() > 0) {
            List<RentedBook> rentalsWithSameBook = rentalRepository.findAll().stream().filter(r -> r.getBook().equals(book)).toList();
            List<User> ownersThatRentedAlready = rentalsWithSameBook.stream().map(RentedBook::getRentedFrom).toList();

            for (User u : availableUsers){
                if (!ownersThatRentedAlready.contains(u)){
                    owner = u;
                }
            }
        } else {
            owner = availableUsers.get(0);
        }

        if (owner == null && rentalRepository.findAll().size() > 0) {
            return false;
        } else {
                return true;
        }
    }
}
