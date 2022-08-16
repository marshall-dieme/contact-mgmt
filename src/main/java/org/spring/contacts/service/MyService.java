package org.spring.contacts.service;

import org.spring.contacts.model.UserDao;

import java.util.List;

public interface MyService<E> {
    List<E> getAll(UserDao dao);

    E getById(int id);

    E create(E entity);

    E update(E entity);

    void delete(int id);
}
