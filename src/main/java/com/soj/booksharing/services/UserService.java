package com.soj.booksharing.services;


import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.entity.Wishlist;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {



    User fetchUser(@NotNull Long id);

    List<User> fetchAllUsers();

    String deleteUser(Long id);

    String update(User user,Long id);
    String add(User user);
    String addExistingBook(Long userId, Long bookId);

    String addRental(Long userId, Long bookId, Integer rentInterval);
    String addNewBook(Book book, Long userId);
    String addToWishlist(Long userId, Long bookId);
    List<Book> fetchOwnedBooks(Long id);
    List<String> rentedBooksByUser(Long id);
    List<User> fetchAllUsersThatOwn(Long id);
    List<String> whoRentedMyBooks(Long userId);
    List<Wishlist> wishListByUserId(Long userId);

    String deleteWish(Long userId, Integer wish);
}
