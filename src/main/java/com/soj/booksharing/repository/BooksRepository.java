package com.soj.booksharing.repository;

import com.soj.booksharing.entity.Books;
import com.soj.booksharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> { }
