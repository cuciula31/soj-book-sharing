package com.soj.booksharing.services;

import com.soj.booksharing.data.*;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.entity.Wishlist;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;
import com.soj.booksharing.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BooksRepository booksRepository;
    private final RentalRepository rentalRepository;
    private final WishlistRepository wishlistRepository;

    public UserServiceImpl(UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository, WishlistRepository wishlistRepository) {
        this.repository = repository;
        this.booksRepository = booksRepository;
        this.rentalRepository = rentalRepository;
        this.wishlistRepository = wishlistRepository;
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
        return StringFormatters.userDeleted(id);
    }

    public String update(User user, Long id) {

        User toBeUpdated = repository.findById(id).get();
        UserUtils.userUpdate(user, toBeUpdated);
        repository.save(toBeUpdated);

        return StringFormatters.userUpdated(id);

    }

    @Override
    public String add(User user) {
        repository.save(user);
        return StringFormatters.userAdded(user.getId());
    }

    @Override
    public String addExistingBook(Long userId, Long bookId) {
        User user = fetchUser(userId);
        Book book = BookUtils.getById(bookId, booksRepository);

        user.getOwnedBooks().add(book);
        book.getUsers().add(user);

        repository.save(user);
        booksRepository.save(book);

        return StringFormatters.userUpdated(userId);
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

        return StringFormatters.userUpdated(userId);
    }

    @Override
    public String addToWishlist(Long userId, Long bookId) {
        User user = fetchUser(userId);
        Book book = BookUtils.getById(bookId, booksRepository);

        if (WishlistUtils.isAlready(bookId, fetchUser(userId).getWishlist().stream().toList(), booksRepository)) {
            return StringFormatters.bookFailedToAdd(book);
        } else {
            Wishlist wishlist = new Wishlist();

            user.getWishlist().add(wishlist);
            book.getWishlist().add(wishlist);

            wishlist.setUser(user);
            wishlist.setBook(book);

             if (RentalUtils.checkIfAvailable(bookId,booksRepository,rentalRepository)){
                 wishlist.setEstimatedDate(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
             }else{
                 wishlist.setEstimatedDate(book.getRentedBook().get(0).getEndDate());
             }

            repository.save(user);
            booksRepository.save(book);
            wishlistRepository.save(wishlist);

            return StringFormatters.bookAddedToWishList(book);
        }
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
    public List<String> rentedBooksByUser(Long id) {
        List<String> toBeReturned = new ArrayList<>();
        fetchUser(id).getRentedBooks().forEach(r -> {
            toBeReturned.add(StringFormatters.rental(r));
        });
        return toBeReturned;
    }


    @Override
    public List<User> fetchAllUsersThatOwn(Long id) {
        Book book = booksRepository.getById(id);
        return book.getUsers();
    }

    @Override
    public List<String> whoRentedMyBooks(Long userId) {
        User user = repository.findById(userId).get();
        List<String> toBeReturned = new ArrayList<>();

        List<RentedBook> rentedBooks = rentalRepository.findAll().stream().filter(rb -> rb.getRentedFrom().equals(user)).toList();

        for (RentedBook rentedBook : rentedBooks) {
            toBeReturned.add(StringFormatters.whoRentedFromMe(rentedBook));
        }

        return toBeReturned;
    }

    @Override
    public List<Wishlist> wishListByUserId(Long userId) {
        return fetchUser(userId).getWishlist().stream().toList();
    }

    @Override
    public String deleteWish(Long userId, Integer wish){
        User user = fetchUser(userId);
        List<Wishlist> wishlist = new ArrayList<>(user.getWishlist().stream().toList());
        if (wish > wishlist.size()){
            return StringFormatters.wishRemovalFailed();
        }else{
            Wishlist wishToRemove = wishlist.get(wish);
            Book book = wishToRemove.getBook();
            book.getWishlist().remove(wishToRemove);
            wishlistRepository.delete(wishToRemove);
            wishlist.remove(wish.intValue());
            repository.save(user);
            booksRepository.save(book);
            return StringFormatters.wishRemoved(book);
        }
    }
}
