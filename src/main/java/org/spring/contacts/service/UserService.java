package org.spring.contacts.service;

import org.spring.contacts.model.UserDao;
import org.spring.contacts.repository.UserRepository;
import org.spring.contacts.utils.LoginRequest;
import org.spring.contacts.utils.LoginResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserDao registration(UserDao dao) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(dao.getPassword());
        dao.setPassword(encodedPwd);
        return repo.save(dao);
    }

    /*public LoginResponse login(LoginRequest request) {

    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDao dao = repo.findByUsername(username);
        if (dao == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        return dao;
    }
}
