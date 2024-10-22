package com.sc.servicecompanies.infrastructure.repositories.jwttoken;

import com.sc.servicecompanies.domain.entities.security.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
}
