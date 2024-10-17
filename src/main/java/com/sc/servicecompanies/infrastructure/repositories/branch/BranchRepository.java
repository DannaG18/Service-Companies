package com.sc.servicecompanies.infrastructure.repositories.branch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.Branch;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long>{

}
