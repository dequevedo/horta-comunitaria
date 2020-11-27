package com.energy.users.repository;

import com.energy.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(UUID userId);

    Collection<User> findByEmailAndPassword(String email, String password);

    Collection<User> findByEmail(String email);
}