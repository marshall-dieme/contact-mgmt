package org.spring.contacts.service;

import org.spring.contacts.model.ContactDao;
import org.spring.contacts.model.UserDao;
import org.spring.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements MyService<ContactDao> {

    private final ContactRepository repo;

    public ContactService(ContactRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ContactDao> getAll(UserDao user) {
        return repo.findByUser(user);
    }

    @Override
    public ContactDao getById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public ContactDao create(ContactDao entity) {
        return repo.save(entity);
    }

    @Override
    public ContactDao update(ContactDao entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }


}
