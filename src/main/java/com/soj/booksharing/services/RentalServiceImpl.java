package com.soj.booksharing.services;

import com.soj.booksharing.dao.UserDAO;
import com.soj.booksharing.data.*;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.RentalRepository;
import com.soj.booksharing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RentedBook> fetchAll() {
        return rentalRepository.findAll();
    }

    @Override
    public RentedBook fetchById(Long id) {
        return rentalRepository.findById(id).get();
    }

    @Override
    public String addNew(RentedBook rentedBook) {
        rentalRepository.save(rentedBook);
        return "You have successfully rented a book!";
    }


    @Override
    public String update(RentedBook rentedBook, Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        rentalRepository.delete(rentalRepository.findById(id).get());
        return "Rent ended successfully";
    }

    @Override
    public List<String> availableBooks() {
        List<String> toReturn = new ArrayList<>();

        for (Book b : booksRepository.findAll()){
            if(RentalUtils.checkIfAvailable(b.getId(),booksRepository,rentalRepository)){
                toReturn.add(StringFormatters.availableBook(b));
            }
        }
        return toReturn;
    }

    @Override
    public String extend(Long id) {
        RentedBook toBeUpdated = rentalRepository.findById(id).get();

        if (!toBeUpdated.getWasExtended()){
            toBeUpdated.setWasExtended(true);
            LocalDate currentEndDatePlusOneWeek = toBeUpdated.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusWeeks(1);
            toBeUpdated.setEndDate(Date.from(currentEndDatePlusOneWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()));

            rentalRepository.save(toBeUpdated);
            return StringFormatters.rentalExtended(id);
        }else{
            return StringFormatters.rentalFailed();
        }

    }


}
