package com.soj.booksharing.services;

import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService{

    private final BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<Book> fetchAll() {
       return booksRepository.findAll();
    }

    @Override
    public Book fetchById(@NotNull Long id) {
        return booksRepository.findById(id).get();
    }

    @Override
    public String deleteById(Long id) {
        booksRepository.deleteById(id);
        return "Book with id: %s deleted".formatted(id);
    }

    @Override
    public String update(Book book,Long id) {
        Book toBeUpdated = booksRepository.findById(id).get();

        if (Objects.nonNull(book.getBookTitle())
                && !"".equalsIgnoreCase(
                book.getBookTitle())) {
            toBeUpdated.setBookTitle(
                    book.getBookTitle());
        }

        if (Objects.nonNull(book.getAuthor())
                && !"".equalsIgnoreCase(
                book.getBookTitle())) {
            toBeUpdated.setAuthor(
                    book.getAuthor());
        }

        booksRepository.save(toBeUpdated);

        return "Book with id: %s updated".formatted(id);

    }

    @Override
    public String add(Book book) {
        booksRepository.save(book);
        return "Book with id: %s added".formatted(book.getId());
    }

    @Override
    public List<Book> booksWithTitle(String title) {
        return booksRepository.findByBookTitleIgnoreCaseContaining(title);
    }

    @Override
    public List<Book> booksWithAuthor(String author) {
        return booksRepository.findByAuthorIgnoreCaseContaining(author);
    }


}
