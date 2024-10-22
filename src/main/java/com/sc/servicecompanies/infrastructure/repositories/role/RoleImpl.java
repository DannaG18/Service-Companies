package com.sc.servicecompanies.infrastructure.repositories.role;

import com.sc.servicecompanies.application.services.RoleService;
import com.sc.servicecompanies.domain.entities.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Value("${security.default.role}")
    private String defaultRole;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }
}
