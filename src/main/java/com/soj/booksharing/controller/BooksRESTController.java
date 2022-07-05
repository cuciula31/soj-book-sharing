package com.soj.booksharing.controller;

import com.soj.booksharing.assembler.BookModelAssembler;
import com.soj.booksharing.entity.Books;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.exception.user.UserNotFoundException;
import com.soj.booksharing.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksRESTController {

    @Autowired
    private BooksRepository repository;
    @Autowired
    private BookModelAssembler assembler;


    @GetMapping(value = "/books")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Books> readBooks(){
        return new ArrayList<>(repository.findAll());
    }

    @GetMapping(value = "/books/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EntityModel<Books> getBooksById(@PathVariable int id){
        Books user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

    @PostMapping(value = "/books")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createBook(@RequestBody Books books){
        EntityModel<Books> entityModel = assembler.toModel(repository.save(books));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
