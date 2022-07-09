package com.soj.booksharing.services;

import com.soj.booksharing.data.RentalUtils;
import com.soj.booksharing.data.RentingIntervals;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        if (booksRepository.findAll().stream().anyMatch(b -> b.getBookTitle().equals(book.getBookTitle()))) {
            Book existentBook = booksRepository.findAll().stream().filter(b -> b.getBookTitle().equals(book.getBookTitle())).findFirst().get();
            existentBook.getUsers().add(user);
            user.getOwnedBooks().add(existentBook);
            booksRepository.save(existentBook);
        } else {
            book.getUsers().add(user);
            user.getOwnedBooks().add(book);
            booksRepository.save(book);
        }

        repository.save(user);

        return "User with id: %s updated".formatted(userId);
    }

    @Override
    public String addRental(Long userId, Long bookId, Integer rentingPeriod) {
        return RentalUtils.checkIfSuccess(userId, bookId, rentingPeriod, repository, booksRepository, rentalRepository);
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

    @Override
    public List<String> whoRentedMyBooks(Long userId) {
        User user = repository.findById(userId).get();
        List<Book> books = rentalRepository.findAll().stream().map(RentedBook::getBook).filter(b -> b.getUsers().contains(user)).toList();
        List<String> toBeReturned = new ArrayList<>();

        books.forEach(book -> {
            toBeReturned.add(book.getBookTitle() +
                    " " + "is rented by " + book.getRentedBook().getUser().getName() + " " + book.getRentedBook().getUser().getSurname() +
                    " until " + book.getRentedBook().getEndDate().toString());
        });


        return toBeReturned;
    }

}
