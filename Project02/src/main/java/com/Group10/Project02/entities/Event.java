package com.Group10.Project02.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "events")
public class Event {
    private @Id
    @GeneratedValue Long id;
    private String Name;
    private String Host;
    private String Location;
    private String Description;
    private String StartTime;
    private String EndTime;

    public Event() {}

    public Event(String name, String host, String location, String description, String startTime, String endTime) {
        Name = name;
        Host = host;
        Location = location;
        Description = description;
        StartTime = startTime;
        EndTime = endTime;
    }

    // Setters and Getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    // Hash and equals

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(Name, event.Name) && Objects.equals(Host, event.Host) && Objects.equals(Location, event.Location) && Objects.equals(Description, event.Description) && Objects.equals(StartTime, event.StartTime) && Objects.equals(EndTime, event.EndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, Host, Location, Description, StartTime, EndTime);
    }

}
