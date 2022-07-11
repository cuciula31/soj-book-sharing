package com.soj.booksharing.services;

import com.soj.booksharing.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> fetchAll();
    Book fetchById(Long id);
    String deleteById(Long id);
    String update( Book book, Long id);
    String add(Book book);

    List<String> booksWithTitle(String title);
    List<String> booksWithAuthor(String author);

}
