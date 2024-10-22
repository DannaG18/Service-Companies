package com.sc.servicecompanies.infrastructure.repositories.operation;

import com.sc.servicecompanies.domain.entities.security.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o where o.permitAll = true")
    List<Operation> findByPublicAccess();
}
