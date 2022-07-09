package com.soj.booksharing.data;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;

import java.util.List;

public class RentalUtils {

    private static void eliminateIfRentedAlready(List<User> availableUsers, List<RentedBook> rentals, User user, Book book) {
        availableUsers.forEach(u -> {

            if (rentals.stream().map(RentedBook::getBook).toList().size() > 0 &&
                    rentals.stream().map(RentedBook::getBook).toList().contains(book)) {

                if (rentals.stream().filter(r -> r.getBook().equals(book)).findFirst().get().getRentedFrom().equals(user)) {
                    availableUsers.remove(user);
                }

            }

        });
    }

    private static void insertRelationsBetweenUserOwnerAndBook(RentedBook rentedBook, User user, User owner, Book book) {
        user.getRentedBooks().add(rentedBook);
        owner.getRentedTo().add(rentedBook);
        book.setRentedBook(rentedBook);
    }

    private static void saveRental(UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository, User user, User owner, Book book, RentedBook rentedBook) {
        repository.save(owner);
        booksRepository.save(book);
        rentalRepository.save(rentedBook);
        repository.save(user);
    }

    private static Boolean checkIfAvailableUsersEqualsZero(List<User> availableUsers) {
        return availableUsers.size() == 0;
    }

    private static Boolean checkIfRentingPeriodNotInGivenIntervals(int interval) {
        return interval > 4;
    }


    private static String availableUsersEqualsZeroAlert(Long bookId) {
        return "We're sorry. Book with id: %s is already rented. You can add it on wishlist".formatted(bookId);
    }

    private static String intervalGreaterThanMaximum() {
        return "You can select between 1 and 4, that's representing weeks of rental";
    }

    public static String checkIfSuccess(Long userId, Long bookId, Integer rentingPeriod, UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository) {

        User user = repository.findById(userId).get();
        Book book = booksRepository.findById(bookId).get();
        List<User> availableUsers = book.getUsers();

        eliminateIfRentedAlready(availableUsers, rentalRepository.findAll(), user, book);

        if (checkIfAvailableUsersEqualsZero(availableUsers)) {
            return availableUsersEqualsZeroAlert(book.getId());
        } else {
            if (checkIfRentingPeriodNotInGivenIntervals(rentingPeriod)) {
                return intervalGreaterThanMaximum();
            } else {
                User owner = availableUsers.get(0);
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
}
