package com.soj.booksharing.assembler;

import com.soj.booksharing.controller.UserRESTController;
import com.soj.booksharing.entity.User;

import com.sun.istack.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(@NotNull User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserRESTController.class).getUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserRESTController.class).readUsers()).withRel("user"));
    }
}
