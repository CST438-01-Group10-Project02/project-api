package com.Group10.Project02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Group10.Project02.Entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.username = ?1")
    List<Users> findByUsername(String username);

    @Query("select u from Users u where u.email = ?1")
    Users findByEmail(String email);
}
