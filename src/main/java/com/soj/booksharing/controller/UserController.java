package com.soj.booksharing.controller;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> readUsers() {
       return userService.fetchAllUsers();
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable(value = "id") Long id) {
        return userService.fetchUser(id);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable(value = "id") Long id){
        return userService.deleteUser(id);
    }

    @PostMapping
    public String add(@RequestBody User user){
       return userService.add(user);
    }

    @PutMapping(value = "/{id}")
    public String update(@RequestBody User user ,@PathVariable("id") Long id){
        return "";
    }

    @GetMapping(value = "/{id}/books")
    public List<Book> getOwnedBooks(@PathVariable("id") Long id){
        return userService.fetchOwnedBooks(id);
    }

    @PutMapping(value = "/{userId}/books")
    public String addNewBook(@PathVariable("userId") Long userId,@RequestBody Book book){
        return userService.addNewBook(book,userId);
    }

    @PutMapping(value = "/{userId}/books/rent/{bookId}/period/{period}")
    public String addRental(@PathVariable(name = "userId") Long userId, @PathVariable(name = "bookId")Long bookId, @PathVariable(name = "period")Integer period){
        return userService.addRental(userId, bookId,period);
    }

    @PutMapping(value = "/{userId}/books/{bookId}")
    public String addExistingBook(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        return userService.addExistingBook(userId, bookId);
    }

    @GetMapping(value = "/books/{id}/owners")
    public List<User> getUsersThatOwns(@PathVariable("id")Long id){
        return userService.fetchAllUsersThatOwn(id);
    }

    @GetMapping(value = "/{userId}/books/rented")
    public List<Book> rentedBooks(@PathVariable(value = "userId")Long userId){
        return userService.rentedBooksByUser(userId);
    }



}
