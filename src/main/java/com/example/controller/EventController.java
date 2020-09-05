package com.example.controller;

import com.example.domain.Event;
import com.example.modelAssembler.EventModelAssembler;
import com.example.exceptions.EventNotFoundException;
import com.example.repository.EventRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EventController{


    private final EventRepository repository;
    private final EventModelAssembler assembler;

    EventController(EventRepository repository, EventModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/events")
    public CollectionModel<EntityModel<Event>> all() {

        List<EntityModel<Event>> events = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(events, linkTo(methodOn(EventController.class).all()).withSelfRel());
    }

    @PostMapping("/events")
    ResponseEntity<?> newEvent(@RequestBody Event newEvent) {

        EntityModel<Event> entityModel = assembler.toModel(repository.save(newEvent));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item

    @GetMapping("/events/{id}")
    public EntityModel<Event> one(@PathVariable Long id) {

        Event event = repository.findById(id) //
                .orElseThrow(() -> new EventNotFoundException(id));

        return assembler.toModel(event);
    }

    @PutMapping("/events/{id}")
    ResponseEntity<?> replaceEvent(@RequestBody Event newEvent, @PathVariable Long id) {

        Event updatedEvent = repository.findById(id) //
                .map(event -> {
                    event.setName(newEvent.getName());
                    event.setCategory(newEvent.getCategory());
                    event.setDescription(newEvent.getDescription());
                    event.setOrganizer(newEvent.getOrganizer());
                    event.setLocation(newEvent.getLocation());
                    event.setDateAndTime(newEvent.getDateAndTime());
                    event.setMaximumParticipants(newEvent.getMaximumParticipants());
                    event.setRegisteredParticipants(newEvent.getRegisteredParticipants());
                    event.setActualParticipants(newEvent.getActualParticipants());
                    event.setType(newEvent.getType());
                    event.setRating(newEvent.getRating());
                    event.setExtra(newEvent.getExtra());
                    return repository.save(event);
                }) //
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return repository.save(newEvent);
                });

        EntityModel<Event> entityModel = assembler.toModel(updatedEvent);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/events/{id}")
    ResponseEntity<?> deleteEvent(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
