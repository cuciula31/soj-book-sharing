package com.soj.booksharing.assembler;

import com.soj.booksharing.controller.UserRESTController;
import com.soj.booksharing.entity.Books;
import com.soj.booksharing.entity.User;
import com.sun.istack.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Books, EntityModel<Books>> {

    @Override
    public EntityModel<Books> toModel(@NotNull Books books) {
        return EntityModel.of(books,
                linkTo(methodOn(UserRESTController.class).getUserById(books.getId())).withSelfRel(),
                linkTo(methodOn(UserRESTController.class).readUsers()).withRel("books"));
    }

}
