package com.example.controller;

import com.example.domain.User;
import com.example.modelAssembler.UserModelAssembler;
import com.example.exceptions.UserNotFoundException;
import com.example.repository.UserRepository;
import com.example.services.FireBaseServiceUser;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;
    private final FireBaseServiceUser fireBaseServiceUser;

    UserController(UserRepository repository, UserModelAssembler assembler, FireBaseServiceUser fireBaseServiceUser) {
        this.repository = repository;
        this.assembler = assembler;
        this.fireBaseServiceUser = fireBaseServiceUser;
    }

    // Aggregate root

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() throws ExecutionException, InterruptedException {

        User user = fireBaseServiceUser.getAllUserDetails();

        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) throws ExecutionException, InterruptedException {

        EntityModel<User> entityModel = assembler.toModel(fireBaseServiceUser.saveUserDetails(newUser));
        //fireBaseServiceUser.saveUserDetails(newUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) throws ExecutionException, InterruptedException {

//        User user = repository.findById(id) //
//                .orElseThrow(() -> new UserNotFoundException(id));

        User user = fireBaseServiceUser.getOneUserDetails(id);

        if(user != null) {
            return assembler.toModel(user);}
        else {
            return null;
        }
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) throws ExecutionException, InterruptedException {

        fireBaseServiceUser.updateUserDetails(newUser, id);

        User updatedUser = repository.findById(id) //
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    user.setAddress(newUser.getAddress());

                    return repository.save(user);
                }) //
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });

        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        fireBaseServiceUser.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
