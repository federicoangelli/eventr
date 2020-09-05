package com.example.modelAssembler;

import com.example.controller.EventController;
import com.example.domain.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {

    @Override
    public EntityModel<Event> toModel(Event event) {

        return EntityModel.of(event,
                linkTo(methodOn(EventController.class).one(event.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class).all()).withRel("events"));
    }
}
