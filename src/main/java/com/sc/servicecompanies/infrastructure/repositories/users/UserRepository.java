package com.sc.servicecompanies.infrastructure.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sc.servicecompanies.domain.entities.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
