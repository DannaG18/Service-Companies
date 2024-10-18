package com.sc.servicecompanies.application.services.auth;

import com.sc.servicecompanies.application.services.UserService;
import com.sc.servicecompanies.domain.entities.User;
import com.sc.servicecompanies.domain.entities.dto.RegisterUser;
import com.sc.servicecompanies.domain.entities.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    public RegisterUser registerOneCustomer(UserDto newUser) {
        User user = userService.registrOneCustomer(newUser);

        RegisterUser userDto = new RegisterUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }
}
