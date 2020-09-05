package com.example.modelAssembler;

import com.example.controller.OrganizerController;
import com.example.domain.Organizer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrganizerModelAssembler implements RepresentationModelAssembler<Organizer, EntityModel<Organizer>> {

    @Override
    public EntityModel<Organizer> toModel(Organizer organizer) {

        return EntityModel.of(organizer, //
                linkTo(methodOn(OrganizerController.class).one(organizer.getId())).withSelfRel(),
                linkTo(methodOn(OrganizerController.class).all()).withRel("organizers"));
    }
}
