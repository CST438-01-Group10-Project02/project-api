package com.Group10.Project02;

import com.Group10.Project02.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
