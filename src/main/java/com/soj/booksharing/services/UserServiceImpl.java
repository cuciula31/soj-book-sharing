package com.soj.booksharing.services;

import com.soj.booksharing.data.RentingIntervals;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BooksRepository booksRepository;

    private final RentalRepository rentalRepository;

    public UserServiceImpl(UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository) {
        this.repository = repository;
        this.booksRepository = booksRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public User fetchUser(@NotNull Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<User> fetchAllUsers() {
        return repository.findAll();
    }

    @Override
    public String deleteUser(Long id) {
        repository.deleteById(id);
        return "User with id: %s deleted".formatted(id);
    }

    public String update(User user, Long id) {
        User toBeUpdated = repository.findById(id).get();

        if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
            toBeUpdated.setName(user.getName());
        }

        if (Objects.nonNull(user.getSurname()) && !"".equalsIgnoreCase(user.getSurname())) {
            toBeUpdated.setSurname(user.getSurname());
        }

        if (Objects.nonNull(user.getUser()) && !"".equalsIgnoreCase(user.getUser())) {
            toBeUpdated.setUser(user.getUser());
        }

        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            toBeUpdated.setEmail(user.getEmail());
        }

        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            toBeUpdated.setPassword(user.getPassword());
        }

        repository.save(toBeUpdated);

        return "User with id: %s updated".formatted(id);

    }

    @Override
    public String add(User user) {
        repository.save(user);
        return "User with id: %s added".formatted(user.getId());
    }

    @Override
    public String addExistingBook(Long userId, Long bookId) {
        User user = fetchUser(userId);
        Book book = booksRepository.getById(bookId);

        user.getOwnedBooks().add(book);
        book.getUsers().add(user);

        repository.save(user);
        booksRepository.save(book);

        return "User with id: %s updated".formatted(userId);
    }


    @Override
    public String addNewBook(Book book, Long userId) {
        User user = fetchUser(userId);
        book.getUsers().add(user);

        user.getOwnedBooks().add(book);

        repository.save(user);
        booksRepository.save(book);

        return "User with id: %s updated".formatted(userId);
    }

    @Override
    public String addRental(Long userId, Long bookId, Integer rentingPeriod) {
        User user = fetchUser(userId);
        Book book = booksRepository.getById(bookId);

        if (rentalRepository.findAll().stream().map(RentedBook::getBook).toList().contains(book)){
            return "We're sorry. Book with id: %s is already rented. You can add it on wishlist".formatted(bookId);
        }else{
            if (rentingPeriod>4){
                return "You can select between 1 and 4, that's representing weeks of rental";
            }else{
                RentedBook rentedBook = new RentedBook();
                rentedBook.setStartDate(RentingIntervals.getIntervalsForSpecificPeriod(rentingPeriod).getStartDate());
                rentedBook.setEndDate(RentingIntervals.getIntervalsForSpecificPeriod(rentingPeriod).getStartDate());
                rentedBook.setBook(book);
                rentedBook.setUser(user);
                user.getRentedBooks().add(rentedBook);
                book.setRentedBook(rentedBook);
                booksRepository.save(book);

                rentalRepository.save(rentedBook);

                repository.save(user);
                return "You have rented successfully a book!";
            }
        }
    }

    @Override
    public List<Book> fetchOwnedBooks(Long id) {
        return fetchUser(id).getOwnedBooks();
    }

    @Override
    public List<Book> rentedBooksByUser(Long id) {
        return repository.findById(id).get().getRentedBooks().stream().map(RentedBook::getBook).toList();
    }

    @Override
    public List<User> fetchAllUsersThatOwn(Long id) {
        Book book = booksRepository.getById(id);
        return book.getUsers();
    }


}
