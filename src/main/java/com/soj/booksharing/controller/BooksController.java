package com.soj.booksharing.controller;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books")

public class BooksController {

 private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> readBooks(){
        return bookService.fetchAll();
    }

    @GetMapping(value = "/{id}")
    public Book getById(@PathVariable(value = "id") Long id){
        return bookService.fetchById(id);
    }

    @GetMapping("/title")
    public List<Book> getByName(@RequestBody  String title){
        return bookService.booksWithTitle(title);
    }

    @GetMapping("/author")
    public List<Book> getByAuthor(@RequestBody  String author){
        return bookService.booksWithAuthor(author);
    }

    @DeleteMapping(value =  "/{id}")
    public String deleteById(@PathVariable(value = "id") Long id){
        return bookService.deleteById(id);
    }

    @PostMapping
    public String add(@RequestBody Book book){
        return bookService.add(book);
    }

    @PutMapping(value = "/{id}")
    public String update(@RequestBody Book book, @PathVariable("id") Long id){
        return "";
    }



}
