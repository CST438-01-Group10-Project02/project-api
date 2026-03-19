package com.Group10.Project02.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Event {

    //Events field
    private @Id
    @GeneratedValue Long id;
    private String name;
    private Long hostId;
    private String location;
    private String description;
    private String startTime;
    private String endTime;
    private String date;

    // Constructors
    public Event() {}

    public Event(String name, Long hostId, String location, String description, String startTime, String endTime, String date) {
        this.name = name;
        this.hostId = hostId;
        this.location = location;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    // Setters and Getters

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

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.endTime = date;
    }
// Hash and equals

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(hostId, event.hostId) && Objects.equals(location, event.location) && Objects.equals(description, event.description) && Objects.equals(startTime, event.startTime) && Objects.equals(endTime, event.endTime) && Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hostId, location, description, startTime, endTime, date);
    }
}
