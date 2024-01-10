package com.thrivefuse.ums.repository;

import com.thrivefuse.ums.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.userCredentials.username = :username")
    Optional<User> findByUsername(String username);
}
