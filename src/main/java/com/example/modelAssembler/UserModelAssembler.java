package com.example.modelAssembler;

import com.example.controller.UserController;
import com.example.domain.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {

        try {
            return EntityModel.of(user, //
                    linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).all()).withRel("users"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
