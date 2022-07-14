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
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
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
    }

}
