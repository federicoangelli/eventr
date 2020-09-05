package com.example.controller;

import com.example.domain.Organizer;
import com.example.modelAssembler.OrganizerModelAssembler;
import com.example.exceptions.OrganizerNotFoundException;
import com.example.repository.OrganizerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrganizerController {


    private final OrganizerRepository repository;
    private final OrganizerModelAssembler assembler;

    OrganizerController(OrganizerRepository repository, OrganizerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/organizers")
    public CollectionModel<EntityModel<Organizer>> all() {

        List<EntityModel<Organizer>> organizers = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(organizers, linkTo(methodOn(OrganizerController.class).all()).withSelfRel());
    }

    @PostMapping("/organizers")
    ResponseEntity<?> newOrganizer(@RequestBody Organizer newOrganizer) {

        EntityModel<Organizer> entityModel = assembler.toModel(repository.save(newOrganizer));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item

    @GetMapping("/organizers/{id}")
    public EntityModel<Organizer> one(@PathVariable Long id) {

        Organizer organizer = repository.findById(id) //
                .orElseThrow(() -> new OrganizerNotFoundException(id));

        return assembler.toModel(organizer);
    }

    @PutMapping("/organizers/{id}")
    ResponseEntity<?> replaceOrganizer(@RequestBody Organizer newOrganizer, @PathVariable Long id) {

        Organizer updatedOrganizer = repository.findById(id) //
                .map(organizer -> {
                    organizer.setName(newOrganizer.getName());
                    organizer.setEmail(newOrganizer.getEmail());
                    organizer.setPhoneNumber(newOrganizer.getPhoneNumber());
                    organizer.setAddress(newOrganizer.getAddress());

                    return repository.save(organizer);
                }) //
                .orElseGet(() -> {
                    newOrganizer.setId(id);
                    return repository.save(newOrganizer);
                });

        EntityModel<Organizer> entityModel = assembler.toModel(updatedOrganizer);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/organizers/{id}")
    ResponseEntity<?> deleteOrganizer(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
