package com.sc.servicecompanies.infrastructure.repositories.users;

import com.sc.servicecompanies.application.services.RoleService;
import com.sc.servicecompanies.application.services.UserService;
import com.sc.servicecompanies.domain.entities.dto.UserDto;
import com.sc.servicecompanies.domain.entities.security.Role;
import com.sc.servicecompanies.domain.entities.security.User;
import com.sc.servicecompanies.infrastructure.utils.exceptions.InvalidPasswordException;
import com.sc.servicecompanies.infrastructure.utils.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User registrOneCustomer(UserDto newUser) {
        validatePassword(newUser);

        User user = new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        Role defaultRole = roleService.findDefaultRole()
                .orElseThrow(() -> new ObjectNotFoundException("Role not found."));
        user.setRole(defaultRole);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(UserDto dto) {

        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())) {
            throw new InvalidPasswordException("Passwords don't match");
        }

        if(!dto.getPassword().equals(dto.getRepeatedPassword())) {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
    
}
