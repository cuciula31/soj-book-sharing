package com.soj.booksharing.controller;

import com.soj.booksharing.data.RentingIntervals;
import com.soj.booksharing.entity.Book;
import com.soj.booksharing.entity.RentedBook;
import com.soj.booksharing.services.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentedBook> all(){
        return rentalService.fetchAll();
    }

    @GetMapping(value = "/{id}")
    public RentedBook getById(@PathVariable(value = "id") Long id){
        return rentalService.fetchById(id);
    }

    @PostMapping
    public String add(@RequestBody RentedBook rentedBook){
        return rentalService.addNew(rentedBook);
    }

//    @PostMapping(value = "/user/{userId}/book/{bookId}")
//    public String add(@PathVariable(value = "userId") Long userId,
//                      @PathVariable(value = "bookId") Long bookId, @RequestBody RentingIntervals rentingIntervals){
//        return rentalService.addNew(bookId,userId,rentingIntervals);
//    }

    @PutMapping(value = "/{id}")
    public String update(@RequestBody RentedBook rentedBook, @PathVariable(value = "id") Long id){
        return rentalService.update(rentedBook, id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        return rentalService.delete(id);
    }

    @GetMapping(value = "/available")
    public List<Book> available(){
        return rentalService.availableBooks();
    }


}
