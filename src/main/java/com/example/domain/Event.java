package com.example.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public
class Event {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String category;
    private String description;
    private Long organizer;
    private String location;
    private Date dateAndTime;
    private int maximumParticipants;
    private int registeredParticipants;
    private int actualParticipants;
    private String type;
    private double rating;
    private String extra;

    public Event() {
        name = "";
        category = "";
        description = "";
        organizer = null;
        location = "";
        dateAndTime = null;
        maximumParticipants = 0;
        registeredParticipants = 0;
        actualParticipants = 0;
        type = "";
        rating = 0;
        extra = "";
    }

    public Event(String name, String category, String description, Long organizer, String location, Date dateAndTime,
                 int maximumParticipants, int registeredParticipants, int actualParticipants, String type, double rating,
                 String extra) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.organizer = organizer;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.maximumParticipants = maximumParticipants;
        this.registeredParticipants = registeredParticipants;
        this.actualParticipants = actualParticipants;
        this.type = type;
        this.rating = rating;
        this.extra = extra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Long organizer) {
        this.organizer = organizer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getMaximumParticipants() {
        return maximumParticipants;
    }

    public void setMaximumParticipants(int maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }

    public int getRegisteredParticipants() {
        return registeredParticipants;
    }

    public void setRegisteredParticipants(int registeredParticipants) {
        this.registeredParticipants = registeredParticipants;
    }

    public int getActualParticipants() {
        return actualParticipants;
    }

    public void setActualParticipants(int actualParticipants) {
        this.actualParticipants = actualParticipants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return maximumParticipants == event.maximumParticipants &&
                registeredParticipants == event.registeredParticipants &&
                actualParticipants == event.actualParticipants &&
                Double.compare(event.rating, rating) == 0 &&
                id.equals(event.id) &&
                name.equals(event.name) &&
                category.equals(event.category) &&
                description.equals(event.description) &&
                Objects.equals(organizer, event.organizer) &&
                location.equals(event.location) &&
                Objects.equals(dateAndTime, event.dateAndTime) &&
                Objects.equals(type, event.type) &&
                Objects.equals(extra, event.extra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, organizer, location, dateAndTime, maximumParticipants, registeredParticipants, actualParticipants, type, rating, extra);
    }
}
