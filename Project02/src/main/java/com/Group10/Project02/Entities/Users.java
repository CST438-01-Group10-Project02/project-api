package com.Group10.Project02.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Users {
    private @Id
    @GeneratedValue long id;

    private String authId;
    private String username;
    private String email;
    private String role;
    private String bio;

    public Users() {
    }

    public Users(String authId, String username, String email, String role, String bio) {
        this.authId = authId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    // test change
    
    public void setId(long id) {
        this.id = id;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(authId, users.authId) &&
                Objects.equals(username, users.username) &&
                Objects.equals(email, users.email) &&
                Objects.equals(role, users.role) &&
                Objects.equals(bio, users.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authId, username, email, role, bio);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", authId='" + authId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}