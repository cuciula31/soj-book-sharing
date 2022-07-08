package com.soj.booksharing;

import com.soj.booksharing.controller.UserController;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.services.BookService;
import com.soj.booksharing.services.RentalService;
import com.soj.booksharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SojBookSharingApplication implements CommandLineRunner {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @Autowired
    RentalService rentalService;

    public static void main(String[] args) {
        SpringApplication.run(SojBookSharingApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

//        User user = new User();
//        user.setUser("Cuciula31");
//        user.setName("Cuciula");
//        user.setSurname("Sebastian");
//        user.setPassword("1750607");
//        user.setEmail("cuciula31@gmail.com");
//
//        List<Book> list = new ArrayList<>();
//
//        Book book = new Book();
//        book.setBookTitle("Carte");
//        book.setAuthor("Autor");
////        book.getUsers().add();
//        list.add(book);
////        user.setOwnedBooks(list);
//
//        RentedBook rentedBook = new RentedBook();
//        rentedBook.setUser(user);
//
//
//        user.getRentedBooks().add(rentedBook);
//        rentedBook.setBook(book);
//        userService.add(user);
//
//        bookService.add(book);
//
//        rentalService.addNew(rentedBook);
////        userService.add(user);

    }

}
