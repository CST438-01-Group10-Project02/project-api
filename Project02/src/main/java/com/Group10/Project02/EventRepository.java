package com.Group10.Project02;

import com.Group10.Project02.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Singles

    @Query("select e from Event e where e.Location = ?1")
    List<Event> findByLocation(String location);

    @Query("select e from Event e where e.Host.id = ?1")
    List<Event> findByHostId(Long id);

    @Query("select e from Event e where e.Date = ?1")
    List<Event> findByDate(String date);

    // Doubles

    @Query("select e from Event e where e.Location = ?1 and e.Host.id = ?2")
    List<Event> findByLocationHostId(String location, Long id);

    @Query("select e from Event e where e.Location = ?1 and e.Date = ?2")
    List<Event> findByLocationDate(String location, String date);

    @Query("select e from Event e where e.Host.id = ?1 and e.Date = ?2")
    List<Event> findByHostIdDate(Long id, String date);

    // Triples

    @Query("select e from Event e where e.Location = ?1 and e.Host.id = ?2 and e.Date = ?3")
    List<Event> findByLocationHostIdDate(String location, Long id, String date);
}
