package com.Group10.Project02.Entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String email;

    @Column(name = "auth_id")
    private String authId;

    @Column(name = "role")
    private String role;

    @Column(name = "bio")
    private String bio;

    public Users() {
    }

    public Users(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public long getId() {
        return id;
    }

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
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id
                && Objects.equals(username, users.username)
                && Objects.equals(email, users.email)
                && Objects.equals(authId, users.authId)
                && Objects.equals(role, users.role)
                && Objects.equals(bio, users.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, authId, role, bio);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", authId='" + authId + '\'' +
                ", role='" + role + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}