package com.example.repository;

import com.example.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository  extends JpaRepository<Event, Long> {

}
