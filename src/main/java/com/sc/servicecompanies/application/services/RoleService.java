package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.security.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
