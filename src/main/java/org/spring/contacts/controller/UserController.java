package org.spring.contacts.controller;

import org.spring.contacts.model.UserDao;
import org.spring.contacts.service.UserService;
import org.spring.contacts.utils.LoginRequest;
import org.spring.contacts.utils.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService service;

    @Autowired
    private AuthenticationManager auth;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDao> register(@RequestBody UserDao dao) {
        return new ResponseEntity<>(service.registration(dao), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        authentication(request.getUsername(), request.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        UserDao user = (UserDao) service.loadUserByUsername(request.getUsername());
        loginResponse.setId(user.getId());
        loginResponse.setUsername(user.getUsername());
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

    private void authentication(String username, String password) {
        try {
            auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad Credentials!!!!!!!");
        } catch (DisabledException e) {
            throw new DisabledException("Account disabled!!!");
        }
    }
}
