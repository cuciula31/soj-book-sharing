package com.soj.booksharing.controller;

import com.soj.booksharing.data.JwtUtil;
import com.soj.booksharing.entity.Authority;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.entity.Wishlist;
import com.soj.booksharing.exception.ExceptionHandling;
import com.soj.booksharing.repository.AuthorityRepository;
import com.soj.booksharing.repository.UserRepository;
import com.soj.booksharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> readUsers() {
       return userService.fetchAllUsers();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") Long id) {
        return userService.fetchUser(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id){
        return userService.deleteUser(id);
    }

//    @PostMapping(value = "/register")
//    public ResponseEntity<?> add(@RequestBody User user){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encryptedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encryptedPassword);
//        repository.save(user);
//
//        Authority authority = new Authority();
//        authority.setAuthority("BASIC_USER");
//        authority.setUser(user);
//
//        authorityRepository.save(authority);
//
//
//
//    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody User user ,@PathVariable("id") Long id){
        return userService.update(user, id);
    }

    @GetMapping(value = "/{id}/books")
    public ResponseEntity<List<Book>> getOwnedBooks(@PathVariable("id") Long id){
        return userService.fetchOwnedBooks(id);
    }

    @PutMapping(value = "/{userId}/books")
    public ResponseEntity<String> addNewBook(@PathVariable("userId") Long userId,@RequestBody Book book){
        return userService.addNewBook(book,userId);
    }

    @PutMapping(value = "/{userId}/books/rent/{bookId}/period/{period}")
    public ResponseEntity<String> addRental(@PathVariable(name = "userId") Long userId, @PathVariable(name = "bookId")Long bookId, @PathVariable(name = "period")Integer period){
        return userService.addRental(userId, bookId,period);
    }

    @PutMapping(value = "/{userId}/books/{bookId}")
    public ResponseEntity<String> addExistingBook(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        return userService.addExistingBook(userId, bookId);
    }

    @GetMapping(value = "/books/{id}/owners")
    public ResponseEntity<List<User>> getUsersThatOwns(@PathVariable("id")Long id){
        return userService.fetchAllUsersThatOwn(id);
    }

    @GetMapping(value = "/{userId}/books/rented")
    public ResponseEntity<List<String>> rentedBooks(@PathVariable(value = "userId")Long userId){
        return userService.rentedBooksByUser(userId);
    }

    @GetMapping(value = "/{userId}/books/whorented")
    public ResponseEntity<List<String>> whoRentedFromMe(@PathVariable(value = "userId")Long userId){
        return userService.whoRentedMyBooks(userId);
    }

    @GetMapping(value = "/{userId}/wishlist")
    public ResponseEntity<List<Wishlist>> getWishlistWhereUserIs(@PathVariable(value = "userId")Long userId){
        return userService.wishListByUserId(userId);
    }

    @PutMapping(value = "/{userId}/books/{bookId}/addtowishlist")
    public ResponseEntity<String> addToWishlist(@PathVariable(value = "userId")Long userId, @PathVariable(value = "bookId")Long bookId){
        return userService.addToWishlist(userId,bookId);
    }

    @PutMapping(value = "/{userId}/wishlist/remove/{wishId}")
    public ResponseEntity<String> removeFromWishList(@PathVariable(value = "userId") Long userId, @PathVariable(value = "wishId")Integer wishId){
        return userService.deleteWish(userId, wishId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> validationCheck(MethodArgumentNotValidException e){
        return ExceptionHandling.handleForValidationErrors(e);
    }

}
