package com.soj.booksharing;

import com.soj.booksharing.controller.UserRESTController;
import com.soj.booksharing.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SojBookSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SojBookSharingApplication.class, args);
        UserRESTController userRESTController = new UserRESTController();
    }

}
