package com.sc.servicecompanies.infrastructure.repositories.approvalstatus;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.ApprovalStatusService;
import com.sc.servicecompanies.domain.entities.ApprovalStatus;

@Service
public class ApprovalStatusImpl implements ApprovalStatusService{
    @Autowired
    private ApprovalStatusRepository approvalStatusRepository;

    @Transactional
    @Override
    public Optional<ApprovalStatus> delete(Long id) {
        Optional<ApprovalStatus> approvalStatusOp = approvalStatusRepository.findById(id);
        approvalStatusOp.ifPresent(approvalStatusDb -> {
            approvalStatusRepository.delete(approvalStatusDb);
        });
        return approvalStatusOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApprovalStatus> findAll() {
        return (List<ApprovalStatus>) approvalStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ApprovalStatus> findById(Long id) {
        return approvalStatusRepository.findById(id);
    }

    @Transactional
    @Override
    public ApprovalStatus save(ApprovalStatus approvalStatus) {
        return approvalStatusRepository.save(approvalStatus);
    }

    @Transactional
    @Override
    public Optional<ApprovalStatus> update(Long id, ApprovalStatus approvalStatus) {
        Optional<ApprovalStatus> approvalStatusOld = approvalStatusRepository.findById(id);
        if (approvalStatusOld.isPresent()) {
            ApprovalStatus approvalStatusDb = approvalStatusOld.orElseThrow();
            approvalStatusDb.setNameApprovalStatus(approvalStatus.getNameApprovalStatus());
            return Optional.of(approvalStatusRepository.save(approvalStatusDb));
        }
        return Optional.empty();
    } 
}
