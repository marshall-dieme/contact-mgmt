package org.spring.contacts.controller;

import org.spring.contacts.model.ContactDao;
import org.spring.contacts.model.UserDao;
import org.spring.contacts.service.ContactService;
import org.spring.contacts.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService service;
    private final UserService userService;

    public ContactController(ContactService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDao>> getContacts() {
        return ResponseEntity.ok(service.getAll(getPrincipal()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDao> getContact(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ContactDao> saveContact(@RequestBody ContactDao dao) {
        dao.setUser(getPrincipal());
        return new ResponseEntity<>(service.create(dao), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ContactDao> updateContact(@RequestBody ContactDao dao) {
        return new ResponseEntity<>(service.update(dao), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Contact Deleted");
    }

    @DeleteMapping("/void/{id}")
    public ResponseEntity<Void> deleteContactWithVoid(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    private UserDao getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return (UserDao) userService.loadUserByUsername(username);
    }
}
