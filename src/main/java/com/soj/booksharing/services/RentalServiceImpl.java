package com.soj.booksharing.services;

import com.soj.booksharing.dao.UserDAO;
import com.soj.booksharing.data.*;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final UserRepository repository;
    private final BooksRepository booksRepository;
    private final RentalRepository rentalRepository;

    public RentalServiceImpl(UserRepository repository, BooksRepository booksRepository, RentalRepository rentalRepository) {
        this.repository = repository;
        this.booksRepository = booksRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public ResponseEntity<List<RentedBook>> fetchAll() {
        return ResponseEntity.ok(rentalRepository.findAll());
    }

    @Override
    public ResponseEntity<RentedBook> fetchById(Long id) {

        if (rentalRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(rentalRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<String> addNew(RentedBook rentedBook) {
        rentalRepository.save(rentedBook);
        return ResponseEntity.ok("You have successfully rented a book!");
    }


    @Override
    public ResponseEntity<String> update(RentedBook rentedBook, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        rentalRepository.delete(rentalRepository.findById(id).get());
        return ResponseEntity.ok("Rent ended successfully");
    }

    @Override
    public ResponseEntity<List<String>> availableBooks() {
        List<String> toReturn = new ArrayList<>();

        for (Book b : booksRepository.findAll()) {
            if (RentalUtils.checkIfAvailable(b.getId(), booksRepository, rentalRepository)) {
                toReturn.add(StringFormatters.availableBook(b));
            }
        }
        return ResponseEntity.ok(toReturn);
    }

    @Override
    public ResponseEntity<String> extend(Long id) {
        RentedBook toBeUpdated = rentalRepository.findById(id).get();

        if (!toBeUpdated.getWasExtended()) {
            toBeUpdated.setWasExtended(true);
            LocalDate currentEndDatePlusOneWeek = toBeUpdated.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusWeeks(1);
            toBeUpdated.setEndDate(Date.from(currentEndDatePlusOneWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()));

            rentalRepository.save(toBeUpdated);
            return ResponseEntity.ok(StringFormatters.rentalExtended(id));
        } else {
            return ResponseEntity.ok(StringFormatters.rentalFailed());
        }

    }


}
