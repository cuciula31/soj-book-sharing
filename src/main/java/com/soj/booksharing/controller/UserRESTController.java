package com.soj.booksharing.controller;

import com.soj.booksharing.advice.UserNotFoundAdvice;
import com.soj.booksharing.assembler.UserModelAssembler;
import com.soj.booksharing.entity.Books;
import com.soj.booksharing.entity.User;
import com.soj.booksharing.exception.user.UserNotFoundException;
import com.soj.booksharing.repository.BooksRepository;
import com.soj.booksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRESTController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserModelAssembler assembler;
    @Autowired
    private BooksRepository booksRepository;


    @GetMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> readUsers(){
        repository.findAll().forEach(u  -> System.out.println(u.getName()));

        return new ArrayList<>(repository.findAll());
    }

    @GetMapping(value = "/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EntityModel<User> getUserById(@PathVariable int id){
        User user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createUser(@RequestBody User user){
        EntityModel<User> entityModel = assembler.toModel(repository.save(user));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
