package com.Group10.Project02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Group10.Project02.Entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e order by e.Date")
    List<Event> findAllSortDate();

    // Singles
    @Query("select e from Event e where e.Date > ?1 order by e.Date")
    List<Event> findAfter(String date);

    @Query("select e from Event e where e.Location = ?1 order by e.Date")
    List<Event> findByLocation(String location);

    @Query("select e from Event e where e.Host.id = ?1 order by e.Date")
    List<Event> findByHostId(Long id);

    @Query("select e from Event e where e.Date = ?1 order by e.Date")
    List<Event> findByDate(String date);

    // Doubles
    @Query("select e from Event e where e.Location = ?1 and e.Host.id = ?2 order by e.Date")
    List<Event> findByLocationHostId(String location, Long id);

    @Query("select e from Event e where e.Location = ?1 and e.Date = ?2 order by e.Date")
    List<Event> findByLocationDate(String location, String date);

    @Query("select e from Event e where e.Host.id = ?1 and e.Date = ?2 order by e.Date")
    List<Event> findByHostIdDate(Long id, String date);

    @Query("select e from Event e where e.Location = ?1 and e.Date > ?2 order by Date")
    List<Event> findByLocationAfter(String location, String after);

    @Query("select e from Event e where e.Host.id = ?1 and e.Date > ?2 order by Date")
    List<Event> findByHostIdAfter(Long id, String after);

    // Triples
    @Query("select e from Event e where e.Location = ?1 and e.Host.id = ?2 and e.Date = ?3 order by e.Date")
    List<Event> findByLocationHostIdDate(String location, Long id, String date);

    @Query("select e from Event e where e.Location = ?1 and e.Host.id = ?2 and e.Date > ?3 order by e.Date")
    List<Event> findByLocationHostId(String location, Long id, String after);

    @Query("select e from Event e where e.Location = ?1 and e.Date = ?2 and e.Date > ?3 order by e.Date")
    List<Event> findByLocationDate(String location, String date, String after);

    @Query("select e from Event e where e.Host.id = ?1 and e.Date = ?2 and e.Date > ?3 order by e.Date")
    List<Event> findByHostIdDate(Long id, String date, String after);
}
