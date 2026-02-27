package com.Group10.Project02;

import com.Group10.Project02.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select u from Users u where u.username = ?1")
    List<Users> findByUsername(String username);
}
