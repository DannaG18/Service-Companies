package com.sc.servicecompanies.infrastructure.repositories.branch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.BranchService;
import com.sc.servicecompanies.domain.entities.Branch;

public class BranchImpl implements BranchService{
    @Autowired
    private BranchRepository branchRepository;

    @Transactional
    @Override
    public Optional<Branch> delete(Long id) {
        Optional<Branch> branchOp = branchRepository.findById(id);
        branchOp.ifPresent(branchDb -> {
            branchRepository.delete(branchDb);
        });
        return branchOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Branch> findAll() {
        return (List<Branch>) branchRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Branch> findById(Long id) {
        return branchRepository.findById(id);
    }

    @Transactional
    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Transactional
    @Override
    public Optional<Branch> update(Long id, Branch branch) {
        Optional<Branch> branchOld = branchRepository.findById(id);
        if (branchOld.isPresent()) {
            Branch branchDb = branchOld.orElseThrow();
            branchDb.setNit(branch.getNit());
            branchDb.setCompany(branch.getCompany());
            branchDb.setCity(branch.getCity());
            branchDb.setNameBranch(branch.getNameBranch());
            branchDb.setCreationDate(branch.getCreationDate());
            return Optional.of(branchRepository.save(branchDb));
        }
        return Optional.empty();
    }   
}
