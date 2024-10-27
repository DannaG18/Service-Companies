package com.sc.servicecompanies.application.services.auth;

import com.sc.servicecompanies.application.services.UserService;
import com.sc.servicecompanies.domain.entities.security.JwtToken;
import com.sc.servicecompanies.domain.entities.security.User;
import com.sc.servicecompanies.domain.entities.dto.RegisterUser;
import com.sc.servicecompanies.domain.entities.dto.UserDto;
import com.sc.servicecompanies.domain.entities.dto.auth.AuthenticationRequest;
import com.sc.servicecompanies.domain.entities.dto.auth.AuthenticationResponse;
import com.sc.servicecompanies.infrastructure.utils.exceptions.ObjectNotFoundException;
import com.sc.servicecompanies.infrastructure.repositories.jwttoken.JwtTokenRepository;

import org.springframework.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenRepository jwtRepository;

    public RegisterUser registerOneCustomer(UserDto newUser){
        User user = userService.registrOneCustomer(newUser);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        saveUserToken(user, jwt);
        RegisterUser userDto = new RegisterUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().getName());

        userDto.setJwt(jwt);

        return userDto;
    }
    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().getName());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }
    public AuthenticationResponse login(AuthenticationRequest autRequest) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                autRequest.getUsername(), autRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUserName(autRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));
        saveUserToken((User) user, jwt);
        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);

        return authRsp;
    }

    private void saveUserToken(User user, String jwt) {
        JwtToken token = new JwtToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setValid(true);

        jwtRepository.save(token);
    }

    public boolean validateToken(String jwt) {

        try{
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
    public User findLoggedInUser() {
        UsernamePasswordAuthenticationToken auth =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String username = (String) auth.getPrincipal();
        return userService.findOneByUserName(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
    }

    public void logout(HttpServletRequest request) {

        String jwt = jwtService.extractJwtFromRequest(request);
        if(jwt == null || !StringUtils.hasText(jwt)) return;

        Optional<JwtToken> token = jwtRepository.findByToken(jwt);

        if(token.isPresent()  && token.get().isValid()){
            token.get().setValid(false);
            jwtRepository.save(token.get());
        }
    }
}